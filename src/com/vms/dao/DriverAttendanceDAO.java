package com.vms.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.vms.dto.DriverAttendanceConsolidateDTO;
import com.vms.dto.DriverAttendanceDTO;
import com.vms.util.VmsSessionFactory;

public class DriverAttendanceDAO
{
	SessionFactory sessionFactory=VmsSessionFactory.getSessionFactory();
	public void addDriverAttendance(DriverAttendanceDTO driverAttendanceDTO)
	{
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.save(driverAttendanceDTO);
		session.getTransaction().commit();
		session.close();
	}
	
	public List<DriverAttendanceConsolidateDTO> getListOfDriverAttendances(String attMon, String attYear)
	{
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Query query=session.createQuery(" select new com.vms.dto.DriverAttendanceConsolidateDTO(driver.licenceNo,driver.driverName,count(att.present))" +
				                        " from DriverAttendanceDTO att,DriverDetailsDTO driver" +
				                        " where att.licenceNo=driver.licenceNo" +
				                        " and att.recordStatus='A'" +
				                        " and driver.recordStatus='A'" +
				                        " and att.present=true" +
			                            " and month(att.dateOfAtt) = :attMon" +
				                        " and year(att.dateOfAtt) = :attYear" +
				                        " group by driver.licenceNo,driver.driverName");
		query.setInteger("attMon", Integer.parseInt(attMon));
		query.setInteger("attYear", Integer.parseInt(attYear));
		return query.list();
		
	}
}
