package com.hotel.modelHibernate;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.emp.model.EmpVO;

import util.HibernateUtil;

public class HotelDAO implements HotelDAO_interface {
	private static final Base64.Encoder encoder = Base64.getEncoder();
	private static final Base64.Encoder encoder1 = Base64.getEncoder();
	private static final String GET_ALL_STMT = "from HotelVO order by hotelid";
	public static final String GET_RANDOM_HOTEL_TO_VIEW ="SELECT * FROM "
			+ "(select h.hotelName,h.hotelRatingResult,r.roomName,r.roomid,r.roomPrice,o.roomPhotoPic "
			+ "from room r,roomphoto o,hotel h where r.roomforsell = '1' and r.roomid=o.roomPhotoRoomId and r.roomHotelId = h.hotelId "
			+ "ORDER BY dbms_random.value) where rownum <= 8";
	
	@Override
	public List<Map> GET_RANDOM_HOTEL_TO_VIEW() {
		List<Map> list = new ArrayList<Map>();
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createSQLQuery(GET_RANDOM_HOTEL_TO_VIEW);
			List<Object[]> a = query.list();
			for(Object[] rs:a){
				Map<Object,Object> map = new HashMap<Object,Object>();
//				byte[] pic = String.valueOf(rs[5]).getBytes();
				
				Blob blob = (Blob) rs[5];
				byte[] blobBytes = blob.getBytes(1, (int) blob.length());
				map.put("hotelName",rs[0].toString());
				map.put("hotelRatingResult",rs[1].toString());
				map.put("roomName",rs[2].toString());
				map.put("roomid",rs[3].toString());
				map.put("roomPrice",rs[4].toString());
				map.put("roomPhotoPic",encoder.encodeToString(blobBytes));
				

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
	
	@Override
	public List<HotelVO> getAll() {
		List<HotelVO> list = null;
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ALL_STMT);
			list = query.list();
//			for(EmpVO empVO:list){
//				if(empVO.getEmpProfile() == null){
//					empVO.setBs64("");
//				} else {
//					empVO.setBs64(encoder.encodeToString(empVO.getEmpProfile()));
//				}
//			}			
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}


	@Override
	public List<HotelVO> getAll_NEED_CHECK() {
		List<HotelVO> list = null;
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from HotelVO where hotelStatus='0'");
			list = query.list();
			for(HotelVO hotelVO:list){
				if(hotelVO.getHotelRegisterPic() == null){
					hotelVO.setBs64("");
				} else {
					hotelVO.setBs64(encoder.encodeToString(hotelVO.getHotelRegisterPic()));
				}
				if(hotelVO.getHotelCoverPic() == null){
					hotelVO.setBs64_2("");
				} else {
					hotelVO.setBs64_2(encoder.encodeToString(hotelVO.getHotelCoverPic()));
				}
			}			
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	@Override
	public List<HotelVO> getAll_TO_VIEW() {
		List<HotelVO> list = null;
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from HotelVO order by hotelId");
			list = query.list();
			for(HotelVO hotelVO:list){
				if(hotelVO.getHotelRegisterPic() == null){
					hotelVO.setBs64("");
				} else {
					hotelVO.setBs64(encoder.encodeToString(hotelVO.getHotelRegisterPic()));
				}
			}			
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	@Override
	public List<HotelVO> getListBySql(String sql) {
		List<HotelVO> list = null;
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createSQLQuery("sql");
			list = query.list();
	
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	@Override
	public HotelVO getUser(String aAccount) {
		HotelVO hotelVO = null;
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from HotelVO where hotelAccount=?");
			query.setParameter(0,aAccount);
			List results = query.list();
			if(results.size() > 0){
				hotelVO  = (HotelVO) results.get(0);
		      }	
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return hotelVO;
	}
	
	@Override
	public void insert(HotelVO aHotelVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(aHotelVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			throw ex;
		}
	}
	

	@Override
	public void update(HotelVO aHotelVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			Query query = session.createSQLQuery("UPDATE hotel set  hotelType=?, hotelName=?, hotelTaxId=?, hotelRegisterPic=?"
					+ ",hotelCity=?,hotelCounty=?, hotelRoad=? ,hotelOwner=?,hotelAccount=?,hotelPhone=? "
					+ ",hotelLon=?,hotelLat=?,hotelIntro=?,hotelCoverPic=?,hotelLink=?,hotelCreditCardNo=?,hotelCreditCheckNo=?"
					+ ",hotelCreditDueDate=? where hotelId = ?");
			
			query.setParameter(0, aHotelVO.getHotelType());
			query.setParameter(1, aHotelVO.getHotelName());
			query.setParameter(2, aHotelVO.getHotelTaxId());
			query.setParameter(3, aHotelVO.getHotelRegisterPic());
			query.setParameter(4, aHotelVO.getHotelCity());
			query.setParameter(5, aHotelVO.getHotelCounty());
			query.setParameter(6, aHotelVO.getHotelRoad());
			query.setParameter(7, aHotelVO.getHotelOwner());
			query.setParameter(8, aHotelVO.getHotelAccount());
			query.setParameter(9, aHotelVO.getHotelPhone());
			query.setParameter(10, aHotelVO.getHotelLon());
			query.setParameter(11, aHotelVO.getHotelLat());
			query.setParameter(12, aHotelVO.getHotelIntro());
			query.setParameter(13, aHotelVO.getHotelCoverPic());
			query.setParameter(14, aHotelVO.getHotelLink());
			query.setParameter(15, aHotelVO.getHotelCreditCardNo());
			query.setParameter(16, aHotelVO.getHotelCreditCheckNo());
			query.setParameter(17, aHotelVO.getHotelCreditDueDate());
			query.setParameter(18, aHotelVO.getHotelId());
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			throw ex;
		}
	}


	@Override
	public void update_status_1(HotelVO aHotelVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			Query query = session.createSQLQuery("UPDATE hotel set  hotelType=?, hotelName=?, hotelTaxId=?, hotelRegisterPic=?"
					+ ",hotelCity=?,hotelCounty=?, hotelRoad=? ,hotelOwner=?,hotelAccount=?,hotelPhone=? "
					+ ",hotelLon=?,hotelLat=?,hotelIntro=?,hotelCoverPic=?,hotelLink=?,hotelCreditCardNo=?,hotelCreditCheckNo=?"
					+ ",hotelCreditDueDate=?,hotelStatus=? where hotelId = ?");
			
			query.setParameter(0, aHotelVO.getHotelType());
			query.setParameter(1, aHotelVO.getHotelName());
			query.setParameter(2, aHotelVO.getHotelTaxId());
			query.setParameter(3, aHotelVO.getHotelRegisterPic());
			query.setParameter(4, aHotelVO.getHotelCity());
			query.setParameter(5, aHotelVO.getHotelCounty());
			query.setParameter(6, aHotelVO.getHotelRoad());
			query.setParameter(7, aHotelVO.getHotelOwner());
			query.setParameter(8, aHotelVO.getHotelAccount());
			query.setParameter(9, aHotelVO.getHotelPhone());
			query.setParameter(10, aHotelVO.getHotelLon());
			query.setParameter(11, aHotelVO.getHotelLat());
			query.setParameter(12, aHotelVO.getHotelIntro());
			query.setParameter(13, aHotelVO.getHotelCoverPic());
			query.setParameter(14, aHotelVO.getHotelLink());
			query.setParameter(15, aHotelVO.getHotelCreditCardNo());
			query.setParameter(16, aHotelVO.getHotelCreditCheckNo());
			query.setParameter(17, aHotelVO.getHotelCreditDueDate());
			query.setParameter(18, aHotelVO.getHotelStatus());
			query.setParameter(19, aHotelVO.getHotelId());
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update_status(String hotelId, String hotelStatus) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();		
			Query query = session.createSQLQuery("UPDATE hotel set hotelStatus=? where hotelId =?");		
			query.setParameter(0, hotelStatus);
			query.setParameter(1, hotelId);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			throw ex;
		}
	}
	
	@Override
	public void update_hotelBlackList(String hotelId, String hotelBlackList) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();		
			Query query = session.createSQLQuery("UPDATE hotel set hotelBlackList=? where hotelId =?");		
			query.setParameter(0, hotelBlackList);
			query.setParameter(1, hotelId);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			throw ex;
		}
	}
	
