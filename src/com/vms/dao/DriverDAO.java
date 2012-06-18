package com.vms.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vms.dto.DriverDetails;
import com.vms.util.VmsSessionFactory;

public class DriverDAO
{
  public static SessionFactory sessionFactory=VmsSessionFactory.getSessionFactory();
  public void addDriver(DriverDetails driverDetails)
  {
	  Session session=sessionFactory.openSession();
	  session.beginTransaction();
	  session.save(driverDetails);
	  session.getTransaction().commit();
	  session.close();
	 	  
  }

}
