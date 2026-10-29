package examenCurso;

import java.util.concurrent.locks.*;

public class Curso {

	private int nIni = 0;
	private int nAva = 0;
	private int nTerm = 0;
	
	
	private Lock l = new ReentrantLock(true);
	
	private Condition entrarIni = l.newCondition();
	
	private boolean puedeEntrar = true;
	private Condition entrarAva = l.newCondition();
	
	
	private boolean esperaGrupo = true;
	private Condition iniciaProyecto = l.newCondition();
	
	private boolean esperaTerminar = true;
	private Condition terminar = l.newCondition();
	
	
	
	
	
	//Numero maximo de alumnos cursando simultaneamente la parte de iniciacion
	private final int MAX_ALUMNOS_INI = 10;
	
	//Numero de alumnos por grupo en la parte avanzada
	private final int ALUMNOS_AV = 3;
	
	
	//El alumno tendra que esperar si ya hay 10 alumnos cursando la parte de iniciacion
	public void esperaPlazaIniciacion(int id) throws InterruptedException{
		l.lock();
		try {
			//Espera si ya hay 10 alumnos cursando esta parte
			while(nIni == MAX_ALUMNOS_INI) entrarIni.await();
			nIni++;
			//Mensaje a mostrar cuando el alumno pueda conectarse y cursar la parte de iniciacion
			System.out.println("PARTE INICIACION: Alumno " + id + " cursa parte iniciacion");
		
		}finally {
			l.unlock();
		}
		
	}

	//El alumno informa que ya ha terminado de cursar la parte de iniciacion
	public void finIniciacion(int id) throws InterruptedException{
		l.lock();
		
		try {
			//Mensaje a mostrar para indicar que el alumno ha terminado la parte de principiantes
			System.out.println("PARTE INICIACION: Alumno " + id + " termina parte iniciacion");
			nIni--;
			if(nIni == MAX_ALUMNOS_INI - 1)entrarIni.signalAll(); //Libera la conexion para que otro alumno pueda usarla
		}finally {
			l.unlock();
		}
		
	
	}
	
	/* El alumno tendra que esperar:
	 *   - si ya hay un grupo realizando la parte avanzada
	 *   - si todavia no estan los tres miembros del grupo conectados
	 */
	public void esperaPlazaAvanzado(int id) throws InterruptedException{
		l.lock();
		try {
		//Espera a que no haya otro grupo realizando esta parte
		while(!puedeEntrar)entrarAva.await();
		nAva++;
		
		
		//Espera a que haya tres alumnos conectados
		if(nAva == ALUMNOS_AV) {
			puedeEntrar = false;
			esperaGrupo = false;
			esperaTerminar = true;
			nAva = 0;
			iniciaProyecto.signalAll();
		}
		
		while(esperaGrupo) {
			//Mensaje a mostrar si el alumno tiene que esperar al resto de miembros en el grupo
			System.out.println("PARTE AVANZADA: Alumno " + id + " espera a que haya " + ALUMNOS_AV + " alumnos");
			iniciaProyecto.await();
		}
		
		
		

		//Mensaje a mostrar cuando el alumno pueda empezar a cursar la parte avanzada
		System.out.println("PARTE AVANZADA: Hay " + ALUMNOS_AV + " alumnos. Alumno " + id + " empieza el proyecto");
	
			
			
		}finally {
			l.unlock();
		}
		
		
		
	
	}
	
	/* El alumno:
	 *   - informa que ya ha terminado de cursar la parte avanzada 
	 *   - espera hasta que los tres miembros del grupo hayan terminado su parte 
	 */ 
	public void finAvanzado(int id) throws InterruptedException{
		l.lock();
		try {
			nTerm++;
			//Espera a que los 3 alumnos terminen su parte avanzada
			
			if(nTerm == ALUMNOS_AV) {
				esperaTerminar = false;
				nTerm = 0;
				terminar.signalAll();
				puedeEntrar = true;
				entrarAva.signalAll();
				puedeEntrar = true;
			}
			
			while(esperaTerminar) {
				//Mensaje a mostrar si el alumno tiene que esperar a que los otros miembros del grupo terminen
				System.out.println("PARTE AVANZADA: Alumno " +  id + " termina su parte del proyecto. Espera al resto");
				terminar.await();
			}
			


			//Mensaje a mostrar cuando los tres alumnos del grupo han terminado su parte
			System.out.println("PARTE AVANZADA: LOS " + ALUMNOS_AV + " ALUMNOS HAN TERMINADO EL CURSO");

			
			
			
			
			
		}finally {
			l.unlock();
		}
		
	}
}
