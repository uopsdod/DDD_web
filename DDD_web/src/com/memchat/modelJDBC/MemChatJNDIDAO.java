//package com.memchat.modelJDBC;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
//import com.memchat.model.MemChatVO;
//import com.memchat.model.MemChatDAO_interface;
//
//
//public class MemChatJNDIDAO implements MemChatDAO_interface {
//	private static DataSource ds = null;
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
//	// 全部欄位名(複製用):
//	// memChatMemId memChatChatId memChatDate memChatContent memChatPic
//	private static final String INSERT = "INSERT INTO memChat (memChatChatId,memChatMemId, memChatDate, memChatContent, memChatPic, memChatStatus, memChatToMemId) VALUES (?,?,?,?,?,?,?)";
//	private static final String UPDATE = "";
//	private static final String DELETE = "DELETE FROM memChat WHERE memChatChatId= ? AND memChatMemId = ? AND TO_CHAR(memChatDate, 'YYYYMMDD HH24:MI:SSxFF3') = TO_CHAR(?, 'YYYYMMDD HH24:MI:SSxFF3')";
//	
//	private static final String GET_ALL = "SELECT memChatChatId, memChatMemId, memChatDate, memChatContent, memChatPic, memChatStatus, memChatToMemId FROM memChat ORDER BY memChatChatId";
//	private static final String GET_ONE = "SELECT memChatChatId, memChatMemId, memChatDate, memChatContent, memChatPic, memChatStatus, memChatToMemId FROM memChat WHERE memChatChatId= ? AND memChatMemId = ? AND TO_CHAR(memChatDate, 'YYYYMMDD HH24:MI:SSxFF3') = TO_CHAR(?, 'YYYYMMDD HH24:MI:SSxFF3')";
//	private static final String GET_ALL_MEMCHATCHATID = "SELECT memChatChatId, memChatMemId, memChatDate, memChatContent, memChatPic, memChatStatus, memChatToMemId FROM memChat WHERE memChatChatId= ? ORDER BY memChatChatId";
//	
//	private static final String GET_ALL_MSGBTWNTWOMEMS = "SELECT * FROM memchat WHERE memChatMemId IN (?,?) AND memChatToMemId IN (?,?) ORDER BY memChatDate";
//	private static final String GET_CHAT_IDBTWNTWOMEMS = "SELECT distinct memChatChatId FROM memchat WHERE memChatMemId IN (?,?) AND memChatToMemId IN (?,?)";
//	private static final String GET_NEWESTMSG_CHATID = "SELECT * FROM memChat JOIN (SELECT memChatChatId as chatId_b, MAX(memChatDate) as date_b FROM memChat GROUP BY memChatChatId) ON memChatChatId = chatId_b AND memChatDate = date_b WHERE memChatMemId = ? OR memChatToMemId = ? ORDER BY memChatDate DESC";
//	
//	@Override
//	public void insert(MemChatVO aMemChatVO) {
//		try(Connection con = ds.getConnection();
//			PreparedStatement pstmt = con.prepareStatement(MemChatJNDIDAO.INSERT);) {
//			
//			pstmt.setString(1, aMemChatVO.getMemChatChatId());
//			pstmt.setString(2, aMemChatVO.getMemChatMemId());
//			pstmt.setTimestamp(3, aMemChatVO.getMemChatDate());
//			pstmt.setString(4, aMemChatVO.getMemChatContent());
//			pstmt.setBytes(5, aMemChatVO.getMemChatPic());
//			pstmt.setString(6, aMemChatVO.getMemChatStatus());
//			pstmt.setString(7, aMemChatVO.getMemChatToMemId());
//			
//			int result = pstmt.executeUpdate();
//			System.out.println("number of rows inserted: " + result);	
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "+ se.getMessage());
//		}		
//	}
//
//	@Override
//	public void update(MemChatVO aMemChatVO) {
//		// 似乎不需要有修改這個方法，找不到欄位適合被修改的
//		
//	}
//
//	@Override
//	public void delete(String aMemChatChatId, String aMemChatMemId, Timestamp aDate) { 
//		try(Connection con = ds.getConnection();
//			PreparedStatement pstmt = con.prepareStatement(MemChatJNDIDAO.DELETE);){
//			pstmt.setString(1, aMemChatChatId);
//			pstmt.setString(2, aMemChatMemId);
//			pstmt.setTimestamp(3, aDate);
//			int result = pstmt.executeUpdate();
//			System.out.println("number of rows deleted: " + result);
//		}catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "+ se.getMessage());
//		}
//	}
//
//	@Override
//	public List<MemChatVO> getAll() {
//		List<MemChatVO> memChatVOList = new ArrayList<>();
//		ResultSet rs = null;
//		try(Connection con = ds.getConnection();
//			PreparedStatement pstmt = con.prepareStatement(MemChatJNDIDAO.GET_ALL);) {
//				rs = pstmt.executeQuery();
//				while(rs.next()){
//					MemChatVO memChatVO = new MemChatVO();
//					memChatVO.setMemChatChatId(rs.getString("memChatChatId"));
//					memChatVO.setMemChatMemId(rs.getString("memChatMemId"));
//					memChatVO.setMemChatDate(rs.getTimestamp("memChatDate"));
//					memChatVO.setMemChatContent(rs.getString("memChatContent"));
//					memChatVO.setMemChatPic(rs.getBytes("memChatPic"));
//					memChatVO.setMemChatStatus(rs.getString("memChatStatus"));
//					memChatVO.setMemChatToMemId(rs.getString("memChatToMemId"));
//
//					memChatVOList.add(memChatVO);
//				}// end while
//				
//			} catch (SQLException se) {
//				throw new RuntimeException("A database error occured. "+ se.getMessage());
//			} finally {
//				if (rs != null) {
//					try {
//						rs.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//			}// end try-catch-finally	
//		return memChatVOList;
//	}
//
//
//	@Override
//	public MemChatVO findByPrimaryKey(String aMemChatChatId, String aMemChatMemId, Timestamp aDate) {
//		MemChatVO memChatVO = null;
//		ResultSet rs = null;
//		try(Connection con = ds.getConnection();
//			PreparedStatement pstmt = con.prepareStatement(MemChatJNDIDAO.GET_ONE);) {
//				pstmt.setString(1, aMemChatChatId);
//				pstmt.setString(2, aMemChatMemId);
//				pstmt.setTimestamp(3, aDate);
//				rs = pstmt.executeQuery();
//				if (rs.next()){
//					memChatVO = new MemChatVO();
//					
//					memChatVO.setMemChatChatId(rs.getString("memChatChatId"));
//					memChatVO.setMemChatMemId(rs.getString("memChatMemId"));
//					memChatVO.setMemChatDate(rs.getTimestamp("memChatDate"));
//					memChatVO.setMemChatContent(rs.getString("memChatContent"));
//					memChatVO.setMemChatPic(rs.getBytes("memChatPic"));
//					memChatVO.setMemChatStatus(rs.getString("memChatStatus"));
//					memChatVO.setMemChatToMemId(rs.getString("memChatToMemId"));
//					
//				}else{
//					System.out.println("no matched data");
//				}
//			} catch (SQLException se) {
//				throw new RuntimeException("A database error occured. "+ se.getMessage());
//			} finally {
//				if (rs != null) {
//					try {
//						rs.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//			}// end try-catch-finally	
//		return memChatVO;
//	}
//
//	@Override
//	public List<MemChatVO> findByMemChatChatId(String aMemChatChatId) {
//		List<MemChatVO> memChatVOList = new ArrayList<>();
//		ResultSet rs = null;
//		try(Connection con = ds.getConnection();
//			PreparedStatement pstmt = con.prepareStatement(MemChatJNDIDAO.GET_ALL_MEMCHATCHATID);) {
//				pstmt.setString(1, aMemChatChatId);
//				rs = pstmt.executeQuery();
//				while (rs.next()){
//					MemChatVO memChatVO = new MemChatVO();
//					
//					memChatVO.setMemChatChatId(rs.getString("memChatChatId"));
//					memChatVO.setMemChatMemId(rs.getString("memChatMemId"));
//					memChatVO.setMemChatDate(rs.getTimestamp("memChatDate"));
//					memChatVO.setMemChatContent(rs.getString("memChatContent"));
//					memChatVO.setMemChatPic(rs.getBytes("memChatPic"));
//					memChatVO.setMemChatStatus(rs.getString("memChatStatus"));
//					memChatVO.setMemChatToMemId(rs.getString("memChatToMemId"));
//					
//					memChatVOList.add(memChatVO);
//				};
//			} catch (SQLException se) {
//				throw new RuntimeException("A database error occured. "+ se.getMessage());
//			} finally {
//				if (rs != null) {
//					try {
//						rs.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//			}// end try-catch-finally	
//		return memChatVOList;
//	}
//	
//	@Override
//	public List<MemChatVO> getAll(Map<String, String[]> aMap) {
//		return getAll(aMap, MemChatDAO_interface.tableName);
//	}
//	
//	public List<MemChatVO> getAll(Map<String, String[]> aMap, String aTableName) {
//		List<MemChatVO> list = new ArrayList<MemChatVO>();
//		MemChatVO empVO = null;
//	
//		ResultSet rs = null;
//		// 重點在此行 - testing.CompositeQuery_anyTable_JNDI.getQuerySQL(map, tableName);
//		String finalSQL = util.CompositeQuery_anyTable_JNDI.getQuerySQL(aMap, aTableName);
//		System.out.println("●●finalSQL(by DAO) = "+finalSQL);
//		try(Connection con = ds.getConnection();
//			PreparedStatement pstmt = con.prepareStatement(finalSQL);) {
//			rs = pstmt.executeQuery();
//	
//			while (rs.next()) {
//				MemChatVO memChatVO = new MemChatVO();
//				
//				memChatVO.setMemChatChatId(rs.getString("memChatChatId"));
//				memChatVO.setMemChatMemId(rs.getString("memChatMemId"));
//				memChatVO.setMemChatDate(rs.getTimestamp("memChatDate"));
//				memChatVO.setMemChatContent(rs.getString("memChatContent"));
//				memChatVO.setMemChatPic(rs.getBytes("memChatPic"));
//				memChatVO.setMemChatStatus(rs.getString("memChatStatus"));
//				memChatVO.setMemChatToMemId(rs.getString("memChatToMemId"));
//
//				
//				list.add(memChatVO); // Store the row in the List
//			}
//	
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;		
//	}
//
//	@Override
//	public List<MemChatVO> getOldMsgBtwnTwoMems(String aMemChatMemId01, String aMemChatMemId02) {
//		// GET_ALL_MSGBTWNTWOMEMS
//		List<MemChatVO> memChatVOList = new ArrayList<>();
//		ResultSet rs = null;
//		try(Connection con = ds.getConnection();
//			PreparedStatement pstmt = con.prepareStatement(MemChatJNDIDAO.GET_ALL_MSGBTWNTWOMEMS);) {
//				pstmt.setString(1, aMemChatMemId01);
//				pstmt.setString(2, aMemChatMemId02);
//				pstmt.setString(3, aMemChatMemId01);
//				pstmt.setString(4, aMemChatMemId02);
//				rs = pstmt.executeQuery();
//				while (rs.next()){
//					MemChatVO memChatVO = new MemChatVO();
//					
//					memChatVO.setMemChatChatId(rs.getString("memChatChatId"));
//					memChatVO.setMemChatMemId(rs.getString("memChatMemId"));
//					memChatVO.setMemChatDate(rs.getTimestamp("memChatDate"));
//					memChatVO.setMemChatContent(rs.getString("memChatContent"));
//					memChatVO.setMemChatPic(rs.getBytes("memChatPic"));
//					memChatVO.setMemChatStatus(rs.getString("memChatStatus"));
//					memChatVO.setMemChatToMemId(rs.getString("memChatToMemId"));
//					
//					memChatVOList.add(memChatVO);
//				};
//			} catch (SQLException se) {
//				throw new RuntimeException("A database error occured. "+ se.getMessage());
//			} finally {
//				if (rs != null) {
//					try {
//						rs.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//			}// end try-catch-finally	
//		return memChatVOList;
//	}
//
//	@Override
//	public String getChatIdBtwenTwoMems(String aMemChatMemId01, String aMemChatMemId02) {
//		String chatId = null;
//		ResultSet rs = null;
//		try(Connection con = ds.getConnection();
//			PreparedStatement pstmt = con.prepareStatement(MemChatJNDIDAO.GET_CHAT_IDBTWNTWOMEMS);) {
//				pstmt.setString(1, aMemChatMemId01);
//				pstmt.setString(2, aMemChatMemId02);
//				pstmt.setString(3, aMemChatMemId01);
//				pstmt.setString(4, aMemChatMemId02);
//				rs = pstmt.executeQuery();
//				while (rs.next()){
//					chatId = rs.getString("memchatChatId");
//				};
//			} catch (SQLException se) {
//				throw new RuntimeException("A database error occured. "+ se.getMessage());
//			} finally {
//				if (rs != null) {
//					try {
//						rs.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//			}// end try-catch-finally	
//		return chatId;
//	}
//
//	@Override
//	public List<MemChatVO> getNewestMsgEachChatId(String aMemChatMemId) {
//		// GET_ALL_MSGBTWNTWOMEMS
//		List<MemChatVO> memChatVOList = new ArrayList<>();
//		ResultSet rs = null;
//		try(Connection con = ds.getConnection();
//			PreparedStatement pstmt = con.prepareStatement(MemChatJNDIDAO.GET_NEWESTMSG_CHATID);) {
//				pstmt.setString(1, aMemChatMemId);
//				pstmt.setString(2, aMemChatMemId);
//				rs = pstmt.executeQuery();
//				while (rs.next()){
//					MemChatVO memChatVO = new MemChatVO();
//					
//					memChatVO.setMemChatChatId(rs.getString("memChatChatId"));
//					memChatVO.setMemChatMemId(rs.getString("memChatMemId"));
//					memChatVO.setMemChatDate(rs.getTimestamp("memChatDate"));
//					memChatVO.setMemChatContent(rs.getString("memChatContent"));
//					memChatVO.setMemChatPic(rs.getBytes("memChatPic"));
//					memChatVO.setMemChatStatus(rs.getString("memChatStatus"));
//					memChatVO.setMemChatToMemId(rs.getString("memChatToMemId"));
//					
//					memChatVOList.add(memChatVO);
//				};
//			} catch (SQLException se) {
//				throw new RuntimeException("A database error occured. "+ se.getMessage());
//			} finally {
//				if (rs != null) {
//					try {
//						rs.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//			}// end try-catch-finally	
//		return memChatVOList;
//	}
//
//
//
//}
