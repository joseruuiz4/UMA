package ExamenJun2019;

import java.util.concurrent.Semaphore;

public class Huerto {
    private int N; // Distancia máxima entre David y Fran
    private Semaphore esperaHacerHoyo = new Semaphore(0);
    
    private Semaphore esperaPonerSemilla = new Semaphore(0);
    private Semaphore finPonerSemilla = new Semaphore(0);

    private Semaphore esperaEcharTierra = new Semaphore(0);
    private Semaphore mutex = new Semaphore(1);
    

    private int hoyosPorHacer = 0;
    
    public Huerto(int tam) {
        N = tam;
    }

    public void esperaHacerHoyo() throws InterruptedException {
    	mutex.acquire();
    	
    	if(hoyosPorHacer == N)
    	esperaHacerHoyo.acquire();
    	
    	
    	mutex.release();

    }

    public void finHacerHoyo(int num) throws InterruptedException {
        mutex.acquire();
    	
    	hoyosPorHacer++;
    	System.out.println("David ha cavado el hoyo " + num);
        esperaPonerSemilla.release(); // Informar a Fran que hay un hoyo por rellenar
        
        mutex.release();
  
    }

    public void esperaPonerSemilla() throws InterruptedException {
    	esperaPonerSemilla.acquire();
    	finPonerSemilla.release();
    	
    }

    public void finPonerSemilla(int num) throws InterruptedException {
    	finPonerSemilla.acquire();
    	System.out.println("Juan ha puesto la semilla en el hoyo " + num);
    	esperaEcharTierra.release();
    }

    public void esperaEcharTierra() throws InterruptedException {
        esperaEcharTierra.acquire();
        
    }

    public void finEcharTierra(int num) throws InterruptedException {
        mutex.acquire();
        
    	hoyosPorHacer--;
        System.out.println("Fran ha tapado el hoyo " + num);
    	esperaHacerHoyo.release();
    	mutex.release();
    }
}
