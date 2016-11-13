package com.chat.model;

import java.util.List;
import java.util.Map;

public interface ChatDAO_interface {
	public void insert(ChatVO aChatVO);
	public void update(ChatVO aChatVO);
	public void delete(String aChatId);
	
	public List<ChatVO> getAll();
	public ChatVO findByPrimaryKey(String aChatId);	
	// 萬用查詢
	public List<ChatVO> getAll(Map<String, String[]> aMap);
}
