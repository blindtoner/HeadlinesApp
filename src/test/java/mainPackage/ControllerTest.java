package mainPackage;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import display.Display;

public class ControllerTest {
	@Test
	public void testConstructorCallsAddObserver() throws Exception {
		Display mockDisplay = mock(Display.class);
		ObservedData mockObservedData = mock(ObservedData.class);
		ConnectToHeadLinesURLWrapper mockConnectToHeadLinesURLWrapper = mock(ConnectToHeadLinesURLWrapper.class);
		List<HNHeadLinesModel> expectedResults = Arrays.asList(new HNHeadLinesModel(), new HNHeadLinesModel());
		when(mockConnectToHeadLinesURLWrapper.getDataFromHN()).thenReturn(expectedResults);

		Controller controller = new Controller(mockObservedData, mockConnectToHeadLinesURLWrapper);
		verify(mockObservedData, Mockito.atMost(1)).addObserver(mockDisplay);
	}

	@Test
	public void testGettingHeadLinesfromHN() throws Exception {
		ObservedData mockObservedData = mock(ObservedData.class);
		List<HNHeadLinesModel> expectedResults = Arrays.asList(new HNHeadLinesModel(), new HNHeadLinesModel());

		ConnectToHeadLinesURLWrapper mockConnectToHeadLinesURLWrapper = mock(ConnectToHeadLinesURLWrapper.class);
		when(mockConnectToHeadLinesURLWrapper.getDataFromHN()).thenReturn(expectedResults);

		Controller controller = new Controller(mockObservedData, mockConnectToHeadLinesURLWrapper);
		verify(mockConnectToHeadLinesURLWrapper).getDataFromHN();
		assertEquals(expectedResults, mockConnectToHeadLinesURLWrapper.getDataFromHN());
	}
}
