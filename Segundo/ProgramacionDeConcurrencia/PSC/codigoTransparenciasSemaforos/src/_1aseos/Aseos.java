package _1aseos;

import java.util.concurrent.Semaphore;

public class Aseos {
	
	private int nClientes = 0;
	
	private Semaphore mutex = new Semaphore(1, true);
	private Semaphore mutex2 = new Semaphore(1, true);
	
	
	private Semaphore esperaL = new Semaphore(1, true);
	private Semaphore esperaC = new Semaphore(1, true);
	
	
	
	/**
	 * Utilizado por el cliente id cuando quiere entrar en los aseos
	 * CS Version injusta: El cliente espera si el equipo de limpieza est� trabajando
	 * CS Version justa: El cliente espera si el equipo de limpieza est� trabajando o
	 * est� esperando para poder limpiar los aseos
	 * @throws InterruptedException 
	 * 
	 */
	public void entroAseo(int id) throws InterruptedException{
		esperaC.acquire();
		
		mutex.acquire();
		
		nClientes++;
		
		System.out.println("El cliente " + id + " ha entrado al aseo");
		if(nClientes == 1) esperaL.acquire();
		
		mutex.release();
		esperaC.release();
	
	}

	/**
	 * Utilizado por el cliente id cuando sale de los aseos
	 * @throws InterruptedException 
	 * 
	 */
	public void salgoAseo(int id) throws InterruptedException{
		mutex.acquire();
		
		nClientes--;
		System.out.println("El cliente " + id + " ha salido del aseo");

		
		if(nClientes == 0) esperaL.release();
		
		
		mutex.release();
	}
	
	/**
	 * Utilizado por el Equipo de Limpieza cuando quiere entrar en los aseos 
	 * CS: El equipo de trabajo est� solo en los aseos, es decir, espera hasta que no
	 * haya ning�n cliente.
	 * @throws InterruptedException 
	 * 
	 */
    public void entraEquipoLimpieza() throws InterruptedException{
    	System.out.println("Limpiando");

    	esperaC.acquire();
    	esperaL.acquire();
    	
		
	}
    
    /**
	 * Utilizado por el Equipo de Limpieza cuando  sale de los aseos 
     * @throws InterruptedException 
	 * 
	 * 
	 */
    public void saleEquipoLimpieza() throws InterruptedException{
    	System.out.println("El equipo de limpieza sale del aseo");
    	esperaC.release();
    	esperaL.release();
    	
	}
}
