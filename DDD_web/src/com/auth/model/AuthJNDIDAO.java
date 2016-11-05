package com.auth.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AuthJNDIDAO implements AuthVO_interface {
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
	private static final String GET_AUTH_ONE = "select empAuthAuthId from empAuth  where empAuthEmpId=?" ;
			
	private static final String DELETE = "DELETE  from empAuth where empAuthEmpId=?";
	private static final String INSERT = "INSERT INTO empAuth (empAuthEmpId,empAuthAuthId) VALUES (?,?)";
	
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
	@Override
	public List<String> getAuthsByEmpId(String empAuthEmpId) {
		List<String> list = new ArrayList<String>();
		String authId = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_AUTH_ONE);
			pstmt.setString(1, empAuthEmpId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				
				authId=rs.getString("empAuthAuthId");
				

				list.add(authId); 
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
	@Override
	public void update(String empAuthEmpId, String[] empAuthList) {
		Connection con = null;
		PreparedStatement pstmt_delete = null;
		PreparedStatement pstmt_insert = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt_delete = con.prepareStatement(DELETE);
			pstmt_delete.setString(1, empAuthEmpId);

			rs = pstmt_delete.executeQuery();

			pstmt_insert = con.prepareStatement(INSERT);
			for (String authid : empAuthList) {

				pstmt_insert.setString(1, empAuthEmpId);
				pstmt_insert.setString(2, authid);
				rs = pstmt_insert.executeQuery();
			}

			con.commit();
			// Handle any driver errors
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			if (pstmt_delete != null) {
				try {
					pstmt_delete.close();
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
	}
}
