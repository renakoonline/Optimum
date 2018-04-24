package com.login.application;

import java.util.Scanner;

import com.login.controller.Controller;
import com.login.controller.User;
import com.login.dao.LoginDAOImpl;
import com.login.model.CredCheck;

public class LoginPage {

	static Controller refCon = new Controller();
	static Scanner sc = new Scanner(System.in);
	static String inputEmail;
	static String inputPW;
	static String secQ;
	static String secA;

	//Front Page
	public static void front() {
		System.out.println("Please select your options.");
		System.out.println("1) Login");
		System.out.println("2) Register");
		System.out.println("3) Forget password");
		System.out.println("4) Close application");
		refCon.option(sc.nextInt());	//Option selection
	}
	
	//Login Page
	public static void login() {
		System.out.println("Please input your login ID and password.");
		System.out.println("Login ID: ");
		inputEmail = sc.next();
		User.setEmail(inputEmail);	//Email setter
		
		System.out.println("");
		System.out.println("Password: ");
		inputPW = sc.next();
		User.setPass(inputPW);	//Password setter
		
		System.out.println("");
		LoginDAOImpl.userLogin(inputEmail,inputPW);	//Passing data to DAOImpl
	}

	//Admin Page
	public static void admin() {
		System.out.println("Welcome, administrator.");
		System.out.println("1) View list of users");
		System.out.println("2) Register new user");
		System.out.println("3) Unlock user");
		System.out.println("4) Log out");
		refCon.adminLogin(sc.nextInt());	//Admin option selection
	}
	
	//Registration
	public static void register() {
		System.out.println("Hi, thank you for signing up with us. Please fill up the following details.");
		System.out.println("");
		System.out.println("Please enter your name: ");
		CredCheck.nameCheck();	//Name checker
		System.out.println("Please enter your NRIC: ");
		CredCheck.nricCheck();	//NRIC checker
		System.out.println("Please enter your DOB (DD/MM/YYYY): ");
		CredCheck.checkDOB();	//Date of Birth checker
		System.out.println("Please enter your Email: ");
		CredCheck.emailCheck();	//Email checker
		System.out.println("Please enter your Mobile: ");
		CredCheck.mobileCheck();	//Mobile checker

		//Information confirmation
		System.out.println("");
		System.out.println("Please confirm the following data:");
		System.out.println("Name: " + User.getName());
		System.out.println("NRIC: " + User.getNRIC());
		System.out.println("DOB: " + User.getDoB());
		System.out.println("Email: " + User.getEmail());
		System.out.println("Mobile: " + User.getMobile());
		System.out.println("");
		System.out.println("Are the data correct?");
		System.out.println("1) Yes");
		System.out.println("2) No");
		System.out.println("3) Quit");
		refCon.RegOp(sc.next());	//Registration confirmation selection

	}
	
	//Security question selection
	public static void securityQuestion() {
		System.out.println("Please choose one of the following security question.");
		System.out.println("1) What is your favourite country?");
		System.out.println("2) What is your mother's first name?");
		System.out.println("3) Are security questions dumb? Don't answer yes or no!");
		int i = sc.nextInt();
		refCon.SecChoice(i);	//Security choice selection

	}

	//Initial Login
	public static void firstLogin() {
		System.out.println("Welcome " + User.getName());
		System.out.println("As part of your first login, please change your password and set your security question!.");
		System.out.println("");
		CredCheck.ComPass();	//Initiate password check
		
	}
	
	//Forget password
	public static void forgetPW() {
		System.out.println("Please enter your email: ");
		User.setEmail(sc.next());
		System.out.println();
		System.out.println("Please enter your security answer: ");
		User.setSecA2(sc.next());
		System.out.println();
		LoginDAOImpl.SecForgetPW();	//Initiate  email and answer check
		
	}
	
	//Normal login
	public static void loginTwo() {
		System.out.println("You have successfully login to the system!");
		System.out.println("Would you like to return to the front page?");
		System.out.println("1) Yes");
		System.out.println("2) No, please close the application");
		refCon.LoginOff(sc.nextInt());	//Normal login option selection
		
	}
	
	//Admin - Unlocking user
	public static void unlockUser() {
		System.out.println("Which user would you like to unlock?");
		User.setEmail(sc.next());	//Input user email to unlock
	}

}
