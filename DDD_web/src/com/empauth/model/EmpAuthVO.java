package com.empauth.model;

public class EmpAuthVO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
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
