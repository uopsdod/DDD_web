package com.emp.model.JDBC;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class EmpJDBCDAO implements EmpDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "Alen";
	String passwd = "alen27";
	private static final String GET_ALL_STMT = "SELECT empId,empName,empAccount,empPwd,empPhone,empHireDate,empFireDate,empStatus,empBirthDate,empProfile,empROCId,empAddress FROM emp order by empId";
	private static final String GET_ONE_STMT = "SELECT empId,empName,empAccount,empPwd,empPhone,empHireDate,empFireDate,empStatus,empBirthDate,empProfile,empROCId,empAddress FROM emp where empId=?";
	private static final String GET_ONE_USER ="SELECT empAccount,empPwd FROM emp where empAccount=?";
	private static final String INSERT_STMT = "INSERT INTO emp (empId,empName,empAccount,empPwd,empPhone,empHireDate,empFireDate,empStatus,empBirthDate,empProfile,empROCId,empAddress) VALUES (emp_seq.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE emp set  empName=?, empAccount=?, empPwd=?, empPhone=?,empHireDate=?,empFireDate=?, empStatus=? ,empBirthDate=?,empProfile=?,empROCId=?,empAddress=? where empId =?";
	private static final String UPDATE_PSW ="UPDATE EMP set empPwd=? where empId=?";
	private static final String GET_PHOTO ="SELECT empProfile from emp where empId=?";
	private static final Base64.Encoder encoder = Base64.getEncoder();
	
	@Override
	public EmpVO getUser(String aAccount) {
		EmpVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_USER);
			pstmt.setString(1, aAccount);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				empVO = new EmpVO();
				
				empVO.setEmpAccount(rs.getString("empAccount"));
				empVO.setEmpPwd(rs.getString("empPwd"));
				
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public EmpVO getOne(String aEmpId) {
		EmpVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			return empVO;
		}
	
	@Override
	public byte[] getPhoto(String aEmpId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] empProfile;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_PHOTO);
			pstmt.setString(1, aEmpId);
			rs = pstmt.executeQuery();
			rs.next();
			empProfile=rs.getBytes("empProfile");
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
			
			return empProfile;
		}

	@Override
	public List<EmpVO> getAll() {
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void insert(EmpVO aEmpVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
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

	@Override
	public void update(EmpVO aEmpVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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


	@Override
	public void update_Psw(EmpVO aEmpVO) {
	Connection con = null;
	PreparedStatement pstmt = null;

	try {

		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(UPDATE_PSW);

		pstmt.setString(1, aEmpVO.getEmpPwd());
		pstmt.setString(2, aEmpVO.getEmpId());
	

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
		EmpJDBCDAO dao = new EmpJDBCDAO();
		
		// UPDATEPSW
//		 EmpVO empVO2 = new EmpVO();
//		 empVO2.setEmpPwd("011323235");	 
//		 empVO2.setEmpId("10003");
//		 dao.update_Psw(empVO2);
		
		// insert
//		 EmpVO empVO = new EmpVO();
//		 empVO.setEmpName("Scott");
//		 empVO.setEmpAccount("pica123@hotmail.com");
//		 empVO.setEmpPwd("123");
//		 empVO.setEmpPhone("0953113254");
//		 empVO.setEmpHireDate(Date.valueOf("2016-10-23"));
//		 empVO.setEmpStatus("0");
//		 empVO.setEmpBirthDate(Date.valueOf("1998-02-27"));
//		 empVO.setEmpProfile(new byte[1024]);
//		 empVO.setEmpROCId("H125665225");
//		 empVO.setEmpAddress("台北市信義區111號");
//		 dao.insert(empVO);

		// UPDATE
//		 EmpVO empVO1 = new EmpVO();
//		 empVO1.setEmpName("BIGPIC");
//		 empVO1.setEmpAccount("pica123@hotmail.com");
//		 empVO1.setEmpPwd("123");
//		 empVO1.setEmpPhone("0953113254");
//		 empVO1.setEmpHireDate(Date.valueOf("2016-10-23"));
//		 empVO1.setEmpFireDate(Date.valueOf("2016-10-26"));
//		 empVO1.setEmpStatus("0");
//		 empVO1.setEmpBirthDate(Date.valueOf("1998-02-27"));
//		 empVO1.setEmpProfile(new byte[1024]);
//		 empVO1.setEmpROCId("H125665225");
//		 empVO1.setEmpAddress("台北市信義區1233號");
//		 empVO1.setEmpId("10003");
//		 dao.update(empVO1);

		// select
		List<EmpVO> list = dao.getAll();
		for (EmpVO aEmp : list) {
			System.out.print(aEmp.getEmpId() + ",");
			System.out.print(aEmp.getEmpName() + ",");
			System.out.print(aEmp.getEmpAccount() + ",");
			System.out.print(aEmp.getEmpPwd() + ",");
			System.out.print(aEmp.getEmpPhone() + ",");
			System.out.print(aEmp.getEmpHireDate() + ",");
			System.out.print(aEmp.getEmpFireDate() + ",");
			System.out.print(aEmp.getEmpStatus() + ",");
			System.out.print(aEmp.getEmpBirthDate() + ",");
			System.out.print(aEmp.getEmpProfile() + ",");
			System.out.print(aEmp.getEmpROCId() + ",");
			System.out.print(aEmp.getEmpAddress() + ",");

			System.out.println();
		}

	}




	




	

	

}
