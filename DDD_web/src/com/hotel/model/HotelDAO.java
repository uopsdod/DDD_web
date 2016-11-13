package com.hotel.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.emp.model.EmpVO;
import com.ord.model.OrdVO;

public class HotelDAO implements HotelDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String GET_ALL_STMT = "select hotelId,hotelType,hotelName,hotelTaxId,hotelRegisterPic"
			+ ",hotelCity,hotelCounty,hotelRoad,hotelOwner,hotelAccount,hotelPwd,hotelPhone,hotelLon,hotelLat,"
			+ "hotelIntro,hotelCoverPic,hotelLink,hotelStatus,hotelBlackList,hotelRatingTotal,hotelRatingResult,"
			+ "hotelCreditCardNo,hotelCreditCheckNo,hotelCreditDueDate from hotel order by hotelId";
	private static final String GET_ALL_CHECK = "select hotelId,hotelType,hotelName,hotelTaxId,hotelRegisterPic"
			+ ",hotelCity,hotelCounty,hotelRoad,hotelOwner,hotelAccount,hotelPwd,hotelPhone,hotelLon,hotelLat,"
			+ "hotelIntro,hotelCoverPic,hotelLink,hotelStatus,hotelBlackList,hotelRatingTotal,hotelRatingResult,"
			+ "hotelCreditCardNo,hotelCreditCheckNo,hotelCreditDueDate from hotel where hotelStatus='0' order by hotelId";
	private static final String GET_ALL_USER = "select hotelId,hotelType,hotelName,hotelTaxId,hotelRegisterPic"
			+ ",hotelCity,hotelCounty,hotelRoad,hotelOwner,hotelAccount,hotelPwd,hotelPhone,hotelLon,hotelLat,"
			+ "hotelIntro,hotelCoverPic,hotelLink,hotelStatus,hotelBlackList,hotelRatingTotal,hotelRatingResult,"
			+ "hotelCreditCardNo,hotelCreditCheckNo,hotelCreditDueDate from hotel where hotelAccount=?";
	private static final String GET_ALL_VIEW ="select hotelId,hotelType,hotelName,hotelTaxId,hotelRegisterPic,"
			+ "hotelCity,hotelCounty,hotelRoad,hotelOwner,hotelPhone from hotel order by hotelId";
	private static final String INSERT_STMT = "INSERT INTO hotel (hotelId,hotelType,hotelName,hotelTaxId,"
			+ "hotelRegisterPic,hotelCity,hotelCounty,hotelRoad,hotelOwner,hotelAccount,hotelPwd,hotelPhone,"
			+ "hotelLon,hotelLat,hotelIntro,hotelCoverPic,hotelLink,hotelStatus,hotelBlackList,hotelRatingTotal,"
			+ "hotelRatingResult,hotelCreditCardNo,hotelCreditCheckNo,hotelCreditDueDate) "
			+ "VALUES (hotel_seq.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_BASIC = "UPDATE hotel set  hotelType=?, hotelName=?, hotelTaxId=?, hotelRegisterPic=?"
			+ ",hotelCity=?,hotelCounty=?, hotelRoad=? ,hotelOwner=?,hotelAccount=?,hotelPhone=? "
			+ ",hotelLon=?,hotelLat=?,hotelIntro=?,hotelCoverPic=?,hotelLink=?,hotelCreditCardNo=?,hotelCreditCheckNo=?"
			+ ",hotelCreditDueDate=? where hotelId = ?";
	private static final String UPDATE_BASIC_OF_STU = "UPDATE hotel set  hotelType=?, hotelName=?, hotelTaxId=?, hotelRegisterPic=?"
			+ ",hotelCity=?,hotelCounty=?, hotelRoad=? ,hotelOwner=?,hotelAccount=?,hotelPhone=? "
			+ ",hotelLon=?,hotelLat=?,hotelIntro=?,hotelCoverPic=?,hotelLink=?,hotelCreditCardNo=?,hotelCreditCheckNo=?"
			+ ",hotelCreditDueDate=?,hotelStatus=? where hotelId = ?";
	private static final String UPDATE_STATUS = "UPDATE hotel set hotelStatus=? where hotelId = ?";
	private static final String UPDATE_PSW = "UPDATE hotel set hotelPwd=? where hotelId = ?";
	private static final String UPDATE_HOTELBLACKLIST = "UPDATE hotel set hotelBlackList=? where hotelId = ?";
	public static final String GET_ONE_STMT = "select hotelId,hotelType,hotelName,hotelTaxId,hotelRegisterPic"
			+ ",hotelCity,hotelCounty,hotelRoad,hotelOwner,hotelAccount,hotelPwd,hotelPhone,hotelLon,hotelLat,"
			+ "hotelIntro,hotelCoverPic,hotelLink,hotelStatus,hotelBlackList,hotelRatingTotal,hotelRatingResult,"
			+ "hotelCreditCardNo,hotelCreditCheckNo,hotelCreditDueDate from hotel where hotelId = ?";
	private static final String GET_PHOTO_REGISTER ="SELECT hotelRegisterPic from hotel where hotelId=?";//廠商正
	private static final String GET_PHOTO_COV ="SELECT hotelCoverPic from hotel where hotelId=?";//封面
	private static final Base64.Encoder encoder = Base64.getEncoder();
	private static final Base64.Encoder encoder1 = Base64.getEncoder();
	public static final String CHECK_MEMBER = "SELECT hotelAccount, hotelPwd FROM hotel where hotelAccount = ?";
	
	/*下面是韓哥需要的*/
	private static final String GET_ORDS_BYHOTELID_STMT = "SELECT ordID,ordRoomId,"
			+"ordMemId,ordHotelId,ordPrice, ordLiveDate, ordDate,ordStatus,ordRatingContent,"
			+"ordRatingStarNo,ordMsgNo FROM ord where OrdHotelId = ? order by ordID DESC";		
	
	
	@Override
	public List<HotelVO> getAll() {
		List<HotelVO> list = new ArrayList<HotelVO>();
		HotelVO hotelVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_CHECK);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				hotelVO = new HotelVO();
				hotelVO.setHotelId(rs.getString("hotelId"));
				hotelVO.setHotelType(rs.getString("hotelType"));
				hotelVO.setHotelName(rs.getString("hotelName"));
				hotelVO.setHotelTaxId(rs.getString("hotelTaxId"));
				hotelVO.setHotelRegisterPic(rs.getBytes("hotelRegisterPic"));
				if(hotelVO.getHotelRegisterPic() == null){
					hotelVO.setBs64("");
				} else {
					hotelVO.setBs64(encoder.encodeToString(hotelVO.getHotelRegisterPic()));
				}
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
				if(hotelVO.getHotelCoverPic() == null){
					hotelVO.setBs64_2("");
				} else {
					hotelVO.setBs64_2(encoder1.encodeToString(hotelVO.getHotelCoverPic()));
				}
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
	public List<HotelVO> getAll_TO_VIEW() {
		List<HotelVO> list = new ArrayList<HotelVO>();
		HotelVO hotelVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_VIEW);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				hotelVO = new HotelVO();
				hotelVO.setHotelId(rs.getString("hotelId"));
				hotelVO.setHotelType(rs.getString("hotelType"));
				hotelVO.setHotelName(rs.getString("hotelName"));
				hotelVO.setHotelTaxId(rs.getString("hotelTaxId"));
				hotelVO.setHotelRegisterPic(rs.getBytes("hotelRegisterPic"));
				if(hotelVO.getHotelRegisterPic() == null){
					hotelVO.setBs64("");
				} else {
					hotelVO.setBs64(encoder.encodeToString(hotelVO.getHotelRegisterPic()));
				}
				hotelVO.setHotelCity(rs.getString("hotelCity"));
				hotelVO.setHotelCounty(rs.getString("hotelCounty"));
				hotelVO.setHotelRoad(rs.getString("hotelRoad"));
				hotelVO.setHotelOwner(rs.getString("hotelOwner"));
				hotelVO.setHotelPhone(rs.getString("hotelPhone"));
				
				list.add(hotelVO);
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
	
	@Override
	public void insert(HotelVO aHotelVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
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
			con = ds.getConnection();
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
//			pstmt.setString(10, aHotelVO.getHotelPwd());
			pstmt.setString(10, aHotelVO.getHotelPhone());
			pstmt.setDouble(11, aHotelVO.getHotelLon());
			pstmt.setDouble(12, aHotelVO.getHotelLat());
			pstmt.setString(13, aHotelVO.getHotelIntro());
			pstmt.setBytes(14, aHotelVO.getHotelCoverPic());
			pstmt.setString(15, aHotelVO.getHotelLink());
			pstmt.setString(16, aHotelVO.getHotelCreditCardNo());
			pstmt.setString(17, aHotelVO.getHotelCreditCheckNo());
			pstmt.setString(18, aHotelVO.getHotelCreditDueDate());
			pstmt.setString(19, aHotelVO.getHotelId());

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
	public void update_status_1(HotelVO aHotelVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_BASIC_OF_STU);

			pstmt.setString(1, aHotelVO.getHotelType());
			pstmt.setString(2, aHotelVO.getHotelName());
			pstmt.setString(3, aHotelVO.getHotelTaxId());
			pstmt.setBytes(4, aHotelVO.getHotelRegisterPic());
			pstmt.setString(5, aHotelVO.getHotelCity());
			pstmt.setString(6, aHotelVO.getHotelCounty());
			pstmt.setString(7, aHotelVO.getHotelRoad());
			pstmt.setString(8, aHotelVO.getHotelOwner());
			pstmt.setString(9, aHotelVO.getHotelAccount());
//			pstmt.setString(10, aHotelVO.getHotelPwd());
			pstmt.setString(10, aHotelVO.getHotelPhone());
			pstmt.setDouble(11, aHotelVO.getHotelLon());
			pstmt.setDouble(12, aHotelVO.getHotelLat());
			pstmt.setString(13, aHotelVO.getHotelIntro());
			pstmt.setBytes(14, aHotelVO.getHotelCoverPic());
			pstmt.setString(15, aHotelVO.getHotelLink());
			pstmt.setString(16, aHotelVO.getHotelCreditCardNo());
			pstmt.setString(17, aHotelVO.getHotelCreditCheckNo());
			pstmt.setString(18, aHotelVO.getHotelCreditDueDate());
			pstmt.setString(18, aHotelVO.getHotelCreditDueDate());
			pstmt.setString(19, aHotelVO.getHotelStatus());
			pstmt.setString(20, aHotelVO.getHotelId());

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
	public void update_status(String hotelId, String hotelStatus) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATUS);

			pstmt.setString(1, hotelStatus);
			pstmt.setString(2, hotelId);
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
	public void update_hotelBlackList(String hotelId, String hotelBlackList) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_HOTELBLACKLIST);

			pstmt.setString(1, hotelBlackList);
			pstmt.setString(2, hotelId);
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
	public HotelVO findByPrimaryKey(String aHotelId) {
		HotelVO hotelVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, aHotelId);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				hotelVO = new HotelVO();
				hotelVO.setHotelId(rs.getString("hotelId"));
				hotelVO.setHotelType(rs.getString("hotelType"));
				hotelVO.setHotelName(rs.getString("hotelName"));
				hotelVO.setHotelTaxId(rs.getString("hotelTaxId"));
				hotelVO.setHotelRegisterPic(rs.getBytes("hotelRegisterPic"));
				if(hotelVO.getHotelRegisterPic() == null){
					hotelVO.setBs64("");
				} else {
					hotelVO.setBs64(encoder.encodeToString(hotelVO.getHotelRegisterPic()));
				}
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
				if(hotelVO.getHotelCoverPic() == null){
					hotelVO.setBs64_2("");
				} else {
					hotelVO.setBs64_2(encoder1.encodeToString(hotelVO.getHotelCoverPic()));
				}
				hotelVO.setHotelLink(rs.getString("hotelLink"));
				hotelVO.setHotelStatus(rs.getString("hotelStatus"));
				hotelVO.setHotelBlackList(rs.getString("hotelBlackList"));
				hotelVO.setHotelRatingTotal(rs.getInt("hotelRatingTotal"));
				hotelVO.setHotelRatingResult(rs.getInt("hotelRatingResult"));
				hotelVO.setHotelCreditCardNo(rs.getString("hotelCreditCardNo"));
				hotelVO.setHotelCreditCheckNo(rs.getString("hotelCreditCheckNo"));
				hotelVO.setHotelCreditDueDate(rs.getString("hotelCreditDueDate"));
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
		return hotelVO;
	}

	
	@Override
	public byte[] getPhoto_cov(String aHotelId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] hotelCoverPic=null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PHOTO_COV);
			pstmt.setString(1, aHotelId);
			rs = pstmt.executeQuery();
			rs.next();
			hotelCoverPic=rs.getBytes("hotelCoverPic");
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
			
			return hotelCoverPic;
		}

	@Override
	public byte[] getPhoto_register(String aHotelId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] hotelRegisterPic=null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PHOTO_REGISTER);
			pstmt.setString(1, aHotelId);
			rs = pstmt.executeQuery();
			rs.next();
			hotelRegisterPic=rs.getBytes("hotelRegisterPic");
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
			
			return hotelRegisterPic;
		}

	@Override
	public void update_psw(String hotelPwd,String hotelId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PSW);	
			pstmt.setString(1, hotelPwd);
			pstmt.setString(2, hotelId);
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
	public HotelVO getUser(String aAccount) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HotelVO hotelVO = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_USER);
			pstmt.setString(1, aAccount);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				
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
			return hotelVO;
		}

	/*下面是韓哥需要的*/
	@Override
	 public Set<OrdVO> getOrdsByHotelId(String aHotelId){
		
		Set<OrdVO> set = new LinkedHashSet<OrdVO>();
		OrdVO ordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(HotelDAO.GET_ORDS_BYHOTELID_STMT);
			pstmt.setString(1, aHotelId);
			rs = pstmt.executeQuery();

			while(rs.next()){
				ordVO = new OrdVO();
				ordVO.setOrdId(rs.getString("ordID"));
							
				com.room.model.RoomVO roomVO = new com.room.model.RoomVO();
				roomVO.setRoomId(rs.getString("ordRoomId"));
				ordVO.setOrdRoomVO(roomVO);
				
				com.mem.model.MemVO memVO = new com.mem.model.MemVO();
				memVO.setMemId(rs.getString("ordMemId"));
				ordVO.setOrdMemVO(memVO);

				com.hotel.model.HotelVO hotelVO = new com.hotel.model.HotelVO();
				hotelVO.setHotelId(rs.getString("ordHotelId"));
				ordVO.setOrdHotelVO(hotelVO);				
				
				
				ordVO.setOrdPrice(rs.getInt("ordPrice"));
				ordVO.setOrdLiveDate(rs.getTimestamp("ordLiveDate"));
				ordVO.setOrdDate(rs.getTimestamp("ordDate"));
				ordVO.setOrdStatus(rs.getString("ordStatus"));
				ordVO.setOrdRatingContent(rs.getString("ordRatingContent"));
				ordVO.setOrdRatingStarNo(rs.getInt("ordRatingStarNo"));
				ordVO.setOrdMsgNo(rs.getString("ordMsgNo"));
				//ordVO.setOrdQrPic(rs.getBytes("ordQrPic"));
				set.add(ordVO);
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
		return set;		
		
	 }
	/*勿動感恩*/
	@Override
	public HotelVO hotelMemCheck(String aHotelAccount, String aHotelPwd) {
		HotelVO hotelVO = new HotelVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(CHECK_MEMBER);
			pstmt.setString(1, aHotelAccount);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				hotelVO.setHotelAccount(rs.getString("hotelAccount"));
				hotelVO.setHotelPwd(rs.getString("hotelPwd"));
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
		return hotelVO;
	}

}

