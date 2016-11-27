package com.hotelrep.modelJDBC;

import java.util.*;
import java.sql.*;

public class HotelRepJDBCDAO implements HotelRepDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "scott";
	String passwd = "tiger";
	
	private static final String INSERT =
			"INSERT INTO hotelrep (hotelrepId, hotelrepHotelId, hotelrepMemId, hotelrepOrdId, hotelrepEmpId, hotelrepContent, hotelrepStatus, hotelrepDate, hotelrepReviewDate) "
		  + "VALUES (hotelRep_seq.NEXTVAL,?,?,?,null,?,'0',sysdate,null)";
	
	private static final String UPDATE =
			"UPDATE hotelrep SET hotelrepEmpId=?, hotelrepStatus=?, hotelrepreviewdate = sysdate where hotelrepId = ?";
		
	private static final String DELETE =
			"DELETE FROM hotelrep WHERE hotelrepId = ?";

	private static final String GET_ALL =
			"SELECT hotelrepId, hotelrepHotelId, hotelrepMemId, hotelrepOrdId, hotelrepEmpId, hotelrepContent, hotelrepStatus, hotelrepDate, hotelrepReviewDate FROM hotelrep ORDER BY hotelrepId";
	
	private static final String GET_ALL_HOTELREPSTATUS =
			"SELECT hotelrepId, hotelrepHotelId, hotelrepMemId, hotelrepOrdId, hotelrepEmpId, hotelrepContent, hotelrepStatus, hotelrepDate, hotelrepReviewDate FROM hotelrep WHERE hotelrepStatus = ? ORDER BY hotelrepId";
	
	private static final String GET_ONE =
			"SELECT hotelrepId, hotelrepHotelId, hotelrepMemId, hotelrepOrdId, hotelrepEmpId, hotelrepContent, hotelrepStatus, hotelrepDate, hotelrepReviewDate FROM hotelrep WHERE hotelrepId = ?";
	
	
	@Override
	public int insert(HotelRepVO aHotelRepVO){
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateCount = 0;
		
		try {
			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url,this.userid,this.passwd);
			pstmt = con.prepareStatement(HotelRepJDBCDAO.INSERT);
			
			pstmt.setString(1,aHotelRepVO.getHotelRepHotelId());
			pstmt.setString(2,aHotelRepVO.getHotelRepMemId());
			pstmt.setString(3,aHotelRepVO.getHotelRepOrdId());
			pstmt.setString(4,aHotelRepVO.getHotelRepContent());
			updateCount = pstmt.executeUpdate();
		}
		
		catch(ClassNotFoundException e){
			throw new RuntimeException("Could not load database driver."+ e.getMessage());
		}
		
		catch(SQLException se){
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}
				catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}
				catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		
		return updateCount;
	}
	
	@Override
	public int update(HotelRepVO aHotelRepVO){
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateCount = 0;
		
		try {
			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url,this.userid,this.passwd);
			
			pstmt = con.prepareStatement(HotelRepJDBCDAO.UPDATE);
			pstmt.setString(1, aHotelRepVO.getHotelRepEmpId());
			pstmt.setString(2, aHotelRepVO.getHotelRepStatus());
			pstmt.setString(3, aHotelRepVO.getHotelRepId());

			updateCount = pstmt.executeUpdate();

		}
		catch(ClassNotFoundException e){
			throw new RuntimeException("Could not load database driver."+ e.getMessage());
		}
		catch(SQLException se){
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}
				catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}
				catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		return updateCount;
	}
	
	@Override
	public int delete(String aHotelRepId){
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateCount = 0;
		try {
			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url,this.userid,this.passwd);
			pstmt = con.prepareStatement(HotelRepJDBCDAO.DELETE);
			pstmt.setString(1, aHotelRepId);
			updateCount = pstmt.executeUpdate();
			
		}
		
		catch(ClassNotFoundException e){
			throw new RuntimeException("Could not load database driver."+ e.getMessage());
		}
		
		catch(SQLException se){
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}
				catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}
				catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		return updateCount;
	}
	
	@Override
	public List<HotelRepVO> getAll(){
		List<HotelRepVO> list = new ArrayList<HotelRepVO>();
		HotelRepVO hotelRepVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url,this.userid,this.passwd);
			pstmt = con.prepareStatement(HotelRepJDBCDAO.GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				hotelRepVO = new HotelRepVO();
				hotelRepVO.setHotelRepId(rs.getString("hotelRepId"));
				hotelRepVO.setHotelRepHotelId(rs.getString("hotelRepHotelId"));
				hotelRepVO.setHotelRepMemId(rs.getString("hotelRepMemId")); 
				hotelRepVO.setHotelRepOrdId(rs.getString("hotelRepOrdId"));
				hotelRepVO.setHotelRepEmpId(rs.getString("hotelRepEmpId"));
				hotelRepVO.setHotelRepContent(rs.getString("hotelRepContent"));
				hotelRepVO.setHotelRepStatus(rs.getString("hotelRepStatus"));
				hotelRepVO.setHotelRepDate(rs.getDate("hotelRepDate")); 
				hotelRepVO.setHotelRepReviewDate(rs.getDate("hotelRepReviewDate"));
				list.add(hotelRepVO);				
			}
		}
		
		catch(ClassNotFoundException e){
			throw new RuntimeException("Could not load database driver."+ e.getMessage());
		}
		
		catch(SQLException se){
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}
				catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}
				catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public List<HotelRepVO> getAllByHotelRepStatus(String aHotelRepStatus){
		List<HotelRepVO> list = new ArrayList<HotelRepVO>();
		HotelRepVO hotelRepVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url,this.userid,this.passwd);
			pstmt = con.prepareStatement(HotelRepJDBCDAO.GET_ALL_HOTELREPSTATUS);
			pstmt.setString(1, aHotelRepStatus);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				hotelRepVO = new HotelRepVO();
				hotelRepVO.setHotelRepId(rs.getString("hotelRepId"));
				hotelRepVO.setHotelRepHotelId(rs.getString("hotelRepHotelId"));
				hotelRepVO.setHotelRepMemId(rs.getString("hotelRepMemId")); 
				hotelRepVO.setHotelRepOrdId(rs.getString("hotelRepOrdId"));
				hotelRepVO.setHotelRepEmpId(rs.getString("hotelRepEmpId"));
				hotelRepVO.setHotelRepContent(rs.getString("hotelRepContent"));
				hotelRepVO.setHotelRepStatus(rs.getString("hotelRepStatus"));
				hotelRepVO.setHotelRepDate(rs.getDate("hotelRepDate")); 
				hotelRepVO.setHotelRepReviewDate(rs.getDate("hotelRepReviewDate"));
				list.add(hotelRepVO);				
			}
			
		}
		
		catch(ClassNotFoundException e){
			throw new RuntimeException("Could not load database driver."+ e.getMessage());
		}
		
		catch(SQLException se){
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}
				catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}
				catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public HotelRepVO findByPrimaryKey(String aHotelRepId){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HotelRepVO hotelRepVO = null;
		
		try {
			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url,this.userid,this.passwd);
			pstmt = con.prepareStatement(HotelRepJDBCDAO.GET_ONE);
			pstmt.setString(1, aHotelRepId);
			rs = pstmt.executeQuery();
			
			if (rs.next()){
				hotelRepVO = new HotelRepVO();
				hotelRepVO.setHotelRepId(rs.getString("hotelRepId")); 
				hotelRepVO.setHotelRepOrdId(rs.getString("hotelRepOrdId"));
				hotelRepVO.setHotelRepMemId(rs.getString("hotelRepMemId")); 
				hotelRepVO.setHotelRepHotelId(rs.getString("hotelRepHotelId")); 
				hotelRepVO.setHotelRepEmpId(rs.getString("hotelRepEmpId"));
				hotelRepVO.setHotelRepContent(rs.getString("hotelRepContent"));
				hotelRepVO.setHotelRepStatus(rs.getString("hotelRepStatus"));
				hotelRepVO.setHotelRepDate(rs.getDate("hotelRepDate")); 
				hotelRepVO.setHotelRepReviewDate(rs.getDate("hotelRepReviewDate"));
			}
		}
		
		catch(ClassNotFoundException e){
			throw new RuntimeException("Could not load database driver."+ e.getMessage());
		}
		
		catch(SQLException se){
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}
				catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}
				catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		return hotelRepVO;
	}	
	
	@Override
	public List<HotelRepVO> getAll(Map<String, String[]> aMap){
		return getAll(aMap, HotelRepDAO_interface.tableName);
	}
	
	private List<HotelRepVO> getAll(Map<String, String[]> aMap, String aTableName) {
		List<HotelRepVO> list = new ArrayList<HotelRepVO>();
		HotelRepVO empVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			Class.forName(this.driver);			
			con = DriverManager.getConnection(this.url,this.userid,this.passwd);		
			String finalSQL = testing.CompositeQuery_anyTable_JNDI.getQuerySQL(aMap, tableName);
			
			pstmt = con.prepareStatement(finalSQL);
			
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				HotelRepVO hotelRepVO = new HotelRepVO();
				hotelRepVO.setHotelRepId(rs.getString("hotelRepId")); 
				hotelRepVO.setHotelRepOrdId(rs.getString("hotelRepOrdId"));
				hotelRepVO.setHotelRepMemId(rs.getString("hotelRepMemId")); 
				hotelRepVO.setHotelRepHotelId(rs.getString("hotelRepHotelId")); 
				hotelRepVO.setHotelRepEmpId(rs.getString("hotelRepEmpId"));
				hotelRepVO.setHotelRepContent(rs.getString("hotelRepContent"));
				hotelRepVO.setHotelRepStatus(rs.getString("hotelRepStatus"));
				hotelRepVO.setHotelRepDate(rs.getDate("hotelRepDate")); 
				hotelRepVO.setHotelRepReviewDate(rs.getDate("hotelRepReviewDate"));

				list.add(hotelRepVO); // Store the row in the List
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}
		
		catch(ClassNotFoundException e){
			throw new RuntimeException("Could not load database driver."+ e.getMessage());
		}		
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
		HotelRepJDBCDAO dao = new HotelRepJDBCDAO();
		
		//新增
