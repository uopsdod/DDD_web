package com.room.controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotel.model.HotelService;
import com.hotel.model.HotelVO;
import com.hotelserv.model.HotelServService;
import com.hotelserv.model.HotelServVO;
import com.room.model.RoomService;
import com.room.model.RoomVO;
import com.serv.model.ServService;
import com.serv.model.ServVO;

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
		String zone = req.getParameter("zone");
		String hotelRatingResult = req.getParameter("hotelRatingResult");
		
	
		Map map = new HashMap();
		map.put("HOTELCITY", city);
		map.put("HOTELCOUNTY", zone);
		map.put("HOTELRATINGRESULT", hotelRatingResult);
		
		String SQL = HotelCompositeQuery.GetSQLString(map);	//取得SQL指令
		
		
	
		
		
		
		
		HotelService hotelSvc = new HotelService();
		List<HotelVO>  hotelVOlist =  hotelSvc.getListBySql(SQL);	//下條件SQL指令得到符合條件的VO
		
		HotelServService hotelServService = new HotelServService();
		ServService servSvc = new ServService();
		RoomService roomSvc = new RoomService();
		
		for(HotelVO hotelVO:hotelVOlist){
			String str = hotelVO.getHotelId();
			System.out.println(hotelVO.getHotelId());
			
			List<HotelServVO> hotelServVOList= hotelServService.getAllByHotelId(hotelVO.getHotelId());
			for(HotelServVO hotelServVO:hotelServVOList){
				//System.out.println(str +" :  " + hotelServVO.getHotelServServId());
				
				ServVO servVO =servSvc.getOneServ(hotelServVO.getHotelServServId());
	
				System.out.println(str + " : " + servVO.getServName());
				
			}
			Map RoomMap = new HashMap();
			RoomMap.put("ROOMHOTELID",str );
			String RoomSQL = RoomCompositeQuery.GetSQLString(RoomMap);	//取得SQL指令
			List<RoomVO> roomVOlist = roomSvc.getListBySQL(RoomSQL);
			for(RoomVO roomVO:roomVOlist){
				System.out.println(roomVO.getRoomName());
			}
			
		}
		
		
		
		
		System.out.println(SQL);
		
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
