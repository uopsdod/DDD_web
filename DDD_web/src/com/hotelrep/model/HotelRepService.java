package com.hotelrep.model;
import java.util.List;

public class HotelRepService {
	private HotelRepDAO_interface dao;
	public HotelRepService(){
		this.dao = new HotelRepHibernateDAO();
	}
	
	public HotelRepVO insert(String aHotelRepHotelId, String aHotelRepMemId, String aHotelRepOrdId, String aHotelRepEmpId, String aHotelRepContent){
		HotelRepVO hotelRepVO = new HotelRepVO();
		
		//hotelRepVO.setHotelRepHotelId(aHotelRepHotelId);
		com.hotel.model.HotelVO hotelVO = new com.hotel.model.HotelVO();
		hotelVO.setHotelId(aHotelRepHotelId);
		hotelRepVO.setHotelRepHotelVO(hotelVO);
		
		//hotelRepVO.setHotelRepMemId(aHotelRepMemId);
		com.mem.model.MemVO memVO = new com.mem.model.MemVO();
		memVO.setMemId(aHotelRepMemId);
		hotelRepVO.setHotelRepMemVO(memVO);
		
		//hotelRepVO.setHotelRepOrdId(aHotelRepOrdId);
		com.ord.model.OrdVO ordVO = new com.ord.model.OrdVO();
		ordVO.setOrdId(aHotelRepOrdId);
		hotelRepVO.setHotelRepOrdVO(ordVO);
		
		if(!aHotelRepEmpId.isEmpty()){
			com.emp.model.EmpVO empVO = new com.emp.model.EmpVO();
			empVO.setEmpId(aHotelRepEmpId);
			hotelRepVO.setHotelRepOrdVO(ordVO);
		}
		
		if(!aHotelRepContent.isEmpty()){
			hotelRepVO.setHotelRepContent(aHotelRepContent);
		}
		
		/* 新增時 都是 0 (未審核) */
		hotelRepVO.setHotelRepStatus("0");
		
		/* 新增的時間就是現在時間 */
		java.util.Date now = new java.util.Date();
		java.sql.Date currentTime = new java.sql.Date(now.getTime());
		hotelRepVO.setHotelRepDate(currentTime);
		
		/* 新增時 預設員工編號跟處理時間都是null */
		
		String hotelRepVOId  = dao.insert(hotelRepVO);
		return dao.findByPrimaryKey(hotelRepVOId);
	}
	
	public HotelRepVO update(HotelRepVO aHotelRepVO){
		
		 dao.update(aHotelRepVO);
		 
		/* 處理的時間就是現在時間 */
		java.util.Date now = new java.util.Date();
		java.sql.Date currentTime = new java.sql.Date(now.getTime());
		aHotelRepVO.setHotelRepReviewDate(currentTime);
		 
		 return dao.findByPrimaryKey(aHotelRepVO.getHotelRepId());
	}
	
	public void delete(String aHotelRepId){
		dao.delete(aHotelRepId);
	}
	
	public List<HotelRepVO> getAll(){
		return dao.getAll();
	}
	public List<HotelRepVO> getAllByHotelRepStatus(String aHotelRepStatus){
		return dao.getAllByHotelRepStatus(aHotelRepStatus);
	}
	public HotelRepVO findByPrimaryKey(String aHotelRepId){
		return dao.findByPrimaryKey(aHotelRepId);
	}
		
}
