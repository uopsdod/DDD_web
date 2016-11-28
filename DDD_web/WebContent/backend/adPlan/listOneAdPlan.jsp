<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.adplan.model.*"%>

<%
	AdPlanVO adPlanVO = (AdPlanVO) request.getAttribute("adPlanVO"); //把抓到的資料放在這裡
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
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

	<table border='1' bordercolor='#CCCCFF' width='800'> <!-- 把資料秀出來 -->
		<tr>
			<th>Banner方案編號</th>
			<th>Banner方案名稱</th>
			<th>刊登開始日期</th>
			<th>刊登結束日期</th>
			<th>方案費用</th>
			<th>剩餘可購買數量</th>
		</tr>
		<tr>
				<td>${adPlanVO.adPlanId}</td><!-- EL語法 p148 -->
				<td>${adPlanVO.adPlanName}</td>
				<td>${adPlanVO.adPlanStartDate}</td>
				<td>${adPlanVO.adPlanEndDate}</td>
				<td>${adPlanVO.adPlanPrice}</td>				
				<td>${adPlanVO.adPlanRemainNo}</td>
								
				<td>
	</table>

</body>
</html>