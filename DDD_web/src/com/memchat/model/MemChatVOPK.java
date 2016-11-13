package com.memchat.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Embeddable;

@Embeddable
public class MemChatVOPK implements Serializable {
	protected String memChatMemId; // NOT NULL
	protected String memChatChatId; // NOT NULL
	protected Timestamp memChatDate;

	public MemChatVOPK() {
	}

	public MemChatVOPK(String aMemChatMemId, String aMemChatChatId, Timestamp aMemChatDate) {
		this.memChatMemId = aMemChatMemId;
		this.memChatChatId = aMemChatChatId;
		this.memChatDate = aMemChatDate;
	}

	// equals, hashCode
	@Override
	public boolean equals(Object other) {
		if (this == other) { // auto-upcasting Object to the corresponding Class
			return true; // In this case, cast Object to Cat. Then, compare the
							// content
		}
		if (other instanceof MemChatVOPK) { // object instanceof class
			MemChatVOPK otherPK = (MemChatVOPK) other;
			return this.getMemChatMemId().equals(otherPK.getMemChatMemId())
					&& this.getMemChatChatId().equals(otherPK.getMemChatChatId())
					&& this.getMemChatDate().getTime() == otherPK.getMemChatDate().getTime();
		}
		return false;
	}

	@Override
	public int hashCode() { // must override hashCode in every class that overrides equals
		return this.getMemChatMemId().hashCode() + this.getMemChatChatId().hashCode() + this.getMemChatDate().hashCode();
	}

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

}