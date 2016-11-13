package com.memchat.modelHibernate;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.EmbeddedId;

import org.hibernate.annotations.Entity;

@Entity
public class MemChatVO implements Serializable {
	// memChatMemId memChatChatId memChatDate 這三個欄位共同組成一個PK
    @EmbeddedId
    private MemChatVOPK memChatVOPK;
	private String memChatContent;
	private byte[] memChatPic;
	private String memChatStatus;

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
	public MemChatVOPK getMemChatVOPK() {
		return memChatVOPK;
	}
	public void setMemChatVOPK(MemChatVOPK memChatVOPK) {
		this.memChatVOPK = memChatVOPK;
	}
	
	
	
}
