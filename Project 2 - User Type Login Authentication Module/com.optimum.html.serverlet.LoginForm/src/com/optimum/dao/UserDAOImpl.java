																																																																																																																																																																																																																																																																																																																																																																																																																																																	package com.optimum.dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import java.sql.Statement;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.optimum.controller.UserController;
import com.optimum.pojo.User;
import com.optimum.sql.SQLConnect;

public class UserDAOImpl implements UserDAO {

	private static UserController refUserCon = new UserController();
	private static Connection ping = SQLConnect.getConnection();
	private boolean loginStatus;
	static Logger log4j = Logger.getLogger(UserDAOImpl.class);
	
	public UserDAOImpl () {
		
		ping = SQLConnect.getConnection();
		
	}
	
	@Override
	public String register(User refUser, InputStream inputStream) {
		
		try {
			String query ="Insert into JEE (FName, LName, Gender, Email, Password, NRIC, DOB, Address, Country, Qualification, Department, Mobile, EmpID, File, Status, dlock, attempts) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
			PreparedStatement preparedStatement = ping.prepareStatement(query);
			preparedStatement.setString(1, User.getFname());
			preparedStatement.setString(2, User.getLname());
			preparedStatement.setString(3, User.getGender());
			preparedStatement.setString(4, User.getEmail());
			preparedStatement.setString(5, User.getPassword());
			preparedStatement.setString(6, User.getNric());
			preparedStatement.setString(7, User.getDob());
			preparedStatement.setString(8, User.getAddress());
			preparedStatement.setString(9, User.getCountry());
			preparedStatement.setString(10, User.getQualification());
			preparedStatement.setString(11, User.getDepartment());
			preparedStatement.setString(12, User.getMobile());
			preparedStatement.setString(13, User.getEmpid());
			preparedStatement.setBlob(14, inputStream);
			preparedStatement.setString(15, "User");
			preparedStatement.setString(16, "No");
			preparedStatement.setInt(17, 0);
			preparedStatement.executeUpdate();
			log4j.info(refUser.getEmail() +" has been registered.");
			return "regok";
				
		} catch (MySQLIntegrityConstraintViolationException e) {
			log4j.warn(refUser.getEmail() +" has already been registered.");
			
		} catch(SQLException e) {
			e.printStackTrace();
			log4j.error("System Error: Register");
		}
		return "regok";
		
	}
	
	@Override
	public String Login(User refUser) {
		
		try {
			
			String query = "Select * from JEE where email = '"+refUser.getEmail()+"'";
			PreparedStatement preparedStatement = ping.prepareStatement(query);
			ResultSet hold = preparedStatement.executeQuery();
			
			if(hold.next()) {
				String holdpass = hold.getString("password");
				int refAttempts = hold.getInt("attempts");
				String refStatus = hold.getString("status");
				String refDlock = hold.getString("dlock");
				refUser.setFname(hold.getString("fname"));
				refUser.setLname(hold.getString("lname"));
				refUser.setEmpid(hold.getString("empid"));
				refUser.setAddress(hold.getString("address"));
				refUser.setNric(hold.getString("nric"));
				refUser.setCountry(hold.getString("country"));
				refUser.setMobile(hold.getString("mobile"));
				refUser.setQualification(hold.getString("qualification"));
				refUser.setGender(hold.getString("gender"));
				refUser.setDepartment(hold.getString("department"));
				refUser.setDob(hold.getString("dob"));
				
				if(refStatus.equals("Admin")) {
						return "Admin";		
				}
				if(refAttempts < 6 && refDlock.equals("No") && holdpass.equals(refUser.getPassword())) {
							UserDAOImpl.resetAttempts();
							log4j.info("Admin " +refUser.getEmail() +"connected.");
							return "User";
				} else if(refAttempts < 6 && refDlock.equals("No")) {
							String query1 ="Update JEE set attempts=? where email='"+refUser.getEmail()+"'";
							PreparedStatement prepareStatement1 = ping.prepareStatement(query1);
							prepareStatement1.setInt(1, refAttempts+1);
							prepareStatement1.executeUpdate();
							log4j.warn(refUser.getEmail() +" wrong password attempt.");
							return "WrongPW";
						
				} else if (refAttempts > 5 && refDlock.equals("No")) {	
					String query2 = "Update JEE set dlock=? where email='"+refUser.getEmail()+"'";
					PreparedStatement prepareStatement2 = ping.prepareStatement(query2);
					prepareStatement2.setString(1, "Yes");
					prepareStatement2.executeUpdate();
					log4j.warn(refUser.getEmail() + " maximum attempts, user locked.");
					return "Invalid";
					
				} else {
					log4j.warn("Invalid user attempt: " +refUser.getEmail());
					return "Invalid";
				}
				
			}
			log4j.error("System Error: Login");
			return "Invalid";
			
		}	catch(SQLException e) {
			e.printStackTrace();
		}
		log4j.error("System Error: Login");
		return "Error";
	}
	
	public static void resetAttempts() {
	try {
		String query = "update JEE set attempts =? where email='"+User.getEmail()+"'";
		PreparedStatement prepareStatement = ping.prepareStatement(query);
		prepareStatement.setInt(1, 0);
		prepareStatement.executeUpdate();
		log4j.info(User.getEmail() +" login attempts reset.");
		
	}catch(SQLException e) {
		e.printStackTrace();
	}
	
	}

