package _2ProdConsSemaforosGenerales;

import java.util.concurrent.Semaphore;

//Buffer sincronizado utilizando sem�foros
public class Buffer {
	private int[] elem; //array de elementos
//	private int nelem;  //n�mero de elementos en el buffer
	private int p;      //posici�n donde guardar pr�ximo elemento
	private int c;      //posici�n donde est� el siguiente elemento a consumir
	
	private Semaphore mutex = new Semaphore(1); //sem�foro binario para resolver exclusi�n mutua
	private Semaphore hayHuecos; //sem�foro general para implementar condici�n de sincronizaci�n del productor
	private Semaphore hayElementos; //sem�foro general para implementar condici�n de sincronizaci�n del consumidor
	
	public Buffer(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException();
		}
		hayHuecos = new Semaphore(n); //hay n huecos al principio
		hayElementos = new Semaphore(0); //hay 0 elementos al principio
		
		//n == hayHuecos + hayElementos
				
		elem = new int[n];
		p = 0;
		c = 0;
//		nelem = 0;
	}
	
	public void insertar(int x) throws InterruptedException {
		//Condici�n de sincronizaci�n - si el buffer est� lleno espero
		hayHuecos.acquire();
		mutex.acquire();
		//------SC-----
		elem[p] = x;
		p = (p+1) % elem.length; //incremento circular
//		++nelem;
		System.out.println("Elemento Producido: " + x);
		//------FIN SC-----
		hayElementos.release();
		//System.out.println("hayHuecos: " + hayHuecos.availablePermits());
		mutex.release();
	}
	
	/* MUY IMPORTANTE EL ORDEN DE LLAMADA A LOS SEM�FOROS
	 *   - Probad a intercambiar los dos acquire() y ver qu� ocurre
	 *   
	 */
	public int extraer() throws InterruptedException {
		//Condici�n de sincronizaci�n - si el buffer est� vac�o esper
		hayElementos.acquire();
		mutex.acquire();
		//------SC------
		int x = elem[c];
		c = (c+1) % elem.length; //incremento circular
//		--nelem;
		System.out.println("Elemento Consumido: " + x);
		//------FIN SC----
		hayHuecos.release();
		//System.out.println("hayHuecos: " + hayHuecos.availablePermits());
		mutex.release();
		return x;
	}
}
