<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.text.SimpleDateFormat, com.ord.model.OrdVO" %>
<!DOCTYPE html PUBLIC>

<jsp:useBean id="listOrdsByCompositeQuery" scope="request" type="java.util.List" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>複合查詢 - ListOrdsByCompositeQuery</title>
</head>
<body>

<table border='1'>
	<tr>
		<td>
			<h3>複合查詢 訂單 - ListOrdsByCompositeQuery.jsp</h3>
			<a href="<%=request.getContextPath()%>/backend/selectPage.jsp"> <img src="images/back1.gif"> 回首頁 </a>
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


	<c:forEach var="ordVO" items="${listOrdsByCompositeQuery}">
		<tr align="center" valign='middle'>
			<td>${ordVO.ordId}</td>
			<td>${ordVO.ordRoomVO.roomId}</td>
			<td>${ordVO.ordMemVO.memId}</td>
			<td>${ordVO.ordHotelVO.hotelId}</td>

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

<!-- 			<td> -->
<%-- 				<form method="post" action="<%=request.getContextPath()%>/ord/ord.do"> --%>
<!-- 					<input type="submit" value="修改"> -->
<%-- 					<input type="hidden" name="ordId" value="${ordVO.ordId}"> --%>
<%-- 					<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> --%>
<%-- 					<input type="hidden" name="whichPage" value="<%=whichPage%>"> --%>
<!-- 					<input type="hidden" name="action" value="getOneForUpdate"> -->
<!-- 				</form> -->
<!-- 			</td> -->
<!-- 			<td> -->
<%-- 				<form method="post" action="<%=request.getContextPath()%>/ord/ord.do"> --%>
<!-- 					<input type="submit" value="刪除"> -->
<%-- 					<input type="hidden" name="ordId" value="${ordVO.ordId}"> --%>
<%-- 					<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> --%>
<%-- 					<input type="hidden" name="whichPage" value="<%=whichPage%>"> --%>
<!-- 					<input type="hidden" name="action" value="delete"> -->
<!-- 				</form> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>

</body>
</html>