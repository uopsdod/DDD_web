package com.emp.model;
import java.util.*;


public interface EmpDAO_interface {
	 public List<EmpVO> getAll();
	 public EmpVO getOne(String aEmpId);
	 public void insert(EmpVO aEmpVO);
     public void update(EmpVO aEmpVO);
     public void update_Psw(EmpVO aEmpVO);
     public byte[] getPhoto(String aEmpId);
     

}
