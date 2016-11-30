package com.hotelrep.model;

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

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.mem.model.MemVO;

import util.HibernateUtil;


public class HotelRepHibernateDAO implements HotelRepDAO_interface {

	// 全部欄位名(複製用):
	// memrepId memrepOrdId memrepMemId memrepHotelId memrepEmpId memrepContent memrepStatus memrepDate memrepReviewDate 
	
	private static final String GET_ALL_STMT = "from HotelRepVO order by hotelRepId desc";
	private static final String GET_ALL_HOTELREPSTATUS = "from HotelRepVO where hotelRepStatus=:hotelRepStatus order by hotelRepId desc";
	
	
	@Override
	public String insert(HotelRepVO aHotelRepVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(aHotelRepVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return aHotelRepVO.getHotelRepId();
	}

	@Override
	public void update(HotelRepVO aHotelRepVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(aHotelRepVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}		
	}

	@Override
	public void delete(String aHotelRepId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

//        【此時多方(宜)可採用HQL刪除】
//			Query query = session.createQuery("delete MemRepVO where memRepId=?");
//			query.setParameter(0, aMemrepId);
//			System.out.println("刪除的筆數=" + query.executeUpdate());

//        【或此時多方(也)可採用去除關聯關係後，再刪除的方式】
//			MemRepVO memRepVO = new MemRepVO();
//			memRepVO.setMemRepId(aMemrepId);
//			session.delete(memRepVO);

//        【此時多方不可(不宜)採用cascade聯級刪除】
//        【多方memRep.hbm.xml如果設為 cascade="all"或 cascade="delete"將會刪除所有相關資料-包括所屬部門與同部門的其它員工將會一併被刪除】
			HotelRepVO hotelRepVO = (HotelRepVO) session.get(HotelRepVO.class, aHotelRepId);
			session.delete(hotelRepVO);

			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}	
	}

	@Override
	public List<HotelRepVO> getAll() {
		List<HotelRepVO> hotelRepVOList = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(HotelRepHibernateDAO.GET_ALL_STMT);
			hotelRepVOList = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return hotelRepVOList;
	}

	@Override
	public HotelRepVO findByPrimaryKey(String aHotelRepId) {
		HotelRepVO hotelRepVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			hotelRepVO = (HotelRepVO) session.get(HotelRepVO.class, aHotelRepId);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return hotelRepVO;
	}


	@Override
	public List<HotelRepVO> getAllByHotelRepStatus(String aHotelRepStatus){

		List<HotelRepVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(HotelRepHibernateDAO.GET_ALL_HOTELREPSTATUS);
			query.setParameter("hotelRepStatus", aHotelRepStatus);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;		
		
	}
	
	/* =============== */
	
	@Override
	public void setMemBlackList(HotelRepVO aHotelRepVO,MemVO aMemVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(aHotelRepVO);
			session.saveOrUpdate(aMemVO);			
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}		
	}

	
}
