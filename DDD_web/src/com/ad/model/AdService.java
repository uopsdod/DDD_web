package com.ad.model;

import java.util.List;


import com.ad.model.AdDAO_interface;

public class AdService {//��DAO�n��� �M��DAO�A�^�Ǹ�Ƶ��ۤv

	private AdDAO_interface dao;

	public AdService() {
		dao = new AdDAO();
	}

	public AdVO addAd(String adAdPlanId,String adHotelId,String aAdStatus,java.sql.Date aAdPayDate, byte[] aAdPic,
			String aAdPicContent, Integer aAdHit) {

		AdVO AdVO = new AdVO();

		AdVO.setAdAdPlanId(adAdPlanId);
		AdVO.setAdHotelId(adHotelId);
		AdVO.setAdStatus(aAdStatus);
		AdVO.setAdPayDate(aAdPayDate);
		AdVO.setAdPic(aAdPic);
		AdVO.setAdPicContent(aAdPicContent);
		AdVO.setAdHit(aAdHit);
		
		dao.insert(AdVO);

		return AdVO;
	}

	public AdVO updateAd(String aAdId,String aAdAdPlanId, String aAdStatus,java.sql.Date aAdPayDate, byte[] aAdPic,
			String aAdPicContent, Integer aAdHit) {

		AdVO AdVO = new AdVO();
		AdVO.setAdId(aAdId);
		AdVO.setAdAdPlanId(aAdAdPlanId);
		AdVO.setAdStatus(aAdStatus);
		AdVO.setAdPayDate(aAdPayDate);
		AdVO.setAdPic(aAdPic);
		AdVO.setAdPicContent(aAdPicContent);
		AdVO.setAdHit(aAdHit);
		dao.update(AdVO);

		return AdVO;
	}

	public void deleteAd(String aAdId) {
		dao.delete(aAdId);
	}

	public AdVO getOneAd(String aAdId) {
		return dao.findByPrimaryKey(aAdId);
	}

	public List<AdVO> getAll() {
		return dao.getAll();
	}
	
	public List<AdVO> getAll(String aAdHotelId) {
		return dao.getAllByHotelId(aAdHotelId);
	}
}