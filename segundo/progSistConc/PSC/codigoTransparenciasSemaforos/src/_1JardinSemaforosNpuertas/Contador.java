package _1JardinSemaforosNpuertas;

import java.util.concurrent.Semaphore;

public class Contador {
    private int cont = 0;
    private Semaphore mutex = new Semaphore(1); //Queremos asegurar la exclusi�n mutua
    //Sem�foro binario - se inicializa a 1 porque no hay nadie en la SC
    
    public void inc() throws InterruptedException{
    	mutex.acquire(); //decrementa sem�foro en 1
        cont++;
        mutex.release(); //incrementa sem�foro en 1
    }

    public int valor(){
          return cont;
    }
}
