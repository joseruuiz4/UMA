package ExamenSep2018;

import java.util.Random;

public class Hombre extends Thread{
	private Aseo aseo;
	private int id;
	private static Random r = new Random();
	public Hombre(Aseo aseo,int id){
		this.id = id;
		this.aseo = aseo;
	}

	
	public void run(){
		while (true){
			try {
				Thread.sleep(5000);
				aseo.llegaHombre(id);
				Thread.sleep(r.nextInt(500));
				aseo.saleHombre(id);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
