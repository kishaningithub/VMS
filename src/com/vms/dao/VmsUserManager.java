package com.vms.dao;

import org.hibernate.Session;

import com.vms.dto.VmsUserData;
import com.vms.util.PasswordHash;
import com.vms.util.UserData;
import com.vms.util.UserMangager;

public class VmsUserManager extends UserMangager
{
	public VmsUserData user;
	@Override
	public Boolean Authenticate(String emailId,String password)
	{
		VmsUserData vmsUserData=new VmsUserData();
		vmsUserData.setEmailId(emailId);
		vmsUserData.setPassword(password);
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		VmsUserData retrievedUserData=(VmsUserData)session.get(VmsUserData.class,vmsUserData.getEmailId());
		if(vmsUserData.equals(retrievedUserData)){
			user=retrievedUserData;
			return true;
		}
		session.close();
		return false;
	}
	public Boolean signUp(VmsUserData vmsUserData)
	{
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.save(vmsUserData);
		session.getTransaction().commit();
		session.close();
		return true;
	}
	public static void main(String[] args) 
	{
		
//		new VmsUserManager().signUp(new VmsUserData("admin",new PasswordHash().encrypt("Admin123"),"A"));
//		new VmsUserManager().signUp(new VmsUserData("mantrichi",new PasswordHash().encrypt("mant444"),"M"));
//		new VmsUserManager().signUp(new VmsUserData("manchennai",new PasswordHash().encrypt("mant555"),"M"));
	}
}
