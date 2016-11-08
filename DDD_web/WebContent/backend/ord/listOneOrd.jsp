<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ord.model.*"%>
<!DOCTYPE html>
<%
OrdVO ordVO = (OrdVO) request.getAttribute("ordVO"); //OrdServlet.java(Controller),存入req的ordVO物件
%>
<html>
<head>
<title>訂單資料 - listOneOrd.jsp</title>
</head>
<body>
	<table border='1'>
		<tr>
			<td>
				<h3>訂單資料 - ListOneOrd.jsp</h3>
				<a href="selectPage.jsp">
					<img src="images/back1.gif" alt="Back Home">回首頁
				</a>
			</td>
		</tr>
	</table>


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
		</tr>

		<tr>
			<td>${ordVO.ordId}</td>
			<td>${ordVO.ordRoomId}</td>
			<td>${ordVO.ordMemId}</td>
			<td>${ordVO.ordHotelId}</td>
			<td>${ordVO.ordPrice}</td>
			<td>${ordVO.ordLiveDate}</td>
			<td>${ordVO.ordDate}</td>
			<td>${ordVO.ordStatus}</td>
			<td>${ordVO.ordRatingContent}</td>
			<td>${ordVO.ordRatingStarNo}</td>
			<td>${ordVO.ordMsgNo}</td>
			<td><img src="DBGifReader4?ordId=${ordVO.ordId}"></td>
		</tr>

	</table>

</body>
</html>