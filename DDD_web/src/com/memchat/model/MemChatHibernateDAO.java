package com.memchat.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;

import com.memchat.model.MemChatVO;

import util.HibernateUtil;

import com.chat.model.ChatVO;
import com.mem.model.MemVO;
import com.memchat.model.MemChatDAO_interface;


public class MemChatHibernateDAO implements MemChatDAO_interface {
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
	// memChatMemId memChatChatId memChatDate memChatContent memChatPic
	private static final String GET_ALL = "from MemChatVO ORDER BY memChatChatId";

	
	private static final String GET_ALL_MEMCHATCHATID = "SELECT memChatChatId, memChatMemId, memChatDate, memChatContent, memChatPic, memChatStatus, memChatToMemId FROM memChat WHERE memChatChatId= ? ORDER BY memChatChatId";
	private static final String GET_ALL_MSGBTWNTWOMEMS = "SELECT * FROM memchat WHERE memChatMemId IN (?,?) AND memChatToMemId IN (?,?) ORDER BY memChatDate";
	private static final String GET_CHAT_IDBTWNTWOMEMS = "SELECT distinct memChatChatId FROM memchat WHERE memChatMemId IN (?,?) AND memChatToMemId IN (?,?)";
	private static final String GET_NEWESTMSG_CHATID = "SELECT * FROM memChat JOIN (SELECT memChatChatId as chatId_b, MAX(memChatDate) as date_b FROM memChat GROUP BY memChatChatId) ON memChatChatId = chatId_b AND memChatDate = date_b WHERE memChatMemId = ? OR memChatToMemId = ? ORDER BY memChatDate DESC";
	//private static final String GET_NEWESTMSG_CHATID = "SELECT * FROM (SELECT * FROM memChat WHERE ROWID IN (SELECT MAX(ROWID) FROM memchat GROUP BY memChatChatId)) WHERE memChatMemId = ? OR memChatToMemId = ? ORDER BY memChatDate DESC";
	//private static final String GET_NEWESTMSG_CHATID = "SELECT * FROM memChat WHERE (memChatMemId = ? OR memChatToMemId = ?) AND ROWID IN (SELECT MAX(ROWID) FROM memchat GROUP BY memChatChatId) ORDER BY memChatDate DESC";
	


	
	@Override
	public void insert(MemChatVO aMemChatVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(aMemChatVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
	}
	@Override
	public void update(MemChatVO aMemChatVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(aMemChatVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
	}
	@Override
	public void delete(String aMemChatId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

//        【此時多方(宜)可採用HQL刪除】
//			Query query = session.createQuery("delete MemChatVO where memChatId=?");
//			query.setParameter(0, aMemChatId);
//			System.out.println("刪除的筆數=" + query.executeUpdate());

//        【或此時多方(也)可採用去除關聯關係後，再刪除的方式】
			MemChatVO memChatVO = new MemChatVO();
			memChatVO.setMemChatId(aMemChatId);
			session.delete(memChatVO);

//        【此時多方不可(不宜)採用cascade聯級刪除】
//        【多方emp2.hbm.xml如果設為 cascade="all"或 cascade="delete"將會刪除所有相關資料-包括所屬部門與同部門的其它員工將會一併被刪除】
//			MemChatVO memChatVO = (MemChatVO) session.get(MemChatVO.class, aMemChatId);
//			session.delete(memChatVO);

			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
	}
	@Override
	public List<MemChatVO> getAll() {
		List<MemChatVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(MemChatHibernateDAO.GET_ALL);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
	@Override
	public MemChatVO findByPrimaryKey(String aMemChatId) {
		MemChatVO memChatVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			memChatVO = (MemChatVO) session.get(MemChatVO.class, aMemChatId);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return memChatVO;
	}

	
	
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
	@Override
	public List<MemChatVO> getOldMsgBtwnTwoMems(String aMemChatMemId01, String aMemChatMemId02) {
		// GET_ALL_MSGBTWNTWOMEMS
		List<MemChatVO> memChatVOList = new ArrayList<>();
		ResultSet rs = null;
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(MemChatHibernateDAO.GET_ALL_MSGBTWNTWOMEMS);) {
				pstmt.setString(1, aMemChatMemId01);
				pstmt.setString(2, aMemChatMemId02);
				pstmt.setString(3, aMemChatMemId01);
				pstmt.setString(4, aMemChatMemId02);
				rs = pstmt.executeQuery();
				while (rs.next()){
					MemChatVO memChatVO = new MemChatVO();
					
					ChatVO chatVO = new ChatVO();
					chatVO.setChatId(rs.getString("memChatChatId"));
					memChatVO.setMemChatChatVO(chatVO);
//					memChatVO.setMemChatChatId(rs.getString("memChatChatId"));
					MemVO memVO = new MemVO();
					memVO.setMemId(rs.getString("memChatMemId"));
					memChatVO.setMemChatMemVO(memVO);
//					memChatVO.setMemChatMemId(rs.getString("memChatMemId"));
					memChatVO.setMemChatDate(rs.getTimestamp("memChatDate"));
					memChatVO.setMemChatContent(rs.getString("memChatContent"));
					memChatVO.setMemChatStatus(rs.getString("memChatStatus"));
					MemVO toMemVO = new MemVO();
					toMemVO.setMemId(rs.getString("memChatToMemId"));
					memChatVO.setMemChatToMemVO(toMemVO);
//					memChatVO.setMemChatToMemId(rs.getString("memChatToMemId"));
					
					memChatVOList.add(memChatVO);
				};
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
		return memChatVOList;
	}

	@Override
	public String getChatIdBtwenTwoMems(String aMemChatMemId01, String aMemChatMemId02) {
		String chatId = null;
		ResultSet rs = null;
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(MemChatHibernateDAO.GET_CHAT_IDBTWNTWOMEMS);) {
				pstmt.setString(1, aMemChatMemId01);
				pstmt.setString(2, aMemChatMemId02);
				pstmt.setString(3, aMemChatMemId01);
				pstmt.setString(4, aMemChatMemId02);
				rs = pstmt.executeQuery();
				while (rs.next()){
					chatId = rs.getString("memchatChatId");
				};
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
		return chatId;
	}

	@Override
	public List<MemChatVO> getNewestMsgEachChatId(String aMemChatMemId) {
		// GET_ALL_MSGBTWNTWOMEMS
		List<MemChatVO> memChatVOList = new ArrayList<>();
		ResultSet rs = null;
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(MemChatHibernateDAO.GET_NEWESTMSG_CHATID);) {
				pstmt.setString(1, aMemChatMemId);
				pstmt.setString(2, aMemChatMemId);
				rs = pstmt.executeQuery();
				while (rs.next()){
					MemChatVO memChatVO = new MemChatVO();
					
					MemVO memVO = new MemVO();
					memVO.setMemId(rs.getString("memChatMemId"));
					memChatVO.setMemChatMemVO(memVO);
					memChatVO.setMemChatDate(rs.getTimestamp("memChatDate"));
					memChatVO.setMemChatContent(rs.getString("memChatContent"));
					memChatVO.setMemChatStatus(rs.getString("memChatStatus"));
					MemVO toMemVO = new MemVO();
					toMemVO.setMemId(rs.getString("memChatToMemId"));
					memChatVO.setMemChatToMemVO(toMemVO);
					
					memChatVOList.add(memChatVO);
				};
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
		return memChatVOList;
	}
//	@Override
//	public ChatVO getChatVOBtwenTwoMems(String aMemChatMemId01, String aMemChatMemId02) {
//		// "SELECT distinct memChatChatId FROM memchat WHERE memChatMemId IN (?,?) AND memChatToMemId IN (?,?)";
//		String GET_CHATVO_STMT = "FROM (SELECT * FROM MemChatVO WHERE memChatMemId IN (?,?) AND memChatToMemId IN (?,?)) WHERE ROWID IN (SELECT MAX(ROWID) FROM memchat GROUP BY memChatChatId)";
//		
//		
//		
//		ChatVO chatVO = null;
//		List<MemChatVO> list = null;
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
//			session.beginTransaction();
//			Query query = session.createQuery(GET_CHATVO_STMT);
//			query.setParameter(0, aMemChatMemId01);
//			query.setParameter(1, aMemChatMemId02);
//			query.setParameter(2, aMemChatMemId01);
//			query.setParameter(3, aMemChatMemId02);
//			
//			list = query.list();
//			chatVO = list.get(0).getMemChatChatVO();
//			session.getTransaction().commit();
//		} catch (RuntimeException ex) {
//			session.getTransaction().rollback();
//			throw ex;
//		}
//		return chatVO;
//	}
//


}
