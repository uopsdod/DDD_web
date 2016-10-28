package com.serv.model;

import java.util.*;

public interface ServDAO_interface {
	
	public void insert(ServVO servVO);
	public void update(ServVO servVO);
	public void delete(String servVO);
	public ServVO findByPrimaryKey(String aServId);
	public List<ServVO> getAll();
}
