package com.vms.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.vms.dto.DriverDetailsDTO;
import com.vms.dto.RegionDTO;
import com.vms.util.Logger;
import com.vms.util.VmsSessionFactory;

public class DriverDAO
{
	SessionFactory sessionFactory=VmsSessionFactory.getSessionFactory();
	public void addDriver(DriverDetailsDTO driverDetails)
	{
		Logger.log("Inside addDriver");
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		DriverDetailsDTO oldDriver=(DriverDetailsDTO)session.get(DriverDetailsDTO.class,driverDetails.getLicenceNo());
		if(oldDriver!=null)
			oldDriver.changeTo(driverDetails);
		else
			oldDriver=driverDetails;
		session.save(oldDriver);
		session.getTransaction().commit();
		session.close();
	}
	
	public List<DriverDetailsDTO> getListOfDrivers()
	{
		Logger.log("Inside getListOfDrivers");
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Query query=session.createQuery("from DriverDetailsDTO where recordStatus='A'");
		List<DriverDetailsDTO> list = query.list();
		session.close();
		return list;
	}

}
