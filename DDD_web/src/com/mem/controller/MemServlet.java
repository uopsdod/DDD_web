package com.mem.controller;

import java.io.IOException;
import java.io.InputStream;
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

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.hotel.model.HotelService;
import com.hotel.model.HotelVO;
import com.mem.model.MemDAO;
import com.mem.model.MemService;
import com.mem.model.MemVO;


import util.AddressToLat;
import util.Util_psw;
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)


public class MemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	// 查此人的資料
		public MemVO allowUser(String account, String password) {
			MemService memsvc = new MemService();
			MemVO memvO = null;
			memvO = memsvc.getUser(account);

			return memvO;
		}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		session.removeAttribute("account_mem");
		session.removeAttribute("memVO");
		String url = "/frontend_mem/index.jsp";
		RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
		successView.forward(request, response);
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String action = request.getParameter("action");
		System.out.println(action);
		
		
		if ("login".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			// 取帳密
			String account = request.getParameter("UserName");
			if (account.trim().isEmpty()) {
				errorMsgs.add("帳號不能空白");
			}
			String password = request.getParameter("Password");
			if (password.trim().isEmpty()) {
				errorMsgs.add("密碼不能空白");

			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = request.getRequestDispatcher("/frontend_mem/mem/loginOfmember.jsp");
				failureView.forward(request, response);
				return;
			}

			// 查
			MemVO memVO = allowUser(account, password);
			if (memVO == null) {
				errorMsgs.add("帳號或密碼錯誤");
			} else if (memVO.getMemBlackList().equals("1")) {// 0非黑 1黑
				errorMsgs.add("此用戶已被停權");
			} else {
				String paw = Util_psw.key_open(memVO.getMemPsw());// 去掉開鎖
				if (!paw.equals(password)) {
					errorMsgs.add("密碼錯誤");
				} else {
					HttpSession session = request.getSession();
					session.setAttribute("account_mem", account);
					session.setAttribute("memVO", memVO);
					
					try {                                                        
				         String location = (String) session.getAttribute("location_mem");
				         if (location != null) {
				           session.removeAttribute("location_mem");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
				           response.sendRedirect(location);            
				           return;
				         }
				       }catch (Exception ignored) { }

					 response.sendRedirect(request.getContextPath()+"/frontend_mem/index.jsp");  //*工作3: (-->如無來源網頁:則重導至login_success.jsp)
				    }
			}			
			if (!errorMsgs.isEmpty()) {
				request.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = request
						.getRequestDispatcher("/frontend_mem/mem/loginOfmember.jsp");
				failureView.forward(request, response);
				return;
			}
		}
		
		if ("getOne".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			String requestURL = request.getParameter("requestURL"); // 送出修改的來源網頁路徑:
																	// 可能為【/emp/listAllEmp.jsp】
																	// 或
																	// 【/dept/listEmps_ByDeptno.jsp】
																	// 或 【
																	// /dept/listAllDept.jsp】

			try {
				/***************************
				 * 1.接收請求參數
				 ****************************************/

				String memId = new String(request.getParameter("memId"));

				/***************************
				 * 2.開始查詢資料
				 ****************************************/
				MemService memSvc = new MemService();
				MemVO memid = memSvc.getOneMem_web(memId);

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				request.setAttribute("memid", memid); // 資料庫取出的empVO物件,存入req
				String url = "/backend/mem/getonemem.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交
																					// update_emp_input.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/backend/mem/listAllMem.jsp");
				failureView.forward(request, response);
			}
		}
		
		if ("update_blacklist".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			String requestURL = request.getParameter("requestURL"); // 送出修改的來源網頁路徑:
																	// 可能為【/emp/listAllEmp.jsp】
																	// 或
																	// 【/dept/listEmps_ByDeptno.jsp】
																	// 或 【
																	// /dept/listAllDept.jsp】

			try {
				/***************************
				 * 1.接收請求參數
				 ****************************************/
				String memId = request.getParameter("memId").trim();
				String memBlackList = request.getParameter("memBlackList");

				/***************************
				 * 2.開始查詢資料
				 ****************************************/
				if (memBlackList.equals("0")) {
					MemService memSvc = new MemService();
					memSvc.update_memblackList(memId, "1");
				}

				MemService memSvc = new MemService();
				MemVO memvo = memSvc.getOneMem_web(memId);

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				request.setAttribute("memvo", memvo); // 資料庫取出的empVO物件,存入req
				String url = requestURL;
				RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交
																					// update_emp_input.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("requestURL");
				failureView.forward(request, response);
			}
		}
		
		if ("update_blacklist1".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			String requestURL = request.getParameter("requestURL"); // 送出修改的來源網頁路徑:
																	// 可能為【/emp/listAllEmp.jsp】
																	// 或
																	// 【/dept/listEmps_ByDeptno.jsp】
																	// 或 【
																	// /dept/listAllDept.jsp】

			try {
				/***************************
				 * 1.接收請求參數
				 ****************************************/
				String memId = request.getParameter("memId").trim();
				String memBlackList = request.getParameter("memBlackList");

				/***************************
				 * 2.開始查詢資料
				 ****************************************/
				if (memBlackList.equals("1")) {
					MemService memSvc = new MemService();
					memSvc.update_memblackList(memId, "0");
				}

				MemService memSvc = new MemService();
				MemVO memvo = memSvc.getOneMem_web(memId);

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				request.setAttribute("memvo", memvo); // 資料庫取出的empVO物件,存入req
				String url = requestURL;
				RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交
																					// update_emp_input.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("requestURL");
				failureView.forward(request, response);
			}
		}
		
		
		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				String memName = request.getParameter("memName").trim();
				if (memName.trim().isEmpty()) {
					errorMsgs.add("名字必填");
				}
				String memAccount = request.getParameter("memAccount").trim();
				if (memAccount.trim().isEmpty()) {
					errorMsgs.add("信箱不能空白");
				} else {
					if (!memAccount.matches("^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$")) {
						errorMsgs.add("信箱格式錯誤");
					}
				}

				String memPsw_since = request.getParameter("memPsw").trim();
				if (memPsw_since.trim().isEmpty()) {
					errorMsgs.add("密碼不能空白");
				} else {
					if (!memPsw_since.matches("^[a-zA-Z]\\w{6,10}$")) {
						errorMsgs.add("以字母開頭，長度在6-10之間，只能包含字符、數字和下劃線。");
					}
				}		
				String memPsw = Util_psw.key(memPsw_since);
							
				
				String memTwId = request.getParameter("memTwId").trim();
				if(memTwId.trim().isEmpty()){
					errorMsgs.add("ID必填");
				}else{
					if(!memTwId.matches("^[A-Z]{1}[1-2]{1}[0-9]{8}$")){
						errorMsgs.add("請輸入正確的身分證");
					}	
				}

				
				java.sql.Date memBirthDate = null;
				try {
					memBirthDate = java.sql.Date.valueOf(request.getParameter("memBirthDate").trim());
				} catch (IllegalArgumentException e) {
					memBirthDate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入出生日期!");
				}
				
				String memPhone = request.getParameter("memPhone").trim();
				if (memPhone.trim().isEmpty()) {
					errorMsgs.add("電話號碼必填");
				} else {
					if (!memPhone.matches("^[0-9]*$")) {
						errorMsgs.add("電話只能輸入數字");
					}
				}
				
				String memGender = request.getParameter("memGender").trim();
				if (memGender.trim().isEmpty()) {
					errorMsgs.add("請選姓別");
				}
				String memBlackList = request.getParameter("memBlackList").trim();
				
				
				MemVO memVO = new MemVO();
				
				memVO.setMemName(memName);
				memVO.setMemAccount(memAccount);
				memVO.setMemPsw(memPsw);
				memVO.setMemTwId(memTwId);
				memVO.setMemBirthDate(memBirthDate);
				memVO.setMemPhone(memPhone);
				memVO.setMemGender(memGender);
				memVO.setMemBlackList(memBlackList);
				
				MemService memSvc = new MemService();
				List<MemVO> account = memSvc.getAll();
				for (MemVO amem : account) {
					if (amem.getMemAccount().equals(memAccount)) {
						errorMsgs.add("此帳戶已存在");
					}
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = request.getRequestDispatcher("/frontend_mem/mem/registerOfmember.jsp");
					failureView.forward(request, response);
					return;
				}

				/***************************
				 * 2.開始新增資料
				 ***************************************/

				memVO = memSvc.insert_basic(memAccount,memPsw,memName,memGender,memTwId,
						memBirthDate,memPhone,memBlackList);
				// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
				 Util_psw.sendMail(memAccount,memName+"您好歡迎您選擇使用DDD，請查收您的私密資料", "您的帳號為 :"+memAccount+"您的密碼為 : "+memPsw_since+"謝謝您。");
				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/frontend_mem/mem/rigisterSecces.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/frontend_mem/mem/registerOfmember.jsp");
				failureView.forward(request, response);
			}
		}
		
		 if ("updatememIntro".equals(action)) { // 來自listAllEmp.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				request.setAttribute("errorMsgs", errorMsgs);
				
				String requestURL = request.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】

				
				try {
					/***************************1.接收請求參數****************************************/
					String tab=request.getParameter("tab").trim();										
					String memIntro=request.getParameter("memIntro").trim();										
					String memLiveBudget = request.getParameter("memLiveBudget").trim();				
					if(!memLiveBudget.matches("^[0-9]*$")){
						errorMsgs.add("只能輸入數字");
					}	
					Integer memLiveBudget_Int = Integer.parseInt(memLiveBudget);
					Part part = request.getPart("upfile");
					
					InputStream in = part.getInputStream();
					byte[] buf = new byte[in.available()];
					in.read(buf);
					in.close();	
					
					
					String memId = request.getParameter("memId").trim();
					
					MemVO memVO = new MemVO();
					 memVO.setMemIntro(memIntro);
					 memVO.setMemLiveBudget(memLiveBudget_Int);
					 memVO.setMemProfile(buf);
					 memVO.setMemId(memId);
					

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						request.setAttribute("memVO", memVO);
						request.setAttribute("tab", tab);// 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = request
								.getRequestDispatcher("/frontend_mem/mem/member.jsp");
						failureView.forward(request, response);
						return;
					}
					/***************************2.開始查詢資料****************************************/
					MemService memSvc = new MemService();
					memVO = memSvc.update_iontroduction( memLiveBudget_Int, memIntro, buf,memId);
									
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
//					request.setAttribute("empVO", memVO);         // 資料庫取出的empVO物件,存入req
					String url = requestURL;
					RequestDispatcher successView = request.getRequestDispatcher("/frontend_mem/mem/updateSucess.jsp");// 成功轉交 update_emp_input.jsp
					successView.forward(request, response);

					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					RequestDispatcher failureView = request
							.getRequestDispatcher("/frontend_mem/mem/member.jsp");
					failureView.forward(request, response);
				}
			}
		
		 if ("memInformation".equals(action)) { // 來自listAllEmp.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				request.setAttribute("errorMsgssss", errorMsgs);
				
				String requestURL = request.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】

				
				try {
					/***************************1.接收請求參數****************************************/
					String tab=request.getParameter("tab").trim();		
					String memName=request.getParameter("memName").trim();
					if (memName.trim().isEmpty()) {
						errorMsgs.add("名字必填");
					} 
					String memTwId = request.getParameter("memTwId").trim();				
					if(memTwId.trim().isEmpty()){
						errorMsgs.add("ID必填");
					}else{
						if(!memTwId.matches("^[A-Z]{1}[1-2]{1}[0-9]{8}$")){
							errorMsgs.add("請輸入正確的身分證");
						}	
					}
					java.sql.Date memBirthDate = null;
					try {
						memBirthDate = java.sql.Date.valueOf(request.getParameter("memBirthDate").trim());
					} catch (IllegalArgumentException e) {
						memBirthDate=new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入出生日期!");
					}			
					
					String memPhone = request.getParameter("memPhone").trim();
					String memGender = request.getParameter("memGender").trim();
					String memId = request.getParameter("memId").trim();
					
					
					MemVO memVO = new MemVO();
					 memVO.setMemName(memName);
					 memVO.setMemTwId(memTwId);
					 memVO.setMemBirthDate(memBirthDate);
					 memVO.setMemPhone(memPhone);
					 memVO.setMemGender(memGender);
					 memVO.setMemId(memId);
					

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						request.setAttribute("memVO", memVO);
						request.setAttribute("tab", tab);// 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = request
								.getRequestDispatcher("/frontend_mem/mem/member.jsp");
						failureView.forward(request, response);
						return;
					}
					/***************************2.開始查詢資料****************************************/
					MemService memSvc = new MemService();
					memVO = memSvc.update_memInformation( memName, memTwId, memBirthDate,memPhone,memId,memGender);
					
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
//					request.setAttribute("empVO", memVO);         // 資料庫取出的empVO物件,存入req
					String url = "/frontend_mem/mem/updateSucess.jsp";
					RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
					successView.forward(request, response);

					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					RequestDispatcher failureView = request
							.getRequestDispatcher("/frontend_mem/mem/member.jsp");
					failureView.forward(request, response);
				}
			}
		 
		 if ("update_card".equals(action)) { // 來自listAllEmp.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				request.setAttribute("errorMsgsss", errorMsgs);
				
				String requestURL = request.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】

				
				try {
					/***************************1.接收請求參數****************************************/
					String tab=request.getParameter("tab").trim();		
					String memCreditCardNo=request.getParameter("memCreditCardNo").trim();
					if (memCreditCardNo.trim().isEmpty()) {
						errorMsgs.add("卡號必填");
					} 
					String memCreditCheckNo = request.getParameter("memCreditCheckNo").trim();	
					if (memCreditCheckNo.trim().isEmpty()) {
						errorMsgs.add("驗證碼必填");
					} 
					String memCreditDueDate = request.getParameter("memCreditDueDate").trim();				
					if (memCreditDueDate.trim().isEmpty()) {
						errorMsgs.add("有效日期必填");
					} 
					
					String memId = request.getParameter("memId").trim();
					
					
					MemVO memVO = new MemVO();
					 memVO.setMemCreditCardNo(memCreditCardNo);
					 memVO.setMemCreditCheckNo(memCreditCheckNo);
					 memVO.setMemCreditDueDate(memCreditDueDate);
					 memVO.setMemId(memId);
					

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						request.setAttribute("memVO", memVO);
						request.setAttribute("tab", tab);// 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = request
								.getRequestDispatcher("/frontend_mem/mem/member.jsp");
						failureView.forward(request, response);
						return;
					}
					/***************************2.開始查詢資料****************************************/
					MemService memSvc = new MemService();
					memVO = memSvc.update_card( memCreditCardNo, memCreditCheckNo, memCreditDueDate,memId);
					
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
//					request.setAttribute("empVO", memVO);         // 資料庫取出的empVO物件,存入req
					String url = "/frontend_mem/mem/updateSucess.jsp";
					RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
					successView.forward(request, response);

					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					RequestDispatcher failureView = request
							.getRequestDispatcher("/frontend_mem/mem/member.jsp");
					failureView.forward(request, response);
				}
			}
		 
		 if ("update_psw".equals(action)) { // 來自listAllEmp.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				request.setAttribute("errorMsgss", errorMsgs);

				String requestURL = request.getParameter("requestURL"); // 送出修改的來源網頁路徑:
																		// 可能為【/emp/listAllEmp.jsp】
																		// 或
																		// 【/dept/listEmps_ByDeptno.jsp】
																		// 或 【
																		// /dept/listAllDept.jsp】

				try {
					/***************************
					 * 1.接收請求參數
					 ****************************************/
					String tab=request.getParameter("tab").trim();		
					String memId = request.getParameter("memId").trim();
					// 進DB亂數
					String memPsw = request.getParameter("memPsw").trim();
					if (memPsw.trim().isEmpty()) {
						errorMsgs.add("請輸入密碼");
					} else {
						if (!memPsw.matches("^[a-zA-Z]\\w{6,10}$")) {
							errorMsgs.add("以字母開頭，長度在6-10之間，只能包含字符、數字和下劃線。");
						}
					}

					String memPsw_1 = request.getParameter("memPsw_1").trim();
					if (memPsw_1.trim().isEmpty()) {
						errorMsgs.add("請輸入密碼");
					} else {
						if (!memPsw_1.matches("^[a-zA-Z]\\w{6,10}$")) {
							errorMsgs.add("以字母開頭，長度在6-10之間，只能包含字符、數字和下劃線。");
						}
					}
					if (!memPsw_1.equals(memPsw)) {
						errorMsgs.add("密碼必須一致");
					}

					String memPsw_p = Util_psw.key(memPsw);

					if (!errorMsgs.isEmpty()) {
						request.setAttribute("tab", tab);
						RequestDispatcher failureView = request
								.getRequestDispatcher("/frontend_mem/mem/member.jsp");
						failureView.forward(request, response);
						return;
					}

					/***************************
					 * 2.開始查詢資料
					 ****************************************/
					MemService memSvc = new MemService();
					MemVO memVO = memSvc.update_psw(memPsw_p,memId);

					/***************************
					 * 3.查詢完成,準備轉交(Send the Success view)
					 ************/

					String url = "/frontend_mem/mem/updateSucess.jsp";
					RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交
																						// update_emp_input.jsp
					successView.forward(request, response);

					/*************************** 其他可能的錯誤處理 **********************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					RequestDispatcher failureView = request.getRequestDispatcher("/frontend_hotel/hotel/updatesecess.jsp");
					failureView.forward(request, response);
				}
			}
		 
	}
}
