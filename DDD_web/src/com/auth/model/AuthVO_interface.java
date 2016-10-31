package com.auth.model;

import java.util.List;


public interface AuthVO_interface {
	public List<AuthVo> getAll();  
	public List<AuthVo> getAuthsByEmpId(String empAuthEmpId);
	public void update(String empAuthEmpId, List<AuthVo> empAuthList);
	
	
}
