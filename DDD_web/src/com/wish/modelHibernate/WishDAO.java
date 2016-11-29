package com.wish.modelHibernate;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;

import com.emp.model.EmpVO;
import com.hotel.model.HotelVO;
import com.roomphoto.model.RoomPhotoVO;

import util.HibernateUtil;

import java.sql.*;

public class WishDAO implements WishDAO_interface {
	private static final String GET_ONE ="select * from(select o.roomPhotoRoomId,o.roomPhotoPic,h.hotelid,h.hotelName,r.roomPrice,r.roomid,r.roomDisccountPercent,r.roomName,r.roomRemainNo,r.roomDiscountStartDate,r.roomDiscountEndDate,h.hotelRatingResult,row_number() over (partition by o.roomPhotoRoomId,h.hotelid,h.hotelName,r.roomPrice,r.roomid,r.roomDisccountPercent,r.roomName,r.roomRemainNo,r.roomDiscountStartDate,r.roomDiscountEndDate,h.hotelRatingResult order by o.roomPhotoRoomId)RW from wish w,room r,roomphoto o,hotel h where  w.wishMemId=? and r.roomId = w.wishRoomId and r.roomid=o.roomPhotoRoomId and r.roomHotelId = h.hotelId)where RW = 1";
	private static final Base64.Encoder encoder = Base64.getEncoder();
	
	
	
	@Override
	public void insert(WishVO aWishVO) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updata(WishVO aWishVO) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(String aWishMemId, String aWishRoomId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public WishVO findByPrimaryKey(String aWishMemId, String aWishRommId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<WishVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<String> getAllWishRoomId(String aWishMemId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//----------------------------------------------------貴


	@Override
	public List<WishVO> getOneWishOfmem(String wishMemId) {
		List<WishVO> list = null;
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("SELECT wishRoomId FROM wish where wishMemId=?");
			query.setParameter(0, wishMemId);
			list = query.list();
		
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
	

	@Override
	public void delete_one(String wishMemId, String WishRoomId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			Query query = session.createSQLQuery("DELETE  from wish where wishMemId=? and WishRoomId =?");
			query.setParameter(0, wishMemId);
			query.setParameter(1, WishRoomId);
			query.executeUpdate();
			System.out.println(wishMemId+"=="+WishRoomId);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public List<Map> getOneWishOfmemNO(String wishMemId) {
		List<Map> list = new ArrayList<Map>();
		WishVO wishVO =new WishVO();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createSQLQuery(GET_ONE);
			query.setParameter(0, wishMemId);
			List<Object[]> a = query.list();
			for(Object[] rs:a){
				Map<Object,Object> map = new HashMap<Object,Object>();
				byte[] pic = String.valueOf(rs[1]).getBytes();
				
				Blob blob = (Blob) rs[1];
				byte[] blobBytes = blob.getBytes(1, (int) blob.length());
				map.put("roomPhotoRoomId",rs[0].toString());
				map.put("roomPhotoPic",encoder.encodeToString(blobBytes));
				map.put("hotelid",rs[2].toString());
				map.put("hotelName",rs[3].toString());
				map.put("roomPrice",rs[4].toString());
				map.put("roomid",rs[5].toString());
				map.put("roomDisccountPercent",rs[6].toString());
				map.put("roomName",rs[7].toString());
				map.put("roomRemainNo",rs[8].toString());
				map.put("roomDiscountStartDate",rs[9].toString());
				map.put("roomDiscountEndDate",rs[10].toString());
				map.put("hotelRatingResult",rs[11].toString());

				list.add(map);
				
			}
			
			
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	
}
