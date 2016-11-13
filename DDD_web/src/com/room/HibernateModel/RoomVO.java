package com.room.HibernateModel;

import java.sql.Date;
import java.util.*;
import com.ord.model.OrdVO;

public class RoomVO implements java.io.Serializable{
	private String roomId;
	private String roomHotelId;
	private String roomName;
	private Integer roomTotalNo;
	private Integer roomPrice;
	private Integer roomBottomPrice;
	private Boolean roomForSell;
	private Boolean roomForSellAuto;
	private Integer roomRemainNo;
	private Integer roomDefaultNo;
	private Integer roomDiscountStartDate;	//毫秒數
	private Integer roomDiscountEndDate;    //毫秒數
	private Integer roomDisccountPercent;
	private Integer roomDiscountHr;
	private Boolean roomOnePrice;
	private String roomFun;
	private String roomMeal;
	private String roomSleep;
	private String roomFacility;
	private String roomSweetFacility;
	private Integer roomCapacity;
	private Integer roomOneBed;
	private Integer roomTwoBed;
	
	private Set<OrdVO> roomOrds = new HashSet<OrdVO>();
	
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String aRoomId) {
		this.roomId = aRoomId;
	}
	public String getRoomHotelId() {
		return roomHotelId;
	}
	public void setRoomHotelId(String aRoomHotelId) {
		this.roomHotelId = aRoomHotelId;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String aRoomName) {
		this.roomName = aRoomName;
	}
	public Integer getRoomTotalNo() {
		return roomTotalNo;
	}
	public void setRoomTotalNo(Integer aRoomTotalNo) {
		this.roomTotalNo = aRoomTotalNo;
	}
	public Integer getRoomPrice() {
		return roomPrice;
	}
	public void setRoomPrice(Integer aRoomPrice) {
		this.roomPrice = aRoomPrice;
	}
	public Integer getRoomBottomPrice() {
		return roomBottomPrice;
	}
	public void setRoomBottomPrice(Integer aRoomBottomPrice) {
		this.roomBottomPrice = aRoomBottomPrice;
	}
	public Boolean getRoomForSell() {
		return roomForSell;
	}
	public void setRoomForSell(Boolean aRoomForSell) {
		this.roomForSell = aRoomForSell;
	}
	public Boolean getRoomForSellAuto() {
		return roomForSellAuto;
	}
	public void setRoomForSellAuto(Boolean aRoomForSellAuto) {
		this.roomForSellAuto = aRoomForSellAuto;
	}
	public Integer getRoomRemainNo() {
		return roomRemainNo;
	}
	public void setRoomRemainNo(Integer aRoomRemainNo) {
		this.roomRemainNo = aRoomRemainNo;
	}
	public Integer getRoomDefaultNo() {
		return roomDefaultNo;
	}
	public void setRoomDefaultNo(Integer aRoomDefaultNo) {
		this.roomDefaultNo = aRoomDefaultNo;
	}
	public Integer getRoomDiscountStartDate() {
		return roomDiscountStartDate;
	}
	public void setRoomDiscountStartDate(Integer aRoomDiscountStartDate) {
		this.roomDiscountStartDate = aRoomDiscountStartDate;
	}
	public Integer getRoomDiscountEndDate() {
		return roomDiscountEndDate;
	}
	public void setRoomDiscountEndDate(Integer aRoomDiscountEndDate) {
		this.roomDiscountEndDate = aRoomDiscountEndDate;
	}
	public Integer getRoomDisccountPercent() {
		return roomDisccountPercent;
	}
	public void setRoomDisccountPercent(Integer aRoomDisccountPercent) {
		this.roomDisccountPercent = aRoomDisccountPercent;
	}
	public Integer getRoomDiscountHr() {
		return roomDiscountHr;
	}
	public void setRoomDiscountHr(Integer aRoomDiscountHr) {
		this.roomDiscountHr = aRoomDiscountHr;
	}
	public Boolean getRoomOnePrice() {
		return roomOnePrice;
	}
	public void setRoomOnePrice(Boolean aRoomOnePrice) {
		this.roomOnePrice = aRoomOnePrice;
	}
	public String getRoomFun() {
		return roomFun;
	}
	public void setRoomFun(String aRoomFun) {
		this.roomFun = aRoomFun;
	}
	public String getRoomMeal() {
		return roomMeal;
	}
	public void setRoomMeal(String aRoomMeal) {
		this.roomMeal = aRoomMeal;
	}
	public String getRoomSleep() {
		return roomSleep;
	}
	public void setRoomSleep(String aRoomSleep) {
		this.roomSleep = aRoomSleep;
	}
	public String getRoomFacility() {
		return roomFacility;
	}
	public void setRoomFacility(String aRoomFacility) {
		this.roomFacility = aRoomFacility;
	}
	public String getRoomSweetFacility() {
		return roomSweetFacility;
	}
	public void setRoomSweetFacility(String aRoomSweetFacility) {
		this.roomSweetFacility = aRoomSweetFacility;
	}
	public Integer getRoomCapacity() {
		return roomCapacity;
	}
	public void setRoomCapacity(Integer aRoomCapacity) {
		this.roomCapacity = aRoomCapacity;
	}
	public Integer getRoomOneBed() {
		return roomOneBed;
	}
	public void setRoomOneBed(Integer aRoomOneBed) {
		this.roomOneBed = aRoomOneBed;
	}
	public Integer getRoomTwoBed() {
		return roomTwoBed;
	}
	public void setRoomTwoBed(Integer aRoomTwoBed) {
		this.roomTwoBed = aRoomTwoBed;
	}
	
	public Set<OrdVO> getRoomOrds(){
		return this.roomOrds;
	}
	
	public void setRoomOrds(Set<OrdVO> aRoomOrds){
		this.roomOrds = aRoomOrds;
	}
	
}