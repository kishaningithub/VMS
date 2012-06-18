package com.vms.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class DriverAttendance
{
	@Id @GeneratedValue
	Integer attId;
	@OneToOne
	DriverDetails licenceNo;
	@OneToOne
	Vehicle vechicleNo; 
	Date attendance;  //(OnDuty OffDuty)
	Integer overTime; //(No. of hrs) 
	
	public DriverDetails getLicenceNo()
	{
		return licenceNo;
	}
	public void setLicenceNo(DriverDetails licenceNo)
	{
		this.licenceNo = licenceNo;
	}
	public Vehicle getVechicleNo()
	{
		return vechicleNo;
	}
	public void setVechicleNo(Vehicle vechicleNo)
	{
		this.vechicleNo = vechicleNo;
	}
	public Date getAttendance()
	{
		return attendance;
	}
	public void setAttendance(Date attendance)
	{
		this.attendance = attendance;
	}
	public Integer getOverTime()
	{
		return overTime;
	}
	public void setOverTime(Integer overTime)
	{
		this.overTime = overTime;
	}
	
}
