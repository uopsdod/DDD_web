<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 讓java資料更好呈現在頁面 -->
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

	<ul>
		<li><a href='listAllAdPlan.jsp'>List</a>all Ad</li>
		<br>
		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/adPlan.do"><!-- 從jsp連過去就要加資料夾名稱 -->
				<b>輸入廣告編號 :</b> <input type="text" name="adPlanId"> <input
					type="submit" value="送出"><input type="hidden"
					name="action" value="getOne_For_Display">
			</FORM>
		</li>
		
		<h3>廣告方案管理</h3>
	<ul>
		<li><a href='addAdPlan.jsp'>Add</a> a new AdPlan.</li>
	</ul>


</body>
</html>