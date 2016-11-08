package com.hotel.model;

import java.util.List;

import com.emp.model.EmpVO;



public class HotelService {
	private HotelDAO_interface dao;
	
	public HotelService() {
		dao = new HotelDAO();
	}
	//取全
	public List<HotelVO> getAll() {
		return dao.getAll();
	}
	//一筆
	public HotelVO getOne(String aHotelId) {
		return dao.findByPrimaryKey(aHotelId);
	}
	
	public List<HotelVO> getAll_TO_VIEW(){
		return dao.getAll_TO_VIEW();
	}
	
	//取得需要審核2
	public List<HotelVO> getAll_NEED_CHECK(){
		
		return dao.getAll_NEED_CHECK();	
	}
	//新增廠商會員
	public HotelVO addHotel(String hotelType,String hotelName,String hotelTaxId,byte[] hotelRegisterPic
			,String hotelCity,String hotelCounty,String hotelRoad,String hotelOwner,String hotelAccount,
			String hotelPwd,String hotelPhone,Double hotelLon,Double hotelLat,String hotelIntro,byte[] hotelCoverPic,
			String hotelLink,String hotelStatus,String hotelBlackList,Integer hotelRatingTotal,Integer hotelRatingResult,
			String hotelCreditCardNo,String hotelCreditCheckNo,String hotelCreditDueDate) {
		HotelVO hotelvo = new HotelVO();

		hotelvo.setHotelType(hotelType);
		hotelvo.setHotelName(hotelName);
		hotelvo.setHotelTaxId(hotelTaxId);
		hotelvo.setHotelRegisterPic(hotelRegisterPic);
		hotelvo.setHotelCity(hotelCity);
		hotelvo.setHotelCounty(hotelCounty);
		hotelvo.setHotelRoad(hotelRoad);
		hotelvo.setHotelOwner(hotelOwner);
		hotelvo.setHotelAccount(hotelAccount);
		hotelvo.setHotelPwd(hotelPwd);
		hotelvo.setHotelPhone(hotelPhone);
		hotelvo.setHotelLon(hotelLon);
		hotelvo.setHotelLat(hotelLat);
		hotelvo.setHotelIntro(hotelIntro);
		hotelvo.setHotelLink(hotelLink);
		hotelvo.setHotelCoverPic(hotelCoverPic);
		hotelvo.setHotelStatus(hotelStatus);
		hotelvo.setHotelBlackList(hotelBlackList);
		hotelvo.setHotelRatingTotal(hotelRatingTotal);
		hotelvo.setHotelRatingResult(hotelRatingResult);
		hotelvo.setHotelCreditCardNo(hotelCreditCardNo);
		hotelvo.setHotelCreditCheckNo(hotelCreditCheckNo);
		hotelvo.setHotelCreditDueDate(hotelCreditDueDate);
		
		
		dao.insert(hotelvo);

		return hotelvo;
	}

	//會員修改基本資料哦
	public HotelVO updateBasic(String hotelType,String hotelName,String hotelTaxId,byte[] hotelRegisterPic,
			String hotelCity,String hotelCounty,String hotelRoad,String hotelOwner,String hotelAccount,
			String hotelPwd,String hotelPhone,Double hotelLon,Double hotelLat,String hotelIntro,
			byte[] hotelCoverPic,String hotelLink,String hotelCreditCardNo,String hotelCreditCheckNo,
			String hotelCreditDueDate,String hotelId) {
		HotelVO hotelvo = new HotelVO();

		hotelvo.setHotelType(hotelType);
		hotelvo.setHotelName(hotelName);
		hotelvo.setHotelTaxId(hotelTaxId);
		hotelvo.setHotelRegisterPic(hotelRegisterPic);
		hotelvo.setHotelCity(hotelCity);
		hotelvo.setHotelCounty(hotelCounty);
		hotelvo.setHotelRoad(hotelRoad);
		hotelvo.setHotelOwner(hotelOwner);
		hotelvo.setHotelAccount(hotelAccount);
		hotelvo.setHotelPwd(hotelPwd);
		hotelvo.setHotelPhone(hotelPhone);
		hotelvo.setHotelLon(hotelLon);
		hotelvo.setHotelLat(hotelLat);
		hotelvo.setHotelIntro(hotelIntro);
		hotelvo.setHotelCoverPic(hotelCoverPic);
		hotelvo.setHotelLink(hotelLink);
		hotelvo.setHotelCreditCardNo(hotelCreditCardNo);
		hotelvo.setHotelCreditCheckNo(hotelCreditCheckNo);
		hotelvo.setHotelCreditDueDate(hotelCreditDueDate);
		
		
		dao.update(hotelvo);

		return hotelvo;
	}
	
	//按鍵修改狀態2
	public void update_status(String hotelId, String hotelStatus) {
		dao.update_status(hotelId, hotelStatus);
	}
	
	//更新黑名單
	public void update_hotelBlackList(String hotelId, String hotelBlackList) {
		dao.update_hotelBlackList(hotelId, hotelBlackList);
	}
}
