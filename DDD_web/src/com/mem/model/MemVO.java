package com.mem.model;

import java.sql.*;
import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ord.model.OrdVO;
 
public class MemVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String bs64;
	public String getBs64() {
		return this.bs64;
	}
	public void setBs64(String aBs64) {
		this.bs64 = aBs64;
	}
	//----------------------------上面貴新增
	private String memId;
	private String memAccount;
	private String memPsw;
	private String memName;
	private String memGender;
	private String memTwId;
	private Date memBirthDate;
	private String memPhone;
	private Integer memLiveBudget;
	private String memIntro;
	private byte[] memProfile;
	private String memBlackList;
	private String memCreditCardNo;
	private String memCreditCheckNo;
	private String memCreditDueDate;
	
	@JsonIgnore
	private Set<OrdVO> memOrds = new LinkedHashSet<OrdVO>();
	
	public String getMemId() {
		return this.memId;
	}
	public void setMemId(String aMemId) {
		this.memId = aMemId;
	}
	public String getMemAccount() {
		return this.memAccount;
	}
	public void setMemAccount(String aMemAccount) {
		this.memAccount = aMemAccount;
	}
	public String getMemPsw() {
		return this.memPsw;
	}
	public void setMemPsw(String aMemPsw) {
		this.memPsw = aMemPsw;
	}
	public String getMemName() {
		return this.memName;
	}
	public void setMemName(String aMemName) {
		this.memName = aMemName;
	}
	public String getMemGender() {
		return this.memGender;
	}
	public void setMemGender(String aMemGender) {
		this.memGender = aMemGender;
	}
	public String getMemTwId() {
		return this.memTwId;
	}
	public void setMemTwId(String aMemTwId) {
		this.memTwId = aMemTwId;
	}
	public Date getMemBirthDate() {
		return this.memBirthDate;
	}
	public void setMemBirthDate(Date aMemBirthDate) {
		this.memBirthDate = aMemBirthDate;
	}
	public String getMemPhone() {
		return this.memPhone;
	}
	public void setMemPhone(String aMemPhone) {
		this.memPhone = aMemPhone;
	}
	public Integer getMemLiveBudget() {
		return this.memLiveBudget;
	}
	public void setMemLiveBudget(Integer aMemLiveBudget) {
		this.memLiveBudget = aMemLiveBudget;
	}
	public String getMemIntro() {
		return this.memIntro;
	}
	public void setMemIntro(String aMemIntro) {
		this.memIntro = aMemIntro;
	}
	public byte[] getMemProfile() {
		return this.memProfile;
	}
	public void setMemProfile(byte[] aMemProfile) {
		this.memProfile = aMemProfile;
	}
	public String getMemBlackList() {
		return this.memBlackList;
	}
	public void setMemBlackList(String aMemBlackList) {
		this.memBlackList = aMemBlackList;
	}
	public String getMemCreditCardNo() {
		return this.memCreditCardNo;
	}
	public void setMemCreditCardNo(String aMemCreditCardNo) {
		this.memCreditCardNo = aMemCreditCardNo;
	}
	public String getMemCreditCheckNo() {
		return this.memCreditCheckNo;
	}
	public void setMemCreditCheckNo(String aMemCreditCheckNo) {
		this.memCreditCheckNo = aMemCreditCheckNo;
	}
	public String getMemCreditDueDate() {
		return this.memCreditDueDate;
	}
	public void setMemCreditDueDate(String aMemCreditDueDate) {
		this.memCreditDueDate = aMemCreditDueDate;
	}
	public Set<OrdVO> getMemOrds(){
		return this.memOrds;
	}
	
	public void setMemOrds(Set<OrdVO> aMemOrds){
		this.memOrds = aMemOrds;
	}
	
}
