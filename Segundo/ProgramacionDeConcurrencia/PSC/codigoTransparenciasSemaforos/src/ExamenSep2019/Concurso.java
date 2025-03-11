package ExamenSep2019;

import java.util.concurrent.Semaphore;

public class Concurso {

	
	private boolean juegoTerminado = false;
	private int nCaras[] = new int[2];
	private int nTiradas[] = new int[2];
	private int juegosGanados[] = new int[2];
	private int njuego = 0;
	
	
	private Semaphore mutex = new Semaphore(1, true);
	private Semaphore espera = new Semaphore(0, true);

	public void tirarMoneda(int id, boolean cara) throws InterruptedException {
		mutex.acquire();
		nTiradas[id]++;
		
		if(cara) nCaras[id]++;
		
		if(nTiradas[id] == 10 && nTiradas[1-id] < 10) {
			mutex.release();
			espera.acquire();
			mutex.acquire();
		}else if(nTiradas[id] == 10 && nTiradas[1-id] == 10){
			njuego++;
			if(nCaras[0] == nCaras[1]) System.out.println("Ha habido un empate");
			else if(nCaras[0] > nCaras[1]) {
				juegosGanados[0]++;
				System.out.println ("Juego " + njuego + ": Ha ganado la partida el jugador 0 con " + nCaras[0] + " caras");
			}else {
				juegosGanados[1]++;
				System.out.println ("Juego " + njuego + ": Ha ganado la partida el jugador 1 con " + nCaras[1] + " caras");
			}
		
			
			
			if (juegosGanados[0] == 3) {
				System.out.println ("Fin del concurso. Ha ganado la partida el jugador 0");
				juegoTerminado = true;
			}else if (juegosGanados[1] == 3) {
				System.out.println ("Fin del concurso. Ha ganado la partida el jugador 1");
				juegoTerminado = true;

			}else {
			}
			
			
			
			
			for (int i=0; i<2; i++) {
				nTiradas[i]=0;
				nCaras[i] = 0;
				//esperaJugador[i].release();
			}
		
		
			espera.release();
		
		
		
		
		
		}
		
		
		
		
		
		mutex.release();

	}

	public boolean concursoTerminado() {
		

		return juegoTerminado;
	}
}