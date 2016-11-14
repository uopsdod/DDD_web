package com.room.controler;


import java.util.*;
public class CompositeQuery {

	public static String get_aCondition_For_Oracle(String columnName, String value) 
	{
		String aCondition = null;

		if ("HOTELCITY".equals(columnName) || "HOTELCOUNTY".equals(columnName) || "HOTELID".equals(columnName) || "HOTELTYPE".equals(columnName) ||"HOTELROAD".equals(columnName) ||"HOTELOWNER".equals(columnName) ||"HOTELSTATUS".equals(columnName)||"HOTELPHONE".equals(columnName)) // 用於其他
			aCondition = columnName + "=" + value;
		else if ("HOTELACCOUNT".equals(columnName)|| "HOTELPWD".equals(columnName) || "HOTELTAXID".equals(columnName) || "HOTELBLACKLIST".equals(columnName)) // 用於其他
			aCondition = columnName + "=" + value;
		else if ("HOTELNAME".equals(columnName) || "HOTELINTRO".equals(columnName)) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";
		else if ("HOTELRATINGRESULT".equals(columnName)) // 用於varchar
			aCondition = columnName + ">=" + value;
//		else if ("hiredate".equals(columnName))                          // 用於Oracle的date
//			aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";

		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = (String)map.get(key)[0];
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

		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
//		Map<String, String[]> map = new TreeMap<String, String[]>();
//		map.put("empno", new String[] { "7001" });
//		map.put("ename", new String[] { "KING" });
//		map.put("job", new String[] { "PRESIDENT" });
//		map.put("hiredate", new String[] { "1981-11-17" });
//		map.put("sal", new String[] { "5000.5" });
//		map.put("comm", new String[] { "0.0" });
//		map.put("deptno", new String[] { "10" });
//		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key

		String finalSQL = "select * from hotel "
				          + CompositeQuery.get_WhereCondition(Data)
				          + "order by hotelId";
		System.out.println("●●finalSQL = " + finalSQL);
		return finalSQL;
	}
	
	
	
}
