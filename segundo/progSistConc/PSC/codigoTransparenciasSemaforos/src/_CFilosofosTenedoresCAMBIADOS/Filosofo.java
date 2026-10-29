package _CFilosofosTenedoresCAMBIADOS;

import java.util.Random;

public class Filosofo extends Thread{
	private static Random rnd = new Random();
	private int id;
	private Tenedor izq, dcha;
	
	public Filosofo(int id, Tenedor izq, Tenedor dcha){
		this.id = id;
		this.izq = izq;
		this.dcha = dcha;
	}
	
	public void run(){
		try{
			while(true){
				Thread.sleep(rnd.nextInt(10)); //pensando
				//Thread.yield(); //pensando
				izq.cojoTenedor(id);
				dcha.cojoTenedor(id);
				System.out.println("El Filosofo " + id + " esta comiendo.");
				Thread.sleep(rnd.nextInt(10)); //comiendo
				dcha.devuelvoTenedor(id);
				izq.devuelvoTenedor(id);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
