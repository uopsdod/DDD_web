<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hotel.model.*"%>
<%@ include file="../head.jsp"%>
<meta http-equiv="Refresh" content="3;url=<%=request.getContextPath()%>/frontend_hotel/hotel/findByPrimaryKey.jsp">
<style>
#register{
	margin-top:20%;
}
	
</style>


<div class="col-xs-12 col-sm-10 tablediv" align="center"> 
	<H1 id="register">修改成功</H1>
	<a href="<%=request.getContextPath()%>/frontend_hotel/hotel/findByPrimaryKey.jsp"><li>3秒後回資料修改，或您可以直接按此回</li></a>


</div>
<%@ include file="../footer.jsp" %>
</body>
</html>