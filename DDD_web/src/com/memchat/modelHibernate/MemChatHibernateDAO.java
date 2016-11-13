package com.memchat.modelHibernate;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.mem.model.MemVO;
import com.ord.model.OrdVO;

import util.CompositeQuery_anyDB_Hibernate;
import util.HibernateUtil;


public class MemChatHibernateDAO implements MemChatDAO_interface {

	private static final String GET_ALL_STMT = "from MemChatVO";
	private static final String DELETE_STMT = "FROM MemChatVO WHERE memChatChatId= ? AND memChatMemId = ? AND TO_CHAR(memChatDate, 'YYYYMMDD HH24:MI:SSxFF3') = TO_CHAR(?, 'YYYYMMDD HH24:MI:SSxFF3')";

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
	public void delete(String aMemChatChatId, String aMemChatMemId, Timestamp aMemChatDate) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

//        【此時多方(宜)可採用HQL刪除】
//			Query query = session.createQuery(DELETE_STMT);
//			query.setParameter(0, aMemChatChatId);
//			query.setParameter(1, aMemChatMemId);
//			query.setParameter(2, aDate);
//			System.out.println("刪除的筆數=" + query.executeUpdate());

//        【或此時多方(也)可採用去除關聯關係後，再刪除的方式】
//			MemChatVO memChatVO = new MemChatVO();
//			memChatVO.setMemChatChatId(aMemChatChatId);
//			memChatVO.setMemChatMemId(aMemChatMemId);
//			memChatVO.setMemChatDate(aMemChatDate);
//			session.delete(memChatVO);

//        【此時多方不可(不宜)採用cascade聯級刪除】
//        【多方memChat.hbm.xml如果設為 cascade="all"或 cascade="delete"將會刪除所有相關資料-包括所屬部門與同部門的其它員工將會一併被刪除】
//			MemChatVO memChatVO = (MemChatVO) session.get(MemChatVO.class, aMemrepId); // ????
//			session.delete(memChatVO);

			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}	
	}

	@Override
	public List<MemChatVO> getAll() {
		List<MemChatVO> memRepVOList = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ALL_STMT);
			memRepVOList = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return memRepVOList;
	}

	@Override
	public MemChatVO findByPrimaryKey(MemChatVOPK aMemChatVOPK) {
		MemChatVO memChatVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			memChatVO = (MemChatVO) session.get(MemChatVO.class, aMemChatVOPK);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return memChatVO;
	}

	@Override
	public List<MemChatVO> getAll(Map<String, String[]> aMap) {
		List<MemChatVO> tmpList = null;
//		Class myVOClass = MemRepVO.class; // 這邊相依各DAO的VO
//	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//	    Criteria query = null;
//	    try{
//		    session.beginTransaction();	    	
//		    query = session.createCriteria(myVOClass); 
//			CompositeQuery_anyDB_Hibernate.getAll(query, aMap, myVOClass); 
//			tmpList = query.list();
//			session.getTransaction().commit();
//	    }catch(RuntimeException ex){
//	    	session.getTransaction().rollback();
//	    	throw ex;
//	    }
		return tmpList;
	}

	@Override
	public List<MemChatVO> findByMemChatChatId(String aMemChatChatId) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	@Override
//	public List<MemRepVO> getAllByMemRepStatus(String aMemRepStatus) {
//		List<MemRepVO> memrepVOList = new ArrayList<>();
//		ResultSet rs = null;
//		try(Connection con = ds.getConnection();
//			PreparedStatement pstmt = con.prepareStatement(MemRepHibernateDAO.GET_ALL_MEMREPSTATUS);) {
//				pstmt.setString(1, aMemRepStatus);
//				rs = pstmt.executeQuery();
//				while(rs.next()){
//					MemRepVO memrepVO = new MemRepVO();
//					memrepVO.setMemRepId(rs.getString("memRepId")); 
//					memrepVO.setMemRepOrdId(rs.getString("memRepOrdId"));
//					memrepVO.setMemRepMemId(rs.getString("memRepMemId")); 
//					memrepVO.setMemRepHotelId(rs.getString("memRepHotelId")); 
//					memrepVO.setMemRepEmpId(rs.getString("memRepEmpId"));
//					memrepVO.setMemRepContent(rs.getString("memRepContent"));
//					memrepVO.setMemRepStatus(rs.getString("memRepStatus"));
//					memrepVO.setMemRepDate(rs.getDate("memRepDate")); 
//					memrepVO.setMemRepReviewDate(rs.getDate("memRepReviewDate"));
//					
//					memrepVOList.add(memrepVO);
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
//		return memrepVOList;	
//	}
	

		

}
