package test.unitTests;

import junit.framework.TestCase;
import production.business.UserAccount;


public class EmailAddressTests extends TestCase {

	public void testValidEmailAddress() {
		assertTrue(UserAccount.isEmailValid("ms.dhoni@gmail.com"));
		assertTrue(UserAccount.isEmailValid("ivsr@amazon.in"));
		assertTrue(UserAccount.isEmailValid("i@canvas.com"));
	}
	
	public void testInvalidValidEmailAddress() {
		assertFalse(UserAccount.isEmailValid("@google.com"));
		assertFalse(UserAccount.isEmailValid("hello@amazon.i"));
		assertFalse(UserAccount.isEmailValid("inti:sr@gmail.com"));
	}
}
