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


import com.vms.dao.VehiclesDAO;
import com.vms.dto.VehicleListDTO;
import com.vms.dto.VehicleDetailsDTO;


/**
 * Servlet implementation class Detail
 */
public class VehicleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VehicleServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			JAXBContext context=JAXBContext.newInstance(VehicleListDTO.class);
			Marshaller marshaller=context.createMarshaller();
			VehicleListDTO vehicleList=new VehicleListDTO();
			List<VehicleDetailsDTO> vehicles=vehicleList.getVehicle();
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
