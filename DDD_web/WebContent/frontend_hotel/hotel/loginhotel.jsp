<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 	注意之後要註解掉,因為EL直接從session取出hotelId -->

<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>廠商前端</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/frontend_hotel/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/frontend_hotel/css/0_main.css">
		<script src="<%=request.getContextPath()%>/frontend_hotel/js/jquery.js"></script>
		<script src="<%=request.getContextPath()%>/frontend_hotel/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath()%>/frontend_hotel/js/0_new.js "></script>
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<style type="text/css">
				
			.vcenter {
			    display: inline-block;
			    vertical-align: middle;
			    float: none;
			}
			.tablediv{
				margin-top:5%;
			}
		
		</style>
		</head>
<body style="background:#FFFAF0">		
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
		<div class="col-xs-12 col-sm-12 tablediv" align="center"> 
				<c:if test="${not empty errorMsgs}">
					<font color='red'>請修正以下錯誤:
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li>${message}</li>
							</c:forEach>
						</ul>
					</font>
				</c:if>
			<H1 align="center">DDD廠商平台登入</H1>
			<form action="<%=request.getContextPath()%>/hotel/hotel.do" method="post">
					<input type="hidden" name="action" value="login">
					<table border=1 align="center">
						<tr>
							<td colspan=2>
								<p align=center>
									<b>登入</b>:<br> 
							</td>
						</tr>
		
						<tr>
							<td>
								<p align=right>
									<b>account:</b>
							</td>
							<td>
								<p>
									<input type=text name="account" value="" size=15>
							</td>
						</tr>
		
						<tr>
							<td>
								<p align=right>
									<b>password:</b>
							</td>
							<td>
								<p>
									<input type=password name="password" value="" size=15>
							</td>
						</tr>
		
		
						<tr>
							<td colspan=2 align=center>
									
									<input type=submit value="登入">
								
							</td>
						</tr>
					</table>
			</form>
		</div>

</body>
</html>