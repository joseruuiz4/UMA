package primos;

import java.util.*;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

public class WorkerSexy extends SwingWorker<Void, Primos> {
	private int n;
	private Panel panel;
	
	
	public WorkerSexy(int n, Panel panel) {
		this.n = n;
		this.panel = panel;
	}
	
	protected Void doInBackground() throws Exception {
		List<Integer> primes = new ArrayList<>();

		int pos = 0;
		int i = 0;
		while(pos  < n && !isCancelled()) {
			if (esPrimo(i)) {
                primes.add(i);
                // Verificar si hay un primo en la lista que esté a una distancia de 4
                if (primes.contains(i - 6)) {
                	publish(new Primos(i-6, i, pos));
                    pos++;
                    this.setProgress((pos*100)/n);
                }
            }
			i++;
		}
       
		return null;
	}
	
	private static boolean esPrimo(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
	

	public void done() {
		try {
			get();
			panel.mensajeSexy("numeros primos sexy calculados");
		}catch (InterruptedException e) {
			System.out.println("Tarea cancelada");
			e.printStackTrace();
		}catch (ExecutionException | CancellationException e) {
			System.out.println("Tarea cancelada");
		}

		
	}
	
	public void process(List<Primos> lista) {
		try {
			panel.escribePrimosSexy(lista);
			panel.mensajeSexy("numero primos twin calculados");
		}catch (CancellationException e) {
			panel.mensajeSexy("Tarea cancelada");
		}
	}

}