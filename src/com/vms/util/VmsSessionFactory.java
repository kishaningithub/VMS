package com.vms.util;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class VmsSessionFactory
{
  private static SessionFactory sessionFactory;
  private VmsSessionFactory(){}
  public static SessionFactory getSessionFactory()
  {
	  if(sessionFactory==null)
			  sessionFactory=new Configuration().configure().buildSessionFactory();
	  return sessionFactory;
  }
  
}
