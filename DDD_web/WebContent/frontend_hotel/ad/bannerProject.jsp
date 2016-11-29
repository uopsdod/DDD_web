<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adplan.model.*"%>
<%@ include file="../head.jsp"%>
<%
	/* 在網頁使用java語法 */
	AdPlanService adPlanSvc = new AdPlanService(); /* 建一個Service物件 */
	List<AdPlanVO> list = adPlanSvc.getAll();/* 用Service物件取得資料庫資料 */
	pageContext.setAttribute("list", list); /* 把資料放在當前頁面 */
%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html">

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Examples</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link href="" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

</head>
<body>
<div class="col-xs-12 col-sm-10 bb" style="background-color:#FFFAF0;postion:relative;top:220px" >
	<ol class="breadcrumb">
		<li>
			Banner
		</li>
		<li class="active">Banner廣告購買</li>
	</ol>
   <div class="container">
   	<div class="row">
   	
   	
   	
   	
   	<c:forEach var="adPlanVO" items="${list}">
   		<div class="col-xs-12 col-sm-4">
   			<div class="thumbnail">
   		<!--images資料夾放1~12月圖片 抓開始日期的月份 顯示當月圖片 -->
   		   		
   				
<c:set var="month" value="${fn:split(adPlanVO.adPlanStartDate, '-')}"/>
						
 			
   		
   				<img src="images/${month[1]}.jpg" alt="" width="200" height="200">
   				<%-- <p>${month[1]}</p> --%>
   				<div class="caption">
   					<h2>${adPlanVO.adPlanName}</h2>
   					<p><br>開始日期:${adPlanVO.adPlanStartDate}<br>結束日期:${adPlanVO.adPlanEndDate}<br>費用:5000元<br><div class="amount1">剩餘數量:${adPlanVO.adPlanRemainNo}</div></p>
   					
   					<p>
   					    
   						
   						
   						
   						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Ad/ad.do">
							<input type="hidden" name="adPlanId" value="${adPlanVO.adPlanId}">
							<input type="hidden" name="action" value="getOne_For_AdplanId">
							<button type="submit" class="btn btn-info"><span class="glyphicon glyphicon-shopping-cart">加入購物車</span></button>
						</FORM>
   					</p>
   				</div>
   			</div>
   		</div>
   		
   		</c:forEach>
   	</div>
   </div>
   </div>
   </div>
</body>
</html>
