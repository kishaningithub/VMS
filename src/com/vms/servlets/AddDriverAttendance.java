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
import org.hibernate.exception.ConstraintViolationException;

import com.vms.dao.DriverAttendanceDAO;
import com.vms.dto.DriverAtt;
import com.vms.dto.DriverAttendanceDTO;
import com.vms.dto.DriverDetailsDTO;
import com.vms.dto.VehicleDetailsDTO;
import com.vms.util.VmsSessionFactory;

/**
 * Servlet implementation class AddDriverAttendance
 */
public class AddDriverAttendance extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddDriverAttendance() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DriverAttendanceDTO driverAttendanceDetails = new DriverAttendanceDTO();
		DateFormat dateFormater=new SimpleDateFormat("dd/MMM/yyyy");
		try {
			
			DriverAtt driverAtt=new DriverAtt();
			SessionFactory sessionFactory=VmsSessionFactory.getSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			driverAtt.setDateOfAtt((dateFormater.parse(request.getParameter("dateOfAtt"))));
			driverAtt.setLicenceNo((DriverDetailsDTO)session.get(DriverDetailsDTO.class,request.getParameter("licenceNo")));
			driverAttendanceDetails.setVehicleNo((VehicleDetailsDTO)session.get(VehicleDetailsDTO.class,request.getParameter("vehicleNo")));
			if(driverAtt.getLicenceNo()==null)
				throw new IllegalArgumentException("The given License No. does not exist");
			else if(driverAttendanceDetails.getVehicleNo()==null)
				throw new IllegalArgumentException("The given vehicle No. does not exist");
			driverAttendanceDetails.setAttId(driverAtt);
			driverAttendanceDetails.setPresent(new Boolean(request.getParameter("present")));
			driverAttendanceDetails.setOverTime(Integer.parseInt(request.getParameter("overTime")));
			driverAttendanceDetails.setRecordStatus(request.getParameter("recordStatus"));
			new DriverAttendanceDAO().addDriverAttendance(driverAttendanceDetails);
			session.close();
		}
		catch (NumberFormatException e) {
			response.setStatus(500);
			response.getWriter().print("Please enter proper value in overtime");
		}
		catch (IllegalArgumentException e) {
			response.setStatus(500);
			response.getWriter().print(e.getMessage());
		}
		catch (ParseException e) {
			response.setStatus(500);
			response.getWriter().print("Please enter proper dates");
		}
		catch (ConstraintViolationException e) {
			response.setStatus(500);
			response.getWriter().print("Cannot put attendance for a person more than once");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
