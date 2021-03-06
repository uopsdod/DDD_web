package com.wish.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.wish.model.WishService;
import com.wish.model.WishVO;


public class WishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String action = request.getParameter("action");
		System.out.println(action);
		
		if ("insertWish".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			
			request.setAttribute("errorMsgs", errorMsgs);
			String requestURL = request.getParameter("requestURL");
//			try {
				JSONObject obj = new JSONObject();
				JSONObject obj_A = new JSONObject();
				String WISHMEMID = request.getParameter("WISHMEMID").trim();
				String WISHROOMID = request.getParameter("WISHROOMID").trim();
						
				

				WishService wishsvc = new WishService();
				//SELECT wishRoomId FROM wish where wishMemId=?
				List<WishVO> list =wishsvc.getOneWishOfmem(WISHMEMID);
				for(WishVO a:list){
					if(a.getWishRoomId().contains(WISHROOMID)){
						try {
							obj.put("MESSAGE", "您已經加入過此房型了!");
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				if(obj.length()!=0){
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.write(obj.toString());
				out.flush();
				out.close();
				return;
				}else{
					wishsvc.addWish_web(WISHMEMID, WISHROOMID);
					try {
						obj_A.put("MESSAGE", "加入成功");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					response.setContentType("text/plain");
					response.setCharacterEncoding("UTF-8");
					PrintWriter out = response.getWriter();
					out.write(obj_A.toString());
					out.flush();
					out.close();
					return;
				}
//				if (!errorMsgs.isEmpty()) {
//					
//					RequestDispatcher failureView = request.getRequestDispatcher("/frontend_mem/index.jsp");
//					failureView.forward(request, response);
//					return;
//				}
				
				
				
//				String url="/frontend_mem/index.jsp";
//				RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(request, response);
				
				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = request.getRequestDispatcher("requestURL");
//				failureView.forward(request, response);
//			}
		}
		
		if ("delect".equals(action)) { // 來自/dept/listAllDept.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數***************************************/
				String memid = new String(request.getParameter("memid"));
				String roomPhotoRoomId = new String(request.getParameter("roomPhotoRoomId"));
				
				/***************************2.開始刪除資料***************************************/
				WishService wishSvc = new WishService();
				wishSvc.delete(memid,roomPhotoRoomId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
//				String url = "/frontend_mem/wish/wishList.jsp";
//				RequestDispatcher successView = request.getRequestDispatcher(url);// 刪除成功後, 成功轉交 回到 /dept/listAllDept.jsp
//				successView.forward(request, response);
				response.sendRedirect(request.getContextPath()+"/frontend_mem/wish/wishList.jsp");
				
				/***************************其他可能的錯誤處理***********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/frontend_mem/wish/wishList.jsp");
				failureView.forward(request, response);
			}
		}
	}

}
