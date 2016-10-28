package com.hotelserv.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class HotelServJNDIDAO implements HotelServDAO_interface{
	private static DataSource ds = null;
	static{
		try{
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		}catch(NamingException e){
			e.printStackTrace(System.err);
		}
	}
	
	private static String INSERT_STMT = "INSERT INTO HotelServ (hotelServServId,hotelServHotelId) VALUES (?,?)";
	private static String GET_ONE_STMT = "SELECT hotelServServId,hotelServHotelId FROM HotelServ where hotelServServId=? AND hotelServHotelId=?";
	private static String GET_ALL_STMT = "SElECT hotelServServId,hotelServHotelId FROM HotelServ order by hotelServServId";
	private static String UPDATE = "UPDATE wish set hotelServHotelId = ? where hotelServServId = ?";
	private static String DELETE = "DELETE FROM HotelServ where hotelServServId = ? AND  hotelServHotelId = ?";
	@Override
	public void insert(HotelServVO aHotelServVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			
			pstmt = con.prepareStatement(HotelServJNDIDAO.INSERT_STMT);
			
			pstmt.setString(1, aHotelServVO.getHotelServServId());
			pstmt.setString(2, aHotelServVO.getHotelServHotelId());
			pstmt.executeUpdate();
		}catch(SQLException se){
			throw new RuntimeException("A database error occured." + se.getMessage());
		}finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		
		
	}

	@Override
	public void update(HotelServVO aHotelServVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String aHotelServServId, String aHotelServHotelId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			
			pstmt = con.prepareStatement(HotelServJNDIDAO.DELETE);
			
			pstmt.setString(1, aHotelServServId);
			pstmt.setString(2, aHotelServHotelId);
			pstmt.executeUpdate();
		}catch(SQLException se){
			throw new RuntimeException("A database error occured." + se.getMessage());
		}finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
				
	}

	@Override
	public HotelServVO findByPrimaryKey(String aHotelServServId, String aHotelServHotelId) {
		HotelServVO hotelServVO = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			
			pstmt = con.prepareStatement(HotelServJNDIDAO.GET_ONE_STMT);
			pstmt.setString(1, aHotelServServId);
			pstmt.setString(2, aHotelServHotelId);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				hotelServVO = new HotelServVO();
				hotelServVO.setHotelServServId(rs.getString("hotelServServId"));
				hotelServVO.setHotelServHotelId(rs.getString("hotelServHotelId"));
			}
		}catch(SQLException se){
			throw new RuntimeException("A database error occured." + se.getMessage());
		}finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		return hotelServVO;
	}

	@Override
	public List<HotelServVO> getAll() {
		List<HotelServVO> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			
			pstmt = con.prepareStatement(HotelServJNDIDAO.GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while(rs.next()){
				HotelServVO hotelServVO = new HotelServVO();
				hotelServVO.setHotelServServId(rs.getString("hotelServServId"));
				hotelServVO.setHotelServHotelId(rs.getString("hotelServHotelId"));
				list.add(hotelServVO);
			}
		}catch(SQLException se){
			throw new RuntimeException("A database error occured." + se.getMessage());
		}finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
}
