package testing;


import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.chat.model.ChatDAO_interface;
import com.chat.model.ChatJNDIDAO;
import com.livecond.model.LiveCondDAO_interface;
import com.livecond.model.LiveCondJDNIDAO;
import com.memchat.model.MemChatDAO_interface;
import com.memchat.model.MemChatJDBCDAO;
import com.memchat.model.MemChatJNDIDAO;
import com.memchat.model.MemChatVO;
import com.memlivecond.model.MemLiveCondDAO_interface;
import com.memlivecond.model.MemLiveCondJDBCDAO;
import com.memrep.model.MemRepDAO_interface;
import com.memrep.model.MemRepJNDIDAO;
import com.memrep.model.MemRepVO;
    
@WebServlet("/Testing_yo")
public class Testing_yo extends HttpServlet {
  public void doGet(HttpServletRequest req, HttpServletResponse res)
                               throws ServletException, IOException {
   	req.setCharacterEncoding("Big5");
    res.setContentType("text/html; charset=Big5");
    PrintWriter out = res.getWriter();
    
//	MemRepDAO_interface dao1 = new MemRepJNDIDAO();
//	out.println(dao1.findByPrimaryKey("1000000001").getMemRepContent()+"<br>");

	MemLiveCondDAO_interface dao2 = new MemLiveCondJDBCDAO();
	out.println(dao2.findByPrimaryKey("102", "10000001")+"<br>");
	
	MemChatDAO_interface dao3 = new MemChatJNDIDAO();
	MemChatVO myMemChatVO = dao3.getAll().get(0);
	out.println(dao3.findByPrimaryKey(myMemChatVO.getMemChatChatId(), myMemChatVO.getMemChatMemId(), myMemChatVO.getMemChatDate())+"<br>");
	
	LiveCondDAO_interface dao4 = new LiveCondJDNIDAO();
	out.println(dao4.findByPrimaryKey("102")+"<br>");
	
	ChatDAO_interface dao5 = new ChatJNDIDAO();
	out.println(dao5.findByPrimaryKey("10000001")+"<br>");
	
	
  }
}
