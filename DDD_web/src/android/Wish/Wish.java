package android.Wish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
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
import com.wish.model.WishService;
import com.wish.model.WishVO;
import com.hotel.model.HotelJDBCDAO;
import android.Hotel.ImageUtil;
import javafx.scene.effect.Light.Spot;
@SuppressWarnings("serial")
public class Wish extends HttpServlet {
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
			Map<String, Map> one = RoomServlet.OnData;
			Map<String, Integer> two = one.get(id);
			int price = two.get("price");
			RoomService dao = new RoomService();
			RoomVO hotelVO = dao.findByPrimaryKey(id);
			hotelVO.setRoomPrice(price);
//			System.out.println("======================" + price);
			outStr = gson.toJson(hotelVO);
			rp.setContentType(CONTENT_TYPE);
			PrintWriter out = rp.getWriter();
			out.println(outStr);
//			System.out.println(outStr);
//			System.out.println("id123123132131313132 " + id);
			
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
		}else if(action.equals("getAll")){  // 取得當前會員願望清單內所有的房間資料
			
			
			String id = jsonObject.get("id").getAsString();
//			System.out.println("------------id------------------  :"+id);
	
			WishService wishDAO = new WishService();
			RoomService roomDAO = new RoomService();
			RoomVO roomVO = null;
			List<RoomVO> roomVOList = new ArrayList<RoomVO>();
			
			List<String> wishRoomIdList = wishDAO.SgetAllWishRoomId(id);
			for(String wishRoomId : wishRoomIdList){
				roomVO = roomDAO.findByPrimaryKey(wishRoomId);
				if(roomVO.getRoomForSell() != false){
					Map<String, Map> one = RoomServlet.OnData;
					Map<String, Integer> two = one.get(roomVO.getRoomId());
					Integer price = two.get("price");
					roomVO.setRoomPrice(price);
				}
				roomVOList.add(roomVO);
			}
			
				
			
			outStr = gson.toJson(roomVOList);
			rp.setContentType(CONTENT_TYPE);
			PrintWriter out = rp.getWriter();
			out.println(outStr);
			System.out.println(outStr);
			
		}else if(action.equals("getImage")){ //取得當前會員願望清單內所有房型的封面圖片

			int imageSize = jsonObject.get("imageSize").getAsInt();
			String id = jsonObject.get("id").getAsString();
			OutputStream os = rp.getOutputStream();
			List<RoomPhotoVO> roomPhotoVOList = new ArrayList<RoomPhotoVO>();
			List<String> list = new ArrayList<String>();
			WishService wishDAO = new WishService();
			RoomPhotoService roomPhotoDAO = new RoomPhotoService();
//			System.out.println("id : " + id);
			
			List<String> wishRoomIdList = wishDAO.SgetAllWishRoomId(id);
			for(String wishRoomId : wishRoomIdList){
				list = roomPhotoDAO.getRoomAllRoomPhotoId(wishRoomId);
			}
			
			String roomPhotoId = list.get(0);
			RoomPhotoVO roomPhotoVO = roomPhotoDAO.getOneRoomPhoto(roomPhotoId);
			byte[] image = roomPhotoVO.getRoomPhotoPic();
			if (image != null) {
				image = ImageUtil.shrink(image, imageSize);
				rp.setContentType("image/jpeg");
				rp.setContentLength(image.length);
			}
			os.write(image);
//			System.out.println("image"+image);
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
//				System.out.println("image"+image);
		
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