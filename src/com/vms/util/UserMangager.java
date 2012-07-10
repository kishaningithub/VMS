package com.vms.util;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public abstract class UserMangager {
	protected SessionFactory sessionFactory=VmsSessionFactory.getSessionFactory();
	
	// Cannot login after forgot password is clicked
	public abstract Boolean Authenticate(String emailId,String password);
	
	public Boolean signUp(UserData userData)
	{
	   Session session=sessionFactory.openSession();
	   session.beginTransaction();
	   session.save(userData);
	   session.getTransaction().commit();
	   session.close();
	   return true;
	}
}
