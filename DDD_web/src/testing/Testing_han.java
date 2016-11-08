package testing;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.hotelrep.model.HotelRepDAO_interface;
import com.hotelrep.model.HotelRepJNDIDAO;
import com.hotelrep.model.HotelRepVO;
import com.memrep.model.MemRepDAO_interface;
import com.memrep.model.MemRepJNDIDAO;
import com.memrep.model.MemRepVO;

@WebServlet("/Testing_han")
public class Testing_han extends HttpServlet {
	private static DataSource ds  = null;
	static{
		try{
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
					
		}catch(NamingException e){
			e.printStackTrace(System.err);
		}
	}
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	   	req.setCharacterEncoding("UTF-8");
	    res.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = res.getWriter();
		Map<String, String[]> map1 = req.getParameterMap();
	    HotelRepDAO_interface dao1 = new HotelRepJNDIDAO();
		out.println("<b>HotelRepService-OK: </b>");
		List<HotelRepVO> hotelRepList = dao1.getAll(map1);
		for (HotelRepVO myVO: hotelRepList){
			out.println("myVO: " + myVO.getHotelRepContent() + "<br>");
		}
		out.println("---------------------------------------------<br>");
	    
	    
	}


}
