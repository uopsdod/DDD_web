package com.memlivecond.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class MemLiveCondService {
	private MemLiveCondDAO_interface dao;
	public MemLiveCondService(){
		this.dao = new MemLiveCondJNDIDAO();
	}
	// aMemLiveCondLiveCondId aMemLiveCondMemId
	public void insert(String aMemLiveCondLiveCondId, String aMemLiveCondMemId){
		MemLiveCondVO memLiveCondVO = new MemLiveCondVO();
		memLiveCondVO.setMemLiveCondLiveCondId(aMemLiveCondLiveCondId);
		memLiveCondVO.setMemLiveCondMemId(aMemLiveCondMemId);
		dao.insert(memLiveCondVO);
	}
	
	public void insert(MemLiveCondVO aMemLiveCondVO){
		this.dao.insert(aMemLiveCondVO);
	}
	public void update(MemLiveCondVO aMemLiveCondVO){
		this.dao.update(aMemLiveCondVO);
	}
	public void delete(String aMemLiveCondLiveCondId, String aMemLiveCondMemId){
		this.dao.delete(aMemLiveCondLiveCondId, aMemLiveCondMemId);
	}
	
	public List<MemLiveCondVO> getAll(){
		return this.dao.getAll();
	}
	public MemLiveCondVO findByPrimaryKey(String aMemLiveCondLiveCondId, String aMemLiveCondMemId){
		return this.dao.findByPrimaryKey(aMemLiveCondLiveCondId, aMemLiveCondMemId);
	}
	public List<MemLiveCondVO> findByMemLiveCondMemId(String aMemLiveCondMemId){// 找一個會員所有的期望被什麼樣的人找的條件
		return this.dao.findByMemLiveCondMemId(aMemLiveCondMemId);
	}
}
