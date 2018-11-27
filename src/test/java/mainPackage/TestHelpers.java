package mainPackage;

import java.util.Collection;

import org.mockito.Mockito;
import org.mockito.invocation.Invocation;

public class TestHelpers {

	protected static int mockitoCallCount(Object mockToCount) {
		Collection<Invocation> invocations = Mockito.mockingDetails(mockToCount).getInvocations();
		return invocations.size();
	}

}
