package com.vms.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.vms.dto.DriverDetailsDTO;
import com.vms.dto.VehicleDetailsDTO;
import com.vms.util.Logger;
import com.vms.util.VmsSessionFactory;

public class VehiclesDAO
{
	SessionFactory sessionFactory=VmsSessionFactory.getSessionFactory();
	public void addVehicle(VehicleDetailsDTO vehicleDetails)
	{
		Logger.log("Inside addVehicle");
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Logger.log("veh det"+vehicleDetails.getVehicleNo());
		VehicleDetailsDTO oldVehicle=(VehicleDetailsDTO)session.get(VehicleDetailsDTO.class,vehicleDetails.getVehicleNo());
		if(oldVehicle!=null)
			oldVehicle.changeTo(vehicleDetails);
		else
			oldVehicle=vehicleDetails;
		session.save(oldVehicle);
		session.getTransaction().commit();
		session.close();
	}
	public List<VehicleDetailsDTO> getListOfVehicles()
	{
		Logger.log("Inside getListOfVehicles");
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Query query=session.createQuery("from VehicleDetailsDTO where recordStatus='A'");
		List<VehicleDetailsDTO> list = query.list();
		session.close();
		return list;
	}
}

