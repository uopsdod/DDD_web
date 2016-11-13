<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, com.ord.model.*, java.text.SimpleDateFormat" %>

<%-- 用EL練習寫 --%>

<jsp:useBean id="listOrdsByHotelId" scope="request" type="java.util.Set"/>
<jsp:useBean id="roomSvc" scope="page" class="com.room.model.RoomService" />
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
<jsp:useBean id="hotelSvc" scope="page" class="com.hotel.model.HotelService" />

<!DOCTYPE html>
<html>
<head>
<title>廠商訂單資料 - listOrdsByHotelId.jsp</title>
</head>
<body>
練習用EL寫法取值

<table>
	<tr>
		<td>
			<h3>廠商訂單資料 - listOrdsByHotelId.jsp</h3>
			<a href="<%=request.getContextPath()%>/backend/selectPage.jsp"> <img src="/DDD_web/backend/hotel/images/back1.gif"> 回首頁 </a>
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
<!-- 		<th>入住日期</th> -->
		<th>下訂日期</th>
		<th>訂單狀態名稱</th>
<!-- 		<th>評價內容</th> -->
<!-- 		<th>評價星星數</th> -->
<!-- 		<th>簡訊驗證碼</th> -->
<!-- 		<th>QR Code圖片</th> -->
		<th>修改</th>
		<th>刪除</th>
	</tr>

	<c:forEach var="ordVO" items="${listOrdsByHotelId}">
		<tr>
			<td>${ordVO.ordId}</td>

			<%-- <td>${ordVO.ordRoomId}</td> --%>
			
			<td>
				<c:forEach var="roomVO" items="${roomSvc.all}">
					<c:if test="${ordVO.ordRoomVO.roomId==roomVO.roomId}">
						${roomVO.roomId} <br>
						[${roomVO.roomName}]
					</c:if>
				</c:forEach>
			</td>

			<%-- <td>${ordVO.ordMemId}</td> --%>
			<td>
				<c:forEach var="memVO" items="${memSvc.all}">
					<c:if test="${ordVO.ordMemVO.memId==memVO.memId}">
						${memVO.memId} <br>
						[${memVO.memName}]
					</c:if>
				</c:forEach>
			</td>

			<%-- <td>${ordVO.ordHotelId}</td> --%>
			<td>
				<c:forEach var="hotelVO" items="${hotelSvc.all}">
					<c:if test="${ordVO.ordHotelVO.hotelId==hotelVO.hotelId}">
						${hotelVO.hotelId} <br>
						[${hotelVO.hotelName}]
					</c:if>
				</c:forEach>
			</td>

			<td>${ordVO.ordPrice}</td>
<%-- 			<td><%=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(((OrdVO)(pageContext.getAttribute("ordVO"))).getOrdLiveDate())%></td> --%>
			<td><%=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(((OrdVO)(pageContext.getAttribute("ordVO"))).getOrdDate())%></td>		
			
<%-- 			<td>${ordVO.ordStatus}</td> --%>
<%-- 			<td><%=ordStatusTrans.get( ((OrdVO)(pageContext.getAttribute("ordVO"))).getOrdStatus() )%></td> --%>

			<td>${ordStatusTrans.get(ordVO.ordStatus)}</td>
			
<%-- 			<td>${ordVO.ordRatingContent}</td> --%>
<%-- 			<td>${ordVO.ordRatingStarNo}</td> --%>
<%-- 			<td>${ordVO.ordMsgNo}</td> --%>
<%-- 			<td><img src="DBGifReader4?ordId=${ordVO.ordId}"></td> --%>
			<td>
				<form method="post" action="<%=request.getContextPath()%>/backend/ord/ord.do">
					<input type="submit" value="修改">
					<input type="hidden" name="ordId" value="${ordVO.ordId}">
					<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
					<input type="hidden" name="action" value="getOneForUpdate">
				</form>
			</td>
			<td>
				<form method="post" action="<%=request.getContextPath()%>/backend/ord/ord.do">
					<input type="submit" value="刪除">
					<input type="hidden" name="ordId" value="${ordVO.ordId}">
					<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
					<input type="hidden" name="action" value="delete">
				</form>
			</td>
		</tr>
	</c:forEach>
</table>


<br>本網頁路徑:<br>
	request.getServletPath(): <%= request.getServletPath() %> <br>
	request.getRequestURI():  <%= request.getRequestURI() %>
</body>
</html>