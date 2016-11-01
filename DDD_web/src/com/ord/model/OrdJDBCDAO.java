package com.ord.model;

import java.util.*;
import java.sql.*;
import java.io.*;

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
	 * 07    ordDate
	 * 08    ordStatus
	 * 09-06 ordRatingContent
	 * 10-07 ordRatingStarNo
	 * 11-08 ordQrPic
	 * 12-09 ordMsgNo
	*/
	
	/* 日期記得改回Sysdate */
	private static final String INSERT_STMT = 
		"INSERT INTO ord (ordID,ordRoomId,ordMemId,ordHotelId,ordPrice,ordLiveDate,ordDate,ordStatus,ordRatingContent,ordRatingStarNo,ordQrPic,ordMsgNo)"
		+ "VALUES (CONCAT(TO_CHAR(SYSDATE,'YYYYMM'),ord_seq.NEXTVAL), ?, ?, ?, ?, ?, sysdate, '0', ?,?,?,?)";

	private static final String GET_ALL_STMT = 
		"SELECT * FROM ord order by ordID DESC";

	private static final String GET_ONE_STMT = 
		"SELECT * FROM ord where ordId = ?";
	
	private static final String GET_ALL_ORDMEMID_STMT = 
		"SELECT * FROM ord where OrdMemId = ?";	
	
	private static final String GET_ALL_ORDHOTELID_STMT = 
		"SELECT * FROM ord where OrdHotelId = ?";		
	
	private static final String DELETE = 
		"DELETE FROM ord where ordId = ?";
	
	private static final String UPDATE = 
		"UPDATE ord set ordStatus=? where ordId = ?";	
	
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
		 * 09-06 ordRatingContent
		 * 10-07 ordRatingStarNo
		 * 11-08 ordQrPic
		 * 12-09 ordMsgNo
		*/		
		
		try{
			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url,this.userid,this.passwd);
			pstmt = con.prepareStatement(OrdJDBCDAO.INSERT_STMT);
			pstmt.setString(1, aOrdVO.getOrdRoomId());
			pstmt.setString(2, aOrdVO.getOrdMemId());
			pstmt.setString(3, aOrdVO.getOrdHotelId());
			pstmt.setInt(4, aOrdVO.getOrdPrice());
			pstmt.setDate(5, aOrdVO.getOrdLiveDate());
	
			pstmt.setString(6, aOrdVO.getOrdRatingContent());
			pstmt.setInt(7, aOrdVO.getOrdRatingStarNo());
			pstmt.setBytes(8, aOrdVO.getOrdQrPic());
			pstmt.setString(9, aOrdVO.getOrdMsgNo());
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
	
	/* 列出該一般會員的所有訂單 */
	public List<OrdVO> getAllByOrdMemId(String aOrdMemId){
		List<OrdVO> list = new ArrayList<OrdVO>();
		OrdVO ordVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try{
			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url,this.userid,this.passwd);
			pstmt = con.prepareStatement(OrdJDBCDAO.GET_ALL_ORDMEMID_STMT);		
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
	
	/* 列出該廠商會員的所有訂單 */
	public List<OrdVO> getAllByOrdHotelId(String aOrdHotelId){
		List<OrdVO> list = new ArrayList<OrdVO>();
		OrdVO ordVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url,this.userid,this.passwd);
			pstmt = con.prepareStatement(OrdJDBCDAO.GET_ALL_ORDHOTELID_STMT);		
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
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		OrdJDBCDAO dao = new OrdJDBCDAO();
		
		//新增		
		OrdVO ordVO1 = new OrdVO();
				
		//測試圖片		
//		File pic = new File("C:/Users/cuser/Desktop/QRCode.png");
//		if(pic.exists()){
//			System.out.println("Panda Here.");
//		}
//		else{
//			System.out.println("Panda Not Here.");
//		}
//		
//		InputStream fin = new FileInputStream(pic);
//		byte[] byteAry = new byte[fin.available()];
//		fin.read(byteAry);
//		fin.close();
//		
//		ordVO1.setOrdRoomId("7654321");
//		ordVO1.setOrdMemId("87654321");
//		ordVO1.setOrdHotelId("54321");
//		ordVO1.setOrdPrice(888);
//		ordVO1.setOrdLiveDate(new java.sql.Date(new java.util.Date().getTime()));
//		ordVO1.setOrdRatingContent("我愛台灣");
//		ordVO1.setOrdRatingStarNo(5);
//		ordVO1.setOrdQrPic(byteAry);
//		ordVO1.setOrdMsgNo("YMCA");
//		dao.insert(ordVO1);
		
		//修改
//		OrdVO ordVO2 = new OrdVO();
//		ordVO2.setOrdId("2016101025");
//		ordVO2.setOrdStatus("5");
//		int updateCount_update =  dao.update(ordVO2);
//		System.out.println(updateCount_update);
		
		//刪除
//		int updateCount_delete = dao.delete("2016101002");
//		System.out.println(updateCount_delete);
		
		//查詢
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
		
		//列出所有		
//		List<OrdVO> list0 = dao.getAll();
//		for(OrdVO aOrd : list0){
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
		
		//依一般會員ID查詢		
//		List<OrdVO> list1 = dao.getAllByOrdMemId("10000001");
//		for(OrdVO aOrd : list1){
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
		
		//依廠商會員ID查詢		
		List<OrdVO> list2 = dao.getAllByOrdHotelId("10001");
		for(OrdVO aOrd : list2){
			System.out.print(aOrd.getOrdId() +",");
			System.out.print(aOrd.getOrdRoomId()+",");
			System.out.print(aOrd.getOrdMemId()+",");
			System.out.print(aOrd.getOrdHotelId()+",");
			System.out.print(aOrd.getOrdPrice() +",");			
			System.out.print(aOrd.getOrdLiveDate()+",");
			System.out.print(aOrd.getOrdDate()+",");
			System.out.print(aOrd.getOrdStatus()+",");
			System.out.print(aOrd.getOrdRatingContent()+",");
			System.out.print(aOrd.getOrdRatingStarNo() +",");
			System.out.print(aOrd.getOrdMsgNo());
			System.out.println();
		}		
		
	}
	
}
