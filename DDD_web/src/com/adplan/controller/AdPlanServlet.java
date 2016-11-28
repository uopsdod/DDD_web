package com.adplan.controller;

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
import java.sql.*;
import com.ad.model.AdService;
import com.ad.model.AdVO;
import com.adplan.model.AdPlanVO;

import com.adplan.model.AdPlanService;

/**
 * Servlet implementation class AdPlanServlet
 */
@WebServlet("/adPlan/adPlan.do")
public class AdPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); /* 處理編碼 */
		String action = request.getParameter("action");
		

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();/* 建錯誤列表 */
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				String adPlanId = new String(request.getParameter("adPlanId"));/* 抓表單送出去的數值 */
				if (adPlanId == null || (adPlanId.trim()).length() == 0) {
					errorMsgs.add("請輸入廣告方案編號");
				}
				if (!errorMsgs.isEmpty()) { /* 如果錯誤不是空的 就傳到jsp那個頁面 */
					RequestDispatcher failureView = request.getRequestDispatcher("/backend/adPlan/select_page.jsp");
					failureView.forward(request, response);/* 跳到jsp頁面 */
					return;// 程式中斷
				}

				AdPlanService adPlanSvc = new AdPlanService();/* 建service物件 */
				AdPlanVO adPlanVO = adPlanSvc.getOneAd(adPlanId);/* 用ID去資料庫找同樣的資料 */
				if (adPlanVO == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) { /* 如果錯誤不是空的 就傳到jsp那個頁面 */
					RequestDispatcher failureView = request.getRequestDispatcher("/backend//adPlan/select_page.jsp");
					failureView.forward(request, response);/* 跳到jsp頁面 */
					return;// 程式中斷
				}

				request.setAttribute("adPlanVO", adPlanVO);/* 把值放進request */

				String url = "/backend//adPlan/listOneAdPlan.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);/* 說要跳到哪 */
				successView.forward(request, response);/* 執行跳轉 */
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/backend//adPlan/select_page.jsp");
				failureView.forward(request, response);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String adPlanId = request.getParameter("adPlanId");/* 對到listall的name */

				//System.out.println("getOne_For_Update gets the " + adPlanId);
				
				/*************************** 2.開始查詢資料 ****************************************/
				AdPlanService adPlanSvc = new AdPlanService();
				AdPlanVO adPlanVO = adPlanSvc.getOneAd(adPlanId);

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				request.setAttribute("adPlanVO", adPlanVO); // 資料庫取出的empVO物件,存入req
				String url = "/backend/adPlan/update_adplancss.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交
																					// update_emp_input.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/backend/adPlan/listAllAdPlan.jsp");
				failureView.forward(request, response);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				String adPlanId = request.getParameter("adPlanId").trim();

				String adPlanName = request.getParameter("adPlanName").trim();
				if (adPlanName==null||adPlanName.trim().length()==0) {
					errorMsgs.add("請選擇方案名稱!");
				}

			
				Date adPlanStartDate = Date.valueOf(request.getParameter("adPlanStartDate").trim());
				Date adPlanEndDate = Date.valueOf(request.getParameter("adPlanEndDate").trim());

				Integer adPlanPrice = new Integer(request.getParameter("adPlanPrice").trim());
				Integer adPlanRemainNo = new Integer(request.getParameter("adPlanRemainNo").trim());

				AdPlanVO adPlanVO = new AdPlanVO();

				adPlanVO.setAdPlanId(adPlanId);
				adPlanVO.setAdPlanName(adPlanName);
				adPlanVO.setAdPlanStartDate(adPlanStartDate);
				adPlanVO.setAdPlanEndDate(adPlanEndDate);
				adPlanVO.setAdPlanPrice(adPlanPrice);
				adPlanVO.setAdPlanRemainNo(adPlanRemainNo);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {

					request.setAttribute("adPlanVO", adPlanVO); // 含有輸入格式錯誤的AdPlanVO物件,也存入req
					RequestDispatcher failureView = request.getRequestDispatcher("/backend/adPlan/update_adplancss.jsp");
					failureView.forward(request, response);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/
				AdPlanService adPlanSvc = new AdPlanService();
				adPlanSvc.updateAd(adPlanId, adPlanName, adPlanStartDate, adPlanEndDate, adPlanPrice, adPlanRemainNo);

				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				request.setAttribute("adPlanVO", adPlanVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/backend/adPlan/adPlanPage.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {/* 不可預期的錯誤 */
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/backend/adPlan/update_adPlan_input.jsp");
				failureView.forward(request, response);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/

				String adPlanName = request.getParameter("adPlanName").trim();
				if (adPlanName==null||adPlanName.trim().length()==0) {
					errorMsgs.add("請選擇方案名稱!");
				}

				if (!errorMsgs.isEmpty()) {

					RequestDispatcher failureView = request.getRequestDispatcher("/backend/adPlan/addAdPlanPage.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}
				Date adPlanStartDate = Date.valueOf(request.getParameter("adPlanStartDate").trim());
				Date adPlanEndDate = Date.valueOf(request.getParameter("adPlanEndDate").trim());
				
				Integer adPlanPrice = new Integer(request.getParameter("adPlanPrice").trim());
				Integer adPlanRemainNo = new Integer(request.getParameter("adPlanRemainNo").trim());

				AdPlanVO adPlanVO = new AdPlanVO();

				adPlanVO.setAdPlanName(adPlanName);
				adPlanVO.setAdPlanStartDate(adPlanStartDate);
				adPlanVO.setAdPlanEndDate(adPlanEndDate);
				adPlanVO.setAdPlanPrice(adPlanPrice);
				adPlanVO.setAdPlanRemainNo(adPlanRemainNo);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {

					request.setAttribute("AdPlanVO", adPlanVO); // 含有輸入格式錯誤的AdPlanVO物件,也存入req
					RequestDispatcher failureView = request.getRequestDispatcher("/backend/adPlan/addAdPlanPage.jsp");
					failureView.forward(request, response);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				AdPlanService adPlanSvc = new AdPlanService();

				System.out.println("adPlanName=" + adPlanName);
				System.out.println("adPlanStartDate=" + adPlanStartDate);
				System.out.println("adPlanEndDate=" + adPlanEndDate);
				System.out.println("adPlanPrice=" + adPlanPrice);
				System.out.println("adPlanRemainNo=" + adPlanRemainNo);

				adPlanVO = adPlanSvc.addAd(adPlanName, adPlanStartDate, adPlanEndDate, adPlanPrice, adPlanRemainNo);

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/				
				
				String url = "/backend/adPlan/adPlanPage.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/backend/adPlan/addAdPlanPage.jsp");
				failureView.forward(request, response);
			}
		}
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String adPlanId = request.getParameter("adPlanId");
				
				/***************************2.開始刪除資料***************************************/
				AdPlanService adPlanSvc = new AdPlanService();
				adPlanSvc.deleteAd(adPlanId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/backend/adPlan/adPlanPage.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(request, response);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/backend/adPlan/listAllAdPlan.jsp");
				failureView.forward(request, response);
			}
		}

	}
}
