package _8LectoresEscritoresINJUSTO;

import java.util.concurrent.Semaphore;

//NO ES UNA IMPLEMENTACIÓN JUSTA
//Los lectores podrían no dejar a los escritores entrar nunca
public class GestorBD {

	//CS - Escritores
	private Semaphore escribiendo = new Semaphore(1); //Semáforo binario - exclusión mutua
	private int nLectores = 0;
	private int nEsc = 0;
	private Semaphore mutex1 = new Semaphore(1); //Semáforo binario - protege nLeidos
	private Semaphore mutex2 = new Semaphore(1); //Semáforo binario - protege nLeidos
	private Semaphore mutex3 = new Semaphore(1); //Semáforo binario - protege nLeidos
	private Semaphore leyendo = new Semaphore(1); //Semáforo binario - exclusión mutua
	
	
	
	public void entraEscritor(int id) throws InterruptedException{
		mutex2.acquire();
		nEsc++;
		if(nEsc == 1) leyendo.acquire();
		mutex2.release();
		
		escribiendo.acquire();
		
		System.out.println("Escritor " + id + " entrando a la BD ...");
		
	}
	
	public void saleEscritor(int id) throws InterruptedException{
		System.out.println("Escritor " + id + " saliendo de la BD ...");
		
		escribiendo.release();
		mutex2.acquire();
		nEsc--;
		if(nEsc == 0) leyendo.release();
		mutex2.release();
	}
	
	public void entraLector(int id) throws InterruptedException{
		/* Este mutex tiene dos propósitos:
		 *  - Proteger el acceso a la variable nLectores (exclusión mutua)
		 *  - Condición de sincronización de los lectores
		 *  Un lector se va a quedar esperando en este mutex si es el segundo
		 *  lector o siguiente (el primero se ha quedado bloqueado dentro de la SC
		 *  porque hay un escritor en la base de datos
		 */
		mutex3.acquire();
		leyendo.acquire();
		mutex1.acquire();
		System.out.println("Lector " + id + " entrando a la BD ...");
		nLectores++;
		if (nLectores == 1)	{
			System.out.println("Lector " + id + " bloquea a los escritores");
			//Si hay un escritor dentro el lector se queda aquí bloqueado
			//En este caso nos bloqueamos dentro de la SC adrede, para que el
			//resto de los lectores no puedan entrar a la BD
			escribiendo.acquire();
		}
		mutex1.release();
		leyendo.release();
		mutex3.release();
	}
	
	public void saleLector(int id) throws InterruptedException{
		mutex1.acquire();
		System.out.println("Lector " + id + " saliendo de la BD ...");
		nLectores--;
		if (nLectores == 0) {
			System.out.println("Lector " + id + " desbloquea a los escritores ...");
			escribiendo.release();
		}
		mutex1.release();
	}
}
