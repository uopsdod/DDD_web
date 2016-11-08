package com.ad.model;

import java.util.List;


import com.ad.model.AdDAO_interface;

public class AdService {//跟DAO要資料 然後DAO再回傳資料給自己

	private AdDAO_interface dao;

	public AdService() {
		dao = new AdDAO();
	}

	public AdVO addAd(String aAdStatus,java.sql.Date aAdPayDate, byte[] aAdPic,
			String aAdPicContent, Integer aAdHit) {

		AdVO AdVO = new AdVO();

		AdVO.setAdStatus(aAdStatus);
		AdVO.setAdPayDate(aAdPayDate);
		AdVO.setAdPic(aAdPic);
		AdVO.setAdPicContent(aAdPicContent);
		AdVO.setAdHit(aAdHit);
		
		dao.insert(AdVO);

		return AdVO;
	}

	public AdVO updateAd(String aAdStatus,java.sql.Date aAdPayDate, byte[] aAdPic,
			String aAdPicContent, Integer aAdHit) {

		AdVO AdVO = new AdVO();

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
}