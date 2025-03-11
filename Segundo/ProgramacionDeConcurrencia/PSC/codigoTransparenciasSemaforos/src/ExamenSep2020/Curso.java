package ExamenSep2020;

import java.util.concurrent.Semaphore;

public class Curso {

	//Numero maximo de alumnos cursando simultaneamente la parte de iniciacion
	private final int MAX_ALUMNOS_INI = 10;
	private int numINI = 0;
	private int numAV = 0;
	private int numFAV = 0;
	
	private Semaphore mutex = new Semaphore(1, true);
	private Semaphore entrarINI = new Semaphore(1, true);
	private Semaphore entrarAV = new Semaphore(1, true);
	private Semaphore espera = new Semaphore(0, true);
	private Semaphore espera2 = new Semaphore(0, true);
	
	
	//Numero de alumnos por grupo en la parte avanzada
	private final int ALUMNOS_AV = 3;
	
	
	//El alumno tendra que esperar si ya hay 10 alumnos cursando la parte de iniciacion
	public void esperaPlazaIniciacion(int id) throws InterruptedException{
		//Espera si ya hay 10 alumnos cursando esta parte
		entrarINI.acquire();
		mutex.acquire();
		
		//Mensaje a mostrar cuando el alumno pueda conectarse y cursar la parte de iniciacion
		System.out.println("PARTE INICIACION: Alumno " + id + " cursa parte iniciacion");
				
		
		
		numINI++;
		if(numINI < MAX_ALUMNOS_INI) entrarINI.release();
		
		
		
		
		
		mutex.release();
		
	}

	//El alumno informa que ya ha terminado de cursar la parte de iniciacion
	public void finIniciacion(int id) throws InterruptedException{
		//Mensaje a mostrar para indicar que el alumno ha terminado la parte de principiantes
		mutex.acquire();
		System.out.println("PARTE INICIACION: Alumno " + id + " termina parte iniciacion");
		numINI--;
		
		
		
		//Libera la conexion para que otro alumno pueda usarla
		if (numINI == MAX_ALUMNOS_INI - 1) entrarINI.release();
			
		
	
		mutex.release();
	}
	
	
	/* El alumno tendra que esperar:
	 *   - si ya hay un grupo realizando la parte avanzada
	 *   - si todavia no estan los tres miembros del grupo conectados
	 */
	public void esperaPlazaAvanzado(int id) throws InterruptedException{
		//Espera a que no haya otro grupo realizando esta parte
		entrarAV.acquire();
		mutex.acquire();
		numAV++;
		//Espera a que haya tres alumnos conectados
		//Mensaje a mostrar si el alumno tiene que esperar al resto de miembros en el grupo
		System.out.println("PARTE AVANZADA: Alumno " + id + " espera a que haya " + ALUMNOS_AV + " alumnos");

		if(numAV < ALUMNOS_AV) {
			entrarAV.release();
			
			mutex.release();
			espera.acquire();
			mutex.acquire();
		}
		
		//Mensaje a mostrar cuando el alumno pueda empezar a cursar la parte avanzada
		System.out.println("PARTE AVANZADA: Hay " + ALUMNOS_AV + " alumnos. Alumno " + id + " empieza el proyecto");
		
		numAV--;
		if(numAV != 0) espera.release();
	
		
	
	
		mutex.release();
	}
	
	/* El alumno:
	 *   - informa que ya ha terminado de cursar la parte avanzada 
	 *   - espera hasta que los tres miembros del grupo hayan terminado su parte 
	 */ 
	public void finAvanzado(int id) throws InterruptedException{
		
		mutex.acquire();
		//Espera a que los 3 alumnos terminen su parte avanzada
		numFAV++;
		
		//Mensaje a mostrar si el alumno tiene que esperar a que los otros miembros del grupo terminen
		System.out.println("PARTE AVANZADA: Alumno " +  id + " termina su parte del proyecto. Espera al resto");
		if(numFAV != ALUMNOS_AV) {
		
			mutex.release();
			espera2.acquire();
			mutex.acquire();
		}else {
			System.out.println("PARTE AVANZADA: LOS " + ALUMNOS_AV + " ALUMNOS HAN TERMINADO EL CURSO");

		}
		
		numFAV--;
		if(numFAV != 0) {
			espera2.release();
		}else {
			entrarAV.release();
		}
	
		//Mensaje a mostrar cuando los tres alumnos del grupo han terminado su parte
		mutex.release();
	}
}
