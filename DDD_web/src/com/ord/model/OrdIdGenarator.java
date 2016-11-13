package com.ord.model;

import java.io.Serializable;
import java.sql.*;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class OrdIdGenarator implements IdentifierGenerator {

	@Override
	public Serializable generate(SessionImplementor aSession, Object aObject) throws HibernateException {
		String ordID = null;
		Connection con = aSession.connection();
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT CONCAT(TO_CHAR(SYSDATE,'YYYYMM'),ord_seq.NEXTVAL) as ordID FROM DUAL");
			rs.next();
			ordID = rs.getString("ordID");
			con.close();
		}
		catch(SQLException e){
			throw new HibernateException("Unable to generate Sequence for OrdId ");
		}
		return ordID;
	}	
}
