package com.wish.model;

import java.util.List;

public class WishService {
	private WishDAO_interface dao;
	
	public WishService(){
		dao = new WishDAO();
	}
	
	public WishVO addWish(String aWishRoomId){
		WishVO wishVO = new WishVO();
		
		wishVO.setWishRoomId(aWishRoomId);
		dao.insert(wishVO);
		return wishVO;
	}
	
	public WishVO updateWish(String aWishMemId, String aWishRoomId){
		return null;
	}
	
	public void deleteWish(String aWishMemId, String aWishRoomId){
		dao.delete(aWishMemId, aWishRoomId);
	}
	
	public WishVO getOneWish(String aWishMemId, String aWishRoomId){
		return dao.findByPrimaryKey(aWishMemId, aWishRoomId);
	}
	
	public List<WishVO> getAll(){
		return dao.getAll();
	}
}
