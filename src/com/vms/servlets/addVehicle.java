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
			vehicleDetails.setVehicleNo(request.getParameter("vehicleNo"));
			vehicleDetails.setVehicleDesc(request.getParameter("vehicleDesc"));
			vehicleDetails.setOwnerName(request.getParameter("ownerName"));
			vehicleDetails.setInsuranceNo(request.getParameter("insuranceNo"));
			vehicleDetails.setInsuranceStartDate(dateFormater.parse(request.getParameter("insuranceStartDate")));
			vehicleDetails.setInsuranceEndDate(dateFormater.parse(request.getParameter("insuranceEndDate")));
			vehicleDetails.setRcNo(request.getParameter("rcNo"));
			vehicleDetails.setFcDate(dateFormater.parse(request.getParameter("fcDate")));
			vehicleDetails.setRecordStatus(request.getParameter("recordStatus"));
			new VehiclesDAO().addVehicle(vehicleDetails);
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
