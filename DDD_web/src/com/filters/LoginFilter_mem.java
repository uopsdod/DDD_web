package com.filters;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginFilter_mem implements Filter{
	
	private FilterConfig config;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		
	}

	@Override
	public void destroy() {
		config = null;		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】
		Object account_mem = session.getAttribute("account_mem");
		if (account_mem == null) {
			session.setAttribute("location_mem", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/frontend_mem/mem/loginOfmember.jsp");
			return;
		} else {
			chain.doFilter(request, response);
		}
	}


}
