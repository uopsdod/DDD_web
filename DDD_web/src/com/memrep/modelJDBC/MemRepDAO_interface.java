package com.memrep.modelJDBC;

import java.util.List;
import java.util.Map;

public interface MemRepDAO_interface {
	public static final String tableName = "memRep"; // 萬用查詢用
	public void insert(MemRepVO aMemrepVO);
	public void update(MemRepVO aMemrepVO);
	public void delete(String aMemrepId);
	
	public List<MemRepVO> getAll();
	public List<MemRepVO> getAllByMemRepStatus(String aMemRepStatus); //0.未審核 1.已審核未通過 2.已審核已通過
	public MemRepVO findByPrimaryKey(String aMemrepId);	
	//萬用查詢
	public List<MemRepVO> getAll(Map<String, String[]> map); 
}
