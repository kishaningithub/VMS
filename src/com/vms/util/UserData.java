package com.vms.util;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserData {
	@Id
 	public String emailId;
	public String password;
	public Boolean forgotPassword;
	public UserData(){}
	public UserData(String emailId,String password,Boolean forgotPassword)
	{
		this.setEmailId(emailId);
		this.setPassword(password);
		this.setForgotPassword(forgotPassword);
	}
	public UserData(String emailId,String password)
	{
		this.setEmailId(emailId);
		this.setPassword(password);
		this.setForgotPassword(false);
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = new PasswordHash().encrypt(password);
	}
	public Boolean isForgotPassword() {
		return forgotPassword;
	}
	public void setForgotPassword(Boolean forgotPassword) {
		this.forgotPassword = forgotPassword;
	}
	@Override
	public boolean equals(Object newUserData) {
		Boolean emailIdEquality=emailId.equals(((UserData)newUserData).getEmailId());
		Boolean passwordEquality=password.equals(((UserData)newUserData).getPassword());
		return  emailIdEquality&&passwordEquality;
	}
}
