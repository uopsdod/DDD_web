package com.room.controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.room.model.RoomService;
import com.room.model.RoomVO;

import java.util.*;
/**
 * Servlet implementation class HotelRoomSearch
 */
@WebServlet("/HotelRoomSearch")
public class HotelRoomSearch extends HttpServlet {
	
  

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		
		
		
		
		
		
		
		
		
	
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		
		
		
		String city = req.getParameter("city");
		System.out.println(city);
		String zone = req.getParameter("zone");
		System.out.println(zone);
		String hotelRatingResult = req.getParameter("hotelRatingResult");
		System.out.println(hotelRatingResult);
		
		
		Map map = new HashMap();
		map.put("hotelCity", city);
		map.put("hotelCounty", zone);
		map.put("hotelRatingResult", hotelRatingResult);
		
		
		
		
//		RoomService roomSvc = new RoomService();
//		RoomVO roomVO = null;
//		
//		Set<String> keys= RoomServlet.OnData.keySet();
//		for(String key:keys){			
//			roomVO = roomSvc.findByPrimaryKey(key);	
//			
//		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
