package com.adplan.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ad.model.AdJDBCDAO;
import com.adplan.model.AdPlanVO;

public class AdPlanDAO implements AdPlanDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO AdPlan (adPlanId,adPlanName,adPlanStartDate,adPlanEndDate,adPlanPrice,adPlanRemainNo) VALUES (adPlan_seq.NEXTVAL,?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT adPlanId,adPlanName,to_char(adPlanStartDate,'yyyy-mm-dd') adPlanStartDate,to_char(adPlanEndDate,'yyyy-mm-dd') adPlanEndDate,adPlanPrice,adPlanRemainNo FROM AdPlan order by adPlanId";
	private static final String GET_ONE_STMT = "SELECT adPlanId,adPlanName,to_char(adPlanStartDate,'yyyy-mm-dd') adPlanStartDate,to_char(adPlanEndDate,'yyyy-mm-dd') adPlanEndDate,adPlanPrice,adPlanRemainNo FROM AdPlan where adPlanId = ?";
	private static final String DELETE = "DELETE FROM AdPlan where adPlanId = ?";
	private static final String UPDATE = "UPDATE AdPlan set adPlanName=?, adPlanStartDate=?, adPlanEndDate=?, adPlanPrice=?, adPlanRemainNo=? where adPlanId = ?";

	@Override
	public void insert(AdPlanVO aAdPlanVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(AdPlanDAO.INSERT_STMT);

			pstmt.setString(1, aAdPlanVO.getAdPlanName());
			pstmt.setDate(2, aAdPlanVO.getAdPlanStartDate());
			pstmt.setDate(3, aAdPlanVO.getAdPlanEndDate());
			pstmt.setInt(4, aAdPlanVO.getAdPlanPrice());
			pstmt.setInt(5, aAdPlanVO.getAdPlanRemainNo());
			

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
	public void update(AdPlanVO aAdPlanVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(AdPlanDAO.UPDATE);

			pstmt.setString(1, aAdPlanVO.getAdPlanName());
			pstmt.setDate(2, aAdPlanVO.getAdPlanStartDate());
			pstmt.setDate(3, aAdPlanVO.getAdPlanEndDate());
			pstmt.setInt(4, aAdPlanVO.getAdPlanPrice());
			pstmt.setInt(5, aAdPlanVO.getAdPlanRemainNo());
			pstmt.setString(6, aAdPlanVO.getAdPlanId());

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
	public void delete(String aAdPlanId) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(AdPlanDAO.DELETE);

			pstmt.setString(1, aAdPlanId);

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
	public AdPlanVO findByPrimaryKey(String aAdPlanId) {
		AdPlanVO adPlanVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(AdPlanDAO.GET_ONE_STMT);

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
		return adPlanVO;
	}

	@Override
	public List<AdPlanVO> getAll() {
		List<AdPlanVO> list = new ArrayList<AdPlanVO>();
		AdPlanVO adPlanVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(AdPlanDAO.GET_ALL_STMT);
			rs = pstmt.executeQuery();
			/*ResultSetMetaData rsmd = rs.getMetaData();
			int len = rsmd.getColumnCount();
			for (int i = 1; i <= len; i++) {
				System.out.println("rsmd.getColumnName(i)" + rsmd.getColumnName(i));
			}*/

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
