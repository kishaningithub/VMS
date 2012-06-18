package com.vms.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
@Entity @XmlRootElement
public class DriverDetails
{
	@Id
	String licenceNo;
	String driverName; 
	Date dateofJoining;
	String contAddress;
	String phoneNo;
	
	public String getLicenceNo()
	{
		return licenceNo;
	}
	public void setLicenceNo(String licenceNo)
	{
		this.licenceNo = licenceNo;
	}
	public String getDriverName()
	{
		return driverName;
	}
	public void setDriverName(String driverName)
	{
		this.driverName = driverName;
	}
	public Date getDateofJoining()
	{
		return dateofJoining;
	}
	public void setDateofJoining(Date dateofJoining)
	{
		this.dateofJoining = dateofJoining;
	}
	public String getContAddress()
	{
		return contAddress;
	}
	public void setContAddress(String contAddress)
	{
		this.contAddress = contAddress;
	}
	public String getPhoneNo()
	{
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo)
	{
		this.phoneNo = phoneNo;
	}
	

}
