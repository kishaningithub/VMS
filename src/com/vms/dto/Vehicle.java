package com.vms.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity @XmlRootElement
public class Vehicle 
{
	@Id
	String vehicleNo;
	String vehicleDesc;
	String ownerName;
	String insuranceNo;
	Date insuranceStartDate;
	Date insuranceEndDate;
	String rcNo;
	Date fcDate;
	String recordStatus;
	public String getVehicleNo()
	{
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo)
	{
		this.vehicleNo = vehicleNo;
	}
	public String getVehicleDesc()
	{
		return vehicleDesc;
	}
	public void setVehicleDesc(String vehicleDesc)
	{
		this.vehicleDesc = vehicleDesc;
	}
	public String getOwnerName()
	{
		return ownerName;
	}
	public void setOwnerName(String ownerNmae)
	{
		this.ownerName = ownerNmae;
	}
	public String getInsuranceNo()
	{
		return insuranceNo;
	}
	public void setInsuranceNo(String insuranceNo)
	{
		this.insuranceNo = insuranceNo;
	}
	public Date getInsuranceStartDate()
	{
		return insuranceStartDate;
	}
	public void setInsuranceStartDate(Date insuranceStartDate)
	{
		this.insuranceStartDate = insuranceStartDate;
	}
	public Date getInsuranceEndDate()
	{
		return insuranceEndDate;
	}
	public void setInsuranceEndDate(Date insuranceEndDate)
	{
		this.insuranceEndDate = insuranceEndDate;
	}
	public String getRcNo()
	{
		return rcNo;
	}
	public void setRcNo(String rcNo)
	{
		this.rcNo = rcNo;
	}
	public Date getFcDate()
	{
		return fcDate;
	}
	public void setFcDate(Date fcDate)
	{
		this.fcDate = fcDate;
	}
	
	public String getRecordStatus()
	{
		return recordStatus;
	}
	public void setRecordStatus(String recordStatus)
	{
		this.recordStatus = recordStatus;
	}
	public void changeTo(Vehicle vehicle)
	{
		this.setVehicleDesc(vehicle.getVehicleDesc());
		this.setRcNo(vehicle.getRcNo());
		this.setOwnerName(vehicle.getOwnerName());
		this.setInsuranceNo(vehicle.getInsuranceNo());
		this.setInsuranceStartDate(vehicle.getInsuranceStartDate());
		this.setInsuranceEndDate(vehicle.getInsuranceEndDate());
		this.setFcDate(vehicle.getFcDate());
		this.setRecordStatus(vehicle.getRecordStatus());
	}
	
	
}
