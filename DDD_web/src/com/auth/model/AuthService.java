package com.auth.model;

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

	public AuthService updateAuthEmp(String empAuthEmpId, String[] authId) {
		AuthService authService = new AuthService();
		authService.getAuthsByEmpId(empAuthEmpId);

		dao.update(empAuthEmpId, authId);
		return authService;
	}

	private void update(String empAuthEmpId, String[] authId) {
		// TODO Auto-generated method stub

	}
}