//		HotelRepVO hotelRepVO01 = new HotelRepVO();
//		
//		hotelRepVO01.setHotelRepHotelId("54321");
//		hotelRepVO01.setHotelRepMemId("87654321");
//		hotelRepVO01.setHotelRepOrdId("0987654321");
//		hotelRepVO01.setHotelRepContent("一定要配溫開水2");
//		int updateCount_insert = dao.insert(hotelRepVO01);
//		System.out.println(updateCount_insert);
		
		//修改
//		HotelRepVO hotelRepVO02 = new HotelRepVO();
//		hotelRepVO02.setHotelRepEmpId("88887");
//		hotelRepVO02.setHotelRepStatus("3");
//		hotelRepVO02.setHotelRepId("100000004");
//		int updateCount_update = dao.update(hotelRepVO02);
//		System.out.println(updateCount_update);
		
		//刪除
//		int updateCount_delete = dao.delete("100000010");
//		System.out.println(updateCount_delete);
		
		//查詢全部
//		List<HotelRepVO> list01 = dao.getAll();
//		for(HotelRepVO aHotelRepVO : list01){
//			System.out.println(aHotelRepVO.getHotelRepId()); 
//			System.out.println(aHotelRepVO.getHotelRepMemId()); 
//			System.out.println(aHotelRepVO.getHotelRepHotelId()); 
//			System.out.println(aHotelRepVO.getHotelRepEmpId()); 
//			System.out.println(aHotelRepVO.getHotelRepContent()); 
//			System.out.println(aHotelRepVO.getHotelRepStatus()); 
//			System.out.println(aHotelRepVO.getHotelRepDate()); 
//			System.out.println(aHotelRepVO.getHotelRepDate()); 			
//		}
		
		//依狀態查詢
