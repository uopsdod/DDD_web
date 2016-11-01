package com.hotelrep.model;
import java.util.List;

public interface HotelRepDAO_interface {
	public int insert(HotelRepVO aHotelRepVO);
	public int update(HotelRepVO aHotelRepVO);
	public int delete(String aHotelRepId);
	public List<HotelRepVO> getAll();
	public List<HotelRepVO> getAllByHotelRepStatus(String aHotelRepStatus);
	public HotelRepVO findByPrimaryKey(String aHotelRepId);
}
