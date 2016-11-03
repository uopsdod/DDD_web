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
		
		if("getOneForDisplay".equals(action)){ //來自selectPage.jsp請求
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
				
				/* 取得訂單編號 不用轉數字 也就不用相關例外處理了~ */

					
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
		
		if("getOneForUpdate".equals(action)){ //來自listAllOrd.jsp請求
			List<String> errorMsgs = new LinkedList<String>();
			aReq.setAttribute("errorMsgs", errorMsgs);
			
			try{
				/* 1.接收請求參數 */
				String ordId = aReq.getParameter("ordId").trim();
				
				/* 2.開始查詢 */
				OrdService ordSvc = new OrdService();
				OrdVO ordVO = ordSvc.getOneOrd(ordId);	
				
				/* 3.查詢完成 準備轉交 */
				aReq.setAttribute("ordVO", ordVO);
				String url ="/ord/updateOrdInput.jsp";
				RequestDispatcher successView = aReq.getRequestDispatcher(url);
				successView.forward(aReq,aRes);
			}
				/* 其他可能錯誤處理 */
			catch(Exception e){
				errorMsgs.add("無法取得修改資料: " + e.getMessage());
				RequestDispatcher failureView = aReq.getRequestDispatcher("/ord/listAllOrd.jsp");
				failureView.forward(aReq,aRes);
			}
			
		}
		
		if("update".equals(action)){ // 來自updateEmpInput.jsp
			List<String> errorMsgs = new LinkedList<String>();
			aReq.setAttribute("errorMsgs", errorMsgs);
			
			try{
				/* 1.接收請求參數 輸入格式錯誤 */
				String ordId = aReq.getParameter("ordId").trim();
				String ordRatingContent = aReq.getParameter("ordRatingContent").trim();				
				String ordStatus = aReq.getParameter("ordStatus").trim();
				
				Integer ordRatingStarNo = null;
				
				try{
					ordRatingStarNo = new Integer(aReq.getParameter("ordRatingStarNo").trim());	
				}
				catch(NumberFormatException e){
					ordRatingStarNo = 0;
					errorMsgs.add("評價星星數請填數字");
				}
				
				/* UPDATE ord set ordStatus=?, ordRatingContent=?, ordRatingStarNo=? where ordId = ? */
				OrdVO ordVO = new OrdVO();
				ordVO.setOrdId(ordId);
				ordVO.setOrdStatus(ordStatus);
				ordVO.setOrdRatingContent(ordRatingContent);
				ordVO.setOrdRatingStarNo(ordRatingStarNo);
				
				if(!errorMsgs.isEmpty()){
					aReq.setAttribute("ordVO", ordVO);
					RequestDispatcher failureView = aReq.getRequestDispatcher("/ord/updateOrdInput.jsp");
					failureView.forward(aReq,aRes);
					return;
				}
				
				/* 2.開始修改資料 */
				OrdService ordSvc = new OrdService();
				ordVO = ordSvc.updateOrd(ordStatus, ordRatingContent, ordRatingStarNo, ordId);
				
				/* 3.修改完成 準備轉交 */
				aReq.setAttribute("ordVO", ordVO);
				String url = "/ord/listOneOrd.jsp";
				RequestDispatcher successView = aReq.getRequestDispatcher(url);
				successView.forward(aReq,aRes);
				/* 其他可能錯誤處理 */
			}
			catch(Exception e){
				errorMsgs.add("修改資料失敗" + e.getMessage());
				RequestDispatcher failureView = aReq.getRequestDispatcher("/ord/updateOrdInput.jsp");
				failureView.forward(aReq, aRes);
			}
		}
		
		/* !新增欠圖片上傳 */
		if("insert".equals(action)){ //來自addOrd.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			aReq.setAttribute("errorMsgs",errorMsgs);
			
			try{				
				String ordRoomId = aReq.getParameter("ordRoomId").trim();
				String ordMemId = aReq.getParameter("ordMemId").trim();
				String ordHotelId = aReq.getParameter("ordHotelId").trim();
				
				Integer ordPrice = null;
				
				try{
					ordPrice = new Integer(aReq.getParameter("ordPrice").trim()); 
				}
				catch(NumberFormatException e){
					ordPrice = 0;
					errorMsgs.add("訂單金額請填數字");
				}
				
				String ordMsgNo = aReq.getParameter("ordMsgNo").trim();
				
				java.sql.Date ordLiveDate = null;
				
				try{
					ordLiveDate = java.sql.Date.valueOf(aReq.getParameter("ordLiveDate").trim());
				}
				catch(IllegalArgumentException e){
					ordLiveDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期");
				}
	
				/* 1.接收請求參數 */				
				OrdVO ordVO = new OrdVO();
				ordVO.setOrdRoomId(ordRoomId);
				ordVO.setOrdMemId(ordMemId);
				ordVO.setOrdHotelId(ordHotelId);
				ordVO.setOrdPrice(ordPrice);
				ordVO.setOrdLiveDate(ordLiveDate);
				ordVO.setOrdMsgNo(ordMsgNo);
				
				/* 先給空圖 */
				ordVO.setOrdQrPic(null);
				

				if(!errorMsgs.isEmpty()){
					aReq.setAttribute("ordVO", ordVO);
					RequestDispatcher failureView = aReq.getRequestDispatcher("/ord/addOrd.jsp");
					failureView.forward(aReq, aRes);
					return;
				}
				
				/*
				 * = INSERT_STMT 對應 =
				 * 01    ordID
				 * 02-01 ordRoomId
				 * 03-02 ordMemId
				 * 04-03 ordHotelId
				 * 05-04 ordPrice
				 * 06-05 ordLiveDate
				 * 07    ordDate
				 * 08    ordStatus
				 * 09	 ordRatingContent
				 * 10	 ordRatingStarNo
				 * 11-06 ordQrPic
				 * 12-07 ordMsgNo
				*/
				
				/* 2.開始新增資料 */
				OrdService ordSvc = new OrdService();
				ordVO = ordSvc.addOrd(ordRoomId, ordMemId, ordHotelId, ordPrice, ordLiveDate, null, ordMsgNo);
				
				/* 3.新增完成 */
				String url = "/ord/listAllOrd.jsp";
				RequestDispatcher successView = aReq.getRequestDispatcher(url);
				successView.forward(aReq, aRes);
			}
			catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = aReq.getRequestDispatcher("/ord/addOrd.jsp");
				failureView.forward(aReq,aRes);
			}
		}
		
		if("delete".equals(action)){ //來自listAllOrd.jsp
			List<String> errorMsgs = new LinkedList<String>();
			aReq.setAttribute("errorMsgs", errorMsgs);

			try{
				/* 1.接收請求參數 */
				String ordId = aReq.getParameter(("ordId"));
				/* 2.開始刪除資料 */
				OrdService ordSvc = new OrdService();
				ordSvc.deleteOrd(ordId);
				/* 3.刪除完成 準備轉交 */
				String url = "/ord/listAllOrd.jsp";
				RequestDispatcher successView = aReq.getRequestDispatcher(url);
				successView.forward(aReq,aRes);
			}
			catch(Exception e){
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = aReq.getRequestDispatcher("/ord/listAllOrd.jsp");
				failureView.forward(aReq,aRes);				
			}		
		
		
		}
		
	}
	
}
	

