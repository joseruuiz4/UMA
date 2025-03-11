package _1Impresoras;

import java.util.concurrent.Semaphore;

public class Gestor {
	private int numImpresoras;
	public Semaphore mutex = new Semaphore(1); //variables
	public Semaphore hayImpresora = new Semaphore(1);
	
	
	public Gestor(int N) throws IllegalArgumentException{
		if (N<=0) throw new IllegalArgumentException();
		
		this.numImpresoras = N;
		System.out.println("Hay " + N + " impresoras libres");
	}
	
	public void cogeImpresora(int id) throws InterruptedException{
		hayImpresora.acquire();
		mutex.acquire();
		numImpresoras--;
		System.out.println("Cliente " + id + " coge 1 impresora, quedan " + numImpresoras +" impresoras libres");
		if(numImpresoras > 0) hayImpresora.release();
		
		mutex.release();

	}
	
	public void sueltaImpresora(int id) throws InterruptedException{
		mutex.acquire();
		numImpresoras++;
		System.out.println("Cliente " + id + " devuelve 1 impresora, quedan " + numImpresoras +" impresoras libres");

		
		if(numImpresoras == 1) hayImpresora.release();
		
		mutex.release();

	}
}
