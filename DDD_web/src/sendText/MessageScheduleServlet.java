package sendText;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class MessageScheduleServlet extends HttpServlet {
   
    Timer timer;
    Send se = new Send();
    
    public void init() throws ServletException {
        
    TimerTask task = new TimerTask(){ 
	      public void run() {
	        String[] tel ={"0988000000" , "0988000000"};
 	        String message = "排程訊息測試";
 	        se.sendMessage(tel , message);
	      } 
     };
        
        
    
        timer = new Timer(); 
        Calendar cal = new GregorianCalendar(2111, Calendar.NOVEMBER, 30, 0, 0, 0);
        timer.scheduleAtFixedRate(task, cal.getTime(), 24*60*60*1000);
        
        System.out.println("已建立簡訊發送排程!");       

    }
    

    public void doGet(HttpServletRequest req, HttpServletResponse res)
                               throws ServletException, IOException {
    }                           	

    public void destroy() {
        timer.cancel();
        System.out.println("已移除簡訊發送排程!");
    }
    
}