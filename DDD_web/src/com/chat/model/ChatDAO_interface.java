package com.chat.model;

import java.util.List;

public interface ChatDAO_interface {
	public void insert(ChatVO aChatVO);
	public void update(ChatVO aChatVO);
	public void delete(String aChatId);
	
	public List<ChatVO> getAll();
	public ChatVO findByPrimaryKey(String aChatId);	
}
