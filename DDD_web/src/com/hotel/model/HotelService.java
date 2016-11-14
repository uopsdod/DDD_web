package com.hotel.model;

import java.util.List;
import java.util.Set;

import com.emp.model.EmpVO;
import com.ord.model.OrdVO;

public class HotelService {
	private HotelDAO_interface dao;

	public HotelService() {
		dao = new HotelDAO();
	}

	// 取全
	public List<HotelVO> getAll() {
		return dao.getAll();
	}

	// 一筆
	public HotelVO getOne(String aHotelId) {
		return dao.findByPrimaryKey(aHotelId);
	}

	public List<HotelVO> getAll_TO_VIEW() {
		return dao.getAll_TO_VIEW();
	}

	// 取得需要審核2
	public List<HotelVO> getAll_NEED_CHECK() {

		return dao.getAll_NEED_CHECK();
	}

	// 新增廠商會員
	public HotelVO addHotel(String hotelType, String hotelName, String hotelTaxId, byte[] hotelRegisterPic,
			String hotelCity, String hotelCounty, String hotelRoad, String hotelOwner, String hotelAccount,
			String hotelPwd, String hotelPhone, Double hotelLon, Double hotelLat, String hotelIntro, String hotelLink,
			byte[] hotelCoverPic, String hotelStatus, String hotelBlackList, Integer hotelRatingTotal,
			Integer hotelRatingResult, String hotelCreditCardNo, String hotelCreditCheckNo, String hotelCreditDueDate) {
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

	// 會員修改基本資料哦
	public HotelVO updateBasic(String hotelType, String hotelName, String hotelTaxId, byte[] hotelRegisterPic,
			String hotelCity, String hotelCounty, String hotelRoad, String hotelOwner, String hotelAccount,
			String hotelPhone, Double hotelLon, Double hotelLat, String hotelIntro, byte[] hotelCoverPic,
			String hotelLink, String hotelCreditCardNo, String hotelCreditCheckNo, String hotelCreditDueDate,
			String hotelId) {
		HotelVO hotelvo = new HotelVO();

		hotelvo.setHotelType(hotelType);
		hotelvo.setHotelName(hotelName);
		hotelvo.setHotelTaxId(hotelTaxId);

		if (hotelRegisterPic.length == 0) {
			hotelvo.setHotelRegisterPic(dao.getPhoto_register(hotelId));
		} else {
			hotelvo.setHotelRegisterPic(hotelRegisterPic);
		}
		hotelvo.setHotelCity(hotelCity);
		hotelvo.setHotelCounty(hotelCounty);
		hotelvo.setHotelRoad(hotelRoad);
		hotelvo.setHotelOwner(hotelOwner);
		hotelvo.setHotelAccount(hotelAccount);

		hotelvo.setHotelPhone(hotelPhone);
		hotelvo.setHotelLon(hotelLon);
		hotelvo.setHotelLat(hotelLat);
		hotelvo.setHotelIntro(hotelIntro);

		if (hotelCoverPic.length == 0) {
			hotelvo.setHotelCoverPic(dao.getPhoto_cov(hotelId));
		} else {
			hotelvo.setHotelCoverPic(hotelCoverPic);
		}
		hotelvo.setHotelLink(hotelLink);
		hotelvo.setHotelCreditCardNo(hotelCreditCardNo);
		hotelvo.setHotelCreditCheckNo(hotelCreditCheckNo);
		hotelvo.setHotelCreditDueDate(hotelCreditDueDate);
		hotelvo.setHotelId(hotelId);

		dao.update(hotelvo);

		return hotelvo;
	}

	// 未了修改狀態
	public HotelVO updateBasic_hotelStatus(String hotelType, String hotelName, String hotelTaxId,
			byte[] hotelRegisterPic, String hotelCity, String hotelCounty, String hotelRoad, String hotelOwner,
			String hotelAccount, String hotelPhone, Double hotelLon, Double hotelLat, String hotelIntro,
			byte[] hotelCoverPic, String hotelLink, String hotelCreditCardNo, String hotelCreditCheckNo,
			String hotelCreditDueDate, String hotelStatus, String hotelId) {
		HotelVO hotelvo = new HotelVO();

		hotelvo.setHotelType(hotelType);
		hotelvo.setHotelName(hotelName);
		hotelvo.setHotelTaxId(hotelTaxId);

		if (hotelRegisterPic.length == 0) {
			hotelvo.setHotelRegisterPic(dao.getPhoto_register(hotelId));
		} else {
			hotelvo.setHotelRegisterPic(hotelRegisterPic);
		}
		hotelvo.setHotelCity(hotelCity);
		hotelvo.setHotelCounty(hotelCounty);
		hotelvo.setHotelRoad(hotelRoad);
		hotelvo.setHotelOwner(hotelOwner);
		hotelvo.setHotelAccount(hotelAccount);

		hotelvo.setHotelPhone(hotelPhone);
		hotelvo.setHotelLon(hotelLon);
		hotelvo.setHotelLat(hotelLat);
		hotelvo.setHotelIntro(hotelIntro);

		if (hotelCoverPic.length == 0) {
			hotelvo.setHotelCoverPic(dao.getPhoto_cov(hotelId));
		} else {
			hotelvo.setHotelCoverPic(hotelCoverPic);
		}
		hotelvo.setHotelLink(hotelLink);
		hotelvo.setHotelCreditCardNo(hotelCreditCardNo);
		hotelvo.setHotelCreditCheckNo(hotelCreditCheckNo);
		hotelvo.setHotelCreditDueDate(hotelCreditDueDate);
		hotelvo.setHotelStatus(hotelStatus);
		hotelvo.setHotelId(hotelId);

		dao.update_status_1(hotelvo);

		return hotelvo;
	}

	// 按鍵修改狀態2
	public void update_status(String hotelId, String hotelStatus) {
		dao.update_status(hotelId, hotelStatus);
	}

	public void update_pasw(String paw, String hotelId) {
		dao.update_psw(paw, hotelId);
	}

	// 帳戶比對
	public HotelVO getUser(String aAccount) {
		HotelVO hotelVO = new HotelVO();
		hotelVO = dao.getUser(aAccount);
		return hotelVO;
	}

	// 更新黑名單
	public void update_hotelBlackList(String hotelId, String hotelBlackList) {
		dao.update_hotelBlackList(hotelId, hotelBlackList);
	}
	
	
	//嘉鴻你的意思是這樣嗎
		public List<HotelVO> getListBySql(String sql){
					
			return dao.getListBySql(sql);		
		}
	
	
	
	/* 以下是韓哥需要 */
	/* (廠商會員)列出該廠商會員的所有訂單 */
	public Set<OrdVO> getOrdsByHotelId(String aOrdHotelId) {
		return dao.getOrdsByHotelId(aOrdHotelId);
	}

	public HotelVO hotelMemCheck(String aHotelMemAccount, String aHotelMemPwd) {
		HotelVO hotelVO = dao.hotelMemCheck(aHotelMemAccount, aHotelMemPwd);
		if (aHotelMemAccount.equals(hotelVO.getHotelAccount()) && aHotelMemPwd.equals(hotelVO.getHotelPwd())) {
			{
				return hotelVO;
			}
		}
		return null;

	}
}
