<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.ord.model.*, java.text.SimpleDateFormat" %>
<%
	OrdVO ordVO = (OrdVO) request.getAttribute("ordVO");
%>

<!DOCTYPE html>
<html>
<head>
	<title>訂單資料修改 - updateOrdInput.jsp</title>
	<link rel="stylesheet" type="text/css">
	<script type="text/javascript"></script>
</head>

<body>
<table border='1'>
	<tr>
		<td>
			<h3>訂單資料修改 - updateOrdInput.jsp</h3>
			<a href="selectPage.jsp"><img src="images/back1.gif" alt="">回首頁</a>
		</td>
	</tr>

</table>

<h3>資料修改:</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">	
			<li>${message}</li>	
		</c:forEach>
	</ul>
</c:if>


<form method="post" action="ord.do" name="form1" enctype="multipart/form-data">
	<table>
		<tr>
			<td>訂單編號:</td>
			<td><%=ordVO.getOrdId()%></td>
		</tr>

		<jsp:useBean id="roomSvc" scope="page" class="com.room.model.RoomService"/>
		<tr>
			<td>房型編號:</td>
			<td>
				<select name="ordRoomId">
					<c:forEach var="roomVO" items="${roomSvc.all}">
						<option value="${roomVO.roomId}" ${(ordVO.ordRoomId==roomVO.roomId)?'selected':''}>${roomVO.roomName}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		
		<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService"/>
		<tr>
			<td>一般會員編號:</td>
			<td>
				<select name="ordMemId">
					<c:forEach var="memVO" items="${memSvc.all}">
						<option value="${memVO.memId}" ${(ordVO.ordMemId==memVO.memId)? 'selected':''}>${memVO.memName}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		
		<jsp:useBean id="hotelSvc" scope="page" class="com.hotel.model.HotelService"/>
		<tr>
			<td>廠商會員編號:</td>
			<td>
				<select name="ordHotelId">
					<c:forEach var="hotelVO" items="${hotelSvc.all}">
						<option value="${hotelVO.hotelId}" ${(ordVO.ordHotelId==hotelVO.hotelId)?'selected':''}>${hotelVO.hotelName}</option>
					</c:forEach>
				</select>
			</td>
		</tr>

		<tr>
			<td>訂單金額:</td>
			<td>
				<input type="number" name="ordPrice" 
				value="<%=ordVO.getOrdPrice()%>"/>
			</td>
		</tr>

		<%java.sql.Date dateSQL = new java.sql.Date(ordVO.getOrdLiveDate().getTime());%>
		<tr>
			<td>入住日期:</td>
			<td>
				<input type="date" name="ordLiveDate" value="<%=dateSQL%>">
			</td>
		</tr>

		<tr>
			<td>下訂日期:</td>
			<td><%=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(ordVO.getOrdDate())%></td>
		</tr>

		<tr>
			<td>訂單狀態名稱:</td>
			<td>				
				<select name="ordStatus">
  					<option value="0">已下單</option>
  					<option value="1">主動取消</option>
  					<option value="2">已入住</option>
  					<option value="3">已繳費</option>
  					<option value="4">逾時取消</option>
				</select>
			</td>
		</tr>

		<tr>
			<td>評價內容:</td>
			<td>
				<input type="text" name="ordRatingContent" value="<%=ordVO.getOrdRatingContent()%>">
			</td>
		</tr>

		<tr>
			<td>評價星星數:</td>
			<td>
				<select name="ordRatingStarNo">
  					<option value="1">1顆星</option>
  					<option value="2">2顆星</option>
  					<option value="3">3顆星</option>
  					<option value="4">4顆星</option>
  					<option value="5">5顆星</option>				
				</select>			
			</td>
		</tr>

		<tr>
			<td>簡訊驗證碼:</td>	
			<td>
				<input type="text" name="ordMsgNo" value="<%=ordVO.getOrdMsgNo()%>">
			</td>			
			
		</tr>
		
		<tr>
			<th>QR Code圖片</th>
			<td>
				<img src="DBGifReader4?ordId=${ordVO.ordId}">
				<br>
				<input type="file" name="ordQrPic"/>
			</td>
		</tr>
	</table>
	<br>
	<input type="hidden" name="action" value="update">
	<input type="hidden" name="ordId" value="<%=ordVO.getOrdId()%>">
	<input type="hidden" name="ordDate" value="<%=ordVO.getOrdDate().getTime()%>">
	<input type="submit" value="送出修改">
</form>
	
</body>
</html>