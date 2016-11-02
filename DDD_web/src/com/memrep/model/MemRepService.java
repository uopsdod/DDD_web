package com.memrep.model;

import java.util.List;

public class MemRepService {
	private MemRepDAO_interface dao;
	public MemRepService(){
		this.dao = new MemRepJNDIDAO();
	}
	public void insert(String aMemrepOrdId, String aMemrepContent){
		MemRepVO memRepVO = new MemRepVO();
		memRepVO.setMemRepOrdId(aMemrepOrdId);
		memRepVO.setMemRepContent(aMemrepContent);
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
	public List<MemRepVO> getAllByMemRepStatus(String aMemRepStatus){//0.未審核 1.已審核未通過 2.已審核已通過
		return this.dao.getAllByMemRepStatus(aMemRepStatus);
	}
	public MemRepVO findByPrimaryKey(String aMemrepId){
		return this.dao.findByPrimaryKey(aMemrepId);
	}
}
