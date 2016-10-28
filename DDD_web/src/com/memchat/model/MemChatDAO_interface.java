package com.memchat.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface MemChatDAO_interface {
	public void insert(MemChatVO aMemChatVO);
	public void update(MemChatVO aMemChatVO);
	public void delete(String aMemChatChatId, String aMemChatMemId, Timestamp aDate);
	
	public List<MemChatVO> getAll();
	public MemChatVO findByPrimaryKey(String aMemChatChatId, String aMemChatMemId, Timestamp aDate);
	public List<MemChatVO> findByMemChatChatId(String aMemChatChatId); // 找一個聊天室中所有對話 - 最後再寫
}
