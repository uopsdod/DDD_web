<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../head.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ad.model.*"%>
<%@ page import="com.hotel.model.*"%>

<%
	/* 在網頁使用java語法 */
	AdService adSvc = new AdService(); /* 建一個Service物件 */
	String hotelId=(String)session.getAttribute("hotelId");
	List<AdVO> list = adSvc.getAll(hotelId);/* 用Service物件取得資料庫資料 */
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

 .tablediv{
 margin-top:10%;
 }
 
 #h_text{
  font-weight:Italic;
 }

</style>
</head>
<body>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>
<div class="col-xs-12 col-sm-10 bb" style="background-color:#FFFAF0;postion:relative;top:220px" >
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-12">




				<table class="table table-hover table-bordered  table-striped">

					<thead>
						<tr style="background-color: #B0C4DE;">
							<th>Banner編號</th>
							<th>Banner方案編號</th>
							<th>廠商會員編號</th>
							<!-- <th>處理狀態</th> -->
							<!-- <th>繳費日期</th>  -->
							<th>圖片</th>
							<th>圖片內容說明</th>
							<!-- <th>點擊數</th> -->
							<th>繳費管理</th>


						</tr>
					</thead>

					<tbody>


						<c:forEach var="adVO" items="${list}">								
						 		
						 	
							<!-- 秀出全部資料 -->
								<tr>
									<td>${adVO.adId}</td>
									<c:set var="adid" scope="session" value="${adVO.adId}"/>
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

									<!-- <td>未繳費 --><%--${adVO.adStatus} 
								<td><%=("0".equals(((AdVO)(pageContext.getAttribute("adVO"))).getAdStatus())
						|| "1".equals(((AdVO) (pageContext.getAttribute("adVO"))).getAdStatus())
						|| "2".equals(((AdVO) (pageContext.getAttribute("adVO"))).getAdStatus())) ? "未繳費"
								: ((AdVO) (pageContext.getAttribute("adVO"))).getAdPayDate()%></td> --%>

										<%-- 				<td>${adVO.adPayDate}</td> --%> <%-- <td>${adVO.adPic}</td> --%>
								<!-- 	<td> -->
										<%-- <img
									src="<%=request.getContextPath()%>/backend/Ad/AdPic?adId=${adVO.adId}"> --%>
										<%-- <a
									id="thumb1" href="<%=request.getContextPath()%>/Ad/AdPic?adId=${adVO.adId}" class="highslide"
									onclick="return hs.expand(this, { captionId: 'caption1', align: 'center' } )"> --%>
									<td>	<img
										src="<%=request.getContextPath()%>/Ad/AdPic?adId=${adVO.adId}"
										 width="100px" height="100px" /> </a>
									</td>
									<td>${adVO.adPicContent}</td>
									<%-- <td>${adVO.adHit}</td> --%>
									 <c:if test="${adVO.adStatus=='0' }">
									 <td>圖片審核中</td>
									 </c:if>
                                     <c:if test="${adVO.adStatus=='2' }">
									<td class="hwidad">
										<FORM METHOD="post" id="hmodad" ACTION="<%=request.getContextPath()%>/frontend_hotel/ad/pay.jsp">
											<button type="submit" class="btn btn-info">
												<span class="glyphicon glyphicon-usd">繳費</span>
											</button>
											<input type="hidden" name="adid" value="${adVO.adId}">
											<input type="hidden" name="action" value="getOne_For_Update">
										</FORM>
										




										<%-- <FORM METHOD="post" id="hdelad"
											ACTION="<%=request.getContextPath()%>/Ad/ad.do">
											<button type="submit" class="btn btn-danger ">
												<span class="glyphicon glyphicon-remove">刪除</span>
											</button>
											<input type="hidden" name="adid" value="${adVO.adId}">
											<!-- 隱藏欄位 幫想秀到畫面上 可是值也要送到servlet才知道要改的值在哪 -->
											<input type="hidden" name="action" value="delete">
										</FORM> --%>
									</td>
                                    </c:if>
                                    <c:if test="${adVO.adStatus=='3' }">
                                      <td id="h_text">繳費成功</td>
                                      </c:if>
								</tr>
														
						</c:forEach>
					</tbody>
				</table>
</body>
</html>