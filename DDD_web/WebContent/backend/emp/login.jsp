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
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/emp/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/emp/css/0_main.css">
<script src="<%=request.getContextPath()%>/backend/emp/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/backend/emp/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/backend/emp/js/0_new.js "></script>
<style type="text/css">
body {
	font-family: Tahoma, Verdana, 微軟正黑體;
	font-size: 13px;
}

#LoginButton {
	opacity: 0.7;
	z-index: 1;
	background: #0283df;
	color: #ffffff;
	position: relative;
	font-weight: bold;
	font-family: Tahoma, Verdana, 微軟正黑體;
	border: 0px;
	border-radius: 10px;
	padding: 4px;
	font-size: 12px;
}

.vcenter {
	display: inline-block;
	vertical-align: middle;
	float: none;
}
</style>
</head>
<body>
	
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
		<!-- 手機隱藏選單區結束 --> </nav>
	</div>
	<!-- 		bar 開始 -->
	
		<!-- 				bar結束 -->
	<%-- 錯誤表列 --%>
	<div class="col-xs-12 col-sm-12 tablediv text-center" > 
		<c:if test="${not empty errorMsgs}">
			<font color='red'>請修正以下錯誤:
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li>${message}</li>
					</c:forEach>
				</ul>
			</font>
		</c:if>
	<H1 align="center">DDD後端平台登入</H1>
	<form action="<%=request.getContextPath()%>/emp/emp.do" method="post">
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
							
							<input type=submit value="  ok   ">
						
					</td>
				</tr>
			</table>
	</form>
	</div>
</body>
</html>