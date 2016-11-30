package android.live2;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import javax.servlet.http.HttpServlet;

import com.mem.model.MemService;
import com.mem.model.MemVO;

import util.GetDistanceByCoord;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MemCoordServlet extends HttpServlet {

	static HashSet<MemCoordVO> onlineUserSet = new HashSet<MemCoordVO>();
	
	static{
		/* 建立線上假人資料 */
		String[] fakeMemId = {"10000012","10000011","10000010","10000009","10000008","10000007","10000006","10000005","10000004","10000003"};
		
		/* lat,lng */
		Double[][] fakeMemPos ={{24.953675, 121.225d},{24.969055d,121.190d},{24.967880d,121.193d},{22.701490, 120.302d},{24.962880d,121.200d},{24.960880d,121.190d},
								{24.967d,121.190d}	 ,{24.963d,121.191d}   ,{24.969d,121.193d}   ,{24.970d,121.194d}};
		
		MemService memSvc = new MemService();
		
		for(int count=0; count<fakeMemId.length ; count++){
			MemVO memVO = memSvc.getOneMem(fakeMemId[count]);
			MemCoordVO memCoordVO = new MemCoordVO();
			try {
				/* 複製memVO資料 */
				BeanUtils.copyProperties(memCoordVO, memVO);
				
				/* 給它假座標 */				
				memCoordVO.setMemLat(fakeMemPos[count][0]);
				memCoordVO.setMemLng(fakeMemPos[count][1]);
				
				onlineUserSet.add(memCoordVO);
				
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
				
	}
	
	public void doGet(HttpServletRequest aReq, HttpServletResponse aRes)throws ServletException, IOException {
		doPost(aReq,aRes);
	}
	
	public void doPost(HttpServletRequest aReq, HttpServletResponse aRes)throws ServletException, IOException{
		aReq.setCharacterEncoding("UTF-8");
		aRes.setCharacterEncoding("UTF-8");
		String action = aReq.getParameter("action");
		
		/* 登入上傳自己座標 並計算出和其他人的距離 */
		
		if("uploadCoord".equals(action)){
			
			/* 弄一個有其他使用者的座標距離的list(給上傳座標的使用者) */
			List<MemCoordVO> listForUploader = new ArrayList<MemCoordVO>();
			
			/* 1.接收請求參數 */
			String memId = aReq.getParameter("memId");
		 	Double memLat = new Double(aReq.getParameter("memLat"));
		 	Double memLng = new Double(aReq.getParameter("memLng"));
		
		 	/* uploader假座標 */
			//String memId = "10000001";
			//Double memLat = 24.967880d;
			//Double memLng = 121.191602d; 
			 
		 	for(MemCoordVO memCoordVO : onlineUserSet){
		 		
		 		//System.out.println("線上有誰: "+memCoordVO.getMemId());
		 		
		 		/* Uploader本人就不放進list裡面 */
		 		if(memId.equals(memCoordVO.getMemId())){
		 			continue;
		 		}
		 		
		 		/* 複製一份給Uploader的VO 以免被污染到Servlet的VO 然後放進給Uploader的list */
		 		MemCoordVO memCoordVOForUploader = new MemCoordVO();
		 		
		 		try {
					BeanUtils.copyProperties(memCoordVOForUploader, memCoordVO);
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
		 		
		 		/* 計算Uploader與線上人的距離 */
		 		//memCoordVOForUploader.getMemLat();
		 		//memCoordVOForUploader.getMemLng();
		 		
		 		Double aMemDis = GetDistanceByCoord.GetDistance(memCoordVOForUploader.getMemLat(), memCoordVOForUploader.getMemLng(), memLat, memLng);
		 		
		 		//System.out.println("距離幾公尺? " + aMemDis);
		 		
		 		memCoordVO.setMemDis(aMemDis);
		 		
		 		/* 放進list裡面 */
		 		listForUploader.add(memCoordVO);
		 		
		 	}
					 	
			/* 包裝成jason回傳 */
		 	JSONArray jArray = new JSONArray(); 
		 	
		    for(MemCoordVO aMemCoordVO : listForUploader){
		    	JSONObject jObj = new JSONObject();	
		    	try {
					jObj.put("memId", aMemCoordVO.getMemId());
			    	jObj.put("memName", aMemCoordVO.getMemName());
			    	jObj.put("memGender", aMemCoordVO.getMemGender());
			    	jObj.put("memIntro", aMemCoordVO.getMemIntro());
			    	jObj.put("memLat", aMemCoordVO.getMemLat());
			    	jObj.put("memLng", aMemCoordVO.getMemLng());
			    	jObj.put("memDis", aMemCoordVO.getMemDis());
				} catch (JSONException e) {

					e.printStackTrace();
				}
		    	jArray.put(jObj);
		    }
		    
		    //System.out.println(jArray);
		    
			PrintWriter out = aRes.getWriter();
			//System.out.println("jArray.toString(): " + jArray.toString());
			out.write(jArray.toString());
			
			
		 	/* 最後把自己的座標加到Servlet的MemCoordVO list上面 */
		 	MemService memSvc = new MemService();
		 	
		 	MemVO memVO = memSvc.getOneMem(memId);
		 	MemCoordVO memCoordVO = new MemCoordVO();
		 	
		 	try {
				BeanUtils.copyProperties(memCoordVO, memVO);
				memCoordVO.setMemLat(memLat);
				memCoordVO.setMemLng(memLng);
				
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		 	
		 	/* 把Uploader加進去之前 先把線上的Uploader拿掉 避免重複 */
		 	Iterator<MemCoordVO> iterator = onlineUserSet.iterator();
		 	while (iterator.hasNext()) {
		 		MemCoordVO aMemCoordVO = iterator.next();
		 	    if (memId.equals(aMemCoordVO.getMemId())) {
		 	    	//System.out.println("刪掉: "+aMemCoordVO.getMemId());
		 	        iterator.remove();
		 	    }
		 	}		 	
		 	
		 	onlineUserSet.add(memCoordVO);
			
		}
	}

}
