package com.serv.model;

import java.util.*;
import java.sql.*;

public class ServJDBCDAO implements ServDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "plzdongo";
	String passwd = "Tom800712";
	
	private static final String INSERT_STMT="INSERT INTO serv(servId,servName)VALUES(to_char(serv_seq.NEXTVAL),?)";
	private static final String GET_ALL_STMT="SELECT servId,servName FROM serv order by servId";
	private static final String GET_ONE_STMT="SELECT servId,servName FROM serv where servId = ?";
	private static final String DELETE="DELETE FROM serv where servId = ?";
	private static final String UPDATE="UPDATE serv set servName = ? where servId = ?";
	
	@Override
	public void insert(ServVO aServVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(this.driver).getClass();
			con = DriverManager.getConnection(this.url, this.userid, this.passwd);
			pstmt = con.prepareStatement(ServJDBCDAO.INSERT_STMT);
			
			pstmt.setString(1,aServVO.getServName());
			
			pstmt.executeUpdate();
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver."+ e.getMessage());
			
		}catch(SQLException se){
			throw new RuntimeException("A database error occured."+ se.getMessage());
			
		}finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(ServVO aServVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url, this.userid, this.passwd);
			pstmt = con.prepareStatement(ServJDBCDAO.UPDATE);
			
			pstmt.setString(1,aServVO.getServName());
			pstmt.setString(2,aServVO.getServId());
			pstmt.executeUpdate();
			
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
			
		}catch(SQLException se){
			throw new RuntimeException("A database error occured." + se.getMessage());
			
		}finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void delete(String aServId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url, this.userid, this.passwd);
			pstmt = con.prepareStatement(ServJDBCDAO.DELETE);
			
			pstmt.setString(1, aServId);
			pstmt.executeUpdate();
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database dirver." + e.getMessage());
			
		}catch(SQLException se){
			throw new RuntimeException("A database error occured." + se.getMessage());
			
		}finally{
			if(pstmt != null){
				try{
				pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public ServVO findByPrimaryKey(String aServVO) {
		ServVO servVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url, this.userid, this.passwd);
			pstmt = con.prepareStatement(ServJDBCDAO.GET_ONE_STMT);
			
			pstmt.setString(1, aServVO);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				servVO = new ServVO();
				servVO.setServId(rs.getString("servId"));
				servVO.setServName(rs.getString("servName"));
			}
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
			
		}catch(SQLException se){
			throw new RuntimeException("A database error occured." + se.getMessage());
			
		}finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		return servVO;
	}


	@Override
	public List<ServVO> getAll() {
		List<ServVO> list = new ArrayList<ServVO>();
		ServVO servVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url, this.userid, this.passwd);
			pstmt = con.prepareStatement(ServJDBCDAO.GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				servVO = new ServVO();
				servVO.setServId(rs.getString("servId"));
				servVO.setServName(rs.getString("servName"));
				list.add(servVO);
			}
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		
		}catch(SQLException se){
			throw new RuntimeException("A darabase error occured." + se.getMessage());
			
		}finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		
		ServJDBCDAO dao = new ServJDBCDAO();
		
		//insert
//		ServVO servVO1 = new ServVO();
//		servVO1.setServName("wi-fi");
//		dao.insert(servVO1);
//		System.out.println(123123);
		
		//update
//		ServVO servVO2 = new ServVO();
//		servVO2.setServId("1002");
//		servVO2.setServName("���\");
//		dao.update(servVO2);
		
		//delete
//		dao.delete("1002");
//		System.out.println(123123);
//		
		//select one only
//		ServVO servVO3 = dao.findByPrimaryKey("1002");
//		System.out.print(servVO3.getServId() + ",");
//		System.out.print(servVO3.getServName());
//		System.out.println("--------------------------");
		
//		//select All
//		List<ServVO> list = dao.getAll();
//		for(ServVO servVO : list){
//			System.out.print(servVO.getServId() + ",");
//			System.out.print(servVO.getServName());
//			System.out.println();
//		}
	}

}
