package com.ord.model;

import java.util.*;
import java.sql.*;
import java.io.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class OrdDAO implements OrdDAO_interface {
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "scott";
//	String passwd = "tiger";
	
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		}
		catch(NamingException e){
			e.printStackTrace();
		}
	}
	
	/*
	 * = INSERT_STMT 對應 =
	 * 01    ordID
	 * 02-01 ordRoomId
	 * 03-02 ordMemId
	 * 04-03 ordHotelId
	 * 05-04 ordPrice
	 * 06-05 ordLiveDate
	 * 07    ordDate
	 * 08    ordStatus
	 * 09	 ordRatingContent
	 * 10	 ordRatingStarNo
	 * 11-06 ordQrPic
	 * 12-07 ordMsgNo
	*/

	/* (一般會員)新增一筆訂單 */
	private static final String INSERT_STMT = 
		"INSERT INTO ord (ordID,ordRoomId,ordMemId,ordHotelId,ordPrice,ordLiveDate,ordDate,ordStatus,ordRatingContent,ordRatingStarNo,ordQrPic,ordMsgNo)"
		+ "VALUES (CONCAT(TO_CHAR(SYSDATE,'YYYYMM'),ord_seq.NEXTVAL), ?, ?, ?, ?, ?, sysdate, '0', null,null,?,?)";

	/* (一般會員)新增評論及星星數 & (系統)修改訂單狀態 */
	private static final String UPDATE = 
		"UPDATE ord set ordStatus=?, ordRatingContent=?, ordRatingStarNo=? where ordId = ?";	

	/* (練習用)刪除 */
	private static final String DELETE = 
		"DELETE FROM ord where ordId = ?";

	/* (管理員)依訂單編號查詢 */
	private static final String GET_ONE_STMT = 
		"SELECT * FROM ord where ordId = ?";
	
	/* (管理員)查詢所有訂單內容 評論及星星數 */
	private static final String GET_ALL_STMT = 
		"SELECT * FROM ord order by ordID DESC";

	/* (一般會員)列出該一般會員的所有訂單 QRCode 驗證碼 */
	private static final String GET_ALL_ORDMEMID_STMT = 
		"SELECT * FROM ord where OrdMemId = ? order by ordID DESC";	
	
	/* (廠商會員)列出該廠商會員的所有訂單 */
	private static final String GET_ALL_ORDHOTELID_STMT = 
		"SELECT * FROM ord where OrdHotelId = ? order by ordID DESC";		
	
	
	@Override
	public int insert(OrdVO aOrdVO){
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateCount = 0;
		
		/*
		 * = INSERT_STMT 對應 =
		 * 01    ordID
		 * 02-01 ordRoomId
		 * 03-02 ordMemId
		 * 04-03 ordHotelId
		 * 05-04 ordPrice
		 * 06-05 ordLiveDate
		 * 07    ordDate
		 * 08    ordStatus
		 * 09	 ordRatingContent
		 * 10	 ordRatingStarNo
		 * 11-06 ordQrPic
		 * 12-07 ordMsgNo
		*/	
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(OrdDAO.INSERT_STMT);
			
			pstmt.setString(1, aOrdVO.getOrdRoomId());
			pstmt.setString(2, aOrdVO.getOrdMemId());
			pstmt.setString(3, aOrdVO.getOrdHotelId());
			pstmt.setInt(4, aOrdVO.getOrdPrice());
			pstmt.setDate(5, aOrdVO.getOrdLiveDate());
			pstmt.setBytes(6, aOrdVO.getOrdQrPic());
			pstmt.setString(7, aOrdVO.getOrdMsgNo());
			updateCount = pstmt.executeUpdate();
		}
		catch(SQLException se){
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
			if(con!=null){
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
			con = ds.getConnection();			
			pstmt = con.prepareStatement(OrdDAO.UPDATE);
			
			pstmt.setString(1, aOrdVO.getOrdStatus());
			pstmt.setString(2, aOrdVO.getOrdRatingContent());
			pstmt.setInt(3, aOrdVO.getOrdRatingStarNo());
			pstmt.setString (4, aOrdVO.getOrdId());
			updateCount = pstmt.executeUpdate();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(OrdDAO.DELETE);
			
			pstmt.setString(1,aOrdId);
			updateCount = pstmt.executeUpdate();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(OrdDAO.GET_ONE_STMT);
			
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(OrdDAO.GET_ALL_STMT);
			
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
	
	/* 列出該一般會員的所有訂單 */
	public List<OrdVO> getAllByOrdMemId(String aOrdMemId){
		List<OrdVO> list = new ArrayList<OrdVO>();
		OrdVO ordVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(OrdDAO.GET_ALL_ORDMEMID_STMT);
			
			pstmt.setString(1, aOrdMemId);
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
	
	/* 列出該廠商會員的所有訂單 */
	public List<OrdVO> getAllByOrdHotelId(String aOrdHotelId){
		List<OrdVO> list = new ArrayList<OrdVO>();
		OrdVO ordVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(OrdDAO.GET_ALL_ORDHOTELID_STMT);
			
			pstmt.setString(1, aOrdHotelId);
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
		
}
