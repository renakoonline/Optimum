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
@WebServlet("/UserController")
public class UserController extends HttpServlet {

	private UserDAO refUserDAO;
	private User refUser;
		
	public UserController() {
		refUserDAO = new UserDAOImpl();
		refUser = new User();
	}	
	
protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	PrintWriter out = response.getWriter();
	RequestDispatcher ref;
	
	//Get data from html page
	String Email = request.getParameter("username");
	String Password = request.getParameter("password");
	
	//Set data to POJO
	refUser.setEmail(Email);
	refUser.setPassword(UserDAOImpl.hashing(Password));
	
	
	if(refUserDAO.Login(refUser).equals("Admin")) {
		HttpSession session = request.getSession();
		Date lastAccessTime = new Date(session.getLastAccessedTime());	//Get last access time
		session.setAttribute("lastAccessTime", lastAccessTime);
		String encode = response.encodeRedirectURL("Admin.jsp");
		response.sendRedirect(encode);
		
	} else if(refUserDAO.Login(refUser).equals("User")) {
		HttpSession session = request.getSession();
		Date lastAccessTime = new Date(session.getLastAccessedTime());
		session.setAttribute("lastAccessTime", lastAccessTime);
		String encode = response.encodeRedirectURL("User.jsp");
		response.sendRedirect(encode);
		
	} else if(refUserDAO.Login(refUser).equals("WrongPW")) {
		request.setAttribute("Error", "<font color=red>Wrong Password! Please try again!</font>");
		getServletContext().getRequestDispatcher("/Login.jsp").forward(request,response);
	
	} else if(refUserDAO.Login(refUser).equals("Invalid")) {
		request.setAttribute("Error", "<font color=red>User does not exist!</font>");
		getServletContext().getRequestDispatcher("/Login.jsp").forward(request,response);
	}
}
	
	
}
