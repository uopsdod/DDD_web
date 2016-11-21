package com.listener;


import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.room.controler.MyEchoServer;
import com.room.controler.RoomServlet;
import com.room.model.*;
public class InitializeRoomListener implements ServletContextListener {
	
	
 		Timer down = null;
 static	Timer WsPush = null;
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		
		if(down!=null){down.cancel();}
		
		//準備定時系統的MAP,內以時間為KEY,對應一個裝有該時需上架之roomID的list
		Map<Integer,List> RoomRegularTime = new HashMap<Integer,List>();
		
		RoomService roomSvc = new RoomService();
		List<RoomVO> roomList = roomSvc.getAll();
	    for(RoomVO roomVO : roomList){
	    	roomVO.setRoomForSell(false);
	    	roomSvc.update(roomVO);
	    	
	    	if(roomVO.getRoomForSellAuto()==true){
	    		if(RoomRegularTime.get(roomVO.getRoomDiscountStartDate())==null){
	    			List<String> now = new LinkedList<String>();
	    			now.add(roomVO.getRoomId());
	    			RoomRegularTime.put(roomVO.getRoomDiscountStartDate(),now);	
	    			
	    		}else{
	    			List now =RoomRegularTime.get(roomVO.getRoomDiscountStartDate());
	    			now.add(roomVO.getRoomId());
	    			RoomRegularTime.put(roomVO.getRoomDiscountStartDate(),now);	
	    		}  		
	    	}	    	
	    }
		
		context.setAttribute("RoomRegularTime", RoomRegularTime);
		
		Integer timeGo =0;
		
		context.setAttribute("timeGo", timeGo);
		
		
		     
		     Calendar caler = new GregorianCalendar();
		     int year = caler.get(Calendar.YEAR);
		     int month = caler.get(Calendar.MONTH);
		     int date = caler.get(Calendar.DATE);
		     int hour = caler.get(Calendar.HOUR_OF_DAY);
		     int minute = caler.get(Calendar.MINUTE);
		   
     
		     Calendar TodayTime = new GregorianCalendar(year,month,date,hour,minute+1,0);	//取出今日0:0:00的Date物件	     
		     Date DateTask = TodayTime.getTime(); 			    
		     Timer down = new Timer();   	 
  

			  TimerTask taskDown = new TimerTask(){
			        
				  Calendar caler2;
				  int count = 0;
				  int Go = 0;
			         public void run(){
			         	//排程器要執行的任務	
			        	 
//			        	 System.out.println("每分鐘排程啟動");
			        	 
			        	 if(Go==0){
			        		 caler2 = new GregorianCalendar();				
						     int hour2 = caler.get(Calendar.HOUR_OF_DAY);
						     int minute2 = caler.get(Calendar.MINUTE);
				        	 int nowTime = hour2*60*60*1000+minute*60*1000; 	 
				        	 count = nowTime;
				        	 Go ++;
			        	 }
			        	 count = count + 60*1000;
			        	 
			        	 if(count%(24*60*60*1000)==0){count = 0;}	//每日凌晨歸0
			        	 
			        	 
			        	 
			        	 
//			        	 System.out.println(count);
//			        	 System.out.println(RoomRegularTime);

			        	 
			        	 List<String> nowList = (List<String>)RoomRegularTime.get(count);
			        	 if(nowList!=null){
			        		for(String roomId:nowList)
			        		{
			        			RoomServlet.RegularOnTime(roomId);
			        		
			        		}
			        		 
			        	 }
			     
			        	 /*************下架了***************/
			         }
			     };
//			     System.out.println("伺服器啟動");
		     down.schedule(taskDown, DateTask,60*1000); 
		
		
		/*webSocket推波初始排程*************************************************************************/
		     
		     WsPush = new Timer();
			 
			 TimerTask WsTaskDown = new TimerTask(){
			    
			         public void run(){
			         	//排程器要執行的任務	
//			        	 System.out.println("推送排成啟動");
			        	 MyEchoServer.BufferBox("",0,"",1); //前三個參數沒意義,第四個參數告訴方法,是要推資料出去的排成
			        	 
			        	 /*************下架了***************/
			         }
			     };
			  System.out.println("伺服器啟動");
			     WsPush.schedule(WsTaskDown, 0, 10*1000); 
		
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// do nothing
		//System.out.println("ServletContextListener通知: contextDestroyed....");
		if(down!=null){
		down.cancel();
		}
		if(WsPush!=null){
			WsPush.cancel();
		}
	}	
	
}
