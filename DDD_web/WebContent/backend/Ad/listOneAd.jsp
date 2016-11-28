<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ad.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	AdVO adVO = (AdVO) request.getAttribute("adVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html">
<title>Insert title here</title>
</head>
<body>

	<c:if test="${not empty errorMsgs}">
		<ul>
			<font color='red'>請修正以下錯誤: <c:forEach var="message"
					items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
		</ul>
		</font>
	</c:if>

	<table border='1' bordercolor='#CCCCFF' width='800'>
		<tr>
			<th>adId</th>
			<th>adAdPlanId</th>
			<th>adHotelId</th>
			<th>adStatus</th>
			<th>adPayDate</th>
			<th>adPic</th>
			<th>adPicContent</th>
			<th>adHit</th>
		</tr>
		<tr>
			<td>${adVO.adId}</td>
			<td>${adVO.adAdPlanId}</td>
			<td>${adVO.adHotelId}</td>
			<td>${adVO.adStatus}</td>
			<td>${adVO.adPayDate}</td>
			<td>${adVO.adPic}</td>
			<td>${adVO.adPicContent}</td>
			<td>${adVO.adHit}</td>
		</tr>
	</table>

</body>
</html>