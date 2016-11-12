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

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;

import com.emp.model.EmpVO;
import com.hotel.model.HotelVO;
//
//import com.mem.model.MemDAO;
//import com.mem.model.MemJDBCDAO;
import com.mem.model.MemVO;

public class CompositeQuery_anyDB_Hibernate {
	
	public static Criteria getAll(Criteria aQuery, Map<String, String[]> aMap, Class aVOClass){
		Set<String> cols = aMap.keySet();
		for (String colName : cols) {
			String value = aMap.get(colName)[0];
			if (value != null && value.trim().length() != 0) {
				// 用aVOClass + Metadata判斷資料型別:
				SessionFactory sessionFactory  = HibernateUtil.getSessionFactory();
				ClassMetadata classMetadata = sessionFactory.getClassMetadata(aVOClass);
				// 取得VO類別的所有實體變數名稱
				String[] names = classMetadata.getPropertyNames();
				// 取得VO類別的所有實體變數資料型態
				org.hibernate.type.Type[] types = classMetadata.getPropertyTypes();
				
				// 開始找:
				for (int i = 0; i < names.length; i++){
					// 比對現在colName對到的colName，得到其index，再藉由此index，找出其對應的dataType
					if (colName.equals(names[i])){
						// 把query放進去增加限制條件
						// 並將現在確定的colName,colValue,以及其對應的dataType放入方法內
						queryAddRestrictions(aQuery, types[i].getName(), colName, value);
						System.out.println(colName + ": " + types[i].getName());
						break;
					}
				}// end for
			}// end if
		}// end for 

		return aQuery;
	}
		

	private static void queryAddRestrictions(Criteria aQuery, String aTypeName, String aColName, String aValue) {
		switch(aTypeName){
			case "string":
				aQuery.add(Restrictions.like(aColName, aValue));
				break;
			case "integer":
				aQuery.add(Restrictions.eq(aColName, new Integer(aValue)));    
				break;
			case "double":
				aQuery.add(Restrictions.eq(aColName, new Double(aValue)));               
				break;
			case "date":
				aQuery.add(Restrictions.eq(aColName, java.sql.Date.valueOf(aValue))); // 讚
				break;
//			case "binary":
//			break;	
		}
	}
}
