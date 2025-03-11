package primos;

import java.beans.PropertyChangeEvent;

import java.beans.PropertyChangeListener;

public class ControladorBarra3 implements PropertyChangeListener{

	private Panel panel;
	
	public ControladorBarra3(Panel panel){
		this.panel = panel;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("progress")){
			panel.progreso3((Integer) evt.getNewValue());
		}
	}

}
