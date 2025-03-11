package _AFilosofosBLOQUEO;

import java.util.Random;

public class Filosofo extends Thread{
	private static Random rnd = new Random();
	private int id;
	private Tenedor izq, dcha;
	private Sillas silla;
	
	public Filosofo(int id, Tenedor izq, Tenedor dcha, Sillas s){
		this.id = id;
		this.izq = izq;
		this.dcha = dcha;
		this.silla = s;
	}
	
	public void run(){
		try{
			while(true){
				//CUIDADO PORQUE CON LOS sleep NO BLOQUEA SIEMPRE Y NO SE VE EL BLOQUEO
				Thread.sleep(rnd.nextInt(500)); //pensando
				silla.cojoSilla(id);
				izq.cojoTenedor(id); //Puede bloquear
				dcha.cojoTenedor(id);//Puede bloquear
				Thread.sleep(rnd.nextInt(100)); //comiendo
				dcha.devuelvoTenedor(id);
				izq.devuelvoTenedor(id);
				silla.dejoSilla(id);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
