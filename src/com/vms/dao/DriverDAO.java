package com.vms.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.vms.dto.DriverDetails;
import com.vms.util.VmsSessionFactory;

public class DriverDAO
{
	SessionFactory sessionFactory=VmsSessionFactory.getSessionFactory();
	public void addDriver(DriverDetails driverDetails)
	{
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		DriverDetails oldDriver=(DriverDetails)session.get(DriverDetails.class,driverDetails.getLicenceNo());
		if(oldDriver!=null)
			oldDriver.changeTo(driverDetails);
		else
			oldDriver=driverDetails;
		session.save(oldDriver);
		session.getTransaction().commit();
		session.close();
	}
	
	public List<DriverDetails> getListOfDrivers()
	{
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Query query=session.createQuery("from DriverDetails where recordStatus='A'");
		return query.list();
	}

}
