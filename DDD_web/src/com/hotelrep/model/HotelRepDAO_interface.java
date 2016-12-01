package com.hotelrep.model;
import java.util.List;
import com.mem.model.MemVO;

public interface HotelRepDAO_interface {
	public String insert(HotelRepVO aHotelRepVO);
	public void update(HotelRepVO aHotelRepVO);
	public void delete(String aHotelRepId);
	public List<HotelRepVO> getAll();
	public List<HotelRepVO> getAllByHotelRepStatus(String aHotelRepStatus);
	public HotelRepVO findByPrimaryKey(String aHotelRepId);
	/* 改廠商檢舉單狀態  + 改旅客黑名單狀態*/
	public void setMemBlackList(HotelRepVO aHotelRepVO,MemVO aMemVO);
}
