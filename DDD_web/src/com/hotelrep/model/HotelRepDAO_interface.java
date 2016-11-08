package com.hotelrep.model;
import java.util.List;
import java.util.Map;

public interface HotelRepDAO_interface {
	public static final String tableName = "hotelRep"; //柚子的萬用查詢
	public int insert(HotelRepVO aHotelRepVO);
	public int update(HotelRepVO aHotelRepVO);
	public int delete(String aHotelRepId);
	public List<HotelRepVO> getAll();
	public List<HotelRepVO> getAllByHotelRepStatus(String aHotelRepStatus);
	public HotelRepVO findByPrimaryKey(String aHotelRepId);
	//柚子的萬用查詢
	public List<HotelRepVO> getAll(Map<String, String[]> aMap);
}
