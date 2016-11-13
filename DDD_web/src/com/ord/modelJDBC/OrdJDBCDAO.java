package com.ord.modelJDBC;

import java.util.*;
import java.sql.*;
import java.io.*;
import java.text.SimpleDateFormat;

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
		 * 08-06 ordStatus
		 * 09-07 ordRatingContent
		 * 10-08 ordRatingStarNo
		 * 11-09 ordQrPic
		 * 12-10 ordMsgNo
		*/
		
		try{
			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url,this.userid,this.passwd);
			pstmt = con.prepareStatement(OrdJDBCDAO.INSERT_STMT);
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
		catch(ClassNotFoundException e){
			throw new RuntimeException("Could not load database driver. "+ e.getMessage());
		}
		catch(SQLException se){
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}
				catch(SQLException se){
					se.printStackTrace(System.err);
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
		
		try{
						
			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url,this.userid,this.passwd);
			pstmt = con.prepareStatement(OrdJDBCDAO.UPDATE);
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

			/* 
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

			/* 
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

			/* 
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

			/* 
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
		OrdJDBCDAO dao = new OrdJDBCDAO();
						
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
		
		/* 給它指定日期 */
//		java.util.Date aliveDate= new java.util.Date();
	    
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
		
		//新增		
		//OrdVO ordVO1 = new OrdVO();	    
//		ordVO1.setOrdRoomId("1000001");
//		ordVO1.setOrdMemId("10000002");
//		ordVO1.setOrdHotelId("10001");
//		ordVO1.setOrdPrice(777);
//		ordVO1.setOrdLiveDate(new Timestamp(aliveDate.getTime()));
//		
//		ordVO1.setOrdStatus("0");
//		ordVO1.setOrdRatingContent("阿華吃芭樂");
//		ordVO1.setOrdRatingStarNo(4);
//		ordVO1.setOrdQrPic(byteAry);
//		ordVO1.setOrdMsgNo("ZZZZ");
//		dao.insert(ordVO1);
		
		//修改
//		OrdVO ordVO2 = new OrdVO();
//		ordVO2.setOrdId("2016111003");
//		ordVO2.setOrdRoomId("1000001");
//		ordVO2.setOrdMemId("10000001");
//		ordVO2.setOrdHotelId("10001");
//		ordVO2.setOrdPrice(8686);
//		ordVO2.setOrdLiveDate(new Timestamp(aliveDate.getTime()));
//		
//		ordVO2.setOrdStatus("0");
//		ordVO2.setOrdRatingContent("柚子吃便當");
//		ordVO2.setOrdRatingStarNo(4);
//		ordVO2.setOrdQrPic(byteAry);
//		ordVO2.setOrdMsgNo("ABAB");
//		int updateCount_update = dao.update(ordVO2);
//		System.out.println(updateCount_update);
		
		//刪除
//		int updateCount_delete = dao.delete("2016111032");
//		System.out.println(updateCount_delete);
		
		//查詢
//		OrdVO ordVO3 = dao.findByPrimaryKey("2016111001");	
//		System.out.print(ordVO3.getOrdId() +",");
//		System.out.print(ordVO3.getOrdRoomId()+",");
//		System.out.print(ordVO3.getOrdMemId()+",");
//		System.out.print(ordVO3.getOrdHotelId()+",");
//		System.out.print(ordVO3.getOrdPrice() +",");			
//		//String S = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(myTimestamp);	
//		System.out.print(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(ordVO3.getOrdLiveDate()) +",");
//		System.out.print(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(ordVO3.getOrdDate()) +",");
//		System.out.print(ordVO3.getOrdStatus()+",");
//		System.out.print(ordVO3.getOrdRatingContent()+",");
//		System.out.print(ordVO3.getOrdRatingStarNo() +",");
//		System.out.print(ordVO3.getOrdMsgNo());
//		System.out.println();	
		
		
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
		
		//列出所有		
//		List<OrdVO> list0 = dao.getAll();
//		for(OrdVO aOrd : list0){
//			System.out.print(aOrd.getOrdId() +",");
//			System.out.print(aOrd.getOrdRoomId()+",");
//			System.out.print(aOrd.getOrdMemId()+",");
//			System.out.print(aOrd.getOrdHotelId()+",");
//			System.out.print(aOrd.getOrdPrice() +",");			
//			//String S = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(myTimestamp);	
//			System.out.print(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(aOrd.getOrdLiveDate()) +",");
//			System.out.print(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(aOrd.getOrdDate()) +",");
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
//			//String S = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(myTimestamp);	
//			System.out.print(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(aOrd.getOrdLiveDate()) +",");
//			System.out.print(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(aOrd.getOrdDate()) +",");
//			System.out.print(aOrd.getOrdStatus()+",");
//			System.out.print(aOrd.getOrdRatingContent()+",");
//			System.out.print(aOrd.getOrdRatingStarNo() +",");
//			System.out.print(aOrd.getOrdMsgNo());
//			System.out.println();
//		}
		
		//依廠商會員ID查詢		
//		List<OrdVO> list2 = dao.getAllByOrdHotelId("10001");
//		for(OrdVO aOrd : list2){
//		System.out.print(aOrd.getOrdId() +",");
//		System.out.print(aOrd.getOrdRoomId()+",");
//		System.out.print(aOrd.getOrdMemId()+",");
//		System.out.print(aOrd.getOrdHotelId()+",");
//		System.out.print(aOrd.getOrdPrice() +",");			
//		//String S = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(myTimestamp);	
//		System.out.print(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(aOrd.getOrdLiveDate()) +",");
//		System.out.print(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(aOrd.getOrdDate()) +",");
//		System.out.print(aOrd.getOrdStatus()+",");
//		System.out.print(aOrd.getOrdRatingContent()+",");
//		System.out.print(aOrd.getOrdRatingStarNo() +",");
//		System.out.print(aOrd.getOrdMsgNo());
//		System.out.println();
//		}		
		
	}
	
}
