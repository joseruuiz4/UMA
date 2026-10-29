package _3BarberoDormilonBloqueante;

import java.util.concurrent.*;
public class Barberia {
    private int n = 0;
    
    private Semaphore espera = new Semaphore(0,true);
    private Semaphore mutex = new Semaphore(1,true);
    
    private boolean primera = true;
    
    public void nuevoCliente() throws InterruptedException {
            mutex.acquire();
            n++;
            System.out.println("Nuevo cliente...");
            if (n==1) {System.out.println("Despertando al barbero ..."); espera.release();}
            mutex.release();
    }
    
    public void pelar() throws InterruptedException {
         if (primera) {espera.acquire();primera=false;}
         mutex.acquire();
         n--;
         int m = n;
         System.out.println("Cliente atendido..." + n);
         //Esta no es la soluci�n definitiva. Seguiremos explicando mas la semana pr�xima
         mutex.release();
         if (m == 0) {System.out.println("Voy a descansar ..."); espera.acquire();}

    }
}
