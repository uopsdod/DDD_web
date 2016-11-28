<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 讓java資料更好呈現在頁面 -->
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

	<ul>
		<li><a href='<%=request.getContextPath()%>/backend/Ad/listAllAd.jsp'>List</a>all Ad</li>
		<br>
		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Ad/ad.do">
				<b>輸入廣告編號 :</b> <input type="text" name="Adid"> <input
					type="submit" value="送出"> <input type="hidden"
					name="action" value="getOne_For_Display">
			</FORM>
		</li>

		<jsp:useBean id="adSvc" scope="page" class="com.ad.model.AdService" /><!-- 拿一個service物件出來 放在這個頁面 id叫adSvc 把所有的選單拿出來 -->
		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Ad/ad.do">
				<b>選擇廣告編號</b> <select size="1" name="Adid">
					<c:forEach var="adVO" items="${adSvc.all}">
						<!-- 從service呼叫get All方法 就會回傳給他一個集合 集合內的物件會依序放在var -->
						<option value="${adVO.adId}">${adVO.adId}
							<!-- value是等等要送出去的值 外面那個是在頁面上看到的值 -->
					</c:forEach>
				</select> <input type="submit" value="送出"> <input type="hidden"
					name="action" value="getOne_For_Display">
			</FORM>
		</li>
		<jsp:useBean id="adPlanSvc" scope="page"
			class="com.adplan.model.AdPlanService" />
		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/adPlan/adPlan.do">
				<b>選擇廣告方案編號:</b> <select size="1" name="adPlanId">
					<c:forEach var="adPlanVO" items="${adPlanSvc.all}">
						<!-- 從service呼叫get All方法 就會回傳給他一個集合 集合內的物件會依序放在var -->
						<option value="${adPlanVO.adPlanId}">${adPlanVO.adPlanName}
							<!-- value是等等要送出去的值 外面那個是在頁面上看到的值 -->
					</c:forEach>
				</select> <input type="submit" value="送出"> <input type="hidden"
					name="action" value="getOne_For_Display">
			</FORM>
	</ul>
	<h3>廣告管理</h3>
	<ul>
		<li><a href='addAd.jsp'>Add</a> a new Ad.</li>
	</ul>
</body>
</html>