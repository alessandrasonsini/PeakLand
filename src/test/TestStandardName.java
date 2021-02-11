package test;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.model.StandardName;

public class TestStandardName {
	
	@Test
	public void testStandardizeName() {
		assertEquals("Hello",StandardName.standardizeName("hello"));
	}
	
	@Test
	public void testStandardizeNameForEmptyString() {
		assertEquals("",StandardName.standardizeName(""));
	}
}
