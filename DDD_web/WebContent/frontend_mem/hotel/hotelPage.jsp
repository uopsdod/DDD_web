<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	
<%@ page import="java.util.*"%>
<%@ page import="com.ord.model.*"%>
<%@ page import="com.room.model.*"%>
<%@ page import="com.hotel.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.room.controler.RoomServlet"%>
<!DOCTYPE html>
<html lang="">
	<head>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/auth/css/sweet-alert.css">
		<script src="<%=request.getContextPath()%>/backend/auth/js/sweet-alert.js"></script>
		<script src="<%=request.getContextPath()%>/frontend_mem/hotel/js/socket.js"></script>
		<title>Dua Dee Dou:晚鳥有優惠</title>
    	<link rel="shortcut icon" href="<%=request.getContextPath()%>/frontend_mem/images/index.jpg">
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
 				 height:700px;
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
		
<style type="text/css">
	#c-bgBox{
	 	display:none;
	 	position:fixed;
		 width:100%;
		 height:100%;
	 	background:#000;
		 z-index:5;
		 top:0;
		 left:0;
	 	opacity:0.5;
	}
	#c-contentBox{
		 display:none;
		 width:1100px;
		 height:500px;
		 position:fixed;
		 top:50%;
		 margin-top:-250px;
		 background:#fff;
		 z-index:6;
		 left:50%;
		 margin-left:-550px;
	}
</style>
<script>
var imgArray;
var count = 100;


function showHeart(e){
// 	console.log(e);

	e.style = "position:absolute;z-index:4;top:5%;left:80%;opacity:1"
}
function outHeart(e){
// 	console.log(e);	
	
	e.style = "position:absolute;z-index:4;top:5%;left:80%;opacity:0.3"
}
function addWishRoom(e){
// 	console.log(e);	
	var memId = document.getElementById("xxmemId");
	var val = memId.className;
	console.log(val);
	
	if(val!=""){
	var xhr = new XMLHttpRequest();
    
    xhr.onreadystatechange = function (){
		if( xhr.readyState == 4){
	      if( xhr.status == 200){
	    	  swal({
	          	  title: '願望清單',
	          	  text: xhr.responseText,
	          	  timer: 1700
	          	});
// 		     alert(xhr.responseText);		 <!--因後端JSP傳出emp(JSON物件)的JSON字串--><!--因而前端用xhr.responseText取出傳送的JSON字串-->
		  }else{
		    alert( xhr.status );
		  }
	    }
	  };//function 
	  
    var url = "<%=request.getContextPath()%>/RoomDetail";
 	  var data_info = "roomId=" +e.name+"&action=setLove&memId="+val;
 	  xhr.open("Post",url,true);
 	  xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
 	  xhr.send(data_info);
 	  
	}else{
		swal({
      	  title: '加入願望清單',
      	  text: '請先登入會員',
      	  timer: 1700
      	});
		
	}
}




function showRoom(roomId){
// 		console.log(roomId);
	 var xhr = new XMLHttpRequest();
	  //設定好回呼函數   
	  xhr.onreadystatechange = function (){
		if( xhr.readyState == 4){
	      if( xhr.status == 200){
		     RoomDetail(xhr.responseText);		 <!--因後端JSP傳出emp(JSON物件)的JSON字串--><!--因而前端用xhr.responseText取出傳送的JSON字串-->
		  }else{
		    alert( xhr.status );
		  }
	    }
	  };//function 
	  
	  //建立好Get連接與送出請求 	  
	  var url = "<%=request.getContextPath()%>/RoomDetail";
	  var data_info = "roomId=" +roomId+"&action=getDetail";
	  xhr.open("Post",url,true);
	  xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	  xhr.send(data_info);

}

