package com.ord.model;
import java.sql.Date;

public class OrdVO implements java.io.Serializable {
	private String ordId;
	private String ordRoomId;
	private String ordMemId;
	private String ordHotelId;
	private Integer ordPrice;
	private Date ordLiveDate;
	private Date ordDate;
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
	public String getOrdRoomId() {
		return this.ordRoomId;
	}
	public void setOrdRoomId(String aOrdRoomId) {
		this.ordRoomId = aOrdRoomId;
	}
	public String getOrdMemId() {
		return this.ordMemId;
	}
	public void setOrdMemId(String aOrdMemId) {
		this.ordMemId = aOrdMemId;
	}
	public String getOrdHotelId() {
		return this.ordHotelId;
	}
	public void setOrdHotelId(String aOrdHotelId) {
		this.ordHotelId = aOrdHotelId;
	}
	public Integer getOrdPrice() {
		return this.ordPrice;
	}
	public void setOrdPrice(Integer aOrdPrice) {
		this.ordPrice = aOrdPrice;
	}
	public Date getOrdLiveDate() {
		return this.ordLiveDate;
	}
	public void setOrdLiveDate(Date aOrdLiveDate) {
		this.ordLiveDate = aOrdLiveDate;
	}
	public Date getOrdDate() {
		return this.ordDate;
	}
	public void setOrdDate(Date aOrdDate) {
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
}
