package ExamenHuerto;


public class Huerto { //Recurso
	
	private int N; //distancia máxima entre David y Fran
	
	public Huerto(int tam){
		
		N = tam;
	}
	
	/**
	 * David espera en este método para poder empezar a hacer 
	 * un hoyo. Tiene que  esperar si está alejado N hoyos sin rellenar de Fran y,
	 * opcionalmente, si la pala compartida está siendo utilizada.
	 */
	public  void esperaHacerHoyo() throws InterruptedException{
		
	}
	
	/**
	 * David ha hecho el  hoyo número num. Actualiza el recurso
	 * para informar a Juan y a Fran.
	 * @param num
	 */
	public  void finHacerHoyo(int num) throws InterruptedException{
		
	}
	
	/**
	 * Juan espera en este método para poder echar semillas a 
	 * un hoyo. Debe esperar si no hay un hoyo sin semillas.
	 */
	public  void esperaPonerSemilla() throws InterruptedException{
		
	}
	
	/**
	 * Juan ha puesto semillas en el  hoyo número num. 
 	 * Actualiza el recurso para informar Fran.
	 * @param num
	 */
	public  void finPonerSemilla(int num) throws InterruptedException{
	
	}
	
	/**
	 * Fran espera en este método para poder rellenar 
	 * un hoyo. Espera si no hay un hoyo con semilla no relleno
	 *  y, opcionalmente, si la pala no está libre.
	 */
	public  void esperaEcharTierra() throws InterruptedException{
		
	}
	
	/**
	 * Fran ha rellenado el  hoyo número num. 
 	 * Actualiza el recurso para informar a Juan y a David.
	 * @param num
	 */
	public  void finEcharTierra(int num) throws InterruptedException{	
		
	}
	


}
