package impresorasAB;

import java.util.concurrent.locks.*;

/**
 * 
 * @author mmar
 * Soluci�n al problema del gestor de impresoras utilizando
 * condiciones. La condici�n colaGeneral es utilizada por todas las
 * hebras cuando piden una impresora y no hay del tipo que piden.
 *
 * Las colas colaA y colaB son utilizadas en exclusiva por las hebras de tipo
 * A y B, respectivamente.
 * 
 * 
 * 
 */
public class GestorLock implements Gestor {
	
	private int numImpA,numImpB; //numero de impresoras de cada tipo

	public GestorLock(int numA,int numB){
		numImpA = numA;
		numImpB = numB;
	}
	
	public void qImpresoraA(int id) {

	}
	
	
	public void qImpresoraB(int id) {

	}

	
	public char qImpresoraAB(int id) {
		char valor = 'A';
		
		return valor;
	}
	
	
	public void dImpresora(char tipo){

	}
}
