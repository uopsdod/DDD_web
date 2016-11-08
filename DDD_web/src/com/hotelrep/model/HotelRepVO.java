package com.hotelrep.model;
import java.sql.Date;

public class HotelRepVO implements java.io.Serializable {
	private String hotelRepId;
	private String hotelRepHotelId; //Not Null
	private String hotelRepMemId; // Not Null
	private String hotelRepOrdId; //Not Null
	private String hotelRepEmpId;
	private String hotelRepContent;
	private String hotelRepStatus; //0.未審核 1.已審核未通過 2.已審核已通過
	private Date   hotelRepDate;
	private Date   hotelRepReviewDate;
	
	public String getHotelRepId() {
		return this.hotelRepId;
	}
	public void setHotelRepId(String aHotelRepId) {
		this.hotelRepId = aHotelRepId;
	}
	public String getHotelRepHotelId() {
		return this.hotelRepHotelId;
	}
	public void setHotelRepHotelId(String aHotelRepHotelId) {
		this.hotelRepHotelId = aHotelRepHotelId;
	}
	public String getHotelRepMemId() {
		return this.hotelRepMemId;
	}
	public void setHotelRepMemId(String aHotelRepMemId) {
		this.hotelRepMemId = aHotelRepMemId;
	}
	public String getHotelRepOrdId() {
		return this.hotelRepOrdId;
	}
	public void setHotelRepOrdId(String aHotelRepOrdId) {
		this.hotelRepOrdId = aHotelRepOrdId;
	}
	public String getHotelRepEmpId() {
		return this.hotelRepEmpId;
	}
	public void setHotelRepEmpId(String aHotelRepEmpId) {
		this.hotelRepEmpId = aHotelRepEmpId;
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
	
}
