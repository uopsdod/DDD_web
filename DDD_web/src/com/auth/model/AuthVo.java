package com.auth.model;

import com.emp.model.EmpVO;

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
	
	private String empAuthEmpId;
	private String empAuthAuthId;
	
	public String getEmpAuthEmpId() {
		return this.empAuthEmpId;
	}
	public void setEmpAuthEmpId(String aEmpAuthEmpId) {
		this.empAuthEmpId = aEmpAuthEmpId;
	}
	public String getEmpAuthAuthId() {
		return this.empAuthAuthId;
	}
	public void setEmpAuthAuthId(String aEmpAuthAuthId) {
		this.empAuthAuthId = aEmpAuthAuthId;
	}
	
	
	
}
