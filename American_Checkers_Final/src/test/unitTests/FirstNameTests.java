package test.unitTests;

import junit.framework.TestCase;
import production.business.UserAccount;


public class FirstNameTests extends TestCase {

	public void testValidFirstName() {
		assertTrue(UserAccount.isFirstNameValid("SubbaRao"));
		assertTrue(UserAccount.isFirstNameValid("john"));
		assertTrue(UserAccount.isFirstNameValid("VENKAT"));
	}
	
	public void testInValidFirstName() {
		assertFalse(UserAccount.isFirstNameValid("Inti5749"));
		assertFalse(UserAccount.isFirstNameValid("Venkat@"));
		assertFalse(UserAccount.isFirstNameValid("I V_Subbu"));
	}
}
