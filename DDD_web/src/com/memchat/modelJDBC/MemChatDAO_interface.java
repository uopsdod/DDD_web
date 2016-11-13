package com.memchat.modelJDBC;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.memrep.model.MemRepVO;

public interface MemChatDAO_interface {
	public static final String tableName = "memChat";
	public void insert(MemChatVO aMemChatVO);
	public void update(MemChatVO aMemChatVO);
	public void delete(String aMemChatChatId, String aMemChatMemId, Timestamp aDate);
	
	public List<MemChatVO> getAll();
	public MemChatVO findByPrimaryKey(String aMemChatChatId, String aMemChatMemId, Timestamp aDate);
	public List<MemChatVO> findByMemChatChatId(String aMemChatChatId); // 找一個聊天室中所有對話 - 最後再寫
	//萬用查詢
	public List<MemChatVO> getAll(Map<String, String[]> map); 
}
