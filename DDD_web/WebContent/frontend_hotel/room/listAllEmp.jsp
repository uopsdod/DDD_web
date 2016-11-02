<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.room.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    RoomService empSvc = new RoomService();
    List<RoomVO> list = empSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html lang="">
	<head>
<!-- 		<meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
<!-- 		<meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!-- 		<title>Title Page</title> -->
<!-- 		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> -->
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
	</head>
	<body>
<body bgcolor='white' style="padding:0px;margin:0px">	
	
	<div class="panel panel-success" style="margin:0px;background:#dff0d8;padding:15px;height:30px;border-color:pink"  >
		<div class="panel-heading">
			<a href="<%=request.getContextPath()%>/room/addEmp.jsp"><img src="images/add.png" width="30"  border="0"></a>
		 	<span align="right">
		 	<a href="<%=request.getContextPath()%>/room/select_page.jsp"><img src="images/back.png" width="30"  border="0"></a>
			</span>
		</div>			  
	</div>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<table border='1' bordercolor='#CCCCFF' width='100%' cellspacing="0">
	<tr>
<!-- 		<th>房間編號</th> -->
<!-- 		<th>所屬hotel編號</th> -->
		<th>房間名稱</th>
		<th>總房間數</th>
		<th>剩餘房數</th>		
		<th>房間定價</th>
		<th>是否上架中</th>
		<th>是否自動上架</th>	
		<th>折扣開始時間</th>
		<th>折扣結束時間</th>
		<th>單位時間折扣百分比</th>
		<th>折扣單位時間</th>
		<th>是否一價到底</th>	
		
		
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="roomVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
<%-- 		<td>${roomVO.roomId}</td> --%>
<%-- 		<td>${roomVO.roomHotelId}</td> --%>
		<td><a href=""  id="${roomVO.roomId}"  onclick="show()">${roomVO.roomName}</a></td>
		<td>${roomVO.roomTotalNo}</td>
		<td>${roomVO.roomRemainNo}</td>
		
		
		<td>${roomVO.roomPrice}</td>
		<td>${roomVO.roomForSell}</td>
		<td>${roomVO.roomForSellAuto}</td>
		
		<td>${roomVO.roomDiscountStartDate}</td>
		<td>${roomVO.roomDiscountEndDate}</td>
		<td>${roomVO.roomDisccountPercent}</td>
		<td>${roomVO.roomDiscountHr}</td>
		<td>${roomVO.roomOnePrice}</td>
	
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/room/room.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="roomId" value="${roomVO.roomId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/room/room.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="roomId" value="${roomVO.roomId}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
<!-- 		<div > -->
<!-- 			<talbe> -->
<!-- 				<tr> -->
<!-- 					<th >娛樂</th> -->
<!-- 					<th>餐飲</th> -->
<!-- 					<th>舒適睡眠</th> -->
<!-- 					<th>房間設施</th> -->
<!-- 					<th>貼心服務</th> -->
<!-- 					<th>幾人房</th> -->
<!-- 					<th>單人床數</th> -->
<!-- 					<th>雙人床數</th>			 -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<%-- 					<td>${roomVO.roomFun}</td> --%>
<%-- 					<td>${roomVO.roomMeal}</td> --%>
<%-- 					<td>${roomVO.roomSleep}</td> --%>
<%-- 					<td>${roomVO.roomFacility}</td> --%>
<%-- 					<td>${roomVO.roomSweetFacility}</td> --%>
<%-- 					<td>${roomVO.roomCapacity}</td> --%>
<%-- 					<td>${roomVO.roomOneBed}</td> --%>
<%-- 					<td>${roomVO.roomTwoBed}</td>			 --%>
<!-- 				</tr> -->
<!-- 			</talbe> -->
<!-- 		</div> -->
		
		
			
	</c:forEach>
</table>
<%@ include file="page2.file" %>

<%if (pageNumber>0){%>
<b><font color= red>第<%=whichPage%>/<%=pageNumber%>頁</font></b>
<%}%>
<b>●符 合 查 詢 條 件 如 下 所 示: 共<font color=red><%=rowNumber%></font>筆</b>



<!-- <script src="https://code.jquery.com/jquery.js"></script> -->
<!-- <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->



</body>
</html>
