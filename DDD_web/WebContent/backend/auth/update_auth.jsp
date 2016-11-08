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


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>資料修改:</h3>
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

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/auth/auth.do">
	<table border="0">
		<jsp:useBean id="auth" scope="request"
			class="com.auth.model.AuthService" />
		<c:forEach var="authvo" items="${auth.all}">
			<tr>
				<td><input type="checkbox" name="authIdlIST" size="45"
					value="${authvo.authId}"
					<c:if test="${fn:contains(authIdList,authvo.authId)}">checked</c:if>>${authvo.authName}</td>
				<!-- 						這一個會判斷第二個參數是否存在於第一個參數里面，如果有返回true，沒有則false。和java String.contains()一樣概念。-->
				<%-- 					${authIdList.contains(authvo.authId)? 'checked':'' } --%>
				<!-- 							前面是查出來的字串 -->
			</tr>
		</c:forEach>
	</table>
	
		<br> <input type="hidden" name="action" value="update"> 
		
		<input type="hidden" name="authIdlistNo" value="${param.empId}"> 	
		<input type="submit" value="確認修改" >
	</FORM>
</body>
</html>
<script>
	
</script>