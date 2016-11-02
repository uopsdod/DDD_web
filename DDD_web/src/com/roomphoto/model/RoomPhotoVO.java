package com.roomphoto.model;

public class RoomPhotoVO implements java.io.Serializable {
	private String roomPhotoId;
	private String roomPhotoRoomId;
	private byte[] roomPhotoPic;
	
	public String getRoomPhotoId() {
		return roomPhotoId;
	}
	public void setRoomPhotoId(String aRoomPhotoId) {
		this.roomPhotoId = aRoomPhotoId;
	}
	public String getRoomPhotoRoomId() {
		return roomPhotoRoomId;
	}
	public void setRoomPhotoRoomId(String aRoomPhotoRoomId) {
		this.roomPhotoRoomId = aRoomPhotoRoomId;
	}
	public byte[] getRoomPhotoPic() {
		return roomPhotoPic;
	}
	public void setRoomPhotoPic(byte[] aRoomPhotoPic) {
		this.roomPhotoPic = aRoomPhotoPic;
	}
}
