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
<html lang="">
<head>	
	<!-- 請輸入標題 -->
	<title>listOneOrdSimple.jsp</title>

</head>

<body>
	<!-- 主要的table -->
	<table class="table table-hover" border="1">
		<!-- table標題 -->
		<thead style="text-align:center">
			<tr style="background-color: #B0C4DE;">
				<th colspan='2' class="text-center">訂單明細</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th class="text-center">訂單編號</th>
				<td><%=ordVO.getOrdId()%></td>
			</tr>
			<tr>
				<th class="text-center">房型編號</th>
				<td> ${ordVO.ordRoomVO.roomId} [${ordVO.ordRoomVO.roomName}] </td>
			</tr>
			<tr>
				<th class="text-center">一般會員編號</th>
				<td> ${ordVO.ordMemVO.memId} [${ordVO.ordMemVO.memName}] </td>
			</tr>
			<tr>
				<th class="text-center">廠商會員編號</th>
				<td> ${ordVO.ordHotelVO.hotelId} [${ordVO.ordHotelVO.hotelName}] </td>
			</tr>
			<tr>
				<th class="text-center">訂單金額</th>
				<td><%=ordVO.getOrdPrice()%></td>
			</tr>
			<tr>
				<th class="text-center">入住日期</th>
				<% if(ordVO.getOrdLiveDate()!=null){ %>
					<td><%=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(ordVO.getOrdLiveDate())%></td>
				<% } else { %>
					<td>尚未入住</td>
				<% } %>
			</tr>
			<tr>
				<th class="text-center">下訂日期</th>
				<td><%=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(ordVO.getOrdDate())%></td>
			</tr>
			<tr>
				<th class="text-center">訂單狀態名稱</th>
				<td><%=ordStatusTrans.get(ordVO.getOrdStatus())%></td>
			</tr>
			<tr>
				<th class="text-center">評價內容</th>
				
				<% if(ordVO.getOrdRatingContent()!=null){ %>
					<td><%=ordVO.getOrdRatingContent()%></td>
				<% } else { %>
					<td>目前沒有評價內容</td>
				<% } %>				
				
			</tr>
			<tr>
				<th class="text-center">評價星星數</th>
				
				<% if(ordVO.getOrdRatingStarNo()!=null){ %>
					<td><%=ordVO.getOrdRatingStarNo()%></td>
				<% } else { %>
					<td>目前沒有評價星星數</td>
				<% } %>						
				
			</tr>
			<tr>
				<th class="text-center">簡訊驗證碼</th>
				<td><%=ordVO.getOrdMsgNo()%></td>
			</tr>
			<tr>
				<th class="text-center">QR Code圖片</th>
				<td><img src="<%=request.getContextPath()%>/ord/DBGifReader4?ordId=<%=ordVO.getOrdId()%>" width="120"></td>
			</tr>
		</tbody>
	</table>
</body>
</html>