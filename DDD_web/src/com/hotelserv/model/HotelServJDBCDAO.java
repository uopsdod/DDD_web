package com.hotelserv.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelServJDBCDAO implements HotelServDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "plzdongo";
	String passwd = "Tom800712";
	
	private static String GET_All_ByHotelId_STMT = "SElECT hotelServServId,hotelServHotelId FROM HotelServ  where hotelServHotelId = ? order by hotelServServId";
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
			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url, this.userid, this.passwd);
			pstmt = con.prepareStatement(HotelServJDBCDAO.INSERT_STMT);
			
			pstmt.setString(1, aHotelServVO.getHotelServServId());
			pstmt.setString(2, aHotelServVO.getHotelServHotelId());
			pstmt.executeUpdate();
		}catch(ClassNotFoundException e){
			throw new RuntimeException("coludn't load database driver." + e.getMessage());
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
	public List<HotelServVO> findByHotelId(String aHotelId){return null;};
	
	@Override
	public void delete(String aHotelServServId, String aHotelServHotelId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url, this.userid, this.passwd);
			pstmt = con.prepareStatement(HotelServJDBCDAO.DELETE);
			
			pstmt.setString(1, aHotelServServId);
			pstmt.setString(2, aHotelServHotelId);
			pstmt.executeUpdate();
		}catch(ClassNotFoundException e){
			throw new RuntimeException("coludn't load database driver." + e.getMessage());
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
			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url, this.userid, this.passwd);
			pstmt = con.prepareStatement(HotelServJDBCDAO.GET_ONE_STMT);
			pstmt.setString(1, aHotelServServId);
			pstmt.setString(2, aHotelServHotelId);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				hotelServVO = new HotelServVO();
				hotelServVO.setHotelServServId(rs.getString("hotelServServId"));
				hotelServVO.setHotelServHotelId(rs.getString("hotelServHotelId"));
			}
		}catch(ClassNotFoundException e){
			throw new RuntimeException("coludn't load database driver." + e.getMessage());
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
			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url, this.userid, this.passwd);
			pstmt = con.prepareStatement(HotelServJDBCDAO.GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while(rs.next()){
				HotelServVO hotelServVO = new HotelServVO();
				hotelServVO.setHotelServServId(rs.getString("hotelServServId"));
				hotelServVO.setHotelServHotelId(rs.getString("hotelServHotelId"));
				list.add(hotelServVO);
			}
		}catch(ClassNotFoundException e){
			throw new RuntimeException("coludn't load database driver." + e.getMessage());
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
	
	public static void main(String[] args) {
		HotelServJDBCDAO dao = new HotelServJDBCDAO();
		HotelServVO hotelServVO = new HotelServVO();
		//insert
//		hotelServVO.setHotelServServId("101");
//		hotelServVO.setHotelServHotelId("10001");
//		dao.insert(hotelServVO);
		
		//delete
//		dao.delete("101","10001");
		
		//select one only
//		hotelServVO = dao.findByPrimaryKey("115", "10001");
//		System.out.print(hotelServVO.getHotelServServId() + ",");
//		System.out.println(hotelServVO.getHotelServHotelId());
		
		//select All
		List<HotelServVO> list = dao.getAll();
		for(HotelServVO hotelServVO1 : list){
			System.out.print(hotelServVO1.getHotelServServId() + ",");
			System.out.println(hotelServVO1.getHotelServHotelId());
		}

	}


}
