package com.memlivecond.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.sql.Date;
import java.util.List;
import java.io.*;

import com.memchat.model.MemChatVO;
import com.chat.model.ChatJDBCDAO;
import com.chat.model.ChatVO;
import com.memchat.model.MemChatDAO_interface;


public class MemLiveCondJDBCDAO implements MemLiveCondDAO_interface {
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String account = "scott";
	private static String password = "tiger";
	// 全部欄位名(複製用):
	// memLiveCondLiveCondId memLiveCondMemId
	private static final String INSERT = "INSERT INTO memLiveCond (memLiveCondLiveCondId, memLiveCondMemId) VALUES (?, ?)";
	private static final String UPDATE = "";
	private static final String DELETE = "DELETE FROM memLiveCond WHERE memLiveCondLiveCondId= ? AND memLiveCondMemId = ? ";
	
	private static final String GET_ALL = "SELECT memLiveCondLiveCondId, memLiveCondMemId FROM memLiveCond";
	private static final String GET_ONE = "SELECT memLiveCondLiveCondId, memLiveCondMemId FROM memLiveCond WHERE memLiveCondLiveCondId= ? AND memLiveCondMemId = ?";
	private static final String GET_ALL_MemLiveCondMemId = "SELECT memLiveCondLiveCondId, memLiveCondMemId FROM memLiveCond WHERE memLiveCondMemId = ?";
	
	
	static{
		try {
			Class.forName(MemLiveCondJDBCDAO.driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(MemLiveCondVO aMemLiveCondVO) {
		try(Connection con = DriverManager.getConnection(MemLiveCondJDBCDAO.url,MemLiveCondJDBCDAO.account,MemLiveCondJDBCDAO.password);
			PreparedStatement pstmt = con.prepareStatement(MemLiveCondJDBCDAO.INSERT);) {
			
			pstmt.setString(1, aMemLiveCondVO.getMemLiveCondLiveCondId());
			pstmt.setString(2, aMemLiveCondVO.getMemLiveCondMemId());
			
			int result = pstmt.executeUpdate();
			System.out.println("number of rows inserted: " + result);	
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
		}		
	}

	@Override
	public void update(MemLiveCondVO aMemLiveCondVO) {
		// 似乎不需要有修改這個方法，找不到欄位適合被修改的
		
	}

	@Override
	public void delete(String aMemLiveCondLiveCondId, String aMemLiveCondMemId) { 
		try(Connection con = DriverManager.getConnection(MemLiveCondJDBCDAO.url,MemLiveCondJDBCDAO.account,MemLiveCondJDBCDAO.password);
			PreparedStatement pstmt = con.prepareStatement(MemLiveCondJDBCDAO.DELETE);){
			pstmt.setString(1, aMemLiveCondLiveCondId);
			pstmt.setString(2, aMemLiveCondMemId);
			
			int result = pstmt.executeUpdate();
			System.out.println("number of rows deleted: " + result);
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
		}
	}

	@Override
	public List<MemLiveCondVO> getAll() {
		List<MemLiveCondVO> memLiveCondList = new ArrayList<>();
		ResultSet rs = null;
		try(Connection con = DriverManager.getConnection(MemLiveCondJDBCDAO.url,MemLiveCondJDBCDAO.account,MemLiveCondJDBCDAO.password);
			PreparedStatement pstmt = con.prepareStatement(MemLiveCondJDBCDAO.GET_ALL);) {
				rs = pstmt.executeQuery();
				while(rs.next()){
					MemLiveCondVO memLiveCondVO = new MemLiveCondVO();
					memLiveCondVO.setMemLiveCondLiveCondId(rs.getString("MemLiveCondLiveCondId"));
					memLiveCondVO.setMemLiveCondMemId(rs.getString("MemLiveCondMemId"));

					memLiveCondList.add(memLiveCondVO);
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
		return memLiveCondList;
	}


	@Override
	public MemLiveCondVO findByPrimaryKey(String aMemLiveCondLiveCondId, String aMemLiveCondMemId) {
		MemLiveCondVO memLiveCondVO = null;
		ResultSet rs = null;
		try(Connection con = DriverManager.getConnection(MemLiveCondJDBCDAO.url,MemLiveCondJDBCDAO.account,MemLiveCondJDBCDAO.password);
			PreparedStatement pstmt = con.prepareStatement(MemLiveCondJDBCDAO.GET_ONE);) {
				pstmt.setString(1, aMemLiveCondLiveCondId);
				pstmt.setString(2, aMemLiveCondMemId);
				rs = pstmt.executeQuery();
				if (rs.next()){
					memLiveCondVO = new MemLiveCondVO();
					
					memLiveCondVO.setMemLiveCondLiveCondId(rs.getString("MemLiveCondLiveCondId"));
					memLiveCondVO.setMemLiveCondMemId(rs.getString("MemLiveCondMemId"));
					
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
		return memLiveCondVO;
	}

	@Override
	public List<MemLiveCondVO> findByMemLiveCondMemId(String aMemChatChatId) {
		List<MemLiveCondVO> memLiveCondList = new ArrayList<>();
		ResultSet rs = null;
		try(Connection con = DriverManager.getConnection(MemLiveCondJDBCDAO.url,MemLiveCondJDBCDAO.account,MemLiveCondJDBCDAO.password);
			PreparedStatement pstmt = con.prepareStatement(MemLiveCondJDBCDAO.GET_ALL_MemLiveCondMemId);) {
				pstmt.setString(1, aMemChatChatId);
				rs = pstmt.executeQuery();
				while (rs.next()){
					MemLiveCondVO memLiveCondVO = new MemLiveCondVO();
					
					memLiveCondVO.setMemLiveCondLiveCondId(rs.getString("MemLiveCondLiveCondId"));
					memLiveCondVO.setMemLiveCondMemId(rs.getString("MemLiveCondMemId"));
					
					memLiveCondList.add(memLiveCondVO);
				};
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
		return memLiveCondList;
	}
	
	
	public static void main(String[] args) throws Exception{
		MemLiveCondDAO_interface dao = new MemLiveCondJDBCDAO();
		MemLiveCondVO memLiveCondVO = new MemLiveCondVO();
		
		// 新增 insert(總共有2個欄位):		
//		memLiveCondVO.setMemLiveCondLiveCondId("102");
//		memLiveCondVO.setMemLiveCondMemId("10000003");
//		
//		dao.insert(memLiveCondVO);
		
		// 修改 update(共有0個欄位可修改):
		
		// 刪除 delete
//		String memLiveCondLiveCondId = "102";
//		String memLiveCondMemId = "10000001"; 
//		dao.delete(memLiveCondLiveCondId, memLiveCondMemId);
		
		// 查詢全部:
		List<MemLiveCondVO> MemLiveCondVOList = dao.getAll();
		printData(MemLiveCondVOList);
		
		
		// 透過PK查看一筆資料 :
//		String memLiveCondLiveCondId = "102";
//		String memLiveCondMemId = "10000001"; 
//		memLiveCondVO = dao.findByPrimaryKey(memLiveCondLiveCondId, memLiveCondMemId);
//		printData(memLiveCondVO);	
		
		// 透過MemChatChatId查看資料:
//		String MemLiveCondMemId = "10000001";
//		List<MemLiveCondVO> MemLiveCondVOList = dao.findByMemLiveCondMemId(MemLiveCondMemId);
//		printData(MemLiveCondVOList);		
				
	}// end main
	
	//以下不重要，只是用來驗證方法正不正確
	private static void printData(List<MemLiveCondVO> MemLiveCondVOList){
		if (!MemLiveCondVOList.isEmpty() && MemLiveCondVOList != null){
			System.out.printf("%-15s %-15s","memLiveCondLiveCondId", "memLiveCondMemId");	
			System.out.printf("%n%90s","------------------------------------------------------------------------------------------------------------------------------------------------");	
			for (MemLiveCondVO MemLiveCondVO: MemLiveCondVOList){
				System.out.printf("%n%-15s %-15s",
				MemLiveCondVO.getMemLiveCondLiveCondId(),
				MemLiveCondVO.getMemLiveCondMemId());
			};	
		}else{
			System.out.println("no data found.");
		}		
	}
	
	private static void printData(MemLiveCondVO aMemLiveCondVO){
		// memChatMemId memChatChatId memChatDate memChatContent memChatPic
		if (aMemLiveCondVO != null){
			System.out.printf("%-15s %-15s","memLiveCondLiveCondId", "memLiveCondMemId");	
			System.out.printf("%n%90s","------------------------------------------------------------------------------------------------------------------------------------------------");	
			System.out.printf("%n%-15s %-15s", aMemLiveCondVO.getMemLiveCondLiveCondId(), aMemLiveCondVO.getMemLiveCondMemId());
		}			
	}




}
