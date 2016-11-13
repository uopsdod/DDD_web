package com.memrep.modelJDBC;

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

import com.ord.model.OrdJDBCDAO;
import com.ord.model.OrdVO;

public class MemRepJDBCDAO implements MemRepDAO_interface {
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String account = "scott";
	private static String password = "tiger";
	// ��甈���(銴ˊ�):
	// memrepId memrepOrdId memrepMemId memrepHotelId memrepEmpId memrepContent memrepStatus memrepDate memrepReviewDate 
	private static final String INSERT = "INSERT INTO memrep (memrepId, memrepOrdId, memrepMemId, memrepHotelId, memrepEmpId, memrepContent, memrepStatus, memrepDate, memrepReviewDate) "
									   + "VALUES ( memrep_seq.NEXTVAL,?,?,?,null,?,'0',sysdate,null)";
	private static final String UPDATE = "UPDATE memrep SET memrepEmpId=?, memrepStatus=?, memrepReviewDate=? where memrepId = ?";
	private static final String DELETE = "DELETE FROM memrep WHERE memrepId = ?";
	
	private static final String GET_ALL = "SELECT memrepId, memrepOrdId, memrepMemId, memrepHotelId, memrepEmpId, memrepContent, memrepStatus, memrepDate, memrepReviewDate FROM memrep ORDER BY memrepId";
	private static final String GET_ALL_MEMREPSTATUS = "SELECT memrepId, memrepOrdId, memrepMemId, memrepHotelId, memrepEmpId, memrepContent, memrepStatus, memrepDate, memrepReviewDate FROM memrep WHERE memrepStatus = ? ORDER BY memrepId";
	private static final String GET_ONE = "SELECT memrepId, memrepOrdId, memrepMemId, memrepHotelId, memrepEmpId, memrepContent, memrepStatus, memrepDate, memrepReviewDate FROM memrep WHERE memrepId = ?";
	
	
	static{
		try {
			Class.forName(MemRepJDBCDAO.driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(MemRepVO aMemrepVO) {
		try(Connection con = DriverManager.getConnection(MemRepJDBCDAO.url,MemRepJDBCDAO.account,MemRepJDBCDAO.password);
			PreparedStatement pstmt = con.prepareStatement(MemRepJDBCDAO.INSERT);) {
			String memRepOrdId = aMemrepVO.getMemRepOrdId();
			OrdJDBCDAO dao_ord = new OrdJDBCDAO();
			OrdVO myOrdVO = dao_ord.findByPrimaryKey(memRepOrdId);
			String memRepMemId = myOrdVO.getOrdMemId();
			String memRepHotelId = myOrdVO.getOrdHotelId();
	
			pstmt.setString(1, memRepOrdId); // 甇支�����撣園���
			pstmt.setString(2, memRepMemId); // 甇支�����撣園���
			pstmt.setString(3, memRepHotelId); //  甇支�����撣園���
			pstmt.setString(4, aMemrepVO.getMemRepContent());
			
			int result = pstmt.executeUpdate();
			System.out.println("number of rows inserted: " + result);
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
		}
	}

	@Override
	public void update(MemRepVO aMemrepVO) {
		try(Connection con = DriverManager.getConnection(MemRepJDBCDAO.url,MemRepJDBCDAO.account,MemRepJDBCDAO.password);
			PreparedStatement pstmt = con.prepareStatement(MemRepJDBCDAO.UPDATE);) {

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
		try(Connection con = DriverManager.getConnection(MemRepJDBCDAO.url,MemRepJDBCDAO.account,MemRepJDBCDAO.password);
			PreparedStatement pstmt = con.prepareStatement(MemRepJDBCDAO.DELETE);){
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
		try(Connection con = DriverManager.getConnection(MemRepJDBCDAO.url,MemRepJDBCDAO.account,MemRepJDBCDAO.password);
				PreparedStatement pstmt = con.prepareStatement(MemRepJDBCDAO.GET_ALL);) {
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
		try(Connection con = DriverManager.getConnection(MemRepJDBCDAO.url,MemRepJDBCDAO.account,MemRepJDBCDAO.password);
			PreparedStatement pstmt = con.prepareStatement(MemRepJDBCDAO.GET_ONE);) {
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
		try(Connection con = DriverManager.getConnection(MemRepJDBCDAO.url,MemRepJDBCDAO.account,MemRepJDBCDAO.password);
				PreparedStatement pstmt = con.prepareStatement(MemRepJDBCDAO.GET_ALL_MEMREPSTATUS);) {
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
	public List<MemRepVO> getAll(Map<String, String[]> map) {
		return getAll(map, MemRepDAO_interface.tableName);
	}
	
	private List<MemRepVO> getAll(Map<String, String[]> map, String tableName) {
		List<MemRepVO> list = new ArrayList<MemRepVO>();
		MemRepVO empVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = DriverManager.getConnection(MemRepJDBCDAO.url,MemRepJDBCDAO.account,MemRepJDBCDAO.password);
			String finalSQL = testing.CompositeQuery_anyTable_JNDI.getQuerySQL(map, tableName);
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
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
		
	
	public static void main(String[] args){
		MemRepDAO_interface dao = new MemRepJDBCDAO();
		MemRepVO memrepVO = new MemRepVO();
		
		// �憓� insert(蝮賢���9����):
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
//		java.util.Date d = new java.util.Date(); // convert Date to String
//		String yyyyMM = sdf.format(d);
//		String ordid = yyyyMM + "1003";
//
//		//memrepVO.setMemRepId(); //雿輻 memrep_seq
//		memrepVO.setMemRepOrdId(ordid);
//		//memrepVO.setMemRepMemId(myOrdVO.getOrdMemId()); // 蝔�����
//		//memrepVO.setMemRepHotelId(myOrdVO.getOrdHotelId()); // 蝔�����
//		// memrepVO.setMemRepEmpId(null); // ����潮� null
//		memrepVO.setMemRepContent("�瘞�銝嚗����◤������");
//		//memrepVO.setMemRepStatus("0"); // ����潮�"0"
//		//memrepVO.setMemRepDate(); //雿輻sysdate
//		//memrepVO.setMemRepReviewDate(null); // ����潮� null
//		dao.insert(memrepVO);
		
		// 靽格 update(����4���靽格):
//		String memrepId = "1000000005";
//		String memrepEmpId = "10002";
//		GregorianCalendar myGCDate = new GregorianCalendar();
//		myGCDate.setTime(new java.util.Date());
//		java.sql.Date reviewDate = new java.sql.Date(myGCDate.getTime().getTime());
//		memrepVO = dao.findByPrimaryKey(memrepId); // 敺B���O嚗�蝬脤���
//		memrepVO.setMemRepStatus("2"); // �蝬脤��耨�甇丈O鞈��
//		memrepVO.setMemRepEmpId(memrepEmpId); // �蝬脤��耨�甇丈O鞈��
//		memrepVO.setMemRepReviewDate(reviewDate); // �蝬脤��耨�甇丈O鞈��
//		dao.update(memrepVO); // ����DB銝�
		
		// �� delete
//		String memrepId = "1000000005";
//		dao.delete(memrepId);
		
		// �閰Ｗ�:
		List<MemRepVO> memrepVOList = dao.getAll();
		printData(memrepVOList);
		
		// ���emrepStatus�閰Ｗ�
//		String memrepStatusId = "2";  //0.�撖拇 1.撌脣祟����� 2.撌脣祟�撌脤���
//		List<MemRepVO> memrepVOList = dao.getAllByMemRepStatus(memrepStatusId);
//		printData(memrepVOList);
		
		
		// ���K����蝑��� :
//		String memrepId = "1000000001";
//		memrepVO = dao.findByPrimaryKey(memrepId);
//		printData(memrepVO);	
				
	}// end main
	
	
	//隞乩�������靘��瘜迤銝迤蝣�
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
								  myMemrepVO.getMemRepContent().substring(0,5)+"...", // �挾��◢�嚗��葫閰衣������
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
				  memrepVO.getMemRepContent().substring(0,5)+"...", // �挾��◢�嚗��葫閰衣������
				  memrepVO.getMemRepStatus(), 
				  memrepVO.getMemRepDate(), 
				  memrepVO.getMemRepReviewDate());				
		}			
	}

}
