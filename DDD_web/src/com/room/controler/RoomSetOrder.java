package com.room.controler;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.*;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotel.model.HotelService;
import com.hotel.model.HotelVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.ord.model.OrdService;
import com.ord.model.OrdVO;
import com.room.model.RoomService;
import com.room.model.RoomVO;

import sendText.Send;
import util.QRCodeImgGenerator;

import java.awt.image.BufferedImage;
/**
 * Servlet implementation class RoomSetOrder
 */
@WebServlet("/RoomSetOrder")
public class RoomSetOrder extends HttpServlet {
	
	public static Map<String,Timer> orderTimer = Collections.synchronizedMap( new HashMap<String,Timer>());
	static Map<String,Integer> downSellPrice = Collections.synchronizedMap( new HashMap<String,Integer>());

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
			
		req.setAttribute("turn", "addOrd");		//會員來的網頁借過此
		
		
	doPost(req,res);	
		
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		
		String roomId = req.getParameter("orderRoomId");	//將下訂已下架房型的請求導向回原hotelPage網頁
		if(!RoomServlet.OnData.containsKey(roomId)&&roomId!=null){	
			res.setCharacterEncoding("UTF-8");
			String hotelId = req.getParameter("orderHotelId");																				
			res.sendRedirect(req.getContextPath()+"/HotelRoomSearch?action=hotelPage&hotelId="+hotelId+"&news=房型已下架");
			return;
		}
		
		
		HttpSession session = req.getSession(); 
		
		String acount = (String)session.getAttribute("account_mem");
		
		if(acount==null){	//將未登入會員的請求導向至登入會員
			session.setAttribute("orderPrice", req.getParameter("orderPrice"));
			session.setAttribute("orderRoomId", req.getParameter("orderRoomId"));
			session.setAttribute("orderHotelId", req.getParameter("orderHotelId"));		
			session.setAttribute("location", req.getRequestURI()); // 含有輸入格式錯誤的empVO物件,也存入req			
			res.sendRedirect(req.getContextPath()+"/frontend_mem/mem/loginOfmember.jsp");
			return;
			
		}
		
		
		String turn= (String)req.getAttribute("turn");	//將從登入會員過來的請求導向至新增訂單畫面
		if(turn!=null&&"addOrd".equals(turn)){
			String url = "/frontend_mem/hotel/addOrd.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
			return;
		}		
		
		
		
		
		String action = (String)req.getParameter("action");
		
