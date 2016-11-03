package com.ord.model;

import java.util.List;
import java.sql.Date;

public class OrdService {

	private OrdDAO_interface dao;
	
	public OrdService(){
		dao = new OrdDAO();
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
	 * 08    ordStatus
	 * 09	 ordRatingContent
	 * 10	 ordRatingStarNo
	 * 11-06 ordQrPic
	 * 12-07 ordMsgNo
	*/	
	
	/* (一般會員)新增一筆訂單 */
	public OrdVO addOrd(String aOrdRoomId,String aOrdMemId,String aOrdHotelId,Integer aOrdPrice,Date aOrdLiveDate,byte[] aOrdQrPic,String aOrdMsgNo){
		
		OrdVO ordVO = new OrdVO();
		
		ordVO.setOrdRoomId(aOrdRoomId);
		ordVO.setOrdMemId(aOrdMemId);
		ordVO.setOrdHotelId(aOrdHotelId);
		ordVO.setOrdPrice(aOrdPrice);
		ordVO.setOrdLiveDate(aOrdLiveDate);
		ordVO.setOrdQrPic(aOrdQrPic);
		ordVO.setOrdMsgNo(aOrdMsgNo);
		
		dao.insert(ordVO);
		
		return ordVO;
	}
	
	/* (一般會員)新增評論及星星數 & (系統)修改訂單狀態 */
	/* UPDATE ord set ordStatus=?, ordRatingContent=?, ordRatingStarNo=? where ordId = ? */
	public OrdVO updateOrd(String aOrdStatus, String aOrdRatingContent, Integer aOrdRatingStarNo, String aOrdId){
		OrdVO ordVO = new OrdVO();
		
		ordVO.setOrdStatus(aOrdStatus);
		ordVO.setOrdRatingContent(aOrdRatingContent);
		ordVO.setOrdRatingStarNo(aOrdRatingStarNo);
		ordVO.setOrdId(aOrdId);
		
		dao.update(ordVO);
		
		return ordVO;
	}
	
	/* (練習用)刪除 */
	public int deleteOrd(String aOrdId){
		return dao.delete(aOrdId);
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
}
