<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 請輸入標題 -->
		<title></title>		
		
		<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/css/bootstrap.css">
		<!-- 自訂CSS -->
		<link rel="stylesheet" href="">		
		
		<script src="<%=request.getContextPath()%>/backend/js/jquery.js"></script>
		<script src="<%=request.getContextPath()%>/backend/js/bootstrap.js"></script>
		<!-- 自訂JavaScript --> 	
		<script src=""></script>
	</head>
	<body>

		<h3>訂單驗證:</h3>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			請修正以下錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">	
					<li>${message}</li>	
				</c:forEach>
			</ul>
		</c:if>


		<form method="post" action="<%=request.getContextPath()%>/ord/ord.do">
			輸入訂單編號:
			<input type="text" name="ordId"><br>
			輸入簡訊驗證碼:
			<input type="text" name="ordMsgNo">

			<input type="submit" name="送出">
			<input type="hidden" name="action" value="simpleCheckIn">
		</form>

	</body>
</html>