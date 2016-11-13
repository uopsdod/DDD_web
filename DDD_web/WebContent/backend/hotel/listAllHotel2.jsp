<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.ord.model.*"%>

<%-- EL寫法 --%>

<jsp:useBean id="hotelSvc" scope="page" class="com.hotel.model.HotelService" />



<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type">
<title>所有廠商會員</title>
</head>
<body>
此頁採用EL寫法
<table>
	<tr>
		<td>
			<h3>所有廠商會員 - ListAllHotel2.jsp</h3>
			<a href="<%=request.getContextPath()%>/backend/selectPage.jsp"> <img src="/DDD_web/backend/hotel/images/back1.gif"/>回首頁</a>	
		</td>
	</tr>
</table>

<%--錯誤表列--%>
<c:if test="${not empty errorMsgs}">
	請修正以下錯誤
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table border='1'>
	<tr>
		<th>廠商會員編號</th>
		<th>廠商名稱</th>
		<th>負責人</th>
		<th>帳號</th>
		<th>住址</th>
		<th>查詢廠商訂單</th>
	</tr>

	<c:forEach var="hotelVO" items="${hotelSvc.all}">
		<tr>
			<td>${hotelVO.hotelId}</td>
			<td>${hotelVO.hotelName}</td>
			<td>${hotelVO.hotelOwner}</td>
			<td>${hotelVO.hotelAccount}</td>
			<td>${hotelVO.hotelCity}${hotelVO.hotelCounty}${hotelVO.hotelRoad}</td>
			<td>				
				<form method="post" action="<%=request.getContextPath()%>/hotel/hotel.do">
					<input type="submit" value="送出查詢">
					<input type="hidden" name="hotelId" value="${hotelVO.hotelId}">
					<input type="hidden" name="action" value="listOrdsByHotelIdB">
				</form>
			</td>
		</tr>
	</c:forEach>
</table>

<%if(request.getAttribute("listOrdsByHotelId")!=null){%>
	<jsp:include page="listOrdsByHotelId.jsp"/>
<%}%>

</body>
</html>