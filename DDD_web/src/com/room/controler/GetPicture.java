package com.room.controler;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GetPicture
 */
@WebServlet("/GetPicture")
public class GetPicture extends HttpServlet {
	
       
  
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		
		String actionPicture = req.getParameter("actionPicture");
		if("getPicture".equals(actionPicture)){
			
			res.setContentType("image/jpeg");
			List data = picture.getPic();	//取得圖文
			HttpSession session = req.getSession();
			session.setAttribute("password", data.get(0)+"");
			
			BufferedImage bi = (BufferedImage)data.get(1);
			OutputStream out = res.getOutputStream();
			ImageIO.write(bi, "jpg", out);
			out.close();
			
			
			return;
		}
		
		
		
		
		
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
	}

}
