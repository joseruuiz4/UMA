package impresorasAB;

public class HebraAB extends Thread{
	
	private int id;
	private Gestor gestor;
	public HebraAB(int id,Gestor gestor){
		this.id = id;
		this.gestor = gestor;
	}
	
	public void run(){
		
		try{
			Thread.sleep(1000);
		    char i = gestor.qImpresoraAB(id);
		    gestor.dImpresora(i);
		} catch (InterruptedException ie){
			
		}
		
	}

}
