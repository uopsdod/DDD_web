<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>管理後端</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/emp/css/login.css">
<script src="<%=request.getContextPath()%>/backend/emp/js/login.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/emp/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/emp/css/0_main.css">

<script src="<%=request.getContextPath()%>/backend/emp/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/backend/emp/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/backend/emp/js/0_new.js "></script>
<style type="text/css">

</style>
</head>
<body style="background-image: url('<%=request.getContextPath()%>/frontend_mem/images/back7.jpg');">
	
	<div id="top-bar">
		<nav class="navbar navbar-inverse" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-ex1-collapse">
				<span class="sr-only">選單切換</span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"><span
				class="glyphicon glyphicon-heart-empty"></span> DDD後端管理介面</a>
		</div>

		<!-- 手機隱藏選單區 -->
		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<!-- 右選單 -->
			<ul class="nav navbar-nav navbar-right">
				
				<li><a href="#"><span class="glyphicon glyphicon-log-out"></span>
						登入</a></li>
			</ul>
		</div>
	</div>
	
	<%-- 錯誤表列 --%>
	<div class="col-xs-12 col-sm-12 tablediv text-center"  id="loginbg"> 		
	<div class="bg">
		<img src="<%=request.getContextPath()%>/frontend_mem/GIF/loadgif.gif" width="500px" height="400px">
	</div>
	<img src="<%=request.getContextPath()%>/frontend_mem/images/4.png" id="LogoImg" class="animsition">	
	<form action="<%=request.getContextPath()%>/emp/emp.do" method="post">
		<BR><BR><BR>
		<c:if test="${not empty errorMsgs}">
			<font color='red' id="fonterror">			
					<c:forEach var="message" items="${errorMsgs}">
						${message}
					</c:forEach>			
			</font>
		</c:if>
			<H1 id="dddtitle">DDD 後端平台登入<img src="<%=request.getContextPath()%>/backend/emp/img/loginbacka.png" width="45px" height="40px" id="keyimg"></H1>
			<input type="hidden" name="action" value="login">
			
			<table  align="center">
				<tr>
<!-- 					<td colspan=2> -->
<!-- 						<p align=center> -->
<!-- 							<b>登入</b>:<br>  -->
<!-- 					</td> -->
				</tr>
				<tr>
<!-- 					<td> -->
<!-- 						<p align=right> -->
<!-- 							<b id ="acount">帳號:</b> -->
<!-- 					</td> -->
					<td>
						<p>
							<input type=text name="account" value="" size=15 id="UserName" placeholder="UserName">
					</td>
				</tr>

				<tr>
<!-- 					<td> -->
<!-- 						<p align=right> -->
<!-- 							<b>密碼:</b> -->
<!-- 					</td> -->
					<td>
						<p>
							<input type=password name="password" value="" size=15 id="UserName" placeholder="Password">
					</td>
				</tr>
				<tr>
					<td colspan=2 align=center>							
							<button class="LoginButton">Login</button>					
					</td>
				</tr>
			</table>
	</form>
	</div>
</body>
</html>