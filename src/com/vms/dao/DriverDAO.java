package com.vms.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.vms.dto.DriverDetailsDTO;
import com.vms.util.VmsSessionFactory;

public class DriverDAO
{
	SessionFactory sessionFactory=VmsSessionFactory.getSessionFactory();
	public void addDriver(DriverDetailsDTO driverDetails)
	{
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
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Query query=session.createQuery("from DriverDetailsDTO where recordStatus='A'");
		return query.list();
	}

}
