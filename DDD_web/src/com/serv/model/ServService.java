package com.serv.model;

import java.util.List;

public class ServService {
	private ServDAO_interface dao;
	
	public ServService(){
		dao = new ServDAO();
	}
	
	public ServVO addServ(String aServName){
		ServVO servVO = new ServVO();
		
		servVO.setServName(aServName);
		dao.insert(servVO);
		return servVO;
	}
	
	public ServVO updateServ(String aServId, String aServName){
		ServVO servVO = new ServVO();
		
		servVO.setServId(aServId);
		servVO.setServName(aServName);
		dao.update(servVO);
		
		return servVO;
	}
	
	public void deleteServ(String aServId){
		dao.delete(aServId);
	}
	
	public ServVO getOneServ(String aServId){
		return dao.findByPrimaryKey(aServId);
	}
	
	public List<ServVO> getAll(){
		return dao.getAll();
	}
}
