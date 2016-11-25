package com.ad.model;

import java.util.List;


public interface AdDAO_interface {
	  public void insert(AdVO aAdVO);
      public void update(AdVO aAdVO);
      public void delete(String aAdid);
      public AdVO findByPrimaryKey(String aAdid);
      public List<AdVO> getAll();
      public List<AdVO> getAllByHotelId(String aAdHotelId);//�q�X�Ҧ�Banner�����
      //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//    public List<AdVO> getAll(Map<String, String[]> map); 
}
