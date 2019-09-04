package mainPackage;

import java.util.List;

import javax.swing.SwingUtilities;

import display.Display;

public class Controller {
	public Controller(ObservedData observedData) {
		SwingUtilities.invokeLater(() -> {
			observedData.addObserver(new Display());
		});
		ConnectToHeadLinesURLWrapper connectToHeadLinesURLWrapper = new ConnectToHeadLinesURLWrapper();
		List<HNHeadLinesModel> headLinesList = connectToHeadLinesURLWrapper.getDataFromHN();
		observedData.setHeadlines(headLinesList);
	}
}
