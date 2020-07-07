package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {
	static Properties props;
	
	private  void getAppProperties() throws IOException {
		props = new Properties();
		InputStream in = Utils.class.getResourceAsStream("app.properties");
		props.load(in);
		in.close();
	}
	public  int getAmountOfHeadlinesProperty() throws IOException{
		getAppProperties();
		return Integer.parseInt(props.getProperty("getAmountOfHeadlines"));
	}
	
	public  String getSourceURL() throws IOException{
		getAppProperties();
		return props.getProperty("sourceURL");
	}
	public  String getSourceURLTitle() throws IOException{
		getAppProperties();
		return props.getProperty("sourceURLTitle");
	}

}
