package com.adplan.model;
import java.sql.Date;

public class AdPlanVO implements java.io.Serializable {
	private String adPlanId;
	private String adPlanName;
	private Date adPlanStartDate;
	private Date adPlanEndDate;
	private  Integer adPlanPrice;
	private Integer adPlanRemainNo;
	
	public String getAdPlanId() {
		return this.adPlanId;
	}
	public void setAdPlanId(String aAdPlanId) {
		this.adPlanId = aAdPlanId;
	}
	public String getAdPlanName() {
		return this.adPlanName;
	}
	public void setAdPlanName(String aAdPlanName) {
		this.adPlanName = aAdPlanName;
	}
	public Date getAdPlanStartDate() {
		return this.adPlanStartDate;
	}
	public void setAdPlanStartDate(Date aAdPlanStartDate) {
		this.adPlanStartDate = aAdPlanStartDate;
	}
	public Date getAdPlanEndDate() {
		return this.adPlanEndDate;
	}
	public void setAdPlanEndDate(Date aAdPlanEndDate) {
		this.adPlanEndDate = aAdPlanEndDate;
	}
	public Integer getAdPlanPrice() {
		return this.adPlanPrice;
	}
	public void setAdPlanPrice(Integer aAdPlanPrice) {
		this.adPlanPrice = aAdPlanPrice;
	}
	public Integer getAdPlanRemainNo() {
		return this.adPlanRemainNo;
	}
	public void setAdPlanRemainNo(Integer aAdPlanRemainNo) {
		this.adPlanRemainNo = aAdPlanRemainNo;
	}
	
	

}
