package com.memrep.model;

import java.util.List;
import java.util.Map;

import com.ord.model.OrdVO;

public class MemRepService {
	private MemRepDAO_interface dao;
	public MemRepService(){
		this.dao = new MemRepHibernateDAO();
	}
	public void insert(String aMemRepOrdId, String aMemRepContent){
		MemRepVO memRepVO = new MemRepVO();
		OrdVO memRepOrdVO = new OrdVO();
		memRepOrdVO.setOrdId(aMemRepOrdId);
		
		memRepVO.setMemRepOrdVO(memRepOrdVO);
		memRepVO.setMemRepContent(aMemRepContent);
		this.dao.insert(memRepVO);
	}
	
	public void insert(MemRepVO aMemrepVO){
		this.dao.insert(aMemrepVO);
	}
	public void update(MemRepVO aMemrepVO){
		this.dao.update(aMemrepVO);
	}
	public void delete(String aMemrepId){
		this.dao.delete(aMemrepId);
	}
	public List<MemRepVO> getAll(){
		return this.dao.getAll();
	}
//	public List<MemRepVO> getAllByMemRepStatus(String aMemRepStatus){//0.未審核 1.已審核未通過 2.已審核已通過
//		return this.dao.getAllByMemRepStatus(aMemRepStatus);
//	}
	public MemRepVO findByPrimaryKey(String aMemrepId){
		return this.dao.findByPrimaryKey(aMemrepId);
	}
	
	public List<MemRepVO> getAll(Map<String, String[]> aMap){
		return this.dao.getAll(aMap);
	}
}
