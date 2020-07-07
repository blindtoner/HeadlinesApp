package mainPackage;

import java.util.List;

import javax.swing.SwingUtilities;

import display.Display;
import models.HNHeadLinesModel;
import utils.Utils;

public class Controller {
	public Controller(ObservedData observedData, ConnectToHeadLinesURLWrapper connectToHeadLinesURLWrapper) {
		SwingUtilities.invokeLater(() -> {
			observedData.addObserver(new Display(), new Utils().getAmountOfHeadlinesProperty());
		});
		List<HNHeadLinesModel> headLinesList = connectToHeadLinesURLWrapper.getDataFromHN();
		observedData.setProperty(headLinesList);
	}
}
