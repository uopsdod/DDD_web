package com.memchat.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.chat.model.ChatVO;
import com.memchat.model.MemChatHibernateDAO;

public class MemChatService {
	private MemChatDAO_interface dao;
	public MemChatService(){
		this.dao = (MemChatDAO_interface) new MemChatHibernateDAO();
	}
	// aMemChatChatId,aMemChatMemId, aMemChatDate, aMemChatContent, aMemChatPic	
	public void insert(MemChatVO aMemChatVO){
		this.dao.insert(aMemChatVO);
	}
	public void update(MemChatVO aMemChatVO){
		this.dao.update(aMemChatVO);
	}
	public void delete(String aMemChatId){
		this.dao.delete(aMemChatId);
	}
	public List<MemChatVO> getAll(){
		return this.dao.getAll();
	}
	
	public MemChatVO findByPrimaryKey(String aMemChatId){
		return this.dao.findByPrimaryKey(aMemChatId);
	}

//	public List<MemChatVO> findByMemChatChatId(String aMemChatChatId){		// 找一個聊天室中所有對話 - 最後再寫
//		return this.dao.findByMemChatChatId(aMemChatChatId);
//	}
	public List<MemChatVO> getOldMsgBtwnTwoMems(String aMemChatMemId01, String aMemChatMemId02){ //找兩個會員間的歷史對話
		return this.dao.getOldMsgBtwnTwoMems(aMemChatMemId01, aMemChatMemId02);
	}
//	public ChatVO getChatVOBtwenTwoMems(String aMemChatMemId01, String aMemChatMemId02){ // hibernate
//		return this.dao.getChatVOBtwenTwoMems(aMemChatMemId01, aMemChatMemId02);
//	}
	
	public String getChatIdBtwenTwoMems(String aMemChatMemId01, String aMemChatMemId02){ // 找兩個會員間的歷史對話聊天室編號
		return this.dao.getChatIdBtwenTwoMems(aMemChatMemId01, aMemChatMemId02);
	}
	public List<MemChatVO> getNewestMsgEachChatId(String aMemChatMemId){
		return this.dao.getNewestMsgEachChatId(aMemChatMemId);
	}
}
