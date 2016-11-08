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
			String ordId = req.getParameter("ordId");
						
			OrdService OrdSvc = new OrdService();
			OrdVO ordVO = OrdSvc.getOneOrd(ordId);
						
			out.write(ordVO.getOrdQrPic());

		} catch (Exception e) {
			//e.printStackTrace();
			InputStream in = getServletContext().getResourceAsStream("/backend/ord/images/noImage.jpg");
			byte[] buf = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();
		}
	}

	
}
