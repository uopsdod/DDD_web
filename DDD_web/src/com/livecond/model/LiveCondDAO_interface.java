package com.livecond.model;

import java.util.List;

public interface LiveCondDAO_interface {
	public void insert(LiveCondVO aLiveCondVO);
	public void update(LiveCondVO aLiveCondVO);
	public void delete(String aLiveCondId);
	
	public List<LiveCondVO> getAll();
	public LiveCondVO findByPrimaryKey(String aLiveCondId);
}
