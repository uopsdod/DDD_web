package com.roomphoto.model;

import java.io.Serializable;
import java.sql.*;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class RoomPhotoIdGenarator implements IdentifierGenerator {

	@Override
	public Serializable generate(SessionImplementor aSession, Object aObject) throws HibernateException {
		String roomPhotoId = null;
		Connection con = aSession.connection();
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT roomphoto_seq.nextval as nextval FROM DUAL");
			rs.next();
			String nextval = Integer.toString(rs.getInt("nextval"));
			roomPhotoId = nextval;
			con.close();
		}
		catch(SQLException e){
			throw new HibernateException("Unable to generate Sequence for OrdId ");
		}
		System.out.println("roomPhotoId generated: " + roomPhotoId);
		return roomPhotoId;
	}	
}
