package com.optimum.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.optimum.dao.UserDAO;
import com.optimum.dao.UserDAOImpl;
import com.optimum.pojo.User;

@SuppressWarnings("serial")
@WebServlet("/StatusController")
public class StatusController extends HttpServlet {
       
	private UserDAO refUserDAO;
	private User refUser;
		
	public StatusController() {
		refUserDAO = new UserDAOImpl();
		refUser = new User();
	}	
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		
		refUser.setTempmail(request.getParameter("email"));
		refUser.setChoice(request.getParameter("choice"));
		
		if(refUserDAO.DLocker(refUser).equals("Locked")) {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('User has been locked!');");
			out.println("location='UpdateLock.jsp';");
			out.println("</script>");
			
		} else if(refUserDAO.DLocker(refUser).equals("Unlocked")) {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('User has been unlocked!');");
			out.println("location='UpdateLock.jsp';");
			out.println("</script>");
			
		} else {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('User does not exist!');");
			out.println("location='UpdateLock.jsp';");
			out.println("</script>");
		}
		
	}
}

