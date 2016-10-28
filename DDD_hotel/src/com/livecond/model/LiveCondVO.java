package com.livecond.model;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Timestamp;

public class LiveCondVO implements Serializable {
	private String liveCondId;
	private String liveCondName;
	
	public String getLiveCondId() {
		return this.liveCondId;
	}
	public void setLiveCondId(String aLiveCondId) {
		this.liveCondId = aLiveCondId;
	}
	public String getLiveCondName() {
		return this.liveCondName;
	}
	public void setLiveCondName(String aLiveCondName) {
		this.liveCondName = aLiveCondName;
	}
	
}
