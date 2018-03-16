package com.login.controller;

import java.sql.SQLException;

public class User {

	//Static variable list
	private static String username;
	private static String password, password2;
	private static String nric;
	private static String DoB;
	private static String email;
	private static String mobile;
	private static String SecQ;
	private static String SecA, SecA2;
	private static String status;
	private static int attempts;
	private static String dlock;
	private static String firstLogin;
	private static int SecOpt;
	
	
	//Name setter
	public static void setName(String setname) {
		username = setname;
	}
	
	//Name getter
	public static String getName() {
		return username;
	}
	
	//Password setter
	public static void setPass(String setpass) {
		password = setpass;
	}
	
	//Password getter
	public static String getPass() {
		return password;
	}
	
	//Password2 setter
	public static void setPass2(String setPass2) {
		password2 = setPass2;
	}
	
	//Password2 getter
	public static String getPass2() {
		return password2;
	}
	
	//NRIC setter
	public static void setNRIC(String setnric) {
		nric = setnric;
	}
	
	//NRIC getter
	public static String getNRIC() {
		return nric;
	}
	
	//Date of Birth setter
	public static void setDoB(String setDoB) {
		DoB = setDoB;
	}
	
	//Date of Birth getter
	public static String getDoB() {
		return DoB;
	}
	
	//Email setter
	public static void setEmail(String setmail) {
		email = setmail;
	}
	
	//Email getter
	public static String getEmail() {
		return email;
	}
	
	//Mobile setter
	public static void setMobile(String setmobile) {
		mobile = setmobile;
	}
	
	//Mobile getter
	public static String getMobile() {
		return mobile;
	}

	//Security question setter
	public static void setSecQ(String setsec) {
		SecQ = setsec;
	}
	
	//Security question getter
	public static String getSecQ() {
		return SecQ;
	}
	
	//Security answer setter
	public static void setSecA(String secAns) {
		SecA = secAns;
	}
	
	//Security answer getter
	public static String getSecA() {
		return SecA;
	}
	
	//User login attempts setter
	public static void setAttempts(int setAttempts) {
		attempts = setAttempts;
	}
	
	//User login attempts getter
	public static int getAttempts() {
		return attempts;
	}
	
	//User locked status setter
	public static void setLock(String setLock) {
		dlock = setLock;
	}
	
	//User locked status getter
	public static String getLock() {
		return dlock;
	}
	
	//User type setter
	public static void setStatus(String setStatus) {
		status = setStatus;
	}
	
	//User type getter
	public static String getStatus() {
		return status;
	}
	
	//First login check setter
	public static void setFirstLogin(String setFirstLogin) {
		firstLogin = setFirstLogin;
	}
	
	//First login check getter
	public static String getFirstLogin() {
		return firstLogin;
	}

	//Security question option setter
	public static void setSecOpt(int setSecOpt) {
		SecOpt = setSecOpt;
	}
	
	//Security question option getter
	public static int getSecOpt() {
		return SecOpt;
	}
	
	//Security answer hold getter
	public static void setSecA2(String setSecA2) {
		SecA2 = setSecA2;
	}
	
	//Security answer hold setter
	public static String getSecA2() {
		return SecA2;
	}
}
