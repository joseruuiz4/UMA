package ExamenSep2018;

import java.util.concurrent.Semaphore;

	
public class Aseo {

	private int numH = 0; //Solo la modifica la hebra Hombre
	private int numM = 0; //Solo la modifica la hebra Mujer
	private Semaphore hombres = new Semaphore(1); //Caso especial. Vamos a usarlo tambi�n como mutex para modificar numH 
	private Semaphore mujeres = new Semaphore(1); //Caso especial. Vamos a usarlo tambi�n como mutex para modificar numM
	private Semaphore aseo = new Semaphore(1); //Lo usamos para bloquearle el acceso al otro sexo
	
	public void llegaHombre(int id) throws InterruptedException{
		hombres.acquire();
		
		if (numH==0) aseo.acquire(); //Si nos bloqueamos aqu� no liberamos hombres y el resto de hombres se quedan bloqueados
		numH++;
		System.out.println("Hombre "+id+" entra en el aseo. Hay "+numH+" "+numM);
		hombres.release();
	}
	
	public void llegaMujer(int id) throws InterruptedException{
		mujeres.acquire();
		
		if (numM==0) aseo.acquire(); //Si nos bloqueamos aqu� no liberamos mujeres y el resto de mujeres se quedan bloqueados
		numM++;
		System.out.println("Mujer "+id+" entra en el aseo. Hay "+numH+" "+numM);
		mujeres.release();
	}
	
	public void saleHombre(int id)throws InterruptedException{
		hombres.acquire();
		numH--;
		if (numH==0) aseo.release();
		System.out.println("Hombre "+id+" sale del aseo. Hay "+numH+" "+numM);
		hombres.release();
	}
	
	public void saleMujer(int id)throws InterruptedException{
		mujeres.acquire();
		numM--;
		if (numM==0) aseo.release();
		System.out.println("Mujer "+id+" sale del aseo. Hay "+numH+" "+numM);
		mujeres.release();
	}
}
