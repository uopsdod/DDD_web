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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.auth.model.AuthService;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;

import util.Util_psw;
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)


public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public EmpVO allowUser(String account, String password) {
		EmpService empsvc = new EmpService();
		EmpVO empVO =null;
		empVO = empsvc.getUser(account);
	   
	      return empVO;
	  }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();;
		session.removeAttribute("account");
		session.removeAttribute("empVO");
		session.removeAttribute("authorityList");
		
		String url = "/backend/emp/login.jsp";
		
			RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(request, response);	
		
		
		
		
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String action = request.getParameter("action");
		
		
		if("login".equals(action)){
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			
			//取帳密
			String account = request.getParameter("account");
			if(account.trim().isEmpty()){
				errorMsgs.add("帳號不能空白");
			}
			String password = request.getParameter("password");
			if(password.trim().isEmpty()){
				errorMsgs.add("密碼不能空白");
				
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = request
						.getRequestDispatcher("/backend/emp/login.jsp");
				failureView.forward(request, response);
				return;
			}
			
			//查
			EmpVO empVO = allowUser(account, password);
			if(empVO==null){
				errorMsgs.add("帳號或密碼錯誤");
			}
			else if(empVO.getEmpStatus().equals("1")){
				errorMsgs.add("此用戶已離職");
			}else{
				String paw = Util_psw.key(empVO.getEmpPwd());
				//取得此會員 查他權限
				String empid = empVO.getEmpId();
				AuthService authsvc = new AuthService();
				List<String> authorityList =authsvc.getAuthsByEmpId(empid);
				
				if (!paw.equals(password)) {
					errorMsgs.add("密碼錯誤");
				} else {
					HttpSession session = request.getSession();
					session.setAttribute("account", account);
					session.setAttribute("empVO", empVO);
					session.setAttribute("authorityList", authorityList);
					
					 try {                                                        
				         String location = (String) session.getAttribute("location_back");
				         if (location != null) {
				           session.removeAttribute("location_back");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
				           response.sendRedirect(location);            
				           return;
				         }
				       }catch (Exception ignored) { }

					 response.sendRedirect(request.getContextPath()+"/backend/emp_index.jsp");  //*工作3: (-->如無來源網頁:則重導至login_success.jsp)
				    }
			}			
			if (!errorMsgs.isEmpty()) {
				request.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = request
						.getRequestDispatcher("/backend/emp/login.jsp");
				failureView.forward(request, response);
				return;
			}
		}
		
		
		
		
		
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
					
					
					String empPwd=Util_psw.random();
					String empPwdForDB  = Util_psw.key(empPwd) ;
					
					
					
