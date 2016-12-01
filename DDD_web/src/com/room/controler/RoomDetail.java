package com.room.controler;

import java.io.BufferedOutputStream;
import java.io.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.room.model.RoomService;
import com.room.model.RoomVO;
import com.roomphoto.model.RoomPhotoService;
import com.wish.model.WishService;
import com.wish.model.WishVO;

/**
 * Servlet implementation class RoomDetail
 */
@WebServlet("/RoomDetail")
public class RoomDetail extends HttpServlet {
	
	static MemService memSvc;
	static RoomService roomSvc;
	static RoomPhotoService roomPhotoSvc;
	static WishService wishSvc;
	
	static{
		roomSvc = new RoomService();
		roomPhotoSvc = new RoomPhotoService();
		wishSvc = new WishService();
		memSvc = new MemService();
	}
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String memId = req.getParameter("memId");
		MemVO memVO = memSvc.getOneMem(memId);
		res.setContentType("image/jpeg");
		ServletOutputStream out = res.getOutputStream(); 
		BufferedOutputStream buf = new BufferedOutputStream(out);	
		
		if(memVO.getMemProfile()!=null){
		buf.write(memVO.getMemProfile());//掃出照片
		}else{
			String gender = memVO.getMemGender();
			FileInputStream img =null;
			if("f".equals(gender)){
				img = new FileInputStream(req.getContextPath()+"/frontend_mem/images/girlhead.png");
			}else if("m".equals(gender)){
			    img = new FileInputStream(req.getContextPath()+"/frontend_mem/images/man.png");
			}
			BufferedInputStream in = new BufferedInputStream(img);
			byte[] buf1 = new byte[4 * 1024]; // 4K buffer
			int len;
			while ((len = in.read(buf1)) != -1) {
				buf.write(buf1, 0, len);
			}
			img.close();
			in.close();	
		}
		buf.close();
		out.close();
		return;
		
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		String action = req.getParameter("action");
		if("getDetail".equals(action)){
			String roomId = req.getParameter("roomId");
			RoomVO roomVO = roomSvc.findByPrimaryKey(roomId);
			
			int oneBed = roomVO.getRoomOneBed();
			int towBed = roomVO.getRoomTwoBed();
			String name = roomVO.getRoomName();
			int capacity = roomVO.getRoomCapacity();
			String fun = roomVO.getRoomFun();
			String meal = roomVO.getRoomMeal();
			String sleep = roomVO.getRoomSleep();
			String sweet = roomVO.getRoomSweetFacility();
			String facility = roomVO.getRoomFacility();
			
			List<String> roomPhotoList = roomPhotoSvc.getRoomAllRoomPhotoId(roomId);
			
			JSONArray roomPhotoId = new JSONArray();
			for(int i = 0; i<roomPhotoList.size();i++){			
				roomPhotoId.put(roomPhotoList.get(i));
			}	//將roomPhotoId 放到jsonArray
			
			
			
			JSONObject data = new JSONObject();
			try {
				data.put("roomId", roomId);
				data.put("name", name);
				data.put("oneBed", oneBed);
				data.put("towBed", towBed);
				data.put("capacity", capacity);
				data.put("fun", fun);
				data.put("meal", meal);
				data.put("sleep", sleep);
				data.put("sweet", sweet);
				data.put("facility", facility);			
				data.put("roomPhotoId", roomPhotoId);
				
				HttpSession session = req.getSession(); 
				MemVO memVO = (MemVO)session.getAttribute("memVO");
				if(memVO==null){
					data.put("memId","null");	
				}else{
					data.put("memId",memVO.getMemId());
				}
				
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
			
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
//			System.out.println("array.toString(): " + data.toString());
			out.write(data.toString());	//輸出所搜尋到符合條件的旅館資料
//			System.out.println(data.toString());
			out.close();
		}
		
		
		

		if("setLove".equals(action)){
		
//		System.out.println("加入願望清單");
			res.setCharacterEncoding("utf-8");
			String roomId = req.getParameter("roomId");	
			String memId = req.getParameter("memId");
			
			WishVO wishVO = wishSvc.getOneWish(memId, roomId);
			if(wishVO==null){
			wishSvc.addWish(memId, roomId);			
			
			PrintWriter out = res.getWriter();
			out.write("加入願望清單成功");
			out.close();
			return;
			}else{
				PrintWriter out = res.getWriter();
				out.write("您已加入願望清單");
				out.close();
			}
			
				
		}
		
	}

}
