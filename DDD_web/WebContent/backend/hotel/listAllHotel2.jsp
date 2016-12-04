<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.hotel.model.*, com.ord.model.*, java.text.SimpleDateFormat"%>


<%
	HotelService hotelSvc = new HotelService();
	List<HotelVO> list = hotelSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<!-- 請輸入標題 -->
	<title>所有廠商會員</title>
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/css/bootstrap.css">
	<!-- 自訂CSS -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/css/0_main.css">
	
	<script src="<%=request.getContextPath()%>/backend/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/backend/js/bootstrap.js"></script>
	<!-- 自訂JavaScript -->
	<script src=""></script>
</head>

<body>

<%@ include file="/backend/backendBody.jsp"%>

<%
if(!authorityList.contains("102")){
	response.sendRedirect(request.getContextPath()+"/backend/emp_index.jsp");
}
%>	

			<!-- 右邊的主要區塊 -->
			<div class="col-xs-12 col-sm-10 bb"
				style="background-color: #FFFAF0;">

				<!-- 麵包屑(當前路徑) -->
				<ol class="breadcrumb">
					<li>訂單</li>
					<li class="active">廠商訂單查詢</li>
				</ol>

				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
				請修正以下錯誤:
				<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li>${message}</li>
						</c:forEach>
					</ul>
				</c:if>

				<!-- 主要的table -->
				<table class="table table-hover h-table" border="1">
					<!-- table標題 -->
					<thead>
						<tr style="background-color: #B0C4DE;">
							<th class="text-center">廠商會員編號</th>
							<th class="text-center">廠商名稱</th>
							<th class="text-center">負責人</th>
							<th class="text-center">帳號</th>
							<th class="text-center">住址</th>
							<th class="text-center">查詢廠商訂單</th>
						</tr>
					</thead>

					<!-- table內容 -->
					<tbody>
						<%@ include file="/backend/ord/pages/page1.file"%>
						<c:forEach var="hotelVO" items="${list}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">
							<tr align="center" valign='middle' ${(hotelVO.hotelId==param.hotelId)? 'style="background-color:#CCCCFF"' :''}>
								<!--將修改的那頁換底色-->
								<td>${hotelVO.hotelId}</td>
								<td>${hotelVO.hotelName}</td>
								<td>${hotelVO.hotelOwner}</td>
								<td>${hotelVO.hotelAccount}</td>
								<td>${hotelVO.hotelCity}${hotelVO.hotelCounty}${hotelVO.hotelRoad}</td>
								<td>				
									<form method="post" action="<%=request.getContextPath()%>/hotel/hotel.do">										
										<button type="submit" class="btn btn-primary">
											<span class="glyphicon glyphicon-search">送出查詢</span>
										</button>
										<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
										<input type="hidden" name="whichPage" value="<%=whichPage%>">
										<input type="hidden" name="hotelId" value="${hotelVO.hotelId}">
										<input type="hidden" name="action" value="listOrdsByHotelIdB">
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<div class="col-xs-12 col-sm-12">
					<%@ include file="/backend/ord/pages/page2.file"%>
				</div>

				<div id="simpleTable" class="col-xs-12 col-sm-12">			
					<% if (request.getAttribute("listOrdsByHotelId")!=null) { %>
							<div class="col-xs-1 col-sm-1"></div>
						
							<div class="col-xs-10 col-sm-10">	
								<jsp:include page="listOrdsByHotelId.jsp"/>
							</div>
							<div class="col-xs-1 col-sm-1"></div>	
					<% } %>
				<div>

			</div>
		</div>
	</div>
</body>
</html>

<script>
	$(document).ready(function(){
		$(".container-fluid .row #aaa").addClass("in");
	});
</script>