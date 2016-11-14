<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.SimpleDateFormat" %>
<%@ page import="com.ord.model.*, com.room.model.*,com.hotel.model.*,com.mem.model.*" %>

<%-- 用Script練習 --%>

<%
	HashMap<String,String> ordStatusTrans = (HashMap<String,String>) application.getAttribute("ordStatusTrans");
%>

<%
	OrdVO ordVO = (OrdVO) request.getAttribute("ordVO");
%>

<!DOCTYPE html>
<html>
<head>
<title>訂單資料 - listOneOrd.jsp</title>
</head>
<body>
	<table border='1'>
		<tr>
			<td>
				<h3>訂單資料 - ListOneOrd.jsp</h3>
				<a href="<%=request.getContextPath()%>/backend/selectPage.jsp">
					<img src="<%=request.getContextPath()%>/backend/ord/images/back1.gif" alt="Back Home">回首頁
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
			<td><%=ordVO.getOrdId()%></td>			
			<td> ${ordVO.ordRoomVO.roomId} <br> ${ordVO.ordRoomVO.roomName} </td>			
			<td> ${ordVO.ordMemVO.memId} <br> ${ordVO.ordMemVO.memName} </td>
			<td> ${ordVO.ordHotelVO.hotelId} <br> ${ordVO.ordHotelVO.hotelName} </td>
			<td><%=ordVO.getOrdPrice()%></td>
			<td><%=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(ordVO.getOrdLiveDate())%></td>
			<td><%=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(ordVO.getOrdDate())%></td>
			<td><%=ordStatusTrans.get(ordVO.getOrdStatus())%></td>
			<td><%=ordVO.getOrdRatingContent()%></td>
			<td><%=ordVO.getOrdRatingStarNo()%></td>
			<td><%=ordVO.getOrdMsgNo()%></td>
			<td><img src="DBGifReader4?ordId=<%=ordVO.getOrdId()%>"></td>
		</tr>

	</table>

</body>
</html>