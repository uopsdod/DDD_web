package com.livecond.model;

import java.util.List;

public class LiveCondService {
	private LiveCondDAO_interface dao;
	public LiveCondService(){
		this.dao = new LiveCondJDNIDAO();
	}
	// aLiveCondId aLiveCondName
	public void insert(String aLiveCondId, String aLiveCondName){
		LiveCondVO liveCondVO = new LiveCondVO();
		liveCondVO.setLiveCondId(aLiveCondId);
		liveCondVO.setLiveCondName(aLiveCondName);
		
		this.dao.insert(liveCondVO);
	}
	
	public void insert(LiveCondVO aLiveCondVO){
		this.dao.insert(aLiveCondVO);
	}
	public void update(LiveCondVO aLiveCondVO){
		this.dao.update(aLiveCondVO);
	}
	public void delete(String aLiveCondId){
		this.dao.delete(aLiveCondId);
	}
	public List<LiveCondVO> getAll(){
		return this.dao.getAll();
	}
	public LiveCondVO findByPrimaryKey(String aLiveCondId){
		return this.dao.findByPrimaryKey(aLiveCondId);
	}
}
