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
	private RoomVO roomVO;
	private MemVO memVO;
	private HotelVO hotelVO;
	private RoomPhotoVO roomPhotoVO;
	public HotelVO getHotelVO() {
		return hotelVO;
	}
	public void setHotelVO(HotelVO hotelVO) {
		this.hotelVO = hotelVO;
	}
	public RoomPhotoVO getRoomPhotoVO() {
		return roomPhotoVO;
	}
	public void setRoomPhotoVO(RoomPhotoVO roomPhotoVO) {
		this.roomPhotoVO = roomPhotoVO;
	}
	
	
	public RoomVO getRoomVO() {
		return roomVO;
	}
	public void setRoomVO(RoomVO roomVO) {
		this.roomVO = roomVO;
	}
	public MemVO getMemVO() {
		return memVO;
	}
	public void setMemVO(MemVO memVO) {
		this.memVO = memVO;
	}
	
	
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
