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
<html>
<head>
<meta http-equiv="Content-Type" content="text/html">
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
			<th>Banner編號</th>
			<th>Banner方案編號</th>
			<th>廠商會員編號</th>
			<th>處理狀態</th>
			<th>繳費日期</th>
			<th>圖片</th>
			<th>圖片內容說明</th>
			<th>點擊數</th>
		</tr>
		<%@ include file="page1.file"%>

		<c:forEach var="adVO" items="${list}">
			<!-- 秀出全部資料 -->
			<tr>
				<td>${adVO.adId}</td><!-- EL語法 p148 -->
				<td>${adVO.adAdPlanId}</td>
<%-- 				<td>${adVO.adHotelId}</td> --%>

<jsp:useBean id="hotelSvc" scope="page" class="com.hotel.model.HotelService" />

			<td>
				<c:forEach var="hotelVO" items="${hotelSvc.all}">
                    <c:if test="${adVO.adHotelId==hotelVO.hotelId}">
	                    ${hotelVO.hotelId} - ${hotelVO.hotelName} - ${hotelVO.hotelAccount}】
                    </c:if>
                </c:forEach>
			</td>

				<td>${adVO.adStatus}</td>
				
				<td><%=("0".equals( ((AdVO)(pageContext.getAttribute("adVO"))).getAdStatus() )
						||"1".equals( ((AdVO)(pageContext.getAttribute("adVO"))).getAdStatus() )
						||"2".equals( ((AdVO)(pageContext.getAttribute("adVO"))).getAdStatus() ))?
						"未繳費" : ((AdVO)(pageContext.getAttribute("adVO"))).getAdPayDate()  %></td>
				
<%-- 				<td>${adVO.adPayDate}</td> --%>
				<%-- <td>${adVO.adPic}</td> --%>
				<td><img src="<%=request.getContextPath()%>/backend/Ad/AdPic?adId=${adVO.adId}"></td>
				<td>${adVO.adPicContent}</td>
				<td>${adVO.adHit}</td>
				
				<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Ad/ad.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="adid" value="${adVO.adId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			
						
				<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Ad/ad.do">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="adid" value="${adVO.adId}"><!-- 隱藏欄位 幫想秀到畫面上 可是值也要送到servlet才知道要改的值在哪 -->
			     <input type="hidden" name="action"	value="delete"></FORM>
			</td>
		</c:forEach>
		</tr>
	</table>
	<%@ include file="page2.file"%>
</body>
</html>