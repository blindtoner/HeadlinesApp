package mainPackage;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import utils.Utils;
import utils.timer;

public class ConnectToHeadLinesURL {

	public static List<String> getDataFromHN() throws IOException {
		timer.INSTANCE.setTimerStart();
		URL topStoriesURL = new URL("https://hacker-news.firebaseio.com/v0/topstories.json");
		InputStream is = connectAndReturnInputSt(topStoriesURL);
		String json = IOUtils.toString(is, Charset.forName("UTF-8"));

		Type listType = new TypeToken<List<String>>() {
		}.getType();
		Gson gson = new Gson();
		List<String> headLinesFromHnList = gson.fromJson(json, listType);

		int storiesToRetrieve = Utils.getAmountOfHeadlinesProperty();
		for (int i = 0; i < storiesToRetrieve; i++) {
			headLinesFromHnList.set(i, getHeadLineInfo(headLinesFromHnList.get(i)));
		}
		timer.INSTANCE.setTimerEnd();
		timer.INSTANCE.runTime();
		return headLinesFromHnList;
	}

	private static String getHeadLineInfo(String string) throws IOException {
		URL topStoriesURLTitle = new URL("https://hacker-news.firebaseio.com/v0/item/" + string + ".json");
		InputStream is = connectAndReturnInputSt(topStoriesURLTitle);
		String json = IOUtils.toString(is, Charset.forName("UTF-8"));
		Gson gson = new Gson();
		HeadLinesClass headLinesClass = gson.fromJson(json, HeadLinesClass.class);
		return headLinesClass.getTitle() + " - " + headLinesClass.getUrl();

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
