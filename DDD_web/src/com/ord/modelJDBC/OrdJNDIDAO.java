package com.ord.modelJDBC;

import java.util.*;
import java.sql.*;
import java.io.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class OrdJNDIDAO implements OrdDAO_interface {
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
	 * 08-06 ordStatus
	 * 09-07 ordRatingContent
	 * 10-08 ordRatingStarNo
	 * 11-09 ordQrPic
	 * 12-10 ordMsgNo
	*/

	/* (一般會員)新增一筆訂單 */
	private static final String INSERT_STMT = 
		"INSERT INTO ord (ordID,ordRoomId,ordMemId,ordHotelId,ordPrice,ordLiveDate,ordDate,ordStatus,ordRatingContent,ordRatingStarNo,ordQrPic,ordMsgNo)"
		+ "VALUES (CONCAT(TO_CHAR(SYSDATE,'YYYYMM'),ord_seq.NEXTVAL), ?, ?, ?, ?, ?, sysdate, ?, ?, ?, ?, ?)";

	/* 
	 * = UPDATE 對應 =
	 * 02-01 ordRoomId
	 * 03-02 ordMemId
	 * 04-03 ordHotelId
	 * 05-04 ordPrice
	 * 06-05 ordLiveDate
	 * 07    ordDate
	 * 08-06 ordStatus
	 * 09-07 ordRatingContent
	 * 10-08 ordRatingStarNo
	 * 11-09 ordQrPic
	 * 12-10 ordMsgNo
	 * 01-11 ordID
	*/	
	
	/* (一般會員)新增評論及星星數 & (系統)修改訂單狀態 */
	private static final String UPDATE = 
		"UPDATE ord set ordRoomId=?,ordMemId=?,ordHotelId=?,ordPrice=?,ordLiveDate=?,ordStatus=?,ordRatingContent=?,ordRatingStarNo=?,ordQrPic=?,ordMsgNo=? where ordId = ?";	

	/* (練習用)刪除 */
	private static final String DELETE = 
		"DELETE FROM ord where ordId = ?";

	/* (管理員)依訂單編號查詢 單筆資料要抓圖 */
	private static final String GET_ONE_STMT = 
		"SELECT ordID,ordRoomId,ordMemId,ordHotelId,ordPrice, ordLiveDate, ordDate,ordStatus,ordRatingContent,ordRatingStarNo,ordMsgNo, ordQrPic FROM ord where ordId = ?";
	
	/* (管理員)查詢所有訂單內容 評論及星星數 */
	private static final String GET_ALL_STMT = 
		"SELECT ordID,ordRoomId,ordMemId,ordHotelId,ordPrice, ordLiveDate, ordDate,ordStatus,ordRatingContent,ordRatingStarNo,ordMsgNo FROM ord order by ordID DESC";

	/* (一般會員)列出該一般會員的所有訂單 QRCode 驗證碼 */
	private static final String GET_ALL_ORDMEMID_STMT = 
		"SELECT ordID,ordRoomId,ordMemId,ordHotelId,ordPrice, ordLiveDate, ordDate,ordStatus,ordRatingContent,ordRatingStarNo,ordMsgNo FROM ord where OrdMemId = ? order by ordID DESC";	
	
	/* (廠商會員)列出該廠商會員的所有訂單 */
	private static final String GET_ALL_ORDHOTELID_STMT = 
		"SELECT ordID,ordRoomId,ordMemId,ordHotelId,ordPrice, ordLiveDate, ordDate,ordStatus,ordRatingContent,ordRatingStarNo,ordMsgNo FROM ord where OrdHotelId = ? order by ordID DESC";		
		
	
	
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
			pstmt = con.prepareStatement(OrdJNDIDAO.INSERT_STMT);
			
			pstmt.setString(1, aOrdVO.getOrdRoomId());
			pstmt.setString(2, aOrdVO.getOrdMemId());
			pstmt.setString(3, aOrdVO.getOrdHotelId());
			pstmt.setInt(4, aOrdVO.getOrdPrice());
			pstmt.setTimestamp(5, aOrdVO.getOrdLiveDate());	
			pstmt.setString(6, aOrdVO.getOrdStatus());
			pstmt.setString(7, aOrdVO.getOrdRatingContent());
			pstmt.setInt(8, aOrdVO.getOrdRatingStarNo());
			pstmt.setBytes(9, aOrdVO.getOrdQrPic());
			pstmt.setString(10, aOrdVO.getOrdMsgNo());
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
			pstmt = con.prepareStatement(OrdJNDIDAO.UPDATE);
			
			pstmt.setString(1, aOrdVO.getOrdRoomId());
			pstmt.setString(2, aOrdVO.getOrdMemId());
			pstmt.setString(3, aOrdVO.getOrdHotelId());
			pstmt.setInt(4, aOrdVO.getOrdPrice());
			pstmt.setTimestamp(5, aOrdVO.getOrdLiveDate());	
			pstmt.setString(6, aOrdVO.getOrdStatus());
			pstmt.setString(7, aOrdVO.getOrdRatingContent());
			pstmt.setInt(8, aOrdVO.getOrdRatingStarNo());
			pstmt.setBytes(9, aOrdVO.getOrdQrPic());
			pstmt.setString(10, aOrdVO.getOrdMsgNo());
			pstmt.setString(11, aOrdVO.getOrdId());
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
			pstmt = con.prepareStatement(OrdJNDIDAO.DELETE);
			
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
			pstmt = con.prepareStatement(OrdJNDIDAO.GET_ONE_STMT);
			
			pstmt.setString(1, aOrdId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				ordVO = new OrdVO();
				ordVO.setOrdId(rs.getString("ordID"));
				ordVO.setOrdRoomId(rs.getString("ordRoomId"));
				ordVO.setOrdMemId(rs.getString("ordMemId"));
				ordVO.setOrdHotelId(rs.getString("ordHotelId"));
				ordVO.setOrdPrice(rs.getInt("ordPrice"));
				ordVO.setOrdLiveDate(rs.getTimestamp("ordLiveDate"));
				ordVO.setOrdDate(rs.getTimestamp("ordDate"));
				ordVO.setOrdStatus(rs.getString("ordStatus"));
				ordVO.setOrdRatingContent(rs.getString("ordRatingContent"));
				ordVO.setOrdRatingStarNo(rs.getInt("ordRatingStarNo"));
				ordVO.setOrdMsgNo(rs.getString("ordMsgNo"));
				ordVO.setOrdQrPic(rs.getBytes("ordQrPic")); //需要要圖片
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
			pstmt = con.prepareStatement(OrdJNDIDAO.GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				ordVO = new OrdVO();
				ordVO.setOrdId(rs.getString("ordID"));
				ordVO.setOrdRoomId(rs.getString("ordRoomId"));
				ordVO.setOrdMemId(rs.getString("ordMemId"));
				ordVO.setOrdHotelId(rs.getString("ordHotelId"));
				ordVO.setOrdPrice(rs.getInt("ordPrice"));
				ordVO.setOrdLiveDate(rs.getTimestamp("ordLiveDate"));
				ordVO.setOrdDate(rs.getTimestamp("ordDate"));
				ordVO.setOrdStatus(rs.getString("ordStatus"));
				ordVO.setOrdRatingContent(rs.getString("ordRatingContent"));
				ordVO.setOrdRatingStarNo(rs.getInt("ordRatingStarNo"));
				ordVO.setOrdMsgNo(rs.getString("ordMsgNo"));
				//ordVO.setOrdQrPic(rs.getBytes("ordQrPic"));
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
			pstmt = con.prepareStatement(OrdJNDIDAO.GET_ALL_ORDMEMID_STMT);
			
			pstmt.setString(1, aOrdMemId);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				ordVO = new OrdVO();
				ordVO.setOrdId(rs.getString("ordID"));
				ordVO.setOrdRoomId(rs.getString("ordRoomId"));
				ordVO.setOrdMemId(rs.getString("ordMemId"));
				ordVO.setOrdHotelId(rs.getString("ordHotelId"));
				ordVO.setOrdPrice(rs.getInt("ordPrice"));
				ordVO.setOrdLiveDate(rs.getTimestamp("ordLiveDate"));
				ordVO.setOrdDate(rs.getTimestamp("ordDate"));
				ordVO.setOrdStatus(rs.getString("ordStatus"));
				ordVO.setOrdRatingContent(rs.getString("ordRatingContent"));
				ordVO.setOrdRatingStarNo(rs.getInt("ordRatingStarNo"));
				ordVO.setOrdMsgNo(rs.getString("ordMsgNo"));
				//ordVO.setOrdQrPic(rs.getBytes("ordQrPic"));
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
			pstmt = con.prepareStatement(OrdJNDIDAO.GET_ALL_ORDHOTELID_STMT);
			
			pstmt.setString(1, aOrdHotelId);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				ordVO = new OrdVO();
				ordVO.setOrdId(rs.getString("ordID"));
				ordVO.setOrdRoomId(rs.getString("ordRoomId"));
				ordVO.setOrdMemId(rs.getString("ordMemId"));
				ordVO.setOrdHotelId(rs.getString("ordHotelId"));
				ordVO.setOrdPrice(rs.getInt("ordPrice"));
				ordVO.setOrdLiveDate(rs.getTimestamp("ordLiveDate"));
				ordVO.setOrdDate(rs.getTimestamp("ordDate"));
				ordVO.setOrdStatus(rs.getString("ordStatus"));
				ordVO.setOrdRatingContent(rs.getString("ordRatingContent"));
				ordVO.setOrdRatingStarNo(rs.getInt("ordRatingStarNo"));
				ordVO.setOrdMsgNo(rs.getString("ordMsgNo"));
				//ordVO.setOrdQrPic(rs.getBytes("ordQrPic"));
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
