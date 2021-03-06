package com.wish.model;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class WishJNDIDAO implements WishDAO_interface {
	private static DataSource ds = null;
	static{
		try{
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		}catch(NamingException e){
			e.printStackTrace(System.err);
		}
	}

	private static String INSERT_STMT = "INSERT INTO wish (wishmemid,wishroomid) VALUES (?,?)";
	private static String GET_ONE_STMT = "SELECT wishMemId,wishRoomId FROM wish where wishMemId=? AND wishRoomId=?";
	private static String GET_ALL_STMT = "SElECT wishMemId,wishRoomId FROM wish order by wishMemId";
	private static String UPDATE = "UPDATE wish set wishRoomId = ? where wishMemId = ?";
	private static String DELETE = "DELETE FROM wish where wishMemId = ? AND  wishRoomid = ?";
	private static String GET_ALL_FOR_ONE = "SELECT * FROM wish where wishMemId = ?";//新方法，勿刪
	//-----------貴新增
	private static final String GET_ONE_WISH = "SELECT wishRoomId FROM wish where wishMemId=?";
	private static final String DELETE_ONE = "DELETE  from wish where wishMemId=? and WishRoomId =?";
	private static final String GET_ONE ="select o.roomPhotoRoomId,o.roomPhotoPic,h.hotelName,r.roomPrice,r.roomDisccountPercent,r.roomName,r.roomTotalNo,r.roomDiscountStartDate,r.roomDiscountEndDate,h.hotelRatingResult from wish w,room r,roomphoto o,hotel h where w.wishMemId=? and r.roomId = w.wishRoomId and r.roomid=o.roomPhotoRoomId and r.roomHotelId = h.hotelId";
	private static final Base64.Encoder encoder = Base64.getEncoder();
	
	@Override
	public void insert(WishVO aWishVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(WishJNDIDAO.INSERT_STMT);

			pstmt.setString(1, aWishVO.getWishMemId());
			pstmt.setString(2, aWishVO.getWishRoomId());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A databse error occured." + se.getMessage());
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
	public void updata(WishVO aWishVO) {

	}

	@Override
	public void delete(String aWishMemId, String aWishRoomId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(WishJNDIDAO.DELETE);

			pstmt.setString(1, aWishMemId);
			pstmt.setString(2, aWishRoomId);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
	public WishVO findByPrimaryKey(String aWishMemId, String aWishRoomId) {
		WishVO wishVO = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(WishJNDIDAO.GET_ONE_STMT);
			pstmt.setString(1, aWishMemId);
			pstmt.setString(2, aWishRoomId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				wishVO = new WishVO();

				wishVO.setWishMemId(rs.getString("wishMemId"));
				wishVO.setWishRoomId(rs.getString("wishRoomId"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
		return wishVO;
	}

	@Override
	public List<WishVO> getAll() {
		List<WishVO> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(WishJNDIDAO.GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				WishVO wishVO = new WishVO();

				wishVO.setWishMemId(rs.getString("wishMemId"));
				wishVO.setWishRoomId(rs.getString("wishRoomId"));

				list.add(wishVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
	public List<String> getAllWishRoomId(String aWishMemId) {
		List<String> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_FOR_ONE);
			pstmt.setString(1, aWishMemId);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()){
				list.add(rs.getString("wishRoomId"));
			}
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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


	public List<WishVO> getOneWishOfmem(String wishMemId) {
		List<WishVO> list = new ArrayList<WishVO>();
		WishVO wishVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_WISH);
			pstmt.setString(1, wishMemId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				wishVO = new WishVO();
				wishVO.setWishRoomId(rs.getString("wishRoomId"));

				list.add(wishVO);
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
	public void delete_one(String wishMemId,String WishRoomId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_ONE);

			pstmt.setString(1,wishMemId);
			pstmt.setString(2, WishRoomId);
			
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
	public List<Map> getOneWishOfmemNO(String wishMemId) {
		List<Map> list = new ArrayList<Map>();//LISTMAP
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);

			pstmt.setString(1,wishMemId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Map<Object,Object> map = new HashMap<Object,Object>();//MAP
				  byte[] roomPhotoPic = rs.getBytes("roomPhotoPic");

					//放入
				map.put("roomPhotoRoomId",rs.getString("roomPhotoRoomId"));
				map.put("roomPhotoPic",encoder.encodeToString(roomPhotoPic));
				map.put("hotelName",rs.getString("hotelName"));
				map.put("roomPrice",rs.getString("roomPrice"));
				map.put("roomDisccountPercent",rs.getInt("roomDisccountPercent"));
				map.put("roomName",rs.getString("roomName"));
				map.put("roomTotalNo",rs.getInt("roomTotalNo"));
				map.put("roomDiscountStartDate",rs.getInt("roomDiscountStartDate"));
				map.put("roomDiscountEndDate",rs.getInt("roomDiscountEndDate"));
				map.put("hotelRatingResult",rs.getInt("hotelRatingResult"));
				
				list.add(map);//加到回傳
			

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
