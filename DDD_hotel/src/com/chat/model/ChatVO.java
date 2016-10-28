package com.chat.model;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.Date;

public class ChatVO implements Serializable {
	private String chatId; // NOT NULL
	private String chatName;
	
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
	
}
