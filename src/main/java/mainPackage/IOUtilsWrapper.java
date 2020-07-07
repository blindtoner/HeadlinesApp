package mainPackage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

public class IOUtilsWrapper {
	
	public String toString(final InputStream input, final Charset encoding) {
		try {
			return IOUtils.toString(input, encoding);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
