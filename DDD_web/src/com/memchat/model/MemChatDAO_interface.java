package com.memchat.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.chat.model.ChatVO;
import com.memrep.model.MemRepVO;

public interface MemChatDAO_interface {
//	public static final String tableName = "memChat";
	public void insert(MemChatVO aMemChatVO);
	public void update(MemChatVO aMemChatVO);
	public void delete(String aMemChatId);
	
	public List<MemChatVO> getAll();
	public MemChatVO findByPrimaryKey(String aMemChatId);
	
	
//	public List<MemChatVO> findByMemChatChatId(String aMemChatChatId); // 找一個聊天室中所有對話 - 最後再寫
	public List<MemChatVO> getOldMsgBtwnTwoMems(String aMemChatMemId01, String aMemChatMemId02);
	public String getChatIdBtwenTwoMems(String aMemChatMemId01, String aMemChatMemId02);
//	public ChatVO getChatVOBtwenTwoMems(String aMemChatMemId01, String aMemChatMemId02); // Hibernate
	public List<MemChatVO> getNewestMsgEachChatId(String aMemChatMemId);
//	//萬用查詢
//	public List<MemChatVO> getAll(Map<String, String[]> map); 
	
}
