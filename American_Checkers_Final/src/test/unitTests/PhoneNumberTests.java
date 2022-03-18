package test.unitTests;

import junit.framework.TestCase;
import production.business.UserAccount;


public class PhoneNumberTests extends TestCase {

	public void testValidPhoneNumber() {
		assertTrue(UserAccount.isPhoneNumberValid("8162356218"));
		assertTrue(UserAccount.isPhoneNumberValid("8978456345"));
		assertTrue(UserAccount.isPhoneNumberValid("5678456345"));
	}

	public void testInValidPhoneNumber() {
		assertFalse(UserAccount.isPhoneNumberValid("98789876"));
		assertFalse(UserAccount.isPhoneNumberValid("876sub3455"));
		assertFalse(UserAccount.isPhoneNumberValid("987 898 7689"));
	}
}
