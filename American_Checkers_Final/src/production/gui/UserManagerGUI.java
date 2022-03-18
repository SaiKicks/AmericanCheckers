package production.gui;

import javax.swing.*;
import production.business.UserAccountManager;
import java.awt.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.sql.*;

public class UserManagerGUI extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public static final String SuccessfulLogin = "Successful Login";
	public static final String FailedLogin = "Failed Login";
	
	private JLabel usernameLabel, passwordLabel;
	private JTextField usernameTextField = new JTextField();
	private JPasswordField passwordTextField = new JPasswordField();
	private JButton logInButton = new JButton("Login");
	private JButton cancelButton = new JButton("Cancel");
	private JButton registerNewUserButton = new JButton("New User? Register Here");


	private UserAccountManager accountManager;
	
	//Added Code for Database Connection
	
	static Connection connection;
	static PreparedStatement ps;

	static String database="american_checkers";
	static String url="jdbc:mysql://localhost:3306/"+database;
	
	static String user="root";
	static String passwd="root";

	static int logincount=0;
	
	public UserManagerGUI(String title) {
		super(title);
		initUserAccounts();
		initGUI();
	    getContentPane().setLayout(new BorderLayout());
	    getContentPane().add(createLoginPanel(), BorderLayout.CENTER,0);
	    getContentPane().add(createSecondPanel(), BorderLayout.SOUTH,0);
	    pack();
	}
	  
	private void initUserAccounts() {
		accountManager = new UserAccountManager();
	}
	
	private void initGUI(){
		setName("loginWidnow");
	    setBounds(300,300,300,300);
		Font labelFont = new java.awt.Font("Dialog", 0, 13);
		usernameLabel = createJLabel("User name:", labelFont);
	    passwordLabel = createJLabel("Password:", labelFont);
	    setUpLoginButton();
	    setUpCancelButton();
	    usernameTextField.setText("");
		usernameTextField.setName("LoginNameTextField");
	    passwordTextField.setText("");
		passwordTextField.setName("PasswordTextField");
	  }

	private JLabel createJLabel(String text, Font font){
		JLabel label = new JLabel();
		label.setFont(font);
		label.setText(text);
		return label;
	}
	
	private void setUpLoginButton(){
	    logInButton.setFont(new java.awt.Font("Dialog", 0, 12));
		logInButton.setName("LoginButton");
	    logInButton.addActionListener(new java.awt.event.ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        loginButtonHandler(e);	        
	      }
	    });
	} 
	
	private void setUpCancelButton(){
	    cancelButton.setFont(new java.awt.Font("Dialog", 0, 12));
		cancelButton.setName("CancelButton");
	    cancelButton.addActionListener(new java.awt.event.ActionListener(){
	      public void actionPerformed(ActionEvent e) {
	  	    System.exit(0);
	      }
	    });
	}

	private void setUpRegisterNewUserButton(){
		registerNewUserButton.setName("RegisterNewUserButtonButton");
	    registerNewUserButton.addActionListener(new java.awt.event.ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	    	  	registerNewUserButtonButtonHandler(e);
	      }
	    });
	} 


	private JPanel createLoginPanel(){
	    JPanel panel = new JPanel();
	    panel.setLayout(new GridBagLayout());
	    panel.add(usernameLabel,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
	            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(52, 49, 0, 0), 0, 0));
	    panel.add(usernameTextField,    new GridBagConstraints(1, 0, 2, 1, 1.0, 0.0
	            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(52, 7, 0, 73), 214, 0));
	    panel.add(passwordTextField,       new GridBagConstraints(1, 1, 2, 1, 1.0, 0.0
	            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(17, 7, 0, 73), 214, 0));
	    panel.add(passwordLabel,   new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
	            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(19, 49, 0, 7), 0, 0));
	    panel.add(logInButton,   new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0
	            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(35, 88, 62, 0), 12, 0));
	    panel.add(cancelButton,   new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0
	            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(38, 91, 62, 73), 13, 0));
	    return panel;
	}
	
	private JPanel createSecondPanel(){
	    JPanel panel = new JPanel();
	    panel.setLayout(new GridLayout(1,3));
	    setUpRegisterNewUserButton();
	    panel.add(registerNewUserButton);
	    return panel;
	}
	
	void loginButtonHandler(ActionEvent e) {
		String userName = usernameTextField.getText();
		char[] password = passwordTextField.getPassword();
		
		int currentAccount = accountManager.login(userName, new String(password));
	    if(currentAccount==1) {
	    	logincount++;
    		JOptionPane.showConfirmDialog(this,"Login Succeeded", SuccessfulLogin, JOptionPane.PLAIN_MESSAGE);
    		if(logincount==2)
    		{
    			SwingUtilities.invokeLater(new Runnable() {
    	            public void run() {
    	                new AmericanCheckersGUI();
    	            }
    	        });
    		}
	    }
	    else
	    		JOptionPane.showConfirmDialog(this,"Login Failed", FailedLogin, JOptionPane.PLAIN_MESSAGE);
	}

	private void registerNewUserButtonButtonHandler(ActionEvent e) {
		JDialog editProfileWindow = new EditProfileWindow(this, null, accountManager);
		editProfileWindow.setLocationRelativeTo(this);
		editProfileWindow.setVisible(true);
	}
	

	public static void main(String args[]){
		JFrame window = new UserManagerGUI("Welcome");
		window.setVisible(true);
	}

}

