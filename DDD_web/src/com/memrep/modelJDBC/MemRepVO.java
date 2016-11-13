
package com.memrep.modelJDBC;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.Date;

public class MemRepVO implements Serializable {
	// 123 456 789 
	private String memRepId;
	private String memRepMemId; // Not Null
	private String memRepHotelId; // Not Null
	private String memRepOrdId; // Not Null
	private String memRepEmpId;
	private String memRepContent;
	private String memRepStatus; // 0.未審核 1.已審核未通過 2.已審核已通過
	private Date memRepDate;
	private Date memRepReviewDate;
	public String getMemRepId() {
		return this.memRepId;
	}
	public void setMemRepId(String aMemRepId) {
		this.memRepId = aMemRepId;
	}
	public String getMemRepMemId() {
		return this.memRepMemId;
	}
	public void setMemRepMemId(String aMemRepMemId) {
		this.memRepMemId = aMemRepMemId;
	}
	public String getMemRepHotelId() {
		return this.memRepHotelId;
	}
	public void setMemRepHotelId(String aMemRepHotelId) {
		this.memRepHotelId = aMemRepHotelId;
	}
	public String getMemRepOrdId() {
		return this.memRepOrdId;
	}
	public void setMemRepOrdId(String aMemRepOrdId) {
		this.memRepOrdId = aMemRepOrdId;
	}
	public String getMemRepEmpId() {
		return this.memRepEmpId;
	}
	public void setMemRepEmpId(String aMemRepEmpId) {
		this.memRepEmpId = aMemRepEmpId;
	}
	public String getMemRepContent() {
		return this.memRepContent;
	}
	public void setMemRepContent(String aMemRepContent) {
		this.memRepContent = aMemRepContent;
	}
	public String getMemRepStatus() {
		return this.memRepStatus;
	}
	public void setMemRepStatus(String aMemRepStatus) {
		this.memRepStatus = aMemRepStatus;
	}
	public Date getMemRepDate() {
		return this.memRepDate;
	}
	public void setMemRepDate(Date aMemRepDate) {
		this.memRepDate = aMemRepDate;
	}
	public Date getMemRepReviewDate() {
		return this.memRepReviewDate;
	}
	public void setMemRepReviewDate(Date aMemRepReviewDate) {
		this.memRepReviewDate = aMemRepReviewDate;
	}
	
	// abc
	
}
