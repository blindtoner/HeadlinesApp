package mainPackage;

import java.io.IOException;
import java.util.List;

public class ConnectToHeadLinesURLWrapper {

	public List<HeadLinesClass> getDataFromHN() {
		try {
			return ConnectToHeadLinesURL.getDataFromHN();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
