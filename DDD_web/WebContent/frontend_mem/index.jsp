<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fs" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.ad.model.*"%>
<%@ page import="com.hotel.model.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>
	<%@ page import="com.room.model.*"%>
	<%@ page import="com.room.controler.*"%>
	
<!DOCTYPE html>

<%

session.getAttribute("account_mem");
MemVO memVO =(MemVO)session.getAttribute("memVO");
session.setAttribute("memVO", memVO);

%>
<!-- 叫AD圖 -->
<%
AdService adSvc = new AdService();
List<AdVO> advo = (List<AdVO>)adSvc.getAll();
pageContext.setAttribute("advo",advo);
%>
<%
HotelService hotelsvc = new HotelService();
List<Map> list =hotelsvc.GET_RANDOM_HOTEL_TO_VIEW();
pageContext.setAttribute("list", list);
%>

<html>
<head>
        <meta charset="UTF-8">
        
    <link href="http://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend_mem/css/1_css.css">
    <script src="<%=request.getContextPath()%>/frontend_mem/js/1_new.js"></script>
    <script src="<%=request.getContextPath()%>/frontend_mem/wish/socket.js"></script>
<%--     <script src="<%=request.getContextPath()%>/frontend_mem/indexSocket.js"></script> --%>
    <title>Dua Dee Dou:晚鳥有優惠</title>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/frontend_mem/images/index.jpg">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/bs/bootstrap.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/bs/bootstrap-theme.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/frontend_mem/css/style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/auth/css/sweet-alert.css">
<script src="<%=request.getContextPath()%>/backend/auth/js/sweet-alert.js"></script>
    <script src="<%=request.getContextPath()%>/jq/jquery-3.1.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/jq/jquery-ui.js"></script>
    <script src="<%=request.getContextPath()%>/jq/jquery-cycle-all.js"></script>
    <script src="<%=request.getContextPath()%>/bs/bootstrap.min.js"></script>
    <script src="http://cdn.bootcss.com/gsap/1.19.0/TweenMax.min.js"></script>
    <script src="http://cdn.bootcss.com/gsap/1.19.0/plugins/ScrollToPlugin.min.js"></script>
    
    <!-- <script src="jq/jquery.cycle.lite.js"></script> -->
</head>
<script>
<%if(list.size()!=0){%>
var a = [<c:forEach var="wish" items="${list}">"${wish.roomid}",</c:forEach> "${list.get(0).get("roomid")}"];
<%}else{%>

<%}%>
	
	
	var roomMap;
	
	window.onunload =disconnect;
	window.addEventListener('load',function(){ 
		roomMap = new Map;
		for(var i =0;i<a.length;i++){
			var node = document.getElementById(a[i]);
			roomMap.set(a[i],node);	
			}
		console.log(roomMap);
		},false);
	window.addEventListener('load',connect,false);
// 	window.addEventListener('load',connect_B,false);
// 	window.onunload =disconnect_B;
	
	function formSubmit()
	  {
		document.getElementById("myForm").submit();
	  }
	function formSubmit1()
	  {
		document.getElementById("myForm1").submit();
	  }
	function formSubmit2()
	  {
		document.getElementById("myForm2").submit();
	  }
	function formSubmit3()
	  {
		document.getElementById("myForm3").submit();
	  }
	function formSubmit4()
	  {
		document.getElementById("myForm4").submit();
	  }
	function formSubmit5()
	  {
		document.getElementById("myForm5").submit();
	  }
	function formSubmit6()
	  {
		document.getElementById("myForm6").submit();
	  }
	function formSubmit7()
	  {
		document.getElementById("myForm7").submit();
	  }
	function formSubmit8()
	  {
		document.getElementById("myForm8").submit();
	  }
	function formSubmit9()
	  {
		document.getElementById("myForm9").submit();
	  }
	function formSubmit10()
	  {
		document.getElementById("myForm10").submit();
	  }
	function formSubmit11()
	  {
		document.getElementById("myForm11").submit();
	  }
	
	$(function() {
        $("#gotop1,#gotop2").click(function(e) {
            TweenMax.to(window, 1.5, {
                scrollTo: 0,
                ease: Expo.easeInOut
            });
            var huojian = new TimelineLite();
            huojian.to("#gotop1", 1, {
                    rotationY: 720,
                    scale: 0.6,
                    y: "+=40",
                    ease: Power4.easeOut
                })
                .to("#gotop1", 1, {
                    y: -1000,
                    opacity: 0,
                    ease: Power4.easeOut
                }, 0.6)
                .to("#gotop1", 1, {
                    y: 0,
                    rotationY: 0,
                    opacity: 1,
                    scale: 1,
                    ease: Expo.easeOut,
                    clearProps: "all"
                }, "1.4");
        });

    });
	
	
			 function test(room){
				 
				 	
				   var memid = $('.WISHMEMID').val();                        					
				   var roomid = room.id;         
				   var check = $('.action').val(); 
				   
				   var xhr = new XMLHttpRequest();
					  //設定好回呼函數 
					  xhr.onreadystatechange = function (){
					    if( xhr.readyState == 4 ){
					      if(xhr.status == 200){
//			 		    	  alert(xhr.responseText);                               
                              var member = JSON.parse(xhr.responseText);
                              var value= member.MESSAGE;
                                 			       
                              swal({
                            	  title: '願望清單',
                            	  text: value,
                            	  timer: 1700
                            	})
                      	    
                              
					      }else{
					        alert("");
					      }
					    }
					  }
					  
					  
					//建立好Post連接
					  var url = "<%=request.getContextPath()%>/wish/wish.do";
					  var data_info = "WISHMEMID="+memid+"&WISHROOMID="+roomid+"&action="+check;
					  xhr.open("Post",url,true);
					  xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
					  xhr.send(data_info);

		}

			 function openRoomData(roomId,hotelId){
			 	
			 	window.open("/DDD_web/HotelRoomSearch?action=hotelPage&wishLookId=" + roomId + "&hotelId=" + hotelId);
			 	
			 }

