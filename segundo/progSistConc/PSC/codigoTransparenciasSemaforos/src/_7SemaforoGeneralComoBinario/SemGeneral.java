package _7SemaforoGeneralComoBinario;

import java.util.concurrent.Semaphore;

public class SemGeneral {
	
	private int n; //valor del semaforo (n >= 0)
	private Semaphore mutex = new Semaphore(1); //semaforo binario para proteger a n
	
	private Semaphore espera; //semaforo binario donde se encolan los procesos
	
	// espera = 0 sii n == 0
	// espera = 1 sii n > 0
	public SemGeneral(int n) throws IllegalArgumentException{
		if (n < 0) throw new IllegalArgumentException();
		
		this.n = n;
		//Inicializamos el semaforo espera segun si los procesos
		//deben encolarse o no en el semaforo general que estamos implementando
		if (n == 0) espera = new Semaphore(0); //espera = 0 porque n == 0
		else espera = new Semaphore(1);
	}
	
	//PATRÓN DE LA SOLUCIÓN A MUCHOS EJERCICIOS DE LOS QUE RESOLVEREMOS
	public void acquireGen() throws InterruptedException{
		espera.acquire(); //hay que esperar a que el valor del semaforo sea > 0
		//espera == 0
		mutex.acquire();
		//Decrementamos el valor del semaforo en exclusión mutua
		n--;
		//Si el valor de n es mayor que 0 abrimos el semaforo
		if (n > 0) espera.release(); //espera == 1
		mutex.release();
	}
	
	public void releaseGen() throws InterruptedException{
		mutex.acquire();
		//Incrementamos el valor del semaforo en exclusión mutua
		n++;
		//Si n == 1 abrimos el semaforo binario
		//Si n > 1 el semaforo ya está abierto y no se hace nada
		if (n == 1) espera.release(); //espera == 1
		mutex.release();
	}
}
