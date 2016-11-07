<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.ord.model.*" %>
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


<form method="post" action="ord.do" name="form1">
	<table>
		<tr>
			<td>訂單編號:</td>
			<td><%=ordVO.getOrdId()%></td>
		</tr>

		<tr>
			<td>房型編號:</td>
			<td><%=ordVO.getOrdRoomId()%></td>
		</tr>
		
		<tr>
			<td>一般會員編號:</td>
			<td><%=ordVO.getOrdMemId()%></td>
		</tr>
		
		<tr>
			<td>廠商會員編號:</td>
			<td><%=ordVO.getOrdHotelId()%></td>
		</tr>
		<tr>
			<td>訂單金額:</td>
			<td><%=ordVO.getOrdPrice()%></td>
		</tr>
		<tr>
			<td>入住日期:</td>
			<td><%=ordVO.getOrdLiveDate()%></td>
		</tr>

		<tr>
			<td>下訂日期:</td>
			<td><%=ordVO.getOrdDate()%>></td>
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
				<input type="text" name="ordRatingContent" value="<%= (ordVO.getOrdRatingContent() == null)? "我不轉彎" : ordVO.getOrdRatingContent()%>">
			</td>
		</tr>

		<tr>
			<td>評價星星數:</td>
			<td>
				<select name="ordRatingStarNo">
  					<option value="0">1顆星</option>
  					<option value="1">2顆星</option>
  					<option value="2">3顆星</option>
  					<option value="3">4顆星</option>
  					<option value="5">5顆星</option>
    				<option value="6">6顆星</option>
  					<option value="7">7顆星</option>
  					<option value="8">8顆星</option>
  					<option value="9">9顆星</option>
  					<option value="10">10顆星</option>					
				</select>			
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
			<td><%=ordVO.getOrdMsgNo()%></td>
		</tr>
	</table>
	<br>
	<input type="hidden" name="action" value="update">
	<input type="hidden" name="ordId" value="<%=ordVO.getOrdId()%>">
	<input type="submit" value="送出修改">
</form>
	
</body>
</html>