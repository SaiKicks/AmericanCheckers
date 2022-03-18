package test.unitTests;

import junit.framework.TestCase;
import production.business.UserAccount;


public class PasswordTests extends TestCase {

	public void testValidPassword() {
		assertTrue(UserAccount.isPasswordValid("@Qw1$se"));
		assertTrue(UserAccount.isPasswordValid("Hello$World21"));
		assertTrue(UserAccount.isPasswordValid("MyDreams@123"));
	}
	public void testInValidPassword() {
		assertFalse(UserAccount.isPasswordValid("@#&password123")); //No Upper Case Letter
		assertFalse(UserAccount.isPasswordValid("Hello$world"));	//No Digits
		assertFalse(UserAccount.isPasswordValid("QWERTY123"));		//No Lower Case and Special Characters
	}
}
