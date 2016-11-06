package com.adplan.model;

import java.util.List;

import com.adplan.model.AdPlanVO;

public interface AdPlanDAO_interface {
	 public void insert(AdPlanVO aAdPlanVO);
     public void update(AdPlanVO aAdPlanVO);
     public void delete(String aAdPlanId);
     public AdPlanVO findByPrimaryKey(String aAdPlanId);
     public List<AdPlanVO> getAll();//�q�X�Ҧ�Banner�����
     //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//   public List<AdVO> getAll(Map<String, String[]> map); 
}
