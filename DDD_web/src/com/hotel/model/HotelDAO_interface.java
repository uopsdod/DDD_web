package com.hotel.model;
import java.util.*;

import com.mem.model.MemVO;

public interface HotelDAO_interface {
	 public List<HotelVO> getAll();
	 public List<HotelVO> getAll_NEED_CHECK();
	 public List<HotelVO> getAll_TO_VIEW();
	 public void insert(HotelVO aHotelVO);
     public void update(HotelVO aHotelVO);
     public void update_status(String hotelId,String hotelStatus);
     public void update_hotelBlackList(String hotelId,String hotelBlackList);
     public HotelVO findByPrimaryKey(String aHotelId);
     public HotelVO hotelMemCheck(String aHotelAccount, String aHotelPwd);
}
