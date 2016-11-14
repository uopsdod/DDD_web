package com.room.model;


import java.util.*;



import java.sql.Connection;


public interface RoomDAO_interface {
	public void insert(RoomVO aRoomVO,Connection con);
	public void update(RoomVO aRoomVO);
	public boolean delete(String aRoomId);
	public RoomVO findByPrimaryKey(String aRoomId);
	public List<RoomVO> getAll();
	public List<RoomVO> getListBySQL(String SQL);
	public Set<RoomVO> getOneHotelAllRoom(String aHotelId);
	
}
