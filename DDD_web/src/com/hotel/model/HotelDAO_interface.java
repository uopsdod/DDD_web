package com.hotel.model;
import java.util.*;

public interface HotelDAO_interface {
	 public List<HotelVO> getAll();
	 public List<HotelVO> getAll_NEED_CHECK();
	 public List<HotelVO> getAll_TO_VIEW();
	 public void insert(HotelVO aHotelVO);
     public void update(HotelVO aHotelVO);
     public void update_status(String hotelId,HotelVO aHotelVO);
     public void update_hotelBlackList(String hotelId,HotelVO aHotelVO);
     public HotelVO findByPrimaryKey(String aHotelId);
}
