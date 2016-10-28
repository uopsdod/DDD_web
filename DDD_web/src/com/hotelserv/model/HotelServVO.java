package com.hotelserv.model;

import java.io.*;

public class HotelServVO implements Serializable{
	private String hotelServServId;
	private String hotelServHotelId;
	
	
	public String getHotelServServId() {
		return this.hotelServServId;
	}
	public void setHotelServServId(String aHotelServServId) {
		this.hotelServServId = aHotelServServId;
	}
	public String getHotelServHotelId() {
		return this.hotelServHotelId;
	}
	public void setHotelServHotelId(String aHotelServHotelId) {
		this.hotelServHotelId = aHotelServHotelId;
	}
}
