<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hotelrep.model.*"%>
<%@ page import="com.memrep.model.*"%>

<%-- 用EL練習寫 --%>

<%
    HotelRepService hotelRepSvc = new HotelRepService();
    List<HotelRepVO> hotelRepList = hotelRepSvc.getAll();
    pageContext.setAttribute("hotelRepList",hotelRepList);
%>

<%
    MemRepService memRepSvc = new MemRepService();
    List<MemRepVO> memRepList = memRepSvc.getAll();
    pageContext.setAttribute("memRepList",memRepList);
%>

<!DOCTYPE html>
<html lang="">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<!-- 請輸入標題 -->
	<title>所有檢舉單查詢 - listAllHotelRep.jsp</title>
	
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
if(!authorityList.contains("104")){
	response.sendRedirect(request.getContextPath()+"/backend/emp_index.jsp");
}
%>	

			<!-- 右邊的主要區塊 -->
			<div class="col-xs-12 col-sm-10 bb"
				style="background-color: #FFFAF0;">

				<!-- 麵包屑(當前路徑) -->
				<ol class="breadcrumb">
					<li>檢舉</li>
					<li class="active">所有檢舉單查詢</li>
				</ol>

				<!-- 主要的table -->
				<h2 align="left">廠商檢舉單查詢</h2>
				<table class="table table-hover h-table" border="1">
					<!-- table標題 -->
					<thead>
						<tr style="background-color: #B0C4DE;">
							<th class="text-center">廠商檢舉單編號</th>
							<th class="text-center">(原告)廠商名稱</th>
							<th class="text-center">(被告)旅客姓名</th>
							<th class="text-center">訂單編號</th>
							<th class="text-center">處理的員工姓名</th>
							<th class="text-center">檢舉內容</th>
							<th class="text-center">處理狀態</th>
							<th class="text-center">檢舉時間</th>
							<th class="text-center">處理時間</th>
						</tr>
					</thead>

					<!-- table內容 -->
					<tbody>

						<c:forEach var="hotelRepVO" items="${hotelRepList}">
							<tr align='center' valign='middle'>
								<td>${hotelRepVO.hotelRepId}</td>
								<td>${hotelRepVO.hotelRepHotelVO.hotelName}</td>
								<td>${hotelRepVO.hotelRepMemVO.memName}</td>
								<td>${hotelRepVO.hotelRepOrdVO.ordId}</td>
								<td><c:out value="${hotelRepVO.hotelRepEmpVO.empName}" default="尚無員工處理"/></td>
								
								<td><c:out value="${hotelRepVO.hotelRepContent}" default="無檢舉內容"/></td>
								
								<td>${hotelRepStatusTrans.get(hotelRepVO.hotelRepStatus)}</td>
					
								
								<td>${hotelRepVO.hotelRepDate}</td>
								
								<td><c:out value="${hotelRepVO.hotelRepReviewDate}" default="尚未處理"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
	
				<!-- 主要的table -->
				<h2 align="left">旅客檢舉單查詢</h2>
				<table class="table table-hover h-table" border="1">
					<!-- table標題 -->
					<thead>
						<tr style="background-color: #B0C4DE;">
							<th class="text-center">旅客檢舉單編號</th>
							<th class="text-center">(原告)旅客姓名</th>
							<th class="text-center">(被告)廠商名稱</th>
							<th class="text-center">訂單編號</th>
							<th class="text-center">處理的員工姓名</th>
							<th class="text-center">檢舉內容</th>
							<th class="text-center">處理狀態</th>
							<th class="text-center">檢舉時間</th>
							<th class="text-center">處理時間</th>
						</tr>
					</thead>

					<!-- table內容 -->
					<tbody>

						<c:forEach var="memRepVO" items="${memRepList}">
							<tr align='center' valign='middle'>
								<td>${memRepVO.memRepId}</td>
								<td>${memRepVO.memRepMemVO.memName}</td>
								<td>${memRepVO.memRepHotelVO.hotelName}</td>
								<td>${memRepVO.memRepOrdVO.ordId}</td>
								<td><c:out value="${memRepVO.memRepEmpVO.empName}" default="尚無員工處理"/></td>
								
								<td><c:out value="${memRepVO.memRepContent}" default="無檢舉內容"/></td>
								
								<td>${hotelRepStatusTrans.get(memRepVO.memRepStatus)}</td>
					
								
								<td>${memRepVO.memRepDate}</td>
								
								<td><c:out value="${memRepVO.memRepReviewDate}" default="尚未處理"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>				
				
				
			</div>
		</div>
	</div>
</body>
</html>

<script>
	$(document).ready(function(){
		$(".container-fluid .row #fff").addClass("in");
	});
</script>