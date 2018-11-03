package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {
	static Properties props;
	private static void getAppProperties() throws IOException {
		props = new Properties();
		InputStream in = Utils.class.getResourceAsStream("app.properties");
		props.load(in);
		in.close();
	}
	public static int getAmountOfHeadlinesProperty() throws IOException{
		Utils.getAppProperties();
		return Integer.parseInt(props.getProperty("getAmountOfHeadlines"));
	}

}
