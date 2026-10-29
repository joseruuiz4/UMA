package _3Barrera;

import java.util.concurrent.Semaphore;

public class Barrera {
	private int n; //numero de trabajadores
	private Semaphore mutex = new Semaphore(1);
	private Semaphore espera = new Semaphore(0);
	private Semaphore sigIteracion = new Semaphore(1);
	private int cont = 0;
	
	public Barrera(int n){
		this.n = n;
	}
	
	public void finIteracion(int id, int i) throws InterruptedException{
		sigIteracion.acquire();
		mutex.acquire();
		cont++;
		System.out.println("Trabajador " + id + " realiza su iteracion "+ i);
		
		if(cont < n) {
			mutex.release();
			sigIteracion.release();
			espera.acquire();
			mutex.acquire();
		}
		cont--;
		
		if(cont != 0) espera.release();
		else sigIteracion.release();
		
		
		mutex.release();
		
	}
}
