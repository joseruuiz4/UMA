package MoleculasAgua;
import java.util.concurrent.locks.*;

public class GestorAgua {	

	private Lock l = new ReentrantLock();
	private Condition puertaH = l.newCondition();
	private Condition puertaO = l.newCondition();
	private Condition puertaAgua = l.newCondition();
	
	
	private int nH = 0;
	private int nO = 0;
	
	
	
	public void hListo(int id) { 
		l.lock();
		
		try {
			nH++;
			
			while(nH < 2)
			
			
		}finally {
			l.unlock();
		}
		

	}
	
	public void oListo(int id) { 

		l.lock();

		try {

			
			

		}finally {
			l.unlock();
		}




	}
}
