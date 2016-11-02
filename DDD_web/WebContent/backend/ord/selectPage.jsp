<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Backend Ord Home</title>
</head>
<body>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr align='center' valign='middle'>
		<td>
			<h3>Backend Ord Home (MVC)</h3>
		</td>
	</tr>
</table>

<p>This is Home page for Backend Ord</p>

<h3>資料查詢:</h3>
<%-- 錯誤列表 --%>

<c:if test="${not empty errorMsgs}">
	請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
	<li> <a href="listAllOrd.jsp">List</a> all Ords. </li> <br><br>

	<li>
		<form method="post" action="ord.do">
			<b>輸入帳單編號(像是2016111001)</b>
			<input type="text" name="ordId">
			<input type="submit" value="送出">
			<input type="hidden" name="action" value="getOneForDisplay">
		</form>
	</li>

	<jsp:useBean id="ordSvc" scope="page" class="com.ord.model.OrdService" />

	<li>
		<form method="post" action="ord.do">
			選擇訂單編號:
			<select name="ordId">
				<c:forEach var="ordVO" items="{ordSvc.all}">	
					<option value="${ordVO.ordId}">${ordVO.ordId}
				</c:forEach>
			</select>
			<input type="submit" value="送出">
			<input type="hidden" name="action" value="getOneForDisplay">
		</form>
	</li>
</ul>

</body>
</html>