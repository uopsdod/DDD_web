package com.hotelrep.model;
import java.util.List;

public class HotelRepService {
	private HotelRepDAO_interface dao;
	public HotelRepService(){
		this.dao = new HotelRepHibernateDAO();
	}
	
	public HotelRepVO addHotelRep(String aHotelRepHotelId, String aHotelRepMemId, String aHotelRepOrdId, String aHotelRepEmpId, String aHotelRepContent, String aHotelRepStatus){
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
		
		hotelRepVO.setHotelRepStatus(aHotelRepStatus);
		
		/* 新增的時間就是現在時間 */		
		/* 新增時 預設員工處理時間是null */
		hotelRepVO.setHotelRepDate( new java.sql.Date( new java.util.Date().getTime() ));
				
		String hotelRepId  = dao.insert(hotelRepVO);
		return dao.findByPrimaryKey(hotelRepId);
	}
	
	public HotelRepVO updateHotelRep(HotelRepVO aHotelRepVO){		 
		/* 處理的時間就是現在時間 */
		aHotelRepVO.setHotelRepReviewDate( new java.sql.Date( new java.util.Date().getTime() ));
 
		dao.update(aHotelRepVO);
		
		return dao.findByPrimaryKey(aHotelRepVO.getHotelRepId());
	}
	
	public void deleteHotelRep(String aHotelRepId){
		dao.delete(aHotelRepId);
	}
	
	public List<HotelRepVO> getAll(){
		return dao.getAll();
	}
	public List<HotelRepVO> getAllByHotelRepStatus(String aHotelRepStatus){
		return dao.getAllByHotelRepStatus(aHotelRepStatus);
	}
	public HotelRepVO getOneHotelRep(String aHotelRepId){
		return dao.findByPrimaryKey(aHotelRepId);
	}
		
}
