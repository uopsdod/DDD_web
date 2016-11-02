package com.ord.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.ord.model.*;


public class OrdServlet extends HttpServlet {

	public void doGet (HttpServletRequest aReq, HttpServletResponse aRes ) throws ServletException, IOException {
		doPost(aReq,aRes);
	}
	
	public void doPost (HttpServletRequest aReq, HttpServletResponse aRes ) throws ServletException, IOException {
		aReq.setCharacterEncoding("UTF-8");
		String action = aReq.getParameter("action");
		
		if("getOneForDisplay".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			aReq.setAttribute("errorMsgs",errorMsgs);
				
			try{
				/* 1.接收請求參數 - 輸入格式的錯誤處理 */
				String ordId = aReq.getParameter("ordId");
				if(ordId == null || (ordId.trim()).length()==0){
					errorMsgs.add("請輸入訂單編號");
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = aReq.getRequestDispatcher("/backend/ord/selectPage.jsp");
					failureView.forward(aReq, aRes);
					return;
				}
				
				/* 取得訂單編號 不用轉數字 也就不用例外處理~ */

					
				/* 2.開始資料查詢 */	
				OrdService ordSvc = new OrdService(); 	
				OrdVO ordVO = ordSvc.getOneOrd(ordId);	
				if(ordVO == null){
					errorMsgs.add("查無資料");
				}
				
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = aReq.getRequestDispatcher("/backend/ord/selectPage.jsp");
					failureView.forward(aReq, aRes);
					return;
				}
				/* 3.查詢完成 */	
				aReq.setAttribute("ordVO", ordVO);
				String url = "/ord/listOneOrd.jsp";
				RequestDispatcher successView = aReq.getRequestDispatcher(url);
				successView.forward(aReq,aRes);
				/* 其他可能的錯誤處理 */
			}
			catch(Exception e){
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = aReq.getRequestDispatcher("/backend/ord/selectPage.jsp");
				failureView.forward(aReq, aRes);				
			}
		}
		
		
		
	}
	
}
	

