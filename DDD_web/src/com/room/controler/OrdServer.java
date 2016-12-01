package com.room.controler;
import java.io.*;
import java.util.*;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ord.model.OrdService;
import com.ord.model.OrdVO;

import javax.websocket.Session;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.CloseReason;

@ServerEndpoint("/OrdServer/{myName}/{myRoom}")
public class OrdServer {
	


private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());
	

	@OnOpen
	public void onOpen(@PathParam("myName") String myName, @PathParam("myRoom") String HotelId, Session userSession) throws IOException {
		
		allSessions.add(userSession);
		System.out.println(userSession.getId() + ": 已連線");
		System.out.println(myName + ": 已連線");
		System.out.println(HotelId + ": hotel");
	
		

	}
	
	public static void setOrdData(){
		
		
		
		
	}

	public void ordMessage(){
		
	}
	
	
	
	@OnError
	public void onError(Session userSession, Throwable e){
//		e.printStackTrace();
	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason,@PathParam("myRoom") String HotelId,@PathParam("myName") String myName) {


		allSessions.remove(userSession);
		System.out.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
	}

 
}
