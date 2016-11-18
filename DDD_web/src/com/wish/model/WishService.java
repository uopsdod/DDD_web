package com.wish.model;

import java.util.List;
import java.util.Map;

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
	
	public List<String> SgetAllWishRoomId(String aWishMemId){
		return dao.getAllWishRoomId(aWishMemId);
	}

	//--------------------------------------貴新增
	public List<Map> getOneWishOfmemNO(String wishMemId) {
		
		return dao.getOneWishOfmemNO(wishMemId);		
	}
	

	public void delete(String wishMemId,String WishRoomId) {
		dao.delete_one(wishMemId, WishRoomId);
	}

}
