package _5BarberoDormilonSolucion1Ineficiente;

import java.util.concurrent.*;
public class Barberia {
	//Atributos
	private int n=0;
	
	//SEMÁFOROS BINARIOS
	private Semaphore mutex = new Semaphore(1); //semaforo binario
	private Semaphore espera = new Semaphore(0); //semaforo binario
	
	private boolean primera = true;
	
    public void nuevoCliente() throws InterruptedException {
    	mutex.acquire();
    	//SC
    	n++;
    	System.out.println("Llega un cliente");
    	if (n==1){System.out.println("Despierta al barbero..."); espera.release();}
    	mutex.release();
    	
    }
    
    public void pelar() throws InterruptedException {
    	if (primera) { espera.acquire(); primera = false;}
    	mutex.acquire();
    	//SC
    	n--;
    	int m = n;
    	System.out.println("Pelo a un cliente");
    	mutex.release();
    	//Solución 1. Ineficiente en el numero de release() y acquiere()
    	if (m == 0){ System.out.println("Me voy a dormir"); espera.acquire();}
    }
}
