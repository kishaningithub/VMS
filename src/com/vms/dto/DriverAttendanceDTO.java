package com.vms.dto;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DriverAttendanceDTO
{
	@EmbeddedId
	DriverAtt attId;
	@ManyToOne
	VehicleDetailsDTO vehicleNo; 
	Boolean present; //P or A - Present or absent
	Integer overTime; //(No. of hrs) 
	String recordStatus;
	
	public DriverAtt getAttId()
	{
		return attId;
	}
	public void setAttId(DriverAtt attId)
	{
		this.attId = attId;
	}
	public VehicleDetailsDTO getVehicleNo()
	{
		return vehicleNo;
	}
	public void setVehicleNo(VehicleDetailsDTO vehicleNo)
	{
		this.vehicleNo = vehicleNo;
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
