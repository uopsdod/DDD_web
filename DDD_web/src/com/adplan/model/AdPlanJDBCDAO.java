package com.adplan.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.adplan.model.AdPlanVO;

public class AdPlanJDBCDAO implements AdPlanDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "scott";
	String passwd = "tiger";
	
	private static final String INSERT_STMT = 
	"INSERT INTO adPlan (adPlanId,adPlanName,adPlanStartDate,adPlanEndDate,adPlanPrice,adPlanRemainNo) VALUES (adplan_seq.NEXTVAL,?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
	"SELECT adPlanId,adPlanName,to_char(adPlanStartDate,'yyyy-mm-dd') adPlanStartDate,to_char(adPlanEndDate,'yyyy-mm-dd') adPlanEndDate,adPlanPrice,adPlanRemainNo FROM adPlan order by adPlanId";
	private static final String GET_ONE_STMT = 
	"SELECT adPlanId,adPlanName,to_char(adPlanStartDate,'yyyy-mm-dd') adPlanStartDate,to_char(adPlanEndDate,'yyyy-mm-dd') adPlanEndDate,adPlanPrice,adPlanRemainNo FROM adPlan where adPlanId = ?";
	private static final String DELETE = 
	"DELETE FROM adPlan where adPlanId = ?";
	private static final String UPDATE = 
	"UPDATE adPlan set adPlanId=?, adPlanName=?, adPlanStartDate=?, adPlanEndDate=?, adPlanPrice=?, adPlanRemainNo=?,  where  adPlanId = ?";
	@Override
	public void insert(AdPlanVO adPlanVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url, this.userid, this.passwd);
			pstmt = con.prepareStatement(AdPlanJDBCDAO.INSERT_STMT);

			
			pstmt.setString(1,adPlanVO.getAdPlanName());
			pstmt.setDate(2,adPlanVO.getAdPlanStartDate());
			pstmt.setDate(3,adPlanVO.getAdPlanEndDate());
			pstmt.setInt(4,adPlanVO.getAdPlanPrice());
			pstmt.setInt(5,adPlanVO. getAdPlanRemainNo());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(AdPlanVO adPlanVO) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url, this.userid, this.passwd);
			pstmt = con.prepareStatement(AdPlanJDBCDAO.UPDATE);

			pstmt.setString(1,adPlanVO.getAdPlanName());
			pstmt.setDate(2,adPlanVO.getAdPlanStartDate());
			pstmt.setDate(3,adPlanVO.getAdPlanEndDate());
			pstmt.setInt(4,adPlanVO.getAdPlanPrice());
			pstmt.setInt(5,adPlanVO.getAdPlanRemainNo());
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void delete(String aAdPlanId) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url, this.userid, this.passwd);
			pstmt = con.prepareStatement(AdPlanJDBCDAO.DELETE);

			pstmt.setString(1,aAdPlanId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public AdPlanVO findByPrimaryKey(String aAdPlanId) {
		// TODO Auto-generated method stub
		
		AdPlanVO adPlanVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url, this.userid, this.passwd);
			pstmt = con.prepareStatement(AdPlanJDBCDAO.GET_ONE_STMT);

			pstmt.setString(1, aAdPlanId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// adPlanVO 也稱為 Domain objects
				adPlanVO = new AdPlanVO();
				adPlanVO.setAdPlanId(rs.getString("adPlanId"));
				adPlanVO.setAdPlanName(rs.getString("adPlanName"));
				adPlanVO.setAdPlanStartDate(rs.getDate("adPlanStartDate"));
				adPlanVO.setAdPlanEndDate(rs.getDate("adPlanEndDate"));
				adPlanVO.setAdPlanPrice(rs.getInt("adPlanPrice"));
				adPlanVO.setAdPlanRemainNo(rs.getInt("adPlanRemainNo"));
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return adPlanVO;
		
	}
	@Override
	public List<AdPlanVO> getAll() {
		// TODO Auto-generated method stub
		List<AdPlanVO> list = new ArrayList<AdPlanVO>();
		AdPlanVO adPlanVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url, this.userid, this.passwd);
			pstmt = con.prepareStatement(AdPlanJDBCDAO.GET_ALL_STMT);
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
//			int len = rsmd.getColumnCount();
//			for (int i = 1; i <= len ; i++){
//				System.out.println("rsmd.getColumnName(i)" + rsmd.getColumnName(i));
//			}
			

			while (rs.next()) {
				// adPlanVO 也稱為 Domain objects
			
		
				
				
				adPlanVO = new AdPlanVO();
				adPlanVO.setAdPlanId(rs.getString("adPlanId"));
				adPlanVO.setAdPlanName(rs.getString("adPlanName"));
				adPlanVO.setAdPlanStartDate(rs.getDate("adPlanStartDate"));
				adPlanVO.setAdPlanEndDate(rs.getDate("adPlanEndDate"));
				adPlanVO.setAdPlanPrice(rs.getInt("adPlanPrice"));
				adPlanVO.setAdPlanRemainNo(rs.getInt("adPlanRemainNo"));
				list.add(adPlanVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	
	public static void main(String[] args) {

		AdPlanJDBCDAO dao = new AdPlanJDBCDAO();

		// 新增
//		AdPlanVO adPlanVO1 = new AdPlanVO();
//		adPlanVO1.setAdPlanName("11月方案");
//		adPlanVO1.setAdPlanStartDate(java.sql.Date.valueOf("2016-11-01"));
//		adPlanVO1.setAdPlanEndDate(java.sql.Date.valueOf("2016-11-30"));
//		adPlanVO1.setAdPlanPrice(new Integer(50000));
//		adPlanVO1.setAdPlanRemainNo(new Integer(3));
//		
//		dao.insert(adPlanVO1);

		// 修改
//		AdPlanVO adPlanVO2 = new AdPlanVO();
//		adPlanVO1.setAdPlanName("12月方案");
//		adPlanVO1.setAdPlanStartDate(java.sql.Date.valueOf("2016-12-01"));
//		adPlanVO1.setAdPlanEndDate(java.sql.Date.valueOf("2016-12-31"));
//		adPlanVO1.setAdPlanPrice(new Integer(50000));
//		adPlanVO1.setAdPlanRemainNo(new Integer(3));
//		dao.update(adPlanVO2);

		// 刪除
//		dao.delete("10000001");

		// 查詢
//		AdPlanVO adPlanVO3 = dao.findByPrimaryKey("10000001");
//		System.out.print(adPlanVO3.getAdPlanId() + ",");
//		System.out.print(adPlanVO3.getAdPlanName() + ",");
//		System.out.print(adPlanVO3.getAdPlanStartDate() + ",");
//		System.out.print(adPlanVO3.getAdPlanEndDate() + ",");
//		System.out.print(adPlanVO3.getAdPlanPrice() + ",");
//		System.out.print(adPlanVO3.getAdPlanRemainNo() + ",");
//		System.out.println("---------------------");

		// 查詢
		/*List<AdPlanVO> list = dao.getAll();
		for (AdPlanVO adPlanVO3 : list) {
			System.out.print(adPlanVO3.getAdPlanId() + ",");
			System.out.print(adPlanVO3.getAdPlanName() + ",");
			System.out.print(adPlanVO3.getAdPlanStartDate() + ",");
			System.out.print(adPlanVO3.getAdPlanEndDate() + ",");
			System.out.print(adPlanVO3.getAdPlanPrice() + ",");
			System.out.print(adPlanVO3.getAdPlanRemainNo() + ",");
			System.out.println();
		}*/
	}
	
	
}