</script>
<script>
	function openRoomData(roomId,hotelId){
	 	
	 	window.open("/DDD_web/HotelRoomSearch?action=hotelPage&wishLookId=" + roomId + "&hotelId=" + hotelId);
	 	
	 }
</script>
<body>
    <!-- 開始導覽列 -->
    <header id="top_header">
        <div class="col-md-5 col-md-offset-1 ">
            <a href="<%=request.getContextPath()%>/frontend_mem/index.jsp"><img src="<%=request.getContextPath()%>/frontend_mem/images/4.png" id="LogoImg"></a>
        </div>
        <div class="col-md-5 col-md-offset-1 ">
            <div class="dropdown" style='display: inline-block;'>
                <button class="btn text-muted" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
                    訂單專區
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/ord/listAllOrdByMemId.jsp">我的預訂</a></li>
                </ul>
            </div>
            <div class="dropdown" style='display: inline-block;'>
                <button class="btn text-muted" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
                    共住
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">                    
                     <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/chat/MapAndChat.jsp" target="_blank">聊天地圖</a></li>
                </ul>
            </div>
            <div class="dropdown" style='display: inline-block;'>
                <button class="btn text-muted" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
                    合作夥伴
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_hotel/hotel/addhotel.jsp">成為夥伴</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_hotel/hotel/loginhotel.jsp">夥伴登入</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">問題回報</a></li>
                </ul>
            </div>
            <div class="dropdown" style='display: inline-block;'>
                <button class="btn text-muted" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
                    幫助
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">FAQ</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">連絡我們</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/bookingProcess/bookingProcess.jsp">訂房需知</a></li>
                </ul>
            </div>
            <div class="dropdown" style='display: inline-block;'>
            <c:choose>
            <c:when test="${memVO.memName==null}">
                <button class="btn text-muted" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
                    會員中心
                    <span class="caret"></span>
                </button>      		                
		     </c:when>		  
		     <c:otherwise>
		        <button class="btn text-muted" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
                   ${memVO.memName}您好!
                    <span class="caret"></span>
                </button>  
		      </c:otherwise>
		    </c:choose> 
		    <c:choose>
		     	<c:when test="${memVO.memName==null}">
                	<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
	                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/mem/registerOfmember.jsp">註冊</a></li>
                    	<li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/mem/loginOfmember.jsp">登入</a></li>
	                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/mem/member.jsp">會員中心</a></li>
	                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/wish/wishList.jsp">查看願望清單</a></li>
                	</ul>
           		</c:when>
           		<c:otherwise>
           			<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">	                
	                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/mem/mem.do">登出</a></li>
	                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/mem/member.jsp">會員中心</a></li>
	                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/wish/wishList.jsp">查看願望清單</a></li>
                	</ul>
           		</c:otherwise>
           </c:choose>
            </div>
        </div>
        <!-- 浮動列 -->
        <div id="top_header1" class="hidden-xs">
            <div class="col-md-5 col-md-offset-1 ">
                <a href="<%=request.getContextPath()%>/frontend_mem/index.jsp"><img src="<%=request.getContextPath()%>/frontend_mem/images/4.png" id="LogoImg"></a>
            </div>
            <div class="col-md-5 col-md-offset-1 ">
                <div class="dropdown" style='display: inline-block;'>
                    <button class="btn text-muted" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
                        訂單專區
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/ord/listAllOrdByMemId.jsp">我的預訂</a></li>
                    </ul>
                </div>
                <div class="dropdown" style='display: inline-block;'>
                    <button class="btn text-muted" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
                        共住
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/chat/MapAndChat.jsp" target="_blank">聊天地圖</a></li>
                    </ul>
                </div>
                <div class="dropdown" style='display: inline-block;'>
                    <button class="btn text-muted" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
                        合作夥伴
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                    	<li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_hotel/hotel/addhotel.jsp">成為夥伴</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_hotel/hotel/loginhotel.jsp">夥伴登入</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">問題回報</a></li>
                    </ul>
                </div>
                <div class="dropdown" style='display: inline-block;'>
                    <button class="btn text-muted" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
                        幫助
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">FAQ</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">連絡我們</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/bookingProcess/bookingProcess.jsp">訂房需知</a></li>
                </ul>
                    
                </div>
                <div class="dropdown" style='display: inline-block;'>
                    <c:choose>
			            <c:when test="${memVO.memName==null}">
			                <button class="btn text-muted" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
			                    會員中心
			                    <span class="caret"></span>
			                </button>      		                
					     </c:when>		  
					     <c:otherwise>
					        <button class="btn text-muted" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
			                   ${memVO.memName}您好!
			                    <span class="caret"></span>
			                </button>  
					      </c:otherwise>
					 </c:choose> 
                    <c:choose>
		     	<c:when test="${memVO.memName==null}">
                	<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
	                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/mem/registerOfmember.jsp">註冊</a></li>
                    	<li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/mem/loginOfmember.jsp">登入</a></li>
	                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/mem/member.jsp">會員中心</a></li>
	                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/wish/wishList.jsp">查看願望清單</a></li>
                	</ul>
           		</c:when>
           		<c:otherwise>
           			<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">	                          			
	                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/mem/mem.do">登出</a></li>
	                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/mem/member.jsp">會員中心</a></li>
	                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/wish/wishList.jsp">查看願望清單</a></li>
                	</ul>
           		</c:otherwise>
           </c:choose>
                </div>
            </div>
            <BR>
        </div>
        <!-- 背景圖 -->
        <div id="fade" class="col-md-12">
       		<img src="<%=request.getContextPath()%>/frontend_mem/images/back23.jpg" class="img" />
       		<img src="<%=request.getContextPath()%>/frontend_mem/images/back24.jpg" class="img" />
        	<img src="<%=request.getContextPath()%>/frontend_mem/images/back15.jpg" class="img" />
        	<img src="<%=request.getContextPath()%>/frontend_mem/images/back25.jpg" class="img" />            
            <img src="<%=request.getContextPath()%>/frontend_mem/images/back26.jpg" class="img" />
            <img src="<%=request.getContextPath()%>/frontend_mem/images/back27.jpg" class="img" />                                     
        </div>
        <!-- <div id="title">LIVE THERE</div>  -->
        <div class="hidden-xs">
            <a href="<%=request.getContextPath()%>/frontend_mem/bookingProcess/bookingProcess.jsp">
                <button id="buttnOnimg">如何使用DDD</button>
            </a>
        </div>
    </header>
    <!-- -------------------------------------------------------------------------------->
    <!-- <div id="img" align="center"></div> <!-- 首頁大圖 -->
    <!-- 圖上大字 -->
    <div id="shangxia2">
      <span id="gotop1" style="font-family:Tahoma, Verdana, 微軟正黑體;text-align:center;font-weight:bold;">
		<img src="<%=request.getContextPath()%>/frontend_mem/img/plane (2).png" alt="" width="45px" height="45px">

	  </span>
    </div>
    <section>
        <h2 id='searchTitle'>
        	<img src="<%=request.getContextPath()%>/frontend_mem/img/location.png" style="margin-top:-11px;"/>
        	訂飯店、住Villa、找共住、找便宜 包山包海國內外各類住宿
        	<img src="<%=request.getContextPath()%>/frontend_mem/img/house.png" style="margin-top:-11px;"/>
        </h2>
