
package com.memrep.modelJDBC;


import java.io.Serializable;
import java.sql.Clob;
import java.sql.Date;

import com.emp.model.EmpVO;
import com.hotel.model.HotelVO;
import com.mem.model.MemVO;
import com.ord.model.OrdVO;

public class MemRepVO implements Serializable {
	private String memRepId;
	private String memRepContent;
	private String memRepStatus; // 0.未審核 1.已審核未通過 2.已審核已通過
	private Date memRepDate;
	private Date memRepReviewDate;	
	
	private String memRepMemId; // Not Null
	private String memRepHotelId; // Not Null
	private String memRepOrdId; // Not Null
	private String memRepEmpId;
	public String getMemRepId() {
		return memRepId;
	}
	public void setMemRepId(String memRepId) {
		this.memRepId = memRepId;
	}
	public String getMemRepContent() {
		return memRepContent;
	}
	public void setMemRepContent(String memRepContent) {
		this.memRepContent = memRepContent;
	}
	public String getMemRepStatus() {
		return memRepStatus;
	}
	public void setMemRepStatus(String memRepStatus) {
		this.memRepStatus = memRepStatus;
	}
	public Date getMemRepDate() {
		return memRepDate;
	}
	public void setMemRepDate(Date memRepDate) {
		this.memRepDate = memRepDate;
	}
	public Date getMemRepReviewDate() {
		return memRepReviewDate;
	}
	public void setMemRepReviewDate(Date memRepReviewDate) {
		this.memRepReviewDate = memRepReviewDate;
	}
	public String getMemRepMemId() {
		return memRepMemId;
	}
	public void setMemRepMemId(String memRepMemId) {
		this.memRepMemId = memRepMemId;
	}
	public String getMemRepHotelId() {
		return memRepHotelId;
	}
	public void setMemRepHotelId(String memRepHotelId) {
		this.memRepHotelId = memRepHotelId;
	}
	public String getMemRepOrdId() {
		return memRepOrdId;
	}
	public void setMemRepOrdId(String memRepOrdId) {
		this.memRepOrdId = memRepOrdId;
	}
	public String getMemRepEmpId() {
		return memRepEmpId;
	}
	public void setMemRepEmpId(String memRepEmpId) {
		this.memRepEmpId = memRepEmpId;
	}

	
	
}
