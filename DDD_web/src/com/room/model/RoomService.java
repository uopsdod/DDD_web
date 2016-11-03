package com.room.model;

import java.util.List;
import java.util.Set;

public class RoomService {
	
	private RoomDAO_interface dao;

	public RoomService() {
		dao = new RoomJDBCDAO();
	}

	public List<RoomVO> getAll() {
		return dao.getAll();
	}
	public void insert(RoomVO aRoomVO){
		dao.insert(aRoomVO);
		
	}
	public void update(RoomVO aRoomVO){
		dao.update(aRoomVO);
		
	}
	public boolean delete(String aRoomId){
		return dao.delete(aRoomId);
		
	}
	public RoomVO findByPrimaryKey(Integer aRoomId){
		return dao.findByPrimaryKey(aRoomId);
		
	}	
	public Set<RoomVO> getOneHotelAllRoom(String aHotelId){
	
		return dao.getOneHotelAllRoom(aHotelId);	
	}	
}
