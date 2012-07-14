package com.vms.dto;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.vms.util.UserData;
@Entity
public class VmsUserData extends UserData
{
	String role;
	@ManyToOne
	RegionDTO workLocation;
	public VmsUserData() {}
	public String getRole()
	{
		return role;
	}
	public void setRole(String role)
	{
		this.role = role;
	}
}
