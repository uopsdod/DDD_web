package com.serv.model;

import java.util.*;

import javax.naming.Context;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.sql.*;

public class ServJNDIDAO implements ServDAO_interface{
	private static DataSource ds = null;
	static{
		try{
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		}catch(NamingException e){
			e.printStackTrace(System.err);
		}
	}
	
	private static final String INSERT_STMT="INSERT INTO serv(ServId,servName)VALUES(to_char(serv_seq.NEXTVAL),?)";
	private static final String GET_ALL_STMT="SELECT ServId,servName FROM serv order by ServId";
	private static final String GET_ONE_STMT="SELECT ServId,servName FROM serv where ServId = ?";
	private static final String DELETE="DELETE FROM serv where ServId = ?";
	private static final String UPDATE="UPDATE serv set servName = ? where ServId = ?";
	
	@Override
	public void insert(ServVO aServVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(ServJNDIDAO.INSERT_STMT);
			
			pstmt.setString(1,aServVO.getServName());
			
			pstmt.executeUpdate();
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
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(ServJNDIDAO.UPDATE);
			
			pstmt.setString(1,aServVO.getServName());
			pstmt.setString(2,aServVO.getServId());
			pstmt.executeUpdate();
			
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
	public void delete(String aServVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(ServJNDIDAO.DELETE);
			
			pstmt.setString(1, aServVO);
			pstmt.executeUpdate();
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
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(ServJNDIDAO.GET_ONE_STMT);
			
			pstmt.setString(1, aServVO);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				servVO = new ServVO();
				servVO.setServId(rs.getString("ServId"));
				servVO.setServName(rs.getString("servName"));
			}
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
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(ServJNDIDAO.GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				servVO = new ServVO();
				servVO.setServId(rs.getString("ServId"));
				servVO.setServName(rs.getString("servName"));
				list.add(servVO);
			}
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
}
