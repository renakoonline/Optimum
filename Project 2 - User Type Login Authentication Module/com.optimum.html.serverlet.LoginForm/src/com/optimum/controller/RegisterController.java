 package com.optimum.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.optimum.dao.UserDAO;
import com.optimum.dao.UserDAOImpl;
import com.optimum.pojo.User;

@WebServlet("/RegisterController")
@MultipartConfig(maxFileSize = 16177215)
public class RegisterController extends HttpServlet {

	private User refUser;
	private UserDAOImpl refUserDAO;
	private RequestDispatcher refRequestDispatcher;
	
	static String fname,lname, gender, email, nric, dob, address, country, qualification, department, mobile, empID;
	static String password ="";
	Part file;
	private static Pattern pattern;
	private static Matcher matcher;
	
	static HttpServletRequest request;
	static ServletResponse response;
	
	public RegisterController() {
		refUser = new User();
		refUserDAO = new UserDAOImpl();
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		RegisterController.request = request;
		RegisterController.response = response;
		InputStream inputStream;
		
		fname = request.getParameter("fname");
		lname = request.getParameter("lname");
		gender = request.getParameter("gender");
		email = request.getParameter("email");
		nric = request.getParameter("nric");
		dob = request.getParameter("dob");
		address = request.getParameter("address");
		country = request.getParameter("country");
		qualification = request.getParameter("qualification");
		department = request.getParameter("department");
		mobile = request.getParameter("mobile");
		empID = request.getParameter("empid");
		file = request.getPart("file");
		inputStream = file.getInputStream();
		
		refUser.setFname(fname);
		refUser.setLname(lname);
		refUser.setGender(gender);
		refUser.setDob(dob);
		refUser.setAddress(address);
		refUser.setCountry(country);
		refUser.setQualification(qualification);
		refUser.setDepartment(department);
		
		boolean emailCheck = false;		//Email regex check
		while (!emailCheck) {
			String emailRegex = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";	//Email possible pattern
			pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);	//Email pattern compile
			matcher = pattern.matcher(email);	//Email pattern check
			emailCheck = matcher.matches();
			if (emailCheck == true) {	//Email condition met
				refUser.setEmail(email);	//Email set
			} else {	//Email condition not met
				request.setAttribute("invalidEmail", "<font color=red>Invalid Email type!</font>");
				getServletContext().getRequestDispatcher("/Registration.jsp").forward(request,response);
			}
		}	                                                                                               
		
		boolean mobileCheck = false;		//Mobile regex check
		while (!mobileCheck) {
			if (mobile.matches("[0-9]+") && mobile.length() == 8) {		//Length of mobile numbers only in numbers
				User.setMobile(mobile);		//Mobile number set
				mobileCheck = true;
				} else {	//Mobile conditions not met
					request.setAttribute("invalidMobile", "<font color=red>Invalid mobile number!</font>");
					getServletContext().getRequestDispatcher("/Registration.jsp").forward(request,response);
				}
			}
		
		boolean empIDCheck = false;		//Employer ID regex check
		while (!empIDCheck) {
			if (empID.matches("[0-9]+") && empID.length() == 5) {		//Length of mobile numbers only in numbers
				refUser.setEmpid(empID);		//Mobile number set
				empIDCheck = true;
				} else {	//Mobile conditions not met
					request.setAttribute("invalidID", "<font color=red>Invalid User ID!</font>");
					getServletContext().getRequestDispatcher("/Registration.jsp").forward(request,response);
				}
			}
		
		boolean nricCheck = false;		//NRIC regex check
		while (!nricCheck) {
			try {
				for (int i = 0; i <= 9; i++) {	//Maximum length of NRIC
					if (i == 0 || i == 8) {		//Maximum number in NRIC
						if (Character.isLetter(nric.charAt(i))) {
							nricCheck = true;	//Ensure number follows format in real NRIC card
						}

						else {
							nricCheck = false;	//Real NRIC card format not met
							request.setAttribute("invalidNRIC", "<font color=red>Invalid NRIC!</font>");
							getServletContext().getRequestDispatcher("/Registration.jsp").forward(request,response);
							break;
						}
					}

					if (i > 0 && i < 8) {	//Check for 8 numbers in NRIC input
						if (Character.isDigit(nric.charAt(i))) {
							nricCheck = true;
						}

						else {
							nricCheck = false;	//Does not meet 8 numbers requirement
							request.setAttribute("invalidNRIC", "<font color=red>Invalid NRIC!</font>");
							getServletContext().getRequestDispatcher("/Registration.jsp").forward(request,response);
							break;
						}
					}
				}

				if (nricCheck == true) {	//All conditions met
					refUser.setNric(nric);		//NRIC set
				}
				
				//Too long or too short NRIC input
			} catch (StringIndexOutOfBoundsException e) {	
				request.setAttribute("invalidNRIC", "<font color=red>Invalid NRIC!</font>");
				getServletContext().getRequestDispatcher("/Registration.jsp").forward(request,response);
				nricCheck = false;
			}
		}
		
		for (int x = 1; x < 5; x++) {	
			password += Character.toString(empID.charAt(x));
		}
		for (int x = 4; x < 8; x++) {
			password += Character.toString(mobile.charAt(x));
		}

		refUser.setPassword(UserDAOImpl.hashing(password));
		refUserDAO.register(refUser, inputStream);
		RegEmail.main(refUser);
		out.println("<font color=red>The new password for this user is "+password+", please remind the user to change their password upon login.</font>");
			
		//User created, close iframe
	}
		
		
		
	
	}
	

