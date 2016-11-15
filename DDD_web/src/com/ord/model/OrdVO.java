package com.ord.model;

import com.room.model.*;
import com.mem.model.*;
import com.hotel.model.*;

import java.sql.Timestamp;

public class OrdVO implements java.io.Serializable {
	private String ordId;
//	private String ordRoomId;
	private RoomVO ordRoomVO;
	
//	private String ordMemId;
	private MemVO ordMemVO;
	
//	private String ordHotelId;
	private HotelVO ordHotelVO;
	
	private Integer ordPrice;
	private Timestamp ordLiveDate;
	private Timestamp ordDate;
	private String ordStatus;
	private String ordRatingContent;
	private Integer ordRatingStarNo;
	private byte[] ordQrPic;
	private String ordMsgNo;
	
	public String getOrdId() {
		return this.ordId;
	}
	public void setOrdId(String aOrdId) {
		this.ordId = aOrdId;
	}
	public RoomVO getOrdRoomVO() {
		return this.ordRoomVO;
	}
	
	public void setOrdRoomVO(RoomVO aOrdRoomVO) {
		this.ordRoomVO = aOrdRoomVO;
	}
	public MemVO getOrdMemVO() {
		return this.ordMemVO;
	}
	public void setOrdMemVO(MemVO aOrdMemVO) {
		this.ordMemVO = aOrdMemVO;
	}
	public HotelVO getOrdHotelVO() {
		return this.ordHotelVO;
	}
	public void setOrdHotelVO(HotelVO aOrdHotelVO) {
		this.ordHotelVO = aOrdHotelVO;
	}
	public Integer getOrdPrice() {
		return this.ordPrice;
	}
	public void setOrdPrice(Integer aOrdPrice) {
		this.ordPrice = aOrdPrice;
	}
	public Timestamp getOrdLiveDate() {
		return this.ordLiveDate;
	}
	public void setOrdLiveDate(Timestamp aOrdLiveDate) {
		this.ordLiveDate = aOrdLiveDate;
	}
	public Timestamp getOrdDate() {
		return this.ordDate;
	}
	public void setOrdDate(Timestamp aOrdDate) {
		this.ordDate = aOrdDate;
	}
	public String getOrdStatus() {
		return this.ordStatus;
	}
	public void setOrdStatus(String aOrdStatus) {
		this.ordStatus = aOrdStatus;
	}
	public String getOrdRatingContent() {
		return this.ordRatingContent;
	}
	public void setOrdRatingContent(String aOrdRatingContent) {
		this.ordRatingContent = aOrdRatingContent;
	}
	public Integer getOrdRatingStarNo() {
		return this.ordRatingStarNo;
	}
	public void setOrdRatingStarNo(Integer aOrdRatingStarNo) {
		this.ordRatingStarNo = aOrdRatingStarNo;
	}
	public byte[] getOrdQrPic() {
		return this.ordQrPic;
	}
	public void setOrdQrPic(byte[] aOrdQrPic) {
		this.ordQrPic = aOrdQrPic;
	}
	public String getOrdMsgNo() {
		return this.ordMsgNo;
	}
	public void setOrdMsgNo(String aOrdMsgNo) {
		this.ordMsgNo = aOrdMsgNo;
	}
	
	/* 過渡方法  */
	public String getOrdHotelId(){
		return this.ordHotelVO.getHotelId();
	}
	
	public String getOrdRoomId(){
		return this.ordRoomVO.getRoomId();
	}
	
	public String getOrdMemId(){
		return this.ordMemVO.getMemId();
	}
	
}
