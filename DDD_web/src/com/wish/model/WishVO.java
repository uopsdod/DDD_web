package com.wish.model;

import java.io.Serializable;

public class WishVO implements Serializable {
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
