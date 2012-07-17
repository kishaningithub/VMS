package com.vms.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vms.dao.VehiclesDAO;
import com.vms.dto.VehicleDetailsDTO;

/**
 * Servlet implementation class addVehicle
 */
public class addVehicle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addVehicle() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VehicleDetailsDTO vehicleDetails = new VehicleDetailsDTO();
		DateFormat dateFormater=new SimpleDateFormat("dd/MMM/yyyy");
		try {
			vehicleDetails.setVehicleNo(request.getParameter("vehicleNo").trim());
			vehicleDetails.setVehicleDesc(request.getParameter("vehicleDesc").trim());
			vehicleDetails.setOwnerName(request.getParameter("ownerName").trim());
			vehicleDetails.setInsuranceNo(request.getParameter("insuranceNo").trim());
			vehicleDetails.setInsuranceStartDate(dateFormater.parse(request.getParameter("insuranceStartDate").trim()));
			vehicleDetails.setInsuranceEndDate(dateFormater.parse(request.getParameter("insuranceEndDate").trim()));
			vehicleDetails.setRcNo(request.getParameter("rcNo").trim());
			vehicleDetails.setFcDate(dateFormater.parse(request.getParameter("fcDate").trim()));
			vehicleDetails.setRecordStatus(request.getParameter("recordStatus").trim());
			new VehiclesDAO().addVehicle(vehicleDetails);
		} catch (ParseException e) {
			response.setStatus(500);
			response.getWriter().print("Please enter proper dates");
		}
		catch (NullPointerException e) {
			response.setStatus(403);
			response.getWriter().print("Hacker Get Lost!");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
