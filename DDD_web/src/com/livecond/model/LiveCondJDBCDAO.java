package com.livecond.model;

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

import com.memchat.model.MemChatJDBCDAO;
import com.memchat.model.MemChatVO;

import java.io.*;


public class LiveCondJDBCDAO implements LiveCondDAO_interface {
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String account = "ddd";
	private static String password = "1111";
	// 全部欄位名(複製用):
	// liveCondId liveCondName
	private static final String INSERT = "INSERT INTO livecond (liveCondId, liveCondName) VALUES(livecond_seq.NEXTVAL, ?)";
	private static final String UPDATE = "";
	private static final String DELETE = "DELETE FROM liveCond WHERE liveCondId= ?";
	
	private static final String GET_ALL = "SELECT liveCondId, liveCondName FROM liveCond ORDER BY liveCondId";
	private static final String GET_ONE = "SELECT liveCondId, liveCondName FROM liveCond WHERE liveCondId= ?";
	
	
	static{
		try {
			Class.forName(LiveCondJDBCDAO.driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void insert(LiveCondVO aLiveCondVO) {
		try(Connection con = DriverManager.getConnection(LiveCondJDBCDAO.url,LiveCondJDBCDAO.account,LiveCondJDBCDAO.password);
			PreparedStatement pstmt = con.prepareStatement(LiveCondJDBCDAO.INSERT);) {
				
				pstmt.setString(1, aLiveCondVO.getLiveCondName());
				
				int result = pstmt.executeUpdate();
				System.out.println("number of rows inserted: " + result);	
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "+ se.getMessage());
			}		
	}

	@Override
	public void update(LiveCondVO aLiveCondVO) {
		// 好像不需要
		
	}

	@Override
	public void delete(String aLiveCondId) {
		try(Connection con = DriverManager.getConnection(LiveCondJDBCDAO.url,LiveCondJDBCDAO.account,LiveCondJDBCDAO.password);
			PreparedStatement pstmt = con.prepareStatement(LiveCondJDBCDAO.DELETE);){
				pstmt.setString(1, aLiveCondId);
				int result = pstmt.executeUpdate();
				System.out.println("number of rows deleted: " + result);
			}catch (SQLException se) {
				throw new RuntimeException("A database error occured. "+ se.getMessage());
			}
	}

	@Override
	public List<LiveCondVO> getAll() {
		List<LiveCondVO> liveCondVOList = new ArrayList<>();
		ResultSet rs = null;
		try(Connection con = DriverManager.getConnection(LiveCondJDBCDAO.url,LiveCondJDBCDAO.account,LiveCondJDBCDAO.password);
			PreparedStatement pstmt = con.prepareStatement(LiveCondJDBCDAO.GET_ALL);) {
				rs = pstmt.executeQuery();
				while(rs.next()){
					LiveCondVO liveCondVO = new LiveCondVO();
					liveCondVO.setLiveCondId(rs.getString("liveCondId"));
					liveCondVO.setLiveCondName(rs.getString("liveCondName"));

					liveCondVOList.add(liveCondVO);
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
		return liveCondVOList;
	}

	@Override
	public LiveCondVO findByPrimaryKey(String aLiveCondId) {
		LiveCondVO liveCondVO = null;
		ResultSet rs = null;
		try(Connection con = DriverManager.getConnection(LiveCondJDBCDAO.url,LiveCondJDBCDAO.account,LiveCondJDBCDAO.password);
			PreparedStatement pstmt = con.prepareStatement(LiveCondJDBCDAO.GET_ONE);) {
				pstmt.setString(1, aLiveCondId);
				
				rs = pstmt.executeQuery();
				if (rs.next()){
					liveCondVO = new LiveCondVO();
					liveCondVO.setLiveCondId(rs.getString("liveCondId"));
					liveCondVO.setLiveCondName(rs.getString("liveCondName"));
					
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
		return liveCondVO;
	}

	public static void main(String[] args) throws Exception{
		LiveCondDAO_interface dao = new LiveCondJDBCDAO();
		LiveCondVO liveCondVO = new LiveCondVO();
		
		// 新增 insert(總共有1個欄位):
//		String liveCondName = "被帥哥找";		
//		liveCondVO.setLiveCondName(liveCondName);
//		dao.insert(liveCondVO);
		
		// 修改 update(共有0個欄位可修改):
		
		// 刪除 delete
//		String liveCondId = "102";
//		dao.delete(liveCondId);
		
		// 查詢全部:
		List<LiveCondVO> liveCondVOList = dao.getAll();
		printData(liveCondVOList);
		
		
		// 透過PK查看一筆資料 :
//		String liveCondId = "101";
//		liveCondVO = dao.findByPrimaryKey(liveCondId);
//		printData(liveCondVO);	
		
				
	}// end main
	
	//以下不重要，只是用來驗證方法正不正確
	private static void printData(List<LiveCondVO> liveCondVOList){
		// liveCondId liveCondName
		if (!liveCondVOList.isEmpty() && liveCondVOList != null){
			System.out.printf("%-15s %-15s","liveCondId", "liveCondName");	
			System.out.printf("%n%90s","------------------------------------------------------------------------------------------------------------------------------------------------");	
			for (LiveCondVO liveCondVO: liveCondVOList){
				System.out.printf("%n%-15s %-15s",
								  liveCondVO.getLiveCondId(), 
								  liveCondVO.getLiveCondName());	
			};	
		}else{
			System.out.println("no data found.");
		}		
	}
	
	private static void printData(LiveCondVO aLiveCondVO){
		// memChatMemId memChatChatId memChatDate memChatContent memChatPic
		if (aLiveCondVO != null){
			System.out.printf("%-15s %-15s","liveCondId", "liveCondName");	
			System.out.printf("%n%90s","------------------------------------------------------------------------------------------------------------------------------------------------");	
			System.out.printf("%n%-15s %-15s",
			aLiveCondVO.getLiveCondId(), 
			aLiveCondVO.getLiveCondName());
		}			
	}


}
