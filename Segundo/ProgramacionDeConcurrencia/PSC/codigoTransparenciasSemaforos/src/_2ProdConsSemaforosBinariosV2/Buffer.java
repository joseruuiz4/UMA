package _2ProdConsSemaforosBinariosV2;

import java.util.concurrent.Semaphore;

//Buffer sincronizado utilizando semaforos
public class Buffer {
	private int[] elem; //array de elementos
	private int p;      //posición donde guardar próximo elemento
	private int c;      //posición donde está el siguiente elemento a consumir
	
	private Semaphore mutex = new Semaphore(1); //semáforo binario para resolver exclusión mutua
	private int nelem;  //numero de elementos en el buffer
	private Semaphore hayHuecos = new Semaphore(1); //semáforo binario. Inicialmente hay huecos
	private Semaphore hayDatos = new Semaphore(0); //semáforo binario. Inicialmente no hay datos
	
	public Buffer(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException();
		}
		
		nelem = 0;

		elem = new int[n];
		p = 0;
		c = 0;
	}
	
	public void insertar(int x) throws InterruptedException {
		//Condición de sincronización - si el buffer está lleno espero
		hayHuecos.acquire();
		mutex.acquire();
		//------SC-----
		elem[p] = x;
		p = (p+1) % elem.length; //incremento circular
		
		++nelem;
		
		System.out.println("Elemento Producido: " + x);
		
		if (nelem < elem.length) hayHuecos.release();
		if (nelem == 1) hayDatos.release();
		
		System.out.print("nelem: " + nelem);
		System.out.print(", hayDatos: " + hayDatos.availablePermits());
		System.out.println(", hayHuecos: " + hayHuecos.availablePermits());
		mutex.release();
	}
	
	public int extraer() throws InterruptedException {
		//Condición de sincronización - si el buffer está vacío espero
		hayDatos.acquire();
		mutex.acquire();
		//------SC------
		int x = elem[c];
		c = (c+1) % elem.length; //incremento circular
		
		--nelem;
		
		System.out.println("Elemento Consumido: " + x);
		
		if (nelem > 0) hayDatos.release();
		if (nelem == elem.length-1) hayHuecos.release();
		
		System.out.print("nelem: " + nelem);
		System.out.print(", hayDatos: " + hayDatos.availablePermits());
		System.out.println(", hayHuecos: " + hayHuecos.availablePermits());
		mutex.release();
		return x;
	}
}
