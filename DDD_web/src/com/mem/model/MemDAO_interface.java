package com.mem.model;

import java.util.*;

public interface MemDAO_interface {
	
	public void insert(MemVO aMemVO);
	public void update(MemVO aMemVO);
	public void delete(String aMemId);
	public MemVO findByPrimaryKey(String aMemId);
	public List<MemVO>getAll();
	public MemVO memCheck(String aMemAccount, String aMemPsw);
	
}
