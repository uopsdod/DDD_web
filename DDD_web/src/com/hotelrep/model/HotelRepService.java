package com.hotelrep.model;
import java.util.List;

public class HotelRepService {
	private HotelRepDAO_interface dao;
	
	public HotelRepService(){
		this.dao = new HotelRepJNDIDAO();
	}
	
	public int insert(String aHotelRepHotelId, String aHotelRepMemId, String aHotelRepOrdId, String aHotelRepContent){
		HotelRepVO hotelRepVO = new HotelRepVO();
		hotelRepVO.setHotelRepHotelId(aHotelRepHotelId);
		hotelRepVO.setHotelRepMemId(aHotelRepMemId);
		hotelRepVO.setHotelRepOrdId(aHotelRepOrdId);
		hotelRepVO.setHotelRepContent(aHotelRepContent);
		return dao.insert(hotelRepVO);
	}
	
	public int update(String aHotelRepEmpId, String aHotelRepStatus, String aHotelRepId){
		HotelRepVO hotelRepVO = new HotelRepVO();
		hotelRepVO.setHotelRepEmpId(aHotelRepEmpId);
		hotelRepVO.setHotelRepStatus(aHotelRepStatus);
		hotelRepVO.setHotelRepId(aHotelRepId);
		return dao.insert(hotelRepVO);
	}
	
	public int delete(String aHotelRepId){
		return dao.delete(aHotelRepId);
	}
	
	public List<HotelRepVO> getAll(){
		return dao.getAll();
	}
	public List<HotelRepVO> getAllByHotelRepStatus(String aHotelRepStatus){
		return dao.getAllByHotelRepStatus(aHotelRepStatus);
	}
	public HotelRepVO findByPrimaryKey(String aHotelRepId){
		return dao.findByPrimaryKey(aHotelRepId);
	}
	
	//柚子的萬用查詢
//	public List<HotelRepVO> getAll(Map<String, String[]> aMap){
//		
//	}
	
}
