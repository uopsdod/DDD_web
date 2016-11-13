package com.ord.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.ord.model.*;
import javax.servlet.annotation.MultipartConfig;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class OrdServlet extends HttpServlet {

	public void doGet (HttpServletRequest aReq, HttpServletResponse aRes ) throws ServletException, IOException {
		doPost(aReq,aRes);
	}
	
	public void doPost (HttpServletRequest aReq, HttpServletResponse aRes ) throws ServletException, IOException {
		aReq.setCharacterEncoding("UTF-8");
		String action = aReq.getParameter("action");
		
		//System.out.println("now action: " + action);
		
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
				String url = "/backend/ord/listOneOrd.jsp";
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
				String url ="/backend/ord/updateOrdInput.jsp";
				RequestDispatcher successView = aReq.getRequestDispatcher(url);
				successView.forward(aReq,aRes);
			}
				/* 其他可能錯誤處理 */
			catch(Exception e){
				errorMsgs.add("無法取得修改資料: " + e.getMessage());
				RequestDispatcher failureView = aReq.getRequestDispatcher("/backend/ord/listAllOrd.jsp");
				failureView.forward(aReq,aRes);
			}
			
		}
		
		if("update".equals(action)){ // 來自updateOrdInput.jsp
			List<String> errorMsgs = new LinkedList<String>();
			aReq.setAttribute("errorMsgs", errorMsgs);
			OrdVO ordVO =  null;
			
			try{
				
				/* 
				 * = UPDATE 對應 =
				 * 02-01 ordRoomId
				 * 03-02 ordMemId
				 * 04-03 ordHotelId
				 * 05-04 ordPrice
				 * 06-05 ordLiveDate
				 * 07    ordDate
				 * 08-06 ordStatus
				 * 09-07 ordRatingContent
				 * 10-08 ordRatingStarNo
				 * 11-09 ordQrPic
				 * 12-10 ordMsgNo
				 * 01-11 ordID
				*/				
				
				/* 1.接收請求參數 輸入格式錯誤 */
				String ordRoomId = aReq.getParameter("ordRoomId").trim();
				String ordMemId = aReq.getParameter("ordMemId").trim();
				String ordHotelId = aReq.getParameter("ordHotelId").trim();
				String ordStatus = aReq.getParameter("ordStatus").trim();
				String ordRatingContent = aReq.getParameter("ordRatingContent").trim();				
				String ordMsgNo = aReq.getParameter("ordMsgNo").trim();
				String ordId = aReq.getParameter("ordId").trim();
				
				
				Integer ordPrice = null;
				
				try{
					ordPrice = new Integer(aReq.getParameter("ordPrice").trim()); 
				}
				catch(NumberFormatException e){
					ordPrice = 0;
					errorMsgs.add("訂單金額請填數字");
				}
				
				java.sql.Date ordLiveDate = null;
				Timestamp ordLiveDateTs = null;
				
				try{
					ordLiveDate = java.sql.Date.valueOf(aReq.getParameter("ordLiveDate").trim());
					//System.out.println("入住日期: "+ordLiveDate);
					ordLiveDateTs = new Timestamp(ordLiveDate.getTime());
				}
				catch(IllegalArgumentException e){
					ordLiveDate = new java.sql.Date(System.currentTimeMillis());
					ordLiveDateTs = new Timestamp(ordLiveDate.getTime());
					//errorMsgs.add("請輸入入住日期");
				}				
				
				//java.sql.Date ordDate2 = null;
				Timestamp ordDateTs = null;
				
				try{
					Long ordDate = Long.parseLong(aReq.getParameter("ordDate").trim());;

					ordDateTs = new Timestamp(ordDate);
				}
				catch(IllegalArgumentException e){
					//ordDate2 = new java.sql.Date(System.currentTimeMillis());
					//ordDateTs = new Timestamp(ordDate2.getTime());
					errorMsgs.add("請輸入下訂日期");
				}					
				
				Integer ordRatingStarNo = null;
				
				try{
					ordRatingStarNo = new Integer(aReq.getParameter("ordRatingStarNo").trim());	
				}
				catch(NumberFormatException e){
					ordRatingStarNo = 0;
					errorMsgs.add("評價星星數請填數字");
				}
				
				/* 處理上傳圖片進資料庫 */
				Part part = aReq.getPart("ordQrPic");
				InputStream in = part.getInputStream();
				byte[] ordQrPic = new byte[in.available()];
				in.read(ordQrPic);
				in.close();				
				
				/* 
				 * = UPDATE 對應 =
				 * 02-01 ordRoomId
				 * 03-02 ordMemId
				 * 04-03 ordHotelId
				 * 05-04 ordPrice
				 * 06-05 ordLiveDate
				 * 07    ordDate
				 * 08-06 ordStatus
				 * 09-07 ordRatingContent
				 * 10-08 ordRatingStarNo
				 * 11-09 ordQrPic
				 * 12-10 ordMsgNo
				 * 01-11 ordID
				*/
				
				ordVO = new OrdVO();
				
				com.room.model.RoomVO roomVO = new com.room.model.RoomVO();
				roomVO.setRoomId(ordRoomId);
				ordVO.setOrdRoomVO(roomVO);
				
				com.mem.model.MemVO memVO = new com.mem.model.MemVO();
				memVO.setMemId(ordMemId);
				ordVO.setOrdMemVO(memVO);

				com.hotel.model.HotelVO hotelVO = new com.hotel.model.HotelVO();
				hotelVO.setHotelId(ordHotelId);
				ordVO.setOrdHotelVO(hotelVO);
				
				ordVO.setOrdPrice(ordPrice);
				ordVO.setOrdLiveDate(ordLiveDateTs);
				ordVO.setOrdDate(ordDateTs);
				ordVO.setOrdStatus(ordStatus);
				ordVO.setOrdRatingContent(ordRatingContent);
				ordVO.setOrdRatingStarNo(ordRatingStarNo);
				ordVO.setOrdQrPic(ordQrPic);	
				ordVO.setOrdMsgNo(ordMsgNo);
				ordVO.setOrdId(ordId);

				if(!errorMsgs.isEmpty()){
					aReq.setAttribute("ordVO", ordVO);
					RequestDispatcher failureView = aReq.getRequestDispatcher("/backend/ord/updateOrdInput.jsp");
					failureView.forward(aReq,aRes);
					return;
				}
				
				/* 2.開始修改資料 */
				OrdService ordSvc = new OrdService();
				ordVO = ordSvc.updateOrd(ordRoomId,ordMemId,ordHotelId,ordPrice,ordLiveDateTs,ordStatus,ordRatingContent,ordRatingStarNo,ordQrPic,ordMsgNo,ordId,ordDateTs);
				
				/* 3.修改完成 準備轉交 */
				aReq.setAttribute("ordVO", ordVO);
				String url = "/backend/ord/listOneOrd.jsp";
				RequestDispatcher successView = aReq.getRequestDispatcher(url);
				successView.forward(aReq,aRes);
				/* 其他可能錯誤處理 */
			}
			catch(Exception e){
				errorMsgs.add("修改資料失敗" + e.getMessage());
				aReq.setAttribute("ordVO", ordVO);
				RequestDispatcher failureView = aReq.getRequestDispatcher("/backend/ord/updateOrdInput.jsp");
				failureView.forward(aReq, aRes);
			}
		}
		
		if("insert".equals(action)){ //來自addOrd.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			aReq.setAttribute("errorMsgs",errorMsgs);
			OrdVO ordVO = null;	
			try{
				/* 
				 * = INSERT_STMT 對應 =
				 * 01    ordID
				 * 02-01 ordRoomId
				 * 03-02 ordMemId
				 * 04-03 ordHotelId
				 * 05-04 ordPrice
				 * 06-05 ordLiveDate
				 * 07    ordDate
				 * 08-06 ordStatus
				 * 09-07 ordRatingContent
				 * 10-08 ordRatingStarNo
				 * 11-09 ordQrPic
				 * 12-10 ordMsgNo
				*/				
				
				/* 1.接收請求參數 */		
				String ordRoomId = aReq.getParameter("ordRoomId").trim();
				String ordMemId = aReq.getParameter("ordMemId").trim();
				String ordHotelId = aReq.getParameter("ordHotelId").trim();
				String ordStatus = aReq.getParameter("ordStatus").trim();
				String ordRatingContent = aReq.getParameter("ordRatingContent").trim();
				String ordMsgNo = aReq.getParameter("ordMsgNo").trim();
				
				Integer ordPrice = null;
				
				try{
					ordPrice = new Integer(aReq.getParameter("ordPrice").trim()); 
				}
				catch(NumberFormatException e){
					ordPrice = 0;
					errorMsgs.add("訂單金額請填數字");
				}
				
				java.sql.Date ordLiveDate = null;
				Timestamp ordLiveDateTs = null;
				
				try{
					ordLiveDate = java.sql.Date.valueOf(aReq.getParameter("ordLiveDate").trim());
					ordLiveDateTs = new Timestamp(ordLiveDate.getTime());
				}
				catch(IllegalArgumentException e){
					ordLiveDate = new java.sql.Date(System.currentTimeMillis());
					ordLiveDateTs = new Timestamp(ordLiveDate.getTime());
					errorMsgs.add("請輸入入住日期");
				}				
				
				Integer ordRatingStarNo = null;
				
				try{
					ordRatingStarNo = new Integer(aReq.getParameter("ordRatingStarNo").trim()); 
				}
				catch(NumberFormatException e){
					ordRatingStarNo = 0;
					errorMsgs.add("評價星星數請填數字");
				}
				
				/* 處理上傳圖片進資料庫 */
				Part part = aReq.getPart("ordQrPic");
				InputStream in = part.getInputStream();
				byte[] ordQrPic = new byte[in.available()];
				in.read(ordQrPic);
				in.close();				
				
				/* 
				 * = INSERT_STMT 對應 =
				 * 01    ordID
				 * 02-01 ordRoomId
				 * 03-02 ordMemId
				 * 04-03 ordHotelId
				 * 05-04 ordPrice
				 * 06-05 ordLiveDate
				 * 07    ordDate
				 * 08-06 ordStatus
				 * 09-07 ordRatingContent
				 * 10-08 ordRatingStarNo
				 * 11-09 ordQrPic
				 * 12-10 ordMsgNo
				*/				
								
				ordVO = new OrdVO();
				com.room.model.RoomVO roomVO = new com.room.model.RoomVO();
				roomVO.setRoomId(ordRoomId);
				ordVO.setOrdRoomVO(roomVO);
				
				com.mem.model.MemVO memVO = new com.mem.model.MemVO();
				memVO.setMemId(ordMemId);
				ordVO.setOrdMemVO(memVO);

				com.hotel.model.HotelVO hotelVO = new com.hotel.model.HotelVO();
				hotelVO.setHotelId(ordHotelId);
				ordVO.setOrdHotelVO(hotelVO);
				ordVO.setOrdPrice(ordPrice);
				ordVO.setOrdLiveDate(ordLiveDateTs);
				ordVO.setOrdStatus(ordStatus);
				ordVO.setOrdRatingContent(ordRatingContent);
				ordVO.setOrdRatingStarNo(ordRatingStarNo);
				ordVO.setOrdQrPic(ordQrPic);
				ordVO.setOrdMsgNo(ordMsgNo);

				if(!errorMsgs.isEmpty()){
					aReq.setAttribute("ordVO", ordVO);
					RequestDispatcher failureView = aReq.getRequestDispatcher("/backend/ord/addOrd.jsp");
					failureView.forward(aReq, aRes);
					return;
				}
								
				/* 2.開始新增資料 */
				OrdService ordSvc = new OrdService();
				ordVO = ordSvc.addOrd(ordRoomId, ordMemId, ordHotelId, ordPrice, ordLiveDateTs, ordStatus, ordRatingContent, ordRatingStarNo, ordQrPic, ordMsgNo);
				
				/* 3.新增完成 */
				String url = "/backend/ord/listAllOrd.jsp";
				RequestDispatcher successView = aReq.getRequestDispatcher(url);
				successView.forward(aReq, aRes);
			}
			catch(Exception e){
				errorMsgs.add(e.getMessage());
				aReq.setAttribute("ordVO", ordVO);
				RequestDispatcher failureView = aReq.getRequestDispatcher("/backend/ord/addOrd.jsp");
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
				String url = "/backend/ord/listAllOrd.jsp";
				RequestDispatcher successView = aReq.getRequestDispatcher(url);
				successView.forward(aReq,aRes);
			}
			catch(Exception e){
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = aReq.getRequestDispatcher("/backend/ord/listAllOrd.jsp");
				failureView.forward(aReq,aRes);				
			}		
		
		
		}
		
		if("listAllByMemId".equals(action)){ //來自selectPage.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			aReq.setAttribute("errorMsgs", errorMsgs);
			
			try{
				/* 1.接收請求參數 */
				String ordMemId = aReq.getParameter("ordMemId");				
				aReq.setAttribute("ordMemId",ordMemId);
				
				/* 3.準備至轉交 */
				String url ="/backend/ord/listAllByMemId.jsp";
				RequestDispatcher successView = aReq.getRequestDispatcher(url);
				successView.forward(aReq,aRes);
				
			}
			catch(Exception e){
				errorMsgs.add("無法取得修改資料(listAllByMemId): " + e.getMessage());
				RequestDispatcher failureView = aReq.getRequestDispatcher("/backend/ord/selectPage.jsp");
				failureView.forward(aReq,aRes);
			}
			
		}
		
		if("listAllByHotelId".equals(action)){ //來自selectPage.jsp的請求
						
			List<String> errorMsgs = new LinkedList<String>();
			aReq.setAttribute("errorMsgs", errorMsgs);
			
			try{
				/* 1.接收請求參數 */
				String ordHotelId = aReq.getParameter("ordHotelId");			
				aReq.setAttribute("ordHotelId",ordHotelId);
				
				/* 3.準備至轉交 */
				String url ="/backend/ord/listAllByHotelId.jsp";
				RequestDispatcher successView = aReq.getRequestDispatcher(url);
				successView.forward(aReq,aRes);
				
			}
			catch(Exception e){
				errorMsgs.add("無法取得修改資料(listAllByHotelId): " + e.getMessage());
				RequestDispatcher failureView = aReq.getRequestDispatcher("/backend/ord/selectPage.jsp");
				failureView.forward(aReq,aRes);
			}
			
		}		
		
	}
	
}
	

