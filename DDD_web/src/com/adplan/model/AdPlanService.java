package com.adplan.model;

import java.util.List;

import com.adplan.model.*;
import com.adplan.model.AdPlanVO;

public class AdPlanService {//跟DAO要資料 然後DAO再回傳資料給自己

	private AdPlanDAO_interface dao;

	public AdPlanService() {
		dao = new AdPlanDAO();
	}

	public AdPlanVO addAd(String aAdPlanName,java.sql.Date aAdPlanStartDate, java.sql.Date aAdPlanEndDate,
			Integer aAdPlanPrice, Integer aAdPlanRemainNo) {

		AdPlanVO adPlanVO = new AdPlanVO();

		adPlanVO.setAdPlanName(aAdPlanName);
		adPlanVO.setAdPlanStartDate(aAdPlanStartDate);
		adPlanVO.setAdPlanEndDate(aAdPlanEndDate);
		adPlanVO.setAdPlanPrice(aAdPlanPrice);
		adPlanVO.setAdPlanRemainNo(aAdPlanRemainNo);
		
		dao.insert(adPlanVO);

		return adPlanVO;
	}

	public AdPlanVO updateAd(String aAdPlanName,java.sql.Date aAdPlanStartDate, java.sql.Date aAdPlanEndDate,
			Integer aAdPlanPrice, Integer aAdPlanRemainNo) {

		AdPlanVO adPlanVO = new AdPlanVO();

		adPlanVO.setAdPlanName(aAdPlanName);
		adPlanVO.setAdPlanStartDate(aAdPlanStartDate);
		adPlanVO.setAdPlanEndDate(aAdPlanEndDate);
		adPlanVO.setAdPlanPrice(aAdPlanPrice);
		adPlanVO.setAdPlanRemainNo(aAdPlanRemainNo);
		dao.update(adPlanVO);

		return adPlanVO;
	}

	public void deleteAd(String aAdPlanId) {
		dao.delete(aAdPlanId);
	}

	public AdPlanVO getOneAd(String aAdPlanId) {
		return dao.findByPrimaryKey(aAdPlanId);
	}

	public List<AdPlanVO> getAll() {
		return dao.getAll();
	}
}
