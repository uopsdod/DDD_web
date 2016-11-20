package com.ad.model;
import java.sql.Date;

public class AdVO implements java.io.Serializable {
	private String bs64;
	public String getBs64() {
		return bs64;
	}
	public void setBs64(String bs64) {
		this.bs64 = bs64;
	}
	//貴新增上
	private String adId;
	private String adAdPlanId;
	private String adHotelId;
	private String adStatus;
	private Date adPayDate;
	private byte[] adPic;
	private String adPicContent;
	private Integer adHit;
	
	public String getAdId() {
		return adId;
	}
	public void setAdId(String aAdId) {
		this.adId = aAdId;
	}
	public String getAdAdPlanId() {
		return this.adAdPlanId;
	}
	public void setAdAdPlanId(String aAdAdPlanId) {
		this.adAdPlanId = aAdAdPlanId;
	}
	public String getAdHotelId() {
		return this.adHotelId;
	}
	public void setAdHotelId(String aAdHotelId) {
		this.adHotelId = aAdHotelId;
	}
	public String getAdStatus() {
		return this.adStatus;
	}
	public void setAdStatus(String aAdStatus) {
		this.adStatus = aAdStatus;
	}
	public Date getAdPayDate() {
		return this.adPayDate;
	}
	public void setAdPayDate(Date aAdPayDate) {
		this.adPayDate = aAdPayDate;
	}
	public byte[] getAdPic() {
		return this.adPic;
	}
	public void setAdPic(byte[] adPic) {
		this.adPic = adPic;
	}
	public String getAdPicContent() {
		return this.adPicContent;
	}
	public void setAdPicContent(String aAdPicContent) {
		this.adPicContent = aAdPicContent;
	}
	public Integer getAdHit() {
		return this.adHit;
	}
	public void setAdHit(Integer aAdHit) {
		this.adHit = aAdHit;
	}
	
	
	
}

