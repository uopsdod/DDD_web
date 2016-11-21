package com.ad.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.naming.*;
import javax.sql.DataSource;

public class AdDAO implements AdDAO_interface {
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
	private static final Base64.Encoder encoder = Base64.getEncoder();
//貴新增BS
	@Override
	public void insert(AdVO aAdVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(AdDAO.INSERT_STMT);

			pstmt.setString(1, aAdVO.getAdStatus());
			pstmt.setDate(2, aAdVO.getAdPayDate());
			pstmt.setBytes(3, aAdVO.getAdPic());
			pstmt.setString(4, aAdVO.getAdPicContent());
			pstmt.setInt(5, aAdVO.getAdHit());

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
	public void update(AdVO aAdVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(AdDAO.UPDATE);

			pstmt.setString(1, aAdVO.getAdStatus());
			pstmt.setDate(2, aAdVO.getAdPayDate());
			pstmt.setBytes(3, aAdVO.getAdPic());
			pstmt.setString(4, aAdVO.getAdPicContent());
			pstmt.setInt(5, aAdVO.getAdHit());

			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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
			pstmt = con.prepareStatement(AdDAO.DELETE);

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
	public AdVO findByPrimaryKey(String aAdid) {
		AdVO AdVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(AdDAO.GET_ONE_STMT);

			pstmt.setString(1, aAdid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// AdVO �]�٬� Domain objects
				AdVO = new AdVO();
				AdVO.setAdId(rs.getString("adId"));
				AdVO.setAdAdPlanId(rs.getString("adAdPlanId"));
				AdVO.setAdHotelId(rs.getString("adHotelId"));
				AdVO.setAdStatus(rs.getString("adStatus"));
				AdVO.setAdPayDate(rs.getDate("adPayDate"));
				AdVO.setAdPic(rs.getBytes("adPic"));
				AdVO.setAdPicContent(rs.getString("adPicContent"));
				AdVO.setAdHit(rs.getInt("adHit"));
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
		return AdVO;
	}

	@Override
	public List<AdVO> getAll() {
		List<AdVO> list = new ArrayList<AdVO>();
		AdVO AdVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(AdDAO.GET_ALL_STMT);
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int len = rsmd.getColumnCount();
			for (int i = 1; i <= len; i++) {
				System.out.println("rsmd.getColumnName(i)" + rsmd.getColumnName(i));
			}

			while (rs.next()) {
				// AdVO �]�٬� Domain objects
				System.out.println("rs.getDate(\"adPayDate\"): " + rs.getDate("adPayDate"));

				AdVO = new AdVO();
				AdVO.setAdId(rs.getString("adId"));
				AdVO.setAdAdPlanId(rs.getString("adAdPlanId"));
				AdVO.setAdHotelId(rs.getString("adHotelId"));
				AdVO.setAdStatus(rs.getString("adStatus"));
				AdVO.setAdPayDate(rs.getDate("adPayDate"));
				AdVO.setAdPic(rs.getBytes("adPic"));
				if(AdVO.getAdPic() == null){
					AdVO.setBs64("");
				} else {
					AdVO.setBs64(encoder.encodeToString(AdVO.getAdPic()));
				}
				//貴新增BS
				AdVO.setAdPicContent(rs.getString("adPicContent"));
				AdVO.setAdHit(rs.getInt("adHit"));
				list.add(AdVO); // Store the row in the list
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

//	public static void main(String[] args) throws Exception {
//		File pic = new File("C:/tomcat/eclipse_WTP_WorkSpace1/Banner/src/Ad/panda.jpg");
//		if (pic.exists()) {
//			System.out.println("panda here.");
//		} else {
//			System.out.println("panda not here");
//		}
//		InputStream fin = new FileInputStream(pic);
//
//		byte[] byteAry = new byte[fin.available()];
//		fin.read(byteAry);
//
//		AdDAO dao = new AdDAO();
//
//		// �s�W
//		AdVO adVO1 = new AdVO();
//
//		// �d��
//		AdVO AdVO3 = dao.findByPrimaryKey("10000001");
//		System.out.print(AdVO3.getAdId() + ",");
//		System.out.print(AdVO3.getAdAdPlanId() + ",");
//		System.out.print(AdVO3.getAdHotelId() + ",");
//		System.out.print(AdVO3.getAdStatus() + ",");
//		System.out.print(AdVO3.getAdPayDate() + ",");
//		System.out.print(AdVO3.getAdPic() + ",");
//		System.out.print(AdVO3.getAdPicContent() + ",");
//		System.out.println(AdVO3.getAdHit());
//		System.out.println("---------------------");
//
//		// �d��
//		AdDAO_interface dao2 = new AdJDBCDAO();
//		dao2.getAll();
//		List<AdVO> list = dao2.getAll();
//		for (AdVO aAd : list) {
//			System.out.print(aAd.getAdId() + ",");
//			System.out.print(aAd.getAdAdPlanId() + ",");
//			System.out.print(aAd.getAdHotelId() + ",");
//			System.out.print(aAd.getAdStatus() + ",");
//			System.out.print(aAd.getAdPayDate() + ",");
//			System.out.print(aAd.getAdPic() + ",");
//			System.out.print(aAd.getAdPicContent() + ",");
//			System.out.println(aAd.getAdHit());
//			System.out.println();
//		}
//	}
}
