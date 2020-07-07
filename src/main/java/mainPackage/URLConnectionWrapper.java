package mainPackage;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLConnectionWrapper {

	public InputStream connectAndReturnInputSt(URL urlParameter) throws IOException {
		URL url = null;
		URLConnection conn = null;
		try {
			url = urlParameter;
			conn = url.openConnection();
			conn.connect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return conn.getInputStream();
	}
	
}