		if("addOrd".equals(action)){   //將早已登入會員而從hotelPage過來的請求導向至新增訂單畫面
			session.setAttribute("orderPrice", req.getParameter("orderPrice"));
			session.setAttribute("orderRoomId", req.getParameter("orderRoomId"));
			session.setAttribute("orderHotelId", req.getParameter("orderHotelId"));		
			session.setAttribute("location", req.getRequestURI());
			String url = "/frontend_mem/hotel/addOrd.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
			return;	
		}
		
		
		if("insert".equals(action)){   //確認下單
			
			List<String> errorMsgs = new LinkedList<String>();
				
			
			String ordMail = req.getParameter("ordMail").trim();	//驗證信箱
			if (ordMail.trim().isEmpty()) {
				errorMsgs.add("信箱不能空白");
			} else {
				if (!ordMail.matches("^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$")) {
					errorMsgs.add("信箱格式錯誤");
				}
			}			
			
			
			
			String ordPhone = req.getParameter("ordPhone").trim();	//驗證電話
			if (ordPhone.trim().isEmpty()) {
				errorMsgs.add("電話號碼必填");
			} else {
				if (!ordPhone.matches("^[0-9]*$")) {
					errorMsgs.add("電話只能輸入數字");
				}
			}
						
			
			String picture = req.getParameter("picture");		    //驗證驗證碼	
			String password = (String)session.getAttribute("password");	
			if(!password.equals(picture)){		
				errorMsgs.add("驗證碼錯誤");	
			}
			
			
			
			session.setAttribute("ordPhone", ordPhone);
			session.setAttribute("ordMail", ordMail);
						
			
			
			if (!errorMsgs.isEmpty()) {		//若有問題,導回訂單確認頁面
			req.setAttribute("errorMsgs", errorMsgs);
			RequestDispatcher failureView = req
					.getRequestDispatcher("/frontend_mem/hotel/addOrd.jsp");
			failureView.forward(req, res);
			return;				
			}
			
			
			int ordPrice = new Integer(req.getParameter("ordPrice"));
			String ordRoomId = req.getParameter("ordRoomId");
			if(!RoomServlet.OnData.containsKey(ordRoomId)){
				errorMsgs.add("已未有空房");
			}else{
				
				int nowPrice = (int)(RoomServlet.OnData.get(ordRoomId).get("price"));
				if(ordPrice<nowPrice){
				errorMsgs.add("房型價格已重新修改上架");	
				}
			}
			
			
			String ordhotelId = req.getParameter("ordHotelId");
			if (!errorMsgs.isEmpty()) {		//若價格或上下架有問題,導回旅館頁面
				res.setCharacterEncoding("UTF-8");																				
				res.sendRedirect(req.getContextPath()+"/HotelRoomSearch?action=hotelPage&hotelId="+ordhotelId+"&news="+errorMsgs.get(0));
				return;				
			}
			
			String ordMemId = req.getParameter("ordMemId");
			
		
			ServletContext box = getServletContext();
			setOrder(ordhotelId,ordRoomId,ordMemId,ordPrice,ordMail,ordPhone,box);
			
			
				
			System.out.println("通過驗證");
			
			
			
			req.setAttribute("OrdMessage","謝謝您的訂購");
			RequestDispatcher failureView = req
					.getRequestDispatcher("/frontend_mem/ord/listAllOrdByMemId.jsp");
			failureView.forward(req, res);
			return;	
		}
		
		
		
		
	}
	
	
	
	
	public synchronized static OrdVO setOrder(String ordHotelId,String ordRoomId,String ordMemId,int ordPrice,String ordMail,String ordPhone,ServletContext box){
		
		
		
		 HotelService hotelSvc = new HotelService();			
		 HotelVO hotelVO = hotelSvc.getOne(ordHotelId);
		 
		 MemService memSvc = new MemService();
		 MemVO memVO =memSvc.getOneMem(ordMemId);
		 
		 RoomService roomSvc = new RoomService();
		 RoomVO roomVO = roomSvc.findByPrimaryKey(ordRoomId);
		 	 
		
		 String hotelName = hotelVO.getHotelName();
		 String memName = memVO.getMemName(); 
		 String roomName = roomVO.getRoomName();
		
		
		//取得簡訊驗證碼
		int number=0;
	 	String setkey;
    	while(true){
    		
    		number += randomNumber();
    		setkey = String.valueOf(number);
    		if(!orderTimer.containsKey(setkey)){
    			break;
    		}   		
    	}	    	
    	Timer timer = new Timer();
    	
    	final String  key = setkey;
  	
    	orderTimer.put(key, timer);
	     
    	
    	OrdService ordSvc = new OrdService();
    	OrdVO firstOrdVO= ordSvc.addOrd(ordRoomId,ordMemId,ordHotelId,ordPrice,null,"0",null,null,null,key);	
		//新增訂單
    	
    	
    	
    	//處理QRcode
    	 StringBuffer QRcode = new StringBuffer();
    	
    	 QRcode.append("Dua Dee Dou訂單");
    	 QRcode.append(",");
    	 QRcode.append("訂單明細如下 : ");
    	 QRcode.append(",");
    	 QRcode.append("訂購人 : ");
    	 QRcode.append(",");
    	 QRcode.append(memName);   	 
    	 QRcode.append(",");
    	 QRcode.append("旅館名稱 : ");
    	 QRcode.append(",");
    	 QRcode.append(hotelName);   
    	 QRcode.append(",");
		 QRcode.append("房型名稱 : ");
		 QRcode.append(",");
		 QRcode.append(roomName);
		 QRcode.append(",");
		 QRcode.append("房價 : ");
		 QRcode.append(",");
		 QRcode.append(ordPrice);
		 QRcode.append(",");
		 QRcode.append("訂單編號 : ");
		 QRcode.append(",");
		 QRcode.append(firstOrdVO.getOrdId());
		 QRcode.append(",");
		 QRcode.append("會員編號 : ");
		 QRcode.append(",");
		 QRcode.append(ordMemId);
	
    	String QRUrl =new String(QRcode);; 		
		
    	byte[] ordQrPic = QRCodeImgGenerator.writeQRCode(QRUrl);    	
    	firstOrdVO.setOrdQrPic(ordQrPic);    	    	    	
    	
    	OrdVO newOrdVO = ordSvc.updateOrd(firstOrdVO);
    	
    	
    	
    	
    	//**處理email**********************************************************************	
		
		 
		 StringBuffer messageBf = new StringBuffer();
		 messageBf.append(memName);
		 messageBf.append("您好~");
		 messageBf.append("\n");
		 messageBf.append("謝謝您的訂購 ");
		 messageBf.append("您的訂單明細如下 : ");
		 messageBf.append("\n");
		 messageBf.append("旅館名稱 : ");
		 messageBf.append(hotelName);
		 messageBf.append("\n");
		 messageBf.append("房型名稱 : ");
		 messageBf.append(roomName);
		 messageBf.append("\n");
		 messageBf.append("房價 : ");
		 messageBf.append(ordPrice);
		 messageBf.append("\n");
		 messageBf.append("謝謝您的訂購,請直接到旅館付現即可");
		 
		 String message = new String(messageBf);
		//**處理email**********************************************************************
		 
		 
		//**處理簡訊**********************************************************************
		 
		 
		 StringBuffer telBf = new StringBuffer();
		 telBf.append(memName);
		 telBf.append("您好~");
		 telBf.append("您的訂單明細如下 : ");
		 telBf.append(",旅館名稱 : ");
		 telBf.append(hotelName);
		 telBf.append(",房型名稱 : ");
		 telBf.append(roomName);
		 telBf.append(",房價 : ");
		 telBf.append(ordPrice);
		 telBf.append(",簡訊驗證碼 : "+key);
		 
		 String telMessage = new String(telBf);
			 	
		 String[] tel = {ordPhone};
		 String subject = "Dua Dee Dou訂單通知";
				 //0972283671
		//**處理簡訊**********************************************************************
		 
		 
		 
		//**處理寄送**********************************************************************
		  Timer sendTimer = new Timer();
		 	TimerTask send = new TimerTask(){
		        
		         public void run(){
		         	//排程器要執行的任務	
		        	 Send.send(tel,telMessage);	//傳簡訊
		        	 MailService.gotMail(ordMail, subject, message); 
		        	
		         }
		     };
			 	 
		     sendTimer.schedule(send,1000*3); 
		  //**處理寄送**********************************************************************
		 
	 
		     
		  //處理房數上下架,與動態價格問題   
		     
		     
		     int remainNo = roomVO.getRoomRemainNo();  
		     remainNo = remainNo-1;
		     
		     roomVO.setRoomRemainNo(remainNo);
    
		     Integer nowPrice = null;
		     if(remainNo==0){
		    	 
		    	 roomVO.setRoomForSell(false); 
		    	
		    	 if(RoomServlet.OnTimer.get(ordRoomId)!=null){ 
		    	 RoomServlet.OnTimer.get(ordRoomId).cancel();	//取消動態降價排成
		    	 }
		    	 
		    	 nowPrice=(Integer)RoomServlet.OnData.get(ordRoomId).get("price");	//取得此時的動態底價	
		    	 
		    	 if(RoomServlet.DownTimer.get(ordRoomId)!=null){ 
		    	 RoomServlet.DownTimer.get(ordRoomId).cancel();		//取消下架排成
		    	 }	    	 
		    	 
		    	 RoomServlet.OnTimer.remove(ordRoomId); 	//移除降價排程
		    	 RoomServlet.OnData.remove(ordRoomId);		//移除動態價格資料
		    	 RoomServlet.DownTimer.remove(ordRoomId); 	//移除降價排程
		    
		    	 
		    	 //取得此房型定時上架時間
		    	 int beforeStartDate = roomVO.getRoomDiscountStartDate();				
				 	
				 //從定時系統的上架map移除此房型的資料,以免售完後,因為定時而再次上架
		    	 
				 Map<Integer,List> roomRegularTime = (Map<Integer,List>)box.getAttribute("RoomRegularTime");									
				 if(roomRegularTime.get(beforeStartDate)!=null){	//刪除此room在定時上架的map
					 List<String> beforeList = roomRegularTime.get(beforeStartDate);
					 beforeList.remove(ordRoomId);	
					 if(beforeList.size()==0){	//當該時刻的list已經沒有roomId時,就從map中刪除該list
						 roomRegularTime.remove(beforeStartDate); 
					  }
				 }
				 
				 downSellPrice.put(ordRoomId, nowPrice);
				 MyEchoServer.BufferBox(ordRoomId,-500,"已售完",0);
		     }	     
		     roomSvc.update(roomVO);
		     MyEchoServer.changeRemainNo(ordRoomId,remainNo);
		     
		     
		     
		     
	     Calendar caler = new GregorianCalendar();
	     int year = caler.get(Calendar.YEAR);
	     int month = caler.get(Calendar.MONTH);
	     int date = caler.get(Calendar.DATE);
	     Calendar TodayTime = new GregorianCalendar(year,month,date,0,0,0);	//取出今日0:0:00的Date物件
	     long todayZeroMiliSecond = TodayTime.getTime().getTime();     
     
	     long nowMiliSecond = caler.getTime().getTime();
	     
	     long divideTime = nowMiliSecond - todayZeroMiliSecond;
	     
	     int delayTime = 6*10*1000;
	     long reUpTime = divideTime + delayTime;
	     
	        
		 TimerTask back = new TimerTask(){
		        
	         public void run(){
	         	//排程器要執行的任務	
	        	
	        	 RoomService roomSvc2 = new RoomService();
	        	 RoomVO roomVO2 = roomSvc2.findByPrimaryKey(ordRoomId);
	        	 
	        	 int roomDiscountHr = roomVO2.getRoomDiscountHr();
	        	 
	        	 	        	 
	        	 int remainNo2 =roomVO2.getRoomRemainNo();
	        	 remainNo2 = remainNo2+1;
	        	 roomVO2.setRoomRemainNo(remainNo2);	//剩餘房數+1
	        	 
	        	 boolean sellNow = roomVO2.getRoomForSell();
	        	 
	        	 int downTime = roomVO2.getRoomDiscountEndDate();
	        	 
	        	
	        	
	        	 
	        	 if(sellNow==false&&remainNo2==1&&reUpTime<=(downTime-1000*60*30)){	//再次上架時間要比原定下架時間提前30min才再次上架
	        		
	        		 int price = downSellPrice.get(ordRoomId);
	        		 int roomPrice= roomVO2.getRoomPrice();
		        	 int roomDisccountPercent = roomVO2.getRoomDisccountPercent();
		        	 Float cutPrice = roomPrice*roomDisccountPercent*0.01f;
					 int cutPriceInt = cutPrice.intValue();
		        	 int bottomPrice = roomVO2.getRoomBottomPrice();
		        	 boolean roomOnePrice = roomVO2.getRoomOnePrice(); 
	        	     RoomServlet.DynamicPrice(ordRoomId,false,roomDiscountHr*10*1000,price,cutPriceInt,bottomPrice,roomOnePrice,downTime,remainNo2); 
	        	     roomVO2.setRoomForSell(true);	
	        	 }else{	//上架的推送房數資料,由DynamicPrice方法內來推送
	        		 MyEchoServer.changeRemainNo(ordRoomId,remainNo2); //將剩餘房數+1 並往前推
	        	 }
	        	 
	        	 roomSvc2.update(roomVO2);	//房型修改剩餘房數
	        	 
	        	 MyEchoServer.changeRemainNo(ordRoomId,roomVO2.getRoomRemainNo());
	        	 
	        	 String addOrdId = newOrdVO.getOrdId();         	 
	        	 OrdVO thisOrdVO = ordSvc.getOneOrd(addOrdId);
	        	 thisOrdVO.setOrdStatus("4");
	        	 ordSvc.updateOrd(thisOrdVO);
	        	 
	        	 orderTimer.remove(key);
	        	 System.out.println(orderTimer);
	         }
	     };
	     
	     
	     timer.schedule(back,delayTime); 
	 	
	     System.out.println(orderTimer);
	     
	     return newOrdVO;
	     
	     
	     
}

	 static int randomNumber() {
	        return (int) (Math.random() * 10000);
	    }
	
	
	public synchronized static void checkOrder(String key,String ordId){
		
	
		orderTimer.get(key).cancel();
		orderTimer.remove(key);
		
		System.out.println(orderTimer);
		
		OrdService ordSvc = new OrdService();
		OrdVO ordVO = ordSvc.getOneOrd(ordId);
		ordVO.setOrdStatus("2");
		ordSvc.updateOrd(ordVO);
	}
	
	
	public synchronized static void cancelOrder(String key,String ordId){
		
		
		
		orderTimer.get(key).cancel();
		orderTimer.remove(key);
		
		OrdService ordSvc = new OrdService();
		OrdVO ordVO = ordSvc.getOneOrd(ordId);
		ordVO.setOrdStatus("1");
		OrdVO newOrdVO = ordSvc.updateOrd(ordVO);
		
		
		System.out.println(orderTimer);
		
		
		//******處理主動取消的再次上架*********************************************************
		
		
		RoomVO roomVO2 =newOrdVO.getOrdRoomVO();		
		RoomService roomSvc2 = new RoomService();
    	
    	 
    	 int roomDiscountHr = roomVO2.getRoomDiscountHr();
    	 
    	 	        	 
    	 int remainNo2 =roomVO2.getRoomRemainNo();
    	 remainNo2 = remainNo2+1;
    	 roomVO2.setRoomRemainNo(remainNo2);	//剩餘房數+1
    	 
    	 

    	 boolean sellNow = roomVO2.getRoomForSell();
    	 
    	 int downTime = roomVO2.getRoomDiscountEndDate();
    	 
    	 String ordRoomId = roomVO2.getRoomId();
    	
    	 
    	 
    	 Calendar caler = new GregorianCalendar();
	     int year = caler.get(Calendar.YEAR);
	     int month = caler.get(Calendar.MONTH);
	     int date = caler.get(Calendar.DATE);
	     Calendar TodayTime = new GregorianCalendar(year,month,date,0,0,0);	//取出今日0:0:00的Date物件
	     long todayZeroMiliSecond = TodayTime.getTime().getTime();        
	     long nowMiliSecond = caler.getTime().getTime();	     
	     long divideTime = nowMiliSecond - todayZeroMiliSecond;     
	
    	 
    	 	 
    	 
    	 if(sellNow==false&&remainNo2==1&&divideTime<=(downTime-1000*60*30)){	//再次上架時間要比原定下架時間提前30min才再次上架
    		
    		 int price = downSellPrice.get(ordRoomId);
    		 int roomPrice= roomVO2.getRoomPrice();
        	 int roomDisccountPercent = roomVO2.getRoomDisccountPercent();
        	 Float cutPrice = roomPrice*roomDisccountPercent*0.01f;
			 int cutPriceInt = cutPrice.intValue();
        	 int bottomPrice = roomVO2.getRoomBottomPrice();
        	 boolean roomOnePrice = roomVO2.getRoomOnePrice(); 
    	     RoomServlet.DynamicPrice(ordRoomId,false,roomDiscountHr*10*1000,price,cutPriceInt,bottomPrice,roomOnePrice,downTime,remainNo2); 
    	     roomVO2.setRoomForSell(true);	
    	 }else{	//上架的推送房數資料,由DynamicPrice方法內來推送
    		 MyEchoServer.changeRemainNo(ordRoomId,remainNo2); //將剩餘房數+1 並往前推
    	 }
    	 
    	 roomSvc2.update(roomVO2);	//房型修改剩餘房數
    	 
		
	}

}

