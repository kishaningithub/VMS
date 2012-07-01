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

import com.vms.dao.DriverAttendanceDAO;
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
			driverAttendanceDetails.setPresent(new Boolean(request.getParameter("present")));
			driverAttendanceDetails.setRecordStatus(request.getParameter("recordStatus"));
			driverAttendanceDetails.setDate(dateFormater.parse(request.getParameter("dateOfAtt")));
			driverAttendanceDetails.setOverTime(Integer.parseInt(request.getParameter("overTime")));
			SessionFactory sessionFactory=VmsSessionFactory.getSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			driverAttendanceDetails.setLicenceNo((DriverDetailsDTO)session.get(DriverDetailsDTO.class,request.getParameter("licenceNo")));
			driverAttendanceDetails.setVehicleNo((VehicleDetailsDTO)session.get(VehicleDetailsDTO.class,request.getParameter("vehicleNo")));
			new DriverAttendanceDAO().addDriverAttendance(driverAttendanceDetails);
			session.close();
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
