package primos;

import java.beans.PropertyChangeEvent;

import java.beans.PropertyChangeListener;

public class ControladorBarra2 implements PropertyChangeListener{

	private Panel panel;
	
	public ControladorBarra2(Panel panel){
		this.panel = panel;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("progress")){
			panel.progreso2((Integer) evt.getNewValue());
		}
	}

}
