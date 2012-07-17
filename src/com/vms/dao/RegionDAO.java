package com.vms.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.vms.dto.RegionDTO;
import com.vms.util.Logger;
import com.vms.util.VmsSessionFactory;

public class RegionDAO
{
	SessionFactory sessionFactory=VmsSessionFactory.getSessionFactory();
	public void addRegion(RegionDTO regionDetails)
	{
		Logger.log("Inside addRegion DAO");
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		RegionDTO oldRegion=(RegionDTO)session.get(RegionDTO.class,regionDetails.getRegionCode());
		if(oldRegion!=null)
			oldRegion.changeTo(regionDetails);
		else
			oldRegion=regionDetails;
		session.save(oldRegion);
		session.getTransaction().commit();
		session.close();
	}
	
	public List<RegionDTO> getListOfRegions()
	{
		Logger.log("Inside getListOfRegions DAO");
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Query query=session.createQuery("from RegionDTO where recordStatus='A'");
		List<RegionDTO> list = query.list();
		session.close();
		return list;
	}
}