function RoomDetail(data){
// 	console.log(data);
	var allBag	= JSON.parse(data);
	
    var bgBox = document.getElementById("c-bgBox");
    var contentBox = document.getElementById("c-contentBox");
	
    bgBox.style="display:inline";
    contentBox.style="display:inline";
    

    
    bgBox.onclick=function(){   	
   	bgBox.style="display:none";
    contentBox.style="display:none";   	
    }
    
    
    var photoIdArray = allBag.roomPhotoId;
	
    var contentArrray = contentBox.childNodes;
    for(var i=0;i<contentArrray.length;i++){
    	contentBox.removeChild(contentArrray[i]);	
    }
    
    
    var img = document.createElement("img");
    img.src="<%=request.getContextPath()%>/RoomPhotoServlet?action=getOne_For_Display&roomPhotoId=" + photoIdArray[0]; 
 	img.style = "height:400px;width:730px;top:11%;left:3%;position:absolute";
    contentBox.appendChild(img);
    
    var rightImg = document.createElement("img");
    rightImg.src = "<%=request.getContextPath()%>/frontend_mem/hotel/image/rightArrow.png";
    rightImg.style = "position:absolute;z-index:10;top:50%;left:66%";
    contentBox.appendChild(rightImg);
    rightImg.onclick = function(){
    	count ++;
    	var number = count%(photoIdArray.length);
    	img.src = "<%=request.getContextPath()%>/RoomPhotoServlet?action=getOne_For_Display&roomPhotoId=" +photoIdArray[number];	
    }
    
    
    var leftImg = document.createElement("img");
    leftImg.src = "<%=request.getContextPath()%>/frontend_mem/hotel/image/leftArrow.png";
    leftImg.style = "position:absolute;z-index:10;top:50%;left:4%";
    contentBox.appendChild(leftImg);
    leftImg.onclick = function(){
    	count --;
    	var number = count%(photoIdArray.length);
    	img.src = "<%=request.getContextPath()%>/RoomPhotoServlet?action=getOne_For_Display&roomPhotoId=" +photoIdArray[number];	
    }
    
    
    
    var heartImg = document.createElement("img");
    heartImg.src = "<%=request.getContextPath()%>/frontend_mem/hotel/image/hearts.png";
    heartImg.style = "position:absolute;z-index:10;top:14%;left:65%;opacity:0.3";
    contentBox.appendChild(heartImg);
    heartImg.onclick = function(){	//加入願望清單
    	
      if(allBag.memId!="null"){	
      var xhr = new XMLHttpRequest();
      
      xhr.onreadystatechange = function (){
  		if( xhr.readyState == 4){
  	      if( xhr.status == 200){
  	    	swal({
	          	  title: '願望清單',
	          	  text: xhr.responseText,
	          	  timer: 1700
	          	});
//   		     alert(xhr.responseText);		 <!--因後端JSP傳出emp(JSON物件)的JSON字串--><!--因而前端用xhr.responseText取出傳送的JSON字串-->
  		  }else{
  		    alert( xhr.status );
  		  }
  	    }
  	  };//function 
  	  
      var url = "<%=request.getContextPath()%>/RoomDetail";
   	  var data_info = "roomId=" +allBag.roomId+"&action=setLove&memId="+allBag.memId;
   	  xhr.open("Post",url,true);
   	  xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
   	  xhr.send(data_info);
      }else{
    	  swal({
          	  title: '加入願望清單',
          	  text: '請先登入會員',
          	  timer: 1700
          	});
      }
    }//onclick
    heartImg.onmouseover=function(){
    	heartImg.style = "position:absolute;z-index:10;top:14%;left:65%;opacity:1";
    }//onmouseover
    heartImg.onmouseout=function(){
    	heartImg.style = "position:absolute;z-index:10;top:14%;left:65%;opacity:0.3";
    }//onmouseout
    
    
    
    var textDiv =  document.createElement("div");
    textDiv.style = "top:10%;left:72%;position:absolute;font-family:Microsoft JhengHei;";
    contentBox.appendChild(textDiv);
    
    
    var nameDiv = document.createElement("div");
    var name = document.createTextNode(""+allBag.name+"/"+capacityMap.get(allBag.capacity));
    nameDiv.style="font-size:25px;font-weight:bold";
    nameDiv.appendChild(name);
    textDiv.appendChild(nameDiv);
   
    var br = document.createElement("br");
    textDiv.appendChild(br);
    
    
    var MealDiv = document.createElement("div");
    var Meal = document.createTextNode("餐飲 : ");
    MealDiv.style="font-size:20px;font-weight:bold";
    MealDiv.appendChild(Meal);
    textDiv.appendChild(MealDiv);
    
    
    
    var MealContentDiv = document.createElement("div");
    var MealContent = document.createTextNode(""+allBag.meal);
    MealContentDiv.style="font-size:16px;width:280px ";
    MealContentDiv.appendChild(MealContent);
    textDiv.appendChild(MealContentDiv);
    
    
    
    
    var funDiv = document.createElement("div");
    var fun = document.createTextNode("娛樂 : ");
    funDiv.style="font-size:20px;margin-top:5px;font-weight:bold";
    funDiv.appendChild(fun);
    textDiv.appendChild(funDiv);
    
    
    
    var funContentDiv = document.createElement("div");
    var funContent = document.createTextNode(""+allBag.fun);
    funContentDiv.style="font-size:16px;width:280px ";
    funContentDiv.appendChild(funContent);
    textDiv.appendChild(funContentDiv);
    
    var facilityDiv = document.createElement("div");
    var facility = document.createTextNode("設施 : ");
    facilityDiv.style="font-size:20px;margin-top:5px;font-weight:bold";
    facilityDiv.appendChild(facility);
    textDiv.appendChild(facilityDiv);
    
    
    
    var facilityDiv = document.createElement("div");
    var facility = document.createTextNode(""+allBag.facility);
    facilityDiv.style="font-size:16px;width:280px ";
    facilityDiv.appendChild(facility);
    textDiv.appendChild(facilityDiv);
    
    
    var sleepDiv = document.createElement("div");
    var sleep = document.createTextNode("舒適睡眠 : ");
    sleepDiv.style="font-size:20px;margin-top:5px;font-weight:bold";
    sleepDiv.appendChild(sleep);
    textDiv.appendChild(sleepDiv);
    
    
    
    var sleepDiv = document.createElement("div");
    var sleep = document.createTextNode(""+allBag.sleep);
    sleepDiv.style="font-size:16px;width:280px ";
    sleepDiv.appendChild(sleep);
    textDiv.appendChild(sleepDiv);
    
    var sweetDiv = document.createElement("div");
    var sweet = document.createTextNode("貼心服務 : ");
    sweetDiv.style="font-size:20px;margin-top:5px;font-weight:bold";
    sweetDiv.appendChild(sweet);
    textDiv.appendChild(sweetDiv);
    
    
    
    var sweetDiv = document.createElement("div");
    var sweet = document.createTextNode(""+allBag.sweet);
    sweetDiv.style="font-size:16px;width:280px ";
    sweetDiv.appendChild(sweet);
    textDiv.appendChild(sweetDiv);
    
    imgArray = allBag.roomPhotoId;
   	
    
//     var outDiv = document.createElement("div");
    
    
    
}

