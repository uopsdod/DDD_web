<%@ page contentType="text/html; charset=utf8"%>
<%@ page import="com.room.model.*"%>
<%@ page import="com.roomphoto.model.*"%>
<%@ page import="java.util.*"%>

<%
RoomVO roomVO = (RoomVO)request.getAttribute("roomVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<html>
<head>
<script src="<%=request.getContextPath()%>/room/js/6_new.js"></script>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Title Page</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<title>房型資料 - listOneEmp.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>員工資料 - ListOneEmp.jsp</h3>
		<a href="<%=request.getContextPath()%>/room/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>房間編號</th>
		<th>所屬hotel編號</th>
		<th>房間名稱</th>
		<th>總房間數</th>
		<th>房間定價</th>
		<th>是否上架中</th>
		<th>是否自動上架</th>
		<th>剩餘房數</th>
		<th>折扣開始時間</th>
		<th>折扣結束時間</th>
		<th>單位時間折扣百分比</th>
		<th>折扣單位時間</th>
		<th>是否一價到底</th>
		<th>幾人房</th>
		<th>單人床數</th>
		<th>雙人床數</th>
		
		
	</tr>
	<tr align='center' valign='middle'>
		<td><%=roomVO.getRoomId()%></td>
		<td><%=roomVO.getRoomHotelId()%></td>
		<td><%=roomVO.getRoomName()%></td>
		<td><%=roomVO.getRoomTotalNo()%></td>
		<td><%=roomVO.getRoomPrice()%></td>
		<td><%=roomVO.getRoomForSell()%></td>
		<td><%=roomVO.getRoomForSellAuto()%></td>
		<td><%=roomVO.getRoomRemainNo()%></td>
		<td><%=roomVO.getRoomDiscountStartDate()%></td>
		<td><%=roomVO.getRoomDiscountEndDate()%></td>
		<td><%=roomVO.getRoomDisccountPercent()%></td>
		<td><%=roomVO.getRoomDiscountHr()%></td>
		<td><%=roomVO.getRoomOnePrice()%></td>
		<td><%=roomVO.getRoomCapacity()%></td>
		<td><%=roomVO.getRoomOneBed()%></td>
		<td><%=roomVO.getRoomTwoBed()%></td>
	</tr>
	<tr>
		<th colspan="3">娛樂</th>
		<th colspan="3">餐飲</th>
		<th colspan="3">舒適睡眠</th>
		<th colspan="3">房間設施</th>
		<th colspan="4">貼心服務</th>
		
	</tr>
	<tr>
		<td colspan="3"><%=roomVO.getRoomFun()%></td>
		<td colspan="3"><%=roomVO.getRoomMeal()%></td>
		<td colspan="3"><%=roomVO.getRoomSleep()%></td>
		<td colspan="3"><%=roomVO.getRoomFacility()%></td>
		<td colspan="4"><%=roomVO.getRoomSweetFacility()%></td>
		
		
	</tr>
	
	
<!-- 	-----------------上傳照片------------------ -->
	<tr>
	<td colspan="4">
	<FORM action="<%=request.getContextPath()%>/RoomPhotoServlet" method=post enctype="multipart/form-data">
        
                   	               
                        房型照片<input type="file" name="upfile1" id='upfile1'><br>
                        房型照片<input type="file" name="upfile1" id='upfile2'><br> 
             
             
             <input type="hidden" name="roomPhotoRoomId" value="<%=roomVO.getRoomId()%>">        
             <input type="hidden" name="action" value="upPic">
             <input type="hidden" name="HotelId" value="<%=roomVO.getRoomHotelId()%>">    
             <input type="hidden" name="roomId" value="<%=roomVO.getRoomId()%>">   
             <input type="hidden" name="root" value="<%=request.getServletPath()%>">                 
        <input type="submit" value="上傳">
  	</FORM>
	</td>
	<td colspan="6">
	
			<img width="400" id="image1">
	</td>
	<td colspan="6">		
			<img width="400" id="image2">		
	</td >
	</tr>
<!-- 	-----------------上傳照片------------------ -->	
</table>
		<br><br><br><br><br><br>
		
		
		

<%
List<String> RoomPhotoId = (List)request.getAttribute("RoomPhotoId"); 
%>
		
	<%if(RoomPhotoId.size()!=0){%>	
		<div id="carousel-id" class="carousel slide" data-ride="carousel">
			<ol class="carousel-indicators">
		        <li data-target="#carousel-id" data-slide-to="0" class=""></li>
		        <li data-target="#carousel-id" data-slide-to="1" class=""></li>
		        <li data-target="#carousel-id" data-slide-to="2" class="active"></li>
		    </ol>
		    <!-- 幻燈片主圖區 -->
		    <div class="carousel-inner">
		    	<div class="carousel-inner">
		        <div class="item active">
		            <img src="<%=request.getContextPath()%>/RoomPhotoServlet?action=getOne_For_Display&roomPhotoId=<%=RoomPhotoId.get(0)%>" alt="">
		            <div class="container">
		                <div class="carousel-caption">
		                    
<!-- 		                    <p><a class="btn btn-lg btn-primary" href="#" role="button">Sign up today</a></p> -->
		                </div>
		            </div>
		        </div>
			       
		       <%for(int i =1;i<RoomPhotoId.size();i++){ %>
		        <div class="item">
		            <img src="<%=request.getContextPath()%>/RoomPhotoServlet?action=getOne_For_Display&roomPhotoId=<%=RoomPhotoId.get(i)%>" alt="">
		            <div class="container">
		                <div class="carousel-caption">
		                    
<!-- 		                    <p><a class="btn btn-lg btn-primary" href="#" role="button">詳細內容</a></p> -->
		                </div>
		            </div>
		        </div>
		        <%}%>
		        
		    </div>
<!-- 		   	------------------------- -->
<!-- 		        <div class="item"> -->
<%-- 		           <img src="<%=request.getContextPath()%>/RoomPhotoServlet?action=getOne_For_Display&roomPhotoId=<%=RoomPhotoId.get(0)%>" alt="">  --%>
<!-- 		            <div class="container"> -->
<!-- 		                <div class="carousel-caption"> -->
<!-- 		                    <h1>情慾一整夜</h1> -->
		                   
<!-- 		                    <p><a class="btn btn-lg btn-primary" href="#" role="button">Sign up today</a></p> -->
<!-- 		                </div> -->
<!-- 		            </div> -->
<!-- 		        </div>		         -->
<!-- 		   ----------------------------   -->
		      
		    </div>
		    <!-- 上下頁控制區 -->
		    <a class="left carousel-control" href="#carousel-id" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
		    <a class="right carousel-control" href="#carousel-id" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
		</div>

		<%};%>	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>
