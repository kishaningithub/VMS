package com.vms.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vms.dao.DriverDAO;
import com.vms.dto.DriverDetails;

/**
 * Servlet implementation class addDriver
 */
public class addDriver extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addDriver() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			DriverDetails driverDetails = new DriverDetails();
			DateFormat dateFormater=new SimpleDateFormat("dd/MMM/yyyy");
			driverDetails.setLicenceNo(request.getParameter("licenceNo"));
			driverDetails.setDriverName(request.getParameter("driverName"));
			driverDetails.setDateofJoining(dateFormater.parse(request.getParameter("dateofJoining")));
			driverDetails.setContAddress(request.getParameter("contAddress"));
			driverDetails.setPhoneNo(request.getParameter("phoneNo"));
			driverDetails.setRecordStatus(request.getParameter("recordStatus"));
			new DriverDAO().addDriver(driverDetails);
		} catch (ParseException e) {

			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

