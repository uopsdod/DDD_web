package com.hotelrep.model;
import java.util.List;

public interface HotelRepDAO_interface {
	public String insert(HotelRepVO aHotelRepVO);
	public void update(HotelRepVO aHotelRepVO);
	public void delete(String aHotelRepId);
	public List<HotelRepVO> getAll();
	public List<HotelRepVO> getAllByHotelRepStatus(String aHotelRepStatus);
	public HotelRepVO findByPrimaryKey(String aHotelRepId);
}
