package com.vms.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.vms.dao.DriverDAO;
import com.vms.dto.DriverDetailsDTO;
import com.vms.dto.RegionDTO;
import com.vms.util.VmsSessionFactory;

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

		SessionFactory sessionFactory=VmsSessionFactory.getSessionFactory();
		Session session=sessionFactory.openSession();
		try {
			DriverDetailsDTO driverDetails = new DriverDetailsDTO();
			DateFormat dateFormater=new SimpleDateFormat("dd/MMM/yyyy");
			session.beginTransaction();
			driverDetails.setLicenceNo(request.getParameter("licenceNo").trim());
			driverDetails.setDriverName(request.getParameter("driverName").trim());
			driverDetails.setDateofJoining(dateFormater.parse(request.getParameter("dateofJoining").trim()));
			driverDetails.setContAddress(request.getParameter("contAddress").trim());
			driverDetails.setWorkLocation((RegionDTO)session.get(RegionDTO.class, Integer.parseInt(request.getParameter("workLocation").trim())));
			driverDetails.setPhoneNo(request.getParameter("phoneNo").trim());
			driverDetails.setRecordStatus(request.getParameter("recordStatus").trim());
			new DriverDAO().addDriver(driverDetails);
		} catch (ParseException e) {
			response.setStatus(500);
			response.getWriter().print("Please enter proper dates");
		}
		catch (NullPointerException e) {
			response.setStatus(403);
			response.getWriter().print("Hacker Get Lost!");
		}
		finally{
			session.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

