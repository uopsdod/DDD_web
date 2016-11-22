package com.mem.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

import com.mem.model.MemService;
import com.mem.model.MemVO;

public class DBGifReader5 extends HttpServlet {


	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			String memId = req.getParameter("memId");
						
			MemService MemSvc = new MemService();
			MemVO memVO = MemSvc.getOneMem(memId);
						
			out.write(memVO.getMemProfile());

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
