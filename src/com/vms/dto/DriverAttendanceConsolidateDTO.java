package com.vms.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DriverAttendanceConsolidateDTO
{
	String licenceNo;
	String driverName;
	Long present;
	public DriverAttendanceConsolidateDTO()
	{
		
	}
	public DriverAttendanceConsolidateDTO(String licenceNo,String driverName,Long present) 
	{
		this.licenceNo = licenceNo;
		this.driverName = driverName;
		this.present = present;
	}
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
	public Long getPresent()
	{
		return present;
	}
	public void setPresent(Long present)
	{
		this.present = present;
	}

}
