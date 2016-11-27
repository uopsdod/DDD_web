<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.hotelrep.model.*"%>

<html>
<head>
<title>廠商檢舉單資料 - listOneHotelRep.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>廠商檢舉單資料 - listOneHotelRep.jsp</h3>
		<a href="<%=request.getContextPath()%>/backend/hotelRep/selectPage.jsp"><img src="<%=request.getContextPath()%>/backend/hotelRep/images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
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
	</tr>
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
	</tr>
</table>

</body>
</html>
