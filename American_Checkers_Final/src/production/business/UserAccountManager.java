package production.business;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class UserAccountManager {
	
	public static final String NOINPUTERROR ="";
	
    private ArrayList<UserAccount> userAccounts;
    
	static Connection connection=null;
	static PreparedStatement ps=null;
	
	static String database="american_checkers";
	static String url="jdbc:mysql://localhost:3306/"+database;

	static String user="root";
	static String passwd="root";
	String query;
    
    public UserAccountManager() {
    	//db connections
        userAccounts = new ArrayList<UserAccount>();
        
       // Class.forName("com.mysql.jdbc.Driver");
			
        try {
			connection=DriverManager.getConnection(url,user,passwd);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
    // return an empty string if user registration is successful (all inputs are valid)
    // otherwise an error message.
    public String registerNewUser(String userName, String password, String reenteredPassword, 
    		String firstName, String lastName, String email, String phone){
    		
    	
    	String inputCheckResult = UserAccount.checkInputError(userName, password, firstName, lastName, email, phone);
    		
    		if (!inputCheckResult.equals(""))
    			return inputCheckResult;
    		if 	(doesUserNameExist(userName))
    			return "User name is not available!";
    		if (!password.equals(reenteredPassword)) {
    			return "Re-entered password does not match!";
    		}
    		
    		//below 3 lines are optional code
    		UserAccount newAccount = new UserAccount();
    		setAccountProfile(newAccount, userName, password, firstName, lastName, email, phone);
    		
    		//userAccounts.add(newAccount);
    		//no need of setAccountProfile
    	    //sql query to insert user into table
    		
    		query="INSERT INTO `american_checkers`.`registration`(`username`,`passwordd`,`reenterpasswd`,`firstname`,`lastname`,`email`,`phone`)"
    				+ "VALUES(?,?,?,?,?,?,?);";   		
    		try {
    			connection=DriverManager.getConnection(url,user,passwd);
				ps=connection.prepareStatement(query);
				
				ps.setString(1, userName);
				ps.setString(2, password);
				ps.setString(3, reenteredPassword);
				ps.setString(4, firstName);
				ps.setString(5, lastName);
				ps.setString(6, email);
				ps.setString(7, phone);
				
				ps.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
    		return NOINPUTERROR;
    }

    private void setAccountProfile(UserAccount userAccount, String userName, String password, String firstName, String lastName, String email, String phone){
		userAccount.setUserName(userName);
		userAccount.setPassword(password);
		userAccount.setFirstName(firstName);
		userAccount.setLastName(lastName);
		userAccount.setEmail(email);
		userAccount.setPhoneNumber(phone);
    }
    
    public int login(String userName, String password) {
    	try {
    		connection=DriverManager.getConnection(url,user,passwd);
    		Statement stm=connection.createStatement();
        	query="select * from registration where username='"+userName+"' and passwordd='"+password+"';";
        	
			ResultSet rs=stm.executeQuery(query);
			if(rs.next())
			{
				return 1;
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return 0;
    }
    
    public boolean doesUserNameExist(String userName){
    	//sql query to check whether user exist or not
    		for (UserAccount userAccount: userAccounts)
    			if(userAccount.matchUserName(userName))   
    				return true;   
    		return false;
    }
    
}
