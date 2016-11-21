//package com.memchat.modelJDBC;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.GregorianCalendar;
//import java.sql.Date;
//import java.util.List;
//import java.util.Map;
//import java.io.*;
//
//import com.memchat.model.MemChatVO;
//import com.ord.model.OrdService;
//import com.ord.model.OrdVO;
//import com.chat.model.ChatVO;
//import com.chat.modelJDBC.ChatJDBCDAO;
//import com.memchat.model.MemChatDAO_interface;
//
//
//public class MemChatJDBCDAO implements MemChatDAO_interface {
//	private static String driver = "oracle.jdbc.driver.OracleDriver";
//	private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
//	private static String account = "scott";
//	private static String password = "tiger";
//	// 全部欄位名(複製用):
//	// memChatMemId memChatChatId memChatDate memChatContent memChatPic
//	private static final String INSERT = "INSERT INTO memChat (memChatChatId,memChatMemId, memChatDate, memChatContent, memChatPic) VALUES (?,?,?,?,?)";
//	private static final String UPDATE = "";
//	private static final String DELETE = "DELETE FROM memChat WHERE memChatChatId= ? AND memChatMemId = ? AND TO_CHAR(memChatDate, 'YYYYMMDD HH24:MI:SSxFF3') = TO_CHAR(?, 'YYYYMMDD HH24:MI:SSxFF3')";
//	
//	private static final String GET_ALL = "SELECT memChatChatId, memChatMemId, memChatDate, memChatContent, memChatPic FROM memChat ORDER BY memChatChatId";
//	private static final String GET_ONE = "SELECT memChatChatId, memChatMemId, memChatDate, memChatContent, memChatPic FROM memChat WHERE memChatChatId= ? AND memChatMemId = ? AND TO_CHAR(memChatDate, 'YYYYMMDD HH24:MI:SSxFF3') = TO_CHAR(?, 'YYYYMMDD HH24:MI:SSxFF3')";
//	private static final String GET_ALL_MEMCHATCHATID = "SELECT memChatChatId, memChatMemId, memChatDate, memChatContent, memChatPic FROM memChat WHERE memChatChatId= ? ORDER BY memChatChatId";
//	
//	
//	static{
//		try {
//			Class.forName(MemChatJDBCDAO.driver);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void insert(MemChatVO aMemChatVO) {
//		try(Connection con = DriverManager.getConnection(MemChatJDBCDAO.url,MemChatJDBCDAO.account,MemChatJDBCDAO.password);
//			PreparedStatement pstmt = con.prepareStatement(MemChatJDBCDAO.INSERT);) {
//			
//			pstmt.setString(1, aMemChatVO.getMemChatChatId());
//			pstmt.setString(2, aMemChatVO.getMemChatMemId());
//			pstmt.setTimestamp(3, aMemChatVO.getMemChatDate());
//			pstmt.setString(4, aMemChatVO.getMemChatContent());
//			pstmt.setBytes(5, aMemChatVO.getMemChatPic());
//			
//			int result = pstmt.executeUpdate();
//			System.out.println("number of rows inserted: " + result);	
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "+ se.getMessage());
//		}		
//	}
//
//	@Override
//	public void update(MemChatVO aMemChatVO) {
//		// 似乎不需要有修改這個方法，找不到欄位適合被修改的
//		
//	}
//
//	@Override
//	public void delete(String aMemChatChatId, String aMemChatMemId, Timestamp aDate) { 
//		try(Connection con = DriverManager.getConnection(MemChatJDBCDAO.url,MemChatJDBCDAO.account,MemChatJDBCDAO.password);
//			PreparedStatement pstmt = con.prepareStatement(MemChatJDBCDAO.DELETE);){
//			pstmt.setString(1, aMemChatChatId);
//			pstmt.setString(2, aMemChatMemId);
//			pstmt.setTimestamp(3, aDate);
//			int result = pstmt.executeUpdate();
//			System.out.println("number of rows deleted: " + result);
//		}catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "+ se.getMessage());
//		}
//	}
//
//	@Override
//	public List<MemChatVO> getAll() {
//		List<MemChatVO> memChatVOList = new ArrayList<>();
//		ResultSet rs = null;
//		try(Connection con = DriverManager.getConnection(MemChatJDBCDAO.url,MemChatJDBCDAO.account,MemChatJDBCDAO.password);
//			PreparedStatement pstmt = con.prepareStatement(MemChatJDBCDAO.GET_ALL);) {
//				rs = pstmt.executeQuery();
//				while(rs.next()){
//					MemChatVO memChatVO = new MemChatVO();
//					memChatVO.setMemChatChatId(rs.getString("memChatChatId"));
//					memChatVO.setMemChatMemId(rs.getString("memChatMemId"));
//					memChatVO.setMemChatDate(rs.getTimestamp("memChatDate"));
//					memChatVO.setMemChatContent(rs.getString("memChatContent"));
//					memChatVO.setMemChatPic(rs.getBytes("memChatPic"));
//
//					memChatVOList.add(memChatVO);
//				}// end while
//				
//			} catch (SQLException se) {
//				throw new RuntimeException("A database error occured. "+ se.getMessage());
//			} finally {
//				if (rs != null) {
//					try {
//						rs.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//			}// end try-catch-finally	
//		return memChatVOList;
//	}
//
//
//	@Override
//	public MemChatVO findByPrimaryKey(String aMemChatChatId, String aMemChatMemId, Timestamp aDate) {
//		MemChatVO memChatVO = null;
//		ResultSet rs = null;
//		try(Connection con = DriverManager.getConnection(MemChatJDBCDAO.url,MemChatJDBCDAO.account,MemChatJDBCDAO.password);
//			PreparedStatement pstmt = con.prepareStatement(MemChatJDBCDAO.GET_ONE);) {
//				pstmt.setString(1, aMemChatChatId);
//				pstmt.setString(2, aMemChatMemId);
//				pstmt.setTimestamp(3, aDate);
//				rs = pstmt.executeQuery();
//				if (rs.next()){
//					memChatVO = new MemChatVO();
//					
//					memChatVO.setMemChatChatId(rs.getString("memChatChatId"));
//					memChatVO.setMemChatMemId(rs.getString("memChatMemId"));
//					memChatVO.setMemChatDate(rs.getTimestamp("memChatDate"));
//					memChatVO.setMemChatContent(rs.getString("memChatContent"));
//					memChatVO.setMemChatPic(rs.getBytes("memChatPic"));
//				}else{
//					System.out.println("no matched data");
//				}
//			} catch (SQLException se) {
//				throw new RuntimeException("A database error occured. "+ se.getMessage());
//			} finally {
//				if (rs != null) {
//					try {
//						rs.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//			}// end try-catch-finally	
//		return memChatVO;
//	}
//
//	@Override
//	public List<MemChatVO> findByMemChatChatId(String aMemChatChatId) {
//		List<MemChatVO> memChatVOList = new ArrayList<>();
//		ResultSet rs = null;
//		try(Connection con = DriverManager.getConnection(MemChatJDBCDAO.url,MemChatJDBCDAO.account,MemChatJDBCDAO.password);
//			PreparedStatement pstmt = con.prepareStatement(MemChatJDBCDAO.GET_ALL_MEMCHATCHATID);) {
//				pstmt.setString(1, aMemChatChatId);
//				rs = pstmt.executeQuery();
//				while (rs.next()){
//					MemChatVO memChatVO = new MemChatVO();
//					
//					memChatVO.setMemChatChatId(rs.getString("memChatChatId"));
//					memChatVO.setMemChatMemId(rs.getString("memChatMemId"));
//					memChatVO.setMemChatDate(rs.getTimestamp("memChatDate"));
//					memChatVO.setMemChatContent(rs.getString("memChatContent"));
//					memChatVO.setMemChatPic(rs.getBytes("memChatPic"));
//					
//					memChatVOList.add(memChatVO);
//				};
//			} catch (SQLException se) {
//				throw new RuntimeException("A database error occured. "+ se.getMessage());
//			} finally {
//				if (rs != null) {
//					try {
//						rs.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//			}// end try-catch-finally	
//		return memChatVOList;
//	}
//	
//	
//	public static void main(String[] args) throws Exception{
//		MemChatDAO_interface dao = new MemChatJDBCDAO();
//		MemChatVO memChatVO = new MemChatVO();
//		
//		// 新增 insert(總共有5個欄位):
////		String aMemChatChatId = "10000001";
////		String aMemChatMemId = "10000002";
////		GregorianCalendar myGCDate = new GregorianCalendar();
////		myGCDate.setTime(new java.util.Date());
////		Timestamp myTsp = new Timestamp(myGCDate.getTime().getTime());
////		InputStream is = new FileInputStream("C:/Users/cuser/git/DDD_web/DDD_web/WebContent/images/smile.jpg");
////		byte[] byteAry = new byte[is.available()];
////		is.read(byteAry);
////		is.close();
////		
////		memChatVO.setMemChatChatId(aMemChatChatId);
////		memChatVO.setMemChatMemId(aMemChatMemId);
////		memChatVO.setMemChatDate(myTsp);
////		memChatVO.setMemChatContent(null);
////		memChatVO.setMemChatPic(byteAry);
////		
////		dao.insert(memChatVO);
//		
//		// 修改 update(共有1個欄位可修改):
////		String chatId = "10000001";
////		chatVO = dao.findByPrimaryKey(chatId); // 從DB拿舊VO，放到網頁上呈現
////		chatVO.setChatName("我們的小世界");; // 在網頁上修改此VO資料
////		dao.update(chatVO); // 再回放到DB中
//		
//		// 刪除 delete
//		// 有相依問題，要注意
////		MemChatVO memchatVO = dao.getAll().get(0);
////		dao.delete(memchatVO.getMemChatChatId(), memchatVO.getMemChatMemId(), memchatVO.getMemChatDate());
//		
//		// 查詢全部:
//		List<MemChatVO> memChatVOList = dao.getAll();
//		printData(memChatVOList);
////		MemChatVO myVO = dao.getAll().get(0);
////		java.sql.Timestamp ts = new java.sql.Timestamp(myVO.getMemChatDate().getTime());
////		System.out.println("\n"+ts);
//		
//		OrdService dao_ord = new OrdService();
//		OrdVO myVO = dao_ord.getAll().get(0);
//		java.sql.Date date = new java.sql.Date(myVO.getOrdDate().getTime());
//		System.out.println("\n"+date);
//		
//		
//		// 透過PK查看一筆資料 :
////		MemChatVO memchatVO = dao.getAll().get(0);
////		System.out.println(memchatVO.getMemChatDate());
////		memChatVO = dao.findByPrimaryKey(memchatVO.getMemChatChatId(), memchatVO.getMemChatMemId(), memchatVO.getMemChatDate());
////		printData(memChatVO);	
//		
//		// 透過MemChatChatId查看資料:
////		String memChatChatId = "10000001";
////		List<MemChatVO> memChatVOList = dao.findByMemChatChatId(memChatChatId);
////		printData(memChatVOList);		
////		System.out.println("new java.util.Date().getTime(): " + new java.util.Date().getTime());
//				
//	}// end main
//	
//	//以下不重要，只是用來驗證方法正不正確
//	private static void printData(List<MemChatVO> memChatVOList){
//		// memChatMemId memChatChatId memChatDate memChatContent memChatPic
//		if (!memChatVOList.isEmpty() && memChatVOList != null){
//			System.out.printf("%-15s %-15s  %-15s %-15s %-15s","memChatChatId", "memChatMemId", "memChatDate","memChatContent","memChatPic");	
//			System.out.printf("%n%90s","------------------------------------------------------------------------------------------------------------------------------------------------");	
//			for (MemChatVO memChatVO: memChatVOList){
//				System.out.printf("%n%-15s %-15s %-15s %-15s %-15s",
//				memChatVO.getMemChatChatId(),
//				memChatVO.getMemChatMemId(),
//				memChatVO.getMemChatDate(),
//				(memChatVO.getMemChatContent() != null)?memChatVO.getMemChatContent().substring(0,2): "NULL",
//				memChatVO.getMemChatPic());
//			};	
//		}else{
//			System.out.println("no data found.");
//		}		
//	}
//	
//	private static void printData(MemChatVO aMemChatVO){
//		// memChatMemId memChatChatId memChatDate memChatContent memChatPic
//		if (aMemChatVO != null){
//			System.out.printf("%-15s %-15s  %-15s %-15s %-15s","memChatChatId", "memChatMemId", "memChatDate","memChatContent","memChatPic");	
//			System.out.printf("%n%90s","------------------------------------------------------------------------------------------------------------------------------------------------");	
//			System.out.printf("%n%-15s %-15s %-15s %-15s %-15s",
//			aMemChatVO.getMemChatChatId(),
//			aMemChatVO.getMemChatMemId(),
//			aMemChatVO.getMemChatDate(),
//			(aMemChatVO.getMemChatContent() != null)?aMemChatVO.getMemChatContent().substring(0,2): "NULL",
//			aMemChatVO.getMemChatPic());
//		}			
//	}
//
//	@Override
//	public List<MemChatVO> getAll(Map<String, String[]> map) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<MemChatVO> getOldMsgBtwnTwoMems(String aMemChatMemId01, String aMemChatMemId02) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String getChatIdBtwenTwoMems(String aMemChatMemId01, String aMemChatMemId02) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<MemChatVO> getNewestMsgEachChatId(String aMemChatMemId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//
//
//}
