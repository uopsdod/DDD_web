package com.room.controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
 
/**
 * Servlet implementation class countMan
 */
@WebServlet("/countMan")
public class countMan extends HttpServlet {


	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		
		
		
	}	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("進入countMan");
		
		HttpSession session = req.getSession();
		String hotelId = req.getParameter("hotelId");
		String action = req.getParameter("action");	
		
		if("cut".equals(action)){
		
			cutNumber(hotelId,session);
			
		}else if("add".equals(action)){
			
			addNumber(hotelId,session);
		}		
	}
	
	
	public static void addNumber(String hotelId,HttpSession session){
		
		Map peopleBox=(Map) session.getAttribute("peopleBox");
		if(peopleBox==null){			
			Map newPeopleBox = new HashMap();
			newPeopleBox.put(hotelId, 1);
			session.setAttribute("peopleBox", newPeopleBox);
			MyEchoServer.hotelPeople(hotelId,1);
			
			
		}else{
			Integer number = (Integer)peopleBox.get(hotelId);
			
			if(number==null){
				peopleBox.put(hotelId, 1);
				MyEchoServer.hotelPeople(hotelId,1);
				
			}else{
				number++;
				peopleBox.put(hotelId, number);
				
				System.out.println(peopleBox);
				
			}			
		}
		
	}
	
	public static void cutNumber(String hotelId,HttpSession session){
		
		Map peopleBox=(Map)session.getAttribute("peopleBox");
		Integer number = (Integer)peopleBox.get(hotelId);
		number--;
		peopleBox.put(hotelId, number);
		if(number==0){
			peopleBox.remove(hotelId);
			MyEchoServer.hotelPeople(hotelId,-1);
		}	
		System.out.println(peopleBox);
	}
	
}
