package com.rent.model;

import java.sql.Date;

public class RentVO {
	
	String rentId = null;
	String rentHotelId = null;
	Date rentPayDate = null;
	Date rentStartDate = null;
	Date rentEndDate = null;
	Integer rentPrice = null;
	
	public String getRentId() {
		return rentId;
	}
	public void setRentId(String aRentId) {
		this.rentId = aRentId;
	}
	public String getRentHotelId() {
		return rentHotelId;
	}
	public void setRentHotelId(String aRentHotelId) {
		this.rentHotelId = aRentHotelId;
	}
	public Date getRentPayDate() {
		return rentPayDate;
	}
	public void setRentPayDate(Date aRentPayDate) {
		this.rentPayDate = aRentPayDate;
	}
	public Date getRentStartDate() {
		return rentStartDate;
	}
	public void setRentStartDate(Date aRentStartDate) {
		this.rentStartDate = aRentStartDate;
	}
	public Date getRentEndDate() {
		return rentEndDate;
	}
	public void setRentEndDate(Date aRentEndDate) {
		this.rentEndDate = aRentEndDate;
	}
	public Integer getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(Integer aRentPrice) {
		this.rentPrice = aRentPrice;
	}
	
	
	
	
	
}
