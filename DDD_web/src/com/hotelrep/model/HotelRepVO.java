
package com.hotelrep.model;


import java.io.Serializable;
import java.sql.Date;

import com.emp.model.EmpVO;
import com.hotel.model.HotelVO;
import com.mem.model.MemVO;
import com.ord.model.OrdVO;

public class HotelRepVO implements Serializable {
	private String hotelRepId;
	private String hotelRepContent;
	private String hotelRepStatus; // 0.未審核 1.已審核未通過 2.已審核已通過
	private Date hotelRepDate;
	private Date hotelRepReviewDate;	
	
	private MemVO hotelRepMemVO; // Not Null
	private HotelVO hotelRepHotelVO; // Not Null
	private OrdVO hotelRepOrdVO; // Not Null
	private EmpVO hotelRepEmpVO;
	
	public String getHotelRepId() {
		return this.hotelRepId;
	}
	public void setHotelRepId(String aHotelRepId) {
		this.hotelRepId = aHotelRepId;
	}
	public String getHotelRepContent() {
		return this.hotelRepContent;
	}
	public void setHotelRepContent(String aHotelRepContent) {
		this.hotelRepContent = aHotelRepContent;
	}
	public String getHotelRepStatus() {
		return this.hotelRepStatus;
	}
	public void setHotelRepStatus(String aHotelRepStatus) {
		this.hotelRepStatus = aHotelRepStatus;
	}
	public Date getHotelRepDate() {
		return this.hotelRepDate;
	}
	public void setHotelRepDate(Date aHotelRepDate) {
		this.hotelRepDate = aHotelRepDate;
	}
	public Date getHotelRepReviewDate() {
		return this.hotelRepReviewDate;
	}
	public void setHotelRepReviewDate(Date aHotelRepReviewDate) {
		this.hotelRepReviewDate = aHotelRepReviewDate;
	}
	public MemVO getHotelRepMemVO() {
		return this.hotelRepMemVO;
	}
	public void setHotelRepMemVO(MemVO aHotelRepMemVO) {
		this.hotelRepMemVO = aHotelRepMemVO;
	}
	public HotelVO getHotelRepHotelVO() {
		return this.hotelRepHotelVO;
	}
	public void setHotelRepHotelVO(HotelVO aHotelRepHotelVO) {
		this.hotelRepHotelVO = aHotelRepHotelVO;
	}
	public OrdVO getHotelRepOrdVO() {
		return this.hotelRepOrdVO;
	}
	public void setHotelRepOrdVO(OrdVO aHotelRepOrdVO) {
		this.hotelRepOrdVO = aHotelRepOrdVO;
	}
	public EmpVO getHotelRepEmpVO() {
		return this.hotelRepEmpVO;
	}
	public void setHotelRepEmpVO(EmpVO aHotelRepEmpVO) {
		this.hotelRepEmpVO = aHotelRepEmpVO;
	}
}
