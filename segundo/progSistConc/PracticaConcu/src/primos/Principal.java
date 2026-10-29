package primos;


import javax.swing.*;
import javax.swing.JFrame;

public class Principal {
	
	public static void crearGUI() {
       	System.out.println("crearGUI() - isEventDispatchThread? "+ SwingUtilities.isEventDispatchThread());
		JFrame ventana = new JFrame("Numeros Primos");
		
		Panel panel = new Panel();
		
		ControladorBarra1 controladorBarra1 = new ControladorBarra1(panel);
		ControladorBarra2 controladorBarra2 = new ControladorBarra2(panel);
		ControladorBarra3 controladorBarra3 = new ControladorBarra3(panel);
		
		Controlador ctr = new Controlador(panel, controladorBarra1, controladorBarra2, controladorBarra3);
		panel.controlador(ctr);
		
		ventana.setContentPane(panel);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.pack();
		ventana.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				try {
					crearGUI();
				} catch (Exception e) {
					System.out.println("Tarea cancelada");
				}
				

				
			}
		});

	}
	
	
}
