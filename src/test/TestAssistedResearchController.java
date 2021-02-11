package test;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

import logic.controller.AssistedResearchController;

public class TestAssistedResearchController {

	@Test
	public void TestGetPossibilitiesSizeForGroundEnum() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method m = AssistedResearchController.class.getDeclaredMethod("getPossibilitiesSize", String.class);
		m.setAccessible(true);
		int result = (int)m.invoke(new AssistedResearchController(),"ground");
		assertEquals(3,result);
	}
}
