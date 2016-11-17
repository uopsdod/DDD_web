package com.chat.model;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import com.memchat.model.MemChatVO;
import com.ord.model.OrdVO;

public class ChatVO implements Serializable {
	private String chatId; // NOT NULL
	private String chatName;
	private Set<MemChatVO> chatMemChats = new LinkedHashSet<MemChatVO>();
	
	public String getChatId() {
		return this.chatId;
	}
	public void setChatId(String aChatId) {
		this.chatId = aChatId;
	}
	public String getChatName() {
		return this.chatName;
	}
	public void setChatName(String aChatName) {
		this.chatName = aChatName;
	}
	public Set<MemChatVO> getChatMemChats() {
		return chatMemChats;
	}
	public void setChatMemChats(Set<MemChatVO> chatMemChats) {
		this.chatMemChats = chatMemChats;
	}
	
	
}
