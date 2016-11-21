package com.room.controler;
import java.io.*;
import java.util.*;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONArray;
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
	
	
private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());
	
	

	@OnOpen
	public void onOpen(@PathParam("myName") String myName, @PathParam("myRoom") int myRoom, Session userSession) throws IOException {
		allSessions.add(userSession);
		System.out.println(userSession.getId() + ": 已連線");
		System.out.println(myName + ": 已連線");
		System.out.println(myRoom + ": 房號");
//		userSession.getBasicRemote().sendText("WebSocket 連線成功");
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
			
			if(Bag.length()==0){
				return;
			}
			
			for (Session session : allSessions) {
				if (session.isOpen())
					session.getAsyncRemote().sendText(Bag.toString());				
			}
			
			System.out.println(Bag.toString());
//			for(int i =0;i<Bag.length();i++){
//			Bag.remove(i);
//			}
			Bag = new JSONArray();
		}
		
	}
	
	
	
	@OnError
	public void onError(Session userSession, Throwable e){
//		e.printStackTrace();
	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		allSessions.remove(userSession);
		System.out.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
	}

 
}
