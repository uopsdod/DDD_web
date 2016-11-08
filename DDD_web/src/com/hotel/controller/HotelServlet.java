package com.hotel.controller;

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

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.hotel.model.HotelService;
import com.hotel.model.HotelVO;

/**
 * Servlet implementation class HotelServlet
 */

public class HotelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String action = request.getParameter("action");
		
		
		if ("getOne".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = request.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】		

			
			try {
				/***************************1.接收請求參數****************************************/
				
				String hotelId = new String(request.getParameter("hotelId"));
				
				/***************************2.開始查詢資料****************************************/
				HotelService hotelSvc = new HotelService();
				HotelVO hotelvo = hotelSvc.getOne(hotelId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				request.setAttribute("hotelvo", hotelvo);         // 資料庫取出的empVO物件,存入req
				String url = "/backend/hotel/getOneHotel.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(request, response);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/backend/hotel/getOneHotel.jsp");
				failureView.forward(request, response);
			}
		}
		
		
		 if ("update_blacklist".equals(action)) { // 來自listAllEmp.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				request.setAttribute("errorMsgs", errorMsgs);
				
				String requestURL = request.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
					
				
				try {
					/***************************1.接收請求參數****************************************/
					String hotelId=request.getParameter("hotelId").trim();
					String hotelBlackList = request.getParameter("hotelBlackList"); 

					/***************************2.開始查詢資料****************************************/
					if(hotelBlackList.equals("0")){
						HotelService hotelSvc = new HotelService();
						hotelSvc.update_hotelBlackList(hotelId,"1");
					}
					
					HotelService hotelSvc = new HotelService();
					HotelVO hotelvo = hotelSvc.getOne(hotelId);
									
									
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					request.setAttribute("hotelvo", hotelvo);         // 資料庫取出的empVO物件,存入req
					String url = requestURL;
					RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
					successView.forward(request, response);

					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					RequestDispatcher failureView = request
							.getRequestDispatcher("requestURL");
					failureView.forward(request, response);
				}
			}
		 
		 if ("update_blacklist1".equals(action)) { // 來自listAllEmp.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				request.setAttribute("errorMsgs", errorMsgs);
				
				String requestURL = request.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
					
				
				try {
					/***************************1.接收請求參數****************************************/
					String hotelId=request.getParameter("hotelId").trim();
					String hotelBlackList = request.getParameter("hotelBlackList"); 

					/***************************2.開始查詢資料****************************************/
				
					if(hotelBlackList.equals("1")){
						HotelService hotelSvc = new HotelService();
						hotelSvc.update_hotelBlackList(hotelId,"0");
					}
					HotelService hotelSvc = new HotelService();
					HotelVO hotelvo = hotelSvc.getOne(hotelId);
									
									
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					request.setAttribute("hotelvo", hotelvo);         // 資料庫取出的empVO物件,存入req
					String url = requestURL;
					RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
					successView.forward(request, response);

					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					RequestDispatcher failureView = request
							.getRequestDispatcher("requestURL");
					failureView.forward(request, response);
				}
			}
		 
		
		 
		 if ("secess".equals(action)) { // 來自listAllEmp.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				request.setAttribute("errorMsgs", errorMsgs);
				
				String requestURL = request.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
					
				
				try {
					/***************************1.接收請求參數****************************************/
					String hotelId=request.getParameter("hotelId").trim();
					String hotelStatus = request.getParameter("hotelStatus"); 

					/***************************2.開始查詢資料****************************************/
				//0.未審核 1.已審核未通過 2.審核通過
					if(hotelStatus.equals("0")){
						HotelService hotelSvc = new HotelService();
						hotelSvc.update_status(hotelId,"2");
					}
					
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
					
					String url = requestURL;
					RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
					successView.forward(request, response);

					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					RequestDispatcher failureView = request
							.getRequestDispatcher("requestURL");
					failureView.forward(request, response);
				}
			}
		 
		 if ("nosecess".equals(action)) { // 來自listAllEmp.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				request.setAttribute("errorMsgs", errorMsgs);
				
				String requestURL = request.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
					
				
				try {
					/***************************1.接收請求參數****************************************/
					String hotelId=request.getParameter("hotelId").trim();
					String hotelStatus = request.getParameter("hotelStatus"); 

					/***************************2.開始查詢資料****************************************/
				//0.未審核 1.已審核未通過 2.審核通過
					if(hotelStatus.equals("0")){
						HotelService hotelSvc = new HotelService();
						hotelSvc.update_status(hotelId,"1");
					}
					
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
					
					String url = requestURL;
					RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
					successView.forward(request, response);

					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					RequestDispatcher failureView = request
							.getRequestDispatcher("requestURL");
					failureView.forward(request, response);
				}
			}
		
	}

}
