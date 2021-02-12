package test;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.mockito.Mockito;
import logic.controller.HomeController;
import logic.controller.Controller;
import logic.model.enums.PageId;
import logic.model.exception.SystemException;

public class TestController {

	@Test
	public void testExecuteActionHomeController() throws SystemException {
		Controller controller = Mockito.mock(Controller.class, Mockito.CALLS_REAL_METHODS);
		PageId page = PageId.HOME;
		
		assertTrue(controller.executeAction(page) instanceof HomeController);
	}
	
	@Test(expected = SystemException.class)
	public void testExecuteActionException() throws SystemException {
		Controller controller = Mockito.mock(Controller.class, Mockito.CALLS_REAL_METHODS);
		PageId page = PageId.ADD_REVIEW;
		
		controller.executeAction(page);
	}
}
