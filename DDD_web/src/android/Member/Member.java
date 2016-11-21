package android.Member;

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

import org.apache.tomcat.util.codec.binary.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mem.model.MemJDBCDAO;
import com.mem.model.MemService;
import com.mem.model.MemVO;

import javafx.scene.effect.Light.Spot;

@SuppressWarnings("serial")
public class Member extends HttpServlet {
	// private ServletContext context;
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	private List<String> category;
	private List<Spot> computerList;
	private List<Spot> comicList;

	@Override
	public void doPost(HttpServletRequest rq, HttpServletResponse rp) throws ServletException, IOException {
		MemService dao = new MemService();
		rq.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd").create(); // 從資料庫拉出來的Date要經過轉型，若沒轉型就算有資料也無法辨識
		BufferedReader br = rq.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();

		String outStr = "";
		if (action.equals("getOne")) {

			String id = jsonObject.get("id").getAsString();
			MemVO memVO = dao.getOneMem(id);
			System.out.println("--------memName---------" + memVO.getMemName());
			memVO.setMemProfile(null);
			memVO.setBs64(null);
			outStr = gson.toJson(memVO);
			System.out.println("Profile null*************" + outStr);
			rp.setContentType(CONTENT_TYPE);
			PrintWriter out = rp.getWriter();
			out.println(outStr);

		} else if (action.equals("getAll")) {

			List<MemVO> list = new ArrayList<MemVO>();
			list = dao.getAll();
			for (MemVO myVO : list) {
				myVO.setMemProfile(null);
			}

			outStr = gson.toJson(list);
			System.out.println(outStr);
			rp.setContentType(CONTENT_TYPE);
			PrintWriter out = rp.getWriter();
			out.println(outStr);
		} else if (action.equals("getImage")) {

			OutputStream os = rp.getOutputStream();
			String id = jsonObject.get("id").getAsString();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] image = dao.getOneMem(id).getMemProfile();
			if (image != null) {
				image = ImageUtil.shrink(image, imageSize);
				rp.setContentType("image/jpeg");
				rp.setContentLength(image.length);
			}
			os.write(image);
			System.out.println("image" + image);

		} else if (action.equals("Insert")) {
			String memVOJson = jsonObject.get("memVO").getAsString();
			MemVO memVO = gson.fromJson(memVOJson, MemVO.class);
			// String imageBase64 = jsonObject.get("imageBase64").getAsString();
			// byte[] image = Base64.decodeBase64(imageBase64);

			memVO.setMemBlackList("0");
//			byte[] memProfile = memVO.getMemProfile();
//			System.out.println("memProfile :" + memProfile);
//			if (memProfile.equals(null) || memProfile == null) {
//				memVO.setMemProfile(null);
//			}
			System.out.println(memVO);
			dao.addNewMem(memVO);

		} else if (action.equals("memCheck")) {
			String memUserName = jsonObject.get("userName").getAsString();
			String memPassword = jsonObject.get("password").getAsString();
			MemService memServ = new MemService();
			MemVO memVO = memServ.memCheck(memUserName, memPassword);

			System.out.println("memId : " + memVO.getMemId());
			outStr = gson.toJson(memVO);
			System.out.println(outStr);
			rp.setContentType(CONTENT_TYPE);
			PrintWriter out = rp.getWriter();
			out.println(outStr);
		} else if(action.equals("Update")){
			String memVOJson = jsonObject.get("memVO").getAsString();
			MemVO memVO = gson.fromJson(memVOJson, MemVO.class);
			if(memVO.getMemBlackList().equals("0")){
				memVO.setMemBlackList("0");
			}else{
				memVO.setMemBlackList("1");
			}
			dao.updateAndroidMeminfo(memVO);
		}

		// ��hotelVO�নJSON�r��A�^��

	}

	@Override
	public void doGet(HttpServletRequest rq, HttpServletResponse rp) throws ServletException, IOException {
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

	private void writeText(HttpServletResponse rp, Object valueOf) throws IOException {
		rp.setContentType(CONTENT_TYPE);
		PrintWriter out = rp.getWriter();
		// System.out.println("outText: " + outText);
		out.print(valueOf);

	}

}