<%@page import="util.adStatusTransform"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ad.model.*"%>
<%@ page import="com.hotel.model.*"%>

<%
	/* 在網頁使用java語法 */
	AdService adSvc = new AdService(); /* 建一個Service物件 */
	List<AdVO> list = adSvc.getAll();/* 用Service物件取得資料庫資料 */
	pageContext.setAttribute("list", list); /* 把資料放在當前頁面 */
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Title Page</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->

<!-- 請輸入標題 -->
<title>管理後端</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/backend/css/bootstrap.css">
<!-- 自訂CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/backend/css/0_main.css">

<script src="<%=request.getContextPath()%>/backend/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/backend/js/bootstrap.js"></script>
<!-- 自訂JavaScript -->
<script src="<%=request.getContextPath()%>/backend/css/0_new.js"></script>

<style type="text/css">
.table-hover>tbody>tr:hover {
	background-color: #dcdcdc;
}

#hmodad {
	margin: 5px;
	float: left;
}

#hdelad {
	margin: 5px;
	float: left;
}

.hwidad {
	width: 200px;
}
.h_showImage:hover {
transform: scale(5);
 transition: 1s; /* 一秒後圖片變大 */

}


</style>
</style>
</head>
<body>

<%@ include file="/backend/backendBody.jsp"%>

<%
if(!authorityList.contains("103")){
	response.sendRedirect(request.getContextPath()+"/backend/emp_index.jsp");
}
%>	


			<div class="col-xs-12 col-sm-10 bb" style="background-color: #FFFAF0;">
			
				<!-- 麵包屑(當前路徑) -->
				<ol class="breadcrumb">
					<li >Banner方案管理</li>
					<li class="active">Banner審核作業</li>
				</ol>

	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-12">




				<table class="table table-hover table-bordered  table-striped h-table">

					<thead>
						<tr style="background-color: #B0C4DE;">
							<th>Banner編號</th>
							<th>Banner方案編號</th>
							<th>廠商會員編號</th>
							<th>處理狀態</th>
							<th>繳費日期</th>
							<th>圖片</th>
							<th>圖片內容說明</th>
							<th>點擊數</th>
							<th>管理</th>


						</tr>
					</thead>

					<%@ include file="page1.file"%>
					<tbody>


						<c:forEach var="adVO" items="${list}">
							<!-- 秀出全部資料 -->
							<tr>
								<td>${adVO.adId}</td>
								<!-- EL語法 p148 -->
								<td>${adVO.adAdPlanId}</td>
								<%-- 				<td>${adVO.adHotelId}</td> --%>

								<jsp:useBean id="hotelSvc" scope="page"
									class="com.hotel.model.HotelService" />

								<td><c:forEach var="hotelVO" items="${hotelSvc.all}">
										<c:if test="${adVO.adHotelId==hotelVO.hotelId}">
	                    ${hotelVO.hotelId} - ${hotelVO.hotelName} - ${hotelVO.hotelAccount}】
                    </c:if>
									</c:forEach></td>
                                  <c:set value="${adVO.adStatus}" var="status"></c:set> 
								<td><%=adStatusTransform.status((String)pageContext.getAttribute("status")) %></td>

								<td><%=("0".equals(((AdVO) (pageContext.getAttribute("adVO"))).getAdStatus())
						|| "1".equals(((AdVO) (pageContext.getAttribute("adVO"))).getAdStatus())
						|| "2".equals(((AdVO) (pageContext.getAttribute("adVO"))).getAdStatus())) ? "未繳費"
								: ((AdVO) (pageContext.getAttribute("adVO"))).getAdPayDate()%></td>

								
								<td>
									<%-- <img
									src="<%=request.getContextPath()%>/backend/Ad/AdPic?adId=${adVO.adId}"> --%>
									<%-- <a id="thumb1"
									href="<%=request.getContextPath()%>/Ad/AdPic?adId=${adVO.adId}"
									class="highslide"
									onclick="return hs.expand(this, { captionId: 'caption1', align: 'center' } )"> --%>
										<img
										src="<%=request.getContextPath()%>/Ad/AdPic?adId=${adVO.adId}"
										class="h_showImage" width="100px" height="100px" />
								</a>
								</td>
								<td>${adVO.adPicContent}</td>
								<td>${adVO.adHit}</td>

								<td class="hwidad">
									<FORM METHOD="post" id="hmodad"
										ACTION="<%=request.getContextPath()%>/Ad/ad.do">
										<button type="submit" class="btn btn-primary ">
											<span class="glyphicon glyphicon-pencil">修改</span>
										</button>
										<input type="hidden" name="adid" value="${adVO.adId}">
										<input type="hidden" name="action" value="getOne_For_Update">
									</FORM>




									<FORM METHOD="post" id="hdelad"
										ACTION="<%=request.getContextPath()%>/Ad/ad.do">
										<button type="submit" class="btn btn-danger ">
											<span class="glyphicon glyphicon-remove">刪除</span>
										</button>
										<input type="hidden" name="adid" value="${adVO.adId}">
										<!-- 隱藏欄位 幫想秀到畫面上 可是值也要送到servlet才知道要改的值在哪 -->
										<input type="hidden" name="action" value="delete">
									</FORM>
								</td>
						</c:forEach>
						</tr>
				</table>
				<%@ include file="page2.file"%>
				
				</div>

			</div>
		</div>
	</div>
</body>
</html>

<script>
	$(document).ready(function(){
		$(".container-fluid .row #ddd").addClass("in");
	});
</script>