package mainPackage;

import org.junit.*;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MainApp.class)
public class MainAppTest {
	@Test
	public void testControllerConstructorIsCalledWithObservedData() throws Exception {
		Controller mockController = mock(Controller.class);
		ObservedData mockObservedData = mock(ObservedData.class);
		PowerMockito.whenNew(Controller.class).withArguments(mockObservedData).thenReturn(mockController);
		PowerMockito.whenNew(ObservedData.class).withNoArguments().thenReturn(mockObservedData);

		MainApp.main(null);

		PowerMockito.verifyNew(Controller.class).withArguments(new ObservedData());
	}
}
