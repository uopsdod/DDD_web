<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hotel.model.*"%>
<%@ page import="com.emp.model.*"%>

<%
	HotelService dao = new HotelService();
	List<HotelVO> list = dao.getAll_TO_VIEW();
	pageContext.setAttribute("list", list);
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/hotel/css/listAllHotel.css">
<script src="<%=request.getContextPath()%>/backend/hotel/js/listAllHotel.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>管理後端</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/emp/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/emp/css/0_main.css">
<script src="<%=request.getContextPath()%>/backend/emp/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/backend/emp/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/backend/emp/js/0_new.js "></script>
<style type="text/css">

</style>
</head>

<body>
	<%@ include file="/backend/backendBody.jsp"%>
	<!-- 如果權限沒有人事轉到首頁怕他偷吃步-->

	<%
	if(!authorityList.contains("101")){
		response.sendRedirect(request.getContextPath()+"/backend/emp_index.jsp");
	}
	%>
		
			<div class="col-xs-12 col-sm-10 tablediv text-center" > 
			<h1>旅館資料列表</h1>
			<hr  size="10" width="95%"  align="center" style="border-top: 3px solid #000000">
				<table cellspacing="0" cellpadding="5"
				width='800' class="table table-hover text-center" id="emptableth">	
				<tr>
					<th class="text-center">旅館登記証</th>
					<th class="text-center">廠商會員編號</th>
					<th class="text-center">廠商種類名稱</th>
					<th class="text-center">廠商名稱</th>
					<th class="text-center">統一編號</th>
					
					<th class="text-center">縣市</th>
					<th class="text-center">鄉鎮區</th>
					<th class="text-center">路名牌號</th>
					<th class="text-center">負責人姓名</th>
					<th class="text-center">電話</th>
					<th class="text-center">查看詳情</th>
					
				</tr>
				<%@ include file="page1.file"%>
				<c:forEach var="HotelVO" items="${list}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>">			
					<tr align='center' valign='middle'
						${(HotelVO.hotelId==param.hotelId) ? 'bgcolor=lightblue':''}
						>
						<!--將修改的那一筆加入對比色而已-->
						<td><img src='data:image/jpeg;base64,${HotelVO.bs64}'
							width="120" height="80" /></td>
					
						<td>${HotelVO.hotelId}</td>
						<td>${HotelVO.hotelType}</td>
						<td>${HotelVO.hotelName}</td>
						<td>${HotelVO.hotelTaxId}</td>
						<td>${HotelVO.hotelCity}</td>
						<td>${HotelVO.hotelCounty}</td>
						<td>${HotelVO.hotelRoad}</td>
						<td>${HotelVO.hotelOwner}</td>
						<td>${HotelVO.hotelPhone}</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/hotel/hotel.do">
								<input type="submit" value="查看" id="LoginButtonx"> <input
									type="hidden" name="hotelId" value="${HotelVO.hotelId}"> <input
									type="hidden" name="requestURL"
									value="<%=request.getServletPath()%>">
<!-- 								送出本網頁的路徑給Controller -->
								<input type="hidden" name="whichPage" value="<%=whichPage%>">
<!-- 								送出當前是第幾頁給Controller -->
								<input type="hidden" name="action" value="getOne">
							</FORM>
						</td>
<!-- 						<td> -->
<!-- 							<FORM METHOD="post" -->
<%-- 								ACTION="<%=request.getContextPath()%>/auth/auth.do"> --%>
<!-- 								<input type="submit" value="查詢權限" id="LoginButton"> <input -->
<%-- 									type="hidden" name="empId" value="${EmpVO.empId}"> <input --%>
<!-- 									type="hidden" name="requestURL" -->
<%-- 									value="<%=request.getServletPath()%>"> --%>
<!-- 								送出本網頁的路徑給Controller -->
<%-- 								<input type="hidden" name="whichPage" value="<%=whichPage%>"> --%>
<!-- 								送出當前是第幾頁給Controller -->
<!-- 								<input type="hidden" name="action" value="getOne_For_Auth"> -->
<!-- 							</FORM> -->
<!-- 						</td> -->
					</tr>
				</c:forEach>
			</table>
			</div>
			<%@ include file="page2.file"%>
</body>
</html>

<script>
	$(document).ready(function(){
		$(".container-fluid .row #ccc").addClass("in");
	});
</script>