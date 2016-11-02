package com.memrep.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ord.model.OrdJDBCDAO;
import com.ord.model.OrdVO;
import com.memrep.model.MemRepJNDIDAO;


public class MemRepJNDIDAO implements MemRepDAO_interface {
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
	// memrepId memrepOrdId memrepMemId memrepHotelId memrepEmpId memrepContent memrepStatus memrepDate memrepReviewDate 
	private static final String INSERT = "INSERT INTO memrep (memrepId, memrepOrdId, memrepMemId, memrepHotelId, memrepEmpId, memrepContent, memrepStatus, memrepDate, memrepReviewDate) "
									   + "VALUES ( memrep_seq.NEXTVAL,?,?,?,null,?,'0',sysdate,null)";
	private static final String UPDATE = "UPDATE memrep SET memrepEmpId=?, memrepStatus=?, memrepReviewDate=? where memrepId = ?";
	private static final String DELETE = "DELETE FROM memrep WHERE memrepId = ?";
	
	private static final String GET_ALL = "SELECT memrepId, memrepOrdId, memrepMemId, memrepHotelId, memrepEmpId, memrepContent, memrepStatus, memrepDate, memrepReviewDate FROM memrep ORDER BY memrepId";
	private static final String GET_ALL_MEMREPSTATUS = "SELECT memrepId, memrepOrdId, memrepMemId, memrepHotelId, memrepEmpId, memrepContent, memrepStatus, memrepDate, memrepReviewDate FROM memrep WHERE memrepStatus = ? ORDER BY memrepId";
	private static final String GET_ONE = "SELECT memrepId, memrepOrdId, memrepMemId, memrepHotelId, memrepEmpId, memrepContent, memrepStatus, memrepDate, memrepReviewDate FROM memrep WHERE memrepId = ?";
	


