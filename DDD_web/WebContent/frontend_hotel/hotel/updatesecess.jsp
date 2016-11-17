<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hotel.model.*"%>
<%@ include file="../head.jsp"%>
<meta http-equiv="Refresh" content="3;url=<%=request.getContextPath()%>/frontend_hotel/hotel/findByPrimaryKey.jsp">
<style>
#register{
	margin-top:-4%;
}
.img{
	margin-top:10%;
}	
</style>


<div class="col-xs-12 col-sm-10 tablediv text-center" align="center"> 
<img src="<%=request.getContextPath()%>/frontend_mem/GIF/loadgif.gif" width="500px" height="400px" class="img">	
	<H1 id="register">修改成功</H1>
	<a href="<%=request.getContextPath()%>/frontend_hotel/hotel/findByPrimaryKey.jsp"><h3>3秒後回資料修改，或您可以直接按此回</h3></a>


</div>
<%@ include file="../footer.jsp" %>
</body>
</html>