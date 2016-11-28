<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adplan.model.*"%>
<%
	/* 在網頁使用java語法 */
	AdPlanService adPlanSvc = new AdPlanService(); /* 建一個Service物件 */
	List<AdPlanVO> list = adPlanSvc.getAll();/* 用Service物件取得資料庫資料 */
	pageContext.setAttribute("list", list); /* 把資料放在當前頁面 */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
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

	<table border='1' bordercolor='#CCCCFF' width='800'>
		<tr>
			<th>Banner方案編號</th>
			<th>Banner方案名稱</th>
			<th>刊登開始日期</th>
			<th>刊登結束日期</th>
			<th>方案費用</th>
			<th>剩餘可購買數量</th>
		</tr>
		<%@ include file="page1.file"%>

		<c:forEach var="adPlanVO" items="${list}">
			<!-- 秀出全部資料 -->
			<tr>
				<td>${adPlanVO.adPlanId}</td><!-- EL語法 p148 -->
				<td>${adPlanVO.adPlanName}</td>
				<td>${adPlanVO.adPlanStartDate}</td>
				<td>${adPlanVO.adPlanEndDate}</td>
				<td>${adPlanVO.adPlanPrice}</td>				
				<td>${adPlanVO.adPlanRemainNo}</td>
								
				<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/adPlan/adPlan.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="adPlanId" value="${adPlanVO.adPlanId}"><!-- 隱藏欄位 幫想秀到畫面上 可是值也要送到servlet才知道要改的值在哪 -->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			
							
				<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/adPlan/adPlan.do">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="adPlanId" value="${adPlanVO.adPlanId}"><!-- 隱藏欄位 幫想秀到畫面上 可是值也要送到servlet才知道要改的值在哪 -->
			     <input type="hidden" name="action"	value="delete"></FORM>
			</td>
		</c:forEach>
		</tr>
	</table>
	<%@ include file="page2.file"%>
	
	<%--  <center><button type="button" class="btn btn-success" href='addAdPlan.jsp'><span class="glyphicon glyphicon-plus">新增</span></button></center> --%>
</body>
</html>