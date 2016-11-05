package com.emp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EmpJNDIDAO implements EmpDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String GET_ALL_STMT = "SELECT empId,empName,empAccount,empPwd,empPhone,empHireDate,empFireDate,empStatus,empBirthDate,empProfile,empROCId,empAddress FROM emp order by empId";
	private static final String GET_ONE_STMT = "SELECT empId,empName,empAccount,empPwd,empPhone,empHireDate,empFireDate,empStatus,empBirthDate,empProfile,empROCId,empAddress FROM emp where empId=?";
	private static final String INSERT_STMT = "INSERT INTO emp (empId,empName,empAccount,empPwd,empPhone,empHireDate,empFireDate,empStatus,empBirthDate,empProfile,empROCId,empAddress) VALUES (emp_seq.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE emp set  empName=?, empAccount=?, empPwd=?, empPhone=?,empHireDate=?,empFireDate=?, empStatus=? ,empBirthDate=?,empProfile=?,empROCId=?,empAddress=? where empId =?";
	private static final String UPDATE_PSW ="UPDATE EMP set empPwd=? where empId=?";
	private static final String GET_PHOTO ="SELECT empProfile from emp where empId=?";
	private static final Base64.Encoder encoder = Base64.getEncoder();
	
	@Override
	public List<EmpVO> getAll() {
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				empVO = new EmpVO();
				empVO.setEmpId(rs.getString("empId"));
				empVO.setEmpName(rs.getString("empName"));
				empVO.setEmpAccount(rs.getString("empAccount"));
				empVO.setEmpPwd(rs.getString("empPwd"));
				empVO.setEmpPhone(rs.getString("empPhone"));
				empVO.setEmpHireDate(rs.getDate("empHireDate"));
				empVO.setEmpFireDate(rs.getDate("empFireDate"));
				empVO.setEmpStatus(rs.getString("empStatus"));
				empVO.setEmpBirthDate(rs.getDate("empBirthDate"));
				empVO.setEmpProfile(rs.getBytes("empProfile"));
				if(empVO.getEmpProfile() == null){
					empVO.setBs64("");
				} else {
					empVO.setBs64(encoder.encodeToString(empVO.getEmpProfile()));
				}
				
				empVO.setEmpROCId(rs.getString("empROCId"));
				empVO.setEmpAddress(rs.getString("empAddress"));
				list.add(empVO); // Store the row in the list
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
	public EmpVO getOne(String aEmpId) {
		EmpVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, aEmpId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				empVO = new EmpVO();
				empVO.setEmpId(rs.getString("empId"));
				empVO.setEmpName(rs.getString("empName"));
				empVO.setEmpAccount(rs.getString("empAccount"));
				empVO.setEmpPwd(rs.getString("empPwd"));
				empVO.setEmpPhone(rs.getString("empPhone"));
				empVO.setEmpHireDate(rs.getDate("empHireDate"));
				empVO.setEmpFireDate(rs.getDate("empFireDate"));
				empVO.setEmpStatus(rs.getString("empStatus"));
				empVO.setEmpBirthDate(rs.getDate("empBirthDate"));
				empVO.setEmpProfile(rs.getBytes("empProfile"));
				empVO.setEmpROCId(rs.getString("empROCId"));
				empVO.setEmpAddress(rs.getString("empAddress"));
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
			return empVO;
		}
	

	@Override
	public void insert(EmpVO aEmpVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, aEmpVO.getEmpName());
			pstmt.setString(2, aEmpVO.getEmpAccount());
			pstmt.setString(3, aEmpVO.getEmpPwd());
			pstmt.setString(4, aEmpVO.getEmpPhone());
			pstmt.setDate(5, aEmpVO.getEmpHireDate());
			pstmt.setDate(6, aEmpVO.getEmpFireDate());
			pstmt.setString(7, aEmpVO.getEmpStatus());
			pstmt.setDate(8, aEmpVO.getEmpBirthDate());
			pstmt.setBytes(9,aEmpVO.getEmpProfile() );
			pstmt.setString(10, aEmpVO.getEmpROCId());
			pstmt.setString(11, aEmpVO.getEmpAddress());

			pstmt.executeUpdate();

			// Handle any driver errors
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

	@Override
	public void update(EmpVO aEmpVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, aEmpVO.getEmpName());
			pstmt.setString(2, aEmpVO.getEmpAccount());
			pstmt.setString(3, aEmpVO.getEmpPwd());
			pstmt.setString(4, aEmpVO.getEmpPhone());
			pstmt.setDate(5, aEmpVO.getEmpHireDate());
			pstmt.setDate(6, aEmpVO.getEmpFireDate());
			pstmt.setString(7, aEmpVO.getEmpStatus());
			pstmt.setDate(8, aEmpVO.getEmpBirthDate());
			pstmt.setBytes(9, aEmpVO.getEmpProfile());
			pstmt.setString(10, aEmpVO.getEmpROCId());
			pstmt.setString(11, aEmpVO.getEmpAddress());
			pstmt.setString(12, aEmpVO.getEmpId());

			pstmt.executeUpdate();

			// Handle any driver errors
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

	@Override
	public void update_Psw(EmpVO aEmpVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PSW);

			pstmt.setString(1, aEmpVO.getEmpPwd());
			pstmt.setString(2, aEmpVO.getEmpId());
		

			pstmt.executeUpdate();

			// Handle any driver errors
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

	@Override
	public byte[] getPhoto(String aEmpId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] empProfile;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PHOTO);
			pstmt.setString(1, aEmpId);
			rs = pstmt.executeQuery();
			rs.next();
			empProfile=rs.getBytes("empProfile");
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
			
			return empProfile;
		}

}
