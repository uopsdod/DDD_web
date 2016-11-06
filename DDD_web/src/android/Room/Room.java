package android.Room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mem.model.MemVO;
import com.room.model.RoomJDBCDAO;
import com.room.model.RoomService;
import com.room.model.RoomVO;
import com.roomphoto.model.RoomPhotoJDBCDAO;

import android.Hotel.HotelJDBCDAO;
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
			RoomJDBCDAO dao = new RoomJDBCDAO();
			RoomVO hotelVO = dao.findByPrimaryKey(id);
			outStr = gson.toJson(hotelVO);
			rp.setContentType(CONTENT_TYPE);
			PrintWriter out = rp.getWriter();
			out.println(outStr);
			System.out.println(outStr);
			System.out.println("id123123132131313132 " + id);
			
		}else if(action.equals("getAll")){  // 取得當前飯店內的所有房型
			
			String id = jsonObject.get("id").getAsString();
			RoomService dao = new RoomService();
			Set<RoomVO> set = dao.getOneHotelAllRoom(id);
			
			outStr = gson.toJson(set);
			rp.setContentType(CONTENT_TYPE);
			PrintWriter out = rp.getWriter();
			out.println(outStr);
			System.out.println(outStr);
			
		}else if(action.equals("getImage")){

			OutputStream os = rp.getOutputStream();
			RoomPhotoJDBCDAO dao = new RoomPhotoJDBCDAO();
			String id = jsonObject.get("id").getAsString();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			System.out.println("id : " + id);
//			byte[] image = dao.getRoomAllRoomPhotoId(id);
//			if (image != null) {
//				image = ImageUtil.shrink(image, imageSize);
//				rp.setContentType("image/jpeg");
//				rp.setContentLength(image.length);
//			}
//			os.write(image);
//			System.out.println("image"+image);
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