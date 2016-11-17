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

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.memchat.model.MemChatVO;

import java.io.*;


public class LiveCondJDNIDAO implements LiveCondDAO_interface {
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
	// liveCondId liveCondName
	private static final String INSERT = "INSERT INTO livecond (liveCondId, liveCondName) VALUES(livecond_seq.NEXTVAL, ?)";
	private static final String UPDATE = "";
	private static final String DELETE = "DELETE FROM liveCond WHERE liveCondId= ?";
	
	private static final String GET_ALL = "SELECT liveCondId, liveCondName FROM liveCond ORDER BY liveCondId";
	private static final String GET_ONE = "SELECT liveCondId, liveCondName FROM liveCond WHERE liveCondId= ?";

	@Override
	public void insert(LiveCondVO aLiveCondVO) {
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(LiveCondJDNIDAO.INSERT);) {
				
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
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(LiveCondJDNIDAO.DELETE);){
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
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(LiveCondJDNIDAO.GET_ALL);) {
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
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(LiveCondJDNIDAO.GET_ONE);) {
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

}
