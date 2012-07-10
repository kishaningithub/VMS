package com.vms.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class DriverAtt implements Serializable
{
	Date dateOfAtt;  
	@ManyToOne
	DriverDetailsDTO licenceNo;
	public Date getDateOfAtt()
	{
		return dateOfAtt;
	}
	public void setDateOfAtt(Date dateOfAtt)
	{
		this.dateOfAtt = dateOfAtt;
	}
	public DriverDetailsDTO getLicenceNo()
	{
		return licenceNo;
	}
	public void setLicenceNo(DriverDetailsDTO licenceNo)
	{
		this.licenceNo = licenceNo;
	}
 }