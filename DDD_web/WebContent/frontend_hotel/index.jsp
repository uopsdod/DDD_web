<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hotel.model.*"%>
<%@ include file="head.jsp"%>


<style>
#register{
	margin-top:16%;
}
#h3{
	font-family:Tahoma,Verdana,Arial,微軟正黑體;
	font-weight:bold;
}	
#h2{
	font-family:Tahoma,Verdana,Arial,微軟正黑體;
	font-weight:bolder;
}
#h4{font-family:Tahoma,Verdana,Arial,微軟正黑體;font-size: 24px;text-align:left;}
#showbox{font-family:Tahoma,Verdana,Arial,微軟正黑體;font-size: 16px;color:red;}	
	
</style>


<div class="col-xs-12 col-sm-10 tablediv" align="center"> 
	<div class="col-xs-12 col-sm-2" align="center"></div>
	<div class="col-xs-12 col-sm-8" align="center">
		<img src="<%=request.getContextPath()%>/frontend_mem/images/DDD_NEW_LOGO.png" id="register" width="600" height="350">
		<h2 id ="h2">Welcome Back ! 感謝您選擇使用DDD.com</h2>
		<h3 id ="h3">${hotelVO.getHotelName()} 您好!</h3>		
		<h4 id ="h4">從晚鳥訂房中發現更多DDD.com 是最彈性、最優惠的上架平台，讓您被全世界看到、無往不利，再也不用擔心剩餘的空房。透過手機APP，您可以輕易的使用QRcode做連線，並快速完成入住手續。
		我們的飯店夥伴，您可以自由設定您的降價幅度，也可以定時讓系統幫助你。您也可以以最優惠的價格，讓您的旅館出現在首頁!趕緊上架您的房型吧！</h4>		
		<div id="showbox"></div>	
	</div>
	<div class="col-xs-12 col-sm-2" align="center"></div>
	
</div>
<script>		
	function ShowTime(){
		document.getElementById('showbox').innerHTML = new Date();
		setTimeout('ShowTime()',1000);
	}window.onload=ShowTime();	
</script>
<%@ include file="footer.jsp" %>
</body>
</html>
