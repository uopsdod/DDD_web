<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hotel.model.*"%>
<%
session.getAttribute("account_hotel");

HotelVO hotelVO =(HotelVO)session.getAttribute("hotelVO");
session.setAttribute("hotelVO", hotelVO);
session.setAttribute("hotelId", hotelVO.getHotelId());
// session.setAttribute("hotelId","10002");
%>


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
	

</style>
</head>

<body style="background:#FFFAF0">
	
<%-- <%session.setAttribute("hotelId","10001");%> --%>
<!-- 	注意之後要註解掉,因為EL直接從session取出hotelId -->

		<div id="top-bar" >
			<nav class="navbar navbar-inverse" role="navigation">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
						<span class="sr-only">選單切換</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="<%=request.getContextPath()%>/frontend_hotel/index.jsp"><span class="glyphicon glyphicon-heart-empty" style="font-size:30px">DDD廠商</span> </a>
				</div>
			
				<!-- 手機隱藏選單區 -->
				<div class="collapse navbar-collapse navbar-ex1-collapse">			
					<!-- 右選單 -->
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#">${hotelVO.hotelName} 您好!</a></li>
						<li><a href="#"><span class="glyphicon glyphicon-bell"></span> 通知</a></li>
						<li><a href="<%=request.getContextPath()%>/hotel/hotel.do"><span class="glyphicon glyphicon-log-out"></span> 登出</a></li>
					</ul>
				</div>
				<!-- 手機隱藏選單區結束 -->
			</nav>
		</div> 
	<div style="postion:relative;">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12 col-sm-2 aa" style="background-color: #242627;margin:0;padding-top:250px;height:150vh;">
					<div class="panel-group" id="accordion2" role="tablist" aria-multiselectable="true">
					  <!-- 區塊1 -->
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="tab1" style="background-color:#B0C4DE;">
					      <h4 class="panel-title">
					        <a role="button" data-toggle="collapse" data-parent="#accordion2" href="#aaa" aria-expanded="false" aria-controls="aaa">
							 <span class="glyphicon glyphicon-list-alt"></span> 訂單
					        </a>
					      </h4>
					    </div>
					    <div id="aaa" class="panel-collapse collapse " role="tabpanel" aria-labelledby="tab1">
					      <div class="panel-body">
					        	<ul style="list-style-type: none">
					        		<li><a href="<%=request.getContextPath()%>/frontend_hotel/ord/listAllOrdByHotelId.jsp">所有訂單查詢</a></li>
					        		<li><a href="<%=request.getContextPath()%>/frontend_hotel/ord/simpleCheckIn_Input.jsp">旅客入住驗證</a></li>
					        	</ul>
					      </div>
					    </div>
					  </div>
					  <!-- 區塊4 -->
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="tab4" style="background-color:#B0C4DE;">
					      <h4 class="panel-title">
					        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion2" href="#ddd" aria-expanded="false" aria-controls="ddd">
					          <span class="glyphicon glyphicon-bullhorn"></span> Banner
					        </a>
					      </h4>
					    </div>
					    <div id="ddd" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab4">
					      <div class="panel-body">
					        	<ul style="list-style-type: none">
									<li><a href="<%=request.getContextPath()%>/frontend_hotel/ad/bannerProject.jsp">Banner廣告購買</a></li>
					        		<li><a href="<%=request.getContextPath()%>/frontend_hotel/ad/listAllByHotelIdPage.jsp">購買紀錄</li>
					        	</ul>
					      </div>
					    </div>
					  </div>
					  <!-- 區塊7 -->
										  					  
					  <!-- 區塊2 -->
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="tab2" style="background-color:#B0C4DE;">
					      <h4 class="panel-title">
					        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion2" href="#bbb" aria-expanded="false" aria-controls="bbb">
					          <span class="glyphicon glyphicon-king"></span> 房型資料
					        </a>
					      </h4>
					    </div>
					    <div id="bbb" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab2">
					      <div class="panel-body">
					        	<ul style="list-style-type: none">
					        		<li>
					        			<a href="<%=request.getContextPath()%>/room/room.do?action=getAllRoom_ForOneHotel_Display&hotelId=${hotelId}">房型資料維護
					        		</li>
					        		<li>
					        			<a href="<%=request.getContextPath()%>/room/room.do?action=getAllRoomSell_ForOneHotel_Display&hotelId=${hotelId}">上架管理
					        		</li>
					        	</ul>
					      </div>
					    </div>
					  </div>
					  <!-- 區塊3 -->
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="tab3" style="background-color:#B0C4DE;">
					      <h4 class="panel-title">
					        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion2" href="#ccc" aria-expanded="false" aria-controls="ccc">
					          <span class="glyphicon glyphicon-queen"></span> 廠商會員
					        </a>
					      </h4>
					    </div>
					    <div id="ccc" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab3">
					      <div class="panel-body">
					        	<ul style="list-style-type: none">
					        		
					        		<a href="<%=request.getContextPath()%>/frontend_hotel/hotel/findByPrimaryKey.jsp"><li>廠商會員資料修改</li></a>
					        	</ul>
					      </div>
					    </div>
					  </div>					  
					  <!-- 區塊5 -->
										  					  					  
					  <!-- 區塊6 -->
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="tab6" style="background-color:#B0C4DE;">
					      <h4 class="panel-title">
					        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion2" href="#fff" aria-expanded="false" aria-controls="fff">
					          <span class="glyphicon glyphicon-thumbs-down"></span> 檢舉
					        </a>
					      </h4>
					    </div>
					    <div id="fff" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab6">
					      <div class="panel-body">
					        	<ul style="list-style-type: none">
					        		<li><a href="<%=request.getContextPath()%>/frontend_hotel/hotelRep/listAllHotelRepByHotelId.jsp">廠商檢舉單查詢</a></li>
					        	</ul>
					      </div>
					    </div>
					  </div>
					  <!-- 區塊8 -->
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="tab8" style="background-color:#B0C4DE;">
					      <h4 class="panel-title">
					        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion2" href="#hhh" aria-expanded="false" aria-controls="hhh">
					          <span class="glyphicon glyphicon-thumbs-up"></span> 留言評價
					        </a>
					      </h4>
					    </div>
					    <div id="hhh" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab8">
					      <div class="panel-body">
					        	<ul style="list-style-type: none">
					        		<li>留言評價維護</li>
					        	</ul>
					      </div>
					    </div>
					  </div>						  	
					</div>
				</div>
