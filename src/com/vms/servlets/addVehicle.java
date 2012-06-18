package com.vms.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vms.dao.VehiclesDAO;
import com.vms.dto.Vehicle;

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
		Vehicle vehicleDetails = new Vehicle();
		DateFormat dateFormater=new SimpleDateFormat("dd/MMM/yyyy");
		vehicleDetails.setVehicleNo(request.getParameter("vehicleNo"));
		System.out.println("Svlet veh no"+request.getParameter("vehicleNo")+" "+vehicleDetails.getVehicleNo());
		vehicleDetails.setVehicleDesc(request.getParameter("vehicleDesc"));
		vehicleDetails.setOwnerName(request.getParameter("ownerName"));
		vehicleDetails.setInsuranceNo(request.getParameter("insuranceNo"));
		try {
			vehicleDetails.setInsuranceStartDate((Date)dateFormater.parse(request.getParameter("insuranceStartDate")));
			vehicleDetails.setInsuranceEndDate((Date)dateFormater.parse(request.getParameter("insuranceEndDate")));
			vehicleDetails.setFcDate((Date)dateFormater.parse(request.getParameter("fcDate")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		vehicleDetails.setRcNo(request.getParameter("rcNo"));
		vehicleDetails.setRecordStatus(request.getParameter("recordStatus"));
		new VehiclesDAO().addVehicle(vehicleDetails);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
