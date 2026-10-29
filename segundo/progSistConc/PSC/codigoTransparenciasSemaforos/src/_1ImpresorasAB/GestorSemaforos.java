package _1ImpresorasAB;

import java.util.concurrent.Semaphore;

/**
 * 
 * @author monica
 *
 * 
 * 
 */
public class GestorSemaforos implements Gestor {

	private int numImpA, numImpB; //numero de impresoras de cada tipo
	
	private Semaphore mutexA = new Semaphore(1);
	private Semaphore hayImpA = new Semaphore(1);
	
	
	private Semaphore mutexB = new Semaphore(1);
	private Semaphore hayImpB = new Semaphore(1);

	
	
	public GestorSemaforos(int numA,int numB) throws IllegalArgumentException{
		if (numA<=0 || numB <=0) throw new IllegalArgumentException();
		
		numImpA = numA;
		numImpB = numB;
	}

	public void qImpresoraA(int id) throws InterruptedException{
		hayImpA.acquire();
		mutexA.acquire();
		numImpA--;
		
		System.out.println(id + " Obteniendo Impresora tipo A. Quedan " + numImpA);
		
		if(numImpA > 0) hayImpA.release();
		
		mutexA.release();
	}

	public void qImpresoraB(int id) throws InterruptedException{
		hayImpB.acquire();
		mutexB.acquire();
		numImpB--;
		System.out.println(id + " Obteniendo Impresora tipo B. Quedan " + numImpB);

		
		if(numImpB > 0) hayImpB.release();
		
		mutexB.release();
	
	}

	public char qImpresoraAB(int id) throws InterruptedException{
		//Cliente AB solicita impresora
		//No queremos que se quede bloqueado esperando A si hay de tipo B, o al rev�s
		//Tenemos que consultar numImpA y numImpB en exclusi�n mutua
		
		char valor = 'N'; //valor que nos indica que no se ha encontrado impresora
		
		mutexA.acquire();
		if(numImpA > 0) {
			mutexA.release();
			hayImpA.acquire();
			mutexA.acquire();
			numImpA--;
			if(numImpA > 0) hayImpA.release();
			valor = 'A';
		}
		mutexA.release();
		
		
		mutexB.acquire();
		if(valor == 'N' && numImpB > 0) {
			mutexB.release();
			hayImpB.acquire();
			mutexB.acquire();
			numImpB--;
			if(numImpB > 0) hayImpB.release();
			valor = 'B';
		}
		mutexB.release();
		
		if(valor == 'N') {
			hayImpA.acquire();
			mutexA.acquire();
			valor = 'A';
			numImpA--;
			
			System.out.println(id + " Obteniendo Impresora tipo A. Quedan " + numImpA);
			
			if(numImpA > 0) hayImpA.release();
			
			mutexA.release();
		}
		
		
		
		
		
		
		return valor;
	}


	public void dImpresora(char tipo) throws InterruptedException{
		//Cliente deja una impresora, indicando el tipo de la impresora
		switch(tipo) {
		case 'A': 
			mutexA.acquire();
			numImpA++;
			System.out.println("Soltando Impresora tipo A. Quedan " + numImpA);

			
			if(numImpA == 1) hayImpA.release();
			
			
			mutexA.release();
			
				 break;
		case 'B': 
			mutexB.acquire();
			numImpB++;
			
			System.out.println("Soltando Impresora tipo B. Quedan " + numImpB);
			
			if(numImpA == 1) hayImpB.release();
			
			
			mutexB.release();
				 break;
		}
		
	}
}
