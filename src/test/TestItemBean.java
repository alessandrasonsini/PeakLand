package test;

import static org.junit.Assert.*;
import org.junit.Test;
import org.mockito.Mockito;

import logic.bean.ItemBean;

public class TestItemBean {
	
	@Test
	public void testConvertToText() {
		ItemBean abstractClass = Mockito.mock(ItemBean.class, Mockito.CALLS_REAL_METHODS);
		String[] array = {"test", "convert", "to", "text"};
		
		assertEquals("test convert to text ", abstractClass.convertToText(array));
	}
	
	@Test
	public void testConvertToTextObject() {
		ItemBean abstractClass = Mockito.mock(ItemBean.class, Mockito.CALLS_REAL_METHODS);
		Object obj = null;
		
		assertEquals("Not available", abstractClass.convertToText(obj));
	}
}
