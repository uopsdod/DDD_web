package com.listener;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitializeOrdStatusTransListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		
		HashMap<String,String> ordStatusTrans = new HashMap<String,String>();
		ordStatusTrans.put("0","已下單");
		ordStatusTrans.put("1","主動取消");
		ordStatusTrans.put("2","已入住");
		ordStatusTrans.put("3","已繳費");
		ordStatusTrans.put("4","逾時取消");
			
		context.setAttribute("ordStatusTrans", ordStatusTrans);		
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// do nothing
		//System.out.println("ServletContextListener通知: contextDestroyed....");
	}	
	
}
