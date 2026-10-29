package agua.semaforos;

import java.util.concurrent.Semaphore;



public class GestorAgua {	
	

private int nH = 0;
private int nO = 0;


private Semaphore mutex = new Semaphore(1);

private Semaphore esperaH = new Semaphore(1);
private Semaphore esperaO = new Semaphore(1);

private Semaphore esperaMolecula = new Semaphore(0);


	public void hListo(int id) throws InterruptedException {
		//ENTRA UN ATOMO DE HIDROGENO
		esperaH.acquire();
		mutex.acquire();
		
		System.out.println("Entra Hidrogeno");
		nH++;
		
		if(nH < 2) esperaH.release();
		if(nH == 2 && nO == 1) {
			System.out.println("Agua");
		}
		else {
			mutex.release();
			esperaMolecula.acquire();
			mutex.acquire();
	
		}
		nH --;
		if(nO + nH > 0) esperaMolecula.release();
		else {
			esperaH.release();
			esperaO.release();
		}
		mutex.release();
		
	}
	
	public void oListo(int id) throws InterruptedException { 
		//ENTRA UN ATOMO DE OXIGENO
		esperaO.acquire();
		mutex.acquire();
		System.out.println("Entra oxigeno");
		nO++;
		
		if(nO == 1 && nH == 2) {
			System.out.println("Agua");
		}else {
			mutex.release();
			esperaMolecula.acquire();
			mutex.acquire();
		}
		
		nO--;
		
		if(nO + nH > 0) esperaMolecula.release();
		else {
			esperaH.release();
			esperaO.release();
		}
		
		
		mutex.release();
		
	}
		
	}
