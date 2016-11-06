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

public class AdPlanJNDIDAO implements AdPlanDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO Ad (adId,adAdPlanId,adHotelId,adStatus,adPayDate,adPic,adPicContent,adHit) VALUES (Ad_adId.NEXTVAL,Ad_adAdPlanId.NEXTVAL, adHotelId.NEXTVAL, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT adId,adAdPlanId,adHotelId,adStatus,to_char(adPayDate,'yyyy-mm-dd') adPayDate,adPic,adPicContent,adHit FROM Ad order by adId";
	private static final String GET_ONE_STMT = "SELECT adId,adAdPlanId,adHotelId,adStatus,to_char(adPayDate,'yyyy-mm-dd') adPayDate,adPic,adPicContent,adHit FROM Ad where adId = ?";
	private static final String DELETE = "DELETE FROM Ad where AdId = ?";
	private static final String UPDATE = "UPDATE Ad set adId=?, adAdPlanId=?, adStatus=?, adPayDate=?, adPic=?, adPicContent=?, adHit=? where AdId = ?";

	@Override
	public void insert(AdPlanVO aAdPlanVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(AdPlanJNDIDAO.INSERT_STMT);

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
			pstmt = con.prepareStatement(AdPlanJNDIDAO.UPDATE);

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
	public void delete(String aAdid) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(AdPlanJNDIDAO.DELETE);

			pstmt.setString(1, aAdid);

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
			pstmt = con.prepareStatement(AdPlanJNDIDAO.GET_ONE_STMT);

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
			pstmt = con.prepareStatement(AdPlanJNDIDAO.GET_ALL_STMT);
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int len = rsmd.getColumnCount();
			for (int i = 1; i <= len; i++) {
				System.out.println("rsmd.getColumnName(i)" + rsmd.getColumnName(i));
			}

			while (rs.next()) {
				// adPlanVO 也稱為 Domain objects
				System.out.println("rs.getDate(\"adPayDate\"): " + rs.getDate("adPayDate"));

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
