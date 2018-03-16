package com.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Driver;
import com.login.application.LoginPage;
import com.login.controller.Controller;
import com.login.controller.User;
import com.login.sql.SQLConnect;
import com.login.model.CredCheck;

public class LoginDAOImpl {
	
	private static Controller refCon = new Controller();
	private static Connection ping = SQLConnect.getConnection();

	public LoginDAOImpl() {
		
		//SQL Server connection initialization
		ping = SQLConnect.getConnection();
	}

	//SQL query for new user registration
	public static void register() {
		
		try {
			//Insert data into server table
			String query = "Insert into User (name, nric, DoB, mobile, email, password, status, attempts, firstLogin, dlock) values (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = ping.prepareStatement(query);	//Query preparation
			preparedStatement.setString(1, User.getName());	//Input name of user
			preparedStatement.setString(2, User.getNRIC());	//Input NRIC of user
			preparedStatement.setString(3, User.getDoB());	//Input date of birth of user
			preparedStatement.setString(4, User.getMobile());	//Insert mobile of user
			preparedStatement.setString(5, User.getEmail());	//Insert Email/LoginID of user
			preparedStatement.setString(6, User.getPass());	//Input password of LoginID
			preparedStatement.setString(7, "User");		//Input "User" status for user
			preparedStatement.setInt(8, 0);		//Set number of failed login attempts to 0
			preparedStatement.setString(9, "Y");	//Set 'Y' for first time user
			preparedStatement.setString(10, "N");	//Set 'N' for user not being locked
			preparedStatement.executeUpdate();	//Execute update to SQL
			preparedStatement.close();		//Close SQL
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//Registration upload to SQL successful
		System.out.println("User created! Returning to home page!");
		LoginPage.front();	//Return to front page

	}

	//User login authentication check
	public static void userLogin(String refLogin, String refPassword) {
		
		try {
			//Generate user list based on email/loginID input
			String query = "Select * from User where email = ?";
			PreparedStatement preparedStatement = ping.prepareStatement(query);
			preparedStatement.setString(1, refLogin);
			ResultSet hold = preparedStatement.executeQuery();
			
			//Hold SQL query information using getter setter
			if(hold.next()) {
				String holdpass = hold.getString("password");
				int refAttempts = hold.getInt("attempts");
				String refStatus = hold.getString("status");
				String refFirstLogin = hold.getString("firstLogin");
				String refName = hold.getString("name");
				User.setAttempts(refAttempts);
				User.setStatus(refStatus);
				User.setFirstLogin(refFirstLogin);
				User.setName(refName);
				
				//Check to see if User is Admin
				if(refStatus.equals("Admin") && holdpass.equals(User.getPass())) {
					LoginPage.admin();	//Goes to admin page
					
					//Check to see if normal User has more than 3 failed login attempts and if password is correct
				}	else if(refStatus.equals("User") && holdpass.equals(User.getPass()) && refAttempts < 3) {
					
					//Check to see if User is a first time user
					if(User.getFirstLogin().equals("Y")) {
						String query1 = "Update User set firstLogin = ? where email='"+User.getEmail()+"'";
						PreparedStatement preparedStatement1 = ping.prepareStatement(query1);
						preparedStatement1.setString(1, "N");	//Change First time login count to 'N'
						preparedStatement1.executeUpdate();
						preparedStatement1.close();
						LoginPage.firstLogin();		//Proceed to first time login page
					
					}	else {	//Not a first time login user
						LoginPage.loginTwo();	//Proceed to normal login
					}
					
					//Check for wrong password or more than 3 failed login attempts
				} else if(!holdpass.equals(User.getPass()) && refAttempts < 3) {
					System.out.println("Invalid password! Please try again!");
					refAttempts++;	//Failed login count + 1
					String query2 = "Update User set attempts = ? where email = '"+User.getEmail()+"'";
					PreparedStatement preparedStatement2 = ping.prepareStatement(query2);
					preparedStatement2.setInt(1, refAttempts);	//Update failed login attempts into server
					preparedStatement2.executeUpdate();
					LoginPage.front();		//Return to front page
					
				} else {	//Lock user if more than 3 failed login attempts
					String query3 = "Update User set dlock = ? where email = '"+User.getEmail()+"'";
					PreparedStatement preparedStatement3 = ping.prepareStatement(query3);
					preparedStatement3.setString(1, "Y");	//Update user to locked
					preparedStatement3.executeUpdate();
					System.out.println("User does not exist or has been locked out of the system!");
					System.out.println("Please contact an Administrator if you believe you have been locked out.");
					LoginPage.front();		//Return to front page
				}
			}
			else {	//User cannot be found in database
				System.out.println("User does not exist or has been locked out of the system!");
				System.out.println("Please contact an Administrator if you believe you have been locked out.");
				LoginPage.front();	//Return to front page
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Admin view user list option logic
	public static void viewUser() {
		
		//Variable to hold serial column from SQL
		int serial;
		
		try {
			String query = "Select * from User";
			PreparedStatement preparedStatement = ping.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {		//Hold information from SQL using setter getter
			serial = rs.getInt("serial");	//Hold Serial Number
			User.setName(rs.getString("name"));
			User.setEmail(rs.getString("email"));
			User.setAttempts(rs.getInt("attempts"));
			User.setLock(rs.getString("dlock"));
			
			//generate out user information
			System.out.println(serial);
			System.out.println("Name of user: "+User.getName());
			System.out.println("User Email: "+User.getEmail());
			System.out.println("User failed login attempts: "+User.getAttempts());
			System.out.println("Locked Status: "+User.getLock());
			System.out.println();
			
			}
			//Close query
			rs.close();
			preparedStatement.close();
			System.out.println();
			
			
		}	catch(SQLException e) {
			e.printStackTrace();
		}
		
		LoginPage.admin();	//Return to admin page
		
	}
	
	//Admin unlock user logic
	public static void unlockUser(String refID) {
		
		//One time email/loginID holder
		String ComID = refID;
		
		try {
			
			String query = "Select * from User where email = '"+ComID+"'";
			PreparedStatement preparedStatement = ping.prepareStatement(query);
//			preparedStatement.setString(1, ComID);
			ResultSet hold = preparedStatement.executeQuery();
			if(hold.next()) {	//Hold information from SQL using getter setter
			String refEmail = hold.getString("email");
			String refLock = hold.getString("dlock");
			User.setLock(refLock);
			User.setEmail(refEmail);
			
				if(ComID.equals(User.getEmail())) {		//If one time ID holder value found in SQL database
				
					if(User.getLock().equals("Y")) { //User is found locked, and unlocked by admin
						
					String query2 = "Update User set attempts = ? and dlock =? where email = '"+User.getEmail()+"'";
					PreparedStatement preparedStatement1 = ping.prepareStatement(query2);
					preparedStatement1.setInt(1, 0);
					preparedStatement1.setString(2, "N");	//Update locked status to 'N'
					preparedStatement1.executeUpdate();
					System.out.println("You have unlocked " +User.getEmail() +" sucessfully.");
					LoginPage.admin();	//Return to admin page
					
					}					
			
				}else {		//User does not exist in database
					System.out.println("User does not exist!");
					System.out.println();
					LoginPage.admin();
				}
				
			}
		}	catch(SQLException e) {
			e.printStackTrace();
		}
	}

	//Security question, answer and password update to SQL
	public static void secChoiceDAO() {
		
		try {	//Updating password and security question/answer to SQL
		String query = "Update User set SecQ = ? ,SecA = ? , password = ? where email ='"+User.getEmail()+"'";
		PreparedStatement preparedStatement = ping.prepareStatement(query);
		preparedStatement.setInt(1, User.getSecOpt());
		preparedStatement.setString(2, User.getSecA());
		preparedStatement.setString(3, User.getPass2());
		preparedStatement.executeUpdate();
		preparedStatement.close();
		
		System.out.println("Security question and answer has been set!");
		System.out.println("Returning to front page!");
		LoginPage.front();	//Returning to front page
		
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Forget password security question/answer update to SQL
	public static void SecForgetPW() {
		
		try {	
			String query = "Select * from User where email = '"+User.getEmail()+"'";
			PreparedStatement preparedStatement = ping.prepareStatement(query);
			ResultSet hold = preparedStatement.executeQuery();
			
			if(hold.next()) {	//Hold information from SQL using getter setter
				String holdans = hold.getString("SecA");
				User.setSecA(holdans);	//Hold security answer from SQL
				
				//Security answer comparison
				if(User.getSecA2().equals(User.getSecA())){	//Comparing user input security answer and database security answer
					System.out.println("Security answer correct! Please reset your password and security question/answer!");
					System.out.println("Please input your new password!");
					System.out.println("PS: If you remember your old password now, you must change it!");
					CredCheck.ComPass();	//Input and update new password, security question and answer
					
				} else {	//Incorrect security answer
					System.out.println("Incorrect security answer! Please contact an administrator for help!");
					LoginPage.front();	//Return to front page
				}
				
			}
			
		}	catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}


