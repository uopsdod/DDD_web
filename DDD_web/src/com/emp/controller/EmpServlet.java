package com.emp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.emp.model.EmpJDBCDAO;
import com.emp.model.EmpService;
import com.emp.model.EmpVO;
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)


public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		//我的
		 if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				request.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String empName=request.getParameter("empName").trim();
					if(empName.trim().isEmpty()){
						errorMsgs.add("名字不得為空");
					}		
					
					String empAccount = request.getParameter("empAccount").trim();
					if(empAccount.trim().isEmpty()){
						errorMsgs.add("信箱不能空白");
					}else{
						if(!empAccount.matches("^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$")){
							errorMsgs.add("信箱格式錯誤");
						}	
					}
					
					
					String empPwd=request.getParameter("empPwd").trim();
					if(empPwd.trim().isEmpty()){
						errorMsgs.add("密碼不能空白");
					}else{
						if(!empPwd.matches("^[a-zA-Z]\\w{6,10}$")){
							errorMsgs.add("以字母開頭，長度在6-10之間，只能包含字符、數字和下劃線。");
						}	
					}
					
					String	empPhone = request.getParameter("empPhone").trim();
					if(empPhone.trim().isEmpty()){
						errorMsgs.add("電話號碼不能空白");
					}else{
						if(!empPhone.matches("^[0-9]*$")){
							errorMsgs.add("電話只能輸入數字");
						}	
					}

					java.sql.Date	empHireDate = java.sql.Date.valueOf(request.getParameter("empHireDate").trim());		
					
					String	empStatus = request.getParameter("empStatus").trim();					
					
					java.sql.Date empBirthDate = null;
					try {
						empBirthDate = java.sql.Date.valueOf(request.getParameter("empBirthDate").trim());
					} catch (IllegalArgumentException e) {
						empBirthDate=new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入出生日期!");
					}
					
					
					Part part = request.getPart("upfile1");	
					
					InputStream in = part.getInputStream();
					byte[] buf = new byte[in.available()];
					in.read(buf);
					in.close();				
					
					
					String	empROCId = request.getParameter("empROCId").trim();
					if(empROCId.trim().isEmpty()){
						errorMsgs.add("ID必填");
					}else{
						if(!empROCId.matches("^[A-Z]{1}[1-2]{1}[0-9]{8}$")){
							errorMsgs.add("請輸入正確的ID");
						}	
					}
					
					String empAddress=null;
					try{
						empAddress = request.getParameter("empAddress").trim();
					}catch (NumberFormatException e) {				
						empAddress = "台北市";
						errorMsgs.add("請輸入地址");
					}				
					

					EmpVO empVO = new EmpVO();
					 empVO.setEmpName(empName);
					 empVO.setEmpAccount(empAccount);
					 empVO.setEmpPwd(empPwd);
					 empVO.setEmpPhone(empPhone);
					 empVO.setEmpHireDate(empHireDate);
					 empVO.setEmpStatus(empStatus);
					 empVO.setEmpBirthDate(empBirthDate);
					 empVO.setEmpProfile(buf);
					 empVO.setEmpROCId(empROCId);
					 empVO.setEmpAddress(empAddress);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						request.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = request
								.getRequestDispatcher("/emp/addEmp.jsp");
						failureView.forward(request, response);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					EmpService empSvc = new EmpService();
					empVO = empSvc.addEmp(empName, empAccount, empPwd, empPhone, empHireDate, empStatus,
							empBirthDate,buf,empROCId,empAddress);
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/emp/listAllEmp.jsp";
					RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(request, response);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = request
							.getRequestDispatcher("/emp/addEmp.jsp");
					failureView.forward(request, response);
				}
			}
		 
		 	
		 if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				request.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.接收請求參數****************************************/
					
					String empId = new String(request.getParameter("empId"));
					
					/***************************2.開始查詢資料****************************************/
					EmpService empSvc = new EmpService();
					EmpVO empVO = empSvc.getOne(empId);
									
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					request.setAttribute("empVO", empVO);         // 資料庫取出的empVO物件,存入req
					String url = "/emp/update_emp_input.jsp";
					RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
					successView.forward(request, response);

					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					RequestDispatcher failureView = request
							.getRequestDispatcher("/emp/listAllEmp.jsp");
					failureView.forward(request, response);
				}
			}
		 	
		
		 if ("update".equals(action)) { // 來自listAllEmp.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				request.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.接收請求參數****************************************/
					String empName=request.getParameter("empName").trim();
					if(empName.trim().isEmpty()){
						errorMsgs.add("名字不得為空");
					}		
					
					String empAccount = request.getParameter("empAccount").trim();
					if(empAccount.trim().isEmpty()){
						errorMsgs.add("信箱不能空白");
					}else{
						if(!empAccount.matches("^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$")){
							errorMsgs.add("信箱格式錯誤");
						}	
					}
					
					String empPwd=request.getParameter("empPwd").trim();
					if(empPwd.trim().isEmpty()){
						errorMsgs.add("密碼不能空白");
					}else{
						if(!empPwd.matches("^[a-zA-Z]\\w{6,10}$")){
							errorMsgs.add("以字母開頭，長度在6-10之間，只能包含字符、數字和下劃線。");
						}	
					}
					
					String	empPhone = request.getParameter("empPhone").trim();
					if(empPhone.trim().isEmpty()){
						errorMsgs.add("電話號碼不能空白");
					}else{
						if(!empPhone.matches("^[0-9]*$")){
							errorMsgs.add("電話只能輸入數字");
						}	
					}
					
					java.sql.Date	empHireDate = java.sql.Date.valueOf(request.getParameter("empHireDate").trim());
					java.sql.Date	empFireDate = java.sql.Date.valueOf(request.getParameter("empFireDate").trim());
					
					
					
					
					String empStatus =request.getParameter("empStatus").trim();
					if(empStatus.trim().isEmpty()){
						errorMsgs.add("狀態碼不能空白");
					}else{
						if(!empStatus.matches("^[0-1]$")){
							errorMsgs.add("狀態碼只能輸入1-2");
						}	
					}
					
					java.sql.Date empBirthDate = java.sql.Date.valueOf(request.getParameter("empBirthDate").trim());
					
					InputStream in=null;
					byte[] buf=null ;
					try{
					Part part = request.getPart("upfile1");
					
					 in = part.getInputStream();
					 buf = new byte[in.available()];
					in.read(buf);
					in.close();	
					}catch(Exception e){
						e.getMessage();
					}
					
					String	empROCId = request.getParameter("empROCId").trim();
					if(empROCId.trim().isEmpty()){
						errorMsgs.add("ID必填");
					}else{
						if(!empROCId.matches("^[A-Z]{1}[1-2]{1}[0-9]{8}$")){
							errorMsgs.add("請輸入正確的ID");
						}	
					}
					
					String empAddress=null;
					try{
						empAddress = request.getParameter("empAddress").trim();
					}catch (NumberFormatException e) {				
						empAddress = "台北市";
						errorMsgs.add("請輸入地址");
					}			
					
					String empId = request.getParameter("empId").trim();
					
					EmpVO empVO = new EmpVO();
					 empVO.setEmpName(empName);
					 empVO.setEmpAccount(empAccount);
					 empVO.setEmpPwd(empPwd);
					 empVO.setEmpPhone(empPhone);
					 empVO.setEmpHireDate(empHireDate);
					 empVO.setEmpFireDate(empFireDate);
					 empVO.setEmpStatus(empStatus);
					 empVO.setEmpBirthDate(empBirthDate);
					 empVO.setEmpProfile(buf);
					 empVO.setEmpROCId(empROCId);
					 empVO.setEmpAddress(empAddress);
					 empVO.setEmpId(empId);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						request.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = request
								.getRequestDispatcher("/emp/update_emp_input.jsp");
						failureView.forward(request, response);
						return;
					}
					/***************************2.開始查詢資料****************************************/
					EmpService empSvc = new EmpService();
					empVO = empSvc.updateEmp(empName,empAccount,empPwd,empPhone,empHireDate,
							empFireDate,empStatus,empBirthDate,buf,empROCId,empAddress,empId);
									
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					request.setAttribute("empVO", empVO);         // 資料庫取出的empVO物件,存入req
					String url = "/emp/listAllEmp.jsp";
					RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
					successView.forward(request, response);

					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					RequestDispatcher failureView = request
							.getRequestDispatcher("/emp/listAllEmp.jsp");
					failureView.forward(request, response);
				}
			}
		 
		 
	}
}
