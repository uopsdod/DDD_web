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
import java.util.Arrays;
import java.util.List;

public class AdJDBCDAO implements AdDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "scott";
	String passwd = "tiger";
	
	private static final String INSERT_STMT = 
	"INSERT INTO Ad (adId,adAdPlanId,adHotelId,adStatus,adPayDate,adPic,adPicContent,adHit) VALUES (Ad_seq.NEXTVAL,?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT adId,adAdPlanId,adHotelId,adStatus,to_char(adPayDate,'yyyy-mm-dd') adPayDate,adPic,adPicContent,adHit FROM Ad order by adId";
		private static final String GET_ONE_STMT = 
			"SELECT adId,adAdPlanId,adHotelId,adStatus,to_char(adPayDate,'yyyy-mm-dd') adPayDate,adPic,adPicContent,adHit FROM Ad where adId = ?";
		private static final String DELETE = 
			"DELETE FROM Ad where AdId = ?";
		private static final String UPDATE = 
			"UPDATE Ad set adId=?, adAdPlanId=?, adStatus=?, adPayDate=?, adPic=?, adPicContent=?, adHit=? where AdId = ?";
	@Override
	public void insert(AdVO aAdVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url, this.userid, this.passwd);
			pstmt = con.prepareStatement(AdJDBCDAO.INSERT_STMT);

			pstmt.setString(1,aAdVO.getAdAdPlanId());
			pstmt.setString(2,aAdVO.getAdHotelId());
			pstmt.setString(3,aAdVO.getAdStatus());
			pstmt.setDate(4,aAdVO.getAdPayDate());
			pstmt.setBytes(5,aAdVO.getAdPic());
			pstmt.setString(6,aAdVO.getAdPicContent());
			pstmt.setInt(7,aAdVO. getAdHit());

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
	public void update(AdVO aAdVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(this.url, this.userid, this.passwd);
			pstmt = con.prepareStatement(AdJDBCDAO.UPDATE);

			
			pstmt.setString(1,aAdVO.getAdStatus());
			pstmt.setDate(2,aAdVO.getAdPayDate());
			pstmt.setBytes(3,aAdVO.getAdPic());
			pstmt.setString(4,aAdVO.getAdPicContent());
			pstmt.setInt(5,aAdVO. getAdHit());

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
	public void delete(String aAdId) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url, this.userid, this.passwd);
			pstmt = con.prepareStatement(AdJDBCDAO.DELETE);

			pstmt.setString(1, aAdId);

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
	public AdVO findByPrimaryKey(String aAdId) {
		AdVO AdVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url, this.userid, this.passwd);
			pstmt = con.prepareStatement(AdJDBCDAO.GET_ONE_STMT);

			pstmt.setString(1, aAdId);

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

			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url, this.userid, this.passwd);
			pstmt = con.prepareStatement(AdJDBCDAO.GET_ALL_STMT);
			rs = pstmt.executeQuery();
//			ResultSetMetaData rsmd = rs.getMetaData();
//			int len = rsmd.getColumnCount();
//			for (int i = 1; i <= len ; i++){
//				System.out.println("rsmd.getColumnName(i)" + rsmd.getColumnName(i));
//			}
			

			while (rs.next()) {
				// AdVO �]�٬� Domain objects
//				System.out.println("rs.getDate(\"adPayDate\"): " + rs.getDate("adPayDate"));
				AdVO = new AdVO();
				AdVO.setAdId(rs.getString("adId"));
				AdVO.setAdAdPlanId(rs.getString("adAdPlanId"));
				AdVO.setAdHotelId(rs.getString("adHotelId"));
				AdVO.setAdStatus(rs.getString("adStatus"));
				AdVO.setAdPayDate(rs.getDate("adPayDate"));
				AdVO.setAdPic(rs.getBytes("adPic"));
				AdVO.setAdPicContent(rs.getString("adPicContent"));
				AdVO.setAdHit(rs.getInt("adHit"));
				list.add(AdVO); // Store the row in the list
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
	public static void main(String[] args) throws Exception {
		File pic = new File("C:/tomcat/eclipse_WTP_WorkSpace1/Banner/src/Ad/panda.jpg");
		if (pic.exists()){
			System.out.println("panda here.");
		}else{
			System.out.println("panda not here");
		}
		InputStream fin = new FileInputStream(pic);
		
		byte[] byteAry = new byte[fin.available()];
		fin.read(byteAry);
		
		
		AdJDBCDAO dao = new AdJDBCDAO();

		// �s�W
		AdVO adVO1 = new AdVO();
		
		
		adVO1.setAdStatus("0");
		adVO1.setAdAdPlanId("10000001");
		adVO1.setAdHotelId("10002");
		adVO1.setAdPayDate(new Date(new java.util.Date().getTime()));
		adVO1.setAdPic(byteAry);
		adVO1.setAdPicContent("�}�G��6");
		adVO1.setAdHit(10);
		dao.insert(adVO1);

		// �ק�
//		AdVO adVO2 = new AdVO();
//		adVO2.setAdStatus("1");
//		adVO2.setAdPayDate(new Date(2016, 07, 07));
//		adVO2.setAdPic(byteAry);
//		adVO2.setAdPicContent("�}�G��");
//		adVO2.setAdHit(10);
//		dao.update(adVO2);

		// �R��
//     	dao.delete("1001");

		// �d��
//		AdVO AdVO3 = dao.findByPrimaryKey("1002");
//		System.out.print(AdVO3.getAdId() + ",");
//		System.out.print(AdVO3.getAdAdPlanId() + ",");
//		System.out.print(AdVO3.getAdHotelId() + ",");
//		System.out.print(AdVO3.getAdStatus() + ",");
//		System.out.print(AdVO3.getAdPayDate() + ",");
//		System.out.print(AdVO3.getAdPic()  + ",");
//		System.out.print(AdVO3.getAdPicContent()+",");
//		System.out.println(AdVO3.getAdHit());
//		System.out.println("---------------------");

		// �d��
		AdDAO_interface dao2 = new AdJDBCDAO();
		List<AdVO> list = dao2.getAll();
		for (AdVO aAd : list) {
			System.out.print(aAd.getAdId() + ",");
			System.out.print(aAd.getAdAdPlanId() + ",");
			System.out.print(aAd.getAdHotelId() + ",");
			System.out.print(aAd.getAdStatus() + ",");
			System.out.print(aAd.getAdPayDate()+ ",");			
			System.out.print(aAd.getAdPic() + ",");
			System.out.print(aAd.getAdPicContent()+",");
			System.out.println(aAd.getAdHit());
			System.out.println();
		}
	}

}
