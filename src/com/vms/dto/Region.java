package com.vms.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Region
{
    @Id @GeneratedValue
	Integer regionCode;
	String regionName;
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
	

}
