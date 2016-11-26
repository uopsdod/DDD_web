<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	
<%@ page import="java.util.*"%>
<%@ page import="com.room.model.*"%>
<%@ page import="com.hotel.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.room.controler.RoomServlet"%>
<!DOCTYPE html>
<html lang="">
	<head>
		<script src="<%=request.getContextPath()%>/frontend_mem/hotel/js/socket.js"></script>
		
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Title Page</title>
<!-- 		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> -->
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<style type="text/css">
			.item{
				
				background: grey;
				padding:10px;
				margin-bottom: 30px;
			}
			.aa{margin-bottom: 0px;
				background: transparent;		
			}
			.jumbotron{
				margin-bottom: 20px;
			}
			#c-bigscreen{
				 background-size:100vw;
 				 background-image:url(<%=request.getContextPath()%>/HotelRoomSearch?action=showHotel&hotelId=<%=request.getParameter("hotelId")%>);	<!--進版大圖示-->
 				 
 				 background-repeat:inherit;
 				 background-repeat: no-repeat;
 				 height:900px;
				} 
				
			#c-bigscreenAnchor{position:absolute;top:650px;left:30px;}
			.c-breadcrumb{
					
				height:20vh;
				background:lightblue; 	
			}
			.c-mainHotel{
				height:12vh;
				position:relative;
				z-index:0;
			}
			.c-partHotel{
				height:8vh;
			}
			.c-room{
				margin-bottom:20px; 
			}	
			.c-roomImg{
				width:400px;

			}
			.abody{
				font-size:30px;
			}
			#C-roomContent{
				position:relative;
				z-index:0; 
				margin-top:25%;
			}
		</style>
		
		
		
		
		
		
		

		</head>
		<body>
	
	<!--==主導覽列=====================================================================================================-->	
	
			<%@ include file="/frontend_mem/indexHeader.jsp"%>
<!--==大螢幕=====================================================================================================-->
	<%	
		List<String> servList = (List)request.getAttribute("servList");
		HotelVO hotelVO = (HotelVO)request.getAttribute("hotelVO");		
		List<RoomVO> roomList =  (List<RoomVO>)request.getAttribute("roomVOlist");
		Map AllRoomPhotoMap = (Map)request.getAttribute("AllRoomPhotoMap");	
		
		pageContext.setAttribute("hotelVO",hotelVO);
		pageContext.setAttribute("roomList",roomList);
		pageContext.setAttribute("AllRoomPhotoMap",AllRoomPhotoMap);
		pageContext.setAttribute("servList",servList);
	%>	
	<%   //不要快取
	response.setHeader("Cache-Control", "no-store");//http1.1
	response.setHeader("Pragma", "no-cache");//http1.0
	response.setDateHeader("Expires", 0);	
	%>	
	
	<div class="jumbotron" id="c-bigscreen">
	  <div class="container">
	   
		 
	  </div>
	</div>

			

<!--==內容=====================================================================================================-->	

	<div class="container abody" style="width:60vw;">
		<div class="row" >
			<div class="col-xs-12 col-sm-12" ">
				
				<!--旅館主資料-->

				<div class="row ">
