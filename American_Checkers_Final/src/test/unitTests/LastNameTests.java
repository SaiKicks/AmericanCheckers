package test.unitTests;

import junit.framework.TestCase;
import production.business.UserAccount;


public class LastNameTests extends TestCase {

	public void testValidLastName() {
		assertTrue(UserAccount.isLastNameValid("stevesmith"));
		assertTrue(UserAccount.isLastNameValid("SWATHI"));
		assertTrue(UserAccount.isLastNameValid("Rakesh"));
	}
	
	public void testInValidLastName() {
		assertFalse(UserAccount.isLastNameValid("john smith"));
		assertFalse(UserAccount.isLastNameValid("Inti234"));
		assertFalse(UserAccount.isLastNameValid("Inti@34"));
	}
}
