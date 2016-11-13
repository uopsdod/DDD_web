package android.live2;

import java.io.IOException;
import java.util.Collection;
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
import com.pushraven.Pushraven;

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
		String text = String.format("MsgCenterL ********** - Session ID = %s, connected", userSession.getId() + "************");
		System.out.println(text);
	}

	@OnMessage
	public void onMessage(Session aUserSession, String aMessage) throws JSONException {
		//Gson gson = new Gson();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss.S")
                .create();
		PartnerMsg partnerMsg = gson.fromJson(aMessage, PartnerMsg.class);
		JSONObject jsonObj = new JSONObject(aMessage);
		System.out.println("Msg Sent here: " + jsonObj);
		
		String action = partnerMsg.getAction();
		String fromMemId = partnerMsg.getMemChatMemId();
		String tokenId = partnerMsg.getTokenId();
		String toMemId = partnerMsg.getMemChatToMemId();
		String message = partnerMsg.getMemChatContent();
		
		System.out.println("fromMemId: " + fromMemId);
		// 客戶端傳來FCM - tockenId
		if ("uploadTokenId".equals(action)){
			//System.out.println("客戶端傳來的tokenId,memId: " + jsonObj);
			tokenMap.put(fromMemId, tokenId);
			aUserSession.getAsyncRemote().sendText("Server aleary stored your token.");
			System.out.println("tokenMap.size(): "+ tokenMap.size());
			return;
		}
		
		
		// 使用者建立與MsgCenter建立WebSocket連線，並把memId和session用map綁定
		if("bindMemIdWithSession".equals(action) && !sessionMap.containsKey(fromMemId)){
			sessionMap.put(fromMemId, aUserSession);
			System.out.println("sessionMap.size(): "+ sessionMap.size());
			return;
		}
		
		
		// 狀況一: 使用者A主動寄出訊息，使用者B不在訊息室窗頁面
		
		// 狀況一: 使用者A主動寄出訊息，使用者B也在訊息室窗頁面
		if ("chat".equals(action) && sessionMap.containsKey(toMemId)){			
			sessionMap.get(toMemId).getAsyncRemote().sendText(message);
		}else{
			System.out.println(toMemId +  " is not online yet.");
			String serverKey = "AIzaSyD-c7lq9Moybii1GLLfgRViP1oFrZbYrjA";
			Pushraven raven = new Pushraven(serverKey);
			// notification 設定:
			raven.title("MyTitle")
				.text( fromMemId + " wants to talk to you.")
				.color("#ff0000")
				.to(tokenMap.get(toMemId))
			//  .click_action("OPEN_ACTIVITY_1")
			//	.registration_ids(myReceivers)  // 搭配Collection<String> myReceivers = new java.util.ArrayList<String>();使用
				;
			
			// data設定
			String fcmKey = "fcm";
			String fcmValue = "fcm";
			String fromMemIdKey = "fromMemId";
			String fromMemIdValue = fromMemId;

			StringBuilder sb = new StringBuilder();
			sb.append("{")
//			  .append("\"" + ticketKey + "\""+":").append("\""+ticketValue+"\"").append(",")
			  .append("\"" + fromMemIdKey + "\""+":").append("\""+fromMemIdValue+"\"").append(",")
			  .append("\"" + fcmKey + "\""+":").append("\""+fcmValue+"\"")
			  .append("}");
			
			raven.addRequestAttribute("data", sb);
			
			raven.push();
			raven.clear(); // clears the notification, equatable with "raven = new Pushraven();"
			raven.clearAttributes(); // clears FCM protocol paramters excluding targets
			raven.clearTargets(); // only clears targets
		}
		
//		for (Session session : connectedSessions) {
//			if (session.isOpen())
//				session.getAsyncRemote().sendText(aMessage);
//		}
		//System.out.println("Message received: " + aMessage);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session aUserSession, CloseReason aReason) {
		MsgCenter.sessionMap.inverse().remove(aUserSession);
		System.out.println("sessionMap.size(): "+ sessionMap.size());
		
//		connectedSessions.remove(aUserSession);
//		String text = String.format("session ID = %s, disconnected; close code = %d", aUserSession.getId(),
//				aReason.getCloseCode().getCode());
//		System.out.println(text);
	}

}
