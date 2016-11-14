package com.room.controler;


import java.util.*;
public class RoomCompositeQuery {

	public static String get_aCondition_For_Oracle(String columnName, String value) 
	{
		String aCondition = null;
	
		if ( "ROOMID".equals(columnName) ||"ROOMTWOBED".equals(columnName) ||"ROOMONEBED".equals(columnName) ||"ROOMCAPACITY".equals(columnName) || "ROOMHOTELID".equals(columnName) ) // 用於其他
			aCondition = columnName + "=" + value;
		else if ("ROOMTOTALNO".equals(columnName)||"ROOMPRICE".equals(columnName)) // 用於varchar
			aCondition = columnName + ">=" + value;
		else if ("ROOMBOTTOMPRICE".equals(columnName)||"ROOMREMAINNO".equals(columnName)) // 用於varchar
			aCondition = columnName + ">=" + value;
		else if ("ROOMDEFAULTNO".equals(columnName)||"ROOMDISCOUNTHR".equals(columnName)||"ROOMDISCCOUNTPERCENT".equals(columnName)) // 用於varchar
			aCondition = columnName + ">=" + value;
		
		else if ( "ROOMFORSELL".equals(columnName) || "ROOMFORSELLAUTO".equals(columnName)|| "ROOMONEPRICE".equals(columnName)) // 用於其他
			aCondition = columnName + "=" + value;
		else if ("ROOMNAME".equals(columnName)||"ROOMFUN".equals(columnName)||"ROOMMEAL".equals(columnName)||"ROOMSLEEP".equals(columnName)||"ROOMFACILITY".equals(columnName)||"ROOMSWEETFACILITY".equals(columnName)) // 用於其他
			aCondition = columnName + " like '%" + value + "%'";
		
	
//		else if ("hiredate".equals(columnName))                          // 用於Oracle的date
//			aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";

		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String> map) {

		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
	
			String value = map.get(key);
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
	}

	public static String GetSQLString(Map Data) {

		String finalSQL = "select * from room "
				          + RoomCompositeQuery.get_WhereCondition(Data)
				          + "order by roomId";
		System.out.println("●●finalSQL = " + finalSQL);
		return finalSQL;
	}
	
	
	
}
