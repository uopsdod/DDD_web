package com.wish.model;

import java.io.Serializable;

public class WishVO implements Serializable {
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
