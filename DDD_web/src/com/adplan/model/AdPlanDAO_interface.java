package com.adplan.model;

import java.util.List;

import com.adplan.model.AdPlanVO;

public interface AdPlanDAO_interface {
	 public void insert(AdPlanVO aAdPlanVO);
     public void update(AdPlanVO aAdPlanVO);
     public void delete(String aAdPlanId);
     public AdPlanVO findByPrimaryKey(String aAdPlanId);
     public List<AdPlanVO> getAll();//秀出所有Banner的資料
     //萬用複合查詢(傳入參數型態Map)(回傳 List)
//   public List<AdVO> getAll(Map<String, String[]> map); 
}
