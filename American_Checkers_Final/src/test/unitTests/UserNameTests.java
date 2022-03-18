package test.unitTests;

import junit.framework.TestCase;
import production.business.UserAccount;


public class UserNameTests extends TestCase {

	public void testValidUserName() {
		assertTrue(UserAccount.isUserNameValid("IntiVenkata"));
		assertTrue(UserAccount.isUserNameValid("venkat5749"));
		assertTrue(UserAccount.isUserNameValid("Inti"));
	}
	
	public void testInValidUserName() {
		assertFalse(UserAccount.isUserNameValid("$ubbu"));
		assertFalse(UserAccount.isUserNameValid("98venkat"));
		assertFalse(UserAccount.isUserNameValid("Subba Rao5749"));
	}
}
