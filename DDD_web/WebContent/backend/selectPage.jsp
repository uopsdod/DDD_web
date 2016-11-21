<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>DDD Ord: Home</title>
</head>
<body>
	<table border='1'>
		<tr>
			<td><h3>DDD Ord: Home</h3>( MVC )</td>
		</tr>
	</table>

	<p>This is the Home page for DDD Ord: Home</p>

<h3>資料查詢</h3>
<!-- 錯誤表列 -->
<c:if test="${not empty errorMsgs}">
		請修正以下錯誤:
		<ul>
			<c:forEach var="message" items="{errorMsgs}">
				<li>${message}</li>
			</c:forEach>
		</ul>
</c:if>


<ul>
	<li><a href="<%=request.getContextPath()%>/backend/ord/listAllOrd.jsp">List</a> all Ords. </li><br><br>

	<li>
		<form method="post" action="<%=request.getContextPath()%>/ord/ord.do">
			<b>輸入帳單編號(像是2016111001)</b>
			<input type="text" name="ordId">
			<input type="submit" value="送出">
			<input type="hidden" name="action" value="getOneForDisplay">
		</form>
	</li>

	<jsp:useBean id="ordSvc" scope="page" class="com.ord.model.OrdService"/>

	<li>
		<form method="post" action="<%=request.getContextPath()%>/ord/ord.do">
			選擇訂單編號:
			<select name="ordId">
				<c:forEach var="ordVO" items="${ordSvc.all}">	
					<option value="${ordVO.ordId}">${ordVO.ordId}
				</c:forEach>
			</select>
			<input type="submit" value="送出">
			<input type="hidden" name="action" value="getOneForDisplay">
		</form>
	</li>

	<li>
		<form method="post" action="<%=request.getContextPath()%>/ord/ord.do">
			選擇簡訊驗證碼:
			<select name="ordId">
				<c:forEach var="ordVO" items="${ordSvc.all}">	
					<option value="${ordVO.ordId}">${ordVO.ordMsgNo}</option>
				</c:forEach>
			</select>	
			<input type="submit" value="送出">
			<input type="hidden" name="action" value="getOneForDisplay">		
		</form>
	</li>

	<jsp:useBean id="hotelSvc" scope="page" class="com.hotel.model.HotelService" />
	<li>
		<form method="post" action="<%=request.getContextPath()%>/hotel/hotel.do">
			選擇廠商會員:
			<select name="hotelId">
				<c:forEach var="hotelVO" items="${hotelSvc.all}">
					<option value="${hotelVO.hotelId}">${hotelVO.hotelName}</option>
				</c:forEach>
			</select>
			<input type="submit" value="送出">
			<input type="hidden" name="action" value="listOrdsByHotelIdA">
		</form>
	</li>
</ul>

<%-- 萬用複合查詢 以下欄位可隨意增減 --%>
<ul>
	<li>
		<form method="post" action="<%=request.getContextPath()%>/ord/ord.do" name="form1">
			萬用複合查詢:<br>
			輸入訂單編號:
			<input type="text" name="ordId"><br>
			輸入房型編號:
			<input type="text" name="ordRoomVO.roomId"><br>
			輸入一般會員編號:
			<input type="text" name="ordMemVO.memId"><br>
			輸入廠商會員編號:
			<input type="text" name="ordHotelVO.hotelId"><br>
			輸入訂單金額:
			<input type="number" name="ordPrice"><br>
			輸入簡訊驗證碼:
			<input type="text" name="ordMsgNo"><br>
			
			輸入訂單狀態名稱:
			<select name="ordStatus">
				<option value="">請選擇</option>
  				<option value="0">已下單</option>
  				<option value="1">主動取消</option>
  				<option value="2">已入住</option>
  				<option value="3">已繳費</option>
  				<option value="4">逾時取消</option>
			</select><br>

			輸入評價星星數:
				<select name="ordRatingStarNo">
						<option value="">請選擇</option>
	  					<option value="0">0顆星</option>
	  					<option value="1">1顆星</option>
	  					<option value="2">2顆星</option>
	  					<option value="3">3顆星</option>
	  					<option value="4">4顆星</option>
	    				<option value="5">5顆星</option>				
				</select><br>

			<input type="submit" name="送出">
			<input type="hidden" name="action" value="listOrdsByCompositeQuery">
		</form>
	</li>
</ul>


<h3>訂單管理</h3>

<ul>
	<li><a href="<%=request.getContextPath()%>/backend/ord/addOrd.jsp">Add</a> a new Ord.</li>
</ul>

<h3>廠商管理</h3>

<ul>
	<li><a href="<%=request.getContextPath()%>/backend/hotel/listAllHotel2.jsp">List</a> all Hotels.</li>
</ul>


<h3>[自己的方法](一般會員)列出所有訂單</h3>

		<form method="post" action="<%=request.getContextPath()%>/ord/ord.do">
			選擇一般會員編號:
			<select name="ordMemId">
				<c:forEach var="ordVO" items="${ordSvc.all}">	
					<option value="${ordVO.ordMemVO.memId}">${ordVO.ordMemVO.memId}
				</c:forEach>
			</select>	
			<input type="submit" value="送出">
			<input type="hidden" name="action" value="listAllByMemId">		
		</form>

<h3>[自己的方法](廠商會員)列出所有訂單</h3>

		<form method="post" action="<%=request.getContextPath()%>/ord/ord.do">
			選擇廠商會員編號:
			<select name="ordHotelId">
				<c:forEach var="ordVO" items="${ordSvc.all}">	
					<option value="${ordVO.ordHotelVO.hotelId}">${ordVO.ordHotelVO.hotelId}
				</c:forEach>
			</select>	
			<input type="submit" value="送出">
			<input type="hidden" name="action" value="listAllByHotelId">		
		</form>

</body>
</html>