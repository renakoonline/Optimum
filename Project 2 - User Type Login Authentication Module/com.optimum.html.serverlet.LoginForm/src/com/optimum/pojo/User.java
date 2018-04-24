package com.optimum.pojo;

import javax.servlet.http.Part;

public class User {
		
	private static String fname;
	private static String lname;
	private static String nric;
	private static String dob;
	private static String address;
	private static String country;
	private static String qualification;
	private static String department;
	private static String email;
	private static String mobile;
	private static String empid;
	private static String gender;
	private static String password;
	private static String status;
	private static String dlock;
	private static int attempts;
	private static String tempass;
	private static Part file, pic;
	private static String tempmail;
	private static String choice;
	
	public static String getChoice() {
		return choice;
	}
	public static void setChoice(String choice) {
		User.choice = choice;
	}
	public static String getTempmail() {
		return tempmail;
	}
	public static void setTempmail(String tempmail) {
		User.tempmail = tempmail;
	}
	public static Part getFile() {
		return file;
	}
	public static void setFile(Part file) {
		User.file = file;
	}
	public static Part getPic() {
		return pic;
	}
	public static void setPic(Part pic) {
		User.pic = pic;
	}
	public static String getFname() {
		return fname;
	}
	public static void setFname(String fname) {
		User.fname = fname;
	}
	public static String getLname() {
		return lname;
	}
	public static void setLname(String lname) {
		User.lname = lname;
	}
	public static String getNric() {
		return nric;
	}
	public static void setNric(String nric) {
		User.nric = nric;
	}
	public static String getDob() {
		return dob;
	}
	public static void setDob(String dob) {
		User.dob = dob;
	}
	public static String getAddress() {
		return address;
	}
	public static void setAddress(String address) {
		User.address = address;
	}
	public static String getCountry() {
		return country;
	}
	public static void setCountry(String country) {
		User.country = country;
	}
	public static String getQualification() {
		return qualification;
	}
	public static void setQualification(String qualification) {
		User.qualification = qualification;
	}
	public static String getDepartment() {
		return department;
	}
	public static void setDepartment(String department) {
		User.department = department;
	}
	public static String getEmail() {
		return email;
	}
	public static void setEmail(String email) {
		User.email = email;
	}
	public static String getMobile() {
		return mobile;
	}
	public static void setMobile(String mobile) {
		User.mobile = mobile;
	}
	public static String getEmpid() {
		return empid;
	}
	public static void setEmpid(String empid) {
		User.empid = empid;
	}
	public static String getGender() {
		return gender;
	}
	public static void setGender(String gender) {
		User.gender = gender;
	}
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String password) {
		User.password = password;
	}
	public static String getStatus() {
		return status;
	}
	public static void setStatus(String status) {
		User.status = status;
	}
	public static String getDlock() {
		return dlock;
	}
	public static void setDlock(String dlock) {
		User.dlock = dlock;
	}
	public static int getAttempts() {
		return attempts;
	}
	public static void setAttempts(int attempts) {
		User.attempts = attempts;
	}
	public static String getTempass() {
		return tempass;
	}
	public static void setTempass(String tempass) {
		User.tempass = tempass;
	}
	
	
}
