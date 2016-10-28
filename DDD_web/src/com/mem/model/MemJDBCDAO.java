package com.mem.model;

import java.util.*;
import java.io.*;
import java.sql.*;

public class MemJDBCDAO implements MemDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "plzdongo";
	String passwd = "Tom800712";

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
			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url, this.userid, this.passwd);
			pstmt = con.prepareStatement(MemJDBCDAO.INSERT_STMT);

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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

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
			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url, this.userid, this.passwd);
			pstmt = con.prepareStatement(MemJDBCDAO.UPDATE);

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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url, this.userid, this.passwd);
			pstmt = con.prepareStatement(MemJDBCDAO.DELETE);

			pstmt.setString(1, aMemId);
			pstmt.execute();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public MemVO findByPrimaryKey(String aMemid) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url, this.userid, this.passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, aMemid);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());

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
			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url, this.userid, this.passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

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

	public static void main(String[] args) {

		String picName = "javaWu.jpg";
		File pic = new File("src/com/mem/piFrom", picName);
		byte[] buffer = null;
		InputStream fin = null;
		try {
			if (pic.exists()) {
				System.out.println("pic exists2");
			}
			long flen = pic.length();
			fin = new FileInputStream(pic);
			buffer = new byte[fin.available()];
			fin.read(buffer);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(fin != null){
				try{
					fin.close();
				}catch(IOException ie){
					ie.printStackTrace(System.err);
				}
			}
		}
		MemJDBCDAO dao = new MemJDBCDAO();

		// insert
		MemVO memVO1 = new MemVO();
		memVO1.setMemAccount("abcde43233@gmail.com");
		memVO1.setMemPsw("12345678");
		memVO1.setMemName("吳小志");
		memVO1.setMemGender("M");
		memVO1.setMemTwId("a123456789");
		memVO1.setMemBirthDate(java.sql.Date.valueOf("1999-10-18"));
		memVO1.setMemPhone("0977885516");
		memVO1.setMemLiveBudget(1500);
		memVO1.setMemIntro("我愛JAVA");
		memVO1.setMemProfile(buffer);
		memVO1.setMemBlackList("0");
		memVO1.setMemCreditCardNo("1234567890123456");
		memVO1.setMemCreditCheckNo("123");
		memVO1.setMemCreditDueDate("2018-07-02");
		dao.insert(memVO1);

		// update
		// MemVO memVO2 = new MemVO();
		// memVO2.setMemAccount("abcde43233@gmail.com");
		// memVO2.setMemPsw("12345678");
		// memVO2.setMemName("吳小志");
		// memVO2.setMemGender("M");
		// memVO2.setMemTwId("a123456789");
		// memVO2.setMemBirthDate(java.sql.Date.valueOf("1999-10-18"));
		// memVO2.setMemPhone("0999888855");
		// memVO2.setMemLiveBudget(1500);
		// memVO2.setMemIntro("我愛JAVA");
		// memVO2.setMemProfile(null);
		// memVO2.setMemBlackList("0");
		// memVO2.setMemCreditCardNo("1234567890123456");
		// memVO2.setMemCreditCheckNo("123");
		// memVO2.setMemCreditDueDate(java.sql.Date.valueOf("2019-07-02"));
		// memVO2.setMemId("10000001");
		// dao.update(memVO2);
		//
		// //delete
		// dao.delete("10000001");

		// select one only
		// MemVO memVO3 = dao.findByPrimaryKey("10000001");
		// System.out.print(memVO3.getMemId() + ",");
		// System.out.print(memVO3.getMemAccount() + ",");
		// System.out.print(memVO3.getMemPsw() + ",");
		// System.out.print(memVO3.getMemName() + ",");
		// System.out.print(memVO3.getMemGender() + ",");
		// System.out.print(memVO3.getMemTwId() + ",");
		// System.out.print(memVO3.getMemBirthDate() + ",");
		// System.out.print(memVO3.getMemPhone() + ",");
		// System.out.print(memVO3.getMemLiveBudget() + ",");
		// System.out.print(memVO3.getMemIntro() + ",");
		// System.out.print(memVO3.getMemProfile() + ",");
		// System.out.print(memVO3.getMemBlackList() + ",");
		// System.out.print(memVO3.getMemCreditCardNo() + ",");
		// System.out.print(memVO3.getMemCreditCheckNo() + ",");
		// System.out.println(memVO3.getMemCreditDueDate());

		// select All
		// List<MemVO> list = dao.getAll();
		// for(MemVO memVO : list){
		// System.out.print(memVO.getMemId() + ",");
		// System.out.print(memVO.getMemAccount() + ",");
		// System.out.print(memVO.getMemPsw() + ",");
		// System.out.print(memVO.getMemName() + ",");
		// System.out.print(memVO.getMemGender() + ",");
		// System.out.print(memVO.getMemTwId() + ",");
		// System.out.print(memVO.getMemBirthDate() + ",");
		// System.out.print(memVO.getMemPhone() + ",");
		// System.out.print(memVO.getMemLiveBudget() + ",");
		// System.out.print(memVO.getMemIntro() + ",");
		// System.out.print(memVO.getMemProfile() + ",");
		// System.out.print(memVO.getMemBlackList() + ",");
		// System.out.print(memVO.getMemCreditCardNo() + ",");
		// System.out.print(memVO.getMemCreditCheckNo() + ",");
		// System.out.println(memVO.getMemCreditDueDate());
		// }

	}

}
