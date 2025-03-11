package primos;

import java.util.*;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

public class WorkerTwin extends SwingWorker<Void, Primos> {
	private int n;
	private Panel panel;
	
	
	public WorkerTwin(int n, Panel panel) {
		this.n = n;
		this.panel = panel;
	}
	
	protected Void doInBackground() throws Exception {

		
		int previousPrime = 2;
		int pos = 0;
		int i = 0;
		while(pos  < n) {
			if (esPrimo(i) && !isCancelled()) {
                if (i - previousPrime == 2) {
                    publish(new Primos(previousPrime, i, pos));
                    pos++;
                    this.setProgress((pos*100)/n);
                }
                previousPrime = i;
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
			panel.mensajeCousin("numeros primos twin calculados");
		}catch (InterruptedException e) {
			System.out.println("Tarea cancelada");
			e.printStackTrace();
		}catch (ExecutionException | CancellationException e) {
			System.out.println("Tarea cancelada");
		}

		
	}
	
	public void process(List<Primos> lista) {
		try {
			panel.escribePrimosTwin(lista);
			panel.mensajeTwin("numero primos twin calculados");
		}catch (CancellationException e) {
			panel.mensajeTwin("Tarea cancelada");
		}
	}

}
