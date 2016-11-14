package com.chat.model;

import java.util.List;
import java.util.Map;

import com.memchat.model.MemChatVO;

public interface ChatDAO_interface {
	public void insert(ChatVO aChatVO);
	public void update(ChatVO aChatVO);
	public void delete(String aChatId);
	
	public List<ChatVO> getAll();
	public ChatVO findByPrimaryKey(String aChatId);	
	public void insertWithMemChats(ChatVO chatVO , List<MemChatVO> list);
	
	// 萬用查詢
	public List<ChatVO> getAll(Map<String, String[]> aMap);
	
}
