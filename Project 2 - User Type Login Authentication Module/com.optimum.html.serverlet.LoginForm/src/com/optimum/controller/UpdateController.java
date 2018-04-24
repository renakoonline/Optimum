package com.optimum.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.optimum.dao.UserDAO;
import com.optimum.dao.UserDAOImpl;
import com.optimum.pojo.User;

@WebServlet("/UpdateController")
@MultipartConfig(maxFileSize = 16177215)
public class UpdateController extends HttpServlet {
	
	private UserDAO refUserDAO;
	private User refUser;
		
	public UpdateController() {
		refUserDAO = new UserDAOImpl();
		refUser = new User();
	}	
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		refUser.setFname(request.getParameter("updateFname"));
		//System.out.println(refUser.getFname());
		refUser.setLname(request.getParameter("updateLname"));
		//System.out.println(refUser.getLname());
		refUser.setAddress(request.getParameter("updateAddress"));
		//System.out.println(refUser.getAddress());
		refUser.setCountry(request.getParameter("updateCountry"));
		//System.out.println(refUser.getCountry());
		refUser.setMobile(request.getParameter("updateMobile"));
		//System.out.println(refUser.getMobile());
		refUser.setQualification(request.getParameter("updateQualification"));
		//System.out.println(refUser.getQualification());
		//System.out.println(refUser.getPassword());
		
		if(!(request.getParameter("updatePassword").trim().isEmpty() || request.getParameter("updatePassword") == null || request.getParameter("updatePassword").equals("Enter new password"))) {
			refUser.setPassword(UserDAOImpl.hashing(request.getParameter("updatePassword")));
			//System.out.println(refUser.getPassword());
			
		} else {
			refUser.setPassword(request.getParameter("existingPassword"));
			//System.out.println(refUser.getPassword());
		}
		
		if(request.getParameter("updateQualification") !=null) {
			refUser.setQualification(request.getParameter("updateQualification"));
			
		} else {
			refUser.setQualification(request.getParameter("existingQualification"));
		}
		
		if(request.getParameter("updateCountry") !=null) {
			refUser.setCountry(request.getParameter("updateCountry"));
		
		} else {
			refUser.setCountry(request.getParameter("existingCountry"));
		}
		
		/*Part file = request.getPart("updateFile");
		if (file !=null) {
			refUser.setFile(file);
		}*/
		
		if(request.getPart("updateFile") !=null) {
			Part file = request.getPart("updateFile");
			refUser.setFile(file);
		}
		
		if(request.getPart("updatePic") !=null) {
			Part pic = request.getPart("updatePic");
			refUser.setPic(pic);
		}
		
		boolean mobileCheck = false;		//Mobile regex check
		while (!mobileCheck) {
			if (refUser.getMobile().matches("[0-9]+") && refUser.getMobile().length() == 8) {		//Length of mobile numbers only in numbers
				User.setMobile(request.getParameter("updateMobile"));	//Mobile number set
				mobileCheck = true;
				} else {	//Mobile conditions not met
					request.setAttribute("invalidMobile", "<font color=red>Invalid mobile number!</font>");
					getServletContext().getRequestDispatcher("/UpdateUser.jsp").forward(request,response);
				}
			}
		
		String check =  refUserDAO.updateProfile(refUser);
		
		if(check.equals("UpdateOk")) {
			request.setAttribute("UpdateOk", "<html><body><span>Your profile has been updated successfully!</span></body></html>");
			request.getRequestDispatcher("UpdateUser.jsp").forward(request, response);
	
		} else {
			request.setAttribute("UpdateOk", "<html><body><span>An error has occured! Please check with an administrator for more details.</span></body></html>" );
			request.getRequestDispatcher("UpdateUser.jsp").forward(request, response);
		}
		
	}
}
