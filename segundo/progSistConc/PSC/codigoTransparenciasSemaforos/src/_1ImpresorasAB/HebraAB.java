package _1ImpresorasAB;

public class HebraAB extends Thread{
	
	private int id;
	private Gestor gestor;
	public HebraAB(int id,Gestor gestor){
		this.id = id;
		this.gestor = gestor;
	}
	
	public void run(){
		
		try{
		    char i = gestor.qImpresoraAB(id);
		    Thread.sleep(10);
		    System.out.println(id+": Usando impresora tipo "+i);
		    gestor.dImpresora(i);
		} catch (InterruptedException ie){}
	}

}
