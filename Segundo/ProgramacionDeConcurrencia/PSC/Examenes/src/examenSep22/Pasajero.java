package examenSep22;

public class Pasajero extends Thread{

	private int id;
	private Tren tren;
	
	public Pasajero(Tren tren, int id) {
		this.tren = tren;
		this.id = id;
	}
	
	
	public void run() {
		while (true) {
			try {
				Thread.sleep(2000);
				tren.viaje(id);
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
