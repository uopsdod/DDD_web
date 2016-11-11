package com.hotel.model;
import java.util.*;

public interface HotelDAO_interface {
	 public List<HotelVO> getAll();
	 public List<HotelVO> getAll_NEED_CHECK();
	 public List<HotelVO> getAll_TO_VIEW();
	 public HotelVO getUser(String aAccount);
	 public void insert(HotelVO aHotelVO);
     public void update(HotelVO aHotelVO);
     public void update_status_1(HotelVO aHotelVO);
     public void update_status(String hotelId,String hotelStatus);
     public void update_hotelBlackList(String hotelId,String hotelBlackList);
     public void update_psw(String hotelPwd,String hotelId);
     public HotelVO findByPrimaryKey(String aHotelId);
     public byte[] getPhoto_cov(String aHotelId);
     public byte[] getPhoto_register(String aHotelId);
}
