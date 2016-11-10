<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>DDD Ord: Home</title>
</head>
<body>

	<table>
		<tr>
			<td><h3>DDD Ord: Home</h3>( MVC )</td>
		</tr>
	</table>

	<p>This is the Home page for DDD Ord: Home</p>


<h3>資料查詢</h3>
<!-- 錯誤表列 -->
<c:if test="${not empty errorMsgs}">
		請修正以下錯誤:
		<ul>
			<c:forEach var="message" items="{errorMsgs}">
				<li>${message}</li>
			</c:forEach>
		</ul>
</c:if>


<ul>
	<li><a href="<%=request.getContextPath()%>/backend/ord/listAllOrd.jsp">List</a> all Ords. </li><br><br>

	<li>
		<form method="post" action="<%=request.getContextPath()%>/backend/ord/ord.do">
			<b>輸入帳單編號(像是2016111001)</b>
			<input type="text" name="ordId">
			<input type="submit" value="送出">
			<input type="hidden" name="action" value="getOneForDisplay">
		</form>
	</li>

	<jsp:useBean id="ordSvc" scope="page" class="com.ord.model.OrdService"/>

	<li>
		<form method="post" action="<%=request.getContextPath()%>/backend/ord/ord.do">
			選擇訂單編號:
			<select name="ordId">
				<c:forEach var="ordVO" items="${ordSvc.all}">	
					<option value="${ordVO.ordId}">${ordVO.ordId}
				</c:forEach>
			</select>
			<input type="submit" value="送出">
			<input type="hidden" name="action" value="getOneForDisplay">
		</form>
	</li>

	<li>
		<form method="post" action="<%=request.getContextPath()%>/backend/ord/ord.do">
			選擇簡訊驗證碼:
			<select name="ordId">
				<c:forEach var="ordVO" items="${ordSvc.all}">	
					<option value="${ordVO.ordId}">${ordVO.ordMsgNo}
				</c:forEach>
			</select>	
			<input type="submit" value="送出">
			<input type="hidden" name="action" value="getOneForDisplay">		
		</form>
	</li>

	<jsp:useBean id="hotelSvc" scope="page" class="com.hotel.model.HotelService" />
	<li>
		<form method="post" action="<%=request.getContextPath()%>/backend/hotel/hotel.do">
			選擇部門:(未完工)
			<select name="hotelId">
				<c:forEach var="hotelVO" items="${hotelSvc.all}">
					<option value="${hotelVO.hotelId}">${hotelVO.hotelName}</option>
				</c:forEach>
			</select>
			<input type="submit" value="送出">
			<input type="hidden" name="action" value="listOrdsByHotelIdA">
		</form>
	</li>
</ul>

<h3>訂單管理</h3>

<ul>
	<li><a href="<%=request.getContextPath()%>/backend/ord/addOrd.jsp">Add</a> a new Ord.</li>
</ul>

<h3>廠商管理</h3>

<ul>
	<li><a href="<%=request.getContextPath()%>/backend/hotel/listAllHotel.jsp">List</a> all Hotels.</li>
</ul>


</body>
</html>