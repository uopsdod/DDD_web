package android.memRep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.hotel.model.HotelVO;
import com.mem.model.MemVO;
import com.memrep.model.MemRepService;
import com.memrep.model.MemRepVO;
import com.ord.model.OrdService;
import com.ord.model.OrdVO;
import com.room.model.RoomVO;

/**
 * Servlet implementation class MemRepServlet
 */
@WebServlet("/android/memRep/memRep.do")
public class MemRepServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemRepService dao_memRep;
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	@Override
	public void init() throws ServletException {
		super.init();
		dao_memRep = new MemRepService();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(),JsonObject.class);
		
		String action = (jsonObject.get("action") != null)?jsonObject.get("action").getAsString():null;
		String ordId = (jsonObject.get("ordId") != null)?jsonObject.get("ordId").getAsString():null;
		//String memId = (jsonObject.get("memId") != null)?jsonObject.get("memId").getAsString():null;
		System.out.println("ordId: " + ordId);
		String outStr = null;
		
		if ("findByMemRepOrdId".equals(action)) {
			System.out.println("findByMemRepOrdId match");
			System.out.println("ordId: "+ ordId);
			MemRepVO memRepVO = dao_memRep.findByMemRepOrdId(ordId);
			if (memRepVO == null) {
				res.setContentType(CONTENT_TYPE);
				PrintWriter out = res.getWriter();
				out.println(outStr);
				return;
			}
			
			if (memRepVO.getMemRepEmpVO() != null){
				memRepVO.getMemRepEmpVO().setEmpProfile(null);
				memRepVO.getMemRepEmpVO().setBs64(null);
			}
			
			
			memRepVO.getMemRepMemVO().setMemProfile(null);
			memRepVO.getMemRepMemVO().setBs64(null);
			
			memRepVO.getMemRepHotelVO().setBs64(null);
			memRepVO.getMemRepHotelVO().setHotelCoverPic(null);
			
			memRepVO.getMemRepOrdVO().getOrdHotelVO().setBs64(null);
			memRepVO.getMemRepOrdVO().getOrdHotelVO().setHotelCoverPic(null);
			
			memRepVO.getMemRepOrdVO().getOrdMemVO().setBs64(null);
			memRepVO.getMemRepOrdVO().getOrdMemVO().setMemProfile(null);

			
//			memRepVO.setMemRepEmpVO(new EmpVO());
//			memRepVO.setMemRepHotelVO(new HotelVO());
//			memRepVO.setMemRepMemVO(new MemVO());
//			memRepVO.setMemRepOrdVO(new OrdVO());
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ObjectMapper mapper = new ObjectMapper();
			mapper.setDateFormat(df);
			// 此處特別注意: 為了讓hibernate的VO可以轉成JSON字串，必須在每個一對多的set變數上加上@JsonIgnore
			outStr = mapper.writeValueAsString(memRepVO);
			
			//outStr = gson.toJson(memRepVO);
			res.setContentType(CONTENT_TYPE);
			PrintWriter out = res.getWriter();
			System.out.println("outStr:" + outStr);
			out.println(outStr);
		}else if ("insert".equals(action)){
			System.out.println("insert match");
			String content = (jsonObject.get("content") != null)?jsonObject.get("content").getAsString():null;
			dao_memRep.insert(ordId, content);
		}
		
	}
}
