package com.auth.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;

import com.auth.model.AuthVo;

import util.HibernateUtil;

public class AuthDAO implements AuthVO_interface {	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	private static final String GET_ALL_STMT = "select authId,authName from AUTH order by authId";
	
	private static final String DELETE = "DELETE  from empAuth where empAuthEmpId=?";
	private static final String INSERT = "INSERT INTO empAuth (empAuthEmpId,empAuthAuthId) VALUES (?,?)";
	
	
	@Override
	public List<String> getAuthsByEmpId(String empAuthEmpId) {
		List<String> list = new ArrayList<String>();
		AuthVo authVo = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createSQLQuery("select empAuthAuthId from empAuth  where empAuthEmpId=?");			
			query.setParameter(0, empAuthEmpId);
			list = query.list();
		 
			  System.out.println(empAuthEmpId);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
	
	@Override
	public void update(String empAuthEmpId, String[] empAuthList) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createSQLQuery("DELETE  from empAuth where empAuthEmpId=?");
			query.setParameter(0, empAuthEmpId);
			query.executeUpdate();
			
				if(empAuthList!=null){
					for (String authid : empAuthList) {
						Query query1 = session.createSQLQuery("INSERT INTO empAuth (empAuthEmpId,empAuthAuthId) VALUES (?,?)");
						query1.setParameter(0, empAuthEmpId);
						query1.setParameter(1, authid);
						query1.executeUpdate();
				}
			}
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}	
	}

	
	@Override
	public List<AuthVo> getAll() {
		List<AuthVo> list = new ArrayList<AuthVo>();
		AuthVo authVo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				authVo = new AuthVo();
				authVo.setAuthId(rs.getString("authId"));
				authVo.setAuthName(rs.getString("authName"));

				list.add(authVo); 
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	
	

}
