package com.ord.model;
import java.util.*;

public interface OrdDAO_interface {
	public int insert(OrdVO aOrdVO);
	public int update(OrdVO aOrdVO);
	public int delete(String aOrdID);
	public OrdVO findByPrimaryKey(String aOrdID);
	public List<OrdVO> getAll();
}
