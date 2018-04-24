package com.login.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Driver;
import com.login.application.LoginPage;
import com.login.application.RegEmail;
import com.login.controller.User;
import com.login.sql.SQLConnect;
import java.util.Scanner;

import com.login.application.LoginPage;
import com.login.dao.LoginDAO;
import com.login.dao.LoginDAOImpl;
import com.login.model.CredCheck;

public class Controller {

	private LoginDAO refLoginDAO;
	private LoginDAOImpl refLoginDAOImpl;

	Scanner sc = new Scanner(System.in);

	//Front Page option selection
	public void option(int option) {

		switch (option) {

		case 1:	//Enter Login Page
			LoginPage.login();	
			break;
			
		case 2:	//Enter Registration Page
			LoginPage.register();
			break;
			
		case 3:	//Enter Forget Password page
			LoginPage.forgetPW();
			break;

		case 4:	//Close Application
			System.out.println("We hope to see you again!");
			break;

		default:	//Invalid selection, return to Front Page
			System.out.println("Invalid option! Please choose from 1 to 4.");
			System.out.println("");
			LoginPage.front();
			break;
		}

	}
	
	//Registration Page option selection
	public void RegOp(String held) {
			//Data confirmation = Correct
		if (held.equals("1") || held.equals("Yes") || held.equals("yes")) {
			CredCheck.genPass();	//Generate password
			RegEmail.main();	//Sent email
			LoginDAOImpl.register();	//Log account in SQL Server
			System.out.println("Returning to front page");
			System.out.println();
			LoginPage.front();	//Return to front page
		}
			//Data confirmation = Incorrect
		if (held.equals("2") || held.equals("No") || held.equals("no")) {
			CredCheck.clearInput();	//Registration form clear
			LoginPage.register();	//Return back to registration
		}
			//Quit registering
		if (held.equals("3") || held.equals("Quit") || held.equals("quit")) {
			System.out.println("Returning to front page");
			System.out.println("");
			LoginPage.front();	//Return to Front Page
		}

	}

	//Security question choice selection
	public void SecChoice(int num) {
		
		//Security question selection setter
		User.setSecOpt(num);
		
		switch (num) {

		case 1:	//Option 1
			User.setSecQ("1) What is your favourite country?");
			System.out.println();
			System.out.println("Please provide an answer: ");
			User.setSecA(sc.next());	//Input answer for security question 1
			LoginDAOImpl.secChoiceDAO();	//Pass question and answer to DAOImpl
			break;	

		case 2:	//Option 2
			User.setSecQ("2) What is your mother's first name?");
			System.out.println();
			System.out.println("Please provide an answer: ");
			User.setSecA(sc.next());	//Input answer for security question 2
			LoginDAOImpl.secChoiceDAO();	//Pass question and answer to DAOImpl
			break;	

		case 3:	//Option 3
			User.setSecQ("3) Are security questions dumb? Don't answer yes or no!");
			System.out.println();
			System.out.println("Please provide an answer: ");
			User.setSecA(sc.next());	//Input answer for security question 3
			LoginDAOImpl.secChoiceDAO();	//Pass question and answer to DAOImpl
			break;

		default:	//Invalid selection
			System.out.println("Please choose from 1 to 3!");
			System.out.println("");
			LoginPage.securityQuestion();	//Return to security question selection
			break;
		}
	}
	
	//Admin Login page selection
	public void adminLogin(int i) {
		
		switch (i) {
		
		case 1: 	//View User list
			LoginDAOImpl.viewUser();	//Implement view user list
			break;
		
		case 2:	//Register a user
			LoginPage.register();	//Enter registration form
			break;
		
		case 3:	//Unlock user who has been locked
			System.out.println("Input name of user to unlock: ");
			LoginDAOImpl.unlockUser(sc.next());	//Implement unlock user
			break;
			
		case 4:	//Log out	
			LoginPage.front();	//Return to front page
			break;

		default:	//Invalid option. Scold admin as well.
			System.out.println("Invalid option! Admins don't mess around!");
			LoginPage.admin();	//Return to admin page
			break;
		}
	}
	
	//Sucessful Login
	public void LoginOff(int j) {
		switch (j) {
		
		case 1:
			LoginPage.front();
			break;
			
		case 2:
			System.out.println("We hope to see you again!");
			break;
		
		default:
			System.out.println("Invalid option! Try again!");
			LoginPage.loginTwo();
			break;
		
		}
		
	}



}
