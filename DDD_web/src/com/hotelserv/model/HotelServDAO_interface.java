package com.hotelserv.model;

import java.util.*;

public interface HotelServDAO_interface {
	public void insert(HotelServVO aHotelServVO);
	public void update(HotelServVO aHotelServVO);
	public void delete(String aHotelServServId, String aHotelServHotelId);
	public HotelServVO findByPrimaryKey(String aHotelServServId, String aHotelServHotelId);
	public List<HotelServVO> getAll();
	public List<HotelServVO> findByHotelId(String aHotelId);
}
