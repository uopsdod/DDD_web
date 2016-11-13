package com.room.controler;

import java.util.*;
import java.io.BufferedInputStream;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.room.model.RoomService;
import com.room.model.RoomVO;
import com.roomphoto.model.RoomPhotoService;

/**
 * Servlet implementation class RoomServlet
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class RoomServlet extends HttpServlet {
	
	
	
	public static Map<String,Map> OnData = new HashMap<String,Map>();	//存有各room的降價排程
	static Map<String,Timer> OnTimer = new HashMap<String,Timer>(); //存有各room的即時價格
	static Map<String,Timer> DownTimer = new HashMap<String,Timer>(); //存有各room的即時價格

	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("roomId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入房間編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/room/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer roomId = null;
				try {
					roomId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("房間編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/room/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				RoomService roomSvc = new RoomService();
				RoomVO roomVO = roomSvc.findByPrimaryKey(str);
				if (roomVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/room/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				
				
				RoomPhotoService roomphoSer  = new RoomPhotoService();
				List RoomPhotoId = roomphoSer.getRoomAllRoomPhotoId(str);
				
				req.setAttribute("roomVO", roomVO); // 資料庫取出的empVO物件,存入req
				

				
				
				
				req.setAttribute("RoomPhotoId", RoomPhotoId); // 資料庫取出的empVO物件,存入req
						
				String url = "/room/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/room/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
		}
		
		
		
		
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllRoom.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String roomId = req.getParameter("roomId");
				
				/***************************2.開始查詢資料****************************************/
				RoomService roomSvc = new RoomService();
				RoomVO roomVO = roomSvc.findByPrimaryKey(roomId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("roomVO", roomVO);         // 資料庫取出的empVO物件,存入req
				String url = "/frontend_hotel/room/update_room_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend_hotel/room/listRoomSell.jsp");
				failureView.forward(req, res);
				return;
			}
		}
		
		
		
		
		if ("setSell".equals(action)) { // 來自listAllRoom.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String roomId = req.getParameter("roomId");
				
				/***************************2.開始查詢資料****************************************/
				RoomService roomSvc = new RoomService();
				RoomVO roomVO = roomSvc.findByPrimaryKey(roomId);
													
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("roomVO", roomVO);         // 資料庫取出的empVO物件,存入req
				String url = "/frontend_hotel/room/setSell.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend_hotel/room/listAllRoomSell.jsp");
				failureView.forward(req, res);
				return;
			}
		}
		if ("RegularCancel".equals(action)) { // 來自listAllRoom.jsp的請求

			System.out.println("aaaaa");	
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String roomId = req.getParameter("roomId");
				
				RoomService roomSvc = new RoomService();
				RoomVO roomVO = roomSvc.findByPrimaryKey(roomId);
				
				
				if(roomVO.getRoomForSellAuto()==false){	//若未開啟定時系統直接返回
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend_hotel/room/listAllRoomSell.jsp");
					failureView.forward(req, res);
					return;					
				}				
				
				int beforeStartDate = roomVO.getRoomDiscountStartDate();				
				
				//從定時系統的上架map移除此房型的資料
				ServletContext box = getServletContext();
				Map<Integer,List> RoomRegularTime = (Map<Integer,List>)box.getAttribute("RoomRegularTime");
				
				
				if(RoomRegularTime.get(beforeStartDate)!=null){	//刪除重複定時的原room在map的時刻
				  List<String> beforeList = RoomRegularTime.get(beforeStartDate);
				  beforeList.remove(roomId);	
				  if(beforeList.size()==0){	//當該時刻的list已經沒有roomId時,就從map中刪除該list
					  RoomRegularTime.remove(beforeStartDate); 
				  }
				}				
					
				roomVO.setRoomForSellAuto(false);
				roomSvc.update(roomVO);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend_hotel/room/listAllRoomSell.jsp");
				failureView.forward(req, res);
				return;	

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend_hotel/room/listAllRoomSell.jsp");
				failureView.forward(req, res);
				return;	
			}
		}
		
		if ("DownSell".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
		
				String roomId = req.getParameter("roomId");
				if (roomId == null || (roomId.trim()).length() == 0) {
					errorMsgs.add("請輸入房間編號");
				}
				
				
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend_hotel/room/listAllRoomSell.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
			
				/***************************2.開始查詢資料*****************************************/
				RoomService roomSvc = new RoomService();
				RoomVO roomVO = roomSvc.findByPrimaryKey(roomId);
				
				Boolean onSell = roomVO.getRoomForSell();
				
				if(onSell==false){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend_hotel/room/listAllRoomSell.jsp");
					failureView.forward(req, res);
					return;//程式中斷
		
				}
				
//				------------------------------------------------------------------------------
				try{				
					 OnTimer.get(roomId).cancel();	//前期降價排程取消				
					 DownTimer.get(roomId).cancel(); //前期下架排程取消						
					 OnTimer.remove(roomId);	//清空計時器				
		        	 OnData.remove(roomId); 	//清空資料	
		        	 System.out.println("下架了" +roomId);
				}catch(RuntimeException e){System.out.println("下架發生錯誤");}
				
				
				roomVO.setRoomForSell(false);
				roomSvc.update(roomVO);
				
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend_hotel/room/listAllRoomSell.jsp");
				failureView.forward(req, res);
				return;//程式中斷
				
				
				

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend_hotel/room/listAllRoomSell.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
		}
		
		
		
		if ("OnTimeSell".equals(action)) { // 來自listAllRoom.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String roomId = req.getParameter("roomId");
				
			
				Integer roomPrice = null;
				String roomPriceStr = req.getParameter("roomPrice").trim();
				if ( "".equals(roomPriceStr)) {
					errorMsgs.add("請輸入房間定價");
				}else{
					roomPrice = new Integer(roomPriceStr);
				}
				
				boolean roomForSell = true;

				Calendar now = new GregorianCalendar();		//now
				int roomDiscountStartDate = now.get(Calendar.HOUR_OF_DAY)*60*60*1000+
            	+ now.get(Calendar.MINUTE)*60*1000;
				

				Integer roomDiscountEndDateHour = (new Integer(req.getParameter("roomDiscountEndDatehour").trim()))*60*60*1000;
				Integer roomDiscountEndDateMinute = (new Integer(req.getParameter("roomDiscountEndDateminute").trim()))*60*1000;				
				Integer roomDiscountEndDate = roomDiscountEndDateHour+ roomDiscountEndDateMinute;
				
				if(roomDiscountStartDate>=roomDiscountEndDate){
						errorMsgs.add("下架時間時間得較此時此刻晚");		
				}	
				
				if(roomDiscountEndDate==0){	
						errorMsgs.add("請輸入下架時間");				
				}
				
			
				
				
		
				//roomDisccountPercent
				Integer roomDisccountPercent = (new Integer(req.getParameter("roomDisccountPercent").trim()));
	
				
				//bottomPrice
				Integer roomBottomPrice=0;
				try{
					roomBottomPrice = (new Integer(req.getParameter("roomBottomPrice").trim()));
				}catch(Exception e){
					roomBottomPrice = 0;
				}
				
				//bottomPrice
				Integer roomRemainNo=0;
				try{
					roomRemainNo = (new Integer(req.getParameter("roomRemainNo").trim()));
				}catch(Exception e){
					roomRemainNo = 0;
				}
				
				
				
				//roomDiscountHr
			
				Integer roomDiscountHr = new Integer(req.getParameter("roomDiscountHr").trim());
			
				//roomOnePrice
				
				boolean roomOnePrice;
				try{
					
					roomOnePrice = getBoolean(req.getParameter("roomOnePrice").trim());
					if(req.getParameter("roomOnePrice").trim().length()==0)
					{
						errorMsgs.add("請輸入是否一價到底");
					}	
				}catch(Exception e){
					errorMsgs.add("請輸入是否一價到底");	
					roomOnePrice = false;
				}
				
							
			
				/***************************2.開始查詢資料****************************************/
				
				RoomService roomSvc = new RoomService();
				RoomVO roomVO = roomSvc.findByPrimaryKey(roomId);
				
				boolean beforeSell = roomVO.getRoomForSell();
				
				System.out.println(roomPrice);
				
				roomVO.setRoomPrice(roomPrice);
				roomVO.setRoomBottomPrice(roomBottomPrice);
				roomVO.setRoomRemainNo(roomRemainNo);
				roomVO.setRoomDiscountEndDate(roomDiscountEndDate);
				roomVO.setRoomDisccountPercent(roomDisccountPercent);
				roomVO.setRoomDiscountHr(roomDiscountHr);
				roomVO.setRoomOnePrice(roomOnePrice);
				roomVO.setRoomForSell(roomForSell);
				
			
				
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("roomVO", roomVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend_hotel/room/setSell.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
						
				roomSvc.update(roomVO);
		
				//建立房型的降價機制,與下架機制
				Float cutPrice = roomPrice*roomDisccountPercent*0.01f;
				int cutPriceInt = cutPrice.intValue();
				DynamicPrice(roomId,beforeSell,roomDiscountHr*30*60*1000,roomPrice,cutPriceInt,roomBottomPrice,roomOnePrice,roomDiscountEndDate);

				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("roomVO", roomVO);         // 資料庫取出的empVO物件,存入req
				String url = "/frontend_hotel/room/listAllRoomSell.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
				return;	
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				String roomId = req.getParameter("roomId");
				RoomService roomSvc = new RoomService();
				RoomVO roomVO = roomSvc.findByPrimaryKey(roomId);
				req.setAttribute("roomVO", roomVO);         // 資料庫取出的empVO物件,存入req
								
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend_hotel/room/setSell.jsp");
				failureView.forward(req, res);
				return;
			}
		}
		
		
		if ("OnTimeSellRegular".equals(action)) { // 來自listAllRoom.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String roomId = req.getParameter("roomId");
				
			
				Integer roomPrice = null;
				String roomPriceStr = req.getParameter("roomPrice").trim();
				if ( "".equals(roomPriceStr)) {
					errorMsgs.add("請輸入房間定價");
				}else{
					roomPrice = new Integer(roomPriceStr);
				}
				
				
				
				boolean roomForSellAuto =true;
				
				
				//roomDiscountStartDate
				Integer roomDiscountStartDateHour = (new Integer(req.getParameter("roomDiscountStartDatehour").trim()))*60*60*1000;
				Integer roomDiscountStartDateMinute = (new Integer(req.getParameter("roomDiscountStartDateminute").trim()))*60*1000;	
				Integer roomDiscountStartDate = roomDiscountStartDateHour+ roomDiscountStartDateMinute ;
				if(roomDiscountStartDate==0){	
					errorMsgs.add("請輸入定時上架開始時間");		
				}
				
					
				//roomDiscountEndDate			
				Integer roomDiscountEndDateHour = (new Integer(req.getParameter("roomDiscountEndDatehour").trim()))*60*60*1000;
				Integer roomDiscountEndDateMinute = (new Integer(req.getParameter("roomDiscountEndDateminute").trim()))*60*1000;				
				Integer roomDiscountEndDate = roomDiscountEndDateHour+ roomDiscountEndDateMinute;
				if(roomDiscountStartDate>roomDiscountEndDate){
						errorMsgs.add("下架時間時間得較開始時間晚");		
				}	
				if(roomDiscountEndDate==0){	
						errorMsgs.add("請輸入下架時間");					
				}
				
					
				//roomDisccountPercent
				Integer roomDisccountPercent = (new Integer(req.getParameter("roomDisccountPercent").trim()));
	
				
				//bottomPrice
				Integer roomBottomPrice=0;
				try{
					roomBottomPrice = (new Integer(req.getParameter("roomBottomPrice").trim()));
				}catch(Exception e){
					roomBottomPrice = 0;
				}
				
				
			
				
				//每日預定上架房數
				Integer roomDefaultNo = null;
				String roomDefaultNoStr = req.getParameter("roomDefaultNo").trim();
				if ( "".equals(roomDefaultNoStr)) {
					errorMsgs.add("請輸入每日預定上架房數");
				}else{
					roomDefaultNo = new Integer(roomDefaultNoStr);
				}
				
				
				
				
				//roomDiscountHr		
				Integer roomDiscountHr = new Integer(req.getParameter("roomDiscountHr").trim());
			
				
				//roomOnePrice		
				boolean roomOnePrice;
				try{
					
					roomOnePrice = getBoolean(req.getParameter("roomOnePrice").trim());
					if(req.getParameter("roomOnePrice").trim().length()==0)
					{
						errorMsgs.add("請輸入是否一價到底");
					}	
				}catch(Exception e){
					errorMsgs.add("請輸入是否一價到底");	
					roomOnePrice = false;
				}
				
							
				
				/***************************2.開始查詢資料****************************************/
				
				RoomService roomSvc = new RoomService();
				RoomVO roomVO = roomSvc.findByPrimaryKey(roomId);
				
				int beforeStartDate = roomVO.getRoomDiscountStartDate();
				
				roomVO.setRoomPrice(roomPrice);
			
				roomVO.setRoomBottomPrice(roomBottomPrice);
				roomVO.setRoomDefaultNo(roomDefaultNo);
				roomVO.setRoomForSellAuto(roomForSellAuto);
				roomVO.setRoomDiscountStartDate(roomDiscountStartDate);
				roomVO.setRoomDiscountEndDate(roomDiscountEndDate);
				roomVO.setRoomDisccountPercent(roomDisccountPercent);
				roomVO.setRoomDiscountHr(roomDiscountHr);
				roomVO.setRoomOnePrice(roomOnePrice);
				
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("roomVO", roomVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend_hotel/room/setSell.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
						
				roomSvc.update(roomVO);
				
				
				//放入定時系統的上架map
				ServletContext box = getServletContext();
				Map<Integer,List> RoomRegularTime = (Map<Integer,List>)box.getAttribute("RoomRegularTime");
				
				
				if(RoomRegularTime.get(beforeStartDate)!=null){	//刪除重複定時的原room在map的時刻
				  List<String> beforeList = RoomRegularTime.get(beforeStartDate);
				  beforeList.remove(roomId);	
				  if(beforeList.size()==0){	//當該時刻的list已經沒有roomId時,就從map中刪除該list
					  RoomRegularTime.remove(beforeStartDate); 
				  }
				}				
				if(RoomRegularTime.get(roomVO.getRoomDiscountStartDate())==null){
	    			List<String> now = new LinkedList<String>();
	    			now.add(roomVO.getRoomId());
	    			RoomRegularTime.put(roomVO.getRoomDiscountStartDate(),now);	
	    			
	    		}else{
	    			List now =RoomRegularTime.get(roomVO.getRoomDiscountStartDate());
	    			now.add(roomVO.getRoomId());
	    			RoomRegularTime.put(roomVO.getRoomDiscountStartDate(),now);	
	    		}  		
				
				
//				//建立房型的降價機制,與下架機制
//				Float cutPrice = roomPrice*roomDisccountPercent*0.01f;
//				int cutPriceInt = cutPrice.intValue();
//				DynamicPrice(roomId,roomDiscountHr*30*60*1000,roomPrice,cutPriceInt,bottomPrice,roomOnePrice,roomDiscountEndDate);
//					
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("roomVO", roomVO);         // 資料庫取出的empVO物件,存入req
				String url = "/frontend_hotel/room/listAllRoomSell.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
				return;	
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				String roomId = req.getParameter("roomId");
				RoomService roomSvc = new RoomService();
				RoomVO roomVO = roomSvc.findByPrimaryKey(roomId);
				req.setAttribute("roomVO", roomVO);         // 資料庫取出的empVO物件,存入req
								
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend_hotel/room/setSell.jsp");
				failureView.forward(req, res);
				return;
			}
		}
		
		
		
		
		
		
		
		if ("RoomUpdate".equals(action)) { // 來自update_room_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String roomId = new String(req.getParameter("roomId").trim());
			
				
				String roomHotelId = new String(req.getParameter("roomHotelId").trim());
	
				String roomName = req.getParameter("roomName").trim();
				if (roomName == null || (roomName.trim()).length() == 0) {
					errorMsgs.add("請輸入房間名稱");
				}
				
				Integer roomTotalNo = null;
				String roomTotalNoStr = req.getParameter("roomTotalNo").trim();
				if ( "".equals(roomTotalNoStr)) {
					errorMsgs.add("請輸入房間總數");
				}else{
					 roomTotalNo = new Integer(roomTotalNoStr);
				}
				
				
				Integer roomPrice = null;
				String roomPriceStr = req.getParameter("roomPrice").trim();
				if ( "".equals(roomPriceStr)) {
					errorMsgs.add("請輸入房間定價");
				}else{
					roomPrice = new Integer(roomPriceStr);
				}
				
			
							
				
				//roomFun
				String roomFun = new String(req.getParameter("roomFun").trim());
				if (roomFun == null || (roomFun.trim()).length() == 0) {
					errorMsgs.add("請輸入娛樂    若沒有請註明'無'");
				}
				//roomMeal
				String roomMeal = new String(req.getParameter("roomMeal").trim());
				if (roomMeal == null || (roomMeal.trim()).length() == 0) {
					errorMsgs.add("請輸入餐飲    若沒有請註明'無'");
				}
				//roomSleep
				String roomSleep = new String(req.getParameter("roomSleep").trim());
				if (roomSleep == null || (roomSleep.trim()).length() == 0) {
					errorMsgs.add("請輸入舒適睡眠    若沒有請註明'無'");
				}
				//roomFacility
				String roomFacility = new String(req.getParameter("roomFacility").trim());
				if (roomFacility == null || (roomFacility.trim()).length() == 0) {
					errorMsgs.add("請輸入設施    若沒有請註明'無'");
				}
				//roomSweetFacility
				String roomSweetFacility = new String(req.getParameter("roomSweetFacility").trim());
				if (roomSweetFacility == null || (roomSweetFacility.trim()).length() == 0) {
					errorMsgs.add("請輸入貼心服務    若沒有請註明'無'");
				}
				//roomCapacity
				Integer roomCapacity = new Integer(req.getParameter("roomCapacity").trim());				
				//roomCapacity
				Integer roomOneBed = new Integer(req.getParameter("roomOneBed").trim());
				//roomCapacity
				Integer roomTwoBed = new Integer(req.getParameter("roomTwoBed").trim());
				if(roomOneBed+roomTwoBed*2<roomCapacity)	{
					errorMsgs.add("床數太少,不符合入住人數");	
				}		
				
				
				
				RoomService roomSvc = new RoomService();
				RoomVO roomVO = roomSvc.findByPrimaryKey(roomId);
				
				roomVO.setRoomId(roomId);
				roomVO.setRoomHotelId(roomHotelId);
				roomVO.setRoomName(roomName);
				roomVO.setRoomTotalNo(roomTotalNo);
				roomVO.setRoomPrice(roomPrice);
		
				roomVO.setRoomFun(roomFun);
				roomVO.setRoomMeal(roomMeal);
				roomVO.setRoomSleep(roomSleep);
				roomVO.setRoomFacility(roomFacility);
				roomVO.setRoomSweetFacility(roomSweetFacility);
				roomVO.setRoomCapacity(roomCapacity);
				roomVO.setRoomOneBed(roomOneBed);
				roomVO.setRoomTwoBed(roomTwoBed);
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("roomVO", roomVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend_hotel/room/update_room_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				
				roomSvc.update(roomVO);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("roomVO", roomVO); // 資料庫update成功後,正確的的empVO物件,存入req
				
				String url = new String(req.getParameter("requestURL").trim());
//				System.out.println(url2);
//				String url = "/frontend_hotel/room/listAllRoom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				return;
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				System.out.println("沒包到VO直接跳錯了");
				
				String roomId = new String(req.getParameter("roomId").trim());
				RoomService roomSvc = new RoomService();
				RoomVO roomVO = roomSvc.findByPrimaryKey(roomId);								
				req.setAttribute("roomVO", roomVO); 
				
				
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend_hotel/room/update_room_input.jsp");
				failureView.forward(req, res);
				return;
			}
		}

        if ("RoomInsert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數  輸入格式的錯誤處理*************************/
	
				
			
				

				String roomHotelId = new String(req.getParameter("roomHotelId").trim());
	
				String roomName = req.getParameter("roomName").trim();
				if (roomName == null || (roomName.trim()).length() == 0) {
					errorMsgs.add("請輸入房間名稱");
				}
				
				Integer roomTotalNo = null;
				String roomTotalNoStr = req.getParameter("roomTotalNo").trim();
				if ( "".equals(roomTotalNoStr)) {
					errorMsgs.add("請輸入房間總數");
				}else{
					 roomTotalNo = new Integer(roomTotalNoStr);
				}
				
				
				Integer roomPrice = null;
				String roomPriceStr = req.getParameter("roomPrice").trim();
				if ( "".equals(roomPriceStr)) {
					errorMsgs.add("請輸入房間定價");
				}else{
					roomPrice = new Integer(roomPriceStr);
				}
				
			
				boolean roomForSell= false;			
//				boolean roomForSell;
//				try{
//				roomForSell = getBoolean(req.getParameter("roomForSell").trim());
//				if(req.getParameter("roomForSell").trim().length()==0)
//				{
//					errorMsgs.add("請輸入是否上架");
//				}
//				}catch(Exception e){
//					roomForSell = false;
//				}
				
				
				
				
				boolean roomForSellAuto = false;
//				try{
//					roomForSellAuto = getBoolean(req.getParameter("roomForSellAuto").trim());
//					if(req.getParameter("roomForSellAuto").trim().length()==0)
//					{
//						errorMsgs.add("請輸入是否開啟定時上架");
//					}
//				}catch(Exception e){
//					roomForSellAuto = false;
//				}
																
				Integer roomRemainNo = 0;
			
				
				Integer roomDefaultNo = 0;
//				String roomDefaultNoStr = req.getParameter("roomDefaultNo").trim();
//				if ( "".equals(roomDefaultNoStr)) {
//					errorMsgs.add("請輸入每日預定上架房數");
//				}else{
//					roomDefaultNo = new Integer(roomDefaultNoStr);
//				}
				
	
			
				//roomDiscountStartDate
				Integer roomDiscountStartDate =0;
//				Integer roomDiscountStartDateHour = (new Integer(req.getParameter("roomDiscountStartDatehour").trim()))*60*60*1000;
//				Integer roomDiscountStartDateMinute = (new Integer(req.getParameter("roomDiscountStartDateminute").trim()))*60*1000;
////				Integer roomDiscountStartDateSecond = (new Integer(req.getParameter("roomDiscountStartDateSecond").trim()))*1000;
////				Integer roomDiscountStartDate = roomDiscountStartDateHour+ roomDiscountStartDateMinute + roomDiscountStartDateSecond;
////				if(roomDiscountStartDateHour==0&&roomDiscountStartDateMinute==0&&roomDiscountStartDateSecond==0){
////					errorMsgs.add("請輸入折扣開始時間");		
////				}	
//				Integer roomDiscountStartDate = roomDiscountStartDateHour+ roomDiscountStartDateMinute ;
//				if(roomForSellAuto==true){
//					if(roomDiscountStartDateHour==0&&roomDiscountStartDateMinute==0){
//	
//						errorMsgs.add("請輸入定時上架開始時間");		
//					}
//				}
//				
				
				
				
		
				//roomDiscountEndDate
				Integer roomDiscountEndDate = 0;
//				Integer roomDiscountEndDateHour = (new Integer(req.getParameter("roomDiscountEndDatehour").trim()))*60*60*1000;
//				Integer roomDiscountEndDateMinute = (new Integer(req.getParameter("roomDiscountEndDateminute").trim()))*60*1000;
//				Integer roomDiscountEndDateSecond = (new Integer(req.getParameter("roomDiscountEndDateSecond").trim()))*1000;
//				Integer roomDiscountEndDate = roomDiscountEndDateHour+ roomDiscountEndDateMinute + roomDiscountEndDateSecond;
//				if(roomDiscountStartDate>roomDiscountEndDate){
//					errorMsgs.add("折扣結束時間得較折扣開始時間晚");		
//				}
				
//				Integer roomDiscountEndDateHour = (new Integer(req.getParameter("roomDiscountEndDatehour").trim()))*60*60*1000;
//				Integer roomDiscountEndDateMinute = (new Integer(req.getParameter("roomDiscountEndDateminute").trim()))*60*1000;
//				
//				Integer roomDiscountEndDate = roomDiscountEndDateHour+ roomDiscountEndDateMinute;
//				if(roomForSellAuto==true){
//					if(roomDiscountStartDate>roomDiscountEndDate){
//						errorMsgs.add("下架時間時間得較開始時間晚");		
//					}	
//				}
//				if(roomDiscountEndDate==0){
//					
//						errorMsgs.add("請輸入下架時間");		
//						
//				}
				
		
				//roomDisccountPercent
				Integer roomDisccountPercent = 0 ;
//				Integer roomDisccountPercent = (new Integer(req.getParameter("roomDisccountPercent").trim()));
	
			
				//roomDiscountHr
				Integer roomDiscountHr = 0;
//				Integer roomDiscountHr = new Integer(req.getParameter("roomDiscountHr").trim());
			
				//roomOnePrice
				Integer roomBottomPrice = 0;
				
				
				boolean roomOnePrice = false;
//				try{
//					
//					roomOnePrice = getBoolean(req.getParameter("roomOnePrice").trim());
//					if(req.getParameter("roomOnePrice").trim().length()==0)
//					{
//						errorMsgs.add("請輸入是否一價到底");
//					}	
//				}catch(Exception e){
//					errorMsgs.add("請輸入是否一價到底");	
//					roomOnePrice = false;
//				}
			
				//roomFun
				String roomFun = new String(req.getParameter("roomFun").trim());
				if (roomFun == null || (roomFun.trim()).length() == 0) {
					errorMsgs.add("請輸入娛樂    若沒有請註明'無'");
				}
				//roomMeal
				String roomMeal = new String(req.getParameter("roomMeal").trim());
				if (roomMeal == null || (roomMeal.trim()).length() == 0) {
					errorMsgs.add("請輸入餐飲    若沒有請註明'無'");
				}
				//roomSleep
				String roomSleep = new String(req.getParameter("roomSleep").trim());
				if (roomSleep == null || (roomSleep.trim()).length() == 0) {
					errorMsgs.add("請輸入舒適睡眠    若沒有請註明'無'");
				}
				//roomFacility
				String roomFacility = new String(req.getParameter("roomFacility").trim());
				if (roomFacility == null || (roomFacility.trim()).length() == 0) {
					errorMsgs.add("請輸入設施    若沒有請註明'無'");
				}
				//roomSweetFacility
				String roomSweetFacility = new String(req.getParameter("roomSweetFacility").trim());
				if (roomSweetFacility == null || (roomSweetFacility.trim()).length() == 0) {
					errorMsgs.add("請輸入貼心服務    若沒有請註明'無'");
				}
				//roomCapacity
				Integer roomCapacity = new Integer(req.getParameter("roomCapacity").trim());				
				//roomCapacity
				Integer roomOneBed = new Integer(req.getParameter("roomOneBed").trim());
				//roomCapacity
				Integer roomTwoBed = new Integer(req.getParameter("roomTwoBed").trim());
				if(roomOneBed+roomTwoBed*2<roomCapacity)	{
					errorMsgs.add("床數太少,不符合入住人數");	
				}	
				
				System.out.println("開始包VO");
				System.out.println(roomTotalNo);
				RoomVO roomVO = new RoomVO();
				
				roomVO.setRoomBottomPrice(roomBottomPrice);
				roomVO.setRoomHotelId(roomHotelId);
				roomVO.setRoomName(roomName);
				roomVO.setRoomTotalNo(roomTotalNo);
				roomVO.setRoomPrice(roomPrice);
				roomVO.setRoomForSell(roomForSell);
				roomVO.setRoomForSellAuto(roomForSellAuto);
				roomVO.setRoomRemainNo(roomRemainNo);
				roomVO.setRoomDefaultNo(roomDefaultNo);
				roomVO.setRoomDiscountStartDate(roomDiscountStartDate);
				roomVO.setRoomDiscountEndDate(roomDiscountEndDate);
				roomVO.setRoomDisccountPercent(roomDisccountPercent);
				roomVO.setRoomDiscountHr(roomDiscountHr);
				roomVO.setRoomOnePrice(roomOnePrice);
				roomVO.setRoomFun(roomFun);
				roomVO.setRoomMeal(roomMeal);
				roomVO.setRoomSleep(roomSleep);
				roomVO.setRoomFacility(roomFacility);
				roomVO.setRoomSweetFacility(roomSweetFacility);
				roomVO.setRoomCapacity(roomCapacity);
				roomVO.setRoomOneBed(roomOneBed);
				roomVO.setRoomTwoBed(roomTwoBed);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("roomVO", roomVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend_hotel/room/AddRoom.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				
					
				//處理圖片
				Collection<Part> parts = req.getParts();
				
				
				List<byte[]> imgbyte = new ArrayList<byte[]>();
				int count = 0;
				for(Part part:parts){
					//		System.out.println(part.getContentType());
					//		System.out.println(part.getName());
						
						if("image/jpeg".equals(part.getContentType())){
						java.io.InputStream in = part.getInputStream();
						byte[] Picbyte =SetPic(in);
						
						
						imgbyte.add(Picbyte);
					    count ++;
						}
				
				}
				
				
				
				
				if(count == 0){
					errorMsgs.add("請輸入jpg圖檔");
				}
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("roomVO", roomVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend_hotel/room/AddRoom.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				/***************************2.開始新增資料***************************************/
				System.out.println("開始新增資料");
				
				
				try{
				RoomService roomSvc = new RoomService();
				roomSvc.insert(roomVO,imgbyte);
				}catch(Exception e){
					errorMsgs.add("房型新增資料有錯");	
				}
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("roomVO", roomVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend_hotel/room/AddRoom.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				

				System.out.println("資料新增完畢");
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/frontend_hotel/room/listAllRoom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				return;
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				System.out.println("沒包到VO直接跳錯了");
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend_hotel/room/AddRoom.jsp");
				failureView.forward(req, res);
				return;
			}
		}
		
        if ("getAllRoom_ForOneHotel_Display".equals(action)){
       
        	String hotelId =req.getParameter("hotelId");
//        	RoomService roomSvc = new RoomService();
//        	Set<RoomVO> roomSet = roomSvc.getOneHotelAllRoom(hotelId);     	    	
//        	req.setAttribute("RoomSet", roomSet);
        	String  url ="/frontend_hotel/room/listAllRoom.jsp"; 
        	RequestDispatcher successView = req
					.getRequestDispatcher(url);
        	successView.forward(req, res);
			return;
        	
        }
        
        if ("getAllRoomSell_ForOneHotel_Display".equals(action)){
            
        	String hotelId =req.getParameter("hotelId");

        	String  url ="/frontend_hotel/room/listAllRoomSell.jsp"; 
        	RequestDispatcher successView = req
					.getRequestDispatcher(url);
        	successView.forward(req, res);
			return;
        	
        }
        
        
       
        
        
        
        
        
        
        
        
        
        
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String roomId = new String(req.getParameter("roomId"));
				
				/***************************2.開始刪除資料***************************************/
				RoomService empSvc = new RoomService();
				empSvc.delete(roomId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/room/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/room/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
	}
	
	
	private static boolean getBoolean(String str){

		if("true".equals(str)){
		return true;
		}else{
			
		return false;	
		}
		
		
	} 
	
	private static byte[] SetPic(java.io.InputStream in) {
		
		byte[] bytearr = null;
		try{
		
         BufferedInputStream  bis  =   new  BufferedInputStream(in) ;
         int content =bis.available();
         
         bytearr = new byte[content];
         bis.read(bytearr);     
         
         bis.close();   
               
		}catch(Exception e){
			
		}
		return bytearr;		
	}
	
	private static void DynamicPrice(String roomId,boolean beforeSell,int aCutPriceTime,int price,int CutPrice,int BottomPrice,boolean roomOnePrice,int EndTime) {
		
		 
		if(beforeSell==true){
			try{
			 OnTimer.get(roomId).cancel();	//前期降價排程取消
			 DownTimer.get(roomId).cancel(); //前期下架排程取消	
			}catch(Exception e){}
		}
		 /*為本次新增的房型準被一個動態降價的排程*/
		 Timer timer = new Timer();
		 Timer down = new Timer();
		 OnTimer.put(roomId,timer);
		 DownTimer.put(roomId,down);
		
		 /*為本次新增的房型準被一個List*/
		 Map<String,Integer> data = new HashMap<String,Integer>();
		 data.put("price",price);				//此時價錢
		 data.put("CutPrice",CutPrice);			//單位時間折價	
		 data.put("BottomPrice",BottomPrice);	//最低價錢
		 OnData.put(roomId,data);
		
		
		 
	     TimerTask taskPrice = new TimerTask(){
	        
	         public void run(){
	         	//排程器要執行的任務	
	        	 
	        	
	        	 Map<String,Integer> one = OnData.get(roomId);
	     
	        	 
	        	 int nowPrice = one.get("price");				//此時價錢
	        	 int nowCutPrice = one.get("CutPrice"); 		//單位時間折價
	        	 int nowBottomPrice = one.get("BottomPrice");   //最低價錢
	         
	        	 nowPrice-=nowCutPrice; 
	        	 if(nowPrice< nowBottomPrice){	//達到底價時取消排程,並且讓價格設定為底價
	        		 one.put("price",nowBottomPrice);
	        		 OnTimer.get(roomId).cancel();
	        	 }else{
	        		 one.put("price",nowPrice); //未達底價時繼續降價
	        	 } 
	        	 
	        	System.out.println(one.get("price"));
	         }
	     };
	     
	     
	     if(roomOnePrice==false){     
	     //建立降價排程
//	     timer.scheduleAtFixedRate(taskPrice, aCutPriceTime, aCutPriceTime); 
	     timer.scheduleAtFixedRate(taskPrice, 10000, 10000); 
	     								//執行delay時間		//每次執行間隔時間(毫秒)
											//需要一個java.util.Date物件
	     }
	     
	     
	     TimerTask taskDown = new TimerTask(){
		        
	         public void run(){
	         	//排程器要執行的任務	
	        	 
	        	 RoomService roomSvc = new RoomService();
	        	 RoomVO roomVO = roomSvc.findByPrimaryKey(roomId);
	        	 
	        	 roomVO.setRoomForSell(false);
	        	 roomSvc.update(roomVO);
	        	 
	        	 
	        	 OnTimer.get(roomId).cancel();	//降價排程取消
	        	 OnTimer.remove(roomId);	//清空計時器
	        	 OnData.remove(roomId); 	//清空資料
	        	 System.out.println("下架了");
	        	 
	        	 /*************下架了***************/
	         }
	     };
	     
	     Calendar caler = new GregorianCalendar();
	     int year = caler.get(Calendar.YEAR);
	     int month = caler.get(Calendar.MONTH);
	     int date = caler.get(Calendar.DATE);
	     Calendar TodayTime = new GregorianCalendar(year,month,date,0,0,0);	//取出今日0:0:00的Date物件
	     long todayDate = TodayTime.getTime().getTime()+EndTime; 	
	     Date DateTask = new Date(todayDate); 
	        	     
	     down.schedule(taskDown, DateTask); 
	     		
	}
	public static void RegularOnTime(String roomId){ 
		
		RoomService roomSvc = new RoomService();
		RoomVO roomVO = roomSvc.findByPrimaryKey(roomId);
		if(roomVO.getRoomForSell()==true){
		   return;
		}
		
		System.out.println(roomId + "上架囉~");
		
		
		 /*為本次新增的房型準被一個動態降價的排程*/
		 Timer timer = new Timer();
		 Timer down = new Timer();
		 OnTimer.put(roomId,timer);
		 DownTimer.put(roomId,down);
		 
		 int price = roomVO.getRoomPrice();
		 int roomDisccountPercent = roomVO.getRoomDisccountPercent();
		 
		 Float cutPrice = price*roomDisccountPercent*0.01f;
		 int cutPriceInt = cutPrice.intValue();
		 int BottomPrice = roomVO.getRoomBottomPrice();
		 int EndTime = roomVO.getRoomDiscountEndDate();
		 
		 //將預訂上架房數設為當日上架房數
		 roomVO.setRoomRemainNo(roomVO.getRoomDefaultNo());
		 //將該房型設為上架
		 roomVO.setRoomForSell(true); 
		 roomSvc.update(roomVO);
		 
		 
		 /*為本次新增的房型準被一個List*/
		 Map<String,Integer> data = new HashMap<String,Integer>();
		 data.put("price",price);				//此時價錢
		 data.put("CutPrice",cutPriceInt);		//單位時間折價	
		 data.put("BottomPrice",BottomPrice);	//最低價錢
		 OnData.put(roomId,data);
		
		
		 
	     TimerTask taskPrice = new TimerTask(){
	        
	         public void run(){
	         	//排程器要執行的任務	
	        	 
	        	
	        	 Map<String,Integer> one = OnData.get(roomId);
	     
	        	 
	        	 int nowPrice = one.get("price");				//此時價錢
	        	 int nowCutPrice = one.get("CutPrice"); 		//單位時間折價
	        	 int nowBottomPrice = one.get("BottomPrice");   //最低價錢
	         
	        	 nowPrice-=nowCutPrice; 
	        	 if(nowPrice< nowBottomPrice){	//達到底價時取消排程,並且讓價格設定為底價
	        		 one.put("price",nowBottomPrice);
	        		 OnTimer.get(roomId).cancel();
	        	 }else{
	        		 one.put("price",nowPrice); //未達底價時繼續降價
	        	 } 
	        	 
	        	System.out.println(one.get("price"));
	         }
	     };
	     
	     
	     if(roomVO.getRoomOnePrice()==false){     
	     //建立降價排程
//	     timer.scheduleAtFixedRate(taskPrice, aCutPriceTime, aCutPriceTime); 
	     timer.scheduleAtFixedRate(taskPrice, 10000, 10000); 
	     								//執行delay時間		//每次執行間隔時間(毫秒)
											//需要一個java.util.Date物件
	     }
	     
	     
	     TimerTask taskDown = new TimerTask(){
		        
	         public void run(){
	         	//排程器要執行的任務	
	        	 
	        	 RoomService roomSvc = new RoomService();
	        	 RoomVO roomVO = roomSvc.findByPrimaryKey(roomId);
	        	 
	        	 roomVO.setRoomForSell(false);
	        	 roomSvc.update(roomVO);
	        	 
	        	 
	        	 OnTimer.get(roomId).cancel();	//降價排程取消
	        	 OnTimer.remove(roomId);	//清空計時器
	        	 OnData.remove(roomId); 	//清空資料
	        	 System.out.println("下架了");
	        	 
	        	 /*************下架了***************/
	         }
	     };
	     
	     Calendar caler = new GregorianCalendar();
	     int year = caler.get(Calendar.YEAR);
	     int month = caler.get(Calendar.MONTH);
	     int date = caler.get(Calendar.DATE);
	     Calendar TodayTime = new GregorianCalendar(year,month,date,0,0,0);	//取出今日0:0:00的Date物件
	     long todayDate = TodayTime.getTime().getTime()+EndTime; 	
	     Date DateTask = new Date(todayDate); 
	        	     
	     down.schedule(taskDown, DateTask); 
		
		
		
		
		
		
		
		
	}
	
	
}





