package production.business;

public class UserAccount {
	
	private String userName;
	private String password;
	
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	
	public UserAccount() {	
	}
	
	public static String checkInputError(String userName, String password, String firstName, String lastName, String email, String phone){
		String errorMessage ="";
		if (!isUserNameValid(userName))
				errorMessage += "Invalid user name.\n";
		if (!isPasswordValid(password))
				errorMessage += "Invalid password.\n";	
		if (!isFirstNameValid(firstName))
			errorMessage += "Invalid first name.\n";
		if (!isLastNameValid(lastName))
			errorMessage += "Invalid last name.\n";
		if (!isEmailValid(email))
			errorMessage += "Invalid email.\n";
		if (!isPhoneNumberValid(phone))
			errorMessage += "Invalid phone number.\n";
		return errorMessage;
	}
	
	public String getUserName(){
		return userName;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	// you need to complete this method; do not change the return type
	public static boolean isUserNameValid(String userName){
		// check if userName is valid	
		
		if(userName.contains(" ") || !userName.matches("^[A-Za-z][A-Za-z0-9]*$"))
			return false;
		
		return true;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	

	// you need to complete this method; do not change the return type
	public static boolean isPasswordValid(String password){
		// check if password is valid
		
		if(password.length()<6 || !password.matches(".*[@#$^&]+.*$") || !password.matches(".*[0-9]+.*$") || !password.matches(".*[a-z]+.*$") || !password.matches(".*[A-Z]+.*$"))
			return false;
		
		return true;
	}

    public boolean isValidCredential(String userName, String password) {
         return matchUserName(userName) && matchPassword(password);
    }
    
    public boolean matchUserName(String userName) {
         return userName != null && userName.equalsIgnoreCase(this.userName);
    }
    
    private boolean matchPassword(String password) {
        return password != null && password.equals(this.password);
   }

	public String getFirstName(){
		return firstName;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	// you need to complete this method; do not change the return type
	public static boolean isFirstNameValid(String firstName){
		// check if firstName is valid
		
		if(!firstName.matches("^[A-Za-z]*$"))
			return false;
		
		return true;
	}
	
	public String getLastName(){
		return lastName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	// you need to complete this method; do not change the return type
	public static boolean isLastNameValid(String lastName){
		// check if lastName is valid
		
		if(!lastName.matches("^[A-Za-z]*$"))
			return false;
		
		return true;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	// you need to complete this method; do not change the return type
	public static boolean isEmailValid(String email){
		// check if email is valid
		
		if(!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"))
			return false;
		
		return true;
	}

	public String getPhoneNumber(){
		return phone;
	}

	public void setPhoneNumber(String phone){
		this.phone = phone;
	}

	// you need to complete this method; do not change the return type
	public static boolean isPhoneNumberValid(String phone){
		// check if phone (number) is valid
		
		if(phone.length()!=10 || !phone.matches("^[0-9]*$"))
			return false;
		
		return true;
	}


}
