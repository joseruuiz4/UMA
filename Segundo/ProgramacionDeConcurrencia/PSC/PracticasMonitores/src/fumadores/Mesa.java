package fumadores;

public class Mesa {
	private int ingr = -1; //el que no esta
	
	private boolean fumando = false;
	
	public synchronized void nuevoIngrediente(int i) throws InterruptedException {
		//i es el ingrediente que no se pone
		if(ingr != -1 || fumando) {
			wait();
		}
		
		
		ingr = i;
		System.out.println("El agente no pone "+ingr);
		notifyAll();
		
	}

	public synchronized void quieroFumar(int id) throws InterruptedException{
		//id espera los ingredientes que no tiene
		
		while(id != ingr) {
			wait();
			
		}
		
		fumando = true;
		ingr = -1;
		
		System.out.println("Fumador "+id+" empieza a fumar");
	}
	
	public synchronized void finFumar(int id){
		//el fumador id ha terminado de fumar
		
		fumando = false;
		
		System.out.println("Fumador "+id+" termina de fumar");
		notifyAll();
	}
}
