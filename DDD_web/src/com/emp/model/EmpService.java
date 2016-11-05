package com.emp.model;

import java.util.List;

public class EmpService {
	private EmpDAO_interface dao;
	
	public EmpService() {
		dao = new EmpDAO();
	}
	
	public EmpVO addEmp(String aEmpName, String aEmpAccount,String aEmpPwd,String aEmpPhone, java.sql.Date aEmpHireDate,
			 java.sql.Date aEmpFireDate,String aEmpStatus,java.sql.Date aEmpBirthDate,byte[] aEmpProfile, String aEmpROCId, String aEmpAddress) {

		EmpVO empVO = new EmpVO();

		empVO.setEmpName(aEmpName);
		empVO.setEmpAccount(aEmpAccount);
		empVO.setEmpPwd(aEmpPwd);
		empVO.setEmpPhone(aEmpPhone);
		empVO.setEmpHireDate(aEmpHireDate);
		empVO.setEmpFireDate(aEmpFireDate);
		empVO.setEmpStatus(aEmpStatus);
		empVO.setEmpBirthDate(aEmpBirthDate);
		empVO.setEmpProfile(aEmpProfile);
		empVO.setEmpROCId(aEmpROCId);
		empVO.setEmpAddress(aEmpAddress);
		
		dao.insert(empVO);

		return empVO;
	}
	
	public EmpVO updateEmp(String aEmpName, String aEmpAccount, String aEmpPwd,String aEmpPhone, java.sql.Date aEmpHireDate,
			 java.sql.Date aEmpFireDate,String aEmpStatus,java.sql.Date aEmpBirthDate,byte[] aEmpProfile, 
			 String aEmpROCId, String aEmpAddress,String aEmpId) {
			
		EmpVO empVO = new EmpVO();

		empVO.setEmpName(aEmpName);
		empVO.setEmpAccount(aEmpAccount);
		empVO.setEmpPwd(aEmpPwd);
		empVO.setEmpPhone(aEmpPhone);
		empVO.setEmpHireDate(aEmpHireDate);
		empVO.setEmpFireDate(aEmpFireDate);
		empVO.setEmpStatus(aEmpStatus);
		empVO.setEmpBirthDate(aEmpBirthDate);
		if(aEmpProfile.length==0){
			empVO.setEmpProfile(dao.getPhoto(aEmpId));
		}else{
			empVO.setEmpProfile(aEmpProfile);
		}	
		empVO.setEmpROCId(aEmpROCId);
		empVO.setEmpAddress(aEmpAddress);
		empVO.setEmpId(aEmpId);
		dao.update(empVO);

		return empVO;
	}
	
	public EmpVO updateEmpPsw(String aEmpPwd,String aEmpId) {

		EmpVO empVO = new EmpVO();

		empVO.setEmpPwd(aEmpPwd);	 
		empVO.setEmpId(aEmpId);
		dao.update_Psw(empVO);

		return empVO;
	}
	
	public List<EmpVO> getAll() {
		return dao.getAll();
	}
	
	public EmpVO getOne(String aEmpId) {
		EmpVO empVO = new EmpVO();
		empVO = dao.getOne(aEmpId);
		return empVO;		
	}
}