</script>		
		</head>
		<body>
			
	
	<!--==主導覽列=====================================================================================================-->	
	
			<%@ include file="/frontend_mem/indexHeader.jsp"%>
<!--==燈箱=====================================================================================================-->
	
	<div id="c-bgBox"></div>
	<div id="c-contentBox"></div>
	
<!--=燈箱=====================================================================================================-->	
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
<!--==會員編號=====================================================================================================-->	
	<div id="xxmemId" class="${memVO.memId}"> </div>
<!--==大螢幕=====================================================================================================-->	
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
							<div class="col-xs-12 col-sm-12" style="position:relative">
							<img src="<%=request.getContextPath()%>/RoomPhotoServlet?action=getOne_For_Display&roomPhotoId=${AllRoomPhotoMap.get(roomVO.getRoomId()).get(0).getRoomPhotoId()}"	class="c-roomImg">
							<img name="${roomVO.roomId}" src = "<%=request.getContextPath()%>/frontend_mem/hotel/image/hearts.png"; style = "position:absolute;z-index:4;top:5%;left:80%;opacity:0.3" onmouseover="showHeart(this)" onmouseout="outHeart(this)" onclick="addWishRoom(this)" >
							</div>																																																																		
						</div>
						<div class="col-xs-12 col-sm-7">
							
							<div class="col-xs-12 col-sm-5">
							
							<div >
							<span >${roomVO.roomName}</span>
							<div><input type="button" class="btn btn-info"  value="查看"  onclick="showRoom(${roomVO.roomId})" ></div>
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
								<span style="display:none"><input type="button" class="btn btn-warning"  value="待上架"  ></span>
								<%}else{ %>
								<span style="display:none" id="xx${roomVO.roomId}"><input type="submit" class="btn btn-success"  value="下訂"></span>
								<span style="display:inline"><input type="button" class="btn btn-warning"  value="待上架"  ></span>	
								<%} %>
	
							</div>
					</form>
						</div>
					
				</div>
					<hr>					 
					</c:forEach>
					
				<%List<OrdVO> listOne= (List)request.getAttribute("commentList"); %>	
				<%if(listOne.size()!=0){ %>
				
					
				<div class="panel panel-success col-xs-12 col-sm-12">
				  <div class="panel-heading">
				    <h3 class="panel-title">評論
				 
					
				
				    </h3>
				  </div>		  
				</div>
					<br>
					
					<div 
						class="col-xs-12 col-sm-12"
						style="
						font-size:18px;
						position:absolute;					
						height: 600px;
						overflow: scroll;
						overflow-x: hidden;
						font-family:Microsoft JhengHei;">
					
					<c:forEach var="ordVO" items="${commentList}"  >
						
						<div class="row c-room" style="height:60px">
							<div class="col-xs-12 col-sm-2">
								<img src="<%=request.getContextPath()%>/RoomDetail?memId=${ordVO.ordMemId}" style="width:80px;border-radius: 50%">
								
							</div>
							
							<div class="col-xs-12 col-sm-10">
								${ordVO.ordRatingContent}
							</div>
						</div>
						<hr>
				    </c:forEach>
				    
				    </div>
				<%} %>
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
var capacityMap = new Map();
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
	
	addMan(hotelPageId);	//增加即時觀看一人
