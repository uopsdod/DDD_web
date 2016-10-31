package com.empauth.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpAuthJDBCDAO implements EmpAuthDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "Alen";
	String passwd = "alen27";
	
	private static final String GET_ALL_STMT = "select empAuthEmpId,empAuthAuthId from empAuth";
	private static final String DELETE = "DELETE  from empAuth where empAuthEmpId=?";

	@Override
	public List<EmpAuthVO> getAll() {
		List<EmpAuthVO> list = new ArrayList<EmpAuthVO>();
		EmpAuthVO empAuthVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				empAuthVO = new EmpAuthVO();
				empAuthVO.setEmpAuthEmpId(rs.getString("empAuthEmpId"));
				empAuthVO.setEmpAuthAuthId(rs.getString("empAuthAuthId"));

				list.add(empAuthVO); 
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
	
	@Override
	public void delete(String empAuthEmpId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, empAuthEmpId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
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

	}
	
	public static void main(String[] args) {
		EmpAuthJDBCDAO dao = new EmpAuthJDBCDAO();
		
		// del_ONE_EmpAuthEmpId
//				dao.delete("10007");
				
		// select
		List<EmpAuthVO> list = dao.getAll();
		for (EmpAuthVO aEmpAuth : list) {
			System.out.print(aEmpAuth.getEmpAuthEmpId() + ",");
			System.out.print(aEmpAuth.getEmpAuthAuthId() + ",");
			
			System.out.println();
		}
	}
}

