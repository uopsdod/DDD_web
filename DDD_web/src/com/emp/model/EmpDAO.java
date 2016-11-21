package com.emp.model;

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
import java.util.*;


public class EmpDAO implements EmpDAO_interface {
	
   	private static final Base64.Encoder encoder = Base64.getEncoder();
	private static final String GET_ALL_STMT = "from EmpVO order by empid";

	@Override
	public void insert(EmpVO empVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(empVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			throw ex;
		}
	}


	@Override
	public void update(EmpVO aEmpVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			Query query = session.createSQLQuery("UPDATE emp set  empName=?, empAccount=?, empPhone=?,empHireDate=?,empFireDate=?, empStatus=? ,empBirthDate=?,empProfile=?,empROCId=?,empAddress=? where empId =?");
			query.setParameter(0, aEmpVO.getEmpName());
			query.setParameter(1, aEmpVO.getEmpAccount());
			query.setParameter(2, aEmpVO.getEmpPhone());
			query.setParameter(3, aEmpVO.getEmpHireDate());
			query.setParameter(4, aEmpVO.getEmpFireDate());
			query.setParameter(5, aEmpVO.getEmpStatus());
			query.setParameter(6, aEmpVO.getEmpBirthDate());
			query.setParameter(7, aEmpVO.getEmpProfile());
			query.setParameter(8, aEmpVO.getEmpROCId());
			query.setParameter(9, aEmpVO.getEmpAddress());
			query.setParameter(10, aEmpVO.getEmpId());
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public List<EmpVO> getAll() {
		List<EmpVO> list = null;
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ALL_STMT);
			list = query.list();
			for(EmpVO empVO:list){
				if(empVO.getEmpProfile() == null){
					empVO.setBs64("");
				} else {
					empVO.setBs64(encoder.encodeToString(empVO.getEmpProfile()));
				}
			}
			
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	
	@Override
	public EmpVO getOne(String aEmpId) {
		EmpVO empVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			empVO = (EmpVO) session.get(EmpVO.class, aEmpId);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return empVO;
	}
//	private static final String GET_ONE_USER ="SELECT empId,
	//where empAccount=?

	@Override
	public EmpVO getUser(String aAccount) {
		EmpVO empVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from EmpVO where empAccount=?");
			
			query.setParameter(0, aAccount);
			  List results = query.list();
			  if(results.size() > 0){
		    	empVO  = (EmpVO) results.get(0);
		      }
			  
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return empVO;
	}

	@Override
	public void update_Psw(EmpVO aEmpVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(aEmpVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}
	
	@Override
	public byte[] getPhoto(String aEmpId) {
		EmpVO empVO = null;
		byte[] empProfile;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from EmpVO where empId=?");
			
			query.setParameter(0, aEmpId);
			 List results = query.list();
			 if(results.size() > 0){
			    	empVO  = (EmpVO) results.get(0);
			      }
			empProfile = empVO.getEmpProfile();
			
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return empProfile;
	}
	
	

}
