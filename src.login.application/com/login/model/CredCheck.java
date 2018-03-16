package com.login.model;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.login.application.LoginPage;
import com.login.controller.User;

public class CredCheck {
	
	static Scanner sc = new Scanner(System.in);
	
	//Static variable list
	private static String name;
	private static String nric;
	private static String DoB;
	private static String email;
	private static String mobile;
	private static String password = "";
	private static Pattern pattern;
	private static Matcher matcher;

	//Name check logic
	public static void nameCheck() {
		
		String name = sc.nextLine();	//Input name
		String regex = "(([a-zA-Z]+\\s)*[a-zA-Z]*)";	//Possible patterns for name
				
		Pattern pattern = Pattern.compile(regex);	// Pattern compile
		boolean same = pattern.matcher(name).matches();		//Compare pattern 
		
		if(same==true) {	//Pattern requirements met
			User.setName(name);		//Name set
		}
		else {	//Pattern requirements failed
			System.out.println("Invalid name! Please try again!");
			nameCheck();	//Repeat name input and check
		}
	}

	//NRIC logic check
	public static void nricCheck() {
		boolean nricCheck = false;
		while (!nricCheck) {
			try {
				nric = sc.next();	//Input NRIC
				for (int i = 0; i <= 9; i++) {	//Maximum length of NRIC
					if (i == 0 || i == 8) {		//Maximum number in NRIC
						if (Character.isLetter(nric.charAt(i))) {
							nricCheck = true;	//Ensure number follows format in real NRIC card
						}

						else {
							nricCheck = false;	//Real NRIC card format not met
							System.out.println("Invalid NRIC! Please try again!");
							break;
						}
					}

					if (i > 0 && i < 8) {	//Check for 8 numbers in NRIC input
						if (Character.isDigit(nric.charAt(i))) {
							nricCheck = true;
						}

						else {
							nricCheck = false;	//Does not meet 8 numbers requirement
							System.out.println("Invalid NRIC! Please try again!");
							break;
						}
					}
				}

				if (nricCheck == true) {	//All conditions met
					User.setNRIC(nric);		//NRIC set
				}
				
				//Too long or too short NRIC input
			} catch (StringIndexOutOfBoundsException e) {	
				System.out.println("Invalid NRIC! Please try again!");
				nricCheck = false;
			}
		}
	}

	//Email check logic
	public static void emailCheck() {
		boolean emailCheck = false;
		while (!emailCheck) {
			email = sc.next();	//Email input
			String emailRegex = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";	//Email possible pattern
			pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);	//Email pattern compile
			matcher = pattern.matcher(email);	//Email pattern check
			emailCheck = matcher.matches();
			if (emailCheck == true) {	//Email condition met
				User.setEmail(email);	//Email set
			} else {	//Email condition not met
				System.out.println("Invalid Email! Please try again!");
			}

		}
	}
	
	//Mobile logic check
	public static void mobileCheck() {
		boolean mobileCheck = false;
		while (!mobileCheck) {
			mobile = sc.next();		//Input mobile
			if (mobile.matches("[0-9]+") && mobile.length() == 8) {		//Length of mobile numbers only in numbers
				User.setMobile(mobile);		//Mobile number set
				mobileCheck = true;
			} else {	//Mobile conditions not met
				System.out.println("Invalid Mobile! Please try again!");
			}
		}
	}

	//Generate password logic
	public static void genPass() {
		
		//Generate first 4 digits of password from NRIC
		for (int x = 1; x < 5; x++) {	
			password += Character.toString(nric.charAt(x));
		}

		//Generate last 4 digits of password from mobile
		for (int x = 4; x < 8; x++) {
			password += Character.toString(mobile.charAt(x));
		}
		
		System.out.println("Your new password is: " + password);
		User.setPass(password);		//Password set
		System.out.println();
	}

	//Compare password logic
	public static void ComPass() {
		
		//String holder for password comparison
		String one, two;
		boolean ComPass = false;
		System.out.println("Please enter a new password!");
		while (!ComPass) {
			one = sc.next();	//Input new password

			while (one.equals(User.getPass())) {	//New password same as old password
				System.out.println("New password cannot be the same as the old password!");
				System.out.println("Now enter a new password: ");
				one = sc.next();	//Input new password again due to same password
			}
			System.out.println("Re-enter the new password: ");
			two = sc.next();	//Input new password again for confirmation
			if (two.equals(one)) {
				ComPass = true;
				User.setPass2(two);		//Password set based on String two
				LoginPage.securityQuestion();	//Enter security question selection page
			} else {	//Incorrect password for String Two
				System.out.println("Password doesn't match! Try again.");
			}
		}
		
	}

	//Clear registration form logic
	public static void clearInput() {
		name = "";
		nric = "";
		DoB = "";
		email = "";
		mobile = "";
		password = "";
	}

	//Check date of birth logic
	public static void checkDOB() {
		boolean checkDOB = false;
		while(!checkDOB) {
			DoB = sc.next();	//Enter date of birth
			
			String regex = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";	//DoB possible pattern
			pattern = Pattern.compile(regex);	//Pattern compile
			matcher = pattern.matcher(DoB);		//Pattern check
			
			if(matcher.matches()) {		//Pattern check successful
				matcher.reset();
				if(matcher.find()) {	//Check for the non-existent Day 31 in April, June, September and November
					String dd =matcher.group(1);
					String mm = matcher.group(2);
					int yy = Integer.parseInt(matcher.group(3));
					if(dd.equals("31") && mm.equals("04") || mm.equals("06") || mm.equals("09") || mm.equals("11")) {
						checkDOB = false;
					}
					else if(mm.equals("02")) {	//Check for leap year
						if(yy%4==0) {
							if (dd.equals("30") || dd.equals("31")){
								checkDOB= false;
							} 
							else {
								checkDOB=true;
							} 
						}
							else {		//Check for non-existent day in leap year
								if (dd.equals("29") || dd.equals("30") || dd.equals("31")) {
									checkDOB=false;
								}	
								else {
									checkDOB=true;
								}
							}
						} 
						else {
							checkDOB=true;
						}
					}
				else {
					checkDOB=false;
				}
			}
			else {
				checkDOB=false;
			}
			if(checkDOB==true) {	//DoB conditions met
				User.setDoB(DoB);	//DoB set
			}
			
			else {	//DoB Conditions not met
				System.out.println("Invalid DOB! Please try again!");
			}
			
			}
		}
		
		
	}


