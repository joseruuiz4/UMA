package _9LectoresEscritoresJUSTO;

import java.util.concurrent.Semaphore;

//ES UNA IMPLEMENTACIÓN JUSTA
//Si hay un escritor esperando, los siguientes lectores tendrán también que esperar
public class GestorBD {

	//CS - Escritores
	private Semaphore escribiendo = new Semaphore(1,true); //Semáforo binario - exclusión mutua
	private Semaphore leyendo = new Semaphore(1,true); //Semáforo binario - exclusión mutua
	
	private int nLectores = 0;
	private Semaphore mutex1= new Semaphore(1,true); //Semáforo binario - protege nLectores
	
	private int nEscritores = 0;
	private Semaphore mutex2 = new Semaphore(1,true); //Semáforo binario - protege nEscritores
	
	private Semaphore mutex3 = new Semaphore(1,true);
	
	public void entraEscritor(int id) throws InterruptedException{
		//Incrementar nEscritores en exclusión mutua
		mutex2.acquire();
		nEscritores++;
		//Además, si un escritor entra tiene que bloquear a los siguientes lectores
		if (nEscritores == 1){
			System.out.println("Se cierra la puerta a los siguientes lectores");
			leyendo.acquire(); //cierra la puerta a los siguienes lectores
			//si no hay lectores en la BD le cierra la puerta a todos los que lleguen
		}
		mutex2.release();
		//Modificamos nEscritores en exclusión mutua
		escribiendo.acquire();
		System.out.println("Escritor " + id + " entrando a la BD ...");
	}
	
	public void saleEscritor(int id) throws InterruptedException{
		mutex2.acquire();
		nEscritores--;
		if (nEscritores == 0){ //No hay escritores esperando
			System.out.println("Se abre la puerta a los lectores");
			leyendo.release();
		}
		mutex2.release();
		System.out.println("Escritor " + id + " saliendo de la BD ...");
		escribiendo.release();
	}
	
	public void entraLector(int id) throws InterruptedException{
		/* Pide permiso para entrar a leer
		 *   - Si está bloqueado el lector se queda bloqueado
		 *   - Estará bloqueado si HAY UN ESCRITOR ESPERANDO
		 */
		mutex3.acquire(); 
		leyendo.acquire();  //Si hubiera llegado antes un escritor, este semáforo estaría cerrado
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
		leyendo.release(); //Libera el permiso
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
