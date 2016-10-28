package com.memchat.model;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Timestamp;

public class MemChatVO implements Serializable {
	// memChatMemId memChatChatId memChatDate 這三個欄位共同組成一個PK
	private String memChatMemId; // NOT NULL
	private String memChatChatId; // NOT NULL
	private Timestamp memChatDate;
	private String memChatContent;
	private byte[] memChatPic;
	
	public String getMemChatMemId() {
		return memChatMemId;
	}
	public void setMemChatMemId(String aMemChatMemId) {
		this.memChatMemId = aMemChatMemId;
	}
	public String getMemChatChatId() {
		return this.memChatChatId;
	}
	public void setMemChatChatId(String aMemChatChatId) {
		this.memChatChatId = aMemChatChatId;
	}
	public Timestamp getMemChatDate() {
		return this.memChatDate;
	}
	public void setMemChatDate(Timestamp aMemChatDate) {
		this.memChatDate = aMemChatDate;
	}
	public String getMemChatContent() {
		return this.memChatContent;
	}
	public void setMemChatContent(String aMemChatContent) {
		this.memChatContent = aMemChatContent;
	}
	public byte[] getMemChatPic() {
		return this.memChatPic;
	}
	public void setMemChatPic(byte[] aMemChatPic) {
		this.memChatPic = aMemChatPic;
	}
}
