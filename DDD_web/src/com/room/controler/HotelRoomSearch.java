package com.room.controler;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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
import com.roomphoto.model.RoomPhotoService;
import com.roomphoto.model.RoomPhotoVO;
import com.serv.model.ServService;
import com.serv.model.ServVO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;
/**
 * Servlet implementation class HotelRoomSearch
 */
@WebServlet("/HotelRoomSearch")
public class HotelRoomSearch extends HttpServlet {
	
  

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		

		String action = req.getParameter("action");
		if("showHotel".equals(action)){
		
			
			
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String hotelId = req.getParameter("hotelId");
			
			/***************************2.開始查詢資料*****************************************/
			HotelService hotelService = new HotelService();
			HotelVO hotelVO = hotelService.getOne(hotelId);
			
			res.setContentType("image/jpeg");
			ServletOutputStream out = res.getOutputStream(); 
			BufferedOutputStream buf = new BufferedOutputStream(out);	
			buf.write(hotelVO.getHotelCoverPic());//掃出照片
			
			buf.close();
			out.close();
			return;
			
		}
	
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		
//		Enumeration<String> enume  = req.getParameterNames();	//查看傳來的東西有什麼
//		while(enume.hasMoreElements()){
//			
//			String name = (String) enume.nextElement();
//			String values[] = req.getParameterValues(name);
//			if(values!=null){
//				for(int i =0;i<values.length;i++){
//					System.out.println(name+"+i+"+values[i]);
//				}
//			}
//			
//			
//		}
	
	String action = req.getParameter("action");
		
		if("search".equals(action)){
		
		
			res.setCharacterEncoding("utf-8");	
			String city = req.getParameter("city"); //取得輸入的城市
			String zone = req.getParameter("zone"); //取得輸入的區
			String hotelRatingResult = req.getParameter("hotelRatingResult"); //取得旅館評分數
			String roomCapacity = req.getParameter("roomCapacity");
			
			
			String price = req.getParameter("Price");		//價錢range處理
			String[] range = price.split(" - ");
			int min = new Integer(range[0].substring(1, range[0].length()));	//取得價錢下限
			int max = new Integer(range[1].substring(1, range[1].length()));	//取得價錢上限	
			
	
			String[] servItem = req.getParameterValues("servItem"); //checkBox //取得旅館設施
			
			
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
			
			
			JSONArray array = new JSONArray(); //包裝回送資料
			
			
			
			if(hotelVOlist.size()!=0){
				
				for(HotelVO hotelVO:hotelVOlist){
				
					JSONObject obj = new JSONObject();					
					String hotelId = hotelVO.getHotelId();
					Double hotelLon = hotelVO.getHotelLon();
					Double hotelLat = hotelVO.getHotelLat();
					String hotelName = hotelVO.getHotelName();
					int hotelRating = hotelVO.getHotelRatingResult();
	//				System.out.println(hotelVO.getHotelId());
					JSONArray RoomIdarray= null;
					int bottom =0;
					
		
					
					//hotel查詢上架房型條件
					Map RoomMap = new HashMap();	
					RoomMap.put("ROOMCAPACITY",roomCapacity );
					RoomMap.put("ROOMHOTELID",hotelId );
					RoomMap.put("ROOMFORSELL","1" );
					String RoomSQL = RoomCompositeQuery.GetSQLString(RoomMap);	//取得SQL指令
					List<RoomVO> roomVOlist = roomSvc.getListBySQL(RoomSQL);
						
					if(roomVOlist.size()!=0){
								
						RoomIdarray = new JSONArray();			
					    bottom = 1000000 ;
						
						for(RoomVO roomVO:roomVOlist){
							
							JSONObject Room = new JSONObject();//用來儲存符合條件的房型資料
													
							Map one =RoomServlet.OnData.get(roomVO.getRoomId());	//取的此上架房型的即時價錢
							int thisRoomPrice = (Integer)one.get("price");
							
							if(min<=thisRoomPrice&&thisRoomPrice<=max){
								
								try {
									
									Room.put("price", thisRoomPrice);
									Room.put("roomId", roomVO.getRoomId());
										
								} catch (JSONException e) {}
						
							}else{continue;}	//如果本間價格不滿足,直接跳下一間房型
							
						
							if(bottom >= thisRoomPrice)	//取得所有房間滿足價錢限制的最低價錢
							{
								bottom = thisRoomPrice;
							}
					
							RoomIdarray.put(Room);	//將符合條件的房型JsonObject裝進房型陣列中				
							
						}//forEach ROOMList
					
						if(RoomIdarray.length()==0){continue;}
										
					}else{
						continue;	//如果本間旅館沒有上架房型,直接找下一間旅館
					}
							
					List servItemList = null;
					if(servItem!=null){
						servItemList = new ArrayList();		//將旅館設施陣列換成list ,以利本HOTEL用remove來檢測是否滿足條件
						for(int i = 0;i<servItem.length;i++){
							servItemList.add(servItem[i]);
						}
					}
					
					
					if(servItemList==null){
						
					}else if(servItemList.size()!=0){			
					//hotel查詢設施細項條件篩選
						List<HotelServVO> hotelServVOList= hotelServService.getAllByHotelId(hotelId);		
						for(HotelServVO hotelServVO:hotelServVOList){
							//System.out.println(str +" :  " + hotelServVO.getHotelServServId());
														
							ServVO servVO =servSvc.getOneServ(hotelServVO.getHotelServServId());//取得設施名稱				
	//						System.out.println(hotelId + " : " + servVO.getServName());
							
							servItemList.remove(servVO.getServName());	//用remove的方式來偵測hotel的設施是否滿足搜尋條件
						}
						
						if(servItemList.size()!=0){	//如果搜尋的清單中還有剩下,代表此hotel沒有全部使用者要求的條件,因此直接跳至下一間hotel
							continue;
						}			
			
					}
					
					
					//=================此hotel通通滿足條件,開始加資料至回傳的jsonArrray===================================//
				try{
					obj.put("hotelRating",hotelRating);
					obj.put("hotelId",hotelId);
					obj.put("hotelName",hotelName);
					obj.put("bottomPrice",bottom);
					obj.put("hotelId",hotelId);
					obj.put("hotelLon",hotelLon);
					obj.put("hotelLat",hotelLat);
					obj.put("RoomIdarray",RoomIdarray);
				}catch(Exception e){}	
					
				array.put(obj);
				
				}//foreach 每間hotel
			
			}//if 有沒有hotel
			
			
			PrintWriter out = res.getWriter();
			out.write(array.toString());	//輸出所搜尋到符合條件的旅館資料
			
			
			System.out.println(array);		
		}// if search
		
		
		
		
		
		
	}//doPost

}
