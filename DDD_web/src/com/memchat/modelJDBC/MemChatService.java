package com.memchat.modelJDBC;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class MemChatService {
	private MemChatDAO_interface dao;
	public MemChatService(){
		this.dao = (MemChatDAO_interface) new MemChatJNDIDAO();
	}
	// aMemChatChatId,aMemChatMemId, aMemChatDate, aMemChatContent, aMemChatPic
	public void insert(String aMemChatChatId, String aMemChatMemId, Timestamp aMemChatDate, String aMemChatContent, byte[] aMemChatPic, String aMemChatStatus, String aMemChatToMemId){
		MemChatVO memChatVO = new MemChatVO();
		memChatVO.setMemChatChatId(aMemChatChatId);
		memChatVO.setMemChatMemId(aMemChatMemId);
		memChatVO.setMemChatDate(aMemChatDate);
		memChatVO.setMemChatContent(aMemChatContent);
		memChatVO.setMemChatPic(aMemChatPic);
		memChatVO.setMemChatStatus(aMemChatStatus);
		memChatVO.setMemChatToMemId(aMemChatToMemId);
		
		this.dao.insert(memChatVO);
	}
	
	public void insert(MemChatVO aMemChatVO){
		this.dao.insert(aMemChatVO);
	}
	public void update(MemChatVO aMemChatVO){
		this.dao.update(aMemChatVO);
	}
	public void delete(String aMemChatChatId, String aMemChatMemId, Timestamp aDate){
		this.dao.delete(aMemChatChatId, aMemChatMemId, aDate);
	}
	
	public List<MemChatVO> getAll(){
		return this.dao.getAll();
	}
	public MemChatVO findByPrimaryKey(String aMemChatChatId, String aMemChatMemId, Timestamp aDate){
		return this.dao.findByPrimaryKey(aMemChatChatId, aMemChatMemId, aDate);
	}
	public List<MemChatVO> findByMemChatChatId(String aMemChatChatId){		// 找一個聊天室中所有對話 - 最後再寫
		return this.dao.findByMemChatChatId(aMemChatChatId);
	}
	public List<MemChatVO> getOldMsgBtwnTwoMems(String aMemChatMemId01, String aMemChatMemId02){ //找兩個會員間的歷史對話
		return this.dao.getOldMsgBtwnTwoMems(aMemChatMemId01, aMemChatMemId02);
	}
	public String getChatIdBtwenTwoMems(String aMemChatMemId01, String aMemChatMemId02){ // 找兩個會員間的歷史對話聊天室編號
		return this.dao.getChatIdBtwenTwoMems(aMemChatMemId01, aMemChatMemId02);
	}
	public List<MemChatVO> getNewestMsgEachChatId(String aMemChatMemId){
		return this.dao.getNewestMsgEachChatId(aMemChatMemId);
	}
}
