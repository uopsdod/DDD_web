package android.live2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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

import com.chat.model.ChatService;
import com.chat.model.ChatVO;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.memchat.model.MemChatService;
import com.memchat.model.MemChatVO;
import com.pushraven.Pushraven;

@ServerEndpoint("/android/live2/MsgCenter")
public class MsgCenter extends HttpServlet {
	private static final String SERVERKEY = "AIzaSyD-c7lq9Moybii1GLLfgRViP1oFrZbYrjA";
	// <memId,tokenId> // 注意:要加上static，不然實體每次都會消失
	private static HashMap<String, String> tokenMap = new HashMap<>();
	// <memId,session> // 注意:要加上static，不然實體每次都會消失
	private static BiMap<String, Session> sessionMap = new HashBiMap<>();
	private static HashMap<String, String> chatIdMap = new HashMap<>();
	private MemChatService dao_memChat;

	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());

	@OnOpen
	public void onOpen(Session userSession) throws IOException {
		connectedSessions.add(userSession);
		String text = String.format("MsgCenterL ********** - Session ID = %s, connected",
				userSession.getId() + "************");
		System.out.println(text);
		this.dao_memChat = new MemChatService();
	}

	@OnMessage
	public void onMessage(Session aUserSession, String aMessage) throws JSONException, IOException {
		// Gson gson = new Gson();
		System.out.println("aMessage: " + aMessage);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss.S").create();
		PartnerMsg partnerMsg = gson.fromJson(aMessage, PartnerMsg.class);

		String action = (partnerMsg.getAction() != null) ? partnerMsg.getAction() : null;
		String fromMemId = (partnerMsg.getMemChatMemVO() != null) ? partnerMsg.getMemChatMemVO().getMemId() : null;
		String tokenId = (partnerMsg.getTokenId() != null) ? partnerMsg.getTokenId() : null;
		String toMemId = (partnerMsg.getMemChatToMemVO() != null) ? partnerMsg.getMemChatToMemVO().getMemId() : null;
		String message = (partnerMsg.getMemChatContent() != null) ? partnerMsg.getMemChatContent() : null;
		String fromMobile = (partnerMsg.getFromMobile() != null) ? partnerMsg.getFromMobile() : null;
		String resend = (partnerMsg.getResend() != null) ? partnerMsg.getResend() : null;

		System.out.println("tokenId: " + tokenId);
		System.out.println("fromMemId: " + fromMemId);
		// 客戶端傳來FCM - tockenId
		if ("uploadTokenId".equals(action)) {
			tokenMap.put(fromMemId, tokenId);
			aUserSession.getAsyncRemote().sendText("Server aleary stored your token.");
			System.out.println("after added - tokenMap.size(): " + tokenMap.size());
			return;
		} else if ("removeTokenId".equals(action)) {
			tokenMap.remove(fromMemId);
			aUserSession.getAsyncRemote().sendText("Server aleary removed your token.");
			System.out.println("after removed - tokenMap.size(): " + tokenMap.size());
			return;
		}

		// 使用者建立與MsgCenter建立WebSocket連線，並把memId和session用map綁定
		if ("bindMemIdWithSession".equals(action) && !sessionMap.containsKey(fromMemId)) {
			sessionMap.put(fromMemId, aUserSession);
			System.out.println("sessionMap.size(): " + sessionMap.size());
			String chatId = dao_memChat.getChatIdBtwenTwoMems(fromMemId, toMemId);
			System.out.println("bindMemIdWithSession - chat id: " + chatId);
			this.chatIdMap.put(fromMemId, chatId);
			if (resend != null) {
				System.out.println("resend matched *************");
				aUserSession.getAsyncRemote().sendText(aMessage);
			}
			return;
		} else if ("bindMemIdWithSession".equals(action)) {
			System.out.println("sessionMap.size(): " + sessionMap.size());
			String chatId = dao_memChat.getChatIdBtwenTwoMems(fromMemId, toMemId);
			System.out.println("bindMemIdWithSession(Web) - chat id: " + chatId);
			this.chatIdMap.put(fromMemId, chatId);
		}

		// 使用者要傳送訊息給對方
		if ("chat".equals(action)) {
			// 存入資料庫:
			// String chatId =
			// dao_memChat.getOldMsgBtwnTwoMems(fromMemId,toMemId).get(0).getMemChatChatId();
			String chatId = dao_memChat.getChatIdBtwenTwoMems(fromMemId, toMemId);
			chatIdMap.put(fromMemId, chatId);
			Timestamp ts = new Timestamp(new java.util.Date().getTime());
			String status = "0";

			MemChatVO memChatVO = new MemChatVO();
			memChatVO.setMemChatChatId(chatId);
			memChatVO.setMemChatMemId(fromMemId);
			memChatVO.setMemChatDate(ts);
			memChatVO.setMemChatContent(message);
			memChatVO.setMemChatStatus(status);
			memChatVO.setMemChatToMemId(toMemId);

			// 之前已有對話視窗，用原本的就好
			if (chatId != null) {
				dao_memChat.insert(memChatVO);
				// 第一次聊天，先新增聊天室後再新增訊息(使用Hibernate交易控管)
			} else {
				// 使用Hibernate
				ChatService dao_chat = new ChatService();
				ChatVO chatVO_h = new ChatVO(); // 一方
				Set<MemChatVO> set = new HashSet<>(); // 多方
				MemChatVO memChatVO_h = new MemChatVO(); // 多方01
				// MemChatVO memChatVO_h = new MemChatVO(); // 多方02

				memChatVO.setMemChatChatVO(chatVO_h); // 注意:要先將一方的VO物件放入多方物件中
				set.add(memChatVO); // 多方01放入Set
				chatVO_h.setChatMemChats(set); // 將多方塞入一方

				dao_chat.insert(chatVO_h); // 一方新增
				// end of 使用Hibernate

				this.chatIdMap.put(fromMemId, chatId);
			}
			// end of 存入資料庫

			// 將資料傳給對方
			// 狀況一: 使用者A主動寄出訊息，使用者B有session，且也在同一個訊息室窗頁面
			if (sessionMap.containsKey(toMemId) && this.chatIdMap.get(fromMemId).equals(this.chatIdMap.get(toMemId))) {
				sessionMap.get(toMemId).getAsyncRemote().sendText(aMessage);
				// 狀況二: 使用者A主動寄出訊息，使用者B不在訊息室窗頁面，使用者B仍然是登入狀態
			} else if (tokenMap.containsKey(toMemId)) {
				System.out.println(toMemId + " is not online yet.");
				// Pushraven raven = new Pushraven(MsgCenter.SERVERKEY);
				// // notification 設定:
				// MemVO memVO = new MemService().getOneMem(fromMemId);
				// System.out.println("memVO.getMemName(): " +
				// memVO.getMemName());
				// String title = memVO.getMemName();
				//// raven
				//// .title("MemA" + title).text(" wants to talk to
				// you.").color("#ff0000")
				//// .to(tokenMap.get(toMemId))
				// // .click_action("OPEN_ACTIVITY_1")
				// // .registration_ids(myReceivers) // 搭配Collection<String>
				// // myReceivers = new java.util.ArrayList<String>();使用
				// ;
				//
				// // data設定
				// HashMap<String, String> dataMap = new HashMap();
				// dataMap.put("fcm", "fcm");
				// dataMap.put("fromMemId", fromMemId);
				// StringBuilder data = convertMapToJson(dataMap);
				//
				// raven.to(tokenMap.get(toMemId));
				// raven.addNotificationAttribute("title", memVO.getMemName());
				// raven.addNotificationAttribute("body", "context");
				// raven.addRequestAttribute("data", data); //
				// 這邊一定要用StringBuilder,不然跳脫字元\會被當成字串印出來
				//
				// raven.push();
				// raven.clear(); // clears the notification, equatable with
				// "raven
				// // = new Pushraven();"
				// raven.clearAttributes(); // clears FCM protocol paramters
				// // excluding targets
				// raven.clearTargets(); // only clears targets

				// 為了解決中文問題，自行製作request，並將Content-Type設定為"application/json; charset=utf-8":
				HashMap<String, String> requestMap = new HashMap();
				// dataMap:
				HashMap<String, String> dataMap = new HashMap();
				dataMap.put("fcm", "fcm");
				dataMap.put("fromMemId", fromMemId);
				// notificationMap:
				HashMap<String, String> notificationMap = new HashMap();
				MemVO memVO = new MemService().getOneMem(fromMemId);
				notificationMap.put("title", "DDD hotel");
				notificationMap.put("body", memVO.getMemName() + " 有新的留言");
				notificationMap.put("color", "#709ACF");
				
				// 總設定:
				requestMap.put("data", convertMapToJson(dataMap).toString());
				requestMap.put("notification", convertMapToJson(notificationMap).toString());
				requestMap.put("to", tokenMap.get(toMemId));

				// 將資料轉為JSON型式，並將request交上去給FCM server:
				StringBuilder jsonOut = convertMapToJson(requestMap);
				System.out.println("jsonOut: " + jsonOut);
				getRemoteData(jsonOut.toString());

			} else {// end if - 將資料傳給對方
				System.out.println(toMemId + " is not online and not logged in yet.");
				if (fromMobile != null) {
					return;
				}

				// 網頁端專用:
				if (sessionMap.get(toMemId) != null) {
					Session toMemSession = sessionMap.get(toMemId);

					JSONObject memChatMemVO = new JSONObject();
					JSONObject memChatToMemVO = new JSONObject();
					JSONObject offlineObject = new JSONObject();

					memChatMemVO.put("memId", fromMemId);
					memChatToMemVO.put("memId", toMemId);

					offlineObject.put("action", "offlineMessage");
					offlineObject.put("memChatMemVO", memChatMemVO);
					offlineObject.put("memChatToMemVO", memChatToMemVO);
					offlineObject.put("memChatContent", message);

					// JSONObject notifyJSON = new
					// JSONObject("{\"action\":\"talkToYou\",\"fromMemId\":\"" +
					// fromMemId + + toMemId +"\"}");
					toMemSession.getAsyncRemote().sendText(offlineObject.toString());
				} else {
					System.out.println(toMemId + " 不在共住頁面");
				}

				// end of 網頁端專用
			}
			return;
		} // end if "chat"

	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session aUserSession, CloseReason aReason) {
		String memIdToRemove = MsgCenter.sessionMap.inverse().get(aUserSession);
		System.out.println("ChatId removed: " + MsgCenter.chatIdMap.remove(memIdToRemove));
		// MsgCenter.chatIdMap.remove(memIdToRemove);
		MsgCenter.sessionMap.remove(memIdToRemove);

		System.out.println("sessionMap.size(): " + sessionMap.size());
	}

	private StringBuilder convertMapToJson(HashMap aDataMap) {
		StringBuilder sb = new StringBuilder();
		Set<String> keys = aDataMap.keySet();
		String regex = "^[{].*[}]$";
		sb.append("{");
		for (String key : keys) {
			// 如果key-value pair中的value是一個JsonObject - 則不要加""
			if (aDataMap.get(key).toString().matches(regex)) {
//				System.out.println("matched %%%%%%%%%%%%%%%");
//				System.out.println(aDataMap.get(key).toString());
				sb.append("\"" + key + "\"" + ":").append(aDataMap.get(key)).append(",");
			}else{
				sb.append("\"" + key + "\"" + ":").append("\"" + aDataMap.get(key) + "\"").append(",");				
			}
		}
		sb = new StringBuilder(sb.substring(0, sb.length() - 1));
		sb.append("}");
		System.out.println("convertMapToJson: " + sb.toString());
		return sb;
	}// end convertMapToJson

	private String getRemoteData(String jsonOut) throws IOException {
		String url = "https://fcm.googleapis.com/fcm/send";
		String contentType = "application/json; charset=utf-8";
		String serverKey = "AAAA5sI4fWg:APA91bGzqCWBz5yDWsW8GUf1JW5LTzOSEnzaZcS3Db9yxc5nj-X5ISV2XmjBb94jeRY2icVTOVRjTyv6x1iSKFlIURoPzV1y2dLpGbSa-ouwPB-EtItQ9xX71Lt5UvpwC2cpjSN29uS2Vh7nk45m51y5L9tRXEv20A";

		StringBuilder jsonIn = new StringBuilder();
		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
		connection.setDoInput(true); // allow inputs
		connection.setDoOutput(true); // allow outputs
		connection.setUseCaches(false); // do not use a cached copy
		connection.setRequestMethod("POST");
		// connection.setRequestProperty("charset", "UTF-8");
		connection.setRequestProperty("Content-Type", contentType);
		connection.setRequestProperty("Authorization", "key=" + serverKey);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
		bw.write(jsonOut); // 塞入請求參數
		// Log.d(TAG, "jsonOut: " + jsonOut);
		bw.close(); // 送出request

		int responseCode = connection.getResponseCode();
		// 確認能與server建立Socket連線
		if (responseCode == 200) {
			// connection.getInputStream() - 等待server回應，如果還沒有回應就hold在這邊
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				jsonIn.append(line); // 重點:把server的資料拿到手
			}
		} else {
			System.out.println("response code: " + responseCode);
		}
		connection.disconnect();
		// (TAG, "jsonIn: " + jsonIn);
		return jsonIn.toString();
	}

}