	@Override
	public void insert(MemRepVO aMemrepVO) {
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(MemRepJNDIDAO.INSERT);) {
			String memRepOrdId = aMemrepVO.getMemRepOrdId();
			OrdJDBCDAO dao_ord = new OrdJDBCDAO();
			OrdVO myOrdVO = dao_ord.findByPrimaryKey(memRepOrdId);
			String memRepMemId = myOrdVO.getOrdMemId();
			String memRepHotelId = myOrdVO.getOrdHotelId();
	
			pstmt.setString(1, memRepOrdId); // 此三者有連帶關係
			pstmt.setString(2, memRepMemId); // 此三者有連帶關係
			pstmt.setString(3, memRepHotelId); //  此三者有連帶關係
			pstmt.setString(4, aMemrepVO.getMemRepContent());
			
			int result = pstmt.executeUpdate();
			System.out.println("number of rows inserted: " + result);
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
		}
	}

	@Override
	public void update(MemRepVO aMemrepVO) {
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(MemRepJNDIDAO.UPDATE);) {

			pstmt.setString(1, aMemrepVO.getMemRepEmpId());
			pstmt.setString(2, aMemrepVO.getMemRepStatus());
			pstmt.setDate(3, aMemrepVO.getMemRepReviewDate());
			pstmt.setString(4, aMemrepVO.getMemRepId());
			
			int result = pstmt.executeUpdate();
			System.out.println("number of rows updated: " + result);
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
		}
	}

	@Override
	public void delete(String aMemrepId) {
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(MemRepJNDIDAO.DELETE);){
			pstmt.setString(1, aMemrepId);
			int result = pstmt.executeUpdate();
			System.out.println("number of rows deleted: " + result);
			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
		}
	}

	@Override
	public List<MemRepVO> getAll() {
		List<MemRepVO> memrepVOList = new ArrayList<>();
		ResultSet rs = null;
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(MemRepJNDIDAO.GET_ALL);) {
				rs = pstmt.executeQuery();
				while(rs.next()){
					MemRepVO memrepVO = new MemRepVO();
					memrepVO.setMemRepId(rs.getString("memRepId")); 
					memrepVO.setMemRepOrdId(rs.getString("memRepOrdId"));
					memrepVO.setMemRepMemId(rs.getString("memRepMemId")); 
					memrepVO.setMemRepHotelId(rs.getString("memRepHotelId")); 
					memrepVO.setMemRepEmpId(rs.getString("memRepEmpId"));
					memrepVO.setMemRepContent(rs.getString("memRepContent"));
					memrepVO.setMemRepStatus(rs.getString("memRepStatus"));
					memrepVO.setMemRepDate(rs.getDate("memRepDate")); 
					memrepVO.setMemRepReviewDate(rs.getDate("memRepReviewDate"));
					
					memrepVOList.add(memrepVO);
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
		return memrepVOList;
	}

	@Override
	public MemRepVO findByPrimaryKey(String aMemrepId) {
		MemRepVO memrepVO = null;
		ResultSet rs = null;
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(MemRepJNDIDAO.GET_ONE);) {
				pstmt.setString(1, aMemrepId);			
				rs = pstmt.executeQuery();
				if (rs.next()){
					memrepVO = new MemRepVO();
					memrepVO.setMemRepId(rs.getString("memRepId")); 
					memrepVO.setMemRepOrdId(rs.getString("memRepOrdId"));
					memrepVO.setMemRepMemId(rs.getString("memRepMemId")); 
					memrepVO.setMemRepHotelId(rs.getString("memRepHotelId")); 
					memrepVO.setMemRepEmpId(rs.getString("memRepEmpId"));
					memrepVO.setMemRepContent(rs.getString("memRepContent"));
					memrepVO.setMemRepStatus(rs.getString("memRepStatus"));
					memrepVO.setMemRepDate(rs.getDate("memRepDate")); 
					memrepVO.setMemRepReviewDate(rs.getDate("memRepReviewDate"));
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
		return memrepVO;
	}
	
	@Override
	public List<MemRepVO> getAllByMemRepStatus(String aMemRepStatus) {
		List<MemRepVO> memrepVOList = new ArrayList<>();
		ResultSet rs = null;
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(MemRepJNDIDAO.GET_ALL_MEMREPSTATUS);) {
				pstmt.setString(1, aMemRepStatus);
				rs = pstmt.executeQuery();
				while(rs.next()){
					MemRepVO memrepVO = new MemRepVO();
					memrepVO.setMemRepId(rs.getString("memRepId")); 
					memrepVO.setMemRepOrdId(rs.getString("memRepOrdId"));
					memrepVO.setMemRepMemId(rs.getString("memRepMemId")); 
					memrepVO.setMemRepHotelId(rs.getString("memRepHotelId")); 
					memrepVO.setMemRepEmpId(rs.getString("memRepEmpId"));
					memrepVO.setMemRepContent(rs.getString("memRepContent"));
					memrepVO.setMemRepStatus(rs.getString("memRepStatus"));
					memrepVO.setMemRepDate(rs.getDate("memRepDate")); 
					memrepVO.setMemRepReviewDate(rs.getDate("memRepReviewDate"));
					
					memrepVOList.add(memrepVO);
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
		return memrepVOList;	
	}
	@Override	
	public List<MemRepVO> getAll(Map<String, String[]> aMap) {
		return getAll(aMap, MemRepDAO_interface.tableName);
	}
	
	private List<MemRepVO> getAll(Map<String, String[]> aMap, String aTableName) {
		List<MemRepVO> list = new ArrayList<MemRepVO>();
		MemRepVO empVO = null;
	
		ResultSet rs = null;
		// 重點在此行 - testing.CompositeQuery_anyTable_JNDI.getQuerySQL(map, tableName);
		String finalSQL = util.CompositeQuery_anyTable_JNDI.getQuerySQL(aMap, aTableName);
		System.out.println("●●finalSQL(by DAO) = "+finalSQL);
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(finalSQL);) {
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				MemRepVO memrepVO = new MemRepVO();
				memrepVO.setMemRepId(rs.getString("memRepId")); 
				memrepVO.setMemRepOrdId(rs.getString("memRepOrdId"));
				memrepVO.setMemRepMemId(rs.getString("memRepMemId")); 
				memrepVO.setMemRepHotelId(rs.getString("memRepHotelId")); 
				memrepVO.setMemRepEmpId(rs.getString("memRepEmpId"));
				memrepVO.setMemRepContent(rs.getString("memRepContent"));
				memrepVO.setMemRepStatus(rs.getString("memRepStatus"));
				memrepVO.setMemRepDate(rs.getDate("memRepDate")); 
				memrepVO.setMemRepReviewDate(rs.getDate("memRepReviewDate"));
				
				list.add(memrepVO); // Store the row in the List
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
		
	
	public static void main(String[] args){
		MemRepDAO_interface dao = new MemRepJNDIDAO();
		MemRepVO memrepVO = new MemRepVO();
//		
//		// 新增 insert(總共有9個欄位):
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
//		java.util.Date d = new java.util.Date(); // convert Date to String
//		String yyyyMM = sdf.format(d);
//		String ordid = yyyyMM + "1003";
//
//		//memrepVO.setMemRepId(); //使用 memrep_seq
//		memrepVO.setMemRepOrdId(ordid);
//		//memrepVO.setMemRepMemId(myOrdVO.getOrdMemId()); // 程式內處理
//		//memrepVO.setMemRepHotelId(myOrdVO.getOrdHotelId()); // 程式內處理
//		// memrepVO.setMemRepEmpId(null); // 初始值都是 null
//		memrepVO.setMemRepContent("冷氣不冷，還在半夜被偷偷關掉");
//		//memrepVO.setMemRepStatus("0"); // 初始值都是"0"
//		//memrepVO.setMemRepDate(); //使用sysdate
//		//memrepVO.setMemRepReviewDate(null); // 初始值都是 null
//		dao.insert(memrepVO);
//		
//		// 修改 update(共有4個欄位可修改):
////		String memrepId = "1000000005";
////		String memrepEmpId = "10002";
////		GregorianCalendar myGCDate = new GregorianCalendar();
////		myGCDate.setTime(new java.util.Date());
////		java.sql.Date reviewDate = new java.sql.Date(myGCDate.getTime().getTime());
////		memrepVO = dao.findByPrimaryKey(memrepId); // 從DB拿舊VO，放到網頁上呈現
////		memrepVO.setMemRepStatus("2"); // 在網頁上修改此VO資料
////		memrepVO.setMemRepEmpId(memrepEmpId); // 在網頁上修改此VO資料
////		memrepVO.setMemRepReviewDate(reviewDate); // 在網頁上修改此VO資料
////		dao.update(memrepVO); // 再回放到DB中
//		
//		// 刪除 delete
////		String memrepId = "1000000005";
////		dao.delete(memrepId);
//		
//		// 查詢全部:
//		List<MemRepVO> memrepVOList = dao.getAll();
//		printData(memrepVOList);
//		
//		// 根據memrepStatus查詢全部
////		String memrepStatusId = "2";  //0.未審核 1.已審核未通過 2.已審核已通過
////		List<MemRepVO> memrepVOList = dao.getAllByMemRepStatus(memrepStatusId);
////		printData(memrepVOList);
//		
//		
//		// 特過PK查看一筆資料 :
////		String memrepId = "1000000001";
////		memrepVO = dao.findByPrimaryKey(memrepId);
////		printData(memrepVO);	
//				
	}// end main
	
	
	//以下不重要，只是用來驗證方法正不正確
	private static void printData(List<MemRepVO> memrepVOList){
		if (!memrepVOList.isEmpty() && memrepVOList != null){   
			System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s","MemRepId", "MemRepOrdId","memrepMemId", "MemRepHotelId", "MemRepEmpId", "MemRepContent", "MemRepStatus", "MemRepDate", "RepReviewDate");	
			System.out.printf("%n%90s","------------------------------------------------------------------------------------------------------------------------------------------------");	
			
			for (MemRepVO myMemrepVO: memrepVOList){
				System.out.printf("%n%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s",
								  myMemrepVO.getMemRepId(), 
								  myMemrepVO.getMemRepOrdId(),
								  myMemrepVO.getMemRepMemId(), 
								  myMemrepVO.getMemRepHotelId(), 
								  myMemrepVO.getMemRepEmpId(), 
								  myMemrepVO.getMemRepContent().substring(0,5)+"...", // 這段有風險，不過測試用別擔心兒
								  myMemrepVO.getMemRepStatus(), 
								  myMemrepVO.getMemRepDate(), 
								  myMemrepVO.getMemRepReviewDate());	
			}			
		}else{
			System.out.println("no data found.");
		}		
	}
	
	private static void printData(MemRepVO memrepVO){
		if (memrepVO != null){
			System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s","MemRepId", "MemRepOrdId", "MemRepMemId", "MemRepHotelId", "MemRepEmpId", "MemRepContent", "MemRepStatus", "MemRepDate", "RepReviewDate");	
			System.out.printf("%n%90s","------------------------------------------------------------------------------------------------------------------------------------------------");	
			System.out.printf("%n%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s",
				  memrepVO.getMemRepId(), 
				  memrepVO.getMemRepOrdId(), 
				  memrepVO.getMemRepMemId(),
				  memrepVO.getMemRepHotelId(), 
				  memrepVO.getMemRepEmpId(), 
				  memrepVO.getMemRepContent().substring(0,5)+"...", // 這段有風險，不過測試用別擔心兒
				  memrepVO.getMemRepStatus(), 
				  memrepVO.getMemRepDate(), 
				  memrepVO.getMemRepReviewDate());				
		}			
	}



}
