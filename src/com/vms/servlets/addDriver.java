package com.vms.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

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
	
	   DriverDetails driverDetails = new DriverDetails();
	   driverDetails.setLicenceNo(request.getParameter("licenceNo"));
	   driverDetails.setDriverName(request.getParameter("driverName"));
       driverDetails.setDateofJoining(new Date(request.getParameter("doj")));
       driverDetails.setContAddress(request.getParameter("contAddress"));
       driverDetails.setPhoneNo(request.getParameter("phoneNo"));
	   new DriverDAO().addDriver(driverDetails);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
