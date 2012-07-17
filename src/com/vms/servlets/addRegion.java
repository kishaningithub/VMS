package com.vms.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vms.dao.RegionDAO;
import com.vms.dto.RegionDTO;

/**
 * Servlet implementation class addRegion
 */
public class addRegion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addRegion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			RegionDTO regionDetails=new RegionDTO();
			String regionCode=request.getParameter("regionCode").trim();
			if(!regionCode.equalsIgnoreCase(""))
			 regionDetails.setRegionCode(Integer.parseInt(regionCode));
			else
			  regionDetails.setRegionCode(-1); // Auto generated region code starts from 1
			regionDetails.setRegionName(request.getParameter("regionName").trim());
			regionDetails.setRecordStatus(request.getParameter("recordStatus").trim());
			new RegionDAO().addRegion(regionDetails);
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
