package com.room.model;

import java.util.*;

import javax.naming.Context;
import javax.sql.DataSource;

import java.sql.*;

public class RoomJDBCDAO implements RoomDAO_interface{
	
	

	
	
	

	private static final String INSERT_STMT = 
		"INSERT INTO room VALUES (room_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM room order by roomId";
	private static final String GET_OneHotelALLRoom_STMT = 
			"SELECT * FROM room where roomHotelId = ? order by roomId";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM room where roomId = ?";
	private static final String DELETE = 
		"DELETE FROM room where roomId = ?";
	private static final String UPDATE = 
		"UPDATE room set roomName = ?,roomTotalNo = ?,roomPrice = ?,roomForSell = ?,"+
		"roomForSellAuto = ?,roomRemainNo = ?,roomDiscountStartDate = ?,roomDiscountEndDate = ?,"+
		"roomDisccountPercent = ?,roomDiscountHr = ?,roomOnePrice = ?,roomFun = ?,roomMeal = ?,"+
		"roomSleep = ?,roomFacility = ?,roomSweetFacility = ?,roomCapacity = ?,roomOneBed = ?,roomTwoBed = ?"
		+ "where roomId = ?";

	private String changeBoolean(Boolean aBoolean){
		
		if(aBoolean)
		{return "1";}
		else
		{return "0";}		
	}
	
	private Boolean changeString(String aStr){
		
		if("1".equals(aStr))
		{return true;}
		else 
		{return false;}		
	}
	@Override
	public Set<RoomVO> getOneHotelAllRoom(String aHotelId){
		
		Set<RoomVO> RoomSet = new LinkedHashSet<RoomVO>();
		
		RoomVO roomVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			Context ctx = new javax.naming.InitialContext();	//連線池
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			con = ds.getConnection();	
			
			
	
			pstmt = con.prepareStatement(GET_OneHotelALLRoom_STMT);
			pstmt.setString(1, aHotelId);			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				roomVO = new RoomVO();
				roomVO.setRoomId(rs.getString("roomId"));
				roomVO.setRoomHotelId(rs.getString("roomHotelId"));
				roomVO.setRoomName(rs.getString("roomName"));
				roomVO.setRoomTotalNo(rs.getInt("roomTotalNo"));
				roomVO.setRoomPrice(rs.getInt("roomPrice"));
				roomVO.setRoomForSell(changeString(rs.getString("roomForSell")));
				roomVO.setRoomForSellAuto(changeString(rs.getString("roomForSellAuto")));
				roomVO.setRoomRemainNo(rs.getInt("roomRemainNo"));
				roomVO.setRoomDiscountStartDate(rs.getInt("roomDiscountStartDate"));
				roomVO.setRoomDiscountEndDate(rs.getInt("roomDiscountEndDate"));
				roomVO.setRoomDisccountPercent(rs.getDouble("roomDisccountPercent"));
				roomVO.setRoomDiscountHr(rs.getInt("roomDiscountHr"));
				roomVO.setRoomOnePrice(changeString(rs.getString("roomOnePrice")));
				roomVO.setRoomFun(rs.getString("roomFun"));
				roomVO.setRoomMeal(rs.getString("roomMeal"));
				roomVO.setRoomSleep(rs.getString("roomSleep"));
				roomVO.setRoomFacility(rs.getString("roomFacility"));
				roomVO.setRoomSweetFacility(rs.getString("roomSweetFacility"));
				roomVO.setRoomCapacity(rs.getInt("roomCapacity"));
				roomVO.setRoomOneBed(rs.getInt("roomOneBed"));
				roomVO.setRoomTwoBed(rs.getInt("roomTwoBed"));
				RoomSet.add(roomVO); // Store the row in the list
			}

