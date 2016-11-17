<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 	注意之後要註解掉,因為EL直接從session取出hotelId -->

<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/frontend_hotel/hotel/css/loginhotel.css">
		<script src="<%=request.getContextPath()%>/frontend_hotel/hotel/js/loginhotel.js"></script>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>廠商前端</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/frontend_hotel/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/frontend_hotel/css/0_main.css">
		<script src="<%=request.getContextPath()%>/frontend_hotel/js/jquery.js"></script>
		<script src="<%=request.getContextPath()%>/frontend_hotel/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath()%>/frontend_hotel/js/0_new.js "></script>
		
		</head>
<body style="background:#fffaf0">			
	<div id="top-bar" >
			<nav class="navbar navbar-inverse" role="navigation">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
						<span class="sr-only">選單切換</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#"><span class="glyphicon glyphicon-heart-empty" style="font-size:30px">DDD廠商</span> </a>
				</div>
			
				<!-- 手機隱藏選單區 -->
				<div class="collapse navbar-collapse navbar-ex1-collapse">			
					<!-- 右選單 -->
					<ul class="nav navbar-nav navbar-right">
						<li><a href="<%=request.getContextPath()%>/frontend_hotel/hotel/addhotel.jsp"><span class="glyphicon glyphicon-bell"></span> 註冊</a></li>
						<li><a href="<%=request.getContextPath()%>/frontend_hotel/hotel/loginhotel.jsp"><span class="glyphicon glyphicon-log-out"></span> 登入</a></li>
					</ul>
				</div>
				<!-- 手機隱藏選單區結束 -->
			</nav>
		</div> 
		<div class="col-xs-12 col-sm-12 tablediv" align="center"  id="loginbg"> 
		<div class="bg">
			<img src="<%=request.getContextPath()%>/frontend_mem/GIF/loadgif.gif" width="500px" height="400px">
		</div>
		<img src="<%=request.getContextPath()%>/frontend_mem/images/4.png" id="LogoImg" class="animsition">	
				
			<form action="<%=request.getContextPath()%>/hotel/hotel.do" method="post">
					<input type="hidden" name="action" value="login">
					<BR><BR><BR>
					<c:if test="${not empty errorMsgs}">
						<font color='red' id="fonterror">			
								<c:forEach var="message" items="${errorMsgs}">
									${message}
								</c:forEach>			
						</font>
					</c:if>
				<H1 id="dddtitle">立即登錄，管理住宿<img src="<%=request.getContextPath()%>/frontend_hotel/hotel/img/login.png" width="45px" height="40px" id="keyimg"></H1>
				<input type="hidden" name="action" value="login">
							
							<input type=text name="account" value="" size=15 id="UserName" placeholder="UserName">
									
							<input type=password name="password" value="" size=15 id="UserName" placeholder="Password">
									
							<button class="LoginButton">Login</button>					
			
			</form>
		</div>

</body>
</html>