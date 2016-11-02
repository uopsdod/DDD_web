package com.roomphoto.model;

import java.util.List;

import com.room.model.RoomVO;

public interface RoomPhotoDAO_interface {
	
	public boolean insert(RoomPhotoVO aRoomPhotoVO,String aHotelId);
	public boolean update(RoomPhotoVO aRoomPhotoVO);
	public boolean delete(String aRoomPhotoId);
	public RoomPhotoVO findByPrimaryKey(String aRoomPhotoId);
	public List<RoomPhotoVO> getAll();
	public List<String> getRoomAllRoomPhotoId(String aRoomPhotoRoomId);
		
}
