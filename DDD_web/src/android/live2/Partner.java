package android.live2;

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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mem.model.MemDAO;
import com.mem.model.MemDAO_interface;
import com.mem.model.MemJNDIDAO;
import com.mem.model.MemVO;

@SuppressWarnings("serial")
//@WebServlet("/android/live2/partner.do")
public class Partner extends HttpServlet {
	// private ServletContext context;
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	private MemDAO_interface dao_mem;
	private List<MemVO> memVOList;
	private static Integer reqCount = 0;


	@Override
	public void init() throws ServletException {
		super.init();
		dao_mem = new MemJNDIDAO();
		memVOList = new ArrayList<MemVO>();
		memVOList = dao_mem.getAll();
		System.out.println(memVOList);

	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		reqCount++;
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
		String param = jsonObject.get("action").getAsString();
		String outStr = "";
		if ("getAll".equals(param)) {
			System.out.println("getAll match");
			// 去掉圖片資料，提升效能
//			List<MemVO> tmpMemVOList = this.memVOList;
//			for (MemVO myVO: tmpMemVOList){
//				myVO.setMemProfile(null);
//			}
			outStr = gson.toJson(this.memVOList);
			res.setContentType(CONTENT_TYPE);
			PrintWriter out = res.getWriter();
			System.out.println("outStr:" + outStr);
			out.println(outStr);
		}else if("getImage".equals(param)){
			System.out.println("getImage match");
			OutputStream os = res.getOutputStream();
			int memId = jsonObject.get("memId").getAsInt();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] image = dao_mem.findByPrimaryKey(Integer.toString(memId)).getMemProfile();
			if (image != null) {
				image = ImageUtil.shrink(image, imageSize);
				res.setContentType("image/jpeg");
				res.setContentLength(image.length);
			}
			os.write(image);
		}else  {
			System.out.println("does not match any keys");
		}
		
		System.out.println("reqCount+ " + reqCount);
	}
	

	@Override
	public void doGet(HttpServletRequest rq, HttpServletResponse rp)
			throws ServletException, IOException {

	}
	

}