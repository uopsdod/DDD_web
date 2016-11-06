package com.roomphoto.model;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import com.room.model.RoomJDBCDAO;
import com.room.model.RoomVO;

public class RoomPhotoJDBCDAO implements RoomPhotoDAO_interface {
	

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "JDBCcucuboy";
	String passwd = "123";

	
	private static final String INSERT_STMT = 
		"INSERT INTO roomphoto VALUES ( ? ||roomphoto_seq.NEXTVAL ,room_seq.CURRVAL, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM roomphoto order by roomphotoId";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM roomphoto where roomphotoId = ?";
	private static final String DELETE = 
		"DELETE FROM roomphoto where roomphotoId = ?";
	private static final String UPDATE = 
		"UPDATE roomphoto set roomphotoroomid = ?,roomPhotoPic = ?"
		+ "where roomphotoId = ?";
	private static final String GET_AllForOne_STMT = 
		"SELECT * FROM roomphoto where roomPhotoRoomId = ?";	
	
	
	private static final String GET_AllVOForOne_STMT = 
		"SELECT * FROM roomphoto where roomPhotoRoomId = ?";
	
	
	@Override
	public boolean	insert(String aHotelId,byte[] aRoomPhotoPic,Connection con) {
		boolean boo = false;
		
		
		PreparedStatement pstmt = null;
			
		
		try {
			
			System.out.println("圖片開始新增");
			
			
			
			pstmt = con.prepareStatement(INSERT_STMT);

			
			System.out.println("圖片正在新增");
			
			pstmt.setString(1, aHotelId);	
			System.out.println(aRoomPhotoPic==null);
			System.out.println(aHotelId);
			pstmt.setBytes(2, aRoomPhotoPic);
			
			pstmt.executeUpdate();
			
				System.out.println("圖片以新增");
	
			
			boo =true;
			// Handle any driver errors
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch(Exception e){
				System.out.println(e.getMessage());
				throw new RuntimeException("A database error occured. "
						+ e.getMessage());
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
				
		return boo;
	}
	
	
	
	
	
	
	@Override
	public List<RoomPhotoVO> getOneAllRoomPhotoVO(String aRoomId){
		
	List<RoomPhotoVO> roomPhotoVOList = new ArrayList<RoomPhotoVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RoomPhotoVO roomPhotoVO = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_AllVOForOne_STMT);

			pstmt.setString(1, aRoomId);

			rs = pstmt.executeQuery();
		
			
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				roomPhotoVO = new RoomPhotoVO();
				roomPhotoVO.setRoomPhotoId(rs.getString("roomPhotoId"));
				roomPhotoVO.setRoomPhotoRoomId(rs.getString("roomPhotoRoomId"));
				roomPhotoVO.setRoomPhotoPic(rs.getBytes("roomPhotoPic"));
				
				roomPhotoVOList.add(roomPhotoVO); // Store the row in the list
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
		return roomPhotoVOList;
		
	}
	
	
	
	
	
	
	
	@Override
	public boolean update(RoomPhotoVO aRoomPhotoVO) {
		boolean boo =false;
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, aRoomPhotoVO.getRoomPhotoRoomId());
			pstmt.setBytes(2, aRoomPhotoVO.getRoomPhotoPic());
		
			//條件
			pstmt.setString(3, aRoomPhotoVO.getRoomPhotoId());

			pstmt.executeUpdate();
			
			boo = true;
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
		
		return boo;
	}

