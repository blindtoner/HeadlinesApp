package mainPackage;

import java.util.List;

import display.Display;
import display.DisplayModel;

public class Controller {
	public Controller(ObservedData observedData) {
		DisplayModel displayModel = new DisplayModel();
		observedData.addObserver(new Display());
		ConnectToHeadLinesURLWrapper connectToHeadLinesURLWrapper = new ConnectToHeadLinesURLWrapper();
		List<String> headLinesList = connectToHeadLinesURLWrapper.getDataFromHN();
		observedData.setHeadlines(headLinesList, displayModel);
	}
}
