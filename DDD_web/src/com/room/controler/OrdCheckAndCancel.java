package com.room.controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OrdCheckAndCancel
 */
@WebServlet("/OrdCheckAndCancel")
public class OrdCheckAndCancel extends HttpServlet {
	
       
  

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("阿宏好棒");
	
		doPost(req,res);
		
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	req.setCharacterEncoding("utf-8");
		
		String action = req.getParameter("action");
		
		if("confirm".equals(action)){
			
			System.out.println("11111");
			String key = req.getParameter("ordMsgNo");
			String ordId = req.getParameter("ordId");
			
			RoomSetOrder.checkOrder(key,ordId);
			
			
			return;
		}
		
		
		if("cancel".equals(action)){
			
			String key = req.getParameter("ordMsgNo");
			String ordId = req.getParameter("ordId");
			
			RoomSetOrder.cancelOrder(key,ordId);
			
			
			return;
		}
		
		
	}

}
