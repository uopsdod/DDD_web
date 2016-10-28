package com.memrep.model;

import java.util.List;

public interface MemRepDAO_interface {
	public void insert(MemRepVO aMemrepVO);
	public void update(MemRepVO aMemrepVO);
	public void delete(String aMemrepId);
	
	public List<MemRepVO> getAll();
	public List<MemRepVO> getAllByMemRepStatus(String aMemRepStatus); //0.未審核 1.已審核未通過 2.已審核已通過
	public MemRepVO findByPrimaryKey(String aMemrepId);	
}
