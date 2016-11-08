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
			String RoomId = req.getParameter("RoomId");
			/***************************2.開始查詢資料*****************************************/
			RoomPhotoService RoomPhotoSvc = new RoomPhotoService();
			List<String> RoomPhotoIdList = RoomPhotoSvc.getRoomAllRoomPhotoId(RoomId);
			
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
		
		if ("getAllPic_For_OneRoom".equals(action)) { // 來自select_page.jsp的請求
			
			
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String roomPhotoRoomId = req.getParameter("roomPhotoRoomId");
			/***************************2.開始查詢資料*****************************************/
			RoomPhotoService RoomPhotoSvc = new RoomPhotoService();
			List<RoomPhotoVO> RoomPhotoVOList = RoomPhotoSvc.getOneAllRoomPhotoVO(roomPhotoRoomId);
			
			JSONArray array = new JSONArray();
			for(RoomPhotoVO roomPhotoVO:RoomPhotoVOList)
			{
				 array.put(roomPhotoVO.getRoomPhotoPic());
			}
			
			res.setContentType("text/plain");
		
			PrintWriter out = res.getWriter();
			out.write(array.toString());
			out.flush();
			out.close();
			
			return;
					
	}	
		
	if ("DeleteOneRoomPhoto".equals(action)) { // 來自select_page.jsp的請求
			
			
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String roomPhotoRoomId = req.getParameter("RoomPhotoId");
			/***************************2.開始查詢資料*****************************************/
			RoomPhotoService RoomPhotoSvc = new RoomPhotoService();
			RoomPhotoSvc.deleteRoomPhoto(roomPhotoRoomId);
			
			
			return;
					
	}	
		
		
			
		if ("PhotoUpdate".equals(action)) {
			
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String roomId = null;
			
			try{
			
			roomId = req.getParameter("roomId");
			String HotelId = req.getParameter("hotelId");
			String url = req.getParameter("requestURL");
			Collection<Part> parts = req.getParts();
			RoomPhotoService RoomPhotoSvc = new RoomPhotoService();
			
		
			
			java.io.InputStream in = null;
			int count = 0;
			for(Part part:parts){
					System.out.println(part.getContentType());
					try{
					
							if("image/jpeg".equals(part.getContentType())){
								count++;	
							in = part.getInputStream();
							byte[] Picbyte =SetPic(in);
							RoomPhotoSvc.updateRoomPhoto(HotelId,roomId,Picbyte);
							}
							
		
					}catch(Exception e){
						
						errorMsgs.add("圖片寫入有錯");
					}finally{
						try{
							if(!(in==null)){	
							in.close();
							}
						}catch(Exception e)
						{}
					}					
			}
		
			if(count ==0){
				errorMsgs.add("請輸入jpg圖檔");
			}
			
			
			if (!errorMsgs.isEmpty()) {
				RoomService roomSvc = new RoomService();
				RoomVO roomVO = roomSvc.findByPrimaryKey(roomId);								
				req.setAttribute("roomVO", roomVO);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend_hotel/room/update_room_input.jsp");
				failureView.forward(req, res);
				return; //程式中斷
			}
					
		
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
			return;
				
			
			}catch(Exception e){
				RoomService roomSvc = new RoomService();
				RoomVO roomVO = roomSvc.findByPrimaryKey(roomId);								
				req.setAttribute("roomVO", roomVO); 				
				errorMsgs.add("新增圖檔失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend_hotel/room/update_room_input.jsp");
				failureView.forward(req, res);
				return;
			}
			
		}
	
		
		
		
	}
	
	
	private static byte[] SetPic(java.io.InputStream in) throws IOException {
		
		BufferedInputStream  bis = null;
		byte[] bytearr = null;
		try{
		
         bis  =   new  BufferedInputStream(in) ;
         int content =bis.available();
         
         bytearr = new byte[content];
         bis.read(bytearr);     
         
        
               
		}catch(Exception e){
			  
		}finally{
			 bis.close(); 
		}
		return bytearr;		
	}
}

