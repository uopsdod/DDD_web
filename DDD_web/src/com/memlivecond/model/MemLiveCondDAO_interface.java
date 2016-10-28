package com.memlivecond.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface MemLiveCondDAO_interface {
	public void insert(MemLiveCondVO aMemLiveCondVO);
	public void update(MemLiveCondVO aMemLiveCondVO);
	public void delete(String aMemLiveCondLiveCondId, String aMemLiveCondMemId);
	
	public List<MemLiveCondVO> getAll();
	public MemLiveCondVO findByPrimaryKey(String aMemLiveCondLiveCondId, String aMemLiveCondMemId);
	public List<MemLiveCondVO> findByMemLiveCondMemId(String aMemLiveCondMemId); // 找一個會員所有的期望被什麼樣的人找的條件
}
