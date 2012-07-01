package com.vms.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class DriverAttendanceDTO
{
	@Id @GeneratedValue
	Integer attId;
	@OneToOne
	DriverDetailsDTO licenceNo;
	@OneToOne
	VehicleDetailsDTO vehicleNo; 
	Date dateOfAtt;  
	Boolean present; //P or A - Present or absent
	Integer overTime; //(No. of hrs) 
	String recordStatus;
	public Integer getAttId()
	{
		return attId;
	}
	public void setAttId(Integer attId)
	{
		this.attId = attId;
	}
	public DriverDetailsDTO getLicenceNo()
	{
		return licenceNo;
	}
	public void setLicenceNo(DriverDetailsDTO licenceNo)
	{
		this.licenceNo = licenceNo;
	}
	public VehicleDetailsDTO getVehicleNo()
	{
		return vehicleNo;
	}
	public void setVehicleNo(VehicleDetailsDTO vehicleNo)
	{
		this.vehicleNo = vehicleNo;
	}
	public Date getDate()
	{
		return dateOfAtt;
	}
	public void setDate(Date date)
	{
		this.dateOfAtt = date;
	}
	public Boolean getPresent()
	{
		return present;
	}
	public void setPresent(Boolean present)
	{
		this.present = present;
	}
	public Integer getOverTime()
	{
		return overTime;
	}
	public void setOverTime(Integer overTime)
	{
		this.overTime = overTime;
	}
	public String getRecordStatus()
	{
		return recordStatus;
	}
	public void setRecordStatus(String recordStatus)
	{
		this.recordStatus = recordStatus;
	}
}
