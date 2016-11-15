package util;

import java.util.*;

public class CompositeQureyOrd {
	public static String getAConditionForOracle(String aColumnName,String aValue){
		String aCondition = null;
		
		/* 數字 */
//		ordPrice
//		ordRatingStarNo
		
		if("ordPrice".equals(aColumnName) || "ordRatingStarNo".equals(aColumnName) ){
			aCondition = aColumnName + "=" + aValue;
		}
		
		/* 字串 */
//		ordId
//		ordRoomId
//		ordMemId
//		ordHotelId
//		ordStatus
//		ordMsgNo
		
		else if("ordId".equals(aColumnName) || "ordRoomId".equals(aColumnName) || "ordMemId".equals(aColumnName) || 
				"ordHotelId".equals(aColumnName) || "ordStatus".equals(aColumnName) || "ordMsgNo".equals(aColumnName) ) {
			aCondition = aColumnName + " like '%" + aValue + "%'";
		}

		return aCondition + " ";
	}
	
	
	public static String getWhereCondition(Map<String, String[]> aMap){
		Set<String> keys = aMap.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		
		for(String key : keys){
			String value = aMap.get(key)[0];
			
			if(value != null && value.trim().length() !=0 && !"action".equals(key)){
				count++;
				String aCondition = getAConditionForOracle(key, value.trim());
				
				if(count == 1){
					whereCondition.append(" where " + aCondition);
				}
				else{
					whereCondition.append(" and " + aCondition);
				}
				System.out.println("有送出查詢資料的欄位數 count=" + count);
			}

		}
		
		return whereCondition.toString();
	}
	
}