<!-- 					<div class="col-xs-12 col-sm-3 c-mainHotel text-center"> -->
<!-- 						<img src="https://api.fnkr.net/testimg/50x50/00CED1/FFF/?text=img+placeholder" style="height:18vh; border-radius: 60%;margin-top:25px"> -->
<!-- 					</div> -->
					<input type="hidden" id="hotelPageId" value="${hotelVO.hotelId}">
					<div class="col-xs-12 col-sm-12 c-mainHotel">
						<p><div style="font-size:50px">${hotelVO.hotelName}
						<% 	for(int i=1;i<=hotelVO.getHotelRatingResult();i++){%>
								<img src="<%=request.getContextPath()%>/frontend_mem/hotel/image/star.png"> 
						<%}%>
						</div></p>
						<p><small  style="font-size:18px;" ><span>${hotelVO.hotelCity},${hotelVO.hotelCounty},${hotelVO.hotelRoad}</span><span style="margin-left:30px">聯絡電話    ${hotelVO.hotelPhone}</span></small></p>					
					
				<!--旅館副資料-->


					<div class="row">
						<div class="col-xs-12 col-sm-3 c-partHotel text-center">
							<h2><img src="<%=request.getContextPath()%>/frontend_mem/hotel/image/house.png"></h2>
							<span class"word">${hotelVO.hotelType}<span>	
						</div> 
						
						<div class="col-xs-12 col-sm-3 c-partHotel text-center">
							<h2><img src="<%=request.getContextPath()%>/frontend_mem/hotel/image/man.png"></h2>
							<span class"word">客服<span>
						</div>
						
						<div class="col-xs-12 col-sm-3 c-partHotel text-center">
							<h2><img src="<%=request.getContextPath()%>/frontend_mem/hotel/image/room.png"></h2>
							<span class"word">房型<span>
						</div>
						
						<div class="col-xs-12 col-sm-3 c-partHotel text-center">
							<h2><img src="<%=request.getContextPath()%>/frontend_mem/hotel/image/bed.png"></h2>
							<span class"word">舒適睡眠<span>
						</div>
					</div>
				</div>
			
		<!--麵包屑- - - - - -  - - - - - - - - - - - - - - - - - - - - - - - - - - - -->		
			<div id="C-roomContent"><br>
			
				<div class="col-xs-12 col-sm-3  text-center">
				簡介
				</div>
				<div class="col-xs-12 col-sm-9">
				${hotelVO.hotelIntro}
				</div>
				
				<div class="col-xs-12 col-sm-12"><hr></div>
					
				<div class="col-xs-12 col-sm-3  text-center">
				設施
				</div>
				<div class="col-xs-12 col-sm-9">
					<c:forEach var="servName" items="${servList}"  >	
					<div class="col-xs-12 col-sm-4">
					${servName}
					
					</div>
					</c:forEach>
				</div>
				<div class="col-xs-12 col-sm-12"><hr></div>
				
				

				

			
			</div>	
				<!--面板- - - - - - - - - - - -      - - - - - - - - - - - -  - - - - - -  -->
				<div class="panel panel-success col-xs-12 col-sm-12">
				  <div class="panel-heading">
				    <h3 class="panel-title">哈囉~
				    <%  
				    request.setCharacterEncoding("UTF-8");
				    String newsGap = request.getParameter("news");
				    if(newsGap!=null){
				    String news = new String(newsGap.getBytes("ISO-8859-1"),"UTF-8");
					%>
						<span style="color:red">
						<%=news%>
						</span>
					<%}%>
				    </h3>
				  </div>
				  
				</div>
				<!--房型陳列- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
				
				
					<c:forEach var="roomVO" items="${roomList}"  >					
																							
				<div class="row c-room">
					
						<div class="col-xs-12 col-sm-5">
							<div class="col-xs-12 col-sm-12">
							<img src="<%=request.getContextPath()%>/RoomPhotoServlet?action=getOne_For_Display&roomPhotoId=${AllRoomPhotoMap.get(roomVO.getRoomId()).get(0).getRoomPhotoId()}"	class="c-roomImg">
							</div>
						</div>
						<div class="col-xs-12 col-sm-7">
							
							<div class="col-xs-12 col-sm-5">
							
							<div >
							<span >${roomVO.roomName}</span>
							</div>
							
							</div>
							<div class="col-xs-12 col-sm-5">
							<div ><div id="yy${roomVO.roomId}">剩餘房數${roomVO.roomRemainNo}</div>
							<span>定價        $</span>
							<span style="text-decoration:line-through;">${roomVO.roomPrice}</span>
							</div>
					<form method="post" action="<%=request.getContextPath()%>/RoomSetOrder">
							<div >
							<span>即時價格        $</span>
							<span id="${roomVO.roomId}"><span>     
								<%  
									RoomVO roomVO = (RoomVO)pageContext.getAttribute("roomVO");	
									Map oneRoom = RoomServlet.OnData.get(roomVO.getRoomId());
									if(oneRoom!=null)
									{	
										int onPrice = (Integer)oneRoom.get("price");
										pageContext.setAttribute("onPrice",onPrice);
									%>
									   <%=onPrice %>
									<%
									
// 										System.out.println(onPrice);
									}else{
										out.write("未上架");
									}
								
								%>
								</span>
							</span><input type="hidden" name="orderPrice" value="${onPrice}">		
							</div>
							</div>
							
							<div class="col-xs-12 col-sm-2" style="margin-top:-17px">
								
								<input type="hidden" name="orderHotelId" value="${hotelVO.hotelId}">
								<input type="hidden" name="action" value="addOrd">
								<input type="hidden" name="orderRoomId" value="${roomVO.roomId}">
																																											
								<%if(oneRoom!=null){ %>
								<span style="display:inline" id="xx${roomVO.roomId}"><input type="submit" class="btn btn-success"  value="下訂"></span>
								<span style="display:none"><input type="button" class="btn btn-success"  value="待上架"  ></span>
								<%}else{ %>
								<span style="display:none" id="xx${roomVO.roomId}"><input type="submit" class="btn btn-success"  value="下訂"></span>
								<span style="display:inline"><input type="button" class="btn btn-success"  value="待上架"  ></span>	
								<%} %>
	
							</div>
					</form>
						</div>
					
				</div>
					<hr>					 
					</c:forEach>
	
			
				

