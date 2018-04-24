package com.optimum.controller;

import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.optimum.dao.UserDAOImpl;
import com.optimum.pojo.User;
import com.optimum.dao.UserDAO;

@WebServlet("/PicController")
@MultipartConfig(maxFileSize = 16177215)
public class PicController extends HttpServlet {

	private User refUser;
	private UserDAO refUserDAO;
	
	public PicController() {
		refUserDAO = new UserDAOImpl();
	}

	protected void service (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		OutputStream pic;
		
		byte[] barray = refUserDAO.getPic(email);
		response.setContentType("image/png");
		pic = response.getOutputStream();
		pic.write(barray);
		pic.flush();
		pic.close();
	}

}
