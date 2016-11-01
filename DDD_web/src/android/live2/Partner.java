package android.live2;

import java.io.BufferedReader;
import java.io.IOException;
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
@WebServlet("/Partner")
public class Partner extends HttpServlet {
	// private ServletContext context;
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	private List<MemVO> memVOList;


	@Override
	public void init() throws ServletException {
		super.init();
		MemDAO_interface dao_mem = new MemJNDIDAO();
		memVOList = new ArrayList<MemVO>();
		memVOList = dao_mem.getAll();
		System.out.println(memVOList);

	}

	@Override
	public void doPost(HttpServletRequest rq, HttpServletResponse rp)
			throws ServletException, IOException {
		rq.setCharacterEncoding("UTF-8");
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
		BufferedReader br = rq.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(),
				JsonObject.class);
		String param = jsonObject.get("param").getAsString();
		String outStr = "";
		if (param.equals("partner")) {
			System.out.println("match");
			outStr = gson.toJson(memVOList);
		} else  {
			System.out.println("does not match");
		}

		rp.setContentType(CONTENT_TYPE);
		PrintWriter out = rp.getWriter();
		System.out.println("outStr:" + outStr);
		out.println(outStr);
	}

	@Override
	public void doGet(HttpServletRequest rq, HttpServletResponse rp)
			throws ServletException, IOException {

	}
	

}