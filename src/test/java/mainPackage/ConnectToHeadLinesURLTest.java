package mainPackage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.google.gson.reflect.TypeToken;

import models.HNHeadLinesModel;
import utils.Utils;

class ConnectToHeadLinesURLTest {

	@Test
	void testGetDatafromHN() throws IOException {
		String expectedURLString = "https://localhost";
		String expectedURLTitleString = "https://localhost/title/";
		String expectedURLTitleStringFull = "https://localhost/title/23749821.json";
		URL expectedURL = new URL(expectedURLString);
		URL expectedURLTitle = new URL(expectedURLTitleStringFull);
		Utils mockUtils = mock(Utils.class);
		when(mockUtils.getSourceURL()).thenReturn(expectedURLString);
		when(mockUtils.getSourceURLTitle()).thenReturn(expectedURLTitleString);
		
		when(mockUtils.getAmountOfHeadlinesProperty()).thenReturn(1);
		
		URLConnectionWrapper mockConnectionWrapper = mock(URLConnectionWrapper.class);
		
		InputStream mockInputStream1 = mock(InputStream.class);
		InputStream mockInputStream2 = mock(InputStream.class);
		when(mockConnectionWrapper.connectAndReturnInputSt(expectedURL)).thenReturn(mockInputStream1);
		when(mockConnectionWrapper.connectAndReturnInputSt(expectedURLTitle)).thenReturn(mockInputStream2);
		
		GsonWrapper mockGsonWrapper = mock(GsonWrapper.class);
		
		IOUtilsWrapper mockIOUtilsWrapper = mock(IOUtilsWrapper.class);
		String expectedItemJson = "[23749821,23752284,23749352]";
		when(mockIOUtilsWrapper.toString(Mockito.eq(mockInputStream1), Mockito.any())).thenReturn(expectedItemJson);
		String expectedDetailsJson = "{\"by\":\"user\",\"descendants\":97,\"id\":23749821,\"kids\":[23750499,23752147,23750722,23751105,23752557,23751489,23752226,23750567,23751619,23752484,23751664,23751624,23751107,23751608,23753163,23751838,23752566,23750771,23751414,23751506,23750920,23751480,23750660,23750935,23750806,23750361],\"score\":455,\"time\":1594054662,\"title\":\"Test Title\",\"type\":\"story\",\"url\":\"https://test.com/testArticle\"}";
		when(mockIOUtilsWrapper.toString(Mockito.eq(mockInputStream2), Mockito.any())).thenReturn(expectedDetailsJson );
		
		ArrayList<String> expectedItemList = new ArrayList<String>();
		expectedItemList.add("23749821");
		
		HNHeadLinesModel expectedDetails = new HNHeadLinesModel();
		expectedDetails.setTitle("Test Title");
		expectedDetails.setBy("user");
		expectedDetails.setType("story");
		expectedDetails.setUrl("https://test.com/testArticle");
		
		Type listType = new TypeToken<List<String>>() {}.getType();
		
		
		when(mockGsonWrapper.fromJson(Mockito.eq(expectedItemJson), Mockito.eq(listType))).thenReturn(expectedItemList);
		when(mockGsonWrapper.fromJson(Mockito.eq(expectedDetailsJson), Mockito.eq(HNHeadLinesModel.class))).thenReturn(expectedDetails);
				
		ConnectToHeadLinesURL connectToHeadLinesURL = new ConnectToHeadLinesURL(mockConnectionWrapper, mockGsonWrapper, mockIOUtilsWrapper, mockUtils);
		
		List<HNHeadLinesModel> expected = new ArrayList<HNHeadLinesModel>();
		expected.add(expectedDetails);
		
		List<HNHeadLinesModel> actual = null;
		
		
		actual = connectToHeadLinesURL.getDataFromHN();
		
		
		assertEquals(expected, actual);
		
		
		
	}

}
