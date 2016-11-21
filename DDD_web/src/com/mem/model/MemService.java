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
	
	public MemVO addNewMem(MemVO memVO){
		
		memVO.setMemAccount(memVO.getMemAccount());
		memVO.setMemPsw(memVO.getMemPsw());
		memVO.setMemName(memVO.getMemName());
		memVO.setMemGender(memVO.getMemGender());
		memVO.setMemTwId(memVO.getMemTwId());
		memVO.setMemBirthDate(memVO.getMemBirthDate());
		memVO.setMemPhone(memVO.getMemPhone());
		memVO.setMemLiveBudget(memVO.getMemLiveBudget());
		memVO.setMemIntro(memVO.getMemIntro());
		memVO.setMemProfile(memVO.getMemProfile());
		memVO.setMemBlackList(memVO.getMemBlackList());
		memVO.setMemCreditCardNo(memVO.getMemCreditCardNo());
		memVO.setMemCreditCheckNo(memVO.getMemCreditCheckNo());
		memVO.setMemCreditDueDate(memVO.getMemCreditDueDate());
		dao.insert(memVO);
		
		return memVO;
	}
	
	public MemVO updateAndroidMeminfo(MemVO memVO){
		memVO.setMemName(memVO.getMemName());
		memVO.setMemLiveBudget(memVO.getMemLiveBudget());
		memVO.setMemIntro(memVO.getMemIntro());
		memVO.setMemGender(memVO.getMemGender());
		dao.update(memVO);
		
		return memVO;
	}

	//-----------------------------------------------------貴新增
		public List<MemVO> getAll_web(){
			return dao.getAll_web();
		}
		//取衣
		public MemVO getOneMem_web(String aMemId){
			return dao.findByPrimaryKey_web(aMemId);
		}	
		//註冊
		public MemVO insert_basic(String memAccount,String memPsw,String memName,String memGender, String memTwId,
				java.sql.Date memBirthDate,String memPhone,String memBlackList) { 
			
			MemVO memVO = new MemVO();
			 memVO.setMemAccount(memAccount);
			 memVO.setMemPsw(memPsw);
			 memVO.setMemName(memName);
			 memVO.setMemGender(memGender);
			 memVO.setMemTwId(memTwId);
			 memVO.setMemBirthDate(memBirthDate);
			 memVO.setMemPhone(memPhone); 
			 memVO.setMemBlackList(memBlackList);	
			
			dao.insert_basic(memVO);
			return memVO;
		}
		//信用卡更新
		public MemVO update_card(String memCreditCardNo,String memCreditCheckNo,String memCreditDueDate,String memId) {
			MemVO memVO = new MemVO();
			 memVO.setMemCreditCardNo(memCreditCardNo);
			 memVO.setMemCreditCheckNo(memCreditCheckNo);
			 memVO.setMemCreditDueDate(memCreditDueDate);
			 memVO.setMemId(memId);
			dao.update_card(memVO);
			return memVO;
		}
		//簡介預算照片
		public MemVO update_iontroduction(Integer memLiveBudget,String memIntro,byte[] memProfile,String memId) {
			MemVO memVO = new MemVO();
			 memVO.setMemIntro(memIntro);
			 memVO.setMemLiveBudget(memLiveBudget);
			 if(memProfile.length==0){
				 	memVO.setMemProfile(dao.getPhoto(memId));
				}else{
					memVO.setMemProfile(memProfile);
				}	
			 memVO.setMemId(memId);
			dao.update_iontroduction(memVO);
			return memVO;
			
		}
		//更新黑名單
		public void update_memblackList(String memId,String memBlackList) {
			dao.update_memblackList(memId,memBlackList);
		}
		//更新密碼
		public MemVO update_psw(String memPsw,String memId) {
			MemVO memVO = new MemVO();
			 memVO.setMemPsw(memPsw);
			 
			 memVO.setMemId(memId);
			dao.update_psw(memVO);
			return memVO;
		}
		//更新個人資料
		public MemVO update_memInformation(String memName,String memTwId,java.sql.Date memBirthDate,String memPhone,String memId,String memGender) {
			MemVO memVO = new MemVO();
			 memVO.setMemName(memName);
			 memVO.setMemGender(memGender);
			 memVO.setMemTwId(memTwId);
			 memVO.setMemBirthDate(memBirthDate);
			 memVO.setMemPhone(memPhone);
			 memVO.setMemId(memId);
			dao.update_memInformation(memVO);
			
			return memVO;
		}
		//帳戶比對
		public MemVO getUser(String aAccount) {
			MemVO memVO = new MemVO();
			memVO = dao.getUser(aAccount);
			return memVO;		
		}
}
