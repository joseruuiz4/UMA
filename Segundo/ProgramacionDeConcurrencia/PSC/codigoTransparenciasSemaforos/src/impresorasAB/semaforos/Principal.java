package impresorasAB.semaforos;

public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final int N = 5;
		Gestor gestor = new GestorSemaforos(3,3);
		HebraA[] hA = new HebraA[N];
		HebraB[] hB = new HebraB[N];
		HebraAB[] hAB = new HebraAB[N];
		
		for (int i = 0; i<N; i++){
			hA[i] = new HebraA(i,gestor);
			hB[i] = new HebraB(i,gestor);
			hAB[i] = new HebraAB(i,gestor);
		}
		for (int i = 0; i<N; i++){
			hA[i].start();
			hB[i].start();
			hAB[i].start();
		}
	}

}
