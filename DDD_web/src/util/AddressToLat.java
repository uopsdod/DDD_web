package util;


import java.net.*;
import java.io.*;

public class AddressToLat {
	public static String turn(String lat_lng){
		URL url = null;
		String result =null;
		String lat =null;
		String lng =null;
		try {
			url = new URL("http://maps.googleapis.com/maps/api/geocode/xml?address="+java.net.URLEncoder.encode(lat_lng,"UTF-8")+"&sensor=false&language=zh-TW"); // 建立URL物件url , 以 中文台北市(之地址換算經緯度為例)
		} catch (MalformedURLException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		try {
			// 以URL物件建立URLConnection物件
			URLConnection urlConn = url.openConnection();
			// 以URLConnection物件取得輸入資料流
			InputStream ins = urlConn.getInputStream();

			// 建立URL資料流
			BufferedReader br = new BufferedReader(new InputStreamReader(ins, "UTF-8"));
			String data;
			while ((data = br.readLine()) != null) {
//				System.out.println(data);
				if (data.contains("<lat>")) {
					lat =data.substring(data.indexOf("<lat>") + 5, data.indexOf("</lat>"));
				}
				else if(data.contains("<lng>")){
					lng=data.substring(data.indexOf("<lng>") + 5, data.indexOf("</lng>"));
				}
			}
			result=lat+","+lng;
			br.close();
			ins.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	public static void main(String[] args) {
		String a = "桃園市中壢區中北路340號";
		
		String c =AddressToLat.turn(a);
		System.out.println(c);
		
		String[] token = c.split(",");
		String lat=token[0];
		String lng=token[1];
		System.out.println(lat);
		System.out.println(lng);
		
	}

}
