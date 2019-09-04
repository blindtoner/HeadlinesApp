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
import utils.timer;

public class ConnectToHeadLinesURL {

	public static List<HNHeadLinesModel> getDataFromHN() throws IOException {
		timer.INSTANCE.setTimerStart();
		URL topStoriesURL = new URL("https://hacker-news.firebaseio.com/v0/topstories.json");
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
		timer.INSTANCE.setTimerEnd();
		timer.INSTANCE.runTime();
		return headLinesFromHnHeadlinesList;
	}

	private static HNHeadLinesModel getHeadLineInfo(String string) throws IOException {
		URL topStoriesURLTitle = new URL("https://hacker-news.firebaseio.com/v0/item/" + string + ".json");
		InputStream is = connectAndReturnInputSt(topStoriesURLTitle);
		String json = IOUtils.toString(is, Charset.forName("UTF-8"));
		Gson gson = new Gson();
		HNHeadLinesModel headLinesClass = gson.fromJson(json, HNHeadLinesModel.class);
		return headLinesClass;

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
		InputStream is = conn.getInputStream();
		return is;
	}
}
