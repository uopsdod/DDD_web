package com.ad.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
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

import com.ad.model.AdService;
import com.ad.model.AdVO;
import com.adplan.model.*;
import com.hotel.model.*;

import util.Util_psw;

import javax.servlet.http.Part;

@WebServlet("/Ad/ad.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024
		* 1024) /* 要傳檔案就要加這個 */
public class AdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); /* 處理編碼 */
		String action = request.getParameter("action");
		System.out.println("action: " + action);

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();/* 建錯誤列表 */
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				String Adid = new String(request.getParameter("Adid"));/* 抓表單送出去的數值 */
				if (Adid == null || (Adid.trim()).length() == 0) {
					errorMsgs.add("請輸入廣告編號");
				}
				if (!errorMsgs.isEmpty()) { /* 如果錯誤不是空的 就傳到jsp那個頁面 */
					RequestDispatcher failureView = request.getRequestDispatcher("/backend/Ad/select_page.jsp");
					failureView.forward(request, response);/* 跳到jsp頁面 */
					return;// 程式中斷
				}

				AdService adSvc = new AdService();/* 建service物件 */
				AdVO adVO = adSvc
						.getOneAd(Adid);/* 用ID去資料庫找同樣的資料 用Adid把AdVO拿出來 */
				if (adVO == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) { /* 如果錯誤不是空的 就傳到jsp那個頁面 */
					RequestDispatcher failureView = request.getRequestDispatcher("/backend/Ad/select_page.jsp");
					failureView.forward(request, response);/* 跳到jsp頁面 */
					return;// 程式中斷
				}

				request.setAttribute("adVO", adVO);/* 把值放進request */

				String url = "/backend/Ad/listOneAd.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);/* 說要跳到哪 */
				successView.forward(request, response);/* 執行跳轉 */
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/backend/Ad/select_page.jsp");
				failureView.forward(request, response);
			}
		}
		
		 if("hit".equals(action)){
			 
			 
			 String Adid = new String (request.getParameter("Adid"));
			 AdService adSvc = new AdService();
			 AdVO adVO = adSvc.getOneAd(Adid);
			
			adSvc.updateAd(adVO.getAdId(), adVO.getAdAdPlanId(), adVO.getAdStatus(), adVO.getAdPayDate(), adVO.getAdPic(), adVO.getAdPicContent(), adVO.getAdHit()+1);
			String url=adVO.getAdPicContent();
			
			   response.sendRedirect(url);
 			 
		 }

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String adid = request.getParameter("adid");/* 對到listall的name */

				/*************************** 2.開始查詢資料 ****************************************/
				AdService adSvc = new AdService();
				AdVO adVO = adSvc.getOneAd(adid);

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				request.setAttribute("adVO", adVO); // 資料庫取出的empVO物件,存入req
				
				String url = "/backend/Ad/update_ad_inputpage.jsp";