<!--         -------------------------嘉鴻你的搜尋------------------------------ -->
        <div id="searchbgcolor" class="col-md-12" >
            <table>
                <form action="<%=request.getContextPath()%>/frontend_mem/map/map.jsp" method="get">
                    <tr>
                        <th>想住哪?就搜哪!?</th>
                        <th>
                            <input type="submit" name="sure" value="搜出好價" id="mapSubmit">
                            <img src="<%=request.getContextPath()%>/frontend_mem/img/houseckick.png" alt="GO" width="40px" height="40px">
                        </th>
                    </tr>
                  
                </form>
            </table>
        </div>
 <!--         -------------------------嘉鴻你的搜尋------------------------------ -->     
         <h1 class="title1">
         	<img src="<%=request.getContextPath()%>/frontend_mem/img/left.jpg" />
         	提供給您的超值方案
         	<img src="<%=request.getContextPath()%>/frontend_mem/img/right.jpg" />
         </h1>
        
       <section class="htmleaf-container">
		<div class="container">
		  
		  <div id="full" class="carousel slide" data-ride="carousel">
		    <div class="carousel-inner">

		      <ul class="row item active" >		
		    
		        <li class="col-xs-4">
		          <c:choose>
		             <c:when test='${advo["0"].bs64==""||advo["0"].bs64==null||advo["0"].adStatus!="4"}'>
		                <img src='<%=request.getContextPath()%>/frontend_mem/img/banner1.jpg'  srcset="" width="480" height="270"> 		                
				     </c:when>		  
				     <c:otherwise>
				     <form id="myForm" METHOD="post" ACTION="<%=request.getContextPath()%>/Ad/ad.do">
				       	<img src='data:image/jpeg;base64,${advo["0"].bs64}' srcset="" style="cursor:pointer" width="480" height="270" onclick="formSubmit()">
				     	<input type="hidden" name="Adid" value='${advo["0"].adId}'>
				     	<input type="hidden" name="action" value='hit'>
				     </form>
				     </c:otherwise>
				   </c:choose>
		        </li>	         
		        <li class="col-xs-4">
		          <c:choose>
		             <c:when test='${advo["1"].bs64==""||advo["1"].bs64==null||advo["1"].adStatus!="4"}'>
		                <img src='<%=request.getContextPath()%>/frontend_mem/img/banner2.jpg'  srcset="" width="480" height="270"> 		                
				     </c:when>		  
				     <c:otherwise>
				     <form id="myForm1" METHOD="post" ACTION="<%=request.getContextPath()%>/Ad/ad.do">
				       	<img src='data:image/jpeg;base64,${advo["1"].bs64}' srcset="" style="cursor:pointer" width="480" height="270" onclick="formSubmit1()">
				    	<input type="hidden" name="Adid" value='${advo["1"].adId}'>
				    	<input type="hidden" name="action" value='hit'>
				     </form>
				     </c:otherwise>
				   </c:choose>
		        </li>
		        <li class="col-xs-4">
		          <c:choose>
		             <c:when test='${advo["2"].bs64==""||advo["2"].bs64==null||advo["2"].adStatus!="4"}'>
		                <img src='<%=request.getContextPath()%>/frontend_mem/img/banner3.jpg'  srcset="" width="480" height="270"> 		                
				     </c:when>		  
				     <c:otherwise>
				     <form id="myForm2" METHOD="post" ACTION="<%=request.getContextPath()%>/Ad/ad.do">
				     	<img src='data:image/jpeg;base64,${advo["2"].bs64}' srcset="" style="cursor:pointer" width="480" height="270" onclick="formSubmit2()">
				    	<input type="hidden" name="Adid" value='${advo["2"].adId}'>
				    	<input type="hidden" name="action" value='hit'>
				     </form>
				     </c:otherwise>
				   </c:choose>
		        </li>		       
		      </ul>

		      <ul class="row item">
		        <li class="col-xs-4">
		          <c:choose>
		             <c:when test='${advo["3"].bs64==""||advo["3"].bs64==null||advo["3"].adStatus!="4"}'>
		                <img src='<%=request.getContextPath()%>/frontend_mem/img/banner4.jpg'  srcset="" width="480" height="270"> 		                
				     </c:when>		  
				     <c:otherwise>
				     <form id="myForm3" METHOD="post" ACTION="<%=request.getContextPath()%>/Ad/ad.do">
				       	<img src='data:image/jpeg;base64,${advo["3"].bs64}' srcset="" style="cursor:pointer" width="480" height="270" onclick="formSubmit3()">				     
				    	<input type="hidden" name="Adid" value='${advo["3"].adId}'>
				    	<input type="hidden" name="action" value='hit'>
				     </form>
				     </c:otherwise>
				   </c:choose>
		        </li>
		        <li class="col-xs-4">
		          <c:choose>
		             <c:when test='${advo["4"].bs64==""||advo["4"].bs64==null||advo["4"].adStatus!="4"}'>
		                <img src='<%=request.getContextPath()%>/frontend_mem/img/banner5.jpg'  srcset="" width="480" height="270"> 		                
				     </c:when>		  
				     <c:otherwise>
				     <form id="myForm4" METHOD="post" ACTION="<%=request.getContextPath()%>/Ad/ad.do">
				       	<img src='data:image/jpeg;base64,${advo["4"].bs64}' srcset="" style="cursor:pointer" width="480" height="270" onclick="formSubmit4()">				     
				    	<input type="hidden" name="Adid" value='${advo["4"].adId}'>
				    	<input type="hidden" name="action" value='hit'>
				     </form>
				     </c:otherwise>
				   </c:choose>
		        </li>
		        <li class="col-xs-4">
		          <c:choose>
		             <c:when test='${advo["5"].bs64==""||advo["5"].bs64==null||advo["5"].adStatus!="4"}'>
		                <img src='<%=request.getContextPath()%>/frontend_mem/img/banner6.jpg'  srcset="" width="480" height="270"> 		                
				     </c:when>		  
				     <c:otherwise>
				     <form id="myForm5" METHOD="post" ACTION="<%=request.getContextPath()%>/Ad/ad.do">
				       	<img src='data:image/jpeg;base64,${advo["5"].bs64}' srcset="" style="cursor:pointer" width="480" height="270" onclick="formSubmit5()">				     
				    	<input type="hidden" name="Adid" value='${advo["5"].adId}'>
				    	<input type="hidden" name="action" value='hit'>
				     </form>
				     </c:otherwise>
				   </c:choose>
		        </li>
		      </ul>
			  
			  <ul class="row item">
		        <li class="col-xs-4">
		          <c:choose>
		             <c:when test='${advo["6"].bs64==""||advo["6"].bs64==null||advo["6"].adStatus!="4"}'>
		                <img src='<%=request.getContextPath()%>/frontend_mem/img/banner7.jpg'  srcset="" width="480" height="270"> 		                
				     </c:when>		  
				     <c:otherwise>
				     <form id="myForm6" METHOD="post" ACTION="<%=request.getContextPath()%>/Ad/ad.do">
				       	<img src='data:image/jpeg;base64,${advo["6"].bs64}' srcset="" style="cursor:pointer" width="480" height="270" onclick="formSubmit6()">				     
				    	<input type="hidden" name="Adid" value='${advo["6"].adId}'>
				    	<input type="hidden" name="action" value='hit'>
				     </form>
				     </c:otherwise>
				   </c:choose>
		        </li>
		        <li class="col-xs-4">
		          <c:choose>
		             <c:when test='${advo["7"].bs64==""||advo["7"].bs64==null||advo["7"].adStatus!="4"}'>
		                <img src='<%=request.getContextPath()%>/frontend_mem/img/banner8.jpg'  srcset="" width="480" height="270"> 		                
				     </c:when>		  
				     <c:otherwise>
				     <form id="myForm7" METHOD="post" ACTION="<%=request.getContextPath()%>/Ad/ad.do">
				       	<img src='data:image/jpeg;base64,${advo["7"].bs64}' srcset="" style="cursor:pointer" width="480" height="270" onclick="formSubmit7()">				     
				    	<input type="hidden" name="Adid" value='${advo["7"].adId}'>
				    	<input type="hidden" name="action" value='hit'>
				     </form>
				     </c:otherwise>
				   </c:choose>
		        </li>
		        <li class="col-xs-4">
		          <c:choose>
		             <c:when test='${advo["8"].bs64==""||advo["8"].bs64==null||advo["8"].adStatus!="4"}'>
		                <img src='<%=request.getContextPath()%>/frontend_mem/img/banner9.jpg'  srcset="" width="480" height="270"> 		                
				     </c:when>		  
				     <c:otherwise>
				     <form id="myForm8" METHOD="post" ACTION="<%=request.getContextPath()%>/Ad/ad.do">
				       	<img src='data:image/jpeg;base64,${advo["8"].bs64}' srcset="" style="cursor:pointer" width="480" height="270" onclick="formSubmit8()">				     
				    	<input type="hidden" name="Adid" value='${advo["8"].adId}'>
				    	<input type="hidden" name="action" value='hit'>
				     </form>
				     </c:otherwise>
				   </c:choose>
		        </li>
		      </ul>
			  