			// Handle any driver errors
		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}  catch (Exception se) {
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
		return RoomSet;
	}
	@Override
	public void insert(RoomVO aRoomVO,Connection con){

		
		PreparedStatement pstmt = null;

		try {

//			System.out.println("HotelId  :  "+aRoomVO.getRoomHotelId().getClass()+aRoomVO.getRoomHotelId());
//			System.out.println("RoomName  :  "+aRoomVO.getRoomName().getClass()+aRoomVO.getRoomName());
//			System.out.println("RoomTotalNo  :  "+aRoomVO.getRoomTotalNo().getClass()+aRoomVO.getRoomTotalNo());
//			System.out.println("RoomPrice  :  "+aRoomVO.getRoomPrice().getClass()+aRoomVO.getRoomPrice());
//			System.out.println("RoomForSell  :  "+changeBoolean(aRoomVO.getRoomForSell()).getClass()+changeBoolean(aRoomVO.getRoomForSell()));
//			System.out.println("RoomForSellAuto  :  "+changeBoolean(aRoomVO.getRoomForSellAuto()).getClass()+changeBoolean(aRoomVO.getRoomForSellAuto()));
//			System.out.println("RoomRemainNo  :  "+aRoomVO.getRoomRemainNo().getClass()+aRoomVO.getRoomRemainNo());
//			System.out.println("RoomDiscountStartDate  :  "+aRoomVO.getRoomDiscountStartDate().getClass()+aRoomVO.getRoomDiscountStartDate());
//			System.out.println("RoomDiscountEndDate  :  "+aRoomVO.getRoomDiscountEndDate().getClass()+aRoomVO.getRoomDiscountEndDate());
//			System.out.println("RoomDisccountPercent  :  "+aRoomVO.getRoomDisccountPercent().getClass()+aRoomVO.getRoomDisccountPercent());
//			System.out.println("RoomDiscountHr  :  "+aRoomVO.getRoomDiscountHr().getClass()+aRoomVO.getRoomDiscountHr());
//			System.out.println("RoomOnePrice  :  "+changeBoolean(aRoomVO.getRoomOnePrice()).getClass()+changeBoolean(aRoomVO.getRoomOnePrice()));
//			System.out.println("RoomFun  :  "+aRoomVO.getRoomFun().getClass()+aRoomVO.getRoomFun());
//			System.out.println("RoomMeal  :  "+aRoomVO.getRoomMeal().getClass()+aRoomVO.getRoomMeal());
//			System.out.println("RoomSleep  :  "+aRoomVO.getRoomSleep().getClass()+aRoomVO.getRoomSleep());
//			System.out.println("RoomFacility  :  "+aRoomVO.getRoomFacility().getClass()+aRoomVO.getRoomFacility());
//			System.out.println("RoomSweetFacility  :  "+aRoomVO.getRoomSweetFacility().getClass()+aRoomVO.getRoomSweetFacility());
//			System.out.println("RoomCapacity  :  "+aRoomVO.getRoomCapacity().getClass()+aRoomVO.getRoomCapacity());
//			System.out.println("RoomOneBed  :  "+aRoomVO.getRoomOneBed().getClass()+aRoomVO.getRoomOneBed());
//			System.out.println("RoomTwoBed  :  "+aRoomVO.getRoomTwoBed().getClass()+aRoomVO.getRoomTwoBed());
//			
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, aRoomVO.getRoomHotelId());
			pstmt.setString(2, aRoomVO.getRoomName());
			pstmt.setInt(3, aRoomVO.getRoomTotalNo());
			pstmt.setInt(4, aRoomVO.getRoomPrice());
			pstmt.setString(5, changeBoolean(aRoomVO.getRoomForSell()));
			pstmt.setString(6, changeBoolean(aRoomVO.getRoomForSellAuto()));
			pstmt.setInt(7, aRoomVO.getRoomRemainNo());
			pstmt.setInt(8, aRoomVO.getRoomDiscountStartDate());
			pstmt.setInt(9, aRoomVO.getRoomDiscountEndDate());
			pstmt.setDouble(10, aRoomVO.getRoomDisccountPercent());
			pstmt.setInt(11, aRoomVO.getRoomDiscountHr());
			pstmt.setString(12, changeBoolean(aRoomVO.getRoomOnePrice()));
			pstmt.setString(13, aRoomVO.getRoomFun());
			pstmt.setString(14, aRoomVO.getRoomMeal());
			pstmt.setString(15, aRoomVO.getRoomSleep());
			pstmt.setString(16, aRoomVO.getRoomFacility());
			pstmt.setString(17, aRoomVO.getRoomSweetFacility());
			pstmt.setInt(18, aRoomVO.getRoomCapacity());
			pstmt.setInt(19, aRoomVO.getRoomOneBed());
			pstmt.setInt(20, aRoomVO.getRoomTwoBed());
		
			System.out.println("aaaaa");

			pstmt.executeUpdate();

			
			
			
			System.out.println("aaaaa");

			// Handle any driver errors
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
			
		}
		
	}
	

	@Override
	public void update(RoomVO aRoomVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			Context ctx = new javax.naming.InitialContext();	//連線池
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			con = ds.getConnection();	
			pstmt = con.prepareStatement(UPDATE);
			
		
			
			pstmt.setString(1, aRoomVO.getRoomName());
			pstmt.setInt(2, aRoomVO.getRoomTotalNo());
			pstmt.setInt(3, aRoomVO.getRoomPrice());
			pstmt.setString(4, changeBoolean(aRoomVO.getRoomForSell()));
			pstmt.setString(5, changeBoolean(aRoomVO.getRoomForSellAuto()));
			pstmt.setInt(6, aRoomVO.getRoomRemainNo());
			pstmt.setInt(7, aRoomVO.getRoomDiscountStartDate());
			pstmt.setInt(8, aRoomVO.getRoomDiscountEndDate());
			pstmt.setDouble(9, aRoomVO.getRoomDisccountPercent());
			pstmt.setInt(10, aRoomVO.getRoomDiscountHr());
			pstmt.setString(11, changeBoolean(aRoomVO.getRoomOnePrice()));
			pstmt.setString(12, aRoomVO.getRoomFun());
			pstmt.setString(13, aRoomVO.getRoomMeal());
			pstmt.setString(14, aRoomVO.getRoomSleep());
			pstmt.setString(15, aRoomVO.getRoomFacility());
			pstmt.setString(16, aRoomVO.getRoomSweetFacility());
			pstmt.setInt(17, aRoomVO.getRoomCapacity());
			pstmt.setInt(18, aRoomVO.getRoomOneBed());
			pstmt.setInt(19, aRoomVO.getRoomTwoBed());
			
			

			//條件
			pstmt.setString(20, aRoomVO.getRoomId());
	
			pstmt.executeUpdate();
		
		
			// Handle any driver errors
		}  catch (SQLException se) {
			System.out.println(se.getMessage());
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
			// Clean up JDBC resources
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		}finally {
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
	public boolean delete(String aRoomId) {
		
		boolean boo = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Context ctx = new javax.naming.InitialContext();	//連線池
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			con = ds.getConnection();	
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, aRoomId);
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
	public RoomVO findByPrimaryKey(String aRoomId) {
		
		RoomVO roomVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Context ctx = new javax.naming.InitialContext();	//連線池
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			con = ds.getConnection();	
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, aRoomId);

			rs = pstmt.executeQuery();
		
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects	
				roomVO = new RoomVO();
				roomVO.setRoomId(rs.getString("roomId"));
				roomVO.setRoomHotelId(rs.getString("roomHotelId"));
				roomVO.setRoomName(rs.getString("roomName"));
				roomVO.setRoomTotalNo(rs.getInt("roomTotalNo"));
				roomVO.setRoomPrice(rs.getInt("roomPrice"));
				roomVO.setRoomForSell(changeString(rs.getString("roomForSell")));
				roomVO.setRoomForSellAuto(changeString(rs.getString("roomForSellAuto")));
				roomVO.setRoomRemainNo(rs.getInt("roomRemainNo"));
				roomVO.setRoomDiscountStartDate(rs.getInt("roomDiscountStartDate"));
				roomVO.setRoomDiscountEndDate(rs.getInt("roomDiscountEndDate"));
				roomVO.setRoomDisccountPercent(rs.getDouble("roomDisccountPercent"));
				roomVO.setRoomDiscountHr(rs.getInt("roomDiscountHr"));
				roomVO.setRoomOnePrice(changeString(rs.getString("roomOnePrice")));
				roomVO.setRoomFun(rs.getString("roomFun"));
				roomVO.setRoomMeal(rs.getString("roomMeal"));
				roomVO.setRoomSleep(rs.getString("roomSleep"));
				roomVO.setRoomFacility(rs.getString("roomFacility"));
				roomVO.setRoomSweetFacility(rs.getString("roomSweetFacility"));
				roomVO.setRoomCapacity(rs.getInt("roomCapacity"));
				roomVO.setRoomOneBed(rs.getInt("roomOneBed"));
				roomVO.setRoomTwoBed(rs.getInt("roomTwoBed"));
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
		return roomVO;
		
	}




	@Override
	public List<RoomVO> getAll() {
		List<RoomVO> list = new ArrayList<RoomVO>();
		RoomVO roomVO = null;

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
				roomVO = new RoomVO();
				roomVO.setRoomId(rs.getString("roomId"));
				roomVO.setRoomHotelId(rs.getString("roomHotelId"));
				roomVO.setRoomName(rs.getString("roomName"));
				roomVO.setRoomTotalNo(rs.getInt("roomTotalNo"));
				roomVO.setRoomPrice(rs.getInt("roomPrice"));
				roomVO.setRoomForSell(changeString(rs.getString("roomForSell")));
				roomVO.setRoomForSellAuto(changeString(rs.getString("roomForSellAuto")));
				roomVO.setRoomRemainNo(rs.getInt("roomRemainNo"));
				roomVO.setRoomDiscountStartDate(rs.getInt("roomDiscountStartDate"));
				roomVO.setRoomDiscountEndDate(rs.getInt("roomDiscountEndDate"));
				roomVO.setRoomDisccountPercent(rs.getDouble("roomDisccountPercent"));
				roomVO.setRoomDiscountHr(rs.getInt("roomDiscountHr"));
				roomVO.setRoomOnePrice(changeString(rs.getString("roomOnePrice")));
				roomVO.setRoomFun(rs.getString("roomFun"));
				roomVO.setRoomMeal(rs.getString("roomMeal"));
				roomVO.setRoomSleep(rs.getString("roomSleep"));
				roomVO.setRoomFacility(rs.getString("roomFacility"));
				roomVO.setRoomSweetFacility(rs.getString("roomSweetFacility"));
				roomVO.setRoomCapacity(rs.getInt("roomCapacity"));
				roomVO.setRoomOneBed(rs.getInt("roomOneBed"));
				roomVO.setRoomTwoBed(rs.getInt("roomTwoBed"));
				list.add(roomVO); // Store the row in the list
			}

			// Handle any driver errors
		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (Exception e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		}finally {
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
		
		
		
		
		RoomJDBCDAO dao = new RoomJDBCDAO();

		// 新增
		/*
		RoomVO roomVO1 = new RoomVO();
		roomVO1.setRoomHotelId("10004");
		roomVO1.setRoomName("豪華閣超豪華客房");
		roomVO1.setRoomTotalNo(12);
		roomVO1.setRoomPrice(12900);
		roomVO1.setRoomForSell(false);
		roomVO1.setRoomForSellAuto(true);
		roomVO1.setRoomRemainNo(10);
		roomVO1.setRoomDiscountStartDate(10*3600*1000);
		roomVO1.setRoomDiscountEndDate(22*3600*1000);
		roomVO1.setRoomDisccountPercent(0.03);
		roomVO1.setRoomDiscountHr(3);
		roomVO1.setRoomOnePrice(false);
		roomVO1.setRoomFun("免費無線和有線上網、40 吋電視");
		roomVO1.setRoomMeal("咖啡機/沖茶器、迷你吧、24 小時客房送餐服務和免費瓶裝水");
		roomVO1.setRoomSleep("低過敏寢具、遮光窗簾和開床服務");
		roomVO1.setRoomFacility("保險箱、免費報紙和書桌，可要求提供折疊床/加床和免費搖籃/嬰兒床");
		roomVO1.setRoomSweetFacility("空調和每日客房清潔服務");
		roomVO1.setRoomCapacity(4);
		roomVO1.setRoomOneBed(2);
		roomVO1.setRoomTwoBed(1);
		dao.insert(roomVO1);
		*/
		
		
		// 修改
		/*		
				RoomVO roomVO1 = new RoomVO();
				roomVO1.setRoomId("1000008");
				roomVO1.setRoomHotelId("10004");
				roomVO1.setRoomName("豪華閣超豪華客房");
				roomVO1.setRoomTotalNo(12);
				roomVO1.setRoomPrice(13100);
				roomVO1.setRoomForSell(false);
				roomVO1.setRoomForSellAuto(true);
				roomVO1.setRoomRemainNo(10);
				roomVO1.setRoomDiscountStartDate(18*3600*1000);
				roomVO1.setRoomDiscountEndDate(19*3600*1000);
				roomVO1.setRoomDisccountPercent(0.03);
				roomVO1.setRoomDiscountHr(3);
				roomVO1.setRoomOnePrice(false);
				roomVO1.setRoomFun("免費無線和有線上網、40 吋電視");
				roomVO1.setRoomMeal("咖啡機/沖茶器、迷你吧、24 小時客房送餐服務和免費瓶裝水");
				roomVO1.setRoomSleep("低過敏寢具、遮光窗簾和開床服務");
				roomVO1.setRoomFacility("保險箱、免費報紙和書桌，可要求提供折疊床/加床和免費搖籃/嬰兒床");
				roomVO1.setRoomSweetFacility("空調和每日客房清潔服務");
				roomVO1.setRoomCapacity(4);
				roomVO1.setRoomOneBed(2);
				roomVO1.setRoomTwoBed(1);
				dao.update(roomVO1);
		*/		
		
		
		//刪除
		/*	
		String aRoomId = "xxxx";	
		dao.delete("aRoomId");
		*/
		
		// 查詢	
		/*
				RoomVO roomVO = dao.findByPrimaryKey(1000008);
				System.out.println(roomVO.getRoomId() + ",");
				System.out.println(roomVO.getRoomHotelId() + ",");
				System.out.println(roomVO.getRoomName() + ",");
				System.out.println(roomVO.getRoomTotalNo() + ",");
				System.out.println(roomVO.getRoomPrice() + ",");
				System.out.println(roomVO.getRoomForSell() + ",");
				System.out.println(roomVO.getRoomForSellAuto());
				System.out.println(roomVO.getRoomRemainNo());
				System.out.println(roomVO.getRoomDiscountStartDate());
				System.out.println(roomVO.getRoomDiscountEndDate());
				System.out.println(roomVO.getRoomDisccountPercent());
				System.out.println(roomVO.getRoomDiscountHr());
				System.out.println(roomVO.getRoomOnePrice());
				System.out.println(roomVO.getRoomFun());
				System.out.println(roomVO.getRoomMeal());
				System.out.println(roomVO.getRoomSleep());
				System.out.println(roomVO.getRoomFacility());
				System.out.println(roomVO.getRoomSweetFacility());
				System.out.println(roomVO.getRoomCapacity());
				System.out.println(roomVO.getRoomOneBed());
				System.out.println(roomVO.getRoomTwoBed());
				System.out.println("---------------------");
		*/
				
		/*	
		List<RoomVO> list = dao.getAll();
		for (RoomVO roomVO : list) {
			System.out.println(roomVO.getRoomId() + ",");
			System.out.println(roomVO.getRoomHotelId() + ",");
			System.out.println(roomVO.getRoomName() + ",");
			System.out.println(roomVO.getRoomTotalNo() + ",");
			System.out.println(roomVO.getRoomPrice() + ",");
			System.out.println(roomVO.getRoomForSell() + ",");
			System.out.println(roomVO.getRoomForSellAuto());
			System.out.println(roomVO.getRoomRemainNo());
			System.out.println(roomVO.getRoomDiscountStartDate());
			System.out.println(roomVO.getRoomDiscountEndDate());
			System.out.println(roomVO.getRoomDisccountPercent());
			System.out.println(roomVO.getRoomDiscountHr());
			System.out.println(roomVO.getRoomOnePrice());
			System.out.println(roomVO.getRoomFun());
			System.out.println(roomVO.getRoomMeal());
			System.out.println(roomVO.getRoomSleep());
			System.out.println(roomVO.getRoomFacility());
			System.out.println(roomVO.getRoomSweetFacility());
			System.out.println(roomVO.getRoomCapacity());
			System.out.println(roomVO.getRoomOneBed());
			System.out.println(roomVO.getRoomTwoBed());
			System.out.println("---------------------");
		}
		*/
		
		// 查詢一家HOTEL所有的ROOM	
				/*
						Set<RoomVO> roomSet = dao.getOneHotelAllRoom("10002");
						for (RoomVO roomVO : roomSet)
						{
						System.out.println(roomVO.getRoomId() + ",");
						System.out.println(roomVO.getRoomHotelId() + ",");
						System.out.println(roomVO.getRoomName() + ",");
						System.out.println(roomVO.getRoomTotalNo() + ",");
						System.out.println(roomVO.getRoomPrice() + ",");
						System.out.println(roomVO.getRoomForSell() + ",");
						System.out.println(roomVO.getRoomForSellAuto());
						System.out.println(roomVO.getRoomRemainNo());
						System.out.println(roomVO.getRoomDiscountStartDate());
						System.out.println(roomVO.getRoomDiscountEndDate());
						System.out.println(roomVO.getRoomDisccountPercent());
						System.out.println(roomVO.getRoomDiscountHr());
						System.out.println(roomVO.getRoomOnePrice());
						System.out.println(roomVO.getRoomFun());
						System.out.println(roomVO.getRoomMeal());
						System.out.println(roomVO.getRoomSleep());
						System.out.println(roomVO.getRoomFacility());
						System.out.println(roomVO.getRoomSweetFacility());
						System.out.println(roomVO.getRoomCapacity());
						System.out.println(roomVO.getRoomOneBed());
						System.out.println(roomVO.getRoomTwoBed());
						System.out.println("---------------------");
						}
		*/
	}
}
