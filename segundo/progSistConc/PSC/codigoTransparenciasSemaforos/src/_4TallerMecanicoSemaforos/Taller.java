package _4TallerMecanicoSemaforos;

import java.util.concurrent.Semaphore;

public class Taller {
	private Semaphore cocheSubido = new Semaphore(0);
	private Semaphore entrarTaller = new Semaphore(1);
	private Semaphore empezarFactura = new Semaphore(0);
	private Semaphore cocheListo = new Semaphore(0);
	

	public Taller() {

	}
	
	//CS-Mecanico: Espera hasta que el cliente ha subido su coche a la plataforma
	public void esperaParaRevisar() throws InterruptedException{
		cocheSubido.acquire();
		System.out.println("	Hay un coche en la plataforma. El mecanico empieza su trabajo");
		
	
	}
	
	public void finRevision(){
		
		System.out.println("	Fin de la revision. El mecanico termina su trabajo");
		empezarFactura.release();

	}

	//CS-Administrativo: Espera hasta que el mecanimo le avisa que ha terminado de revisar el coche
	public void esperaParaFacturar() throws InterruptedException{
		empezarFactura.acquire();

		System.out.println("	Hay una factura que hacer. El administrativo empieza su trabajo");
	}
	
	public void finFactura(){
		System.out.println("	Fin de la factura. El administrativo termina su trabajo");
		cocheListo.release();

	}	
	
	public void revisarCoche(int id) throws InterruptedException{
		//CS_Cliente1: Espera a que le avisen de que puede entrar al taller
		//System.out.println("El cliente " + id + " llega al taller y espera su turno");
		entrarTaller.acquire();
		System.out.println("El cliente " + id + " sube el coche a la plataforma");
		cocheSubido.release();
		//CS_Cliente2: Espera a que le avisen de que el coche y la factura están listas
		cocheListo.acquire();
		System.out.println("El cliente " + id + " recoge su coche");
		
		entrarTaller.release();
			
	}
}
