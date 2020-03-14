package mainPackage;

import java.util.List;

import javax.swing.SwingUtilities;

import display.Display;

public class Controller {
	public Controller(ObservedData observedData, ConnectToHeadLinesURLWrapper connectToHeadLinesURLWrapper) {
		SwingUtilities.invokeLater(() -> {
			observedData.addObserver(new Display());
		});
		List<HNHeadLinesModel> headLinesList = connectToHeadLinesURLWrapper.getDataFromHN();
		observedData.setProperty(headLinesList);
	}
}
