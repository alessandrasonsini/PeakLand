package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import logic.controller.MainController;
import logic.model.enums.PageId;

public class TestMainController {

	@Test
	public void TestOnActionRequiredViewInfoWithoutLogin() {
		PageId result = new MainController().onActionRequired(PageId.VIEW_INFO, null);
		assertEquals(PageId.VIEW_INFO, result);
	}
	
	@Test
	public void TestOnActionRequiredAddPathWithoutLogin() {
		PageId result = new MainController().onActionRequired(PageId.ADD_PATH, null);
		assertEquals(PageId.LOGIN, result);
	}
}
