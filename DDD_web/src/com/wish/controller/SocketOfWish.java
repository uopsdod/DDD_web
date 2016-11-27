package com.wish.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.CloseReason;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@ServerEndpoint("/SocketOfWish/{aWishRoomId}/{productCount}")

public class SocketOfWish {
	
	public static JSONObject countObj = new JSONObject();	
	private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());

	private static final long serialVersionUID = 1L;
       
	@OnOpen
	public void onOpen(@PathParam("aWishRoomId") String aWishRoomId, @PathParam("productCount") String productCount, Session userSession) throws IOException {
		allSessions.add(userSession);
		System.out.println(userSession.getId() + ": 已連線");
		System.out.println(aWishRoomId + ": 已連線");
		System.out.println(productCount + ": 考慮");
		
//		userSession.getBasicRemote().sendText("WebSocket 連線成功");
	}
		
	@OnMessage
	public void onMessage(Session userSession, String message) {
		
			
	}
	public static void getCount(String aWishRoomId,String productCount){
		try {
			countObj.put("aWishRoomId", aWishRoomId);
			countObj.put("productCount", productCount);
			
			for (Session session : allSessions) {
				if (session.isOpen())
					session.getAsyncRemote().sendText(countObj.toString());				
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@OnError
	public void onError(Session userSession, Throwable e){
//		e.printStackTrace();
	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason,@PathParam("productCount") String productCount) {
		allSessions.remove(userSession);
		System.out.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
	}
	
}
