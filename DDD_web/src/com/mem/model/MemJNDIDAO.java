package com.mem.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.io.*;
import java.sql.*;

public class MemJNDIDAO implements MemDAO_interface {
	private static DataSource ds  = null;
	static{
		try{
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
					
		}catch(NamingException e){
			e.printStackTrace(System.err);
		}
	}
	
	public static final String INSERT_STMT = "INSERT INTO mem (memId,memAccount,memPsw,memName,memGender,memTwId,memBirthDate,memPhone,memLiveBudget,memIntro,memProfile,memBlackList,memCreditCardNo,memCreditCheckNo,memCreditDueDate) VALUES (mem_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String GET_ALL_STMT = "SELECT memId,memAccount,memPsw,memName,memGender,memTwId,to_char(memBirthDate,'yyyy-mm-dd') memBirthDate,memPhone,memLiveBudget,memIntro,memProfile,memBlackList,memCreditCardNo,memCreditCheckNo,memCreditDueDate FROM mem order by memId";
	public static final String GET_ONE_STMT = "SELECT memId,memAccount,memPsw,memName,memGender,memTwId,to_char(memBirthDate,'yyyy-mm-dd') memBirthDate,memPhone,memLiveBudget,memIntro,memProfile,memBlackList,memCreditCardNo,memCreditCheckNo,memCreditDueDate FROM mem where memId=?";
	public static final String DELETE = "DELETE FROM mem where memId = ?";
	public static final String UPDATE = "UPDATE mem set memAccount=?,memPsw=?,memName=?,memGender=?,memTwId=?,memBirthDate=?,memPhone=?,memLiveBudget=?,memIntro=?,memProfile=?,memBlackList=?,memCreditCardNo=?,memCreditCheckNo=?,memCreditDueDate=? where memId = ?";

	@Override
	public void insert(MemVO aMemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(MemJNDIDAO.INSERT_STMT);

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
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}finally {
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
			
			con =ds.getConnection();
			pstmt = con.prepareStatement(MemJNDIDAO.UPDATE);

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
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}finally {
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
			pstmt = con.prepareStatement(MemJNDIDAO.DELETE);

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
}
