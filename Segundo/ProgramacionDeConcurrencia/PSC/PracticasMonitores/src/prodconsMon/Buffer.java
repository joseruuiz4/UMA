package prodconsMon;

import java.util.Arrays;

public class Buffer {
	//Buffer
	private int N = 10; //Tamaño del buffer
	private int[] buffer = new int[N]; //Buffer
	private int[] numCons = new int[N];//Contador del numero de consumidores que faltan por leer cada elemento del buffer
	//Numero de espacios que hay en el buffer
	private int nespacios = N;
	private int ncons; //Numero de consumidores del buffer
	private int[] nelems; //Numero de elementos en el buffer para cada consumidor
	private int[] c; //posicion a partir de la que consume cada consumidor
	private int p; //posicion a partir de la que guarda el productor
	
	//n - numero de consumidores
	public Buffer(int n){
		System.out.println("Tamanio del buffer " + N);
		System.out.println("Numero de consumidores " + n + "\n");
		p = 0;

	}
	
	//synchronized (ex. mutua) + wait/notify (cond. sincronizaci�n)
	public synchronized void almacenar(int elem) throws InterruptedException {
		//CS-Productor: espera mientras el buffer est� lleno
		while(nespacios == 0) {
			wait();
		}
		//actualiza todas las variables
		
		buffer[p] = elem;
		nespacios--;
		
		for(int i = 0; i < ncons; i++) {
			nelems[i]++;
		}
		
		
		numCons[p] = 3;
		System.out.println("++Produce dato: " + elem + " Posicion: " + p + " Espacios: " + nespacios);
		p = (p+1)%(N);
		
		notifyAll();
	}
	
	//id- identificador del consumidor
	public synchronized int extrae(int id) throws InterruptedException{
		//CS-Consumidor_id: espera mientras no tenga elementos que consumir
		int res = -1;
		while(nelems[id] == 0) {
			wait();
		}
		res = buffer[c[id]];
		
		System.out.println("--Consumidor: " + id + " consume el valor " + c[id] + " (" + buffer[c[id]] + ")");
		numCons[c[id]]--;
		nelems[id]--;
		
		System.out.println("Quedan: " + numCons[c[id]] + " por consumir el valor " + c[id]);
		
		
		if(numCons[c[id]] == 0) {
			System.out.println("!!!Consumido el valor " + c[id] + " (" + buffer[c[id]] + ")");
			nespacios++;
			notifyAll();
		}
		
		c[id] = (c[id] + 1) % N;
		
		
		return res;
	}
}
