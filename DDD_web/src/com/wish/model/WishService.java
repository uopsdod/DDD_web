package com.wish.model;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import com.hotel.model.*;
import com.wish.controller.SocketOfWish;
public class WishService {
	private WishDAO_interface dao;
	
	public WishService(){
		dao = new WishDAO();
	}
	
	
	public WishVO addWish_web(String aWishMemId, String aWishRoomId){
		WishVO wishVO = new WishVO();
		wishVO.setWishMemId(aWishMemId);
		wishVO.setWishRoomId(aWishRoomId);
		dao.insert(wishVO);
				
		HotelService hotelSvc =new HotelService();
		String productCount =hotelSvc.GET_WISH_COUNT(aWishRoomId);
		SocketOfWish.getCount(aWishRoomId, productCount);
  
		return wishVO;
		
	}
	
	
	public WishVO addWish(String aWishMemId, String aWishRoomId){
		WishVO wishVO = new WishVO();
		wishVO.setWishMemId(aWishMemId);
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
	
	public List<String> getAllWishRoomId(String aWishMemId){
		return dao.getAllWishRoomId(aWishMemId);
	}

	//--------------------------------------貴新增
	public List<WishVO> getOneWishOfmem(String wishMemId) {
		return dao.getOneWishOfmem(wishMemId);
	}
	
	public List<Map> getOneWishOfmemNO(String wishMemId) {
		
		return dao.getOneWishOfmemNO(wishMemId);		
	}
	

	public void delete(String wishMemId,String WishRoomId) {
		dao.delete_one(wishMemId, WishRoomId);
	}

}
