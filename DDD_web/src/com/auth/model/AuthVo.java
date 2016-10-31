package com.auth.model;

public class AuthVo implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private String authId;
	private String authName;
	
	
	public String getAuthId() {
		return this.authId;
	}
	public void setAuthId(String aAuthId) {
		this.authId = aAuthId;
	}
	public String getAuthName() {
		return this.authName;
	}
	public void setAuthName(String aAuthName) {
		this.authName = aAuthName;
	}
	
}
