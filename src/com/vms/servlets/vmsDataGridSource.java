package com.vms.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.vms.dao.DriverAttendanceDAO;
import com.vms.dao.DriverDAO;
import com.vms.dao.VehiclesDAO;
import com.vms.dto.VehicleDetailsDTO;
import com.vms.dto.VmsListXmlHolder;
import com.vms.util.vmsJaxbContext;

/**
 * Servlet implementation class vmsDataGridSource
 */
public class vmsDataGridSource extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public vmsDataGridSource() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String showGrid=request.getParameter("showGrid").trim();
			JAXBContext context=vmsJaxbContext.getVmsJaxbContext();
			VmsListXmlHolder vmsList=new VmsListXmlHolder();
			if(showGrid.equalsIgnoreCase("vehicles"))
				vmsList.getVmsDTO().addAll(new VehiclesDAO().getListOfVehicles());
			else if(showGrid.equalsIgnoreCase("drivers"))
				vmsList.getVmsDTO().addAll(new DriverDAO().getListOfDrivers());
			else if(showGrid.equalsIgnoreCase("driverattendance"))
				vmsList.getVmsDTO().addAll(new DriverAttendanceDAO().getListOfDriverAttendances(request.getParameter("attMonth"),request.getParameter("attYear")));
			context.createMarshaller().marshal(vmsList, response.getWriter());
		} 
		catch (JAXBException e) {
			e.printStackTrace();
		}
		catch (NullPointerException e) {
			response.getWriter().print("Hacker Get Lost!!");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
