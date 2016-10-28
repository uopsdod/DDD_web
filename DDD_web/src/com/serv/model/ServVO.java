package com.serv.model;

public class ServVO implements java.io.Serializable {

	private String servId;
	private String servName;
	
	public String getServId() {
		return this.servId;
	}
	public void setServId(String aServId) {
		this.servId = aServId;
	}
	public String getServName() {
		return this.servName;
	}
	public void setServName(String aServName) {
		this.servName = aServName;
	}
}
