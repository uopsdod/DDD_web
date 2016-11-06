package com.roomphoto.model;

import java.util.List;
import java.sql.Connection;
import com.room.model.RoomDAO_interface;
import com.room.model.RoomJDBCDAO;
import com.room.model.RoomVO;
import com.sun.javafx.collections.MappingChange.Map;

public class RoomPhotoService {
	
	private RoomPhotoDAO_interface dao;

	public RoomPhotoService() {
		dao = new RoomPhotoJDBCDAO();
	}

	public void insertRoomPhoto(String aHotelId, byte[] aRoomPhotoPic,Connection con) {

	
		dao.insert(aHotelId,aRoomPhotoPic,con);	
	}

	public RoomPhotoVO updateRoomPhoto(String aRoomPhotoId, String aRoomPhotoRoomId,byte[] aRoomPhotoPic ) 
	{

		RoomPhotoVO roomPhotoVO = new RoomPhotoVO();

		roomPhotoVO.setRoomPhotoId(aRoomPhotoId);
		roomPhotoVO.setRoomPhotoRoomId(aRoomPhotoRoomId);
		roomPhotoVO.setRoomPhotoPic(aRoomPhotoPic);
		dao.update(roomPhotoVO);
	
		return roomPhotoVO;
	}

	public void deleteRoomPhoto(String aRoomPhotoId) {
		dao.delete(aRoomPhotoId);
	}

	public RoomPhotoVO getOneRoomPhoto(String aRoomPhotoId) {
		return dao.findByPrimaryKey(aRoomPhotoId);
	}

	public List<RoomPhotoVO> getAll() {
		return dao.getAll();
	}	
	
	public List<String> getRoomAllRoomPhotoId(String aRoomId){
		return dao.getRoomAllRoomPhotoId(aRoomId);
	}
	
	public List<RoomPhotoVO> getOneAllRoomPhotoVO(String aRoomId){
		return dao.getOneAllRoomPhotoVO(aRoomId);
	}
	
	
	
	
	
}
