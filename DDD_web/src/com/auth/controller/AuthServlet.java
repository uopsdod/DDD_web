package com.auth.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.auth.model.AuthService;
import com.auth.model.AuthVo;
import com.emp.model.EmpService;
import com.emp.model.EmpVO;

public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		if ("getOne_For_Auth".equals(action)) { // 來自listAllEmp.jsp的請求

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
				/*************************** 1.接收請求參數 ****************************************/

				String empId = new String(request.getParameter("empId"));

				/*************************** 2.開始查詢資料 ****************************************/
				AuthService authSvc = new AuthService();
				List<String> authList = authSvc.getAuthsByEmpId(empId);

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				request.setAttribute("authList", authList);
				request.setAttribute("empId", empId);
				String url = "/emp/listAllEmp.jsp";

				RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/auth/update_auth.jsp");
				failureView.forward(request, response);
			}
		}

		if ("update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			 String requestURL = request.getParameter("requestURL"); //
			// 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或
			// 【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String[] authId = request.getParameterValues("authIdlIST");

				String authIdlistNo = request.getParameter("authIdlistNo").trim();

				/*************************** 2.開始查詢資料 ****************************************/
				AuthService authSvc = new AuthService();
				authSvc = authSvc.updateAuthEmp(authIdlistNo, authId);

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				// request.setAttribute("authSvc", authSvc); //
				// 資料庫取出的empVO物件,存入req
				String url = "/auth/update_secess.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交
																					// update_emp_input.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/emp/listAllEmp.jsp");
				failureView.forward(request, response);
			}
		}
	}

}
