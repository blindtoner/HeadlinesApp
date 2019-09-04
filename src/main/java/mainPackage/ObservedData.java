package mainPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;

import javax.swing.Timer;

import display.DisplayModel;

public class ObservedData extends Observable {

	private int timerDelay;
	private static Timer timer;

	public void setHeadlines(List<HNHeadLinesModel> headLines) {
		DisplayModel.INSTANCE.setId(0);
		timerDelay = 0;
		int timerDelayTimeInMilliseconds = 6000;
		timer = new Timer(timerDelayTimeInMilliseconds, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setChanged();
				DisplayModel.INSTANCE.setId(timerDelay);
				notifyObservers(headLines.get(timerDelay));
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
}
