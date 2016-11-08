package com.ad.model;

import java.util.List;


public interface AdDAO_interface {
	  public void insert(AdVO aAdVO);
      public void update(AdVO aAdVO);
      public void delete(String aAdid);
      public AdVO findByPrimaryKey(String aAdid);
      public List<AdVO> getAll();//秀出所有Banner的資料
      //萬用複合查詢(傳入參數型態Map)(回傳 List)
//    public List<AdVO> getAll(Map<String, String[]> map); 
}
