package com.hotel.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HotelJDBCDAO implements HotelDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "Alen";
	String passwd = "alen27";
	private static final String GET_ALL_STMT = "select hotelId,hotelType,hotelName,hotelTaxId,hotelRegisterPic"
			+ ",hotelCity,hotelCounty,hotelRoad,hotelOwner,hotelAccount,hotelPwd,hotelPhone,hotelLon,hotelLat,"
			+ "hotelIntro,hotelCoverPic,hotelLink,hotelStatus,hotelBlackList,hotelRatingTotal,hotelRatingResult,"
			+ "hotelCreditCardNo,hotelCreditCheckNo,hotelCreditDueDate from hotel order by hotelId";
	private static final String GET_ALL_CHECK = "select hotelId,hotelType,hotelName,hotelTaxId,hotelRegisterPic"
			+ ",hotelCity,hotelCounty,hotelRoad,hotelOwner,hotelAccount,hotelPwd,hotelPhone,hotelLon,hotelLat,"
			+ "hotelIntro,hotelCoverPic,hotelLink,hotelStatus,hotelBlackList,hotelRatingTotal,hotelRatingResult,"
			+ "hotelCreditCardNo,hotelCreditCheckNo,hotelCreditDueDate from hotel where hotelStatus='0' order by hotelId";
	private static final String INSERT_STMT = "INSERT INTO hotel (hotelId,hotelType,hotelName,hotelTaxId,"
			+ "hotelRegisterPic,hotelCity,hotelCounty,hotelRoad,hotelOwner,hotelAccount,hotelPwd,hotelPhone,"
			+ "hotelLon,hotelLat,hotelIntro,hotelCoverPic,hotelLink,hotelStatus,hotelBlackList,hotelRatingTotal,"
			+ "hotelRatingResult,hotelCreditCardNo,hotelCreditCheckNo,hotelCreditDueDate) "
			+ "VALUES (hotel_seq.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_BASIC = "UPDATE hotel set  hotelType=?, hotelName=?, hotelTaxId=?, hotelRegisterPic=?"
			+ ",hotelCity=?,hotelCounty=?, hotelRoad=? ,hotelOwner=?,hotelAccount=?,hotelPwd=?,hotelPhone=? "
			+ ",hotelLon=?,hotelLat=?,hotelIntro=?,hotelCoverPic=?,hotelLink=?,hotelCreditCardNo=?,hotelCreditCheckNo=?"
			+ ",hotelCreditDueDate=? where hotelId = ?";
	private static final String UPDATE_STATUS = "UPDATE hotel set hotelStatus=? where hotelId = ?";
	private static final String UPDATE_HOTELBLACKLIST = "UPDATE hotel set hotelBlackList=? where hotelId = ?";

	@Override
	public List<HotelVO> getAll() {
		List<HotelVO> list = new ArrayList<HotelVO>();
		HotelVO hotelVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				hotelVO = new HotelVO();
				hotelVO.setHotelId(rs.getString("hotelId"));
				hotelVO.setHotelType(rs.getString("hotelType"));
				hotelVO.setHotelName(rs.getString("hotelName"));
				hotelVO.setHotelTaxId(rs.getString("hotelTaxId"));
				hotelVO.setHotelRegisterPic(rs.getBytes("hotelRegisterPic"));
				hotelVO.setHotelCity(rs.getString("hotelCity"));
				hotelVO.setHotelCounty(rs.getString("hotelCounty"));
				hotelVO.setHotelRoad(rs.getString("hotelRoad"));
				hotelVO.setHotelOwner(rs.getString("hotelOwner"));
				hotelVO.setHotelAccount(rs.getString("hotelAccount"));
				hotelVO.setHotelPwd(rs.getString("hotelPwd"));
				hotelVO.setHotelPhone(rs.getString("hotelPhone"));
				hotelVO.setHotelLon(rs.getDouble("hotelLon"));
				hotelVO.setHotelLat(rs.getDouble("hotelLat"));
				hotelVO.setHotelIntro(rs.getString("hotelIntro"));
				hotelVO.setHotelCoverPic(rs.getBytes("hotelCoverPic"));
				hotelVO.setHotelLink(rs.getString("hotelLink"));
				hotelVO.setHotelStatus(rs.getString("hotelStatus"));
				hotelVO.setHotelBlackList(rs.getString("hotelBlackList"));
				hotelVO.setHotelRatingTotal(rs.getInt("hotelRatingTotal"));
				hotelVO.setHotelRatingResult(rs.getInt("hotelRatingResult"));
				hotelVO.setHotelCreditCardNo(rs.getString("hotelCreditCardNo"));
				hotelVO.setHotelCreditCheckNo(rs.getString("hotelCreditCheckNo"));
				hotelVO.setHotelCreditDueDate(rs.getString("hotelCreditDueDate"));
				list.add(hotelVO); 
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
	public List<HotelVO> getAll_NEED_CHECK() {
		List<HotelVO> list = new ArrayList<HotelVO>();
		HotelVO hotelVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_CHECK);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				hotelVO = new HotelVO();
				hotelVO.setHotelId(rs.getString("hotelId"));
				hotelVO.setHotelType(rs.getString("hotelType"));
				hotelVO.setHotelName(rs.getString("hotelName"));
				hotelVO.setHotelTaxId(rs.getString("hotelTaxId"));
				hotelVO.setHotelRegisterPic(rs.getBytes("hotelRegisterPic"));
				hotelVO.setHotelCity(rs.getString("hotelCity"));
				hotelVO.setHotelCounty(rs.getString("hotelCounty"));
				hotelVO.setHotelRoad(rs.getString("hotelRoad"));
				hotelVO.setHotelOwner(rs.getString("hotelOwner"));
				hotelVO.setHotelAccount(rs.getString("hotelAccount"));
				hotelVO.setHotelPwd(rs.getString("hotelPwd"));
				hotelVO.setHotelPhone(rs.getString("hotelPhone"));
				hotelVO.setHotelLon(rs.getDouble("hotelLon"));
				hotelVO.setHotelLat(rs.getDouble("hotelLat"));
				hotelVO.setHotelIntro(rs.getString("hotelIntro"));
				hotelVO.setHotelCoverPic(rs.getBytes("hotelCoverPic"));
				hotelVO.setHotelLink(rs.getString("hotelLink"));
				hotelVO.setHotelStatus(rs.getString("hotelStatus"));
				hotelVO.setHotelBlackList(rs.getString("hotelBlackList"));
				hotelVO.setHotelRatingTotal(rs.getInt("hotelRatingTotal"));
				hotelVO.setHotelRatingResult(rs.getInt("hotelRatingResult"));
				hotelVO.setHotelCreditCardNo(rs.getString("hotelCreditCardNo"));
				hotelVO.setHotelCreditCheckNo(rs.getString("hotelCreditCheckNo"));
				hotelVO.setHotelCreditDueDate(rs.getString("hotelCreditDueDate"));
				list.add(hotelVO); 
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
	public void insert(HotelVO aHotelVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, aHotelVO.getHotelType());
			pstmt.setString(2, aHotelVO.getHotelName());
			pstmt.setString(3, aHotelVO.getHotelTaxId());
			pstmt.setBytes(4, aHotelVO.getHotelRegisterPic());
			pstmt.setString(5, aHotelVO.getHotelCity());
			pstmt.setString(6, aHotelVO.getHotelCounty());
			pstmt.setString(7, aHotelVO.getHotelRoad());
			pstmt.setString(8, aHotelVO.getHotelOwner());
			pstmt.setString(9, aHotelVO.getHotelAccount());
			pstmt.setString(10, aHotelVO.getHotelPwd());
			pstmt.setString(11, aHotelVO.getHotelPhone());
			pstmt.setDouble(12, aHotelVO.getHotelLon());
			pstmt.setDouble(13, aHotelVO.getHotelLat());
			pstmt.setString(14, aHotelVO.getHotelIntro());
			pstmt.setBytes(15, aHotelVO.getHotelCoverPic());
			pstmt.setString(16, aHotelVO.getHotelLink());
			pstmt.setString(17, aHotelVO.getHotelStatus());
			pstmt.setString(18, aHotelVO.getHotelBlackList());
			pstmt.setInt(19, aHotelVO.getHotelRatingTotal());
			pstmt.setInt(20, aHotelVO.getHotelRatingResult());
			pstmt.setString(21, aHotelVO.getHotelCreditCardNo());
			pstmt.setString(22, aHotelVO.getHotelCreditCheckNo());
			pstmt.setString(23, aHotelVO.getHotelCreditDueDate());

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
	public void update(HotelVO aHotelVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_BASIC);

			pstmt.setString(1, aHotelVO.getHotelType());
			pstmt.setString(2, aHotelVO.getHotelName());
			pstmt.setString(3, aHotelVO.getHotelTaxId());
			pstmt.setBytes(4, aHotelVO.getHotelRegisterPic());
			pstmt.setString(5, aHotelVO.getHotelCity());
			pstmt.setString(6, aHotelVO.getHotelCounty());
			pstmt.setString(7, aHotelVO.getHotelRoad());
			pstmt.setString(8, aHotelVO.getHotelOwner());
			pstmt.setString(9, aHotelVO.getHotelAccount());
			pstmt.setString(10, aHotelVO.getHotelPwd());
			pstmt.setString(11, aHotelVO.getHotelPhone());
			pstmt.setDouble(12, aHotelVO.getHotelLon());
			pstmt.setDouble(13, aHotelVO.getHotelLat());
			pstmt.setString(14, aHotelVO.getHotelIntro());
			pstmt.setBytes(15, aHotelVO.getHotelCoverPic());
			pstmt.setString(16, aHotelVO.getHotelLink());
			pstmt.setString(17, aHotelVO.getHotelCreditCardNo());
			pstmt.setString(18, aHotelVO.getHotelCreditCheckNo());
			pstmt.setString(19, aHotelVO.getHotelCreditDueDate());
			pstmt.setString(20, aHotelVO.getHotelId());

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
	public void update_status(String hotelId, HotelVO aHotelVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STATUS);

			pstmt.setString(1, aHotelVO.getHotelStatus());
			pstmt.setString(2, hotelId);
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
	public void update_hotelBlackList(String hotelId, HotelVO aHotelVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_HOTELBLACKLIST);

			pstmt.setString(1, aHotelVO.getHotelBlackList());
			pstmt.setString(2, hotelId);
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
		HotelJDBCDAO dao = new HotelJDBCDAO();

		// UPDATE_HotelBlackList
//		HotelVO hotelVo3 = new HotelVO();
//		hotelVo3.setHotelBlackList("1");
//		dao.update_hotelBlackList("10001", hotelVo3);

		// UPDATE_STATUS
//		 HotelVO hotelVo2 = new HotelVO();
//		 hotelVo2.setHotelStatus("1");
//		 dao.update_status("10001", hotelVo2);

		// UPDATE_BASIC
//		 HotelVO hotelVO1 = new HotelVO();
//		 hotelVO1.setHotelType("汽車旅館");
//		 hotelVO1.setHotelName("成旅晶贊飯店-台北蘆洲");
//		 hotelVO1.setHotelTaxId("65448875");
//		 hotelVO1.setHotelRegisterPic(new byte[1]);
//		 hotelVO1.setHotelCity("台北市");
//		 hotelVO1.setHotelCounty("蘆洲區");
//		 hotelVO1.setHotelRoad("中山一路8號");
//		 hotelVO1.setHotelOwner("張西西");
//		 hotelVO1.setHotelAccount("house3@gmail.com");
//		 hotelVO1.setHotelPwd("123456789");
//		 hotelVO1.setHotelPhone("0927887665");
//		 hotelVO1.setHotelLon(121.480265);
//		 hotelVO1.setHotelLat(25.080521);
//		 hotelVO1.setHotelIntro("這家四星級禁菸酒店提供免費Wi-Fi和兩處餐飲場所");
//		 hotelVO1.setHotelCoverPic(new byte[1]);
//		 hotelVO1.setHotelLink("https://www.booking.com/");
//		 hotelVO1.setHotelCreditCardNo("4705435625453652");
//		 hotelVO1.setHotelCreditCheckNo("017");
//		 hotelVO1.setHotelCreditDueDate("2021-09");
//		 hotelVO1.setHotelId("10004");
//		 dao.update(hotelVO1);

		// //insert
//		 HotelVO hotelVO = new HotelVO();
//		 hotelVO.setHotelType("度假村");
//		 hotelVO.setHotelName("龍湯溫泉度假村");
//		 hotelVO.setHotelTaxId("64558878");
//		 hotelVO.setHotelRegisterPic(new byte[10]);
//		 hotelVO.setHotelCity("桃園市");
//		 hotelVO.setHotelCounty("中壢區");
//		 hotelVO.setHotelRoad("民族路二段551巷33號");
//		 hotelVO.setHotelOwner("張永");
//		 hotelVO.setHotelAccount("house3@gmail.com");
//		 hotelVO.setHotelPwd("123456789");
//		 hotelVO.setHotelPhone("0925990128");
//		 hotelVO.setHotelLon(121.154512);
//		 hotelVO.setHotelLat(25.546133);
//		 hotelVO.setHotelIntro("位於桃園鬧區，歡迎入住");
//		 hotelVO.setHotelCoverPic(new byte[10]);
//		 hotelVO.setHotelLink("https://www.agoda.com/zh-tw/city/taipei-tw.html");
//		 hotelVO.setHotelStatus("0");
//		 hotelVO.setHotelBlackList("0");
//		 hotelVO.setHotelRatingTotal(0);
//		 hotelVO.setHotelRatingResult(0);
//		 hotelVO.setHotelCreditCardNo("3801245812546698");
//		 hotelVO.setHotelCreditCheckNo("308");
//		 hotelVO.setHotelCreditDueDate("2021-09");
//		 dao.insert(hotelVO);
		
		//getAll_NEED_CHECK
//		List<HotelVO> list1 = dao.getAll_NEED_CHECK();
//		for (HotelVO aHotel : list1) {
//			System.out.print(aHotel.getHotelId() + ",");
//			System.out.print(aHotel.getHotelType() + ",");
//			System.out.print(aHotel.getHotelName() + ",");
//			System.out.print(aHotel.getHotelTaxId() + ",");
//			System.out.print(aHotel.getHotelRegisterPic() + ",");
//			System.out.print(aHotel.getHotelCity() + ",");
//			System.out.print(aHotel.getHotelCounty() + ",");
//			System.out.print(aHotel.getHotelRoad() + ",");
//			System.out.print(aHotel.getHotelOwner() + ",");
//			System.out.print(aHotel.getHotelAccount() + ",");
//			System.out.print(aHotel.getHotelPwd() + ",");
//			System.out.print(aHotel.getHotelPhone() + ",");
//			System.out.print(aHotel.getHotelLon() + ",");
//			System.out.print(aHotel.getHotelLat() + ",");
//			System.out.print(aHotel.getHotelIntro() + ",");
//			System.out.print(aHotel.getHotelCoverPic() + ",");
//			System.out.print(aHotel.getHotelLink() + ",");
//			System.out.print(aHotel.getHotelStatus() + ",");
//			System.out.print(aHotel.getHotelBlackList() + ",");
//			System.out.print(aHotel.getHotelRatingTotal() + ",");
//			System.out.print(aHotel.getHotelRatingResult() + ",");
//			System.out.print(aHotel.getHotelCreditCardNo() + ",");
//			System.out.print(aHotel.getHotelCreditCheckNo() + ",");
//			System.out.print(aHotel.getHotelCreditDueDate() + ",");
//			System.out.println();
//		}
		
		
		// select
		List<HotelVO> list = dao.getAll();
		for (HotelVO aHotel : list) {
			System.out.print(aHotel.getHotelId() + ",");
			System.out.print(aHotel.getHotelType() + ",");
			System.out.print(aHotel.getHotelName() + ",");
			System.out.print(aHotel.getHotelTaxId() + ",");
			System.out.print(aHotel.getHotelRegisterPic() + ",");
			System.out.print(aHotel.getHotelCity() + ",");
			System.out.print(aHotel.getHotelCounty() + ",");
			System.out.print(aHotel.getHotelRoad() + ",");
			System.out.print(aHotel.getHotelOwner() + ",");
			System.out.print(aHotel.getHotelAccount() + ",");
			System.out.print(aHotel.getHotelPwd() + ",");
			System.out.print(aHotel.getHotelPhone() + ",");
			System.out.print(aHotel.getHotelLon() + ",");
			System.out.print(aHotel.getHotelLat() + ",");
			System.out.print(aHotel.getHotelIntro() + ",");
			System.out.print(aHotel.getHotelCoverPic() + ",");
			System.out.print(aHotel.getHotelLink() + ",");
			System.out.print(aHotel.getHotelStatus() + ",");
			System.out.print(aHotel.getHotelBlackList() + ",");
			System.out.print(aHotel.getHotelRatingTotal() + ",");
			System.out.print(aHotel.getHotelRatingResult() + ",");
			System.out.print(aHotel.getHotelCreditCardNo() + ",");
			System.out.print(aHotel.getHotelCreditCheckNo() + ",");
			System.out.print(aHotel.getHotelCreditDueDate() + ",");
			System.out.println();
		}

	}

}
