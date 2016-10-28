package com.hotelserv.model;

import java.util.List;

public class HotelServService {
	private HotelServDAO_interface dao;
	
	public HotelServService(){
		dao = new HotelServDAO();
	}
	
	public HotelServVO addHotelServ(String aHotelServHotelId){
		HotelServVO hotelServVO = new HotelServVO();
		
		hotelServVO.setHotelServHotelId(aHotelServHotelId);
		return hotelServVO;
	}
	
	public HotelServVO updateHotelServ(String aHotelServServId, String aHotelServHotelId){
		
		return null;
	}
	
	public void deleteHotelServ(String aHotelServServId, String aHotelServHotelId){
		dao.delete(aHotelServServId, aHotelServHotelId);
	}
	
	public HotelServVO getOneHotelServVO(String aHotelServServId, String aHotelServHotelId){
		return dao.findByPrimaryKey(aHotelServServId, aHotelServHotelId);
	}
	
	public List<HotelServVO> getAll(){
		return dao.getAll();
	}
}
