package _1ImpresorasAB;

public class HebraB extends Thread{
	
	private int id;
	private Gestor gestor;
	public HebraB(int id,Gestor gestor){
		this.id = id;
		this.gestor = gestor;
	}
	
	public void run(){
		
		try{
			gestor.qImpresoraB(id);
			System.out.println(id+": Usando impresora tipo B");
			Thread.sleep(1000);
			gestor.dImpresora('B');
		} catch (InterruptedException ie){
			
		}
		
	}

}