<!-- 			  //第十筆不是空，也不是沒圖片 -->
		      <c:if test='${advo["9"]!=null&&advo["9"].bs64!=""&&advo["9"].adStatus=="4"}'>
  			   <ul class="row item">
		        <li class="col-xs-4">
		        <form id="myForm9" METHOD="post" ACTION="<%=request.getContextPath()%>/Ad/ad.do">
				  <img src='data:image/jpeg;base64,${advo["9"].bs64}' srcset="" style="cursor:pointer" width="480" height="270" onclick="formSubmit9()">    
				  <input type="hidden" name="Adid" value='${advo["9"].adId}'>
				  <input type="hidden" name="action" value='hit'>
				</form>
		        </li>
		        <li class="col-xs-4">
		          <c:choose>
		             <c:when test='${advo["10"].bs64==""||advo["10"].bs64==null}'>
		                <a href="http://www.harbour-plaza.com/8degrees/Index-tc.htm"><img src='<%=request.getContextPath()%>/frontend_mem/img/banner14.jpg'  srcset="" width="480" height="270"></a>		                
				     </c:when>		  
				     <c:otherwise>
				     <form id="myForm10" METHOD="post" ACTION="<%=request.getContextPath()%>/Ad/ad.do">
				       	<img src='data:image/jpeg;base64,${advo["10"].bs64}' srcset="" style="cursor:pointer" width="480" height="270" onclick="formSubmit10()">				     
					    <input type="hidden" name="Adid" value='${advo["10"].adId}'>
					    <input type="hidden" name="action" value='hit'>
					 </form>
				     </c:otherwise>
				   </c:choose>
		        </li>
		        <li class="col-xs-4">
		          <c:choose>
		             <c:when test='${advo["11"].bs64==""||advo["11"].bs64==null}'>
		                <a href="https://taipei.grand.hyatt.com/zh-Hant/hotel/dining.html"><img src='<%=request.getContextPath()%>/frontend_mem/img/banner17.jpg'  srcset="" width="480" height="270"></a> 		                
				     </c:when>		  
				     <c:otherwise>
				     <form id="myForm11" METHOD="post" ACTION="<%=request.getContextPath()%>/Ad/ad.do">				   
				       	<img src='data:image/jpeg;base64,${advo["11"].bs64}' srcset="" style="cursor:pointer" width="480" height="270" onclick="formSubmit11()">
				     	<input type="hidden" name="Adid" value='${advo["11"].adId}'>
				     	<input type="hidden" name="action" value='hit'>
					 </form>
				     </c:otherwise>
				   </c:choose>
		        </li>
		      </ul>
		     </c:if>
		     
		      </div>

		    <a class="carousel-control left" href="#full" data-slide="prev" >Previous</a>
		    <a class="carousel-control right" href="#full" data-slide="next" style="margin-top:-10px;"  >next</a>
		  </div>  
		  </div>
	</section>
        
        
