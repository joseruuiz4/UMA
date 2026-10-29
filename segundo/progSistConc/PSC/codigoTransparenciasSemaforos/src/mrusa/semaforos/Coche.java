package mrusa.semaforos;

import java.util.concurrent.Semaphore;

public class Coche{
	
	private int asientos; 	//Capacidad del coche
	
	private int numPas = 0;
	private Semaphore mutex = new Semaphore(1); //exclusion mutua
	
	private Semaphore esperaSubir = new Semaphore(1);
	private Semaphore esperaBajar = new Semaphore(0);
	private Semaphore esperaLleno = new Semaphore(0);
	
	
	public Coche(int tam){
		asientos = tam;
	}
	
	public Coche(){
		asientos = 5;
	}
	
	//CS1-Pasajero: espera hasta que se abra la puerta
	//CS2-Pasajero: espera hasta que pueda bajar
	public void darVuelta(int id) throws InterruptedException{
		//Espera para subir
		esperaSubir.acquire();
		mutex.acquire();
		System.out.println("El pasajero " + id + " sube al coche");
		numPas++;
		
		if (numPas < asientos){
			esperaSubir.release();
		}else if (numPas == asientos){
			//Avisar de que el coche esta lleno
			System.out.println("Coche Lleno");
			esperaLleno.release();
		}
		
		//Esperar para bajar
		mutex.release();
		esperaBajar.acquire();
		mutex.acquire();
		
		//El pasajero baja del coche
		numPas--;
		System.out.println("Pasajero " + id + " baja del coche");
		//Despertado en cascada
		if (numPas > 0){
			//Avisar al siguiente pasajero
			esperaBajar.release();
		}else if (numPas == 0){
			System.out.println("Coche vacio");
			//Avisar de que los siguientes pasajeros pueden subir
			esperaSubir.release();
		}
		mutex.release();
	}

	//CS-Control: espera hasta que este lleno
	public void esperaLleno() throws InterruptedException{
		//Se espera a que el coche este lleno
		esperaLleno.acquire();

	}

	public void finVuelta() throws InterruptedException {
		//Avisa a los pasajeros que deben bajar
		esperaBajar.release();
	}
}