package mainPackage;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import models.HNHeadLinesModel;
import utils.Utils;

public class ConnectToHeadLinesURL {

	URLConnectionWrapper _urlConnectionWrapper;
	GsonWrapper _gson;
	IOUtilsWrapper _IOUtils;
	Utils _Utils;
	
	
	public ConnectToHeadLinesURL(URLConnectionWrapper urlConnectionWrapper, GsonWrapper gson, IOUtilsWrapper IOUtils, Utils utils) {
		_urlConnectionWrapper = urlConnectionWrapper;
		_gson = gson;
		_IOUtils = IOUtils;
		_Utils = utils;
	}

	public List<HNHeadLinesModel> getDataFromHN() throws IOException {
		URL topStoriesURL = new URL(_Utils.getSourceURL());
		
		InputStream is = _urlConnectionWrapper.connectAndReturnInputSt(topStoriesURL);
		String json = _IOUtils.toString(is, Charset.forName("UTF-8"));

		Type listType = new TypeToken<List<String>>() {}.getType();
		
		List<String> headLinesItemList = _gson.fromJson(json, listType);
		
		int storiesToRetrieve = _Utils.getAmountOfHeadlinesProperty();

		HNHeadLinesModel[] headLinesFromHnHeadlinesDetailsList = new HNHeadLinesModel[storiesToRetrieve];
		
		for (int i = 0; i < storiesToRetrieve; i++) {
			HNHeadLinesModel headLineInfo = getHeadLineInfo(_urlConnectionWrapper,_gson, headLinesItemList.get(i));
			headLinesFromHnHeadlinesDetailsList[i] = headLineInfo;
		}
		return Arrays.asList(headLinesFromHnHeadlinesDetailsList);
	}

	private HNHeadLinesModel getHeadLineInfo(URLConnectionWrapper urlConnectionWrapper, GsonWrapper gson, String string) throws IOException {
		URL topStoriesURLTitle = new URL(_Utils.getSourceURLTitle() + string + ".json");
		InputStream inputStreamForTopStoryItem = _urlConnectionWrapper.connectAndReturnInputSt(topStoriesURLTitle);
		String json = _IOUtils.toString(inputStreamForTopStoryItem, Charset.forName("UTF-8"));
		return _gson.fromJson(json, HNHeadLinesModel.class);
	}

}
