package com.chat.modelJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.chat.model.ChatDAO_interface;
import com.chat.model.ChatVO;
import com.memchat.model.MemChatVO;

public class ChatJNDIDAO implements ChatDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	// 全部欄位名(複製用):
	// chatId chatName
	private static final String INSERT = "INSERT INTO chat (chatId, chatName) VALUES ( chat_seq.NEXTVAL, ?)";
	private static final String UPDATE = "UPDATE chat SET chatName=? WHERE chatId = ?";
	private static final String DELETE = "DELETE FROM chat WHERE chatId = ?";
	
	private static final String GET_ALL = "SELECT chatId, chatName FROM chat ORDER BY chatId";
	private static final String GET_ONE = "SELECT chatId, chatName FROM chat WHERE chatId = ?";

	@Override
	public void insert(ChatVO aChatVO) {
		// 進階處理: 關掉auto commit???
		// "INSERT INTO chat VALUES ( chat_seq.NEXTVAL, ?)"
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(ChatJNDIDAO.INSERT);) {

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
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(ChatJNDIDAO.UPDATE);) {

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
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(ChatJNDIDAO.DELETE);){
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
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(ChatJNDIDAO.GET_ALL);) {
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
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(ChatJNDIDAO.GET_ONE);) {
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



	@Override
	public List<ChatVO> getAll(Map<String, String[]> aMap) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void insertWithMemChats(ChatVO chatVO, List<MemChatVO> list) {
		// TODO Auto-generated method stub
		
	}

}
