package com.roomphoto.model;

import java.util.List;
import java.sql.Connection;

import com.room.model.RoomVO;

public interface RoomPhotoDAO_interface {
	
	public boolean insert(String aHotelId,byte[] Picbyte,Connection con);
	public boolean update(RoomPhotoVO aRoomPhotoVO);
	public boolean delete(String aRoomPhotoId);
	public RoomPhotoVO findByPrimaryKey(String aRoomPhotoId);
	public List<RoomPhotoVO> getAll();
	public List<String> getRoomAllRoomPhotoId(String aRoomId);
	public List<RoomPhotoVO> getOneAllRoomPhotoVO(String aRoomId);	
}
