package com.mem.modelHibernate;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;

import com.hotel.model.HotelVO;

import util.HibernateUtil;

import java.io.*;
import java.sql.*;

public class MemDAO implements MemDAO_interface {
	private static final Base64.Encoder encoder = Base64.getEncoder();
	private static final String GET_ALL_STMT = "from MemVO order by memId";
	
	//-------只有動到你的getAll 因為我有用到 有問題再跟我說
	@Override
	public void insert(MemVO aMemVO) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(MemVO aMemVO) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(String aMemId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public MemVO findByPrimaryKey(String aMemId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<MemVO> getAll() {
		List<MemVO> list = null;
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ALL_STMT);
			list = query.list();	
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
	@Override
	public MemVO memCheck(String aMemAccount, String aMemPsw) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	----------------------------------------------------貴-----------------------------------
	
	//取一
	@Override
	public MemVO findByPrimaryKey_web(String aMemId) {
		MemVO memVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			memVO = (MemVO) session.get(MemVO.class, aMemId);		
			if(memVO.getMemProfile() == null){
				memVO.setBs64("");
			} else {
				memVO.setBs64(encoder.encodeToString(memVO.getMemProfile()));
			}
				
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return memVO;
	}
	
	//取全
	@Override
	public List<MemVO> getAll_web() {
		List<MemVO> list = null;
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ALL_STMT);
			list = query.list();
			for(MemVO memVO:list){
			if(memVO.getMemProfile() == null){
				memVO.setBs64("");
			} else {
				memVO.setBs64(encoder.encodeToString(memVO.getMemProfile() ));
			}
		}			
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
	
	//新增基本
	@Override

	public void insert_basic(MemVO aMemVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(aMemVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			throw ex;
		}
	}
	
	//更新信用卡
	@Override
	public void update_card(MemVO aMemVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();		
			Query query = session.createSQLQuery("UPDATE mem set memCreditCardNo=?,memCreditCheckNo=?,memCreditDueDate=? where memId=?");		
			query.setParameter(0, aMemVO.getMemCreditCardNo());
			query.setParameter(1, aMemVO.getMemCreditCheckNo());
			query.setParameter(2, aMemVO.getMemCreditDueDate());
			query.setParameter(3, aMemVO.getMemId());
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			throw ex;
		}
	}
	
	//更新簡介預算照片
	@Override
	public void update_iontroduction(MemVO aMemVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();		
			Query query = session.createSQLQuery("UPDATE mem set memLiveBudget=?,memIntro=?,memProfile=? where memId=?");		
			query.setParameter(0, aMemVO.getMemLiveBudget());
			query.setParameter(1, aMemVO.getMemIntro());
			query.setParameter(2, aMemVO.getMemProfile());
			query.setParameter(3, aMemVO.getMemId());
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			throw ex;
		}
	}

	//改黑名單
	@Override
	public void update_memblackList(String memId, String memBlackList) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();		
			Query query = session.createSQLQuery("UPDATE mem set memBlackList=? where memId=?");		
			query.setParameter(0, memBlackList);
			query.setParameter(1, memId);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			throw ex;
		}
	}
	
	//密碼
	@Override
	public void update_psw(MemVO aMemVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();		
			Query query = session.createSQLQuery("UPDATE mem set memPsw=? where memId=?");		
			query.setParameter(0, aMemVO.getMemPsw());
			query.setParameter(1, aMemVO.getMemId());
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			throw ex;
		}
	}
	
	//個人資料
	@Override
	public void update_memInformation(MemVO aMemVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();		
			Query query = session.createSQLQuery("UPDATE mem set memName=?,memGender=?,memTwId=?,memBirthDate=?,memPhone=? where memId=?");		
			query.setParameter(0, aMemVO.getMemName());
			query.setParameter(1, aMemVO.getMemGender());
			query.setParameter(2, aMemVO.getMemTwId());
			query.setParameter(3, aMemVO.getMemBirthDate());
			query.setParameter(4, aMemVO.getMemPhone());
			query.setParameter(5, aMemVO.getMemId());
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			throw ex;
		}
	}
	
	//取原照片
	@Override
	public byte[] getPhoto(String aMemId) {
		MemVO memVO = null;
		byte[] memProfile;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from MemVO where memId=?");		
			query.setParameter(0, aMemId);
			 List results = query.list();
			 if(results.size() > 0){
				 memVO  = (MemVO) results.get(0);
			      }
			 memProfile = memVO.getMemProfile();
			
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			throw ex;
		}
		return memProfile;
	}
	
	//帳戶比對
	@Override
	public MemVO getUser(String aAccount) {
		MemVO memVO = null;
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("FROM MemVO where memAccount=?");
			query.setParameter(0,aAccount);
			List results = query.list();
			if(results.size() > 0){
				memVO  = (MemVO) results.get(0);
				if(memVO.getMemProfile() == null){
					memVO.setBs64("");
				} else {
					memVO.setBs64(encoder.encodeToString(memVO.getMemProfile()));
				}
		      }	
			
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return memVO;
	}
	
	
	
}
