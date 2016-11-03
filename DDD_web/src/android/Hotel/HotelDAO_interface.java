package android.Hotel;
import java.util.*;

public interface HotelDAO_interface {
	 public List<HotelVO> getAll();
	 public List<HotelVO> getAll_NEED_CHECK();
	 public void insert(HotelVO aHotelVO);
     public void update(HotelVO aHotelVO);
     public void update_status(String hotelId,HotelVO aHotelVO);
     public void update_hotelBlackList(String hotelId,HotelVO aHotelVO);
     public HotelVO findByPrimaryKey(String aHotelId);
}
