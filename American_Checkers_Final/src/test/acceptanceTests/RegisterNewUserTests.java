package test.acceptanceTests;

import junit.framework.TestCase;
import production.business.UserAccountManager;

public class RegisterNewUserTests extends TestCase{
	
	private UserAccountManager userAccountManager;
	private int result;
	
	protected void setUp() throws Exception {
		super.setUp();
		userAccountManager = new UserAccountManager();
		userAccountManager.registerNewUser("Venkat5749", "Hello@World123", "Hello@World123", "Venkat",
				"Inti", "venkat.inti@gmail.com", "2076435734");
	}
	
	public void testSuccessfulRegistration() {
		
		String registrationResult = userAccountManager.registerNewUser("Venkat517", "Hello@World123", "Hello@World123", "Venkat",
				"Inti", "venkat.inti@gmail.com", "2076435734");
		assertEquals(registrationResult, UserAccountManager.NOINPUTERROR);
		result = userAccountManager.login("Venkat517", "Hello@World123");
		assertTrue(result==1);
		// assertTrue(userAccount.getEmail().equalsIgnoreCase("venkat.inti@gmail.com"));
	}
	
	public void testRegistrationWithInvalidEmail() {
		String registrationResult = userAccountManager.registerNewUser("Venkat517", "Hello@World123", "Hello@World123",
				"Venkat", "Inti", "venkat.inti@gmail", "7024265734");
		System.out.println(registrationResult);
		assertFalse(registrationResult.equalsIgnoreCase(UserAccountManager.NOINPUTERROR));
	}
	
	public void testRegistrationWithInvalidUserName() {
		String registrationResult = userAccountManager.registerNewUser("@$517", "Hello@World123", "Hello@World123", "Venkat",
				"Inti", "venkat.inti@gmail.com", "2076435734");
		System.out.println(registrationResult);
		assertFalse(registrationResult.equalsIgnoreCase(UserAccountManager.NOINPUTERROR));
	}

	
	public void testRegistrationWithInvalidPassword() {
		String registrationResult = userAccountManager.registerNewUser("Venkat517", "Hello@World123", "Welcome@World123", "Venkat",
				"Inti", "venkat.inti@gmail.com", "2076435734");
		System.out.println(registrationResult);
		assertFalse(registrationResult.equalsIgnoreCase(UserAccountManager.NOINPUTERROR));
	}
}
