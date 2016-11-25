package com.wish.modelHibernate;

import java.io.Serializable;

import com.hotel.model.HotelVO;
import com.mem.model.MemVO;
import com.room.model.RoomVO;
import com.roomphoto.model.RoomPhotoVO;

public class WishVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String bs64;
	public String getBs64() {
		return this.bs64;
	}
	public void setBs64(String aBs64) {
		this.bs64 = aBs64;
	}
	//---------------------------------上面貴新增
	private String wishMemId;
	private String wishRoomId;
	
	
	
	public String getWishMemId() {
		return this.wishMemId;
	}
	public void setWishMemId(String aWishMemId) {
		this.wishMemId = aWishMemId;
	}
	public String getWishRoomId() {
		return this.wishRoomId;
	}
	public void setWishRoomId(String aWishRoomId) {
		this.wishRoomId = aWishRoomId;
	}
	
}
