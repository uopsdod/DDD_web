package com.hotel.model;

import java.util.*;
import com.ord.model.OrdVO;

public interface HotelDAO_interface {
	 public List<HotelVO> getAll();
	 public List<HotelVO> getAll_NEED_CHECK();
	 public List<HotelVO> getAll_TO_VIEW();
	 public List<HotelVO> getListBySql(String sql);//嘉鴻
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
     public HotelVO hotelMemCheck(String aHotelAccount, String aHotelPwd);
     /* 下面是韓哥需要的 */
     public Set<OrdVO> getOrdsByHotelId(String aHotelId);
}

