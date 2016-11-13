package com.chat.model;

import java.io.Serializable;
import java.sql.*;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class ChatIdGenerator implements IdentifierGenerator {

	public Serializable generate(SessionImplementor session, Object object)
			throws HibernateException {

		String chatId = null;
		Connection con = session.connection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT chat_seq.NEXTVAL as nextval FROM DUAL");
			rs.next();
			String nextval = Integer.toString(rs.getInt("nextval"));
			chatId = nextval;
			con.close();
		} catch (SQLException e) {
			throw new HibernateException("Unable to generate Sequence");
		}
		System.out.println("chatId generated: " + chatId);
		return chatId;
	}
}
