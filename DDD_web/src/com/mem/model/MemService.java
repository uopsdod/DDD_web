package com.mem.model;

import java.sql.Date;
import java.util.List;

public class MemService {
	private MemDAO_interface dao;
	
	public MemService(){
		dao = new MemDAO();
	}
	
	public MemVO addMem(String aMemAccount, String aMemPsw, String aMemName, String aMemGender, String aMemTwId, Date aMemBirthDate,String aMemPhone, Integer aMemLiveBudget, String aMemIntro, byte[] aMemProfile, String aMemBlackList, String aMemCreditCardNo, String aMemCreditCheckNo, String aMemCreditDueDate){
		MemVO memVO = new MemVO();
		
		memVO.setMemAccount(aMemAccount);
		memVO.setMemPsw(aMemPsw);
		memVO.setMemName(aMemName);
		memVO.setMemGender(aMemGender);
		memVO.setMemTwId(aMemTwId);
		memVO.setMemBirthDate(aMemBirthDate);
		memVO.setMemPhone(aMemPhone);
		memVO.setMemLiveBudget(aMemLiveBudget);
		memVO.setMemIntro(aMemIntro);
		memVO.setMemProfile(aMemProfile);
		memVO.setMemBlackList(aMemBlackList);
		memVO.setMemCreditCardNo(aMemCreditCardNo);
		memVO.setMemCreditCheckNo(aMemCreditCheckNo);
		memVO.setMemCreditDueDate(aMemCreditDueDate);
		dao.insert(memVO);
		
		return memVO;
	}
	
	public MemVO updateMem(String aMemId, String aMemAccount, String aMemPsw, String aMemName, String aMemGender, String aMemTwId, Date aMemBirthDate, String aMemPhone, Integer aMemLiveBudget, String aMemIntro, byte[] aMemProfile, String aMemBlackList, String aMemCreditCardNo, String aMemCreditCheckNo, String aMemCreditDueDate){
		MemVO memVO = new MemVO();
		
		memVO.setMemId(aMemId);
		memVO.setMemAccount(aMemAccount);
		memVO.setMemPsw(aMemPsw);
		memVO.setMemName(aMemName);
		memVO.setMemGender(aMemGender);
		memVO.setMemTwId(aMemTwId);
		memVO.setMemBirthDate(aMemBirthDate);
		memVO.setMemPhone(aMemPhone);
		memVO.setMemLiveBudget(aMemLiveBudget);
		memVO.setMemIntro(aMemIntro);
		memVO.setMemProfile(aMemProfile);
		memVO.setMemBlackList(aMemBlackList);
		memVO.setMemCreditCardNo(aMemCreditCardNo);
		memVO.setMemCreditCheckNo(aMemCreditCheckNo);
		memVO.setMemCreditDueDate(aMemCreditDueDate);
		dao.update(memVO);
		
		return memVO;
	}
	
	public void deleteMem(String aMemId){
		dao.delete(aMemId);
	}
	
	public MemVO getOneMem(String aMemId){
		return dao.findByPrimaryKey(aMemId);
	}
	
	public List<MemVO> getAll(){
		return dao.getAll();
	}
	
	public MemVO memCheck(String aMemAccount, String aMemPsw){
		MemVO memVO = dao.memCheck(aMemAccount, aMemPsw);
		if(aMemAccount.equals(memVO.getMemAccount()) && aMemPsw.equals(memVO.getMemPsw())){
			{
				return memVO;
			}
		}
		return null;
	}
}
