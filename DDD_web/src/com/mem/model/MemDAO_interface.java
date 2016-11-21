package com.mem.model;

import java.util.*;

public interface MemDAO_interface {
	
	public void insert(MemVO aMemVO);
	public void update(MemVO aMemVO);
	public void delete(String aMemId);
	public MemVO findByPrimaryKey(String aMemId);
	public MemVO findByPrimaryKey_web(String aMemId);
	public List<MemVO>getAll();

	public MemVO memCheck(String aMemAccount, String aMemPsw);
	//--------------------------------------------貴新增
	public List<MemVO>getAll_web();
	public void insert_basic(MemVO aMemVO);
	public void update_card(MemVO aMemVO);
    public void update_iontroduction(MemVO aMemVO);
    public void update_memblackList(String memId,String memBlackList);
    public void update_psw(MemVO aMemVO);
    public void update_memInformation(MemVO aMemVO);
    public byte[] getPhoto(String aMemId);
    public MemVO getUser(String aAccount);
}
