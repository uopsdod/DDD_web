package com.roomphoto.controler;

import java.io.*;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONArray;
import org.omg.CORBA.portable.InputStream;

import com.room.model.RoomService;
import com.room.model.RoomVO;
import com.roomphoto.model.RoomPhotoService;
import com.roomphoto.model.RoomPhotoVO;

/**
 * Servlet implementation class RoomPhotoServlet
 */



@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
@WebServlet("/RoomPhotoServlet")
public class RoomPhotoServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String roomPhotoId = req.getParameter("roomPhotoId");
				/***************************2.開始查詢資料*****************************************/
				RoomPhotoService RoomPhotoSvc = new RoomPhotoService();
				RoomPhotoVO roomPhotoVO = RoomPhotoSvc.getOneRoomPhoto(roomPhotoId);
				res.setContentType("image/jpeg");
				ServletOutputStream out = res.getOutputStream(); 
				BufferedOutputStream buf = new BufferedOutputStream(out);	
				buf.write(roomPhotoVO.getRoomPhotoPic());//掃出照片
				
				buf.close();
				out.close();
				return;
		}
		
		if ("getAllId_For_OneRoom".equals(action)) { // 來自select_page.jsp的請求
			
			
		
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String roomPhotoRoomId = req.getParameter("roomPhotoRoomId");
			/***************************2.開始查詢資料*****************************************/
			RoomPhotoService RoomPhotoSvc = new RoomPhotoService();
			List<String> RoomPhotoIdList = RoomPhotoSvc.getRoomAllRoomPhotoId(roomPhotoRoomId);
			
			JSONArray array = new JSONArray();
			for(String photoId:RoomPhotoIdList)
			{
				 array.put(photoId);
			}
			
			res.setContentType("text/plain");
		
			PrintWriter out = res.getWriter();
			out.write(array.toString());
			out.flush();
			out.close();
			
			return;
					
	}
			
		if ("upPic".equals(action)) {
			
			String roomPhotoRoomId = req.getParameter("roomPhotoRoomId");
			String HotelId = req.getParameter("HotelId");
			String url = req.getParameter("root");
			Collection<Part> parts = req.getParts();
			RoomPhotoService RoomPhotoSvc = new RoomPhotoService();
		
			
			
			for(Part part:parts){
					System.out.println(part.getContentType());
			
					if("image/jpeg".equals(part.getContentType())){
					java.io.InputStream in = part.getInputStream();
					byte[] Picbyte =SetPic(in);
					
						
					//RoomPhotoSvc.insertRoomPhoto(HotelId, Picbyte);
			
					}
			}
			
			String root = req.getParameter("root");			
			String roomId = req.getParameter("roomId");
		
			List RoomPhotoId = RoomPhotoSvc.getRoomAllRoomPhotoId(roomId);
			RoomService roomSvc = new RoomService();
			RoomVO roomVO = roomSvc.findByPrimaryKey(roomId);
			
			req.setAttribute("roomVO", roomVO);
			req.setAttribute("RoomPhotoId", RoomPhotoId);
		
			RequestDispatcher successView = req.getRequestDispatcher(root); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
			return;
			
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
}

