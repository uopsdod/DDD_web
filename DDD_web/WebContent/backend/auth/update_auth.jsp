<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.auth.model.*"%>
<%@ page import="java.util.*"%>

<%
	List<String> authIdList = (List<String>) request.getAttribute("authList");
	pageContext.setAttribute("authIdList", authIdList);
	System.out.println(authIdList);
	String empId = (String) request.getAttribute("empId");
	System.out.println(empId);
%>
<!-- 如果權限沒有人事轉到首頁怕他偷吃步-->


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/auth/css/sweet-alert.css">
<script src="<%=request.getContextPath()%>/backend/auth/js/sweet-alert.js"></script>
<style>
	.tablediv{
		margin-top:2%;
	}
	.checkboxname{
		margin-right:25px;
		font-size:30px;
	}
	.checkauth{
		padding:10px;
		zoom: 2;
		
	}
	#btnOfauth{
	opacity: 0.9;
	z-index: 1;
	background: #0283df;
	color: #ffffff;
	position: relative;
	font-weight: bold;
	font-family: Tahoma, Verdana, 微軟正黑體;
	border: 0px;
	border-radius: 10px;
	padding: 4px;
	font-size: 20px;
	width:100px;
	margin-bottom:10px;
	
}
	
</style>
</head>
<body>
	
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>
	
	<FORM name="theForm" METHOD="post" ACTION="<%=request.getContextPath()%>/auth/auth.do">
	<div class="col-xs-12 col-sm-10 tablediv text-center" > 
	<table border="0" align="center">
		<jsp:useBean id="auth" scope="request"
			class="com.auth.model.AuthService" />
		<c:forEach var="authvo" items="${auth.all}">
			<tr style="display: inline-block" >
				<td ><input type="checkbox" name="authIdlIST" size="45" class="checkauth"
					value="${authvo.authId}"
					<c:if test="${fn:contains(authIdList,authvo.authId)}">checked</c:if>><div class="checkboxname">${authvo.authName}</div></td>
				<!-- 						這一個會判斷第二個參數是否存在於第一個參數里面，如果有返回true，沒有則false。和java String.contains()一樣概念。-->
				<%-- 					${authIdList.contains(authvo.authId)? 'checked':'' } --%>
				<!-- 							前面是查出來的字串 -->
			</tr>
		</c:forEach>
	</table>
	
		<br> <input type="hidden" name="action" value="update" > 		
		<input type="hidden" name="authIdlistNo" value="${param.empId}"> 	
		<input type="button" value="確認修改"  id="btnOfauth" onClick="Juge()">
		<br>
		</div>
	</FORM>
	
	<script>
	    function Juge() {     			
		    setTimeout('document.theForm.submit();', 2000);
			swal("修改成功", "You clicked the button!", "success");
	    }
	</script>
</body>
</html>

