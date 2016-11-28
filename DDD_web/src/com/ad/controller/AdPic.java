package com.ad.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import javax.naming.Context;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class AdPic
 */
@WebServlet("/Ad/AdPic")
public class AdPic extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// �קK�s�������󪺧֨�
		res.setHeader("Cache-Control", "no-store"); // HTTP 1.1
		res.setHeader("Pragma", "no-cache"); // HTTP 1.0
		res.setDateHeader("Expires", 0);

		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {

			Statement stmt = con.createStatement();
			String adid = req.getParameter("adId");
			String adId2 = new String(adid.getBytes("ISO-8859-1"), "UTF-8");
			ResultSet rs = stmt.executeQuery("select adpic from ad where adId = '" + adId2 + "'");

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("adpic"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				InputStream in = new FileInputStream(getServletContext().getRealPath("NoData/nopic.jpg"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
				// res.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();/*���D�l��*/
//			InputStream in = new FileInputStream(getServletContext().getRealPath("NoData/nopic.jpg"));
//			byte[] buf = new byte[4 * 1024]; // 4K buffer
//			int len;
//			while ((len = in.read(buf)) != -1) {
//				out.write(buf, 0, len);
//			}
//			in.close();
			// System.out.println(e);
		}
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();/* ��@��Ʈw */
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			con = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void destroy() {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
