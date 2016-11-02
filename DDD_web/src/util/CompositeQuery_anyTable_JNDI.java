/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *        所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */

package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mem.model.MemDAO;
import com.mem.model.MemJDBCDAO;
import com.mem.model.MemVO;

public class CompositeQuery_anyTable_JNDI {
	private static DataSource ds  = null;
	static{
		try{
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
					
		}catch(NamingException e){
			e.printStackTrace(System.err);
		}
	}


	public static String get_aCondition_For_Oracle(String columnName, String value, String tableName) {

		ResultSet rs = null;
		String aCondition = null;
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM " + tableName);) {
			
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmt = rs.getMetaData();
			int numberOfColumns = rsmt.getColumnCount();

			// dataType
			// 先看看目前傳入的欄位名稱是指此TABLE中的哪個欄位
			for (int i = 1; i <= numberOfColumns; i++) {
				if (columnName.toUpperCase().equals(rsmt.getColumnName(i))){ // 注意: 資料庫取出的欄位名都是大寫
					System.out.println("Current Column is " + rsmt.getColumnName(i));
					// 再來判斷此欄位屬於哪一種資料型態
					if ("VARCHAR2".equals(rsmt.getColumnTypeName(i))){
						//System.out.println(rsmt.getColumnName(i)  +" is a VARCHAR2 type");	
						aCondition = columnName + " like '%" + value + "%'";
					}else if("DATE".equals(rsmt.getColumnTypeName(i))){
						//System.out.println(rsmt.getColumnName(i)  +" is a DATE type");
						aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";
					}else if ("TIMESTAMP".equals(rsmt.getColumnTypeName(i))){
						//System.out.println(rsmt.getColumnName(i)  +" is a TIMESTAMP type");
						aCondition = "to_char(" + columnName + ",'YYYYMMDD HH24:MI:SSxFF3')='" + value + "'";
					}
					else{
						//System.out.println(rsmt.getColumnName(i)  +" is other type.");
						aCondition = columnName + "=" + value;
					}
				}
			}
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}

		return aCondition + " ";
	}

	private static String get_WhereCondition(Map<String, String[]> map, String tableName) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim(), tableName);

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);
			}
		}
		System.out.println("有送出查詢資料的欄位數count = " + count);

		return whereCondition.toString();
	}
	
	public static String getQuerySQL(Map<String, String[]> map, String tablename){
		StringBuilder finalSql = new StringBuilder();
		finalSql.append("SELECT * FROM " + tablename + " "); // 查詢指令
		finalSql.append(get_WhereCondition(map,tablename)); // 加上where子句
		System.out.println("finalSql: " + finalSql);
		return finalSql.toString();
	}
		

	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳
		// java.util.Map<java.lang.String,java.lang.String[]> 之測試
		MemJDBCDAO dao_mem = new MemJDBCDAO();
		MemVO memVO = dao_mem.getAll().get(0);
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("memName", new String[]{memVO.getMemName()});
		map.put("memAccount", new String[]{memVO.getMemAccount()});
		map.put("memBirthDate", new String[]{memVO.getMemBirthDate().toString()});
		map.put("memLiveBudget", new String[]{memVO.getMemLiveBudget().toString()});
		
		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key

		String finalSQL = "select * from mem " + CompositeQuery_anyTable_JNDI.get_WhereCondition(map, "mem");
		System.out.println("●●finalSQL = " + finalSQL);

	}
}
