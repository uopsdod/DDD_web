package com.wish.model;

import java.util.List;

public interface WishDAO_interface {
	public void insert(WishVO aWishVO);
	public void updata(WishVO aWishVO);
	public void delete(String aWishMemId, String aWishRoomId);
	public WishVO findByPrimaryKey(String aWishMemId, String aWishRommId);
	public List<WishVO> getAll();
}
