package recursos;

public class Principal {

	public static void main(String[] args) {
		int N = 10;
		Control c = new Control(N);
		
		Proceso[] p = new Proceso[5];
		for (int i = 0; i<p.length; i++)
			p[i] = new Proceso(i,c,N);
		
		for (int i = 0; i<p.length; i++)
			p[i].start();
	}
}
