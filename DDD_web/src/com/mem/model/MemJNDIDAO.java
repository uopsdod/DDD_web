package com.mem.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.io.*;
import java.sql.*;

public class MemJNDIDAO implements MemDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");

		} catch (NamingException e) {
			e.printStackTrace(System.err);
		}
	}

	public static final String INSERT_STMT = "INSERT INTO mem (memId,memAccount,memPsw,memName,memGender,memTwId,memBirthDate,memPhone,memLiveBudget,memIntro,memProfile,memBlackList,memCreditCardNo,memCreditCheckNo,memCreditDueDate) VALUES (mem_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String GET_ALL_STMT = "SELECT memId,memAccount,memPsw,memName,memGender,memTwId,to_char(memBirthDate,'yyyy-mm-dd') memBirthDate,memPhone,memLiveBudget,memIntro,memProfile,memBlackList,memCreditCardNo,memCreditCheckNo,memCreditDueDate FROM mem order by memId";
	public static final String GET_ONE_STMT = "SELECT memId,memAccount,memPsw,memName,memGender,memTwId,to_char(memBirthDate,'yyyy-mm-dd') memBirthDate,memPhone,memLiveBudget,memIntro,memProfile,memBlackList,memCreditCardNo,memCreditCheckNo,memCreditDueDate FROM mem where memId=?";
	public static final String DELETE = "DELETE FROM mem where memId = ?";
	public static final String UPDATE = "UPDATE mem set memAccount=?,memPsw=?,memName=?,memGender=?,memTwId=?,memBirthDate=?,memPhone=?,memLiveBudget=?,memIntro=?,memProfile=?,memBlackList=?,memCreditCardNo=?,memCreditCheckNo=?,memCreditDueDate=? where memId = ?";
	public static final String CHECK_MEMBER = "SELECT memId, memAccount, memPsw, memName FROM mem where memAccount = ?";
	//-----------------------------------------------------------貴新增
	private static final String INSERT_BASIC = "INSERT INTO mem (memId,memAccount,memPsw,memName,memGender,memTwId,"
			+ "memBirthDate,memPhone,memBlackList) VALUES (mem_seq.NEXTVAL, ?, ?, ?, ?,?,?,?,?)";//註冊
	private static final String UPDATE_CARD ="UPDATE mem set memCreditCardNo=?,memCreditCheckNo=?,memCreditDueDate=? where memId=?";//信用卡
	private static final String UPDATE_IONTRODUCTION ="UPDATE mem set memLiveBudget=?,memIntro=?,memProfile=? where memId=?";//簡介預算照片		
	private static final String UPDATE_MEMBLACKLIST ="UPDATE mem set memBlackList=? where memId=?";//改黑名單		
	private static final String UPDATE_PSW ="UPDATE mem set memPsw=? where memId=?";//密碼
	private static final String UPDATE_MEMINFORMATION ="UPDATE mem set memName=?,memGender=?,memTwId=?,memBirthDate=?,memPhone=? where memId=?";//個人資料	
	private static final Base64.Encoder encoder = Base64.getEncoder();
	private static final String GET_PHOTO ="SELECT memProfile from mem where memId=?";
	public static final String GET_ALL_ACCOUT = "SELECT memId,memAccount,memPsw,memName,memGender,memTwId,to_char(memBirthDate,'yyyy-mm-dd') memBirthDate,"
			+ "memPhone,memLiveBudget,memIntro,memProfile,memBlackList,memCreditCardNo,memCreditCheckNo,memCreditDueDate FROM mem where memAccount=?";
	
	
	@Override
	public void insert(MemVO aMemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(MemDAO.INSERT_STMT);

			pstmt.setString(1, aMemVO.getMemAccount());
			pstmt.setString(2, aMemVO.getMemPsw());
			pstmt.setString(3, aMemVO.getMemName());
			pstmt.setString(4, aMemVO.getMemGender());
			pstmt.setString(5, aMemVO.getMemTwId());
			pstmt.setDate(6, aMemVO.getMemBirthDate());
			pstmt.setString(7, aMemVO.getMemPhone());
			pstmt.setInt(8, aMemVO.getMemLiveBudget());
			pstmt.setString(9, aMemVO.getMemIntro());
			pstmt.setBytes(10, aMemVO.getMemProfile());
			pstmt.setString(11, aMemVO.getMemBlackList());
			pstmt.setString(12, aMemVO.getMemCreditCardNo());
			pstmt.setString(13, aMemVO.getMemCreditCheckNo());
			pstmt.setString(14, aMemVO.getMemCreditDueDate());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(MemVO aMemVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(MemDAO.UPDATE);

			pstmt.setString(1, aMemVO.getMemAccount());
			pstmt.setString(2, aMemVO.getMemPsw());
			pstmt.setString(3, aMemVO.getMemName());
			pstmt.setString(4, aMemVO.getMemGender());
			pstmt.setString(5, aMemVO.getMemTwId());
			pstmt.setDate(6, aMemVO.getMemBirthDate());
			pstmt.setString(7, aMemVO.getMemPhone());
			pstmt.setInt(8, aMemVO.getMemLiveBudget());
			pstmt.setString(9, aMemVO.getMemIntro());
			pstmt.setBytes(10, aMemVO.getMemProfile());
			pstmt.setString(11, aMemVO.getMemBlackList());
			pstmt.setString(12, aMemVO.getMemCreditCardNo());
			pstmt.setString(13, aMemVO.getMemCreditCheckNo());
			pstmt.setString(14, aMemVO.getMemCreditDueDate());
			pstmt.setString(15, aMemVO.getMemId());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(String aMemId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(MemDAO.DELETE);

			pstmt.setString(1, aMemId);
			pstmt.execute();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occuerd. " + se.getSQLState());
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
	public MemVO findByPrimaryKey(String aMemId) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, aMemId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMemId(rs.getString("memId"));
				memVO.setMemAccount(rs.getString("memAccount"));
				memVO.setMemPsw(rs.getString("memPsw"));
				memVO.setMemName(rs.getString("memName"));
				memVO.setMemGender(rs.getString("memGender"));
				memVO.setMemTwId(rs.getString("memTwId"));
				memVO.setMemBirthDate(rs.getDate("memBirthDate"));
				memVO.setMemPhone(rs.getString("memPhone"));
				memVO.setMemLiveBudget(rs.getInt("memLiveBudget"));
				memVO.setMemIntro(rs.getString("memIntro"));
				memVO.setMemProfile(rs.getBytes("memProfile"));
				
				memVO.setMemBlackList(rs.getString("memBlackList"));
				memVO.setMemCreditCardNo(rs.getString("memCreditCardNo"));
				memVO.setMemCreditCheckNo(rs.getString("memCreditCheckNo"));
				memVO.setMemCreditDueDate(rs.getString("memCreditDueDate"));

			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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

		return memVO;
	}

	@Override
	public List<MemVO> getAll() {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMemId(rs.getString("memId"));
				memVO.setMemAccount(rs.getString("memAccount"));
				memVO.setMemPsw(rs.getString("memPsw"));
				memVO.setMemName(rs.getString("memName"));
				memVO.setMemGender(rs.getString("memGender"));
				memVO.setMemTwId(rs.getString("memTwId"));
				memVO.setMemBirthDate(rs.getDate("memBirthDate"));
				memVO.setMemPhone(rs.getString("memPhone"));
				memVO.setMemLiveBudget(rs.getInt("memLiveBudget"));
				memVO.setMemIntro(rs.getString("memIntro"));
				memVO.setMemProfile(rs.getBytes("memProfile"));
				memVO.setMemBlackList(rs.getString("memBlackList"));
				memVO.setMemCreditCardNo(rs.getString("memCreditCardNo"));
				memVO.setMemCreditCheckNo(rs.getString("memCreditCheckNo"));
				memVO.setMemCreditDueDate(rs.getString("memCreditDueDate"));
				list.add(memVO);

			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
		return list;

	}

	@Override
	public MemVO memCheck(String aMemAccount, String aMemPsw) {
		MemVO memVO = new MemVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(CHECK_MEMBER);
			pstmt.setString(1, aMemAccount);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memVO.setMemAccount(rs.getString("memAccount"));
				memVO.setMemPsw(rs.getString("memPsw"));
				memVO.setMemId(rs.getString("memId"));
				memVO.setMemName(rs.getString("memName"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
		return memVO;
	}
//-------------------------------------------------貴新增
	public void insert_basic(MemVO aMemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_BASIC);
			
			pstmt.setString(1, aMemVO.getMemAccount());
			pstmt.setString(2, aMemVO.getMemPsw());
			pstmt.setString(3, aMemVO.getMemName());
			pstmt.setString(4, aMemVO.getMemGender());
			pstmt.setString(5, aMemVO.getMemTwId());
			pstmt.setDate(6, aMemVO.getMemBirthDate());
			pstmt.setString(7, aMemVO.getMemPhone());
			pstmt.setString(8, aMemVO.getMemBlackList());
			
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
	public void update_card(MemVO aMemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_CARD);
			pstmt.setString(1, aMemVO.getMemCreditCardNo());
			pstmt.setString(2, aMemVO.getMemCreditCheckNo());
			pstmt.setString(3, aMemVO.getMemCreditDueDate());	
			pstmt.setString(4, aMemVO.getMemId());
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
	public void update_iontroduction(MemVO aMemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_IONTRODUCTION);
			pstmt.setInt(1, aMemVO.getMemLiveBudget());
			pstmt.setString(2, aMemVO.getMemIntro());
			pstmt.setBytes(3, aMemVO.getMemProfile());	
			pstmt.setString(4, aMemVO.getMemId());
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
	public void update_memblackList(String memId,String memBlackList) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_MEMBLACKLIST);
			pstmt.setString(1, memBlackList);
			pstmt.setString(2, memId);
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
	public void update_psw(MemVO aMemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PSW);
			pstmt.setString(1, aMemVO.getMemPsw());
			pstmt.setString(2, aMemVO.getMemId());
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
	public void update_memInformation(MemVO aMemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_MEMINFORMATION);
			pstmt.setString(1, aMemVO.getMemName());
			pstmt.setString(2, aMemVO.getMemGender());
			pstmt.setString(3, aMemVO.getMemTwId());
			pstmt.setDate(4, aMemVO.getMemBirthDate());
			pstmt.setString(5, aMemVO.getMemPhone());
			pstmt.setString(6, aMemVO.getMemId());
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
	public byte[] getPhoto(String aMemId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] memProfile;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PHOTO);
			pstmt.setString(1, aMemId);
			rs = pstmt.executeQuery();
			rs.next();
			memProfile=rs.getBytes("memProfile");
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
			
			return memProfile;
		}
	
	@Override
	public MemVO getUser(String aAccount) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemVO memVO = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_ACCOUT);
			pstmt.setString(1, aAccount);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMemId(rs.getString("memId"));
				memVO.setMemAccount(rs.getString("memAccount"));
				memVO.setMemPsw(rs.getString("memPsw"));
				memVO.setMemName(rs.getString("memName"));
				memVO.setMemGender(rs.getString("memGender"));
				memVO.setMemTwId(rs.getString("memTwId"));
				memVO.setMemBirthDate(rs.getDate("memBirthDate"));
				memVO.setMemPhone(rs.getString("memPhone"));
				memVO.setMemLiveBudget(rs.getInt("memLiveBudget"));
				memVO.setMemIntro(rs.getString("memIntro"));
				memVO.setMemProfile(rs.getBytes("memProfile"));
				if(memVO.getMemProfile() == null){
					memVO.setBs64("");
				} else {
					memVO.setBs64(encoder.encodeToString(memVO.getMemProfile()));
				}
				memVO.setMemBlackList(rs.getString("memBlackList"));
				memVO.setMemCreditCardNo(rs.getString("memCreditCardNo"));
				memVO.setMemCreditCheckNo(rs.getString("memCreditCheckNo"));
				memVO.setMemCreditDueDate(rs.getString("memCreditDueDate"));

				
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
			return memVO;
		}
	
	@Override
	public MemVO findByPrimaryKey_web(String aMemId) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, aMemId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMemId(rs.getString("memId"));
				memVO.setMemAccount(rs.getString("memAccount"));
				memVO.setMemPsw(rs.getString("memPsw"));
				memVO.setMemName(rs.getString("memName"));
				memVO.setMemGender(rs.getString("memGender"));
				memVO.setMemTwId(rs.getString("memTwId"));
				memVO.setMemBirthDate(rs.getDate("memBirthDate"));
				memVO.setMemPhone(rs.getString("memPhone"));
				memVO.setMemLiveBudget(rs.getInt("memLiveBudget"));
				memVO.setMemIntro(rs.getString("memIntro"));
				memVO.setMemProfile(rs.getBytes("memProfile"));
				if(memVO.getMemProfile() == null){
					memVO.setBs64("");
				} else {
					memVO.setBs64(encoder.encodeToString(memVO.getMemProfile()));
				}
				memVO.setMemBlackList(rs.getString("memBlackList"));
				memVO.setMemCreditCardNo(rs.getString("memCreditCardNo"));
				memVO.setMemCreditCheckNo(rs.getString("memCreditCheckNo"));
				memVO.setMemCreditDueDate(rs.getString("memCreditDueDate"));

			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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

		return memVO;
	}
	
	@Override
	public List<MemVO> getAll_web() {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMemId(rs.getString("memId"));
				memVO.setMemAccount(rs.getString("memAccount"));
				memVO.setMemPsw(rs.getString("memPsw"));
				memVO.setMemName(rs.getString("memName"));
				memVO.setMemGender(rs.getString("memGender"));
				memVO.setMemTwId(rs.getString("memTwId"));
				memVO.setMemBirthDate(rs.getDate("memBirthDate"));
				memVO.setMemPhone(rs.getString("memPhone"));
				memVO.setMemLiveBudget(rs.getInt("memLiveBudget"));
				memVO.setMemIntro(rs.getString("memIntro"));
				memVO.setMemProfile(rs.getBytes("memProfile"));
				if(memVO.getMemProfile() == null){
					memVO.setBs64("");
				} else {
					memVO.setBs64(encoder.encodeToString(memVO.getMemProfile()));
				}
				memVO.setMemBlackList(rs.getString("memBlackList"));
				memVO.setMemCreditCardNo(rs.getString("memCreditCardNo"));
				memVO.setMemCreditCheckNo(rs.getString("memCreditCheckNo"));
				memVO.setMemCreditDueDate(rs.getString("memCreditDueDate"));
				list.add(memVO);

			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
		return list;

	}
}
