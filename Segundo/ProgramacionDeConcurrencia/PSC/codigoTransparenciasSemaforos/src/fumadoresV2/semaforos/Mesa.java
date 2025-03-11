package fumadoresV2.semaforos;

import java.util.concurrent.Semaphore;

public class Mesa {
	private boolean[] ingr; //true-tiene el ingrediente/false-no lo tiene
	
	private Semaphore mutex = new Semaphore(1);
	
	private Semaphore esperaAgente = new Semaphore(1);
	private Semaphore esperaTabacoPapel = new Semaphore(0);
	private Semaphore esperaTabacoCerilla = new Semaphore(0);
	private Semaphore esperaPapelCerilla = new Semaphore(0);
	
	
	
	public Mesa(){
		ingr = new boolean[3];
	}
	
	//M�todo privado que devuelve los ingredientes que hay en la mesa
	private String[] ingredientes(){
		String[] nombres = new String[3];
		if (ingr[0]){
			nombres[0] = "tabaco";
		}
		if (ingr[1]){
			nombres[1] = "papel";
		}
		if (ingr[2]){
			nombres[2] = "cerillas";
		}	
		return nombres;
	}
	
	//El agente llama a este metodo para poner en la mesa dos ingredientes de los tres
	public void nuevosIngredientes(int ingr1, int ingr2) throws InterruptedException{
		esperaAgente.acquire();
		mutex.acquire();
		
		ingr[ingr1] = true;
		ingr[ingr2] = true;
		System.out.println("En la mesa hay " + java.util.Arrays.toString(ingredientes()));

		
		if(ingr[0] && ingr[1]) {
			esperaTabacoPapel.release();
		}else if(ingr[0] && ingr[2]) {
			esperaTabacoCerilla.release();
		}else {
			esperaPapelCerilla.release();
		}
		
		mutex.release();
		esperaAgente.release();
	}

	//El fumador id quiere los dos ingredientes que le faltan para poder fumar
	public void quieroFumar(int id) throws InterruptedException{
		switch(id) {
		case 0:
			esperaPapelCerilla.acquire();
			break;
		case 1:
			esperaTabacoCerilla.acquire();
			break;
		case 2: 
			esperaTabacoPapel.acquire();
			break;
		}

	}
	
	//El fumador id indica que ha terminado de fumar
	public void finFumar(int id) throws InterruptedException{
		mutex.acquire();
		ingr[0] = false;
		ingr[1] = false;
		ingr[2] = false;
		esperaAgente.release();
		mutex.release();
		
	}
}
