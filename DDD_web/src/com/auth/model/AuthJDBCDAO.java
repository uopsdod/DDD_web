package com.auth.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthJDBCDAO implements AuthVO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "Alen";
	String passwd = "alen27";
	private static final String GET_ALL_STMT = "select authId,authName from AUTH order by authId";
	private static final String GET_AUTH_ONE = "select e.empAuthEmpId,a.authId,a.authName from AUTH a join empAuth e "
			+ "on a.authId=e.empAuthAuthId where e.empAuthEmpId =?";
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				authVo = new AuthVo();
				authVo.setAuthId(rs.getString("authId"));
				authVo.setAuthName(rs.getString("authName"));

				list.add(authVo); 
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

	// for select now
	@Override
	public List<AuthVo> getAuthsByEmpId(String empAuthEmpId) {
		List<AuthVo> list = new ArrayList<AuthVo>();
		AuthVo authVo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_AUTH_ONE);
			pstmt.setString(1, empAuthEmpId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				authVo = new AuthVo();
				authVo.setAuthId(rs.getString("authId"));
				authVo.setAuthName(rs.getString("authName"));

				list.add(authVo); 
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

	// first del them insert
	@Override
	public void update(String empAuthEmpId, List<AuthVo> empAuthList) {

		Connection con = null;
		PreparedStatement pstmt_delete = null;
		PreparedStatement pstmt_insert = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			con.setAutoCommit(false);
			pstmt_delete = con.prepareStatement(DELETE);
			pstmt_delete.setString(1, empAuthEmpId);

			rs = pstmt_delete.executeQuery();

			pstmt_insert = con.prepareStatement(INSERT);
			for (AuthVo authVo : empAuthList) {

				pstmt_insert.setString(1, empAuthEmpId);
				pstmt_insert.setString(2, authVo.getAuthId());
				rs = pstmt_insert.executeQuery();
			}

			con.commit();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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


	public static void main(String[] args) {
		AuthJDBCDAO dao = new AuthJDBCDAO();

		// select one Auth
//		 List<AuthVo> list1 = dao.getAuthsByEmpId("10004");
//		 for (AuthVo aAuth : list1) {
//		 System.out.print(aAuth.getAuthId() + ",");
//		 System.out.print(aAuth.getAuthName() + ",");
//		 System.out.println();
//		 }
//		 System.out.println("--------------------------------------------------");
		
		
		// //UPDATE&insert
//		 List<AuthVo> list2 = new ArrayList<AuthVo>();
//		 AuthVo AuthVo = new AuthVo();
//		 AuthVo.setAuthId("103");
//		 AuthVo AuthVo2 = new AuthVo();
//		 AuthVo2.setAuthId("104");
//		 list2.add(AuthVo);
//		 list2.add(AuthVo2);
//		
//		 dao.update("10006", list2);
//		 //select ans
//		 List<AuthVo> list3 = dao.getAuthsByEmpId("10005");
//		 for (AuthVo aAuth : list3) {
//		 System.out.print(aAuth.getAuthId() + ",");
//		 System.out.print(aAuth.getAuthName() + ",");
//		 System.out.println();
//		 }

		// select ALL
//		List<AuthVo> list = dao.getAll();
//		for (AuthVo aAuth : list) {
//			System.out.print(aAuth.getAuthId() + ",");
//			System.out.print(aAuth.getAuthName() + ",");
//			System.out.println();
//		}

	}

}
