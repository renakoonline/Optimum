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

@WebServlet("/ForgetController")
public class ForgetController extends HttpServlet {

	private User refUser;
	private UserDAOImpl refUserDAO;
	private RequestDispatcher refRequestDispatcher;
	
	public ForgetController() {
		refUser = new User();
		refUserDAO = new UserDAOImpl();
	}
       
	private static Pattern pattern;
	private static Matcher matcher;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String email = request.getParameter("pass1");
		refUser.setTempmail(email);
		
		boolean emailCheck = false;		//Email regex check
		while (!emailCheck) {
			String emailRegex = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";	//Email possible pattern
			pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);	//Email pattern compile
			matcher = pattern.matcher(email);	//Email pattern check
			emailCheck = matcher.matches();
			if (emailCheck == true) {	//Email condition met
				UserDAOImpl.ForgotPW(refUser);
				
				if(refUserDAO.ForgotPW(refUser).equals("Exist")) {	//Email registered in system
					out.println("<script type=\"text/javascript\">");
					out.println("alert('A new password has been sent to your Email! Please change your password after logging in!');");
					out.println("location='Login.jsp';");
					out.println("</script>");
					ForgetEmail.main(refUser);
					break;
					
				} else {	//Email not registered in system
					out.println("<script type=\"text/javascript\">");
					out.println("alert('This Email is not registered with us, please contact an administrator!');");
					out.println("location='Login.jsp';");
					out.println("</script>");
					break;
				}
				
			} else {	//Email condition not met
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Please enter a correct Email format!');");
				out.println("location='Login.jsp';");
				out.println("</script>");
				break;
			}
		}	                                          
		
	}

}
