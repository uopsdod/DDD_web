package testing;


import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;

import com.chat.model.ChatDAO_interface;
import com.chat.model.ChatService;
import com.chat.model.ChatVO;
import com.chat.modelJDBC.ChatJNDIDAO;
import com.emp.model.EmpVO;
import com.livecond.model.LiveCondDAO_interface;
import com.livecond.model.LiveCondJDNIDAO;
import com.livecond.model.LiveCondService;
import com.mem.model.MemJNDIDAO;
//import com.mem.model.MemDAO_interface;
//import com.mem.model.MemJDBCDAO;
//import com.mem.model.MemJNDIDAO;
import com.mem.model.MemVO;
import com.memchat.model.MemChatDAO_interface;
import com.memchat.model.MemChatJDBCDAO;
import com.memchat.model.MemChatJNDIDAO;
import com.memchat.model.MemChatService;
import com.memchat.model.MemChatVO;
import com.memlivecond.model.MemLiveCondDAO_interface;
import com.memlivecond.model.MemLiveCondJDBCDAO;
import com.memlivecond.model.MemLiveCondService;
import com.memrep.model.MemRepDAO_interface;
import com.memrep.model.MemRepHibernateDAO;
import com.memrep.model.MemRepService;
//import com.memrep.model.MemRepHibernateDAO;
//import com.memrep.model.MemRepService;
import com.memrep.model.MemRepVO;
import com.ord.model.OrdService;

import util.CompositeQuery_anyDB_Hibernate;
import util.HibernateUtil;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import com.hotel.model.HotelVO;

