package mainPackage;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import display.Display;
import display.DisplayModel;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Controller.class, ConnectToHeadLinesURL.class, DisplayModel.class })
public class ControllerTest {
	@Test
	public void testConstructorCallsAddObserver() throws Exception {
		Display mockDisplay = mock(Display.class);
		ObservedData mockObservedData = mock(ObservedData.class);
		PowerMockito.whenNew(Display.class).withNoArguments().thenReturn(mockDisplay);
		ConnectToHeadLinesURLWrapper mockConnectToHeadLinesURLWrapper = mock(ConnectToHeadLinesURLWrapper.class);
		PowerMockito.whenNew(ConnectToHeadLinesURLWrapper.class).withNoArguments()
				.thenReturn(mockConnectToHeadLinesURLWrapper);
		List<HeadLinesClass> expectedResults = Arrays.asList(new HeadLinesClass(), new HeadLinesClass());
		when(mockConnectToHeadLinesURLWrapper.getDataFromHN()).thenReturn(expectedResults);

		Controller controller = new Controller(mockObservedData);
		PowerMockito.verifyNew(Display.class).withNoArguments();
		verify(mockObservedData, Mockito.atMost(1)).addObserver(mockDisplay);
	}

	@Test
	public void testGettingHeadLinesfromHN() throws Exception {
		Display mockDisplay = mock(Display.class);
		PowerMockito.whenNew(Display.class).withNoArguments().thenReturn(mockDisplay);
		ObservedData mockObservedData = mock(ObservedData.class);
		List<HeadLinesClass> expectedResults = Arrays.asList(new HeadLinesClass(), new HeadLinesClass());

		ConnectToHeadLinesURLWrapper mockConnectToHeadLinesURLWrapper = mock(ConnectToHeadLinesURLWrapper.class);
		PowerMockito.whenNew(ConnectToHeadLinesURLWrapper.class).withNoArguments()
				.thenReturn(mockConnectToHeadLinesURLWrapper);
		when(mockConnectToHeadLinesURLWrapper.getDataFromHN()).thenReturn(expectedResults);

		Controller controller = new Controller(mockObservedData);
		PowerMockito.verifyNew(ConnectToHeadLinesURLWrapper.class).withNoArguments();
		verify(mockConnectToHeadLinesURLWrapper).getDataFromHN();
		assertEquals(expectedResults, mockConnectToHeadLinesURLWrapper.getDataFromHN());
		verify(mockDisplay);
	}
}
