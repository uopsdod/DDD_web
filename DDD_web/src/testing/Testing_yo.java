package testing;


import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.chat.model.ChatDAO_interface;
import com.chat.model.ChatJNDIDAO;
import com.chat.model.ChatService;
import com.livecond.model.LiveCondDAO_interface;
import com.livecond.model.LiveCondJDNIDAO;
import com.livecond.model.LiveCondService;
import com.mem.model.MemDAO_interface;
import com.mem.model.MemJNDIDAO;
import com.memchat.model.MemChatDAO_interface;
import com.memchat.model.MemChatJDBCDAO;
import com.memchat.model.MemChatJNDIDAO;
import com.memchat.model.MemChatService;
import com.memchat.model.MemChatVO;
import com.memlivecond.model.MemLiveCondDAO_interface;
import com.memlivecond.model.MemLiveCondJDBCDAO;
import com.memlivecond.model.MemLiveCondService;
import com.memrep.model.MemRepDAO_interface;
import com.memrep.model.MemRepJNDIDAO;
import com.memrep.model.MemRepService;
import com.memrep.model.MemRepVO;
    
@WebServlet("/Testing_yo")
public class Testing_yo extends HttpServlet {
  public void doGet(HttpServletRequest req, HttpServletResponse res)
                               throws ServletException, IOException {
   	req.setCharacterEncoding("Big5");
    res.setContentType("text/html; charset=Big5");
    PrintWriter out = res.getWriter();

	MemRepDAO_interface dao1 = new MemRepJNDIDAO();
	out.println("<b>MemRepJNDIDAO-OK: </b>");
	out.println(dao1.findByPrimaryKey("1000000001") +"<br>");
	MemRepService daoServ1 = new MemRepService();
	out.println("<b>MemRepService-OK: </b>");
	out.println(daoServ1.findByPrimaryKey("1000000001") +"<br>");
	out.println("---------------------------------------------<br>");

	MemLiveCondDAO_interface dao2 = new MemLiveCondJDBCDAO();
	out.println("<b>MemLiveCondJDBCDAO-OK: </b>");
	out.println(dao2.findByPrimaryKey("102", "10000001")+"<br>");
	MemLiveCondService daoServ2 = new MemLiveCondService();
	out.println("<b>MemLiveCondService-OK: </b>");
	out.println(daoServ2.findByPrimaryKey("102", "10000001")+"<br>");
	out.println("---------------------------------------------<br>");	
	
	MemChatDAO_interface dao3 = new MemChatJNDIDAO();
	MemChatVO myMemChatVO = dao3.getAll().get(0);
	out.println("<b>MemChatJNDIDAO-OK: </b>");
	out.println(dao3.findByPrimaryKey(myMemChatVO.getMemChatChatId(), myMemChatVO.getMemChatMemId(), myMemChatVO.getMemChatDate())+"<br>");
	MemChatService daoServ3 = new MemChatService();
	out.println("<b>MemChatService-OK: </b>");
	out.println(daoServ3.findByPrimaryKey(myMemChatVO.getMemChatChatId(), myMemChatVO.getMemChatMemId(), myMemChatVO.getMemChatDate())+"<br>");
	out.println("---------------------------------------------<br>");	
	
	LiveCondDAO_interface dao4 = new LiveCondJDNIDAO();
	out.println("<b>LiveCondJDNIDAO-OK: </b>");
	out.println(dao4.findByPrimaryKey("102")+"<br>");
	LiveCondService daoServ4 = new LiveCondService();
	out.println("<b>LiveCondService-OK: </b>");
	out.println(daoServ4.findByPrimaryKey("102")+"<br>");
	out.println("---------------------------------------------<br>");	
	
	ChatDAO_interface dao5 = new ChatJNDIDAO();
	out.println("<b>ChatJNDIDAO-OK: </b>");
	out.println(dao5.findByPrimaryKey("10000001")+"<br>");
	ChatService daoServ5 = new ChatService();
	out.println("<b>ChatService-OK: </b>");
	out.println(daoServ5.findByPrimaryKey("10000001")+"<br>");
	out.println("---------------------------------------------<br>");	
	

	
  }
}