	@Override
	public void update_psw(String hotelPwd, String hotelId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();		
			Query query = session.createSQLQuery("UPDATE hotel set hotelPwd=? where hotelId =?");		
			query.setParameter(0, hotelPwd);
			query.setParameter(1, hotelId);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public HotelVO findByPrimaryKey(String aHotelId) {
		HotelVO hotelVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			hotelVO = (HotelVO) session.get(HotelVO.class, aHotelId);		
				if(hotelVO.getHotelRegisterPic() == null){
					hotelVO.setBs64("");
				} else {
					hotelVO.setBs64(encoder.encodeToString(hotelVO.getHotelRegisterPic()));
				}
				if(hotelVO.getHotelCoverPic() == null){
					hotelVO.setBs64_2("");
				} else {
					hotelVO.setBs64_2(encoder1.encodeToString(hotelVO.getHotelCoverPic()));
				}
				
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return hotelVO;
	}
	
	@Override
	public byte[] getPhoto_cov(String aHotelId) {
		HotelVO hotelVO = null;
		byte[] hotelCoverPic;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from HotelVO where hotelId=?");
			
			query.setParameter(0, aHotelId);
			 List results = query.list();
			 if(results.size() > 0){
				 hotelVO  = (HotelVO) results.get(0);
			      }
			 hotelCoverPic = hotelVO.getHotelCoverPic();
			
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			throw ex;
		}
		return hotelCoverPic;
	}
	
	@Override
	public byte[] getPhoto_register(String aHotelId) {
		HotelVO hotelVO = null;
		byte[] hotelRegisterPic;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from HotelVO where hotelId=?");
			
			query.setParameter(0, aHotelId);
			 List results = query.list();
			 if(results.size() > 0){
				 hotelVO  =  (HotelVO) results.get(0);
			   }
			 hotelRegisterPic = hotelVO.getHotelRegisterPic();
			
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			throw ex;
		}
		return hotelRegisterPic;
	}
	
}
