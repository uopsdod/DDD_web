package com.ord.model;

import java.util.*;
import java.sql.*;

public class OrdJDBCDAO implements OrdDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "scott";
	String passwd = "tiger";
	
	/*
	 * = INSERT_STMT 對應 =
	 * 01    ordID
	 * 02-01 ordRoomId
	 * 03-02 ordMemId
	 * 04-03 ordHotelId
	 * 05-04 ordPrice
	 * 06-05 ordLiveDate
	 * 07-06 ordDate
	 * 08    ordStatus
	 * 09-07 ordRatingContent
	 * 10-08 ordRatingStarNo
	 * 11-09 ordQrPic
	 * 12-10 ordMsgNo
	 * test
	*/

	/* 日期記得改回Sysdate */
	private static final String INSERT_STMT = 
		"INSERT INTO ord (ordID,ordRoomId,ordMemId,ordHotelId,ordPrice,ordLiveDate,ordDate,ordStatus,ordRatingContent,ordRatingStarNo,ordQrPic,ordMsgNo)"
		+ "VALUES (CONCAT(TO_CHAR(SYSDATE,'YYYYMM'),ord_seq.NEXTVAL), ?, ?, ?, ?, ?, ?, '0', ?,?,?,?)";

	private static final String GET_ALL_STMT = 
		"SELECT * FROM ord order by ordID DESC";

	private static final String GET_ONE_STMT = 
		"SELECT * FROM ord where ordId = ?";
	
	private static final String DELETE = 
		"DELETE FROM ord where ordId = ?";
	
	private static final String UPDATE = 
		"UPDATE ord set ordStatus=? where ordId = ?";	
	
	@Override
	public int insert(OrdVO aOrdVO){
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateCount = 0;
		
		try{
			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url,this.userid,this.passwd);
			pstmt = con.prepareStatement(OrdJDBCDAO.INSERT_STMT);
			pstmt.setString(1, aOrdVO.getOrdRoomId());
			pstmt.setString(2, aOrdVO.getOrdMemId());
			pstmt.setString(3, aOrdVO.getOrdHotelId());
			pstmt.setInt(4, aOrdVO.getOrdPrice());
			pstmt.setDate(5, aOrdVO.getOrdLiveDate());
			pstmt.setDate(6, aOrdVO.getOrdDate());
			pstmt.setString(7, aOrdVO.getOrdRatingContent());
			pstmt.setInt(8, aOrdVO.getOrdRatingStarNo());
			pstmt.setBytes(9, aOrdVO.getOrdQrPic());
			pstmt.setString(10, aOrdVO.getOrdMsgNo());
			updateCount = pstmt.executeUpdate();
		}
		catch(ClassNotFoundException e){
			throw new RuntimeException("Could not load database driver. "+ e.getMessage());
		}
		catch(SQLException se){
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		finally{
			if(pstmt!=null){
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
	public int update(OrdVO aOrdVO){
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateCount = 0;
		
		try{
			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url,this.userid,this.passwd);
			pstmt = con.prepareStatement(OrdJDBCDAO.UPDATE);
			pstmt.setString(1, aOrdVO.getOrdStatus());
			pstmt.setString(2, aOrdVO.getOrdId());
			updateCount = pstmt.executeUpdate();
		}
		catch(ClassNotFoundException e){
			throw new RuntimeException("Could not load database driver. "+ e.getMessage());
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
	public int delete(String aOrdId){
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateCount = 0;
		
		try{
			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url,this.userid,this.passwd);
			pstmt = con.prepareStatement(OrdJDBCDAO.DELETE);
			pstmt.setString(1,aOrdId);
			updateCount = pstmt.executeUpdate();
		}
		catch(ClassNotFoundException e){
			throw new RuntimeException("Could not load database driver. "+e.getMessage());
		}
		catch(SQLException se){
			throw new RuntimeException("A database error occured. "+se.getMessage());
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
	public OrdVO findByPrimaryKey(String aOrdId){
		OrdVO ordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url,this.userid,this.passwd);
			pstmt = con.prepareStatement(OrdJDBCDAO.GET_ONE_STMT);
			pstmt.setString(1, aOrdId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				ordVO = new OrdVO();
				ordVO.setOrdId(rs.getString("ordID"));
				ordVO.setOrdRoomId(rs.getString("ordRoomId"));
				ordVO.setOrdMemId(rs.getString("ordMemId"));
				ordVO.setOrdHotelId(rs.getString("ordHotelId"));
				ordVO.setOrdPrice(rs.getInt("ordPrice"));
				ordVO.setOrdLiveDate(rs.getDate("ordLiveDate"));
				ordVO.setOrdDate(rs.getDate("ordDate"));
				ordVO.setOrdStatus(rs.getString("ordStatus"));
				ordVO.setOrdRatingContent(rs.getString("ordRatingContent"));
				ordVO.setOrdRatingStarNo(rs.getInt("ordRatingStarNo"));
				ordVO.setOrdMsgNo(rs.getString("ordMsgNo"));
			}
		}
		
		catch(ClassNotFoundException e){
			throw new RuntimeException("Could not load database driver. " + e.getMessage());
		}
		catch(SQLException se){
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		finally{
			if(rs != null){
				try{
					rs.close();
				}
				catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
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
		return ordVO;
	}
	
	@Override
	public List<OrdVO> getAll(){
		List<OrdVO> list = new ArrayList<OrdVO>();
		OrdVO ordVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try{
			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url,this.userid,this.passwd);
			pstmt = con.prepareStatement(OrdJDBCDAO.GET_ALL_STMT);		
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				ordVO = new OrdVO();
				ordVO.setOrdId(rs.getString("ordID"));
				ordVO.setOrdRoomId(rs.getString("ordRoomId"));
				ordVO.setOrdMemId(rs.getString("ordMemId"));
				ordVO.setOrdHotelId(rs.getString("ordHotelId"));
				ordVO.setOrdPrice(rs.getInt("ordPrice"));
				ordVO.setOrdLiveDate(rs.getDate("ordLiveDate"));
				ordVO.setOrdDate(rs.getDate("ordDate"));
				ordVO.setOrdStatus(rs.getString("ordStatus"));
				ordVO.setOrdRatingContent(rs.getString("ordRatingContent"));
				ordVO.setOrdRatingStarNo(rs.getInt("ordRatingStarNo"));
				ordVO.setOrdMsgNo(rs.getString("ordMsgNo"));
				list.add(ordVO);
			}			
		}
		catch(ClassNotFoundException e){
			throw new RuntimeException("Could not load database driver. "+ e.getMessage());
		}
		catch(SQLException se){
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		finally{
			if(rs != null){
				try{
					rs.close();
				}
				catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
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
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OrdJDBCDAO dao = new OrdJDBCDAO();
		
		//新增
//		OrdVO ordVO1 = new OrdVO();
//		
//		ordVO1.setOrdId("123");
//		ordVO1.setOrdRoomId();
//		ordVO1.setMemID();
//		ordVO1.
//		ordVO1.
//		ordVO1.
//		ordVO1.
//		ordVO1.
//		ordVO1.
//		ordVO1.
//		ordVO1.
//		ordVO1.
//		ordVO1.
//		
//		dao.insert(ordVO1);
		
		//修改
//		OrdVO ordVO2 = new OrdVO();
//		ordVO2.setOrdId("2016101025");
//		ordVO2.setOrdStatus("5");
//		int updateCount_update =  dao.update(ordVO2);
//		System.out.println(updateCount_update);
		
		//刪除(done)	
//		int updateCount_delete = dao.delete("2016101002");
//		System.out.println(updateCount_delete);
		
		//查詢(done)	
//		OrdVO ordVO3 = dao.findByPrimaryKey("2016101001");	
//		System.out.print(ordVO3.getOrdId() +",");
//		System.out.print(ordVO3.getOrdRoomId()+",");
//		System.out.print(ordVO3.getOrdMemId()+",");
//		System.out.print(ordVO3.getOrdHotelId()+",");
//		System.out.print(ordVO3.getOrdPrice() +",");			
//		System.out.print(ordVO3.getOrdLiveDate()+",");
//		System.out.print(ordVO3.getOrdDate()+",");
//		System.out.print(ordVO3.getOrdStatus()+",");
//		System.out.print(ordVO3.getOrdRatingContent()+",");
//		System.out.print(ordVO3.getOrdRatingStarNo() +",");
//		System.out.print(ordVO3.getOrdMsgNo());
//		System.out.println();		
		
		//列出所有(done)		
//		List<OrdVO> list = dao.getAll();
//		for(OrdVO aOrd : list){
//			System.out.print(aOrd.getOrdId() +",");
//			System.out.print(aOrd.getOrdRoomId()+",");
//			System.out.print(aOrd.getOrdMemId()+",");
//			System.out.print(aOrd.getOrdHotelId()+",");
//			System.out.print(aOrd.getOrdPrice() +",");			
//			System.out.print(aOrd.getOrdLiveDate()+",");
//			System.out.print(aOrd.getOrdDate()+",");
//			System.out.print(aOrd.getOrdStatus()+",");
//			System.out.print(aOrd.getOrdRatingContent()+",");
//			System.out.print(aOrd.getOrdRatingStarNo() +",");
//			System.out.print(aOrd.getOrdMsgNo());
//			System.out.println();
//		}
	}
	
}
