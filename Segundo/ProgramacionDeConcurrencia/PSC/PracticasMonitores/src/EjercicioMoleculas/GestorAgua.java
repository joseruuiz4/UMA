package EjercicioMoleculas;


public class GestorAgua {	

	
	
	private int nH = 0;
	private int nO = 0;
	
	private boolean puertaH = true;
	private boolean puertaO = true;
	private boolean molecula = false;
	
	
	public synchronized void hListo(int id) throws InterruptedException { 
		while(!puertaH) wait();
		nH++;
		System.out.println("Llega Hidrogeno " + id);
		if(nH == 2) puertaH = false;
		
		if(nH == 2 && nO == 1) {
			System.out.println("Se ha formado la molecula de agua");
			molecula = true;
			notifyAll();
		}else {
			while(!molecula) wait();
		}
		
		
		nH--;
		System.out.println("Sale Hidrogeno " + id);
		if(nH == 0 & nO == 0) {
			puertaH = true;
			puertaO = true;
			molecula = false;
			notifyAll();
		}
		
		
	}
	
	public synchronized void oListo(int id) throws InterruptedException { 
		while(!puertaO) wait();
		nO++;
		System.out.println("Llega Oxigeno " + id);
		if(nO == 1) puertaO = false;

		if(nH == 2 && nO == 1) {
			System.out.println("Se ha formado la molecula de agua");
			molecula = true;
			notifyAll();
		}else {
			while(!molecula) wait();
		}
		
		
		nO--;
		System.out.println("Sale Oxigeno " + id);
		if(nH == 0 & nO == 0) {
			puertaH = true;
			puertaO = true;
			molecula = false;
			notifyAll();
		}


	}
}
