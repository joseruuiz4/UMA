package _2ProdConsSemaforosBinariosV1;

import java.util.concurrent.Semaphore;

//NO ES LA MEJOR FORMA DE IMPLEMENTARLO. MEJOR V2
public class Buffer {
	private int[] elem; //array de elementos
	private int p;      //posición donde guardar próximo elemento
	private int c;      //posición donde está el siguiente elemento a consumir
	
	//El mutex lo comparten tanto productor como consumidor
	private Semaphore mutex = new Semaphore(1); //semáforo binario para resolver exclusión mutua
	
	//No son necesarias las dos variables nhuecos y nelem, pero sería la forma más LITERAL de implementar
	//el semáforo general según lo explicado en las transparencias
	
	//Variable y semáforo binario para implementar semaforo general para CS-Productor
	private int nhuecos; //numero de huecos en el buffer
	private Semaphore hayHuecos; //semáforo binario. Inicialmente hay huecos

	//Variable y semáforo binario para implementar semaforo general para CS-Consumidor
	private int nelem;  //numero de elementos en el buffer
	private Semaphore hayDatos; //semáforo binario. Inicialmente no hay datos
	
	public Buffer(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException();
		}
		
		nhuecos = n;
		hayHuecos = new Semaphore(1); //Inicializado a 1 porque nhuecos > 0
		
		nelem = 0;
		hayDatos = new Semaphore(0); //Inicializado a 0 porque nelem es 0

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
		nhuecos--;
		nelem++;
		System.out.println("Elemento Producido: " + x);
		
		if (nhuecos > 0) hayHuecos.release();
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
		nelem--;
		nhuecos++;
		System.out.println("Elemento Consumido: " + x);
		
		if (nelem > 0) hayDatos.release();
		if (nhuecos == 1) hayHuecos.release();
		
		System.out.print("nelem: " + nelem);
		System.out.print(", hayDatos: " + hayDatos.availablePermits());
		System.out.println(", hayHuecos: " + hayHuecos.availablePermits());
		mutex.release();
		return x;
	}
}