<!--         <h1 class="title1">提供給您的超值方案</h1> -->
<!--         <ul class="bannerUl"> -->
<!--             <li class="slider_container"> -->
<!--                 <div id="banner" align="center"></div> -->
<!--             </li> -->
<!--             <li class="slider_container"> -->
<!--                 <div id="banner1" align="center"></div> -->
<!--             </li> -->
<!--             <li class="slider_container"> -->
<!--                 <div id="banner2" align="center"></div> -->
<!--             </li> -->
<!--         </ul> -->
<!--         <div class="col-md-12  " align="center"> -->
<%--             <button align="center" class="btnOfLeft" id="btnOfLeft1"><img src="<%=request.getContextPath()%>/frontend_mem/images/left-arrow.png"></button> --%>
<!--             <button class="dot" id="dot"></button> -->
<!--             <button class="dot" id="dot1"></button> -->
<%--             <button align="center" class="btnOfRight" id="btnOfRight1"><img src="<%=request.getContextPath()%>/frontend_mem/images/right.png"></button> --%>
<!--         </div> -->
			
  			  
<%-- 		<c:if test='${list["7"]!=null}'>     --%>
        <h1 class="title5">
        	<img src="<%=request.getContextPath()%>/frontend_mem/img/left.jpg" />
        	提供最優惠的今晚價格
        	<img src="<%=request.getContextPath()%>/frontend_mem/img/right.jpg" />
        </h1>
        <c:if test="${not empty errorMsgs}">
			<div  class="error" align="center" style='color:red;font-family:Tahoma, Verdana, 微軟正黑體;'> 
			${errorMsgs}		
			</div>
		</c:if>   
        <div class="container" style="width: 97%">
            <div class="row">
                <div class="col-xs-12 col-sm-12">
                    <div class="container" style="width: 97%">
                        <div class="row">
                        <c:forEach var="room" items="${list}">
                          
                            <div class="borderOfBanner col-xs-12 col-sm-3" style="position:relative">                            	
                                <div class="item onsaledemo" style="border-bottom: gray double">
                                    <img src='data:image/jpeg;base64,${room.roomPhotoPic}' class="img-responsive" width="100%">
                                    <h4 style='font-weight: bold;'>${room.hotelName}/${room.roomName}</h4>
                                    
                                    	<c:choose>
                                    	 <c:when test='${room.hotelRatingResult==0}'>
                                    	 	目前沒有評價
									     </c:when>
								         <c:when test='${room.hotelRatingResult==1}'>
								         	<img src='<%=request.getContextPath()%>/frontend_mem/img/star.png' width='16px' height="16px">
									     </c:when>
									     <c:when test='${room.hotelRatingResult==2}'>
								         	<img src='<%=request.getContextPath()%>/frontend_mem/img/star.png' width='16px' height="16px">
								         	<img src='<%=request.getContextPath()%>/frontend_mem/img/star.png' width='16px' height="16px">
									     </c:when>
									     <c:when test='${room.hotelRatingResult==3}'>
								         	<img src='<%=request.getContextPath()%>/frontend_mem/img/star.png' width='16px' height="16px">
								         	<img src='<%=request.getContextPath()%>/frontend_mem/img/star.png' width='16px' height="16px">
								         	<img src='<%=request.getContextPath()%>/frontend_mem/img/star.png' width='16px' height="16px">
									     </c:when>
									     <c:when test='${room.hotelRatingResult==4}'>
								         	<img src='<%=request.getContextPath()%>/frontend_mem/img/star.png' width='16px' height="16px">
								         	<img src='<%=request.getContextPath()%>/frontend_mem/img/star.png' width='16px' height="16px">
								         	<img src='<%=request.getContextPath()%>/frontend_mem/img/star.png' width='16px' height="16px">
								         	<img src='<%=request.getContextPath()%>/frontend_mem/img/star.png' width='16px' height="16px">
									     </c:when>		  
									     <c:otherwise>
									     	<img src='<%=request.getContextPath()%>/frontend_mem/img/star.png' width='16px' height="16px">
									     	<img src='<%=request.getContextPath()%>/frontend_mem/img/star.png' width='16px' height="16px">
									     	<img src='<%=request.getContextPath()%>/frontend_mem/img/star.png' width='16px' height="16px">
									     	<img src='<%=request.getContextPath()%>/frontend_mem/img/star.png' width='16px' height="16px">
									     	<img src='<%=request.getContextPath()%>/frontend_mem/img/star.png' width='16px' height="16px">
									     </c:otherwise>
									   </c:choose> 
                                       
                                </div>    
                                   
                                    <div style="margin-top:-29%;" align="RIGHT">
                                    
                                    	<div id ='${room.roomid}+"_forCount"' class="count">
                                    	
                                    	   ${room.count}                           
                                    	
                                    	</div>
                                        <del id="del">原價${room.roomPrice}</del>
                                        <br>
                                        <b id="tonight">↓今晚低至↓</b>
                                        <br>
                                        <div id="TWD">TWD 
                                        	<span id="${room.roomid}"><span>     
												<%  
													String roomId = (String)(((Map)pageContext.getAttribute("room")).get("roomid"));
													Map oneRoom = RoomServlet.OnData.get(roomId);
													if(oneRoom!=null)
													{	
														int onPrice = (Integer)oneRoom.get("price");
													%>
													   <%=onPrice %>
													<%
													
														System.out.println(onPrice);
													}else{
														out.write("未上架");
													}
												
												%>
												</span>
											</span>
                                        </div>                                     
                                    </div>
                                    
                                    <div style='margin-top:-8%;margin-left:10px;'>
                                    	 <div style='float:left;margin-right:5px;' >
                                    	 	<button id="btnwish" onclick="openRoomData(${room.roomid},${room.hotelId})" class="btnwish1">查看詳情</button> 
                                    	 </div>   
	                                     <c:choose>
	                                    	 <c:when test='${!empty memVO}'>
	                                    	 <FORM METHOD="post"> 
	                                    	 <div  id="start">	                                    	 
	                                    	 	<input type="button" value="加入清單" class="btnwish " id="${room.roomid}" onclick="test(this)" >
		                                        <input type="hidden" name="WISHMEMID" value='${memVO.memId}' class="WISHMEMID">
		                                        <input type="hidden" name="WISHROOMID" value='${room.roomid}' class="WISHROOMID">
		                                        <input type="hidden" name="action" value="insertWish" class="action"> 
		                                        </div> 
		                                     </FORM>                                  	 	
										     </c:when>					        
										     <c:otherwise>
										     
										     </c:otherwise>
										</c:choose>  																				
									</div>
									<BR>	                                                                                                   
                            	 </div>
                             
                          </c:forEach>                                                  
                        </div>
                    </div>
                </div>
            </div>
        </div>
