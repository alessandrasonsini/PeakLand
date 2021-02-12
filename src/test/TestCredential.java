package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import logic.model.Credential;
import logic.model.exception.EmptyMandatoryFieldsException;

public class TestCredential {
	
	@Test
	public void testVerifyCredentialTrue() throws EmptyMandatoryFieldsException {
		Credential first = new Credential("test","test");
		Credential second = new Credential("test","test");
		boolean result = first.verifyCredential(second); 
		assertEquals(true, result);
	}
	
	@Test
	public void testVerifyCredentialFalse() throws EmptyMandatoryFieldsException {
		Credential first = new Credential("test","tset");
		Credential second = new Credential("test","test");
		boolean result = first.verifyCredential(second); 
		assertEquals(false, result);
	}
}