//					if(empPwd.trim().isEmpty()){
//						errorMsgs.add("密碼不能空白");
//					}else{
//						if(!empPwd.matches("^[a-zA-Z]\\w{6,10}$")){
//							errorMsgs.add("以字母開頭，長度在6-10之間，只能包含字符、數字和下劃線。");
//						}	
//					}
					
					String	empPhone = request.getParameter("empPhone").trim();
					if(empPhone.trim().isEmpty()){
						errorMsgs.add("電話號碼不能空白");
					}else{
						if(!empPhone.matches("^[0-9]*$")){
							errorMsgs.add("電話只能輸入數字");
						}	
					}

					java.sql.Date empHireDate = null;
					try {
						empHireDate = java.sql.Date.valueOf(request.getParameter("empHireDate").trim());
					} catch (IllegalArgumentException e) {
						
						errorMsgs.add("請輸入日期!");
					}
					
					java.sql.Date empFireDate = null;
					try {
						empFireDate = java.sql.Date.valueOf(request.getParameter("empFireDate").trim());
					} catch (IllegalArgumentException e) {
						
						errorMsgs.add("請輸入日期!");
					}		
					
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
					
					String	empAddress = request.getParameter("empAddress").trim();
					if(empAddress.trim().isEmpty()){
						errorMsgs.add("地址必填");
					}
					

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

					 EmpService empSvc = new EmpService();
						List<EmpVO> account= empSvc.getAll();
						for (EmpVO aEmp : account) {
							if(aEmp.getEmpAccount().equals(empAccount)){
								errorMsgs.add("此帳戶已存在");
							}
						}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						request.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = request
								.getRequestDispatcher("/backend/emp/addEmp.jsp");
						failureView.forward(request, response);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					
					empVO = empSvc.addEmp(empName, empAccount, empPwd, empPhone, empHireDate,empFireDate,empStatus,
							empBirthDate,buf,empROCId,empAddress);
					// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
					Util_psw.sendMail(empAccount, empName+"您好歡迎您加入本公司，請查收您的私密資料", "您的帳號為 : "+empAccount+"您的密碼為 : "+empPwdForDB);
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/backend/emp/listAllEmp.jsp";
					RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(request, response);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = request
							.getRequestDispatcher("/backend/emp/addEmp.jsp");
					failureView.forward(request, response);
				}
			}
		 
		 	
		 if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				request.setAttribute("errorMsgs", errorMsgs);
				
				String requestURL = request.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】		

				
				try {
					/***************************1.接收請求參數****************************************/
					
					String empId = new String(request.getParameter("empId"));
					
					/***************************2.開始查詢資料****************************************/
					EmpService empSvc = new EmpService();
					EmpVO empVO = empSvc.getOne(empId);
									
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					request.setAttribute("empVO", empVO);         // 資料庫取出的empVO物件,存入req
					String url = "/backend/emp/update_emp_input.jsp";
					RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
					successView.forward(request, response);

					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					RequestDispatcher failureView = request
							.getRequestDispatcher("/backend/emp/listAllEmp.jsp");
					failureView.forward(request, response);
				}
			}
		 	
		
		 if ("update".equals(action)) { // 來自listAllEmp.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				request.setAttribute("errorMsgs", errorMsgs);
				
				String requestURL = request.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】

				
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
//					
//					String empPwd=request.getParameter("empPwd").trim();
//					if(empPwd.trim().isEmpty()){
//						errorMsgs.add("密碼不能空白");
//					}else{
//						if(!empPwd.matches("^[a-zA-Z]\\w{6,10}$")){
//							errorMsgs.add("以字母開頭，長度在6-10之間，只能包含字符、數字和下劃線。");
//						}	
//					}
					
					String	empPhone = request.getParameter("empPhone").trim();
					if(empPhone.trim().isEmpty()){
						errorMsgs.add("電話號碼不能空白");
					}else{
						if(!empPhone.matches("^[0-9]*$")){
							errorMsgs.add("電話只能輸入數字");
						}	
					}
					
					java.sql.Date empHireDate = null;
					try {
						empHireDate = java.sql.Date.valueOf(request.getParameter("empHireDate").trim());
					} catch (IllegalArgumentException e) {
						
						errorMsgs.add("請輸入日期!");
					}
					
					java.sql.Date empFireDate = null;
					try {
						empFireDate = java.sql.Date.valueOf(request.getParameter("empFireDate").trim());
					} catch (IllegalArgumentException e) {
						
						errorMsgs.add("請輸入日期!");
					}
					
					String empStatus =request.getParameter("empStatus").trim();
					if(empStatus.trim().isEmpty()){
						errorMsgs.add("狀態碼不能空白");
					}else{
						if(!empStatus.matches("^[0-1]$")){
							errorMsgs.add("狀態碼只能輸入1-2");
						}	
					}
										
					java.sql.Date empBirthDate = null;
					try {
						empBirthDate = java.sql.Date.valueOf(request.getParameter("empBirthDate").trim());
					} catch (IllegalArgumentException e) {
						
						errorMsgs.add("請輸入生日!");
					}
					
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
					
					
					String	empAddress = request.getParameter("empAddress").trim();
					if(empAddress.trim().isEmpty()){
						errorMsgs.add("地址必填");
					}
					
					String empId = request.getParameter("empId").trim();
					
					EmpVO empVO = new EmpVO();
					 empVO.setEmpName(empName);
					 empVO.setEmpAccount(empAccount);
//					 empVO.setEmpPwd(empPwd);
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
								.getRequestDispatcher("/backend/emp/update_emp_input.jsp");
						failureView.forward(request, response);
						return;
					}
					/***************************2.開始查詢資料****************************************/
					EmpService empSvc = new EmpService();
					empVO = empSvc.updateEmp(empName,empAccount,empPhone,empHireDate,
							empFireDate,empStatus,empBirthDate,buf,empROCId,empAddress,empId);
									
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					request.setAttribute("empVO", empVO);         // 資料庫取出的empVO物件,存入req
					String url = requestURL;
					RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
					successView.forward(request, response);

					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					RequestDispatcher failureView = request
							.getRequestDispatcher("/backend/emp/listAllEmp.jsp");
					failureView.forward(request, response);
				}
			}
		 
		 
	}
}
