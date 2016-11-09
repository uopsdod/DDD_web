package android.live2;

import java.io.IOException;
import java.util.Collections;
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



/**
 * Servlet implementation class Chatroom
 */
@ServerEndpoint("/android/live2/Chatroom")
public class Chatroom extends HttpServlet {


	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());

	@OnOpen
	public void onOpen(Session userSession) throws IOException {
		connectedSessions.add(userSession);
		String text = String.format("Session ID = %s, connected", userSession.getId());
		System.out.println(text);
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		for (Session session : connectedSessions) {
			if (session.isOpen())
				session.getAsyncRemote().sendText(message);
		}
		System.out.println("Message received: " + message);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		connectedSessions.remove(userSession);
		String text = String.format("session ID = %s, disconnected; close code = %d", userSession.getId(),
				reason.getCloseCode().getCode());
		System.out.println(text);
	}

}
