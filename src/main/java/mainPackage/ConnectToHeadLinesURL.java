package mainPackage;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import utils.Utils;

public class ConnectToHeadLinesURL {

	public static List<HNHeadLinesModel> getDataFromHN() throws IOException {
		URL topStoriesURL = new URL(Utils.getSourceURL());
		InputStream is = connectAndReturnInputSt(topStoriesURL);
		String json = IOUtils.toString(is, Charset.forName("UTF-8"));

		Type listType = new TypeToken<List<String>>() {
		}.getType();
		Gson gson = new Gson();
		List<String> headLinesFromHnStringList = gson.fromJson(json, listType);
		List<HNHeadLinesModel> headLinesFromHnHeadlinesList = new ArrayList<HNHeadLinesModel>();
		int storiesToRetrieve = Utils.getAmountOfHeadlinesProperty();
		for (int i = 0; i < storiesToRetrieve; i++) {
			headLinesFromHnHeadlinesList.add(i, getHeadLineInfo(headLinesFromHnStringList.get(i) + ""));
		}
		return headLinesFromHnHeadlinesList;
	}

	private static HNHeadLinesModel getHeadLineInfo(String string) throws IOException {
		URL topStoriesURLTitle = new URL(Utils.getSourceURLTitle() + string + ".json");
		InputStream is = connectAndReturnInputSt(topStoriesURLTitle);
		String json = IOUtils.toString(is, Charset.forName("UTF-8"));
		Gson gson = new Gson();
		return gson.fromJson(json, HNHeadLinesModel.class);
	}

	private static InputStream connectAndReturnInputSt(URL urlParameter) throws IOException {
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
