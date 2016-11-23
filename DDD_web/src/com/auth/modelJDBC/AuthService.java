package com.auth.modelJDBC;

import java.util.ArrayList;
import java.util.List;

import com.emp.model.EmpVO;

public class AuthService {
	private AuthVO_interface dao;

	public AuthService() {
		dao = new AuthDAO();
	}

	public List<AuthVo> getAll() {
		return dao.getAll();
	}

	public List<String> getAuthsByEmpId(String empAuthEmpId) {
		List<String> authVo = dao.getAuthsByEmpId(empAuthEmpId);// 查某員工權限
		return authVo;
	}

	public List<String> updateAuthEmp(String empAuthEmpId, String[] authId) {
		dao.update(empAuthEmpId, authId);
		List<String> authid =dao.getAuthsByEmpId(empAuthEmpId);
		
		return authid;
	}

	private void update(String empAuthEmpId, String[] authId) {
		// TODO Auto-generated method stub

	}
}
