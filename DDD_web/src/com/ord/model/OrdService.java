package com.ord.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OrdService {

	private OrdDAO_interface dao;
	
//	public OrdService(){
//		dao = new OrdDAO();
//	}
	
	public OrdService() {
		//dao = new EmpHibernateDAO();
		//註1: 雖然model-config1-DriverManagerDataSource.xml也可以用
		//註2: 但為了使用Apache DBCP連線池,以提高效能,所以底下的model-config2-JndiObjectFactoryBean.xml內部dataSource設定是採用org.springframework.jndi.JndiObjectFactoryBean
		ApplicationContext context = new ClassPathXmlApplicationContext("model-config2-JndiObjectFactoryBean.xml");
		dao =(OrdDAO_interface) context.getBean("ordDAO");
	}	
	
	
	
	/* 
	 * = INSERT_STMT 對應 =
	 * 01    ordID
	 * 02-01 ordRoomId
	 * 03-02 ordMemId
	 * 04-03 ordHotelId
	 * 05-04 ordPrice
	 * 06-05 ordLiveDate
	 * 07    ordDate
	 * 08-06 ordStatus
	 * 09-07 ordRatingContent
	 * 10-08 ordRatingStarNo
	 * 11-09 ordQrPic
	 * 12-10 ordMsgNo
	*/ 
	
	/* (一般會員)新增一筆訂單 */
	public OrdVO addOrd(String aOrdRoomId,String aOrdMemId,String aOrdHotelId,Integer aOrdPrice,Timestamp aOrdLiveDate,String aOrdStatus,String aOrdRatingContent,Integer aOrdRatingStarNo,byte[] aOrdQrPic,String aOrdMsgNo){
		
		OrdVO ordVO = new OrdVO();
		
		com.room.model.RoomVO roomVO = new com.room.model.RoomVO();
		roomVO.setRoomId(aOrdRoomId);
		ordVO.setOrdRoomVO(roomVO);
		
		com.mem.model.MemVO memVO = new com.mem.model.MemVO();
		memVO.setMemId(aOrdMemId);
		ordVO.setOrdMemVO(memVO);

		com.hotel.model.HotelVO hotelVO = new com.hotel.model.HotelVO();
		hotelVO.setHotelId(aOrdHotelId);
		ordVO.setOrdHotelVO(hotelVO);
		
		ordVO.setOrdPrice(aOrdPrice);
		ordVO.setOrdLiveDate(aOrdLiveDate);
		ordVO.setOrdStatus(aOrdStatus);
		ordVO.setOrdRatingContent(aOrdRatingContent);
		ordVO.setOrdRatingStarNo(aOrdRatingStarNo);
		ordVO.setOrdQrPic(aOrdQrPic);
		ordVO.setOrdMsgNo(aOrdMsgNo);
		
		Timestamp currentTime = new Timestamp(new java.util.Date().getTime());
		ordVO.setOrdDate(currentTime);

		/* 把自增主鍵填上去 */
		String ordId = dao.insert(ordVO);
		ordVO.setOrdId(ordId);
		return ordVO;
	}
	
	/* 
	 * = UPDATE 對應 =
	 * 02-01 ordRoomId
	 * 03-02 ordMemId
	 * 04-03 ordHotelId
	 * 05-04 ordPrice
	 * 06-05 ordLiveDate
	 * 07    ordDate
	 * 08-06 ordStatus
	 * 09-07 ordRatingContent
	 * 10-08 ordRatingStarNo
	 * 11-09 ordQrPic
	 * 12-10 ordMsgNo
	 * 01-11 ordID
	*/	
	
	/* (一般會員)新增評論及星星數 & (系統)修改訂單狀態 */
	public OrdVO updateOrd(OrdVO aOrdVO){
//		OrdVO ordVO = new OrdVO();
//
//		com.room.model.RoomVO roomVO = new com.room.model.RoomVO();
//		roomVO.setRoomId(aOrdRoomId);
//		ordVO.setOrdRoomVO(roomVO);
//		
//		com.mem.model.MemVO memVO = new com.mem.model.MemVO();
//		memVO.setMemId(aOrdMemId);
//		ordVO.setOrdMemVO(memVO);
//
//		com.hotel.model.HotelVO hotelVO = new com.hotel.model.HotelVO();
//		hotelVO.setHotelId(aOrdHotelId);
//		ordVO.setOrdHotelVO(hotelVO);
//		
//		ordVO.setOrdPrice(aOrdPrice);
//		ordVO.setOrdLiveDate(aOrdLiveDate);
//		ordVO.setOrdStatus(aOrdStatus);
//		ordVO.setOrdRatingContent(aOrdRatingContent);
//		ordVO.setOrdRatingStarNo(aOrdRatingStarNo);
//		ordVO.setOrdQrPic(aOrdQrPic);
//		ordVO.setOrdMsgNo(aOrdMsgNo);
//		ordVO.setOrdId(aOrdId);
//		ordVO.setOrdDate(aOrdDate);
		
		dao.update(aOrdVO);
		
		//這樣才有找外來鍵的其他資訊的超能力
		return dao.findByPrimaryKey(aOrdVO.getOrdId());
	}
	
	/* (練習用)刪除 */
	public void deleteOrd(String aOrdId){
		dao.delete(aOrdId);
	}
	
	/* (管理員)依訂單編號查詢 */
	public OrdVO getOneOrd(String aOrdId){
		return dao.findByPrimaryKey(aOrdId);
	}
	
	/* (管理員)查詢所有訂單內容 評論及星星數 */
	public List<OrdVO> getAll(){
		return dao.getAll();
	}
	
	/* (一般會員)列出該一般會員的所有訂單 QRCode 驗證碼 */
	public List<OrdVO> getAllByOrdMemId(String aOrdMemId){
		return dao.getAllByOrdMemId(aOrdMemId); 
	}
	
	/* (廠商會員)列出該廠商會員的所有訂單 */
	public List<OrdVO> getAllByOrdHotelId(String aOrdHotelId){
		return dao.getAllByOrdHotelId(aOrdHotelId);
	}
	
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
    public List<OrdVO> getAll(Map<String, String[]> aMap){
    	return dao.getAll(aMap);
    } 
}
