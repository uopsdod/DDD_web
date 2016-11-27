package com.hotelrep.model;

import java.io.Serializable;
import java.sql.*;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class HotelRepIdGenerator implements IdentifierGenerator {

	public Serializable generate(SessionImplementor session, Object object)
			throws HibernateException {

		String hotelRepId = null;
		Connection con = session.connection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT hotelrep_seq.NEXTVAL as nextval FROM DUAL");
			rs.next();
			String nextval = Integer.toString(rs.getInt("nextval"));
			hotelRepId = nextval;
			con.close();
		} catch (SQLException e) {
			throw new HibernateException("Unable to generate Sequence");
		}
		System.out.println("hotelRepId generated: " + hotelRepId);
		return hotelRepId;
	}
}