<!-- 				<div class="row c-room"> -->
					
<!-- 						<div class="col-xs-12 col-sm-3"> -->
<!-- 							<img src="mapImage/room6.jpg"	class="c-roomImg"> -->

<!-- 						</div> -->
<!-- 						<div class="col-xs-12 col-sm-9"> -->
<!-- 							Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cupiditate, voluptatem quaerat doloribus soluta beatae, minus, dicta, aliquam assumenda tempore ipsum id! Repellendus provident expedita earum a voluptates, voluptatum, nemo hic! -->
<!-- 						</div> -->
					
<!-- 				</div> -->





				<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - -->	
			</div>	
		</div>
	</div>

		

			
				
<!--==分頁=====================================================================================================-->			
			
	

	<!--==上下頁=====================================================================================================-->	
				
		

		</div>
	</div>
		


		



		
<!-- 		<script src="https://code.jquery.com/jquery.js"></script> -->
<!-- 		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->
	</body>
<script>
	function setOrder(data){
		
		
		window.location.href = "<%=request.getContextPath()%>/RoomSetOrder?action=getOrderPage&roomId="+data; 
		
	}
	
</script>
	
	
<script>

var roomMap;
var XspanMap;
var remainMap;
var FirstRoomId = [<%for(RoomVO roomVO3:roomList){%> <%=roomVO3.getRoomId()%>, <%}%>  <%=roomList.get(0).getRoomId()%> ];
//
var XspanId = [<%for(RoomVO roomVO3:roomList){%> <%="xx"+roomVO3.getRoomId()%>, <%}%>  <%="xx"+roomList.get(0).getRoomId()%> ];

var remainId = [<%for(RoomVO roomVO3:roomList){%> <%="yy"+roomVO3.getRoomId()%>, <%}%>  <%="yy"+roomList.get(0).getRoomId()%> ];

var hotelPageId = document.getElementById("hotelPageId").value; //取得進入哪一間hotel
	
window.onload=function(){
	connect(hotelPageId);
	roomMap = new Map;
	for(var i=0 ;i<FirstRoomId.length;i++){
		var item = document.getElementById(FirstRoomId[i]);
		if(item!=null){
		roomMap.set(""+FirstRoomId[i],item);
		}
	}
	
	XspanMap = new Map;
	for(var i=0 ;i<XspanId.length;i++){
		var item = document.getElementById(XspanId[i]);
// 		console.log(XspanId[i]);
// 		console.log(FirstRoomId[i]);
		
		XspanMap.set(FirstRoomId[i]+"",XspanId[i]);
		
	}	
	
	remainMap = new Map;
	for(var i=0 ;i<remainId.length;i++){
// 		var item = document.getElementById(remainId[i]);
// 		console.log(remainId[i]);
// 		console.log(FirstRoomId[i]);
		
		remainMap.set(FirstRoomId[i]+"",remainId[i]);
		
	}	
	
	
// 	console.log(remainMap);
	
}
window.onunload=disconnect;

</script>
</html>