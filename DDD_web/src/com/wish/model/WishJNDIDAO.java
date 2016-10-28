package com.wish.model;

import java.util.ArrayList;
import java.util.List;

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
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		}catch(NamingException e){
			e.printStackTrace(System.err);
		}
	}

	private static String INSERT_STMT = "INSERT INTO wish (wishmemid,wishroomid) VALUES (?,?)";
	private static String GET_ONE_STMT = "SELECT wishMemId,wishRoomId FROM wish where wishMemId=? AND wishRoomId=?";
	private static String GET_ALL_STMT = "SElECT wishMemId,wishRoomId FROM wish order by wishMemId";
	private static String UPDATE = "UPDATE wish set wishRoomId = ? where wishMemId = ?";
	private static String DELETE = "DELETE FROM wish where wishMemId = ? AND  wishRoomid = ?";

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

}
