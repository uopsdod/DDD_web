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

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.io.*;

import com.memchat.model.MemChatVO;
import com.chat.model.ChatVO;
import com.chat.modelJDBC.ChatJDBCDAO;
import com.memchat.model.MemChatDAO_interface;


public class MemLiveCondJNDIDAO implements MemLiveCondDAO_interface {
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
	// memLiveCondLiveCondId memLiveCondMemId
	private static final String INSERT = "INSERT INTO memLiveCond (memLiveCondLiveCondId, memLiveCondMemId) VALUES (?, ?)";
	private static final String UPDATE = "";
	private static final String DELETE = "DELETE FROM memLiveCond WHERE memLiveCondLiveCondId= ? AND memLiveCondMemId = ? ";
	
	private static final String GET_ALL = "SELECT memLiveCondLiveCondId, memLiveCondMemId FROM memLiveCond";
	private static final String GET_ONE = "SELECT memLiveCondLiveCondId, memLiveCondMemId FROM memLiveCond WHERE memLiveCondLiveCondId= ? AND memLiveCondMemId = ?";
	private static final String GET_ALL_MemLiveCondMemId = "SELECT memLiveCondLiveCondId, memLiveCondMemId FROM memLiveCond WHERE memLiveCondMemId = ?";

	@Override
	public void insert(MemLiveCondVO aMemLiveCondVO) {
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(MemLiveCondJNDIDAO.INSERT);) {
			
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
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(MemLiveCondJNDIDAO.DELETE);){
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
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(MemLiveCondJNDIDAO.GET_ALL);) {
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
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(MemLiveCondJNDIDAO.GET_ONE);) {
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
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(MemLiveCondJNDIDAO.GET_ALL_MemLiveCondMemId);) {
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
	


}
