package com.rent.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.naming.Context;
import javax.sql.DataSource;

import com.room.model.RoomJDBCDAO;
import com.room.model.RoomVO;
import com.roomphoto.model.RoomPhotoVO;

public class RentJDBCDAO implements RentDAO_interface{



	private static final String INSERT_STMT = 
		"INSERT INTO rent VALUES (rent_seq.NEXTVAL, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM rent order by rentId";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM rent where rentId = ?";
	private static final String DELETE = 
		"DELETE FROM rent where rentId = ?";
	private static final String UPDATE = 
		"UPDATE rent set rentHotelId = ?,rentPayDate = ?,rentStartDate = ?,rentEndDate = ?,"+
		"rentPrice = ?"+
		"where rentId = ?";
	

	
	

	@Override
	public boolean insert(RentVO aRentVO) {
		
		boolean boo = false;
		Connection con = null;
		PreparedStatement pstmt = null;
	
		
		try {

			Context ctx = new javax.naming.InitialContext();	//連線池
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, aRentVO.getRentHotelId());
			pstmt.setDate(2, aRentVO.getRentPayDate());
			pstmt.setDate(3, aRentVO.getRentStartDate());
			pstmt.setDate(4, aRentVO.getRentEndDate());
			pstmt.setInt(5, aRentVO.getRentPrice());
			
			pstmt.executeUpdate();
			
			boo =true;
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (Exception e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
				
		return boo;
	}

	@Override
	public boolean update(RentVO aRentVO) {
		
		boolean boo =false;		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Context ctx = new javax.naming.InitialContext();	//連線池
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, aRentVO.getRentHotelId());
			pstmt.setDate(2, aRentVO.getRentPayDate());
			pstmt.setDate(3, aRentVO.getRentStartDate());
			pstmt.setDate(4, aRentVO.getRentEndDate());
			pstmt.setInt(5, aRentVO.getRentPrice());
		
			//條件
			pstmt.setString(6, aRentVO.getRentId());

			pstmt.executeUpdate();
			
			boo = true;
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (Exception e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		
		return boo;
	}

	@Override
	public boolean delete(String aRentId) {
		
		boolean boo = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Context ctx = new javax.naming.InitialContext();	//連線池
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, aRentId);
			pstmt.executeUpdate();
			
			
			boo = true;
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (Exception e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return boo;
	}

	@Override
	public RentVO findByPrimaryKey(String aRentId) {
		
		RentVO rentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Context ctx = new javax.naming.InitialContext();	//連線池
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, aRentId);

			rs = pstmt.executeQuery();
		
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects	
				rentVO = new RentVO();
				rentVO.setRentId(rs.getString("rentId"));
				rentVO.setRentHotelId(rs.getString("rentHotelId"));
				rentVO.setRentPayDate(rs.getDate("rentPayDate"));
				rentVO.setRentStartDate(rs.getDate("rentStartDate"));
				rentVO.setRentEndDate(rs.getDate("rentEndDate"));
				rentVO.setRentPrice(rs.getInt("rentPrice"));			
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (Exception e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return rentVO;
	}

	@Override
	public List<RentVO> getAll() {
		
		List<RentVO> list = new ArrayList<RentVO>();
		RentVO rentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Context ctx = new javax.naming.InitialContext();	//連線池
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				rentVO = new RentVO();
				rentVO.setRentId(rs.getString("rentId"));
				rentVO.setRentHotelId(rs.getString("rentHotelId"));
				rentVO.setRentPayDate(rs.getDate("rentPayDate"));
				rentVO.setRentStartDate(rs.getDate("rentStartDate"));
				rentVO.setRentEndDate(rs.getDate("rentEndDate"));
				rentVO.setRentPrice(rs.getInt("rentPrice"));		
				list.add(rentVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (Exception e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
			
		
		RentJDBCDAO dao = new RentJDBCDAO();
		try{
		// 新增
		/*
		GregorianCalendar rentcal = new GregorianCalendar(2014, 8, 26);		//月份會-1
		Date rentdate = new Date(rentcal.getTime().getTime());
		
		GregorianCalendar startcal = new GregorianCalendar(2015, 3, 13);	
		Date startdate = new Date(startcal.getTime().getTime());
		
		GregorianCalendar endcal = new GregorianCalendar(2015, 5, 14);	
		Date enddate = new Date(endcal.getTime().getTime());
		
		RentVO rentVO = new RentVO();
		rentVO.setRentHotelId("10002");
		rentVO.setRentPayDate(rentdate);
		rentVO.setRentStartDate(startdate);
		rentVO.setRentEndDate(enddate);
		rentVO.setRentPrice(5000);
		dao.insert(rentVO);
		*/
		
		// 修改
		/*
		GregorianCalendar rentcal = new GregorianCalendar(2009, 1, 26);		//月份會-1
		Date rentdate2 = new Date(rentcal.getTime().getTime());
				
		GregorianCalendar startcal = new GregorianCalendar(2012, 3, 13);	
		Date startdate2 = new Date(startcal.getTime().getTime());
				
		GregorianCalendar endcal = new GregorianCalendar(2015, 5, 14);	
		Date enddate2 = new Date(endcal.getTime().getTime());
				
		RentVO rentVO = new RentVO();
		rentVO.setRentId("1000000003");
		rentVO.setRentHotelId("10003");
		rentVO.setRentPayDate(rentdate2);
		rentVO.setRentStartDate(startdate2);
		rentVO.setRentEndDate(enddate2);
		rentVO.setRentPrice(6000);
		dao.update(rentVO);
		*/
		
		// 刪除
	//	dao.delete("1000000004");
			
			
		//查詢單筆
			/*
			RentVO rentVO = dao.findByPrimaryKey("1000000003");
			System.out.println(rentVO.getRentId() + ",");
			System.out.println(rentVO.getRentHotelId() + ",");
			System.out.println(rentVO.getRentPayDate() + ",");
			System.out.println(rentVO.getRentStartDate() + ",");
			System.out.println(rentVO.getRentEndDate() + ",");
			System.out.println(rentVO.getRentPrice() + ",");
			System.out.println("---------------------");	
			*/
			
			//查詢全部
			/*
			List<RentVO> list = dao.getAll();
			for (RentVO rentVO : list) {
				System.out.println(rentVO.getRentId() + ",");
				System.out.println(rentVO.getRentHotelId() + ",");
				System.out.println(rentVO.getRentPayDate() + ",");
				System.out.println(rentVO.getRentStartDate() + ",");
				System.out.println(rentVO.getRentEndDate() + ",");
				System.out.println(rentVO.getRentPrice() + ",");
				System.out.println("---------------------");
			}
			*/
			
			
		
		}catch(Exception e){}		
	}
}
