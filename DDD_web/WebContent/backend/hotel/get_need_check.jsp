<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hotel.model.*"%>
<%@ page import="com.emp.model.*"%>

<%
	HotelService dao = new HotelService();
	List<HotelVO> list = dao.getAll_NEED_CHECK();
	pageContext.setAttribute("list", list);
%>




<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>管理後端</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/emp/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/emp/css/0_main.css">
<script src="<%=request.getContextPath()%>/backend/emp/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/backend/emp/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/backend/emp/js/0_new.js "></script>
<style type="text/css">
body {
	font-family: Tahoma, Verdana, 微軟正黑體;
	font-size: 13px;
}

#LoginButton {
	opacity: 0.7;
	z-index: 1;
	background: #0283df;
	color: #ffffff;
	position: relative;
	font-weight: bold;
	font-family: Tahoma, Verdana, 微軟正黑體;
	border: 0px;
	border-radius: 10px;
	padding: 4px;
	font-size: 12px;
}

.vcenter {
	display: inline-block;
	vertical-align: middle;
	float: none;
}
#empName{
	color:white;
}
#LoginButton{
	opacity: 0.7;
	z-index: 1;
	background: #0283df;
	color: #ffffff;
	position: relative;
	font-weight: bold;
	font-family: Tahoma, Verdana, 微軟正黑體;
	border: 0px;
	border-radius: 10px;
	padding: 3px;
	font-size: 15px;
}
#LoginButton1{
	opacity: 0.7;
	z-index: 1;
	background: rgb(212, 74, 151);
	color: #ffffff;
	position: relative;
	font-weight: bold;
	font-family: Tahoma, Verdana, 微軟正黑體;
	border: 0px;
	border-radius: 10px;
	padding: 3px;
	font-size: 15px;
}
.tablediv{
	
	font-family: Tahoma, Verdana, 微軟正黑體;
	font-size: 19px;
	font-weight:bold;
	font-weight: bold;
	
}
#emptableth th{
	font-family: Tahoma, Verdana, 微軟正黑體;
	font-size: 20px;
	
}
#emptableth td{
		vertical-align:middle;
	}
#nosecess{
	margin-top:10px;
}
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
			<h1>廠商審核作業</h1>
			<hr  size="10" width="95%"  align="center" style="border-top: 3px solid #000000">
				<table  cellspacing="0" cellpadding="1"
				width='800' class="table table-hover" id="emptableth">	
				<tr>
					<th class="text-center">登記證</th>
					<th class="text-center">封面照片</th>
					
					<th class="text-center">編號</th>
					<th class="text-center">種類</th>
					<th class="text-center">廠商名稱</th>
					<th class="text-center">統編</th>
					<th class="text-center">信箱</th>
					<th class="text-center">縣市</th>
<!-- 					<th>鄉鎮區</th> -->
<!-- 					<th>路名牌號</th> -->
					<th class="text-center">負責人</th>
					<th class="text-center">電話</th>
					<th class="text-center">審核結果</th>
					
					
				</tr>
				<%@ include file="page1.file"%>
				<c:forEach var="HotelVO" items="${list}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>">			
					<tr align='center' valign='middle'
						${(HotelVO.hotelId==param.hotelId) ? 'bgcolor=lightblue':''}
						>
						<!--將修改的那一筆加入對比色而已-->
						<td><img src='data:image/jpeg;base64,${HotelVO.bs64}'
							width="130" height="90" /></td>
						<td><img src='data:image/jpeg;base64,${HotelVO.bs64_2}'
							width="130" height="90" /></td>
						<td>${HotelVO.hotelId}</td>
						<td>${HotelVO.hotelType}</td>
						<td>${HotelVO.hotelName}</td>
						<td>${HotelVO.hotelTaxId}</td>
						<td>${HotelVO.hotelAccount}</td>
						
						<td>${HotelVO.hotelCity}</td>
<%-- 						<td>${HotelVO.hotelCounty}</td> --%>
<%-- 						<td>${HotelVO.hotelRoad}</td> --%>
						<td>${HotelVO.hotelOwner}</td>
						<td>${HotelVO.hotelPhone}</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/hotel/hotel.do">
								<input type="submit" value="" height='32px' style="background-image: url('<%=request.getContextPath()%>/backend/hotel/images/success.png');width:32px;height:32px;background-color:Transparent;border:0px;float:left;margin-left:25%;"> 
								<input type="hidden" name="hotelId" value="${HotelVO.hotelId}"> 
								<input type="hidden" name="hotelStatus" value="${HotelVO.hotelStatus}">
								<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
								<input type="hidden" name="whichPage" value="<%=whichPage%>">
								<input type="hidden" name="action" value="secess">
							</FORM>
					
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/hotel/hotel.do">
								<input type="submit" value="" height='32px' style="background-image: url('<%=request.getContextPath()%>/backend/hotel/images/error.png');width:32px;height:32px;background-color:Transparent;border:0px;"> 
								<input type="hidden" name="hotelId" value="${HotelVO.hotelId}"> 
								<input type="hidden" name="hotelStatus" value="${HotelVO.hotelStatus}">
								<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
								<input type="hidden" name="whichPage" value="<%=whichPage%>">
								<input type="hidden" name="action" value="nosecess"><br>
								<input type="text" name="other" id="nosecess">
							</FORM>
						</td>

					</tr>
				</c:forEach>
			</table>
				<div align="center"><a href="<%=request.getContextPath()%>/backend/hotel/listAllHotel.jsp">回廠商首頁</a></div>
			
			</div>
			<%@ include file="page2.file"%>
</body>
</html>

<script>
	$(document).ready(function(){
		$(".container-fluid .row #ccc").addClass("in");
	});
</script>