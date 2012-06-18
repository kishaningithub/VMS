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
import javax.xml.bind.annotation.XmlRootElement;

import com.vms.dao.VehiclesDAO;
import com.vms.dto.VehicleList;
import com.vms.dto.Vehicle;
import com.vms.dto.VehicleList;

/**
 * Servlet implementation class Detail
 */
public class Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Detail() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			JAXBContext context=JAXBContext.newInstance(VehicleList.class);
			Marshaller marshaller=context.createMarshaller();
			VehicleList vehicleList=new VehicleList();
			List<Vehicle> vehicles=vehicleList.getVehicle();
			vehicles.addAll(new VehiclesDAO().getListOfVehicles());
			marshaller.marshal(vehicleList, response.getWriter());
		} catch (JAXBException e) {
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
