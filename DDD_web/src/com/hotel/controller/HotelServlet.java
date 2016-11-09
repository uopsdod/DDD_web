package com.hotel.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.hotel.model.HotelService;
import com.hotel.model.HotelVO;

import util.AddressToLat;
import util.Util_psw;
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)


public class HotelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String action = request.getParameter("action");
		System.out.println(action);
		
		
		
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
		 
		//我的
		 if ("inserthotel".equals(action)){
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				request.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String hotelType=request.getParameter("hotelType").trim();
					
					
					String hotelName = request.getParameter("hotelName").trim();
					if(hotelName.trim().isEmpty()){
						errorMsgs.add("請輸入貴旅館的名字");
					}
					
					String hotelTaxId = request.getParameter("hotelTaxId").trim();
					if(hotelTaxId.trim().isEmpty()){
						errorMsgs.add("請輸入統一編號");
					}
					
					String hotelCity = request.getParameter("hotelCity").trim();
					if(hotelCity.trim().isEmpty()){
						errorMsgs.add("請輸入縣市");
					}
					
					String hotelCounty = request.getParameter("hotelCounty").trim();
					if(hotelCounty.trim().isEmpty()){
						errorMsgs.add("請輸入鄉鎮區");
					}
					
					String hotelRoad = request.getParameter("hotelRoad").trim();
					if(hotelRoad.trim().isEmpty()){
						errorMsgs.add("請輸入路名牌號");
					}
					//地址轉
					String lat_lng=hotelCity+hotelCity+hotelRoad;
					Double hotelLon=null;
					Double hotelLat=null;
					if(lat_lng.isEmpty()){
						errorMsgs.add("請輸入地址");
					}else{
					String lat_lng_result=AddressToLat.turn(lat_lng);
					
					String[] array = lat_lng_result.split(",");
					String array0=array[0];
					String array1=array[1];
					 hotelLon =Double.parseDouble(array1);
					 hotelLat = Double.parseDouble(array0);

					System.out.println(hotelLon);
					System.out.println(hotelLat);
					}
					
					
					String hotelOwner = request.getParameter("hotelOwner").trim();
					if(hotelOwner.trim().isEmpty()){
						errorMsgs.add("請輸入負責人");
					}
					
					String hotelAccount = request.getParameter("hotelAccount").trim();
					if(hotelAccount.trim().isEmpty()){
						errorMsgs.add("信箱不能空白");
					}else{
						if(!hotelAccount.matches("^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$")){
							errorMsgs.add("信箱格式錯誤");
						}	
					}
					//進DB亂數
					String hotelPwd = request.getParameter("hotelPwd").trim();
					if(hotelPwd.trim().isEmpty()){
						errorMsgs.add("密碼不能空白");
					}else{
						if(!hotelPwd.matches("^[a-zA-Z]\\w{6,10}$")){
							errorMsgs.add("以字母開頭，長度在6-10之間，只能包含字符、數字和下劃線。");
						}	
					}
					
					String hotelLink = request.getParameter("hotelLink").trim();
					if(hotelLink.trim().isEmpty()){
						errorMsgs.add("網站連結不能空");
					}

					String hotelPhone = request.getParameter("hotelPhone").trim();
					if(hotelPhone.trim().isEmpty()){
						errorMsgs.add("電話號碼必填");
					}else{
						if(!hotelPhone.matches("^[0-9]*$")){
							errorMsgs.add("電話只能輸入數字");
						}	
					}
					
					String hotelCreditCardNo = request.getParameter("hotelCreditCardNo").trim();
					if(hotelCreditCardNo.trim().isEmpty()){
						errorMsgs.add("信用卡號必填");
					}
					
					String hotelCreditCheckNo = request.getParameter("hotelCreditCheckNo").trim();
					if(hotelCreditCheckNo.trim().isEmpty()){
						errorMsgs.add("信用卡驗證碼必填");
					}
					
					String hotelCreditDueDate = request.getParameter("hotelCreditDueDate").trim();
					if(hotelCreditDueDate.trim().isEmpty()){
						errorMsgs.add("信用卡有效日期必填");
					}				
					
					//旅館業者登記証
					Part part = request.getPart("upfile1");	
					
					InputStream in = part.getInputStream();
					byte[] buf = new byte[in.available()];
					in.read(buf);
					in.close();				
					
					//封面照片:
					Part part1 = request.getPart("upfile2");	
					
					InputStream in1 = part1.getInputStream();
					byte[] buf1 = new byte[in1.available()];
					in1.read(buf1);
					in1.close();	
					
					String	hotelIntro = request.getParameter("hotelIntro").trim();		
					if(hotelIntro.trim().isEmpty()){
						errorMsgs.add("廠商簡介必填");
					}	
					
					String	hotelStatus = request.getParameter("hotelStatus").trim();		
					String	hotelBlackList = request.getParameter("hotelBlackList").trim();		
					String	hotelRatingTotal0 = request.getParameter("hotelRatingTotal").trim();
					String	hotelRatingResult0 = request.getParameter("hotelRatingResult").trim();
					int hotelRatingTotal =Integer.parseInt(hotelRatingTotal0);
					int hotelRatingResult = Integer.parseInt(hotelRatingResult0);	
					
					
					HotelVO hotelVO = new HotelVO();
					 hotelVO.setHotelType(hotelType);
					 hotelVO.setHotelName(hotelName);
					 hotelVO.setHotelTaxId(hotelTaxId);
					 hotelVO.setHotelRegisterPic(buf);
					 hotelVO.setHotelCity(hotelCity);
					 hotelVO.setHotelCounty(hotelCounty);
					 hotelVO.setHotelRoad(hotelRoad);
					 hotelVO.setHotelOwner(hotelOwner);
					 hotelVO.setHotelAccount(hotelAccount);
					 hotelVO.setHotelPwd(hotelPwd);
					 hotelVO.setHotelPhone(hotelPhone);
					 hotelVO.setHotelLon(hotelLon);
					 hotelVO.setHotelLat(hotelLat);
					 hotelVO.setHotelIntro(hotelIntro);
					 hotelVO.setHotelLink(hotelLink);
					 hotelVO.setHotelCoverPic(buf1);
					 hotelVO.setHotelStatus(hotelStatus);
					 hotelVO.setHotelBlackList(hotelBlackList);
					 hotelVO.setHotelRatingTotal(hotelRatingTotal);
					 hotelVO.setHotelRatingResult(hotelRatingResult);
					 hotelVO.setHotelCreditCardNo(hotelCreditCardNo);
					 hotelVO.setHotelCreditCheckNo(hotelCreditCheckNo);
					 hotelVO.setHotelCreditDueDate(hotelCreditDueDate);
					 HotelService hotelSvc = new HotelService();
						List<HotelVO> account= hotelSvc.getAll();
						for (HotelVO ahotel : account) {
							if(ahotel.getHotelAccount().equals(hotelAccount)){
								errorMsgs.add("此帳戶已存在");
							}
						}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						request.setAttribute("hotelVO", hotelVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = request
								.getRequestDispatcher("/frontend_hotel/hotel/addhotel.jsp");
						failureView.forward(request, response);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					
					hotelVO = hotelSvc.addHotel(hotelType, hotelName, hotelTaxId,buf, hotelCity, hotelCounty,hotelRoad,hotelOwner,
							hotelAccount,hotelPwd,hotelPhone,hotelLon,hotelLat,hotelIntro,hotelLink,buf1,hotelStatus,hotelBlackList,
							hotelRatingTotal,hotelRatingResult,hotelCreditCardNo,hotelCreditCheckNo,hotelCreditDueDate);
					// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
					//Util_psw.sendMail(hotelAccount, hotelOwner+"您好歡迎您加入本公司成為工作夥伴，請查收您的私密資料", "您的帳號為 : "+hotelAccount+"您的密碼為 : "+hotelPwd +"請等候我們審核您的資料，謝謝您。");
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/frontend_hotel/hotel/register.jsp";
					RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(request, response);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = request
							.getRequestDispatcher("/frontend_hotel/hotel/addhotel.jsp");
					failureView.forward(request, response);
				}
			}
		 
		 
		 if ("updatehotel".equals(action)) { // 來自listAllEmp.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				request.setAttribute("errorMsgs", errorMsgs);
				
				String requestURL = request.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】

				
				try {
					/***************************1.接收請求參數****************************************/
					String hotelType=request.getParameter("hotelType").trim();
					
					
					String hotelName = request.getParameter("hotelName").trim();
					if(hotelName.trim().isEmpty()){
						errorMsgs.add("請輸入貴旅館的名字");
					}
					
					String hotelTaxId = request.getParameter("hotelTaxId").trim();
					if(hotelTaxId.trim().isEmpty()){
						errorMsgs.add("請輸入統一編號");
					}
					
					String hotelCity = request.getParameter("hotelCity").trim();
					if(hotelCity.trim().isEmpty()){
						errorMsgs.add("請輸入縣市");
					}
					
					String hotelCounty = request.getParameter("hotelCounty").trim();
					if(hotelCounty.trim().isEmpty()){
						errorMsgs.add("請輸入鄉鎮區");
					}
					
					String hotelRoad = request.getParameter("hotelRoad").trim();
					if(hotelRoad.trim().isEmpty()){
						errorMsgs.add("請輸入路名牌號");
					}
					//地址轉
					String lat_lng=hotelCity+hotelCity+hotelRoad;
					Double hotelLon=null;
					Double hotelLat=null;
					if(lat_lng.isEmpty()){
						errorMsgs.add("請輸入地址");
					}else{
					String lat_lng_result=AddressToLat.turn(lat_lng);
					
					String[] array = lat_lng_result.split(",");
					String array0=array[0];
					String array1=array[1];
					 hotelLon =Double.parseDouble(array1);
					 hotelLat = Double.parseDouble(array0);

					System.out.println(hotelLon);
					System.out.println(hotelLat);
					}
					
					
					String hotelOwner = request.getParameter("hotelOwner").trim();
					if(hotelOwner.trim().isEmpty()){
						errorMsgs.add("請輸入負責人");
					}
					
					String hotelAccount = request.getParameter("hotelAccount").trim();
					if(hotelAccount.trim().isEmpty()){
						errorMsgs.add("信箱不能空白");
					}else{
						if(!hotelAccount.matches("^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$")){
							errorMsgs.add("信箱格式錯誤");
						}	
					}
					//進DB亂數
					String hotelPwd = request.getParameter("hotelPwd").trim();
					if(hotelPwd.trim().isEmpty()){
						errorMsgs.add("請輸入密碼");
					}else{
						if(!hotelPwd.matches("^[a-zA-Z]\\w{6,10}$")){
							errorMsgs.add("以字母開頭，長度在6-10之間，只能包含字符、數字和下劃線。");
						}	
					}
					
					String hotelLink = request.getParameter("hotelLink").trim();
					if(hotelLink.trim().isEmpty()){
						errorMsgs.add("網站連結不能空");
					}

					String hotelPhone = request.getParameter("hotelPhone").trim();
					if(hotelPhone.trim().isEmpty()){
						errorMsgs.add("電話號碼必填");
					}else{
						if(!hotelPhone.matches("^[0-9]*$")){
							errorMsgs.add("電話只能輸入數字");
						}	
					}
					
					String hotelCreditCardNo = request.getParameter("hotelCreditCardNo").trim();
					if(hotelCreditCardNo.trim().isEmpty()){
						errorMsgs.add("信用卡號必填");
					}
					
					String hotelCreditCheckNo = request.getParameter("hotelCreditCheckNo").trim();
					if(hotelCreditCheckNo.trim().isEmpty()){
						errorMsgs.add("信用卡驗證碼必填");
					}
					
					String hotelCreditDueDate = request.getParameter("hotelCreditDueDate").trim();
					if(hotelCreditDueDate.trim().isEmpty()){
						errorMsgs.add("信用卡有效日期必填");
					}				
					
					//旅館業者登記証
					Part part = request.getPart("upfile1");	
					
					InputStream in = part.getInputStream();
					byte[] buf = new byte[in.available()];
					in.read(buf);
					in.close();				
					
					//封面照片:
					Part part1 = request.getPart("upfile2");	
					
					InputStream in1 = part1.getInputStream();
					byte[] buf1 = new byte[in1.available()];
					in1.read(buf1);
					in1.close();	
					
					String	hotelIntro = request.getParameter("hotelIntro").trim();		
					if(hotelIntro.trim().isEmpty()){
						errorMsgs.add("廠商簡介必填");
					}	
					
					String	hotelId = request.getParameter("hotelId").trim();		
					
					
					
					
					HotelVO hotelVO = new HotelVO();
					 hotelVO.setHotelType(hotelType);
					 hotelVO.setHotelName(hotelName);
					 hotelVO.setHotelTaxId(hotelTaxId);
					 hotelVO.setHotelRegisterPic(buf);
					 hotelVO.setHotelCity(hotelCity);
					 hotelVO.setHotelCounty(hotelCounty);
					 hotelVO.setHotelRoad(hotelRoad);
					 hotelVO.setHotelOwner(hotelOwner);
					 hotelVO.setHotelAccount(hotelAccount);
					 hotelVO.setHotelPwd(hotelPwd);
					 hotelVO.setHotelPhone(hotelPhone);
					 hotelVO.setHotelLon(hotelLon);
					 hotelVO.setHotelLat(hotelLat);
					 hotelVO.setHotelIntro(hotelIntro);
					 hotelVO.setHotelLink(hotelLink);
					 hotelVO.setHotelCoverPic(buf1);
					 hotelVO.setHotelCreditCardNo(hotelCreditCardNo);
					 hotelVO.setHotelCreditCheckNo(hotelCreditCheckNo);
					 hotelVO.setHotelCreditDueDate(hotelCreditDueDate);
					 hotelVO.setHotelId(hotelId);
					 
//						List<HotelVO> account= hotelSvc.getAll();
//						for (HotelVO ahotel : account) {
//							if(ahotel.getHotelAccount().equals(hotelAccount)){
//								errorMsgs.add("此帳戶已存在");
//							}
//						}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						request.setAttribute("hotelVO", hotelVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = request
								.getRequestDispatcher("/frontend_hotel/hotel/findByPrimaryKey.jsp");
						failureView.forward(request, response);
						return;
					}
					
					/***************************2.開始查詢資料****************************************/
					HotelService hotelSvc = new HotelService();
					hotelVO = hotelSvc.updateBasic(hotelType,hotelName,hotelTaxId,buf,
							hotelCity,hotelCounty,hotelRoad,hotelOwner,hotelAccount,
							hotelPwd,hotelPhone,hotelLon,hotelLat,hotelIntro,
							buf1,hotelLink,hotelCreditCardNo,hotelCreditCheckNo,
							hotelCreditDueDate,hotelId);
									
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					request.setAttribute("hotelVO", hotelVO);         // 資料庫取出的empVO物件,存入req
					String url ="/frontend_hotel/hotel/findByPrimaryKey.jsp";
					RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
					successView.forward(request, response);

					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					RequestDispatcher failureView = request
							.getRequestDispatcher("/frontend_hotel/hotel/findByPrimaryKey.jsp");
					failureView.forward(request, response);
				}
			}
		 
		 
	}

}
