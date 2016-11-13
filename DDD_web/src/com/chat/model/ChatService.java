package com.chat.model;

import java.util.List;
import java.util.Map;

public class ChatService {
	private ChatDAO_interface dao;
	public ChatService(){
		this.dao = new ChatHibernateDAO();
	}
	// aChatId aChatName
	public void insert(String aChatId, String aChatName){
		ChatVO chatVO = new ChatVO();
		chatVO.setChatId(aChatId);
		chatVO.setChatName(aChatName);
		
		this.dao.insert(chatVO);
	}
	
	public void insert(ChatVO aChatVO){
		this.dao.insert(aChatVO);
	}
	public void update(ChatVO aChatVO){
		this.dao.update(aChatVO);
	}
	public void delete(String aChatId){
		this.dao.delete(aChatId);
	}
	
	public List<ChatVO> getAll(){
		return this.dao.getAll();
	}
	
	public List<ChatVO> getAll(Map<String, String[]> aMap){
		return this.dao.getAll(aMap);
	}
	public ChatVO getOneChat(String aChatId){
		return this.dao.findByPrimaryKey(aChatId);
	}
	
	
}
