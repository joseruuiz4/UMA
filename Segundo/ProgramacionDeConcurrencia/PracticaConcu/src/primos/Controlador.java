package primos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener {
	private Panel panel = new Panel();
	private WorkerTwin workerTwin;
	private WorkerCousin workerCousin;
	private WorkerSexy workerSexy;
	private ControladorBarra1 controladorBarra1;
	private ControladorBarra2 controladorBarra2;
	private ControladorBarra3 controladorBarra3;
	
	
	
	
	public Controlador(Panel panel, ControladorBarra1 controladorBarra1, ControladorBarra2 controladorBarra2, ControladorBarra3 controladorBarra3) {
		this.panel = panel;
		this.controladorBarra1 = controladorBarra1;
		this.controladorBarra2  = controladorBarra2;
		this.controladorBarra3 =  controladorBarra3;
	}

	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Cancelar")) {
			if (workerTwin != null && !workerTwin.isDone()) {
		          workerTwin.cancel(true);
		          
		        }
		    if (workerCousin != null && !workerCousin.isDone()) {
		          workerCousin.cancel(true);
		        }
		    if (workerSexy != null && !workerSexy.isDone()) {
		          workerSexy.cancel(true);
		        }
			panel.limpiaAreaCousin();
			panel.mensajeCousin("Area  primos 'cousin' creada");
			panel.progreso1(0);
			
			panel.limpiaAreaSexy();
			panel.mensajeSexy("Area  primos 'sexy' creada");
			panel.progreso2(0);
			
			panel.limpiaAreaTwin();
			panel.mensajeTwin("Area  primos 'twin' creada");
			panel.progreso3(0);
			
			panel.mensaje("Tarea cancelada");
			
			
		      
			
		}else if(e.getActionCommand().equals("Numero1")) {
			if(workerTwin == null || workerTwin.isDone()) { //si la anterior busqueda no ha terminado espera a que termine o a que sea cancelada
				panel.mensaje("GUI creada");
				panel.limpiaAreaTwin();
				workerTwin = new WorkerTwin(panel.numero1(), panel);
				workerTwin.addPropertyChangeListener(controladorBarra1);
				workerTwin.execute();
				panel.mensajeTwin("Calculando primos twin...");
			}
				
			
			
			
		}else if(e.getActionCommand().equals("Numero2")) {
			if(workerCousin == null || workerCousin.isDone()) {	//si la anterior busqueda no ha terminado espera a que termine o a que sea cancelada
				panel.mensaje("GUI creada");
				panel.limpiaAreaCousin();
				workerCousin = new WorkerCousin(panel.numero2(), panel);
				workerCousin.addPropertyChangeListener(controladorBarra2);
				workerCousin.execute();
				panel.mensajeCousin("Calculando primos cousin...");
			}
				
			
		}else if(e.getActionCommand().equals("Numero3")) {
			if(workerSexy == null || workerSexy.isDone()) {	//si la anterior busqueda no ha terminado espera a que termine o a que sea cancelada
				panel.mensaje("GUI creada");
				panel.limpiaAreaSexy();
				workerSexy = new WorkerSexy(panel.numero3(), panel);
				workerSexy.addPropertyChangeListener(controladorBarra3);
				workerSexy.execute();
				panel.mensajeSexy("Calculando primos sexy...");
			}
			
			
		}
		
	}
	

}
