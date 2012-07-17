package com.vms.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@Entity @XmlRootElement
public class RegionDTO
{
  	@Id @GeneratedValue
  	Integer regionCode;
	String regionName;
	String recordStatus;
	// Getters and Setters
  	public Integer getRegionCode()
	{
		return regionCode;
	}
	public void setRegionCode(Integer regionCode)
	{
		this.regionCode = regionCode;
	}
	public String getRegionName()
	{
		return regionName;
	}
	public void setRegionName(String regionName)
	{
		this.regionName = regionName;
	}
	public String getRecordStatus()
	{
		return recordStatus;
	}
	public void setRecordStatus(String recordStatus)
	{
		this.recordStatus = recordStatus;
	}
	// Other functions
	public void changeTo(RegionDTO regionDetails)
	{
		setRegionName(regionDetails.getRegionName());
		setRecordStatus(regionDetails.getRecordStatus());
	}
}