//				response.sendRedirect(url);
				RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交
																					// update_emp_input.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/backend/Ad/listAllAd.jsp");
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
				String adId = request.getParameter("adId").trim();
				String adAdPlanId = request.getParameter("adAdPlanId").trim();
				// String adAdPlanId = "10000001";
				String adHotelId = request.getParameter("adHotelId").trim();
				// String adHotelId = "10001";
				String adStatus = request.getParameter("adStatus").trim();
				HotelService hotelSvc = new HotelService();
				HotelVO hotel = (HotelVO) hotelSvc.getOne(adHotelId);
				String hotelAccount = hotel.getHotelAccount();
				String hotelName = hotel.getHotelName();
				java.sql.Date adPayDate = null;/* 要檢查所以先設null */
				try {
					adPayDate = java.sql.Date.valueOf(request.getParameter("adPayDate").trim());

				} catch (IllegalArgumentException e) {
					adPayDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				/*
				 * Part part = request.getPart("adPic"); //
				 * Servlet3.0新增了Part介面，讓我們方便的進行檔案上傳處理
				 * 
				 * InputStream in = part.getInputStream(); byte[] buf = new
				 * byte[in.available()]; in.read(buf); in.close();
				 */
				

				String adPicContent = request.getParameter("adPicContent").trim();
				Integer adHit = null;
				try {
					adHit = new Integer(request.getParameter("adHit").trim());
				} catch (NumberFormatException e) {
					adHit = 0;
					errorMsgs.add("點擊數請填數字.");
				}

				AdVO adVO = new AdVO();
				adVO.setAdId(adId);
				adVO.setAdAdPlanId(adAdPlanId);
				adVO.setAdHotelId(adHotelId);
				adVO.setAdStatus(adStatus);
				adVO.setAdPayDate(adPayDate);
				 //adVO.setAdPic(buf);
				adVO.setAdPicContent(adPicContent);
				adVO.setAdHit(adHit);
				
				
				if (adStatus.equals("3")) {
				AdPlanService adPlanSvc = new AdPlanService();/* 建service物件 */
				AdPlanVO adPlanVO = adPlanSvc.getOneAd(adAdPlanId);/* 用ID去資料庫找同樣的資料 用service去取出物件 */
				int i = adPlanVO.getAdPlanRemainNo();
				i -= 1;
				adPlanSvc.updateAd(adPlanVO.getAdPlanId(), adPlanVO.getAdPlanName(), adPlanVO.getAdPlanStartDate(),
						adPlanVO.getAdPlanEndDate(), adPlanVO.getAdPlanPrice(), i);
				}
				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {/* 輸入錯誤 */
					request.setAttribute("adVO", adVO); // 含有輸入格式錯誤的adVO物件,也存入req
					RequestDispatcher failureView = request.getRequestDispatcher("/backend/Ad/update_ad_inputpage.jsp");

					failureView.forward(request, response);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/

				AdService adSvc = new AdService();
				
				byte[] buf = adSvc.getOneAd(adId).getAdPic();
				adSvc.updateAd(adId, adAdPlanId, adStatus, adPayDate, buf, adPicContent, adHit);
				// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
				if (adStatus.equals("2")) {
					Util_psw.sendMail(hotelAccount, "Dua Dee Duo旅宿平台",
							hotelName + "廠商您好，經審核您的廣告資料符合本公司規定 ，請至Dua Dee Dao平台填寫繳費資料，並於期限內完成繳費 ，再次感謝您的購買。");
				}

				if (adStatus.equals("1")) {
					Util_psw.sendMail(hotelAccount, "Dua Dee Duo旅宿平台",
							hotelName + "廠商您好，經審核您的廣告圖片或圖片說明資料未符合本公司規定 ，請重新上傳相關資料，如有任何疑問歡迎與Dua Dee Dao平台聯絡，再次感謝您的購買。");
				}
				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
			
				//request.setAttribute("adVO", adVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "http://localhost:8081/DDD_web/backend/Ad/listAllAdPage.jsp";
				response.sendRedirect(url);
//				RequestDispatcher successView = request.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {/* 不可預期的錯誤 */
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/backend/Ad/update_ad_inputpage.jsp");
				failureView.forward(request, response);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			// try {
			/***********************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 *************************/

			String adAdPlanId = request.getParameter("adAdPlanId").trim();

			System.out.println("adAdPlanId: " + adAdPlanId);

			// String adAdPlanId = "10000001";
			String adHotelId = request.getParameter("adHotelId").trim();

			/* String adHotelId = "10001"; */
			String adStatus = request.getParameter("adStatus").trim();

			/*
			 * java.sql.Date adPayDate = null; 要檢查所以先設null try { adPayDate =
			 * java.sql.Date.valueOf(request.getParameter("adPayDate").trim());
			 * } catch (IllegalArgumentException e) { adPayDate = new
			 * java.sql.Date(System.currentTimeMillis());
			 * errorMsgs.add("請輸入日期!"); }
			 */
			// java.sql.Date adPayDate =
			// java.sql.Date.valueOf(request.getParameter("adPayDate").trim());
			java.sql.Date adPayDate = new Date(System.currentTimeMillis());

			Part part = request.getPart("adPic"); // Servlet3.0新增了Part介面，讓我們方便的進行檔案上傳處理

			InputStream in = part.getInputStream();
			byte[] buf = new byte[in.available()];
			in.read(buf);
			in.close();

			String adPicContent = request.getParameter("adPicContent").trim();
			Integer adHit = null;
			try {
				adHit = new Integer(request.getParameter("adHit").trim());
			} catch (NumberFormatException e) {
				adHit = 0;
				errorMsgs.add("點擊數請填數字.");
			}

			AdVO adVO = new AdVO();

			adVO.setAdAdPlanId(adAdPlanId);
			adVO.setAdHotelId(adHotelId);
			adVO.setAdStatus(adStatus);
			adVO.setAdPayDate(adPayDate);
			adVO.setAdPic(buf);
			adVO.setAdPicContent(adPicContent);
			adVO.setAdHit(adHit);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				request.setAttribute("adVO", adVO); // 含有輸入格式錯誤的adVO物件,也存入req
				RequestDispatcher failureView = request.getRequestDispatcher("/backend/Ad/addAd.jsp");
				failureView.forward(request, response);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			HotelService hotelsvc = new HotelService();
			HotelVO hotelVO = hotelsvc.getOne(adHotelId);
			String hotelName = hotelVO.getHotelName();
			String hotelAccount = hotelVO.getHotelAccount();

			AdService adSvc = new AdService();
			System.out.println("adAdPlanId=" + adAdPlanId);
			System.out.println("adHotelId=" + adHotelId);
			System.out.println("adStatus=" + adStatus);
			System.out.println("adPayDate=" + adPayDate);
			System.out.println("buf=" + buf);
			System.out.println("adPicContent=" + adPicContent);
			System.out.println("adHit=" + adHit);
			System.out.println("新增一筆 AdServlet");
			adVO = adSvc.addAd(adAdPlanId, adHotelId, adStatus, adPayDate, buf, adPicContent, adHit);/* 以新增置資料庫 */

			/***************************
			 * 3.新增完成,準備轉交(Send the Success view)
			 ***********/
			String url = "http://localhost:8081/DDD_web/frontend_hotel/ad/listAllByHotelIdPage.jsp";/* 要跳轉頁面 */
			response.sendRedirect(url);
			
//			RequestDispatcher successView = request.getRequestDispatcher(url); // 轉交
//																				// 新增成功後轉交listAllEmp.jsp
//			successView.forward(request, response);

			/*************************** 其他可能的錯誤處理 **********************************/
			// } catch (Exception e) {
			// errorMsgs.add(e.getMessage());
			// RequestDispatcher failureView = request
			// .getRequestDispatcher("/Ad/addAd.jsp");
			// failureView.forward(request, response);
			// }
		}
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String adid = request.getParameter("adid");

				/*************************** 2.開始刪除資料 ***************************************/
				AdService adSvc = new AdService();
				adSvc.deleteAd(adid);

				/***************************
				 * 3.刪除完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/backend/Ad/listAllAdPage.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/backend/Ad/listAllAdPagesss.jsp");
				failureView.forward(request, response);
			}
		}

		if ("getOne_For_AdplanId".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();/* 建錯誤列表 */
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				String adPlanId = new String(request.getParameter("adPlanId"));/* 抓表單送出去的數值 */
				if (adPlanId == null || (adPlanId.trim()).length() == 0) {
					errorMsgs.add("請輸入廣告方案編號");
				}
				if (!errorMsgs.isEmpty()) { /* 如果錯誤不是空的 就傳到jsp那個頁面 */
					RequestDispatcher failureView = request.getRequestDispatcher("/backend/Ad/select_page.jsp");
					failureView.forward(request, response);/* 跳到jsp頁面 */
					return;// 程式中斷
				}

				AdPlanService adPlanSvc = new AdPlanService();/* 建service物件 */
				AdPlanVO adPlanVO = adPlanSvc
						.getOneAd(adPlanId);/* 用ID去資料庫找同樣的資料 用service去取出物件 */
				

				if (adPlanVO == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) { /* 如果錯誤不是空的 就傳到jsp那個頁面 */
					RequestDispatcher failureView = request.getRequestDispatcher("/backend/Ad/select_page.jsp");
					failureView.forward(request, response);/* 跳到jsp頁面 */
					return;// 程式中斷
				}

				request.setAttribute("adPlanVO",
						adPlanVO);/* 把值放進request 下一頁就可以拿來用 */

				String url = "/frontend_hotel/ad/clientBannerinfo.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);/* 說要跳到哪 */
				successView.forward(request, response);/* 執行跳轉 */
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/backend/Ad/select_page.jsp");
				failureView.forward(request, response);
			}
		}
		
		if ("updatesStatus".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				String adId = request.getParameter("adId").trim();
				String adAdPlanId = request.getParameter("adAdPlanId").trim();
				// String adAdPlanId = "10000001";
				String adHotelId = request.getParameter("adHotelId").trim();
				// String adHotelId = "10001";
				String adStatus = "3";
				
				System.out.println("adId: " + adId);
				System.out.println("adAdPlanId: " + adAdPlanId);
				System.out.println("adHotelId: " + adHotelId);
				
				Date adPayDate = new java.sql.Date(System.currentTimeMillis());
				int hasin=0;
				byte[] buf=null;

				/*
				 * Part part = request.getPart("adPic"); //
				 * Servlet3.0新增了Part介面，讓我們方便的進行檔案上傳處理
				 * 
				 * InputStream in = part.getInputStream(); byte[] buf = new
				 * byte[in.available()]; in.read(buf); in.close();
				 */

				String adPicContent = request.getParameter("adPicContent").trim();
				Integer adHit = 0;
				

				AdVO adVO = new AdVO();
				adVO.setAdId(adId);
				adVO.setAdAdPlanId(adAdPlanId);
				adVO.setAdHotelId(adHotelId);
				adVO.setAdStatus(adStatus);
				adVO.setAdPayDate(adPayDate);
				 //adVO.setAdPic(buf);
				adVO.setAdPicContent(adPicContent);
				adVO.setAdHit(adHit);
				
				
			/*	if (adStatus.equals("3")) {
				AdPlanService adPlanSvc = new AdPlanService(); 建service物件 
				AdPlanVO adPlanVO = adPlanSvc.getOneAd(adAdPlanId); 用ID去資料庫找同樣的資料 用service去取出物件 
				int i = adPlanVO.getAdPlanRemainNo();
				i -= 1;
				adPlanSvc.updateAd(adPlanVO.getAdPlanId(), adPlanVO.getAdPlanName(), adPlanVO.getAdPlanStartDate(),
						adPlanVO.getAdPlanEndDate(), adPlanVO.getAdPlanPrice(), i);
				}
				*/
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {/* 輸入錯誤 */
					
					request.setAttribute("adVO", adVO); // 含有輸入格式錯誤的adVO物件,也存入req
					RequestDispatcher failureView = request.getRequestDispatcher("/frontend_hotel/ad/pay.jsp");
					failureView.forward(request, response);
					
					return;
					
				}

				/*************************** 2.開始修改資料 *****************************************/

				AdService adSvc = new AdService();
				if(hasin==0){
					 buf = adSvc.getOneAd(adId).getAdPic();
				}
				
				
				

				adSvc.updateAd(adId, adAdPlanId, adStatus, adPayDate, buf, adPicContent, adHit);
			/*	// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
				if (adStatus.equals("2")) {
					Util_psw.sendMail(hotelAccount, "Dua Dee Duo旅宿平台",
							hotelName + "廠商您好，經審核您的廣告資料符合本公司規定 ，請至Dua Dee Dao平台填寫繳費資料，並於期限內完成繳費 ，再次感謝您的購買。");
				}

				if (adStatus.equals("1")) {
					Util_psw.sendMail(hotelAccount, "Dua Dee Duo旅宿平台",
							hotelName + "廠商您好，經審核您的廣告圖片或圖片說明資料未符合本公司規定 ，請重新上傳相關資料，如有任何疑問歡迎與Dua Dee Dao平台聯絡，再次感謝您的購買。");
				}*/
				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
			
				//request.setAttribute("adVO", adVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/frontend_hotel/ad/listAllByHotelIdPage.jsp";
				
			RequestDispatcher successView = request.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {/* 不可預期的錯誤 */
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/frontend_hotel/ad/pay.jsp");
				e.printStackTrace();
				failureView.forward(request, response);
				
				
			}
		}
		/*if("payCheck".equals(action)){
			
			String adid = request.getParameter("adid").trim();
			
			String url = "/frontend_hotel/ad/listAllByHotelIdPage.jsp";
				
			RequestDispatcher successView = request.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(request, response);

		

			
			
		}*/
	
		

	}

}
