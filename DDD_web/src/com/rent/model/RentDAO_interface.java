package com.rent.model;

import java.util.List;

import com.room.model.RoomVO;

public interface RentDAO_interface {
	
	
	public boolean insert(RentVO aRentVO);
	public boolean update(RentVO aRentVO);
	public boolean delete(String aRentId);
	public RentVO findByPrimaryKey(String aRentId);
	public List<RentVO> getAll();

	
	
}
