<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hotelrep.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	List<HotelRepVO> list = (List<HotelRepVO>)request.getAttribute("list");
	pageContext.setAttribute("list", list );
%>

<html>
<head>
<title>所有廠商檢舉單資料 - listAllHotelRep.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有廠商檢舉單資料 - ListAllHotelRep.jsp</h3>
		<a href="<%=request.getContextPath()%>/backend/hotelRep/selectPage.jsp"><img src="<%=request.getContextPath()%>/backend/hotelRep/images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

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

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>廠商檢舉單編號</th>
		<th>(原告)廠商名稱</th>
		<th>(被告)一般會員姓名</th>
		<th>訂單編號</th>
		<th>處理的員工姓名</th>
		<th>檢舉內容</th>
		<th>處理狀態</th>
		<th>檢舉時間</th>
		<th>處理時間</th>
		<th>修改</th>
	</tr>
		
	<%@ include file="page1.file" %> 
	<c:forEach var="hotelRepVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${hotelRepVO.hotelRepId}</td>
			<td>${hotelRepVO.hotelRepHotelVO.hotelName}</td>
			<td>${hotelRepVO.hotelRepMemVO.memName}</td>
			<td>${hotelRepVO.hotelRepOrdVO.ordId}</td>
			<td><c:out value="${hotelRepVO.hotelRepEmpVO.empName}" default="尚無員工處理"/></td>
			
			<td><c:out value="${hotelRepVO.hotelRepContent}" default="無檢舉內容"/></td>
			
			<td>${hotelRepStatusTrans.get(hotelRepVO.hotelRepStatus)}</td>

			
			<td>${hotelRepVO.hotelRepDate}</td>
			
			<td><c:out value="${hotelRepVO.hotelRepReviewDate}" default="尚未處理"/></td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/hotelRep/hotelRep.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="hotelRepId" value="${empVO.empno}">
			     <input type="hidden" name="action"	value="getOneForUpdate"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
