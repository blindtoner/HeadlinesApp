package mainPackage;

import java.io.IOException;
import java.util.List;

import models.HNHeadLinesModel;
import utils.Utils;

public class ConnectToHeadLinesURLWrapper {

	private ConnectToHeadLinesURL connectToHeadLinesURL;

	public ConnectToHeadLinesURLWrapper() {
		this.connectToHeadLinesURL = new ConnectToHeadLinesURL(new URLConnectionWrapper(), new GsonWrapper(), new IOUtilsWrapper(), new Utils());
	}

	public List<HNHeadLinesModel> getDataFromHN() {
		try {
			return connectToHeadLinesURL.getDataFromHN();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
