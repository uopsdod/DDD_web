<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ include file="../indexHeader.jsp"%>
<meta http-equiv="Refresh" content="1;url=<%=request.getContextPath()%>/frontend_mem/mem/member.jsp">

<style type="text/css">
    
    body{
    	background-color: #eaebed;
    }
    #h1register{
    font-family: Tahoma, Verdana, 微軟正黑體;
    
    margin-top:-8%;
	}

</style>
    
	
    <div class="col-md-12 text-center">
        <img src="<%=request.getContextPath()%>/frontend_mem/GIF/loading.gif">
        <h1 id="h1register"><img src="<%=request.getContextPath()%>/frontend_mem/img/checked.png" width="35px" hight="40px" style="margin-left:10px;"> 修改成功</h1>
    </div>
<%@ include file="../indexFooter.jsp" %>
