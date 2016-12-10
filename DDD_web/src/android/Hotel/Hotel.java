package android.Hotel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hotel.model.HotelJDBCDAO;
import com.hotel.model.HotelService;
import com.hotel.model.HotelVO;
import com.mem.model.MemVO;


import javafx.scene.effect.Light.Spot;
@SuppressWarnings("serial")
public class Hotel extends HttpServlet {
	// private ServletContext context;
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	private List<String> category;
	private List<Spot> computerList;
	private List<Spot> comicList;
 

	@Override
	public void doPost(HttpServletRequest rq, HttpServletResponse rp)
			throws ServletException, IOException {
		rq.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		BufferedReader br = rq.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line); 
		}
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(),
				JsonObject.class);
		String action = jsonObject.get("action").getAsString();
		
		String outStr = "";
		if (action.equals("getOne")) {
			
			
			String id = jsonObject.get("id").getAsString();
			HotelJDBCDAO dao = new HotelJDBCDAO();
			HotelVO hotelVO = dao.findByPrimaryKey(id);
			outStr = gson.toJson(hotelVO);
			System.out.println(outStr);
			rp.setContentType(CONTENT_TYPE);
			PrintWriter out = rp.getWriter();
			out.println(outStr);
			
		}else if(action.equals("getAll")){
			
			HotelJDBCDAO dao = new HotelJDBCDAO();
			List<HotelVO> list = new ArrayList<HotelVO>();
			list = dao.getAll();
			for (HotelVO myVO: list){
				myVO.setHotelCoverPic(null);
				myVO.setHotelRegisterPic(null);
			}
			
			outStr = gson.toJson(list);
			System.out.println(outStr);
			rp.setContentType(CONTENT_TYPE);
			PrintWriter out = rp.getWriter();
			out.println(outStr);
		}else if(action.equals("getImage")){
			
			OutputStream os = rp.getOutputStream();
			HotelService dao = new HotelService();
			String id = jsonObject.get("id").getAsString();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] image = dao.getOne(id).getHotelCoverPic();
			if (image != null) {
				image = ImageUtil.shrink(image, imageSize);
				rp.setContentType("image/jpeg");
				rp.setContentLength(image.length);
			}
			os.write(image);
			System.out.println("image"+image);
			
		}else if(action.equals("hotelMemCheck")){
			String hotelUserName = jsonObject.get("userName").getAsString();
			String hotelPassword = jsonObject.get("password").getAsString();
			HotelService hotelServ = new HotelService();
			HotelVO hotelVO = hotelServ.hotelMemCheck(hotelUserName, hotelPassword);
			
			outStr = gson.toJson(hotelVO);
			System.out.println(outStr);
			rp.setContentType(CONTENT_TYPE);
			PrintWriter out = rp.getWriter();
			out.println(outStr);
		}
		
		// ��hotelVO�নJSON�r��A�^��
		
		
	}

	@Override
	public void doGet(HttpServletRequest rq, HttpServletResponse rp)
			throws ServletException, IOException {
		Gson gson = new Gson();
		String category_json = gson.toJson(category);
		String computerList_json = gson.toJson(computerList);
		String comicList_json = gson.toJson(comicList);
		rp.setContentType(CONTENT_TYPE);
		PrintWriter out = rp.getWriter();
		out.println("<H3>Category</H3>");
		out.println(category_json);
		out.println("<H3>Computer Books</H3>");
		out.println(computerList_json);
		out.println("<H3>Comic Books</H3>");
		out.println(comicList_json);
	}

}