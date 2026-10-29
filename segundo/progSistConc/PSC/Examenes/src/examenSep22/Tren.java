package examenSep22;

import java.util.concurrent.locks.*;




public class Tren {
	
	private Lock l = new ReentrantLock();
	
	
	private int pasajeros = 0;
	private int N = 5; //Cap vagon
	
	
	
	private boolean bajaVagon1 = false;
	private Condition puedebajarV1 = l.newCondition();
	
	private boolean bajaVagon2 = false;
	private Condition puedebajarV2 = l.newCondition();
	
	private boolean puedeEntrar = true;
	private Condition entraTren = l.newCondition();
	
	private boolean estaLleno = false;
	private Condition empiezaViaje = l.newCondition();
	
	
	public void viaje(int id) throws InterruptedException {
		l.lock();
		
		try {
	
			while(!puedeEntrar) {
				entraTren.await();
			}
			
			pasajeros++;
			
			if(pasajeros == 2*N) {
				puedeEntrar = false;
				estaLleno = true;
				empiezaViaje.signal();
				
			}
			
			if(pasajeros <= N) {
				System.out.println("Pasajero " + id + " ha subido al vagon 1");
				while(!bajaVagon1) {
					puedebajarV1.await();
				}
				System.out.println("Pasajero " + id + " ha bajado al vagon 1");

			}else {
				System.out.println("Pasajero " + id + " ha subido al vagon 2");
				while(!bajaVagon2) {
					puedebajarV2.await();
				}
				System.out.println("Pasajero " + id + " ha bajado al vagon 2");


			}
			
			pasajeros--;
			if(pasajeros == N) {
				bajaVagon2 = true;
				puedebajarV2.signalAll();
			}
			
			if(pasajeros == 0) {
				System.out.println("/*************************/");
				bajaVagon1 = false;
				bajaVagon2 = false;
				puedeEntrar = true;
				entraTren.signalAll();
				
				
			}
			
			
			
			
			
		}finally {
			l.unlock();
		}
		
		
			
	}

	public void empiezaViaje() throws InterruptedException {
		l.lock();
		try {
			while(!estaLleno) empiezaViaje.await();
			System.out.println("        Maquinista:  empieza el viaje");
			estaLleno = false;
			
		}finally {
			l.unlock();
		}
		
		
	}
	public void finViaje() throws InterruptedException  {
		l.lock();
		try {
			System.out.println("        Maquinista:  fin del viaje");
			bajaVagon1 = true;
			puedebajarV1.signalAll();
		}finally {
			l.unlock();
		}
		
		
		
	}
}
