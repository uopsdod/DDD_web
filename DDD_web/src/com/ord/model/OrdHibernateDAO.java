package com.ord.model;

/*
 Hibernate is providing a factory.getCurrentSession() method for retrieving the current session. A
 new session is opened for the first time of calling this method, and closed when the transaction is
 finished, no matter commit or rollback. But what does it mean by the “current session”? We need to
 tell Hibernate that it should be the session bound with the current thread.

 <hibernate-configuration>
 <session-factory>
 ...
 <property name="current_session_context_class">thread</property>
 ...
 </session-factory>
 </hibernate-configuration>

 */

import org.hibernate.*;
import util.HibernateUtil;

import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.hotel.model.HotelService;
import com.hotel.model.HotelVO;

import org.hibernate.Criteria;

public class OrdHibernateDAO implements OrdDAO_interface {

	private static final String GET_ALL_STMT = "from OrdVO order by ordId desc";
	private static final String GET_ALL_ORDMEMID_STMT = "from OrdVO where ordMemId=:ordMemId order by ordId desc";
	private static final String GET_ALL_ORDHOTELID_STMT = "from OrdVO where ordHotelId=:ordHotelId order by ordId desc";
	
	@Override
	public String insert(OrdVO aOrdVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(aOrdVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return null;
	}

	@Override
	public void update(OrdVO aOrdVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(aOrdVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(String aOrdId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

//        【此時多方(宜)可採用HQL刪除】
//			Query query = session.createQuery("delete EmpVO where empno=?");
//			query.setParameter(0, empno);
//			System.out.println("刪除的筆數=" + query.executeUpdate());

//        【或此時多方(也)可採用去除關聯關係後，再刪除的方式】
			OrdVO ordVO = new OrdVO();
			ordVO.setOrdId(aOrdId);
			session.delete(ordVO);

//        【此時多方不可(不宜)採用cascade聯級刪除】
//        【多方emp2.hbm.xml如果設為 cascade="all"或 cascade="delete"將會刪除所有相關資料-包括所屬部門與同部門的其它員工將會一併被刪除】
//			EmpVO empVO = (EmpVO) session.get(EmpVO.class, empno);
//			session.delete(empVO);

			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public OrdVO findByPrimaryKey(String aOrdId) {
		OrdVO ordVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			ordVO = (OrdVO) session.get(OrdVO.class, aOrdId);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return ordVO;
	}

	@Override
	public List<OrdVO> getAll() {
		List<OrdVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(OrdHibernateDAO.GET_ALL_STMT);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	public static Criteria getACriteriaForAntDB(Criteria aQuery, String aColumnName, String aValue){
	
		/* 數字(Integer) */
//		ordPrice
//		ordRatingStarNo
		
		if("ordPrice".equals(aColumnName) || "ordRatingStarNo".equals(aColumnName)){
			aQuery.add(Restrictions.eq(aColumnName, new Integer(aValue)));
		}

		/* 字串 */
//		ordId
//		ordRoomId
//		ordMemId
//		ordHotelId
//		ordStatus
//		ordMsgNo
		
		else if("ordId".equals(aColumnName) || "ordRoomVO.roomId".equals(aColumnName) || "ordMemVO.memId".equals(aColumnName) ||
				"ordHotelVO.hotelId".equals(aColumnName) || "ordStatus".equals(aColumnName) || "ordMsgNo".equals(aColumnName) ){
			aQuery.add(Restrictions.like(aColumnName, "%"+ aValue +"%"));
		}
		
		return aQuery;	
	}
	
	
	@Override
	public List<OrdVO> getAll(Map<String, String[]> aMap){

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		List<OrdVO> list = null;
		
		try{
			Criteria query = session.createCriteria(OrdHibernateDAO.class);
			Set<String> keys = aMap.keySet();
			int count = 0;
			
			for(String key : keys){
				String value = aMap.get(key)[0];
				if(value!=null && value.trim().length()!=0 && !"action".equals(key)){
					count++;
					query = getACriteriaForAntDB(query,key,value);
					System.out.println("有送出複合查詢資料的欄位數 count = " + count);
				}
			}
			
			query.addOrder(Order.desc("ordId"));
			
			list = query.list();
			
			tx.commit();
			
		}
		catch (RuntimeException ex) {
			if(tx != null){
				tx.rollback();
			}
			throw ex;
		}
				
		return list;		

	}
	
	@Override
	public List<OrdVO> getAllByOrdMemId(String aOrdMemId){

		List<OrdVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(OrdHibernateDAO.GET_ALL_ORDMEMID_STMT);
			query.setParameter("ordMemId", aOrdMemId);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;		
		
	}
	
	@Override
	public List<OrdVO> getAllByOrdHotelId(String aOrdHotelId){
		List<OrdVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(OrdHibernateDAO.GET_ALL_ORDHOTELID_STMT);
			query.setParameter("ordHotelId", aOrdHotelId);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;		
	}
	
	@Override
	public void updateRating(String aOrdId, String aOrdRatingStarNo, String aOrdRatingContent) {		
	}
	
	public static void main(String[] args) {

		OrdDAO dao = new OrdDAO();

//		//● 新增
//		com.dept.model.DeptVO deptVO = new com.dept.model.DeptVO(); // 部門POJO
//		deptVO.setDeptno(10);

//		EmpVO empVO1 = new EmpVO();
//		empVO1.setEname("吳永志1");
//		empVO1.setJob("MANAGER");
//		empVO1.setHiredate(java.sql.Date.valueOf("2005-01-01"));
//		empVO1.setSal(new Double(50000));
//		empVO1.setComm(new Double(500));
//		empVO1.setDeptVO(deptVO);
//		dao.insert(empVO1);



		//● 修改
//		EmpVO empVO2 = new EmpVO();
//		empVO2.setEmpno(7001);
//		empVO2.setEname("吳永志2");
//		empVO2.setJob("MANAGER2");
//		empVO2.setHiredate(java.sql.Date.valueOf("2002-01-01"));
//		empVO2.setSal(new Double(20000));
//		empVO2.setComm(new Double(200));
//		empVO2.setDeptVO(deptVO);
//		dao.update(empVO2);



		//● 刪除(小心cascade - 多方emp2.hbm.xml如果設為 cascade="all"或
		// cascade="delete"將會刪除所有相關資料-包括所屬部門與同部門的其它員工將會一併被刪除)
//		dao.delete(7014);



		//● 查詢-findByPrimaryKey (多方emp2.hbm.xml必須設為lazy="false")(優!)
//		EmpVO empVO3 = dao.findByPrimaryKey(7001);
//		System.out.print(empVO3.getEmpno() + ",");
//		System.out.print(empVO3.getEname() + ",");
//		System.out.print(empVO3.getJob() + ",");
//		System.out.print(empVO3.getHiredate() + ",");
//		System.out.print(empVO3.getSal() + ",");
//		System.out.print(empVO3.getComm() + ",");
//		// 注意以下三行的寫法 (優!)
//		System.out.print(empVO3.getDeptVO().getDeptno() + ",");
//		System.out.print(empVO3.getDeptVO().getDname() + ",");
//		System.out.print(empVO3.getDeptVO().getLoc());
//		System.out.println("\n---------------------");



		//● 查詢-getAll (多方emp2.hbm.xml必須設為lazy="false")(優!)
//		List<OrdVO> list = dao.getAll();
//		for (OrdVO aOrd : list) {
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
	}


}
