package primos;

import java.beans.PropertyChangeEvent;

import java.beans.PropertyChangeListener;

public class ControladorBarra1 implements PropertyChangeListener{

	private Panel panel;
	
	public ControladorBarra1(Panel panel){
		this.panel = panel;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("progress")){
			panel.progreso1((Integer) evt.getNewValue());
		}
	}

}
