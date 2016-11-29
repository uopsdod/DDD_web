package com.hotelrep.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import com.mem.model.*;
import com.emp.model.*;
import com.hotel.model.*;
import com.ord.model.*;
import com.hotelrep.model.*;

public class HotelRepServlet extends HttpServlet {

	public void doGet(HttpServletRequest aReq, HttpServletResponse aRes)
			throws ServletException, IOException {
		doPost(aReq, aRes);
	}

	public void doPost(HttpServletRequest aReq, HttpServletResponse aRes)
			throws ServletException, IOException {

		aReq.setCharacterEncoding("UTF-8");
		String action = aReq.getParameter("action");
		
		Integer checkFormat = null;
		
		if ("getOneForDisplay".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			aReq.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String hotelRepId = aReq.getParameter("hotelRepId");
				if (hotelRepId == null || (hotelRepId.trim()).length() == 0) {
					errorMsgs.add("請輸入廠商檢舉單編號");
				}
				
				checkFormat = null;

				try {
					checkFormat = new Integer(hotelRepId);
				} catch (Exception e) {
					e.printStackTrace();
					errorMsgs.add("廠商檢舉單編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = aReq
							.getRequestDispatcher("/backend/hotelRep/selectPage.jsp");
					failureView.forward(aReq, aRes);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				HotelRepService hotelRepSvc = new HotelRepService();
				HotelRepVO hotelRepVO = hotelRepSvc.getOneHotelRep(hotelRepId);
				
				if (hotelRepVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = aReq
							.getRequestDispatcher("/backend/hotelRep/selectPage.jsp");
					failureView.forward(aReq, aRes);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				aReq.setAttribute("hotelRepVO", hotelRepVO); // 資料庫取出的empVO物件,存入req
				String url = "/backend/hotelRep/listOneHotelRep.jsp";
				RequestDispatcher successView = aReq.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(aReq, aRes);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = aReq
						.getRequestDispatcher("/backend/hotelRep/selectPage.jsp");
				failureView.forward(aReq, aRes);
			}
		}
		
		
		if ("getOneForUpdate".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			aReq.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String hotelRepId = aReq.getParameter("hotelRepId");
				if (hotelRepId == null || (hotelRepId.trim()).length() == 0) {
					errorMsgs.add("請輸入廠商檢舉單編號");
				}
				
				checkFormat = null;
				try {
					checkFormat = new Integer(hotelRepId);
				} catch (Exception e) {
					e.printStackTrace();
					errorMsgs.add("廠商檢舉單編號格式不正確");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = aReq
							.getRequestDispatcher("/backend/hotelRep/listAllHotelRep.jsp");
					failureView.forward(aReq, aRes);
					return;//程式中斷
				}				
				
				
				/***************************2.開始查詢資料****************************************/
				HotelRepService hotelRepSvc = new HotelRepService();
				HotelRepVO hotelRepVO = hotelRepSvc.getOneHotelRep(hotelRepId);
				
				if (hotelRepVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = aReq
							.getRequestDispatcher("/backend/hotelRep/listAllHotelRep.jsp");
					failureView.forward(aReq, aRes);
					return;//程式中斷
				}
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				aReq.setAttribute("hotelRepVO", hotelRepVO); // 資料庫取出的empVO物件,存入req
				String url = "/backend/hotelRep/updateHotelRepInput.jsp";
				RequestDispatcher successView = aReq.getRequestDispatcher(url); // 成功轉交update_emp_input.jsp
				successView.forward(aReq, aRes);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
				RequestDispatcher failureView = aReq
						.getRequestDispatcher("/backend/hotelRep/listAllHotelRep.jsp");
				failureView.forward(aReq, aRes);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			aReq.setAttribute("errorMsgs", errorMsgs);
			HotelRepVO hotelRepVO = null;
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				//hotelRepId		
				String hotelRepId = aReq.getParameter("hotelRepId").trim();

				if(hotelRepId == null || (hotelRepId.trim()).length()==0){
					errorMsgs.add("請輸入廠商檢舉單編號");
				}				
				
				try{
					checkFormat = new Integer(hotelRepId); 
				}
				catch(NumberFormatException e){
					e.printStackTrace();
					hotelRepId = null;
					errorMsgs.add("廠商檢舉單編號格式不正確");
				}					
				
				//hotelRepHotelId		
				String hotelRepHotelId = aReq.getParameter("hotelRepHotelId").trim();

				if(hotelRepHotelId == null || (hotelRepHotelId.trim()).length()==0){
					errorMsgs.add("請輸入廠商編號");
				}				
				
				try{
					checkFormat = new Integer(hotelRepHotelId); 
				}
				catch(NumberFormatException e){
					e.printStackTrace();
					hotelRepHotelId = null;
					errorMsgs.add("廠商編號格式不正確");
				}					
				
				//hotelRepMemId
				String hotelRepMemId = aReq.getParameter("hotelRepMemId").trim();

				if(hotelRepMemId == null || (hotelRepMemId.trim()).length()==0){
					errorMsgs.add("請輸入一般會員編號");
				}				
				
				try{
					checkFormat = new Integer(hotelRepMemId); 
				}
				catch(NumberFormatException e){
					hotelRepMemId = null;
					errorMsgs.add("一般會員編號格式不正確");
					e.printStackTrace();
				}					
				
				//hotelRepOrdId
				String hotelRepOrdId = aReq.getParameter("hotelRepOrdId").trim();

				if(hotelRepOrdId == null || (hotelRepOrdId.trim()).length()==0){
					errorMsgs.add("請輸入訂單編號");
				}				
				
				try{
					checkFormat = new Integer(hotelRepOrdId); 
				}
				catch(NumberFormatException e){
					hotelRepOrdId = null;
					errorMsgs.add("訂單編號格式不正確");
					e.printStackTrace();
				}				
				
				//hotelRepEmpId
				String hotelRepEmpId = aReq.getParameter("hotelRepEmpId").trim();

				if(hotelRepEmpId != null && (hotelRepEmpId.trim()).length()!=0){
					try{
						checkFormat = new Integer(hotelRepOrdId); 
					}
					catch(NumberFormatException e){
						e.printStackTrace();
						hotelRepOrdId = null;
						errorMsgs.add("訂單編號格式不正確");
					}
				}
				
				//hotelRepContent
				String hotelRepContent = aReq.getParameter("hotelRepContent").trim();
				
				//hotelRepStatus
				String hotelRepStatus = aReq.getParameter("hotelRepStatus").trim();

				
				HotelRepService hotelRepSvc = new HotelRepService();
					
				//hotelReplId
				//hotelRepVO.setHotelRepId(hotelRepId);
				
				/*從資料庫取出要被修改的 */
				hotelRepVO = hotelRepSvc.getOneHotelRep(hotelRepId); 
				
				//hotelRepHotelId
				HotelVO hotelVO = new HotelVO();
				hotelVO.setHotelId(hotelRepHotelId);
				hotelRepVO.setHotelRepHotelVO(hotelVO);
				
				//hotelRepMemId
				MemVO memVO = new MemVO();
				memVO.setMemId(hotelRepMemId);
				hotelRepVO.setHotelRepMemVO(memVO);
				
				//hotelRepOrdId
				OrdVO ordVO = new OrdVO();
				ordVO.setOrdId(hotelRepOrdId);
				hotelRepVO.setHotelRepOrdVO(ordVO);
				
				//hotelRepEmpId
				if(hotelRepEmpId != null && (hotelRepEmpId.trim()).length()!=0){
					EmpVO empVO = new EmpVO();
					empVO.setEmpId(hotelRepEmpId);
					hotelRepVO.setHotelRepEmpVO(empVO);
				}
				
				//hotelRepContent
				if(!hotelRepContent.isEmpty()){
					hotelRepVO.setHotelRepContent(hotelRepContent);
				}
				
				//hotelRepStatus
				hotelRepVO.setHotelRepStatus(hotelRepStatus);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					aReq.setAttribute("hotelRepVO", hotelRepVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = aReq
							.getRequestDispatcher("/backend/hotelRep/updateHotelRepInput.jsp");
					failureView.forward(aReq, aRes);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/

				hotelRepVO = hotelRepSvc.updateHotelRep(hotelRepVO);	

				System.out.println(" (update) 檢舉時間 " + hotelRepVO.getHotelRepDate());
				System.out.println(" (update) 處理時間 " + hotelRepVO.getHotelRepReviewDate());
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/				
				aReq.setAttribute("hotelRepVO", hotelRepVO);
				
				RequestDispatcher successView = aReq.getRequestDispatcher("/backend/hotelRep/listOneHotelRep.jsp");   // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(aReq, aRes);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = aReq
						.getRequestDispatcher("/backend/hotelRep/updateHotelRepInput.jsp");
				failureView.forward(aReq, aRes);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			aReq.setAttribute("errorMsgs", errorMsgs);
			
			HotelRepVO hotelRepVO = null;

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				//hotelRepHotelId		
				String hotelRepHotelId = aReq.getParameter("hotelRepHotelId").trim();

				if(hotelRepHotelId == null || (hotelRepHotelId.trim()).length()==0){
					errorMsgs.add("請輸入廠商編號");
				}				
				
				try{
					checkFormat = new Integer(hotelRepHotelId); 
				}
				catch(NumberFormatException e){
					e.printStackTrace();
					hotelRepHotelId = null;
					errorMsgs.add("廠商編號格式不正確");
				}					
				
				//hotelRepMemId
				String hotelRepMemId = aReq.getParameter("hotelRepMemId").trim();

				if(hotelRepMemId == null || (hotelRepMemId.trim()).length()==0){
					errorMsgs.add("請輸入一般會員編號");
				}				
				
				try{
					checkFormat = new Integer(hotelRepMemId); 
				}
				catch(NumberFormatException e){
					hotelRepMemId = null;
					errorMsgs.add("一般會員編號格式不正確");
					e.printStackTrace();
				}					
				
				//hotelRepOrdId
				String hotelRepOrdId = aReq.getParameter("hotelRepOrdId").trim();

				if(hotelRepOrdId == null || (hotelRepOrdId.trim()).length()==0){
					errorMsgs.add("請輸入訂單編號");
				}				
				
				try{
					checkFormat = new Integer(hotelRepOrdId); 
				}
				catch(NumberFormatException e){
					hotelRepOrdId = null;
					errorMsgs.add("訂單編號格式不正確");
					e.printStackTrace();
				}				
				
				//hotelRepEmpId
//				String hotelRepEmpId = aReq.getParameter("hotelRepEmpId").trim();
//
//				if(hotelRepEmpId != null && (hotelRepEmpId.trim()).length()!=0){
//					try{
//						checkFormat = new Integer(hotelRepOrdId); 
//					}
//					catch(NumberFormatException e){
//						e.printStackTrace();
//						hotelRepOrdId = null;
//						errorMsgs.add("員工編號格式不正確");
//					}
//				}
				
				//hotelRepContent
				String hotelRepContent = null;
				
				try{
					hotelRepContent = aReq.getParameter("hotelRepContent").trim();
				}
				catch(Exception e){
					System.out.println(aReq.getParameter("hotelRepContent"));
					e.printStackTrace();
					RequestDispatcher failureView = aReq
							.getRequestDispatcher("/backend/hotelRep/addHotelRep.jsp");
					failureView.forward(aReq, aRes);
				}
				
				//hotelRepStatus
				String hotelRepStatus = null;
				
				try{
					hotelRepStatus = aReq.getParameter("hotelRepStatus").trim();
				}
				catch(Exception e){
					System.out.println(aReq.getParameter("hotelRepStatus"));
					e.printStackTrace();
					RequestDispatcher failureView = aReq
							.getRequestDispatcher("/backend/hotelRep/addHotelRep.jsp");
					failureView.forward(aReq, aRes);
				}
				
				/* 包成物件 */
				hotelRepVO = new HotelRepVO(); 
				
				//hotelRepHotelId
				HotelVO hotelVO = new HotelVO();
				hotelVO.setHotelId(hotelRepHotelId);
				hotelRepVO.setHotelRepHotelVO(hotelVO);
				
				//hotelRepMemId
				MemVO memVO = new MemVO();
				memVO.setMemId(hotelRepMemId);
				hotelRepVO.setHotelRepMemVO(memVO);
				
				//hotelRepOrdId
				OrdVO ordVO = new OrdVO();
				ordVO.setOrdId(hotelRepOrdId);
				hotelRepVO.setHotelRepOrdVO(ordVO);
				
				//hotelRepEmpId
//				if(hotelRepEmpId != null && (hotelRepEmpId.trim()).length()!=0){
//					EmpVO empVO = new EmpVO();
//					empVO.setEmpId(hotelRepEmpId);
//					hotelRepVO.setHotelRepEmpVO(empVO);
//				}
				
				//hotelRepContent
				if(!hotelRepContent.isEmpty()){
					hotelRepVO.setHotelRepContent(hotelRepContent);
				}
				
				//hotelRepStatus
				hotelRepVO.setHotelRepStatus(hotelRepStatus);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					aReq.setAttribute("hotelRepVO", hotelRepVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = aReq
							.getRequestDispatcher("/backend/hotelRep/addHotelRep.jsp");
					failureView.forward(aReq, aRes);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				HotelRepService hotelRepSvc = new HotelRepService();
				
				hotelRepVO = hotelRepSvc.addHotelRep(hotelRepHotelId, hotelRepMemId, hotelRepOrdId, hotelRepContent, hotelRepStatus);
								
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/frontend_hotel/ord/listAllOrdByHotelId.jsp";
				RequestDispatcher successView = aReq.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(aReq, aRes);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				aReq.setAttribute("hotelRepVO", hotelRepVO);
				RequestDispatcher failureView = aReq
						.getRequestDispatcher("/backend/hotelRep/addHotelRep.jsp");
				failureView.forward(aReq, aRes);
			}
		}
        
        if("getAllByHotelRepStatus".equals(action)){
        	List<String> errorMsgs = new LinkedList<String>();
        	aReq.setAttribute("errorMsgs", errorMsgs);
        	
        	try{
        		String hotelRepStatus = aReq.getParameter("hotelRepStatus");
        		
        		if(hotelRepStatus == null || (hotelRepStatus.trim()).length() == 0 ){
        			errorMsgs.add("請輸入訂單處理狀態");
        		}
        		
        		HotelRepService hotelRepSvc = new HotelRepService();
        		List<HotelRepVO> list = hotelRepSvc.getAllByHotelRepStatus(hotelRepStatus);
        		
        		if(list == null){
        			errorMsgs.add("查無此資料");
        		}
        		
        		if(!errorMsgs.isEmpty()){
    				RequestDispatcher failureView = aReq
    						.getRequestDispatcher("/backend/hotelRep/selectPage.jsp");
    				failureView.forward(aReq, aRes);
    				return;
        		}
        		
        		aReq.setAttribute("list", list);
        		RequestDispatcher successViews = aReq.getRequestDispatcher("/backend/hotelRep/listAllHotelRepByHotelRepStatus.jsp");
        		successViews.forward(aReq, aRes);
        	}
        	catch(Exception e){
        		e.printStackTrace();
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = aReq
						.getRequestDispatcher("/backend/hotelRep/selectPage.jsp");
				failureView.forward(aReq, aRes);        		
        		
        	}
        	
        }
		
	}
}
