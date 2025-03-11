package _AFilosofosBLOQUEO;

import java.util.concurrent.Semaphore;

public class Tenedor {
	
	private int id;
	private Semaphore mutex = new Semaphore(1); //mutex
	
	public Tenedor(int id){
		this.id = id;
	}
	
	public void cojoTenedor(int id) throws InterruptedException{
		//CS-filosofo: Esperar al  tenedor 
		mutex.acquire(); //Se espera hasta que este libre
		System.out.println("El Filosofo " + id + " coge el tenedor " + this.id);
	}
	
	public void devuelvoTenedor(int id){
		mutex.release(); //Pone en verde el sem�foro
		System.out.println("El Filosofo " + id + " suelta el tenedor " + this.id);
	}
}
