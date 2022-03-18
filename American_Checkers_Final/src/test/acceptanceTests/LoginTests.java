package test.acceptanceTests;

import junit.framework.TestCase;
import production.business.UserAccountManager;

public class LoginTests extends TestCase{
	
	private UserAccountManager userAccountManager;
	private int result;

	protected void setUp() throws Exception {
		super.setUp();
		userAccountManager = new UserAccountManager();
		userAccountManager.registerNewUser("Venkat5749", "Hello@World123", "Hello@World123", "Venkat",
				"Inti", "venkat.inti@gmail.com", "2076435734");
	}
	
	public void testSuccessfulLogin() {
		
		result = userAccountManager.login("Venkat5749", "Hello@World123");
		assertTrue(result==1);
	}
	
	public void testLoginWithInvalidPassword() {
		String registrationResult = userAccountManager.registerNewUser("Venkat517", "Hello@World123", "Hello@World123", "Venkat",
				"Inti", "venkat.inti@gmail.com", "2076435734");
		assertEquals(registrationResult, UserAccountManager.NOINPUTERROR);
		
		int result = userAccountManager.login("Venkat517", "World123");
		assertFalse(result==1);
	}
}
