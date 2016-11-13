<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, com.ord.model.*, java.text.SimpleDateFormat" %>

<%
	String ordHotelId =  (String) request.getAttribute("ordHotelId");
	OrdService ordSvc = new OrdService();
	List<OrdVO> list = ordSvc.getAllByOrdHotelId(ordHotelId);
	pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
<title>該廠商會員所有訂單資料 - listAllByHotelId.jsp</title>
</head>
<body>
練習用EL寫法取值

<table>
	<tr>
		<td>
			<h3>該廠商會員所有訂單資料 - listAllByHotelId.jsp</h3>
			<a href="selectPage.jsp"> <img src="images/back1.gif"> 回首頁 </a>
		</td>
	</tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table border='1'>
	<tr>
		<th>訂單編號</th>
		<th>房型編號</th>
		<th>一般會員編號</th>
		<th>廠商會員編號</th>
		<th>訂單金額</th>
		<th>入住日期</th>
		<th>下訂日期</th>
		<th>訂單狀態名稱</th>
		<th>評價內容</th>
		<th>評價星星數</th>
		<th>簡訊驗證碼</th>
		<th>QR Code圖片</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="pages/page1.file" %>
	<c:forEach var="ordVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${ordVO.ordId}</td>
			<td>${ordVO.ordRoomVO.roomId}</td>
			<td>${ordVO.ordMemVO.memId}</td>
			<td>${ordVO.ordHotelVO.hotelId}</td>
			<td>${ordVO.ordPrice}</td>
			<td><%=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(((OrdVO)(pageContext.getAttribute("ordVO"))).getOrdLiveDate())%></td>
			<td><%=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(((OrdVO)(pageContext.getAttribute("ordVO"))).getOrdDate())%></td>		
			<td>${ordVO.ordStatus}</td>
			<td>${ordVO.ordRatingContent}</td>
			<td>${ordVO.ordRatingStarNo}</td>
			<td>${ordVO.ordMsgNo}</td>
			<td><img src="DBGifReader4?ordId=${ordVO.ordId}"></td>
			<td>
				<form method="post" action="<%=request.getContextPath()%>/backend/ord/ord.do">
					<input type="submit" value="修改">
					<input type="hidden" name="ordId" value="${ordVO.ordId}">
					<input type="hidden" name="action" value="getOneForUpdate">
				</form>
			</td>
			<td>
				<form method="post" action="<%=request.getContextPath()%>/backend/ord/ord.do">
					<input type="submit" value="刪除">
					<input type="hidden" name="ordId" value="${ordVO.ordId}">
					<input type="hidden" name="action" value="delete">
				</form>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file"%>

</body>
</html>