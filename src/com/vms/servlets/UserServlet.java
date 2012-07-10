package com.vms.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import com.vms.dao.VmsUserManager;
import com.vms.dto.VmsUserData;
import com.vms.util.PasswordHash;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		String emailId=request.getParameter("emailId").trim();
		String password=request.getParameter("password").trim();
		VmsUserManager vmsUserManager=new VmsUserManager();
		if(vmsUserManager.Authenticate(emailId,password))
			request.getSession(true);
		}
		catch(NullPointerException e){
			response.setStatus(401);
			response.getWriter().print("Hacker Get Lost!");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String emailId=request.getParameter("emailId");
		String password=request.getParameter("password");
		VmsUserManager vmsUserManager=new VmsUserManager();
		if(vmsUserManager.Authenticate(emailId,new PasswordHash().encrypt(password)))
			request.getSession(true);
	}

}
