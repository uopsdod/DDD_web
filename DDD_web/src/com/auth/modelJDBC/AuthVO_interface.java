package com.auth.modelJDBC;

import java.util.List;


public interface AuthVO_interface {
	public List<AuthVo> getAll();
	public List<String> getAuthsByEmpId(String empAuthEmpId);
	public void update(String empAuthEmpId, String[] empAuthList);
	
	
}
