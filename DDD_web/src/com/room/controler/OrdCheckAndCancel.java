package com.room.controler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ord.model.OrdService;


/**
 * Servlet implementation class OrdCheckAndCancel
 */
@WebServlet("/OrdCheckAndCancel")
public class OrdCheckAndCancel extends HttpServlet {
	
       
  

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
	
		doPost(req,res);
		
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		
		
				
		req.setCharacterEncoding("utf-8");
		
		
		String ordIdCheckTime = req.getParameter("ordId");
		OrdService ordSvc = new OrdService();
		com.ord.model.OrdVO ordVO = ordSvc.getOneOrd(ordIdCheckTime);
		String status = ordVO.getOrdStatus();
		if(!"0".equals(status)){		
			req.setAttribute("OrdMessage","訂單已逾期");
			RequestDispatcher failureView = req
					.getRequestDispatcher("/frontend_mem/ord/listAllOrdByMemId.jsp");
			failureView.forward(req, res);
			return;
		}
		
		
		
		
		String action = req.getParameter("action");
		
		if("confirm".equals(action)){
			
//			System.out.println("11111");
			String key = req.getParameter("ordMsgNo");
			String ordId = req.getParameter("ordId");
			
			RoomSetOrder.checkOrder(key,ordId);
			
			
			return;
		}
		
		
		if("cancel".equals(action)){
			
			String key = req.getParameter("ordMsgNo");
			String ordId = req.getParameter("ordId");
			
			RoomSetOrder.cancelOrder(key,ordId);
			
			
			req.setAttribute("OrdMessage","訂單已取消");
			RequestDispatcher failureView = req
					.getRequestDispatcher("/frontend_mem/ord/listAllOrdByMemId.jsp");
			failureView.forward(req, res);
			return;
		}
		
		
	}

}
