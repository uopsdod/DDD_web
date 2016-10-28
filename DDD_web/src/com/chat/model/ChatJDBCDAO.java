package com.chat.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChatJDBCDAO implements ChatDAO_interface {
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String account = "ddd";
	private static String password = "1111";
	// 全部欄位名(複製用):
	// chatId chatName
	private static final String INSERT = "INSERT INTO chat (chatId, chatName) VALUES ( chat_seq.NEXTVAL, ?)";
	private static final String UPDATE = "UPDATE chat SET chatName=? WHERE chatId = ?";
	private static final String DELETE = "DELETE FROM chat WHERE chatId = ?";
	
	private static final String GET_ALL = "SELECT chatId, chatName FROM chat ORDER BY chatId";
	private static final String GET_ONE = "SELECT chatId, chatName FROM chat WHERE chatId = ?";
	
	
	static{
		try {
			Class.forName(ChatJDBCDAO.driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	


	@Override
	public void insert(ChatVO aChatVO) {
		// 進階處理: 關掉auto commit???
		// "INSERT INTO chat VALUES ( chat_seq.NEXTVAL, ?)"
		try(Connection con = DriverManager.getConnection(ChatJDBCDAO.url,ChatJDBCDAO.account,ChatJDBCDAO.password);
			PreparedStatement pstmt = con.prepareStatement(ChatJDBCDAO.INSERT);) {

			pstmt.setString(1, aChatVO.getChatName());
			
			int result = pstmt.executeUpdate();
			System.out.println("number of rows inserted: " + result);	
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
		}
		
	}



	@Override
	public void update(ChatVO aChatVO) {
		// 進階處理: 關掉auto commit???
		// "UPDATE chat SET chatName=? WHERE chatId = ?"
		try(Connection con = DriverManager.getConnection(ChatJDBCDAO.url,ChatJDBCDAO.account,ChatJDBCDAO.password);
			PreparedStatement pstmt = con.prepareStatement(ChatJDBCDAO.UPDATE);) {

			pstmt.setString(1, aChatVO.getChatName());
			pstmt.setString(2, aChatVO.getChatId());
			

			int result = pstmt.executeUpdate();
			System.out.println("number of rows updated: " + result);
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
		}
		
	}



	@Override
	public void delete(String aChatId) {
		// "DELETE FROM chat WHERE chatId = ?"
		try(Connection con = DriverManager.getConnection(ChatJDBCDAO.url,ChatJDBCDAO.account,ChatJDBCDAO.password);
			PreparedStatement pstmt = con.prepareStatement(ChatJDBCDAO.DELETE);){
			pstmt.setString(1, aChatId);
			int result = pstmt.executeUpdate();
			System.out.println("number of rows deleted: " + result);
			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
		}
	}



	@Override
	public List<ChatVO> getAll() {
		List<ChatVO> chatVOList = new ArrayList<>();
		ResultSet rs = null;
		try(Connection con = DriverManager.getConnection(ChatJDBCDAO.url,ChatJDBCDAO.account,ChatJDBCDAO.password);
			PreparedStatement pstmt = con.prepareStatement(ChatJDBCDAO.GET_ALL);) {
				rs = pstmt.executeQuery();
				while(rs.next()){
					ChatVO chatVO = new ChatVO();
					chatVO.setChatId(rs.getString("chatId"));
					chatVO.setChatName(rs.getString("chatName"));
					
					chatVOList.add(chatVO);
				}// end while
				
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "+ se.getMessage());
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
			}// end try-catch-finally	
		return chatVOList;
	}



	@Override
	public ChatVO findByPrimaryKey(String aChatId) {
		ChatVO chatVO = null;
		ResultSet rs = null;
		try(Connection con = DriverManager.getConnection(ChatJDBCDAO.url,ChatJDBCDAO.account,ChatJDBCDAO.password);
			PreparedStatement pstmt = con.prepareStatement(ChatJDBCDAO.GET_ONE);) {
				pstmt.setString(1, aChatId);			
				rs = pstmt.executeQuery();
				if (rs.next()){
					chatVO = new ChatVO();
					chatVO.setChatId(rs.getString("chatId"));
					chatVO.setChatName(rs.getString("chatName"));
				}else{
					System.out.println("no matched data");
				}
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "+ se.getMessage());
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
			}// end try-catch-finally	
		return chatVO;
	}
	
	public static void main(String[] args){
		ChatDAO_interface dao = new ChatJDBCDAO();
		ChatVO chatVO = new ChatVO();
		
		// 新增 insert(總共有2個欄位):
		//chatVO.setChatId(aChatId); //使用 chat_seq
//		chatVO.setChatName(null);
//		dao.insert(chatVO);
		
		// 修改 update(共有1個欄位可修改):
//		String chatId = "10000001";
//		chatVO = dao.findByPrimaryKey(chatId); // 從DB拿舊VO，放到網頁上呈現
//		chatVO.setChatName("我們的小世界");; // 在網頁上修改此VO資料
//		dao.update(chatVO); // 再回放到DB中
		
		// 刪除 delete
		// 有相依問題，要注意
//		String memrepId = "10000001";
//		dao.delete(memrepId);
		
		// 查詢全部:
		List<ChatVO> chatVOList = dao.getAll();
		printData(chatVOList);
		
		
		// 特過PK查看一筆資料 :
//		String chatId = "10000001";
//		chatVO = dao.findByPrimaryKey(chatId);
//		printData(chatVO);	
				
	}// end main
	
	//以下不重要，只是用來驗證方法正不正確
	private static void printData(List<ChatVO> chatVOList){
		if (!chatVOList.isEmpty() && chatVOList != null){
			System.out.printf("%-15s %-15s","ChatId", "ChatName");	
			System.out.printf("%n%90s","------------------------------------------------------------------------------------------------------------------------------------------------");	
			for (ChatVO chatVO: chatVOList){
				System.out.printf("%n%-15s %-15s",
						chatVO.getChatId(),
						chatVO.getChatName());
			}			
		}else{
			System.out.println("no data found.");
		}		
	}
	
	private static void printData(ChatVO aChatVO){
		if (aChatVO != null){
			System.out.printf("%-15s %-15s","ChatId", "ChatName");	
			System.out.printf("%n%90s","------------------------------------------------------------------------------------------------------------------------------------------------");	
			System.out.printf("%n%-15s %-15s",
					aChatVO.getChatId(),
					aChatVO.getChatName());
		}			
	}


}
