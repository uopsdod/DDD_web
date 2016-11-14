package com.wish.model;

import java.util.List;
import java.util.Map;

public interface WishDAO_interface {
	public void insert(WishVO aWishVO);
	public void updata(WishVO aWishVO);
	public void delete(String aWishMemId, String aWishRoomId);
	public WishVO findByPrimaryKey(String aWishMemId, String aWishRommId);
	public List<WishVO> getAll();
	//-------------------------------------------貴新增
	public List<WishVO> getOneWishOfmem(String wishMemId);
	public void delete_one(String wishMemId,String WishRoomId);
	public List<Map> getOneWishOfmemNO(String wishMemId);
}
