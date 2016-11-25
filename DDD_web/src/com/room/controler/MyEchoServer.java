package com.room.controler;
import java.io.*;
import java.util.*;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.websocket.Session;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.CloseReason;

@ServerEndpoint("/MyEchoServer/{myName}/{myRoom}")
public class MyEchoServer {
	
private static JSONArray Bag= new JSONArray();
public static JSONObject onTimePeople = new JSONObject();	
private static JSONObject RoomRemainNo = new JSONObject();


private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());
	
	

	@OnOpen
	public void onOpen(@PathParam("myName") String myName, @PathParam("myRoom") String HotelId, Session userSession) throws IOException {
		allSessions.add(userSession);
		System.out.println(userSession.getId() + ": 已連線");
		System.out.println(myName + ": 已連線");
		System.out.println(HotelId + ": 房號");
		
		if(!"1".equals(HotelId)){
		hotelPeople(HotelId,1);
		}
//		userSession.getBasicRemote().sendText("WebSocket 連線成功");
	}
	
	synchronized private void hotelPeople(String HotelId,int change){
		
		Integer howMany;
		try{			
				howMany = (Integer)onTimePeople.get(HotelId);	//如果取不到hotelId直接往catch跳			
				howMany += change;
				if(howMany>=0){	
					onTimePeople.put(HotelId,howMany);					
				}else{	
					onTimePeople.remove(HotelId);
				}
			  			
		}catch(Exception e){
			  howMany = 0;
			  howMany += change;
			  if(howMany>=0){
				  	try{
					onTimePeople.put(HotelId,howMany);
				  	}catch(Exception ee){}
			  }
			
			
		}
		
		
	}
	
	
	synchronized static public void changeRemainNo(String roomId,int remainNo){
	
		try{
		RoomRemainNo.put(roomId,remainNo);
		}catch(Exception e){}
		
	}
	
	@OnMessage
	public void onMessage(Session userSession, String message) {
		
		
		
	}
	
		
	
	synchronized public static void BufferBox(String roomId,int Price,String str,int where){
		
		if(where ==0 ){
		
		JSONArray priceBag= new JSONArray();	//建立個別room的資料物件	
		priceBag.put(roomId);		
		if(Price>=0){
		priceBag.put(Price);
		}else{
			priceBag.put(str);	
		}
		
		Bag.put(priceBag); //放入掃出去的buffer物件
		
		return;
		}
		
		
		if(where == 1){
			
//			System.out.println("ws推資料出去了");
			
			
			JSONObject AllBag = new JSONObject();
			try{
				if(Bag.length()!=0){
				AllBag.put("Bag", Bag);
				}
				
				AllBag.put("onTimePeople", onTimePeople);
				
				if(RoomRemainNo.length()!=0){
				AllBag.put("RoomRemainNo", RoomRemainNo);
				}
				
			}catch(Exception e){}
			
			
			for (Session session : allSessions) {
				if (session.isOpen())
					session.getAsyncRemote().sendText(AllBag.toString());				
			}
			
			System.out.println(AllBag.toString());

//			for(int i =0;i<Bag.length();i++){
//			Bag.remove(i);
//			}
			if(Bag.length()!=0){
			Bag = new JSONArray();
			}
			
			if(RoomRemainNo.length()!=0){
				RoomRemainNo = new JSONObject();
			}
		}
		
	}
	
	
	
	@OnError
	public void onError(Session userSession, Throwable e){
//		e.printStackTrace();
	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason,@PathParam("myRoom") String HotelId) {
		hotelPeople(HotelId,-1);
		allSessions.remove(userSession);
		System.out.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
	}

 
}
