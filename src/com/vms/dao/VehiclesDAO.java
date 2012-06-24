package com.vms.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.vms.dto.Vehicle;
import com.vms.util.VmsSessionFactory;

public class VehiclesDAO
{
	SessionFactory sessionFactory=VmsSessionFactory.getSessionFactory();
	public void addVehicle(Vehicle vehicleDetails)
	{
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		System.out.println("veh det"+vehicleDetails.getVehicleNo());
		Vehicle oldVehicle=(Vehicle)session.get(Vehicle.class,vehicleDetails.getVehicleNo());
		if(oldVehicle!=null)
			oldVehicle.changeTo(vehicleDetails);
		else
			oldVehicle=vehicleDetails;
		session.save(oldVehicle);
		session.getTransaction().commit();
		session.close();
	}
	public List<Vehicle> getListOfVehicles()
	{
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Query query=session.createQuery("from Vehicle where recordStatus='A'");
		return query.list();
		
	}
}

