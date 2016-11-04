package com.room.model;


import java.util.*;

public interface RoomDAO_interface {
	public void insert(RoomVO aRoomVO);
	public void update(RoomVO aRoomVO);
	public boolean delete(String aRoomId);
	public RoomVO findByPrimaryKey(String aRoomId);
	public List<RoomVO> getAll();
	
	public Set<RoomVO> getOneHotelAllRoom(String aHotelId);
	
}
