package _AFilosofosBLOQUEO;

import java.util.concurrent.Semaphore;

public class Sillas {
	
	
	private int nSillas = 4;
	private Semaphore mutex = new Semaphore(1);
	private Semaphore hayLibres = new Semaphore(1);
	
	public void cojoSilla(int id) throws InterruptedException {
		hayLibres.acquire();
		mutex.acquire();
		nSillas--;
		System.out.println("El filosofo " + id + " coge una silla, quedan " + nSillas + " libres");
		
		if(nSillas > 0) {
			hayLibres.release();
		}
		mutex.release();
		
	}
	
	public void dejoSilla(int id) throws InterruptedException {
		mutex.acquire();
		nSillas++;
		
		if(nSillas == 1) {
			hayLibres.release();
		}
		mutex.release();
	}
		
	
}