	public static void Unlocker(User refUser) {
		
		try {
			String query = "Update JEE set dlock=?, attempts=? where email='"+refUser.getEmail()+"'";
			PreparedStatement prepareStatement = ping.prepareStatement(query);
			prepareStatement.setString(1, "No");
			prepareStatement.setInt(2, 0);
			prepareStatement.executeUpdate();
			log4j.info(refUser.getEmail() +" has been unlocked by admin.");
			
		} catch(SQLException e) {
			e.printStackTrace();
			log4j.error("System Error: Unlock User");
		}
	
	}
	
	public static void Locker(User refUser) {
		
		try {
			String query = "Update JEE set dlock=? where email='"+refUser.getEmail()+"'";
			PreparedStatement prepareStatement = ping.prepareStatement(query);
			prepareStatement.setString(1, "Yes");
			prepareStatement.executeUpdate();
			log4j.info(refUser.getEmail() +" has been locked by admin.");
			
		} catch(SQLException e) {
			e.printStackTrace();
			log4j.error("System Error: Lock User");
		}
	}
	
	public static String hashing(String password) {
		
	String passHash = password;
	String passGen = null;
	
	try {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(passHash.getBytes());
		
		byte[] bytes = md.digest();
		StringBuilder sb = new StringBuilder();
		for(int j=0; j<bytes.length; j++) {
			sb.append(Integer.toString((bytes[j] & 0xff) + 0xff, 16).substring(1));
		}
		
		passGen = sb.toString();
		
	}	catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	}
	
	return passGen;

	}

	@Override
	public String updateProfile(User refUser) {
		
		InputStream inputStream1, inputStream2;
		Part file = refUser.getFile();
		Part pic = refUser.getPic();
		
		try {
			String query = "Update JEE set fname=?, lname=?, address=?, country=?, qualification=?, password=?, file=?, pic=? where email='"+refUser.getEmail()+"'";
			
			inputStream1 = file.getInputStream();
			inputStream2 = pic.getInputStream();
			
			PreparedStatement prepareStatement = ping.prepareStatement(query);
			prepareStatement.setString(1, refUser.getFname());
			prepareStatement.setString(2, refUser.getLname());
			prepareStatement.setString(3, refUser.getAddress());
			prepareStatement.setString(4, refUser.getCountry());
			prepareStatement.setString(5, refUser.getQualification());
			prepareStatement.setString(6, refUser.getPassword());
			prepareStatement.setBlob(7, inputStream1);
			prepareStatement.setBlob(8, inputStream2);
			prepareStatement.executeUpdate();
			log4j.info(refUser.getEmail() +" updated user profile.");
			return "UpdateOk";
			
		} catch(SQLException | IOException e) {
			e.printStackTrace();
			log4j.error("System Error: Update Profile");
		}
		log4j.error("System Error: Update Profile");
		return "Error";
	}

	@Override
	public byte[] getPic(String email) {
	
		byte[] barray = null;
		System.out.println(email);
		
		try {
			Statement statement = ping.createStatement();
			ResultSet hold = statement.executeQuery("Select pic from JEE where email ='"+email+"'");
			
			while(hold.next()) {
				barray = hold.getBytes("pic");
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return barray;
	}
	
	public static String ForgotPW(User refUser) {
		
		String password = "";
		
		try {
			Statement statement = ping.createStatement();
			ResultSet hold = statement.executeQuery("Select * from JEE where email='"+refUser.getTempmail()+"'");
			
			while(hold.next()) {
				refUser.setEmail(hold.getString("Email"));
				refUser.setFname(hold.getString("Fname"));
				String empID = hold.getString("empid");
				String mobile = hold.getString("mobile");
				
				if(refUser.getTempmail().equals(refUser.getEmail())) {
					for (int x = 1; x < 5; x++) {	
						password += Character.toString(empID.charAt(x));
					}
					for (int x = 4; x < 8; x++) {
						password += Character.toString(mobile.charAt(x));
					}
					
					refUser.setTempass(password);
					refUser.setPassword(UserDAOImpl.hashing(password));
					
					String query = "Update JEE set password=? where email='"+refUser.getEmail()+"'";
					PreparedStatement prepareStatement = ping.prepareStatement(query);
					prepareStatement.setString(1, refUser.getPassword());
					prepareStatement.executeUpdate();
					
					log4j.info(refUser.getEmail() +" password has been reset.");
					return "Exist";
					
				} else {
					log4j.warn("Invalid User forgot password attempt");
					return "Invalid";
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			log4j.error("System Error: Forgot Password");
		}
		log4j.error("System Error: Forgot Password - out of bound");
		return "Invalid";
	}
	
	@Override
	public String DLocker(User refUser) {
		
		try {
			Statement statement = ping.createStatement();
			ResultSet hold = statement.executeQuery("Select * from JEE where email='"+refUser.getTempmail()+"'");
			
			while(hold.next()) {
				refUser.setEmail(hold.getString("email"));
				refUser.setStatus(hold.getString("status"));
				
				if(User.getTempmail().equals(refUser.getEmail()) && refUser.getChoice().equals("lock") && refUser.getStatus().equals("User")) {
					UserDAOImpl.Locker(refUser);
					log4j.info(refUser.getEmail() +" lock processing");
					return "Locked";
					
				} else if(User.getTempmail().equals(refUser.getEmail()) && refUser.getChoice().equals("unlock")&& refUser.getStatus().equals("User")) {
					UserDAOImpl.Unlocker(refUser);
					log4j.info(refUser.getEmail() +" unlock processing");
					return "Unlocked";
					
				} else {
					log4j.warn(refUser.getEmail() + " : Invalid Attempt, User does not exist");
					return "NoExist";
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
			log4j.error("System Error: Dlock process");
		}
		log4j.error("System Error: DLock process");
		return "Error";
		
	}
	
}