//		List<HotelRepVO> list02 = dao.getAllByHotelRepStatus("3");
//		for(HotelRepVO aHotelRepVO : list02){
//			System.out.println(aHotelRepVO.getHotelRepId()); 
//			System.out.println(aHotelRepVO.getHotelRepMemId()); 
//			System.out.println(aHotelRepVO.getHotelRepHotelId()); 
//			System.out.println(aHotelRepVO.getHotelRepEmpId()); 
//			System.out.println(aHotelRepVO.getHotelRepContent()); 
//			System.out.println(aHotelRepVO.getHotelRepStatus()); 
//			System.out.println(aHotelRepVO.getHotelRepDate()); 
//			System.out.println(aHotelRepVO.getHotelRepDate()); 			
//		}		
		
		
		//查詢一筆
//		HotelRepVO hotelRepVO03 = dao.findByPrimaryKey("100000001");
//		System.out.println(hotelRepVO03.getHotelRepId()); 
//		System.out.println(hotelRepVO03.getHotelRepMemId()); 
//		System.out.println(hotelRepVO03.getHotelRepHotelId()); 
//		System.out.println(hotelRepVO03.getHotelRepEmpId()); 
//		System.out.println(hotelRepVO03.getHotelRepContent()); 
//		System.out.println(hotelRepVO03.getHotelRepStatus()); 
//		System.out.println(hotelRepVO03.getHotelRepDate()); 
//		System.out.println(hotelRepVO03.getHotelRepDate()); 	
		
		
		
	}

}
