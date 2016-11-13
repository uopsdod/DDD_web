package com.chat.model;

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
import com.ord.model.OrdVO;

import util.CompositeQuery_anyDB_Hibernate;
import util.HibernateUtil;


public class ChatHibernateDAO implements ChatDAO_interface {

	// 全部欄位名(複製用):
	
	private static final String GET_ALL_STMT = "from ChatVO order by chatId";

	@Override
	public void insert(ChatVO aChatVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(aChatVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(ChatVO aChatVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(aChatVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}		
	}

	@Override
	public void delete(String aChatId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

//        【此時多方(宜)可採用HQL刪除】
//			Query query = session.createQuery("delete ChatVO where chatId=?");
//			query.setParameter(0, aChatId);
//			System.out.println("刪除的筆數=" + query.executeUpdate());

//        【或此時多方(也)可採用去除關聯關係後，再刪除的方式】
			ChatVO chatVO = new ChatVO();
			chatVO.setChatId(aChatId);
			session.delete(chatVO);

//        【此時多方不可(不宜)採用cascade聯級刪除】
//        【多方chat.hbm.xml如果設為 cascade="all"或 cascade="delete"將會刪除所有相關資料-包括所屬部門與同部門的其它員工將會一併被刪除】
//			ChatVO chatVO = (ChatVO) session.get(ChatVO.class, aChatId);
//			session.delete(chatVO);

			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}	
	}

	@Override
	public List<ChatVO> getAll() {
		List<ChatVO> chatVOList = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ALL_STMT);
			chatVOList = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return chatVOList;
	}

	@Override
	public ChatVO findByPrimaryKey(String aChatId) {
		ChatVO chatVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			chatVO = (ChatVO) session.get(ChatVO.class, aChatId);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return chatVO;
	}

	@Override
	public List<ChatVO> getAll(Map<String, String[]> aMap) {
		List<ChatVO> tmpList = null;
		Class myVOClass = ChatVO.class; // 這邊相依各DAO的VO
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Criteria query = null;
	    try{
		    session.beginTransaction();	    	
		    query = session.createCriteria(myVOClass); 
			CompositeQuery_anyDB_Hibernate.getAll(query, aMap, myVOClass); 
			tmpList = query.list();
			session.getTransaction().commit();
	    }catch(RuntimeException ex){
	    	session.getTransaction().rollback();
	    	throw ex;
	    }
		return tmpList;
	}
}
