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

public class WishDAO implements WishDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");

		} catch (NamingException e) {
			e.printStackTrace();

		}
	}

	private static String INSERT_STMT = "INSERT INTO wish (wishmemid,wishroomid) VALUES (?,?)";
	private static String GET_ONE_STMT = "SELECT wishMemId,wishRoomId FROM wish where wishMemId=? AND wishRoomId=?";
	private static String GET_ALL_STMT = "SElECT wishMemId,wishRoomId FROM wish order by wishMemId";
	private static String UPDATE = "UPDATE wish set wishRoomId = ? where wishMemId = ?";
	private static String DELETE = "DELETE FROM wish where wishMemId = ? AND  wishRoomid = ?";
	private static String GET_ALL_FOR_ONE = "SELECT * FROM wish where wishMemId = ?";//新方法，勿刪
	//------------------------------------------貴新增
	private static final String GET_ONE_WISH = "SELECT wishRoomId FROM wish where wishMemId=?";
	private static final String DELETE_ONE = "DELETE  from wish where wishMemId=? and WishRoomId =?";
	private static final String GET_ONE ="select o.roomPhotoRoomId,o.roomPhotoPic,h.hotelid,h.hotelName,r.roomPrice,r.roomid,r.roomDisccountPercent,r.roomName,r.roomRemainNo,r.roomDiscountStartDate,r.roomDiscountEndDate,h.hotelRatingResult from wish w,room r,roomphoto o,hotel h where w.wishMemId=? and r.roomId = w.wishRoomId and r.roomid=o.roomPhotoRoomId and r.roomHotelId = h.hotelId";
	private static final Base64.Encoder encoder = Base64.getEncoder();
	
	@Override
	public void insert(WishVO aWishVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(WishDAO.INSERT_STMT);

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
			con = ds.getConnection();
			pstmt = con.prepareStatement(WishDAO.DELETE);

			pstmt.setString(1, aWishMemId);
			pstmt.setString(2, aWishRoomId);
			System.out.println("12313213131321321321323213213213");
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(WishDAO.GET_ONE_STMT);
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(WishDAO.GET_ALL_STMT);
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
	//------------------------貴新增
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
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);

			pstmt.setString(1,wishMemId);
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
				Map<Object,Object> map = new HashMap<Object,Object>();//MAP
				  byte[] roomPhotoPic = rs.getBytes("roomPhotoPic");

					//放入
				  double salePrice = rs.getInt("roomPrice")*(rs.getDouble("roomDisccountPercent")/100);
				  
				map.put("hotelid",rs.getString("hotelid"));  	//未來查詢詳情用或許看設計
				map.put("roomid",rs.getString("roomid"));  	//未來查詢詳情用或許看設計
				map.put("roomPhotoRoomId",rs.getString("roomPhotoRoomId"));//未來查詢詳情房型編號
				map.put("roomPhotoPic",encoder.encodeToString(roomPhotoPic));//房照片
				map.put("hotelName",rs.getString("hotelName"));//廠商名
				map.put("roomPrice",rs.getInt("roomPrice"));//原價
				map.put("salePrice",salePrice);//搞定
				map.put("roomName",rs.getString("roomName"));//房型名稱 :標準床
				map.put("roomRemainNo",rs.getInt("roomRemainNo"));//房數
				map.put("roomDiscountStartDate",rs.getInt("roomDiscountStartDate"));//暫時不用了
				map.put("roomDiscountEndDate",rs.getInt("roomDiscountEndDate"));//暫時不用了
				map.put("hotelRatingResult",rs.getInt("hotelRatingResult"));//評價統計結果
				
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