	@Override
	public boolean delete(String aRoomPhotoId) {
		
		boolean boo = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, aRoomPhotoId);
			pstmt.executeUpdate();
			
			
			boo = true;
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
		return boo;
	}

	@Override
	public RoomPhotoVO findByPrimaryKey(String aRoomPhotoId) {
		RoomPhotoVO roomPhotoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, aRoomPhotoId);

			rs = pstmt.executeQuery();
		
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects	
				roomPhotoVO = new RoomPhotoVO();
				roomPhotoVO.setRoomPhotoId(rs.getString("roomPhotoId"));
				roomPhotoVO.setRoomPhotoRoomId(rs.getString("roomPhotoRoomId"));
				roomPhotoVO.setRoomPhotoPic(rs.getBytes("roomPhotoPic"));
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
		return roomPhotoVO;
		
	}
	@Override
	public List<String> getRoomAllRoomPhotoId(String aRoomId) {
		
		
		List<String> roomPhotoIdList = new ArrayList<String>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_AllForOne_STMT);

			pstmt.setString(1, aRoomId);

			rs = pstmt.executeQuery();
		
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects	
				
				roomPhotoIdList.add(rs.getString("roomPhotoId"));
			
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
		return roomPhotoIdList;
		
	}
	@Override
	public List<RoomPhotoVO> getAll() {
		
		
		List<RoomPhotoVO> list = new ArrayList<RoomPhotoVO>();
		RoomPhotoVO roomPhotoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				roomPhotoVO = new RoomPhotoVO();
				roomPhotoVO.setRoomPhotoId(rs.getString("roomPhotoId"));
				roomPhotoVO.setRoomPhotoRoomId(rs.getString("roomPhotoRoomId"));
				roomPhotoVO.setRoomPhotoPic(rs.getBytes("roomPhotoPic"));
				
				list.add(roomPhotoVO); // Store the row in the list
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
	
	public static void main(String[] args) {
		
		RoomPhotoJDBCDAO dao = new RoomPhotoJDBCDAO();

		// 新增
		
//		RoomPhotoVO roomPhotoVO = new RoomPhotoVO();
//		roomPhotoVO.setRoomPhotoRoomId("1000004");
//		roomPhotoVO.setRoomPhotoPic(getPic());
//		dao.insert(roomPhotoVO,"10001");
//		
		
		//刪除
		/*
		RoomPhotoVO roomPhotoVO = new RoomPhotoVO();
		roomPhotoVO.setRoomPhotoId("1000110005");
		roomPhotoVO.setRoomPhotoRoomId("10001");
		roomPhotoVO.setRoomPhotoPic(getPic());
		dao.delete(roomPhotoVO);
		*/
		// 修改
		/*
		RoomPhotoVO roomPhotoVO = new RoomPhotoVO();
		roomPhotoVO.setRoomPhotoId("1000110003");
		roomPhotoVO.setRoomPhotoRoomId("10002");
		roomPhotoVO.setRoomPhotoPic(getPic());
		System.out.println(dao.update(roomPhotoVO));
		*/
		
		// 查詢全部
		/*		
		List<RoomPhotoVO> list = dao.getAll();
		for (RoomPhotoVO roomPhotoVO : list) {
			System.out.println(roomPhotoVO.getRoomPhotoId() + ",");
			System.out.println(roomPhotoVO.getRoomPhotoRoomId() + ",");
			System.out.println(roomPhotoVO.getRoomPhotoPic() + ",");
			System.out.println("---------------------");
		}
		*/
		
		
		//查詢單筆
		/*
		RoomPhotoVO roomPhotoVO = dao.findByPrimaryKey("1000110007");	
			System.out.println(roomPhotoVO.getRoomPhotoId() + ",");
			System.out.println(roomPhotoVO.getRoomPhotoRoomId() + ",");
			System.out.println(roomPhotoVO.getRoomPhotoPic() + ",");
			System.out.println("---------------------");
		*/	
		
		//查詢某房型的全部照片
		
		List<String> photoAll =  dao.getRoomAllRoomPhotoId("1000003");	
			for(String photoId : photoAll){
				
				System.out.println(photoId);
				
			}
			
	}

	private static byte[] getPic() {
		
		byte[] bytearr = null;
		try{
		 FileInputStream fis = new FileInputStream("WebContent/room/images/room10.jpg");
         BufferedInputStream  bis  =   new  BufferedInputStream(fis) ;
         int content =bis.available();
         
         bytearr = new byte[content];
         bis.read(bytearr);     
         
         bis.close();   
         fis.close();
         
         
		}catch(Exception e){
			
		}
		return bytearr;		
	}
	
	


	

}
