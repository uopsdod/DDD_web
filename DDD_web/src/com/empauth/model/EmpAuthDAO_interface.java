package com.empauth.model;

import java.util.List;


public interface EmpAuthDAO_interface {
	public List<EmpAuthVO> getAll();
	public void delete(String empAuthEmpId);
	
}
