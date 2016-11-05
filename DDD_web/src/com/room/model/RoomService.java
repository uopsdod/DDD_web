package com.room.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.naming.Context;
import javax.servlet.ServletException;
import javax.sql.DataSource;
import javax.servlet.http.Part;

import com.roomphoto.model.RoomPhotoService;

public class RoomService {
	
//	private static String driver = "oracle.jdbc.driver.OracleDriver";
//	private static String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	private static String userid = "JDBCcucuboy";
//	private static String passwd = "123";
	
	
	
	
	private RoomDAO_interface dao;

	public RoomService() {
		dao = new RoomJDBCDAO();
	}

	public List<RoomVO> getAll() {
		return dao.getAll();
	}
	
	
	
	public void insert(RoomVO aRoomVO,List<byte[]> aImgbyte){
		
		Connection con = null;
			
		try{
			
			Context ctx = new javax.naming.InitialContext();	//連線池
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			con = ds.getConnection();		
			con.setAutoCommit(false);	
		
			try{
				
				dao.insert(aRoomVO,con);	//新增房型資料
				
				}catch(Exception ae){
					System.out.println(ae.getMessage());
					con.rollback();
					if (con != null) {
						try {
							con.close();
						} catch (Exception te) {
							System.out.println(te.getMessage());
							te.printStackTrace(System.err);
						}
					}
					System.out.println("房型資料新增有錯");
					return;		
				}
			
			if(aImgbyte.size()==0){return;}//沒有上傳圖片直接跳出方法,不新增圖片
			
			RoomPhotoService RoomPhotoSvc = new RoomPhotoService();
			for(int i=0;i<aImgbyte.size();i++){
				
				RoomPhotoSvc.insertRoomPhoto(aRoomVO.getRoomHotelId(),aImgbyte.get(i),con);
				//新增圖片
			}
					
			
			con.commit();
		}catch(Exception e){
			System.out.println(e.getMessage());
			try{
				con.rollback();
			}catch(Exception se){
				
			}	
		}
		finally{
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	
	}
	public void update(RoomVO aRoomVO){
		
		
		
		
		dao.update(aRoomVO);
		
	}
	public boolean delete(String aRoomId){
		return dao.delete(aRoomId);
		
	}
	public RoomVO findByPrimaryKey(String aRoomId){
		return dao.findByPrimaryKey(aRoomId);
		
	}	
	public Set<RoomVO> getOneHotelAllRoom(String aHotelId){
	
		return dao.getOneHotelAllRoom(aHotelId);	
	}	
}
