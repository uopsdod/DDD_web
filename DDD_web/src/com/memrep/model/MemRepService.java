package com.memrep.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.emp.model.EmpVO;
import com.hotel.model.HotelVO;
import com.mem.model.MemVO;
import com.ord.model.OrdService;
import com.ord.model.OrdVO;

public class MemRepService {
	private MemRepDAO_interface dao;
	public MemRepService(){
		this.dao = new MemRepHibernateDAO();
	}
	public void insert(String aMemRepOrdId, String aMemRepContent){
		MemRepVO memRepVO = new MemRepVO();
//		HotelVO hotelVO = new HotelVO();
//		MemVO memVO = new MemVO();
		
		OrdService dao_ord = new OrdService();
		OrdVO ordVO = dao_ord.getOneOrd(aMemRepOrdId);
//		hotelVO.setHotelId(ordVO.getOrdHotelId());
//		memVO.setMemId(ordVO.getOrdMemId());
		
		
		memRepVO.setMemRepOrdVO(ordVO); // FK
		memRepVO.setMemRepHotelVO(ordVO.getOrdHotelVO()); // FK
		memRepVO.setMemRepMemVO(ordVO.getOrdMemVO()); // FK
		memRepVO.setMemRepContent(aMemRepContent);
		memRepVO.setMemRepStatus("0");// 0.未審核 1.已審核未通過 2.已審核已通過
		memRepVO.setMemRepDate(new java.sql.Date(new java.util.Date().getTime()));
		memRepVO.setMemRepEmpVO(null);
		memRepVO.setMemRepReviewDate(null);
		
		this.dao.insert(memRepVO);
	}
	
	public void insert(MemRepVO aMemrepVO){
		this.dao.insert(aMemrepVO);
	}
	public void update(MemRepVO aMemrepVO){
		this.dao.update(aMemrepVO);
	}
	public void delete(String aMemrepId){
		this.dao.delete(aMemrepId);
	}
	public List<MemRepVO> getAll(){
		return this.dao.getAll();
	}
//	public List<MemRepVO> getAllByMemRepStatus(String aMemRepStatus){//0.未審核 1.已審核未通過 2.已審核已通過
//		return this.dao.getAllByMemRepStatus(aMemRepStatus);
//	}
	public MemRepVO getOneMemRep(String aMemrepId){
		return this.dao.findByPrimaryKey(aMemrepId);
	}
	
	public List<MemRepVO> getAll(Map<String, String[]> aMap){
		return this.dao.getAll(aMap);
	}
	
	public MemRepVO findByMemRepOrdId(String aMemRepOrdId){
		return this.dao.findByMemRepOrdId(aMemRepOrdId);
	}
}
