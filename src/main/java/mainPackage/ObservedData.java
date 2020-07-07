package mainPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import display.DisplayModel;
import models.HNHeadLinesModel;

public class ObservedData {

	List<HNHeadLinesModel> property= new ArrayList<HNHeadLinesModel>();
	PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private int timerDelay;
	private static Timer timer;

	public void setProperty(List<HNHeadLinesModel> headLinesList) {
			List<HNHeadLinesModel> old = property;
			property = headLinesList;
			
			DisplayModel.INSTANCE.setId(0);
			timerDelay = 0;
			int timerDelayTimeInMilliseconds = 1000;
			timer = new Timer(timerDelayTimeInMilliseconds, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					DisplayModel.INSTANCE.setId(timerDelay);
					pcs.firePropertyChange("property", old, property.get(timerDelay));
					timerDelay++;
				}
			});
			timer.setInitialDelay(100);
			timer.start();
		}

	public static void pauseTimer() {
		timer.stop();
	}

	public static void resumeTimer() {
		timer.restart();
	}
	public void addObserver(PropertyChangeListener l) {
		pcs.addPropertyChangeListener("property", l);
	}
}