// 	console.log(remainMap);
	
	capacityMap.set(1,"單人房");
	capacityMap.set(2,"雙人房");
	capacityMap.set(4,"四人房");
	capacityMap.set(6,"六人房");
	capacityMap.set(8,"八人房");
	
	
	

	
	
}

<%
String wishId = request.getParameter("wishLookId");
System.out.println(wishId);
if(wishId!=null){
%>	
window.addEventListener( "DOMContentLoaded", function(){ready(<%=wishId%>)}, false );	
<%}%>	


function ready(wishId){
	
	console.log(wishId);
	showRoom(wishId);
}

function addMan(hotelId){
	var xhr = new XMLHttpRequest();
	  
	  //建立好Post連接
	  var url = "<%=request.getContextPath()%>/countMan";
	  var data_info = "hotelId=" +hotelId+"&action=add";
	  xhr.open("Post",url,true);
	  xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	  xhr.send(data_info);	
	
}

window.onbeforeunload=function(){
	
	  var xhr2 = new XMLHttpRequest();
	  
	  //建立好Post連接
	  var url = "<%=request.getContextPath()%>/countMan";
	  var data_info = "hotelId=" +hotelPageId+"&action=cut";
	  xhr2.open("Post",url,true);
	  xhr2.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	  xhr2.send(data_info);
   	  disconnect();
   	
}
</script>
</html>