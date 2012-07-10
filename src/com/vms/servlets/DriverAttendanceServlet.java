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
import com.vms.dto.DriverAttendanceConsolidateDTO;
import com.vms.dto.DriverAttendanceConsolidateListDTO;


/**
 * Servlet implementation class DriverAttendanceServlet
 */
public class DriverAttendanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DriverAttendanceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			JAXBContext context=JAXBContext.newInstance(DriverAttendanceConsolidateListDTO.class);
			Marshaller marshaller=context.createMarshaller();
			DriverAttendanceConsolidateListDTO driverAttConsList=new DriverAttendanceConsolidateListDTO();
			List<DriverAttendanceConsolidateDTO> driverAttList=driverAttConsList.getDriverAttendanceList();
			driverAttList.addAll(new DriverAttendanceDAO().getListOfDriverAttendances(request.getParameter("attMonth"),request.getParameter("attYear")));
			marshaller.marshal(driverAttConsList, response.getWriter());
		
		}
		catch (NumberFormatException e) {
			response.sendError(500, "Please enter proper dates");
		}
		catch (JAXBException e) {
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
