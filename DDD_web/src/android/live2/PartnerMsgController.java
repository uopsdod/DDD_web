package android.live2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mem.model.MemDAO_interface;
import com.mem.model.MemJNDIDAO;
import com.mem.model.MemVO;
import com.memchat.model.MemChatDAO_interface;
import com.memchat.model.MemChatService;
import com.memchat.model.MemChatVO;

/**
 * Servlet implementation class PartnerMsgController
 */
@WebServlet("/android/live2/PartnerMsgController")
public class PartnerMsgController extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	private MemChatService dao_memChat;
	private List<MemChatVO> MemChatVOList;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	@Override
	public void init() throws ServletException {
		super.init();
		dao_memChat = new MemChatService();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss.S")
                .create();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(),JsonObject.class);
		String action = (jsonObject.get("action") != null)?jsonObject.get("action").getAsString():null;
		String memId = (jsonObject.get("memId") != null)?jsonObject.get("memId").getAsString():null;
		String toMemId = (jsonObject.get("toMemId") != null)?jsonObject.get("toMemId").getAsString():null;  // backup: String toMemId = jsonObject.get("toMemId").getAsString();
		System.out.println("action: " + action);
		System.out.println("memId: " + memId);
		System.out.println("toMemId: " + toMemId);
		
		if ("getAll".equals(action)) {
			System.out.println("PartnerMsgController getAll match");
			String outStr = "";
			res.setContentType(CONTENT_TYPE);
			PrintWriter out = res.getWriter();
			//outStr = gson.toJson(dao_memChat.getAll()); // no no no not get all
			outStr = gson.toJson(dao_memChat.getOldMsgBtwnTwoMems(memId, toMemId)); // 這邊還無法轉成Hibernate，因為gson與hibernate之間的問題
			
			//System.out.println("outStr:" + outStr);
			out.println(outStr);
			return;
		}
		
		if ("getAll_history".equals(action)){
			System.out.println("PartnerMsgController getAll_history match");
			String outStr = "";
			res.setContentType(CONTENT_TYPE);
			PrintWriter out = res.getWriter();
			//outStr = gson.toJson(dao_memChat.getAll()); // no no no not get all
			List<MemChatVO> list = dao_memChat.getNewestMsgEachChatId(memId);
			for (MemChatVO myVO: list) {
				String msgCount = dao_memChat.getOldMsgCountBtwnTwoMems(myVO.getMemChatMemId(), myVO.getMemChatToMemId());
				myVO.setMemChatId(msgCount); // 不合常理處，請特別注意且小心行事
				System.out.println("msgCount: " + "(" + myVO.getMemChatMemId() + "," + myVO.getMemChatToMemId() + ") - " + msgCount);
			}
			outStr = gson.toJson(list);
			
			//System.out.println("outStr:" + outStr);
			out.println(outStr);
			return;
		}

	}

}
