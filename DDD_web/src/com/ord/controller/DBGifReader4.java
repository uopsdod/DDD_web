package com.ord.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

import com.ord.model.OrdService;
import com.ord.model.OrdVO;

public class DBGifReader4 extends HttpServlet {


	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			String no = req.getParameter("no");
			
			System.out.println("Here is " + no);
			
			OrdService OrdSvc = new OrdService();
			OrdVO ordVO = OrdSvc.getOneOrd(no);
			
			System.out.println("There is " + ordVO.getOrdId());
			
			out.write(ordVO.getOrdQrPic());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Here is Exception.");
			InputStream in = getServletContext().getResourceAsStream("/backend/ord/images/noImage.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	}

	
}
