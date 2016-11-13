package com.memchat.modelJDBC;

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
	private String memChatStatus;
	private String memChatToMemId;
	public String getMemChatMemId() {
		return memChatMemId;
	}
	public void setMemChatMemId(String memChatMemId) {
		this.memChatMemId = memChatMemId;
	}
	public String getMemChatChatId() {
		return memChatChatId;
	}
	public void setMemChatChatId(String memChatChatId) {
		this.memChatChatId = memChatChatId;
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
	public byte[] getMemChatPic() {
		return memChatPic;
	}
	public void setMemChatPic(byte[] memChatPic) {
		this.memChatPic = memChatPic;
	}
	public String getMemChatStatus() {
		return memChatStatus;
	}
	public void setMemChatStatus(String memChatStatus) {
		this.memChatStatus = memChatStatus;
	}
	public String getMemChatToMemId() {
		return memChatToMemId;
	}
	public void setMemChatToMemId(String memChatToMemId) {
		this.memChatToMemId = memChatToMemId;
	}
	
	
}