@WebServlet("/Testing_yo")
public class Testing_yo extends HttpServlet {
	private static DataSource ds  = null;
	static{
		try{
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
					
		}catch(NamingException e){
			e.printStackTrace(System.err);
		}
	}
	
	
  public void doGet(HttpServletRequest req, HttpServletResponse res)
                               throws ServletException, IOException {
   	req.setCharacterEncoding("UTF-8");
    res.setContentType("text/html; charset=UTF-8");
    PrintWriter out = res.getWriter();

//	MemRepDAO_interface dao1 = new MemRepHibernateDAO();
//	out.println("<b>MemRepJNDIDAO-OK: </b>");
//	out.println(dao1.findByPrimaryKey("1000000001") +"<br>");
//	MemRepService daoServ1 = new MemRepService();
//	out.println("<b>MemRepService-OK: </b>");
//	out.println(daoServ1.findByPrimaryKey("1000000001") +"<br>");
//	out.println("---------------------------------------------<br>");
//
//	MemLiveCondDAO_interface dao2 = new MemLiveCondJDBCDAO();
//	out.println("<b>MemLiveCondJDBCDAO-OK: </b>");
//	out.println(dao2.findByPrimaryKey("102", "10000001")+"<br>");
//	MemLiveCondService daoServ2 = new MemLiveCondService();
//	out.println("<b>MemLiveCondService-OK: </b>");
//	out.println(daoServ2.findByPrimaryKey("102", "10000001")+"<br>");
//	out.println("---------------------------------------------<br>");	
//	
	
//    MemChatDAO_interface dao3 = new MemChatJNDIDAO();
//	MemChatVO myMemChatVO = dao3.getAll().get(0);
//	out.println("<b>MemChatJNDIDAO-OK: </b>");
//	out.println(dao3.findByPrimaryKey(myMemChatVO.getMemChatChatId(), myMemChatVO.getMemChatMemId(), myMemChatVO.getMemChatDate())+"<br>");
//	MemChatService daoServ3 = new MemChatService();
//	out.println("<b>MemChatService-OK: </b>");
//	out.println(daoServ3.findByPrimaryKey(myMemChatVO.getMemChatChatId(), myMemChatVO.getMemChatMemId(), myMemChatVO.getMemChatDate())+"<br>");
//	List<MemChatVO> list = daoServ3.getAll();
//	for (MemChatVO myVO: list){
//		out.println(myVO.getMemChatChatId() + " ");
//		out.println(myVO.getMemChatStatus() + " ");		
//	}
//	out.println("---------------------------------------------<br>");	
//	
//	LiveCondDAO_interface dao4 = new LiveCondJDNIDAO();
//	out.println("<b>LiveCondJDNIDAO-OK: </b>");
//	out.println(dao4.findByPrimaryKey("102")+"<br>");
//	LiveCondService daoServ4 = new LiveCondService();
//	out.println("<b>LiveCondService-OK: </b>");
//	out.println(daoServ4.findByPrimaryKey("102")+"<br>");
//	out.println("---------------------------------------------<br>");	
//	
//	ChatDAO_interface dao5 = new ChatJNDIDAO();
//	out.println("<b>ChatJNDIDAO-OK: </b>");
//	out.println(dao5.findByPrimaryKey("10000001")+"<br>");
//	ChatService daoServ5 = new ChatService();
//	out.println("<b>ChatService-OK: </b>");
//	out.println(daoServ5.findByPrimaryKey("10000001")+"<br>");
//	out.println("---------------------------------------------<br>");	
	
	// 萬用查詢
	// 配合 req.getParameterMap()方法 回傳
	// java.util.Map<java.lang.String,java.lang.String[]> 之測試	
	// 萬用查詢 - 測試memRepJNDIDAO
	// 測試用 url:  http://localhost:8081/DDD_web/Testing_yo?memRepStatus=0
	// 測試用 url:  http://localhost:8081/DDD_web/Testing_yo?memRepStatus=2
//	Map<String, String[]> map1 = req.getParameterMap();
//	MemRepDAO_interface dao6 = new MemRepHibernateDAO();
//	List<MemRepVO> memRepList = dao6.getAll(map1);
//	out.println("<b>萬用查詢MemRepJNDIDAO-OK: </b><br>");
//	for (MemRepVO myVO: memRepList){
//		out.println("myVO: " + myVO.getMemRepContent() + "<br>");
//	}
//	out.println("---------------------------------------------<br>");
//	// 萬用查詢 - 測試memChatJNDIDAO
//	MemChatDAO_interface dao7 = new MemChatJNDIDAO();
//	Map<String, String[]> map2 = req.getParameterMap();
//	List<MemChatVO> memChatList = dao7.getAll(map2);
//	out.println("<b>萬用查詢MemChatJNDIDAO-OK: </b><br>");
//	for (MemChatVO myVO: memChatList){
//		if (myVO.getMemChatContent()!=null){
//			out.println("myVO: " + myVO.getMemChatContent() + "<br>");			
//		}
//	}
//	out.println("---------------------------------------------<br>");
//	
	
	// test
//	MemDAO_interface dao8 = new MemJNDIDAO();
//	out.println("<b>MemJNDIDAO-OK: </b>");
//	out.println(dao8.getAll()+"<br>");
//	out.println(dao8.getAll().get(0).getMemProfile()+"<br>");
//	out.println("---------------------------------------------<br>");
    
    /*************************************** HibernateDAO  *********************************************/
//    MemRepService dao9 = new MemRepService();
    // 查詢單筆測試:
//	out.println("<b>MemRepHibernateDAO-findByPrimaryKey- OK: </b>");
//	out.println(dao9.findByPrimaryKey("1000000001").getMemRepContent() + "<br>");  
//	out.println(dao9.findByPrimaryKey("1000000001").getMemRepMemVO().getMemName() + "<br>");
//	out.println(dao9.findByPrimaryKey("1000000001").getMemRepOrdVO().getOrdRatingContent() + "<br>");
//	out.println(dao9.findByPrimaryKey("1000000001").getMemRepHotelVO().getHotelOwner() + "<br>");
//	out.println(dao9.findByPrimaryKey("1000000003").getMemRepEmpVO().getEmpName() + "<br>");
//	out.println("<br>---------------------------------------------<br>");
	// 新增測試:
//	out.println("<b>MemRepHibernateDAO-insert- OK: </b>");
//	MemRepVO memRepVO = new MemRepVO();
//	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
//	java.util.Date d = new java.util.Date(); // convert Date to String
//	String yyyyMM = sdf.format(d);
//	String ordid = yyyyMM + "1003";
//	memRepVO.setMemRepOrdId(ordid); // NOT NULL
//	OrdServce dao_ord = new OrdServce();
//	String memId = dao_ord.getOneOrd(ordid).getOrdMemId();
//	String hotelId = dao_ord.getOneOrd(ordid).getOrdHotelId();
//	MemJNDIDAO dao_mem = new MemJNDIDAO();
//	memRepVO.setMemRepMemVO(dao_mem.findByPrimaryKey(memId)); // NOT NULL
//	memRepVO.setMemRepHotelId(hotelId); // NOT NULL
//	memRepVO.setMemRepContent("Hibernatew 新增測試!!!!!!!!!!!!!");
//	dao9.insert(memRepVO);
//	out.println("<br>---------------------------------------------<br>");
	// 更新測試:
//    MemRepVO memRepVO = dao9.findByPrimaryKey("1000000001");
//    memRepVO.setMemRepContent("Hibernate 更新測試!!!!!!!!!!!");
//    dao9.update(memRepVO);
//    out.println("<br>---------------------------------------------<br>");
    // 刪除測試:
//    dao9.delete("1000000001");
//    out.println("<br>---------------------------------------------<br>");
    // 查全部測試:
//    List<MemRepVO> memRepVOList = dao9.getAll();
//    for (MemRepVO myVO: memRepVOList){
//    	out.println(myVO.getMemRepId() + ": " + myVO.getMemRepContent() + "<br>");
//    }
//    out.println("---------------------------------------------<br>");
    // 萬用查詢測試:
//	out.println("<b>MemRepHibernateDAO-萬用查詢- OK: </b><br>");
//	
//	List<MemRepVO> list = dao9.getAll(req.getParameterMap());
//	for (MemRepVO myVO: list){
//		out.println(myVO.getMemRepContent() + "<br>");
//	}
//	out.println("---------------------------------------------<br>");
    
	// metadata
//	SessionFactory sessionFactory  = HibernateUtil.getSessionFactory();
//	Map myMap = sessionFactory.getAllClassMetadata();
//	Set<String> keys = myMap.keySet();
//	for (String key: keys){
//		out.println( key + " - " + myMap.get(key) + "<br>");
//	}
//	out.println("---------------------------------------------<br>");
//	ClassMetadata classMetadata = sessionFactory.getClassMetadata(HotelVO.class);
//	org.hibernate.type.Type[] types = classMetadata.getPropertyTypes();
//	for (org.hibernate.type.Type myType: types){
//		out.println(myType.getName() + "<br>");
//	}
//	out.println("---------------------------------------------<br>");
//	String[] names = classMetadata.getPropertyNames();
//	for (String name: names){
//		out.println(name + "<br>");
//	}
//	out.println("---------------------------------------------<br>");
	
//	Class myVOClass = MemVO.class;
//    SessionFactory sessionFactory  = HibernateUtil.getSessionFactory();
//    Session session = sessionFactory.getCurrentSession();
//    session.beginTransaction();
//    Criteria query = sessionFactory.getCurrentSession().createCriteria(myVOClass);
//    Map<String, String[]> map3 = req.getParameterMap();
//	CompositeQuery_anyDB_Hibernate.getAll(query, map3, myVOClass);
//	List<MemVO> list = query.list();
//	for (MemVO myVO : list){
//		out.println(myVO.getMemName() + "<br>");
//	}
//	out.println("---------------------------------------------<br>");
//	session.getTransaction().commit();
	
	//ChatHibernateDAO 測試:
//	ChatService dao_chat = new ChatService();
	// 查單筆測試:
//	out.println("<b>ChatHibernateDAO-getOneChat- OK: </b>");
//	out.println(dao_chat.getOneChat("10000001")+ "<br>"); 	
//	out.println("<b>ChatHibernateDAO-insert- OK: </b>");
	// 查全部測試:
//	out.println("<b>ChatHibernateDAO-getAll- OK: </b><br>");
//	List<ChatVO> myList = dao_chat.getAll();
//	for (ChatVO myVO: myList){
//		out.println(myVO.getChatId() + "<br>");
//	}
	// 萬用查詢測試:
//	out.println("<b>ChatHibernateDAO-getAll(aMap)- OK: </b><br>");
//	List<ChatVO> myList = dao_chat.getAll(req.getParameterMap());
//	for (ChatVO myVO: myList){
//		out.println(myVO.getChatId() + "<br>");
//	}	
	// 新增測試:
//	ChatVO chatVO = new ChatVO();
//	dao_chat.insert(chatVO);
//	out.println("<br>");
	// 刪除測試:
//	ChatVO chatVO = new ChatVO();	
//	dao_chat.delete("10000004"); // Constraint - 要先新增一筆沒人用的才能刪掉
    
    // MemChatHibernateDAO 測試:
//	MemChatService dao_memChat = new MemChatService();
//	MemChatVO memChatVO = dao_memChat.getAll().get(0);
    // 新方法-getNewestMsgEachChatId
//	MemChatService dao_memChat = new MemChatService();
//	List<MemChatVO> myList = dao_memChat.getNewestMsgEachChatId("10000001");
//	for (MemChatVO myVO: myList){
//		out.println(myVO.getMemChatContent() + "<br>");
//	}
    
    // 測試ChatService - insertWithMemChats
    ChatService dao_chat = new ChatService();
    ChatVO chatVO = new ChatVO(); // 不設定第二個欄位
    List<MemChatVO> list = new ArrayList<>();
	Timestamp ts = new Timestamp(new java.util.Date().getTime());
	MemChatVO memChatVO = new MemChatVO();
//	memChatVO.setMemChatChatId(chatId); // 此行註解掉
	memChatVO.setMemChatMemId("10000003");
	memChatVO.setMemChatDate(ts);
	memChatVO.setMemChatContent("Aloha");
	memChatVO.setMemChatPic(null);
	memChatVO.setMemChatStatus("0");
	memChatVO.setMemChatToMemId("10000004");
	list.add(memChatVO);
	dao_chat.insertWithMemChats(chatVO, list);
    
	
  }
}
