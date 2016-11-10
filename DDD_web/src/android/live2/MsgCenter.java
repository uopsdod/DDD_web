package android.live2;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@ServerEndpoint("/android/live2/MsgCenter")
public class MsgCenter extends HttpServlet {
				//  <memId,tokenId> // 注意:要加上static，不然實體每次都會消失
	private static HashMap<String,String> tokenMap = new HashMap<>();
				//  <memId,session>	// 注意:要加上static，不然實體每次都會消失
	//private static HashMap<String,Session> sessionMap = new HashMap<>(); 
	
	private static BiMap<String, Session> sessionMap = new HashBiMap();
	
	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());

	@OnOpen
	public void onOpen(Session userSession) throws IOException {
		connectedSessions.add(userSession);
		String text = String.format("Session ID = %s, connected", userSession.getId());
		System.out.println(text);
	}

	@OnMessage
	public void onMessage(Session aUserSession, String aMessage) throws JSONException {
		// 客戶端傳來FCM - tockenId
		JSONObject jsonObj = new JSONObject(aMessage);
		String action = jsonObj.getString("action");
		String memId = jsonObj.getString("memId");
		if ("uploadTokenId".equals(action)){
			System.out.println("客戶端傳來的tokenId,memId: " + jsonObj);
			String token = jsonObj.getString("tokenId");
			tokenMap.put(memId, token);
			aUserSession.getAsyncRemote().sendText("Server aleary stored your token.");
			System.out.println("tokenMap.size(): "+ tokenMap.size());
			return;
		}
		
		// 使用者建立與MsgCenter建立WebSocket連線，並把memId和session用map綁定
		if("bindMemIdWithSession".equals(action) && !sessionMap.containsKey(memId)){
			sessionMap.put(memId, aUserSession);
		}
		System.out.println("sessionMap.size(): "+ sessionMap.size());
		
		// 狀況一: 使用者A主動寄出訊息，使用者B不再訊息室窗頁面
		
		// 一般聊天通訊
		for (Session session : connectedSessions) {
			if (session.isOpen())
				session.getAsyncRemote().sendText(aMessage);
		}
		System.out.println("Message received: " + aMessage);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session aUserSession, CloseReason aReason) {
		MsgCenter.sessionMap.inverse().remove(aUserSession);
		System.out.println("sessionMap.size(): "+ sessionMap.size());
		
		connectedSessions.remove(aUserSession);
		String text = String.format("session ID = %s, disconnected; close code = %d", aUserSession.getId(),
				aReason.getCloseCode().getCode());
		System.out.println(text);
	}

}
