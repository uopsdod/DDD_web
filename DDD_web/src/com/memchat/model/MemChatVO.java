package com.memchat.model;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Timestamp;

import com.chat.model.ChatVO;
import com.mem.model.MemVO;

public class MemChatVO implements Serializable {
	private String memChatId; // PK 
	private ChatVO memChatChatVO; // NOT NULL
	private MemVO memChatMemVO; // NOT NULL

	private Timestamp memChatDate;
	private String memChatContent;
	private String memChatStatus;
	private MemVO memChatToMemVO; // NOT NULL
	public String getMemChatId() {
		return memChatId;
	}
	public void setMemChatId(String memChatId) {
		this.memChatId = memChatId;
	}
	
	public ChatVO getMemChatChatVO() {
		return memChatChatVO;
	}
	public void setMemChatChatVO(ChatVO memChatChatVO) {
		this.memChatChatVO = memChatChatVO;
	}
	public MemVO getMemChatMemVO() {
		return memChatMemVO;
	}
	public void setMemChatMemVO(MemVO memChatMemVO) {
		this.memChatMemVO = memChatMemVO;
	}
	public Timestamp getMemChatDate() {
		return memChatDate;
	}
	public void setMemChatDate(Timestamp memChatDate) {
		this.memChatDate = memChatDate;
	}
	public String getMemChatContent() {
		return memChatContent;
	}
	public void setMemChatContent(String memChatContent) {
		this.memChatContent = memChatContent;
	}
	public String getMemChatStatus() {
		return memChatStatus;
	}
	public void setMemChatStatus(String memChatStatus) {
		this.memChatStatus = memChatStatus;
	}
	public MemVO getMemChatToMemVO() {
		return memChatToMemVO;
	}
	public void setMemChatToMemVO(MemVO memChatToMemVO) {
		this.memChatToMemVO = memChatToMemVO;
	}
	
	// 過渡方法:
	public String getmemChatChatId(){
		return this.getMemChatChatVO().getChatId();
	}
	public void setMemChatChatId(String aChatId){
		ChatVO chatVO = new ChatVO();
		chatVO.setChatId(aChatId);
		this.setMemChatChatVO(chatVO);
	}
	
	public String getMemChatMemId(){
		return this.getMemChatMemVO().getMemId();
	}
	public void setMemChatMemId(String memChatMemId){
		MemVO memVO = new MemVO();
		memVO.setMemId(memChatMemId);
		this.setMemChatMemVO(memVO);
	}
	public String getMemChatToMemId(){
		return this.getMemChatToMemVO().getMemId();
	}
	public void setMemChatToMemId(String aToMemId){
		MemVO toMemVO = new MemVO();
		toMemVO.setMemId(aToMemId);
		this.setMemChatToMemVO(toMemVO);
	}


	
}
