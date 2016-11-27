package com.listener;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitializeHotelRepStatusTransListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		
		HashMap<String,String> hotelRepStatusTrans = new HashMap<String,String>();
		hotelRepStatusTrans.put("0","未審核");
		hotelRepStatusTrans.put("1","已審核未通過");
		hotelRepStatusTrans.put("2","已審核已通過");
		
		context.setAttribute("hotelRepStatusTrans", hotelRepStatusTrans);		
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// do nothing
		//System.out.println("ServletContextListener通知: contextDestroyed....");
	}	
	
}