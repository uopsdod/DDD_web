<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ord.model.*"%>
<%
OrdVO ordVO = (OrdVO) request.getAttribute("ordVO");
%>

<!DOCTYPE html>
<html>
	<head>
		<title>訂單資料新增 - addOrd.jsp</title>
		<link rel="stylesheet" type="text/css">
		<script type="text/javascript"></script>
	</head>	
		
	<body>		
		<table border='1'>
			<tr>
				<td>
					<h3>訂單資料新增 - addOrd.jsp</h3>
				</td>
				<td>
					<a href="selectPage.jsp"><img src="images/tomcat.gif"> 回首頁</a>
				</td>
			</tr>
		</table>

		<h3>資料訂單:</h3>
		<!-- 錯誤表列 -->
		<c:if test="{not empty errorMsgs}">
			請修正以下錯誤:
			<ul>
				<c:forEach var="message" items="{errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</c:if>

		<form method="post" action="ord.do" name="form1">
			<table>
				<jsp:useBean id="roomSvc" scope="page" class="com.room.model.RoomService"/>
				<tr>
					<td>房型編號:</td>
					<td>
						<select name="ordRoomId">
							<c:forEach var="roomVO"  items="${roomSvc.all}">
								<option value="${roomVO.roomId}" ${(ordVO.ordRoomId==roomVO.roomId)?'selected':''}>${roomVO.roomName}
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
								<option value="${memVO.memId}" ${(ordVO.ordMemId==memVO.memId)? 'selected':''}>${memVO.memName}
							</c:forEach>
						</select>
					</td>
				</tr>
				
				<tr>
					<!-- 改天升級成外來鍵 -->
					<td>廠商會員編號:</td>
					<td>
						<input type="text" name="ordHotelId" 
						value="<%= (ordVO==null)? "10001" : ordVO.getOrdHotelId()%>"/>
					</td>
				</tr>
				<tr>
					<td>訂單金額:</td>
					<td>
						<input type="text" name="ordPrice" 
						value="<%= (ordVO==null)? "8888" : ordVO.getOrdPrice()%>"/>
					</td>
				</tr>
				<tr>
					<%java.sql.Date dateSQL = new java.sql.Date(System.currentTimeMillis());%>
					<td>入住日期:</td>
					<td>
						<input type="datetime-local" name="ordLiveDate" value="<%= (ordVO==null)? dateSQL : ordVO.getOrdLiveDate()%>">
					</td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td>QR Code圖片:</td> -->
<!-- 					<td> -->
<!-- 						<input type="text" name="ordQrPic"  -->
<%-- 						value="<%= (ordVO==null)? "" : ordVO.getOrdQrPic()%>"/> --%>
<!-- 					</td> -->
<!-- 				</tr> -->
				<tr>
					<td>簡訊驗證碼:</td>
					<td>
						<input type="text" name="ordMsgNo" value="<%= (ordVO==null)? "AAAA" : ordVO.getOrdMsgNo()%>"/>
					</td>
				</tr>
			</table>

			<br>
			<input type="hidden" name="action" value="insert">
			<input type="submit" value="送出新增">
		</form>
	</body>
</html>