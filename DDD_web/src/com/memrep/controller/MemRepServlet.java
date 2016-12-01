package com.memrep.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import com.mem.model.*;
import com.memrep.model.MemRepService;
import com.emp.model.*;
import com.hotel.model.*;
import com.ord.model.*;
import com.hotelrep.model.*;

public class MemRepServlet extends HttpServlet {

	public void doGet(HttpServletRequest aReq, HttpServletResponse aRes)
			throws ServletException, IOException {
		doPost(aReq, aRes);
	}

	public void doPost(HttpServletRequest aReq, HttpServletResponse aRes)
			throws ServletException, IOException {

		aReq.setCharacterEncoding("UTF-8");
		String action = aReq.getParameter("action");
		
		Integer checkFormat = null;
		
		//System.out.println("action: " + action);
		
        if("setHotelBlackList".equals(action)){
        	
        	List<String> errorMsgs = new LinkedList<String>();
        	aReq.setAttribute("errorMsgs", errorMsgs);
        	
        	try{
        		/* 旅客檢舉單相關 */
				String memRepId = aReq.getParameter("memRepId");
				if (memRepId == null || (memRepId.trim()).length() == 0) {
					errorMsgs.add("請輸入旅客檢舉單編號");
				}
				
				checkFormat = null;

				try {
					checkFormat = new Integer(memRepId);
				} catch (Exception e) {
					e.printStackTrace();
					errorMsgs.add("旅客檢舉單編號格式不正確");
				}
        		
				String memRepStatus = aReq.getParameter("memRepStatus");
				
				if(memRepStatus == null || (memRepStatus.trim()).length() == 0 ){
					errorMsgs.add("請輸入檢舉單處理狀態");
				}
        		
				String memRepEmpId = aReq.getParameter("memRepEmpId").trim();

				if(memRepEmpId == null || (memRepEmpId.trim()).length()==0){
					errorMsgs.add("請輸入員工編號");
				}				
				
				try{
					checkFormat = new Integer(memRepEmpId); 
				}
				catch(NumberFormatException e){
					e.printStackTrace();
					memRepEmpId = null;
					errorMsgs.add("員工編號格式不正確");
				}					
				
				
        		/* 廠商相關 */
				String hotelId = aReq.getParameter("hotelId").trim();

				if(hotelId == null || (hotelId.trim()).length()==0){
					errorMsgs.add("請輸入廠商編號");
				}				
				
				try{
					checkFormat = new Integer(hotelId); 
				}
				catch(NumberFormatException e){
					e.printStackTrace();
					hotelId = null;
					errorMsgs.add("廠商編號格式不正確");
				}	       		
        		
						
				String hotelBlackList = aReq.getParameter("hotelBlackList");
				
				if(hotelBlackList == null || (hotelBlackList.trim()).length() == 0 ){
					errorMsgs.add("請輸入廠商黑名單狀態");
				}      		
        		
        		
        		/* 送回原地方 */
        		if(!errorMsgs.isEmpty()){
    				RequestDispatcher failureView = aReq
    						.getRequestDispatcher("/backend/hotelRep/selectPage.jsp");
    				failureView.forward(aReq, aRes);
    				return;
        		}
        		
        		MemRepService memRepSvc = new MemRepService();

        		memRepSvc.setHotelBlackList(hotelId, hotelBlackList, memRepId, memRepStatus, memRepEmpId);
        		
        		RequestDispatcher successViews = aReq.getRequestDispatcher("/backend/hotelRep/checkHotelRep.jsp");
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
