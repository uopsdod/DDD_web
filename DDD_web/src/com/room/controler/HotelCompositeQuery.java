package com.room.controler;


import java.util.*;
public class HotelCompositeQuery {

	public static String get_aCondition_For_Oracle(String columnName, String value) 
	{
		String aCondition = null;

		if ( "HOTELID".equals(columnName) || "HOTELTYPE".equals(columnName) ||"HOTELROAD".equals(columnName) ||"HOTELOWNER".equals(columnName) ||"HOTELSTATUS".equals(columnName)||"HOTELPHONE".equals(columnName)) // 用於其他
			aCondition = columnName + "=" + value;
		else if ("HOTELACCOUNT".equals(columnName)|| "HOTELPWD".equals(columnName) || "HOTELTAXID".equals(columnName) || "HOTELBLACKLIST".equals(columnName)) // 用於其他
			aCondition = columnName + "=" + value;
		else if ("HOTELNAME".equals(columnName) || "HOTELINTRO".equals(columnName)) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";
		else if ("HOTELCITY".equals(columnName) || "HOTELCOUNTY".equals(columnName)) // 用於varchar
			aCondition = columnName + "='" + value+"'";
		else if ("HOTELRATINGRESULT".equals(columnName)) // 用於varchar
			aCondition = columnName + ">=" + value;
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

				//System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
	}

	public static String GetSQLString(Map Data) {


		String finalSQL = "select * from hotel "
				          + HotelCompositeQuery.get_WhereCondition(Data)
				          + "order by hotelId";
//		System.out.println("●●finalSQL = " + finalSQL);
		return finalSQL;
	}
	
	
	
}
