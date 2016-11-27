package android.ord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.hotel.model.HotelVO;
import com.mem.model.MemDAO_interface;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.ord.model.OrdDAO_interface;
import com.ord.model.OrdService;
import com.ord.model.OrdVO;
import com.room.controler.RoomSetOrder;
import com.room.model.RoomVO;

import android.Hotel.ImageUtil;

@WebServlet("/android/ord/ord.do")
public class OrdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrdService dao_ord;
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	@Override
	public void init() throws ServletException {
		super.init();
		dao_ord = new OrdService();
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
		String memId = (jsonObject.get("memId") != null)?jsonObject.get("memId").getAsString():null;
		// 以下三個參數給updateRating使用
		String ordId = (jsonObject.get("ordId") != null)?jsonObject.get("ordId").getAsString():null;
		String ordRatingStarNo = (jsonObject.get("ordRatingStarNo") != null)?jsonObject.get("ordRatingStarNo").getAsString():null;
		String ordRatingContent = (jsonObject.get("ordRatingContent") != null)?jsonObject.get("ordRatingContent").getAsString():null;
		
		String outStr = "";
		
		if ("getAllOld".equals(action)) {
			System.out.println("getAllOld match");
			// 去掉圖片資料 & bs64資料，提升效能
			//List<OrdVO> tmpOrdVOList = dao_ord.getAll();
			List<OrdVO> tmpOrdVOList = dao_ord.getAllByOrdMemId(memId);
			for (OrdVO myVO: tmpOrdVOList){
				myVO.setOrdQrPic(null);
				myVO.setOrdMemVO(new MemVO());
				myVO.setOrdRoomVO(new RoomVO());
				myVO.getOrdHotelVO().setHotelCoverPic(null);
			}
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ObjectMapper mapper = new ObjectMapper();
			mapper.setDateFormat(df);
			// 此處特別注意: 為了讓hibernate的VO可以轉成JSON字串，必須在每個一對多的set變數上加上@JsonIgnore
			outStr = mapper.writeValueAsString(tmpOrdVOList);
			
//			outStr = gson.toJson(tmpOrdVOList);
			res.setContentType(CONTENT_TYPE);
			PrintWriter out = res.getWriter();
			System.out.println("outStr:" + outStr);
			out.println(outStr);

		}else if("Insert".equals(action)){
			String hotelId = jsonObject.get("hotelId").getAsString();
			String roomId = jsonObject.get("roomId").getAsString();
			String price = jsonObject.get("price").getAsString();
			MemVO memVO = new MemVO();
			OrdVO ordVO = new OrdVO();
			RoomSetOrder setOrd = new RoomSetOrder();
			MemService memSev = new MemService();
			memVO = memSev.getOneMem(memId);
			String memAccount = memVO.getMemAccount();
			String memPhone = memVO.getMemPhone();
			int roomPrice = Integer.parseInt(price);
			
			ordVO = setOrd.setOrder(hotelId, roomId, memId, roomPrice, memAccount, memPhone, getServletContext());
		 
			outStr = gson.toJson(ordVO);
			res.setContentType(CONTENT_TYPE);
			PrintWriter out = res.getWriter(); 
			System.out.println("outStr:" + outStr);
			out.println(outStr);

		}else if ("updateRating".equals(action)){
			dao_ord.updateRating(ordId, ordRatingStarNo, ordRatingContent);

		}else if("getImage".equals(action)){
			int imageSize = jsonObject.get("imageSize").getAsInt();
			System.out.println("ordId: " + ordId);
			OrdVO ordVO = dao_ord.getOneOrd(ordId);
			System.out.println(ordVO);
			OutputStream os = res.getOutputStream();
			byte[] image = ordVO.getOrdQrPic();
			if (image != null) {
				image = ImageUtil.shrink(image, imageSize);
				res.setContentType("image/jpeg");
				res.setContentLength(image.length);
				System.out.println("123112131321212131321231313121231321213");
			}else{
				System.out.println("---------------------------------------------------------------------------------------------------------------------------'");
			}
			
			os.write(image);
			
		}else if("checked".equals(action)){
			String key = jsonObject.get("key").getAsString();
			System.out.println("R*--------------------------*R : " + key);
			boolean check = true;
			RoomSetOrder setOrd = new RoomSetOrder();
			setOrd.checkOrder(key, ordId);
			OrdVO ordVO = dao_ord.getOneOrd(ordId);
			if(ordVO.getOrdStatus().equals("2")){
				check = true;
			}else{
				check = false;
			}
			outStr = gson.toJson(check);
			System.out.println("Q*-------------------*Q" + check);
			res.setContentType(CONTENT_TYPE);
			PrintWriter out = res.getWriter();
			System.out.println("outStr:" + outStr);
			out.println(outStr);
		}
		
	}

}
