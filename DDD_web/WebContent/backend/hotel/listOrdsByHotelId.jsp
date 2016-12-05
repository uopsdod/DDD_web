<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, com.ord.model.*, java.text.SimpleDateFormat" %>

<%-- 用EL練習寫 --%>

<jsp:useBean id="listOrdsByHotelId" scope="request" type="java.util.List"/>

<!DOCTYPE html>
<html>
<head>
<title>廠商訂單資料 - listOrdsByHotelId.jsp</title>
</head>
<body>
<!-- 練習用EL寫法取值 -->

<!-- <table> -->
<!-- 	<tr> -->
<!-- 		<td> -->
<!-- 			<h3>廠商訂單資料 - listOrdsByHotelId.jsp</h3> -->
<%-- 			<a href="<%=request.getContextPath()%>/backend/selectPage.jsp"> <img src="<%=request.getContextPath()%>/backend/hotel/images/back1.gif"> 回首頁 </a> --%>
<!-- 		</td> -->
<!-- 	</tr> -->
<!-- </table> -->

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
</c:if>


<c:choose>
	<c:when test="${listOrdsByHotelId.size()!=0}">	
		<table class="table table-hover h-table" border="1">
			<!-- table標題 -->
			<thead>
				<tr style="background-color: #B0C4DE;">
					<th class="text-center">訂單編號</th>
					<th class="text-center">房型名稱</th>
					<th class="text-center">一般會員名稱</th>
					<th class="text-center">廠商會員名稱</th>
					<th class="text-center">下訂日期</th>
					<th class="text-center">訂單狀態名稱</th>
				</tr>
			</thead>
		
			<tbody>			
			<c:forEach var="ordVO" items="${listOrdsByHotelId}">
				<tr align="center" valign='middle'>
					<td>${ordVO.ordId}</td>
					<td>${ordVO.ordRoomVO.roomName}</td>
					<td>${ordVO.ordMemVO.memName}</td>
					<td>${ordVO.ordHotelVO.hotelName}</td>
					<td><%=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(((OrdVO)(pageContext.getAttribute("ordVO"))).getOrdDate())%></td>		
					<td>${ordStatusTrans.get(ordVO.ordStatus)}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</c:when>	
	<c:otherwise>
		<h3 align="center">您目前沒有任何一筆清單資料</h3>
	</c:otherwise>
</c:choose> 

<!-- <br>本網頁路徑:<br> -->
<%-- 	request.getServletPath(): <%= request.getServletPath() %> <br> --%>
<%-- 	request.getRequestURI():  <%= request.getRequestURI() %> --%>
</body>
</html>