<%--         </c:if> --%>
        <!--  zzzzzzzzzzzz -->
        <h1 class="title4">
        	<img src="<%=request.getContextPath()%>/frontend_mem/img/left.jpg" />
       		 讓我們為您服務
       		<img src="<%=request.getContextPath()%>/frontend_mem/img/right.jpg" />
        </h1>
        <div id="slideshow" class="col-md-12">
            <img src="<%=request.getContextPath()%>/frontend_mem/images/house.jpg" class="imggg" />
            <img src="<%=request.getContextPath()%>/frontend_mem/images/together.jpg" class="imggg" />
        </div>
        <h1 class="title2">
        	<img src="<%=request.getContextPath()%>/frontend_mem/img/left.jpg" />
        	精選人氣城市，體驗在地生活
        	<img src="<%=request.getContextPath()%>/frontend_mem/img/right.jpg" />
        </h1>
        <div class="col-md-12 text-center">
            <ul class="cityList">
                <li class="sectionAddress">
                    <div id="address7">
                        <img src='<%=request.getContextPath()%>/frontend_mem/images/japen.jpg' width='329px' height='328px' >
                    </div>
                </li>
                <li class="sectionAddress">
                    <div id="address">
                        <img src='<%=request.getContextPath()%>/frontend_mem/images/paris.jpg' width='761px' height='328px' >
                    </div>
                </li>
                <li class="sectionAddress">
                    <div id="address1">
                        <img src='<%=request.getContextPath()%>/frontend_mem/images/newyork.jpg' width='329px' height='328px' >
                    </div>
                </li>
            </ul>
        </div>
        <div class="col-md-12 text-center">
            <ul class="cityList">
                <li class="sectionAddress">
                    <div id="address2">
                        <img src='<%=request.getContextPath()%>/frontend_mem/images/londan.jpg' width='330px' height='328px' >
                    </div>
                </li>
                <li class="sectionAddress">
                    <div id="address3">
                        <img src='<%=request.getContextPath()%>/frontend_mem/images/moniblack.jpg' width='760px' height='328px' >
                    </div>
                </li>
                <li class="sectionAddress">
                    <div id="address8">
                        <img src='<%=request.getContextPath()%>/frontend_mem/images/japen1.jpg' width='330px' height='328px' >
                    </div>
                </li>
            </ul>
        </div>
        <div class="col-md-12 text-center">
            <ul class="cityList">
                <li class="sectionAddress">
                    <div id="address4">
                        <img src='<%=request.getContextPath()%>/frontend_mem/images/manko.jpg' width='330px' height='328px' >
                    </div>
                </li>
                <li class="sectionAddress">
                    <div id="address5">
                        <img src='<%=request.getContextPath()%>/frontend_mem/images/mila.jpg' width='375px' height='328px' >
                    </div>
                </li>
                <li class="sectionAddress">
                    <div id="address6">
                        <img src='<%=request.getContextPath()%>/frontend_mem/images/boli.jpg' width='360px' height='328px'>
                    </div>
                </li>
                <li class="sectionAddress">
                    <div id="address9">
                        <img src='<%=request.getContextPath()%>/frontend_mem/images/japen2.jpg' width='330px' height='328px'>
                    </div>
                </li>
            </ul>
        </div>
        <!-- <section>
                    <div class="col-md-4 " align="center" >
                        <img src="<%=request.getContextPath()%>/frontend_mem/images/japen.jpg">
                    </div>

                    <div class="col-md-4" align="center"> 
                        <img src="<%=request.getContextPath()%>/frontend_mem/images/japen1.jpg">
                    </div>

                    <div class="col-md-4 " align="center">
                        <img src="<%=request.getContextPath()%>/frontend_mem/images/japen2.jpg">
                    </div>
                </section> -->
        <!-- <section >
                    <div id ="portfolio">
                        <div class="work" >
                            <img src="<%=request.getContextPath()%>/frontend_mem/images/japen2.jpg">
                            <div class="caption">
                                s
                            </div>
                        </div> 

                        <div class="work">
                            <img src="<%=request.getContextPath()%>/frontend_mem/images/japen2.jpg">
                            <div class="caption">
                                s
                            </div>
                        </div> 

                        <div class="work">
                            <img src="<%=request.getContextPath()%>/frontend_mem/images/japen2.jpg">
                            <div class="caption">
                                s
                            </div>
                        </div> 

                        <div class="work">
                            <img src="<%=request.getContextPath()%>/frontend_mem/images/japen2.jpg">
                            <div class="caption">
                                s
                            </div>
                        </div> 

                        <div class="work">
                            <img src="<%=request.getContextPath()%>/frontend_mem/images/japen2.jpg">
                            <div class="caption">
                                s
                            </div>
                        </div> 

                        <div class="work">
                            <img src="<%=request.getContextPath()%>/frontend_mem/images/japen2.jpg">
                            <div class="caption">
                                s
                            </div>
                        </div> 
                    </div>
                </section> -->
        <!-- div  align="center" width="100%">
            <video poster="<%=request.getContextPath()%>/frontend_mem/images/future-travel.jpg" id="myMovie" width="80%">
                <source src="video/trip.mp4">
            </video>
        </div> -->
    </section>
    <!-- -------------------------------------------------------------------------- -->
    <footer id="the_footer" class="col-md-12">
        <div id="list" class="col-md-3 col-md-offset-3">
            <img src="<%=request.getContextPath()%>/frontend_mem/images/phone-call.png">
            <h3>連絡我們</h3>
            <br>訂購諮詢 412-8101
            <br>(手機加 02)
            <br>手機易遊通 55678
            <br>(中華電信、遠傳、台灣大哥大門號適用)
        </div>
        <div id="listHouse" class="col-md-2 ">
            <img src="<%=request.getContextPath()%>/frontend_mem/images/house.png">
            <h3>住宿類型</h3>
            <br><a href="#">度假村</a>
            <br><a href="#">露營地</a>
            <br><a href="#">青年旅館</a>
            <br><a href="#">家庭旅館</a>
            <br><a href="#">膠囊旅館</a>
            <br><a href="#">連鎖飯店</a>
            <br><a href="#">溫泉住宿</a>
            <br><a href="#">獨棟別墅 </a>
        </div>
        <div id="list" class="col-md-4 ">
            <img src="<%=request.getContextPath()%>/frontend_mem/images/smartphone.png">
            <h3>手機下載APP</h3>
            <br>
            <button class="app"><img src="<%=request.getContextPath()%>/frontend_mem/images/apple.jpg"></button>
            <br>
            <br>
            <button class="app"><img src="<%=request.getContextPath()%>/frontend_mem/images/google.jpg"></button>
        </div>
    </footer>
    <footer id="the_footer1" class="col-md-12 text-center">
        <h4 align="center">加入我們</h4>
        <img src="<%=request.getContextPath()%>/frontend_mem/images/facebook.png" id="facebook">
        <img src="<%=request.getContextPath()%>/frontend_mem/images/twitter.png">
        <img src="<%=request.getContextPath()%>/frontend_mem/images/youtube.png">
        <img src="<%=request.getContextPath()%>/frontend_mem/images/instagram.png">
        <img src="<%=request.getContextPath()%>/frontend_mem/images/google-plus.png">
        <h3 align="center">Dua Dee Dou</h3>
        <h4 align="center">版權所有©2005 – 2016, Dua Dee Dou Company .保留所有權利
                    DuaDeeDou.com是線上旅遊業及相關服務的全球領導品牌。</h4>
    </footer>
</body>

</html>
