package com.room.controler;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
/**
 * Servlet implementation class RoomSetOrder
 */
@WebServlet("/RoomSetOrder")
public class RoomSetOrder extends HttpServlet {
	
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
			
		req.setAttribute("turn", "addOrd");		//會員來的網頁借過此
		
		
	doPost(req,res);	
		
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		
		String roomId = req.getParameter("orderRoomId");
		if(!RoomServlet.OnData.containsKey(roomId)&&roomId!=null){
			
			String hotelId = req.getParameter("orderHotelId");																				
			res.sendRedirect(req.getContextPath()+"/HotelRoomSearch?action=hotelPage&hotelId="+hotelId);
			return;
		}
		
		
		HttpSession session = req.getSession(); 
		
		String acount = (String)session.getAttribute("account_mem");
		
		if(acount==null){
			session.setAttribute("orderPrice", req.getParameter("orderPrice"));
			session.setAttribute("orderRoomId", req.getParameter("orderRoomId"));
			session.setAttribute("orderHotelId", req.getParameter("orderHotelId"));		
			session.setAttribute("location", req.getRequestURI()); // 含有輸入格式錯誤的empVO物件,也存入req			
			res.sendRedirect(req.getContextPath()+"/frontend_mem/mem/loginOfmember.jsp");
			return;
			
		}
		
		
		String turn= (String)req.getAttribute("turn");	//將從登入會員過來的請求導向至新增訂單畫面
		if(turn!=null&&"addOrd".equals(turn)){
			String url = "/frontend_mem/hotel/addOrd.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
			return;
		}		
		
		
		
		
		String action = (String)req.getParameter("action");
		System.out.println(action);
		if("addOrd".equals(action)){   //將早已登入會員而從hotelPage過來的請求導向至新增訂單畫面
			session.setAttribute("orderPrice", req.getParameter("orderPrice"));
			session.setAttribute("orderRoomId", req.getParameter("orderRoomId"));
			session.setAttribute("orderHotelId", req.getParameter("orderHotelId"));		
			session.setAttribute("location", req.getRequestURI());
			String url = "/frontend_mem/hotel/addOrd.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
			return;	
		}
		
		
		if("insert".equals(action)){   //將早已登入會員而從hotelPage過來的請求導向至新增訂單畫面
			
			String picture = req.getParameter("picture");			
			String password = (String)session.getAttribute("password");
			if(!password.equals(picture)){
				
				List<String> errorMsgs = new LinkedList<String>();
				errorMsgs.add("驗證碼錯誤");
				req.setAttribute("errorMsgs", errorMsgs);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend_mem/hotel/addOrd.jsp");
				failureView.forward(req, res);
				return;				
			}
			
			
			
			System.out.println("通過驗證");
			
			
			
			
			
			
			
			
			return;	
		}
		
		
		
		
	}

}
