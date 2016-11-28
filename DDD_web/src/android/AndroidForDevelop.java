package android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mem.model.MemVO;

/**
 * Servlet implementation class AndroidForDevelop
 */
@WebServlet("/android/AndroidForDevelop")
public class AndroidForDevelop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req,res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(),
				JsonObject.class);
		//String action = jsonObject.get("action").getAsString();
		String action = "AllSell";
		String outStr = "";
		// 注意:是大寫
		if ("AllSell".equals(action)) {
			//String url = req.getContextPath() + "/room/room.do";
			String url = "http://10.120.25.22:8081/DDD_web/room/room.do";
			System.out.println("url: " + url);
			String charset = "UTF-8";  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()
			String param1 = "AllSell";
			//String param2 = "value2";
			// ...

			String query = String.format("action=%s", URLEncoder.encode(param1, charset));
			//String query = String.format("action=%s&param2=%s", URLEncoder.encode(param1, charset), URLEncoder.encode(param2, charset));
			
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setDoOutput(true); // Triggers POST.
			connection.setRequestProperty("Accept-Charset", charset);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
			
			// getOutputStream會自動開啟連線:
			try (OutputStream output = connection.getOutputStream()) {
			    output.write(query.getBytes(charset));
			    //output.close(); // try-catch-with resources會自動關閉
			}
//			InputStream response = connection.getInputStream();
			//connection.connect();
			
	        int responseCode = connection.getResponseCode();
	        // 確認能與server建立Socket連線
	        if (responseCode == 200) {
	        	System.out.println("response code: " + responseCode);
	            // connection.getInputStream() - 等待server回應，如果還沒有回應就hold在這邊
	            //BufferedReader br_02 = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        } else {
	            System.out.println("response code: " + responseCode);
	        }
	        connection.disconnect();
			
		}	
		
		
		
	}

}
