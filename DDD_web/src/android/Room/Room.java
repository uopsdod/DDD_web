package android.Room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mem.model.MemVO;
import com.room.controler.RoomServlet;
import com.room.model.RoomJDBCDAO;
import com.room.model.RoomService;
import com.room.model.RoomVO;
import com.roomphoto.model.RoomPhotoJDBCDAO;
import com.roomphoto.model.RoomPhotoService;
import com.roomphoto.model.RoomPhotoVO;

import com.hotel.model.HotelJDBCDAO;
import android.Hotel.ImageUtil;
import javafx.scene.effect.Light.Spot;
@SuppressWarnings("serial")
public class Room extends HttpServlet {
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
			
			System.out.println("1231321231321321313213132132");
			String id = jsonObject.get("id").getAsString();
			Map<String, Map> one = RoomServlet.OnData;
			Map<String, Integer> two = one.get(id);
			int price = two.get("price");
			RoomService dao = new RoomService();
			RoomVO hotelVO = dao.findByPrimaryKey(id);
			hotelVO.setRoomPrice(price);
			outStr = gson.toJson(hotelVO);
			rp.setContentType(CONTENT_TYPE);
			PrintWriter out = rp.getWriter();
			out.println(outStr);
			System.out.println(outStr);
			System.out.println("id123123132131313132 " + id);
			
		}else if(action.equals("getOneAllPhotoId")){
			
			String id = jsonObject.get("id").getAsString();
			RoomPhotoService dao = new RoomPhotoService();
			List<String> list = dao.getRoomAllRoomPhotoId(id);
//			JSONArray array = new JSONArray();
//			for(String roomPhotoId: list){
//				array.put(roomPhotoId);
//			} 
//			Object aaa = (Object) array;
			outStr = gson.toJson(list);
			rp.setContentType(CONTENT_TYPE);
			PrintWriter out = rp.getWriter();
			out.println(outStr);
		}else if(action.equals("getAll")){  // 取得當前飯店內的所有房型
			
			String id = jsonObject.get("id").getAsString();
			System.out.println("------------id------------------  :"+id);
	
			
			RoomService dao = new RoomService();
			RoomVO roomVO = dao.findByPrimaryKey(id);
			
			Set<RoomVO> set = dao.getOneHotelAllRoom(id);
			
//			for(RoomVO newSet : set){
//				Map<String, Map> one = RoomServlet.OnData;
//				Map<String, Integer> two = one.get(newSet.getRoomId());
//				System.out.println("------------id------------------  :"+ newSet.getRoomId());
//				System.out.println("========================" + two.get("price"));
//				int price = two.get("price"));
//				newSet.setRoomPrice(price);
//			}
			
			outStr = gson.toJson(set);
			rp.setContentType(CONTENT_TYPE);
			PrintWriter out = rp.getWriter();
			out.println(outStr);
			System.out.println(outStr);
			
		}else if(action.equals("getImage")){

			OutputStream os = rp.getOutputStream();
			RoomPhotoService dao = new RoomPhotoService();
			String id = jsonObject.get("id").getAsString();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			System.out.println("id : " + id);
			List<String> list = dao.getRoomAllRoomPhotoId(id);
			String roomPhotoId = list.get(0);
			RoomPhotoVO roomPhotoVO = dao.getOneRoomPhoto(roomPhotoId);
			byte[] image = roomPhotoVO.getRoomPhotoPic();
			if (image != null) {
				image = ImageUtil.shrink(image, imageSize);
				rp.setContentType("image/jpeg");
				rp.setContentLength(image.length);
			}
			os.write(image);
			System.out.println("image"+image);
		}else if(action.equals("getAllImage")){
			
			byte[] image = null;
			OutputStream os = rp.getOutputStream();
			RoomPhotoService dao = new RoomPhotoService();
			String id = jsonObject.get("id").getAsString();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			System.out.println("id : " + id);
			RoomPhotoVO roomPhotoVO = dao.getOneRoomPhoto(id);
			
				image = roomPhotoVO.getRoomPhotoPic();
				if (image != null) {
					image = ImageUtil.shrink(image, imageSize);
					rp.setContentType("image/jpeg");
					rp.setContentLength(image.length);
				}
				os.write(image);
				System.out.println("image"+image);
		
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