<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.mem.model.*, java.util.*" %>
<%@ page import="java.text.*"%>

<%
	session.getAttribute("account_mem");
	MemVO memVO =(MemVO)session.getAttribute("memVO");
	session.setAttribute("memVO", memVO);
%>

<!DOCTYPE html>
<html lang="">

<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">	
	<title>聊天地圖</title>
	<script src="<%=request.getContextPath()%>/backend/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/backend/js/bootstrap.js"></script>		
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAg_hj_3C0zLlGwQfq7rQat6MhgkZwrZAk"></script>	
	<script src="<%=request.getContextPath()%>/frontend_mem/chat/js/livecond_index.js"></script>
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/css/bootstrap.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend_mem/chat/css/livecond_index.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend_mem/chat/css/0_chat.css">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/frontend_mem/images/index.jpg">    
     
</head>

<body>
    <header id="top_header">
        <div class="col-md-5 col-md-offset-1 ">
            <a href="<%=request.getContextPath()%>/frontend_mem/index.jsp"><img src="<%=request.getContextPath()%>/frontend_mem/images/4.png" id="LogoImg"></a>
        </div>
        <div class="col-md-5 col-md-offset-1 ">
            <div class="dropdown" style='display: inline-block;'>
                <button class="btn text-muted kbtn" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
                    訂單專區
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">我的訂單</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">QRCODE</a></li>
                </ul>
            </div>
            <div class="dropdown" style='display: inline-block;'>
                <button class="btn text-muted kbtn" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
                    共住
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/chat/MapAndChat.jsp" target="_blank">聊天地圖</a></li>
                </ul>
            </div>
            <div class="dropdown" style='display: inline-block;'>
                <button class="btn text-muted kbtn" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
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
                <button class="btn text-muted kbtn" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
                    幫助
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="https://www.agoda.com/zh-tw/">FAQ</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">連絡我們</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">訂房需知</a></li>
                </ul>
            </div>
            <div class="dropdown" style='display: inline-block;'>
                <c:choose>
            <c:when test="${memVO.memName==null}">
                <button class="btn text-muted kbtn" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
                    會員中心
                    <span class="caret"></span>
                </button>      		                
		     </c:when>		  
		     <c:otherwise>
		        <button class="btn text-muted kbtn" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
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
        
    </header>


    <section>    
       <div class="col-xs-12 col-sm-9" id="map-canvas" style="height:900px;">

       </div>  

        <div class="col-xs-12 col-sm-3" id="myList" style="height: 900px; overflow-y: auto;">

            <div class="row">
                <div class="dropdown sortBar pull-right">
                    <button id="dLabel" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        	依更新日期排序
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dLabel">
                        <li> <a href="#about">預算從大到小</a> </li>
                        <li> <a href="#base">預算從小到大</a> </li>
                        <li> <a href="#blog">年齡從大到小</a> </li>
                        <li> <a href="#contact">年齡從小到大</a> </li>
                    </ul>
                </div>
            </div>

            <div id="memCardList">
				<!-- 右邊的會員列表 -->		
												
<%-- 					<c:forEach var="memVO" items="${list}"> --%>
<!-- 						<div class="card memCard"> -->
<!-- 							<div style="vertical-align: middle" class="memProfile pull-left"> -->
<!-- 								<span style="display:inline-block;height:100%;vertical-align:middle;width:1px;margin-left:-10px"></span> -->
<%-- 		               			<img style="display:inline-block;max-height:100%;vertical-align:middle" class="img-responsive" src="<%=request.getContextPath()%>/mem/DBGifReader5?memId=${memVO.memId}" alt="Card image cap"> --%>
<!-- 							</div> -->
							
<!-- 							<div class="card-block memIntro"> -->
<%-- 					            <h4 class="card-title">${memVO.memName}</h4> --%>
<%-- 					            <p class="card-text">${memVO.memIntro}</p> --%>
<!-- 					            <a href="#" class="btn btn-primary memBtn">跟你聊一聊</a>		             -->
<!-- 						    </div> -->
						    
<!-- 						</div>	 -->
<%-- 					 </c:forEach> --%>
					                                   
            </div> <!-- memCardList -->

    	</div> <!-- myList -->

    </section>
    
	<!-- 聊天的div -->
	<div id="chatWindow" style="display:none">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<!-- 聊天視窗標題 -->
				<span class="glyphicon glyphicon-comment"> Chat</span>
				<div class="btn-group pull-right">
					<span class="glyphicon glyphicon-remove" id="closeChatWindow"></span>
				</div>
			</div>
			<!--  聊天內容視窗 -->
			<div id="messagesArea" class="panel-body">
				<ul class="chat">

				</ul>
			</div>

<!-- 			<div class="panel-footer"> -->
<!-- 				<h5 id="userName"></h5> -->
<!-- 				<input type="button" id="connect" class="btn btn-success btn-sm" -->
<!-- 					value="連線" onclick="connect();" /> <input type="button" -->
<!-- 					id="disconnect" class="btn btn-warning btn-sm" value="離線" -->
<!-- 					onclick="disconnect();" /> -->
<!-- 			</div> -->

			<div class="panel-footer">
				<div class="input-group">
					<input id="message" class="form-control input-sm" type="text"
						placeholder="Type your message here..."
						onkeydown="if (event.keyCode == 13) sendMessage();" /> <span
						class="input-group-btn"> <input type="submit"
						id="sendMessage" class="btn btn-primary btn-sm" value="送出"
						onclick="sendMessage();" />
					</span>
				</div>
			</div>
		</div>
	</div>

<!-- 	<h3 id="statusOutput" class="statusOutput"></h3> -->

</body>

<!-- Ajax  -->
<script>
/* 全域變數 */
var memId = <%=memVO.getMemId()%>;
var memLat;
var memLng;
var toMemArray;

var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));


function getListForUploader(){ 
	  //===建立xhr物件(填入程式碼)
	  var xhr = new XMLHttpRequest();
	  //設定好回呼函數   
	  xhr.onreadystatechange = function (){
	    if( xhr.readyState == 4){
	      if( xhr.status == 200){
	      //取回...回傳的資料
	        //console.log(xhr.responseText);
	        //document.getElementById("showPanel").innerHTML = xhr.responseText;
	        
	        toMemArray = JSON.parse(xhr.responseText);
	        
	        /*
	        for (var i = 0; i < toMemArray.length; i++) {
	            console.log(toMemArray[i].memId);
	        }
	        */
	                
	        for (var i = 0; i < toMemArray.length; i++) {
	        	//利用json建立地圖mark
	        	//利用json建立聊天相關
		    	addMarkerWithTimeout(toMemArray[i], 200);
	        	
		    	//利用json建立右邊列表
 		    	createMemCardList(toMemArray[i]);
			}
	        	          
	      }else{
	         alert( xhr.status );
	      }//xhr.status == 200
	    }//xhr.readyState == 4
	  };//onreadystatechange 
	  
	  //建立好Get連接
	  var ajaxPoint = "/android/live2/memCoord.do";
	  var ajaxUrl = "http://" + window.location.host + webCtx + ajaxPoint;
	  
	  ajaxUrl += "?action=uploadCoord&memId=" + memId + "&memLat=" + memLat + "&memLng=" + memLng;
	  
	  //console.log(ajaxUrl);
	  
	  xhr.open("Get",ajaxUrl,true); 

	  //送出請求 
	  xhr.send( null );
	}

</script>


<!-- 聊天相關 -->
<script>
	var MyPoint = "/android/live2/MsgCenter";
	/* 上面ajax宣告了 */
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

	//var statusOutput = document.getElementById("statusOutput");

	var webSocket;

	/* 使用者資訊 */
	var toMemId = "404"; /* 給個預設收方ID */
	var fromMemId = memId;
	//var yourPic = "img/profile_user.jpg";
	//var myPic = "img/profile_user2.jpg";

// 	document.getElementById("userName").innerHTML = fromMemId;
	var messagesArea = document.getElementById("messagesArea");

	/* 聊天專用物件 */
	var PartnerMsg = {
		"action" : "", // uploadTokenId, removeTokenId, bindMemIdWithSession, chat, 
		"memChatContent" : "Empty Message",
		"memChatDate" : "2016-11-17 12:07:51.1", //2016-11-17 12:07:51.1
		"memChatMemVO" : {
			"memId" : "",
			"memOrds" : []
		},
		"memChatToMemVO" : {
			"memId" : "",
			"memOrds" : []
		},
		"tokenId" : "aoisjcpoaishcp" // tokenId
	};

	function connect() {
		
		// 建立 websocket 物件
		console.log("body connect !");

		webSocket = new WebSocket(endPointURL);

		webSocket.onopen = function(event) {
			//updateStatus("WebSocket 成功連線");
// 			document.getElementById('sendMessage').disabled = false;
// 			document.getElementById('connect').disabled = true;
// 			document.getElementById('disconnect').disabled = false;
			
  			var NowDate = new Date();
  			var year = NowDate.getFullYear(); 
  			var month = NowDate.getMonth();
  			var date = NowDate.getDate();
  			var h = NowDate.getHours();
  			var m = NowDate.getMinutes();
  			var s = NowDate.getSeconds();
  			var mSec = NowDate.getMilliseconds();

  			var myChatTime = year + "-" + ((month+1)<10 ? '0' : '') + (month+1) +"-"+  (date<10 ? '0' : '') + date + " " + (h<10 ? '0' : '') + h +':' + (m<10 ? '0' : '') + m + ":" + (s<10 ? '0' : '') + s + "." + Math.round(mSec/100);

// 			console.log(myChatTime);
			
			var action = "bindMemIdWithSession";

			PartnerMsg.memChatMemVO.memId = fromMemId;
			PartnerMsg.memChatToMemVO.memId = toMemId;
			PartnerMsg.action = action;
			PartnerMsg.memChatDate = myChatTime;
			webSocket.send(JSON.stringify(PartnerMsg));
			
		};

		/* 接收訊息 */
		webSocket.onmessage = function(event) {
			/* 彈出來 */
			$("#chatWindow").show();
			$("#messagesArea").show();
			$(".panel-footer").show();
			
			var jsonObj = JSON.parse(event.data);

			//console.log("Debug: "+jsonObj);

			//var yourChatTime = "13 mins ago";	
			var NowDate = new Date();
			var h = NowDate.getHours();
			var m = NowDate.getMinutes();
			var s = NowDate.getSeconds();
			var yourChatTime = (h<10 ? '0' : '') + h +':' + (m<10 ? '0' : '') + m + ":" + (s<10 ? '0' : '') + s;
			
			toMemId = jsonObj.memChatMemVO.memId;
			
			//可能要在這裡ID NAME轉換
			//var yourName = jsonObj.memChatMemVO.memId;
			var yourName = "";
			for (var i = 0; i < toMemArray.length; i++) {
	            //console.log(toMemArray[i].memId);
	            if(toMemArray[i].memId == jsonObj.memChatMemVO.memId ){
	            	yourName = toMemArray[i].memName;
	            	break;
	            }
	        }
			
			//var yourMessage = jsonObj.memChatContent;

			var yourChatString = document.createElement('li');
			yourChatString.className = "left clearfix";	
			
			var chatSpan = document.createElement('span');
			chatSpan.className = "chat-img pull-left";
			
			yourChatString.appendChild(chatSpan);
			
			var yourPic = document.createElement('img');
			yourPic.className = "img-circle";
			yourPic.alt ="User Avatar";
			
			var memPicPoint = "/mem/DBGifReader5?memId=";
			var memPicUrl = "http://" + window.location.host + webCtx + memPicPoint;
			yourPic.src = memPicUrl + jsonObj.memChatMemVO.memId;
			
			chatSpan.appendChild(yourPic);
			
			
			var chatBody = document.createElement('div');
			chatBody.className = "chat-body clearfix";
			yourChatString.appendChild(chatBody);
			
			var header = document.createElement('div');
			header.className = "header";
			
			chatBody.appendChild(header);
			
			var yourNameFrame = document.createElement('strong');
			yourNameFrame.className = "primary-font";
			//yourNameFrame.innerText = yourName;
			var yourNameTextNode = document.createTextNode(yourName);
			
			header.appendChild(yourNameFrame);
			yourNameFrame.appendChild(yourNameTextNode);
			
			var yourChatTimeFrame = document.createElement('small');
			yourChatTimeFrame.className = "pull-right text-muted";
			//yourChatTimeFrame.innerText = yourChatTime;
			
			var yourChatTimeTextNode = document.createTextNode(yourChatTime);
			
			header.appendChild(yourChatTimeFrame);
			
			var chatTimeIcon = document.createElement('span');
			chatTimeIcon.className = "glyphicon glyphicon-time";
			
			yourChatTimeFrame.appendChild(chatTimeIcon);
			
			yourChatTimeFrame.appendChild(yourChatTimeTextNode);
			
			var yourMessage = document.createElement('p');
			//yourMessage.innerText = jsonObj.memChatContent;
			var yourMessageTextNode = document.createTextNode(jsonObj.memChatContent);
			
			chatBody.appendChild(yourMessage);
			yourMessage.appendChild(yourMessageTextNode);
			
// 				<li class='left clearfix'>
// 					<span class='chat-img pull-left'>
// 						<img src='yourPic' alt='User Avatar' class='img-circle'/>
// 					</span>
// 					<div class='chat-body clearfix'>
// 						<div class='header'>
// 							<strong class='primary-font'>
// 								yourName
// 							</strong>
// 							<small class='pull-right text-muted'>
// 								<span class='glyphicon glyphicon-time'>
// 								</span>
// 									yourChatTime
// 							</small>
// 						</div>
// 						<p>
// 							yourMessage
// 						</p>
// 					</div>
// 				</li>

			//$(".chat").append(yourChatString);
			
			
			var chatObj = document.getElementsByClassName("chat")
			chatObj[0].append(yourChatString);
			
			messagesArea.scrollTop = messagesArea.scrollHeight;
			
			$("span.glyphicon-comment").text(" "+ yourName);
			
		};

// 		webSocket.onclose = function(event) {
// 			updateStatus("WebSocket 已離線");
// 		};
	}

	function sendMessage() {
		var inputMessage = document.getElementById("message");
		var message = inputMessage.value.trim();

		if (message === "") {
			alert("訊息請勿空白!");
			inputMessage.focus();
		} else {
			/* 傳送自己的訊息 */
			var action = "chat";

   			var NowDate = new Date();
   			var year = NowDate.getFullYear(); 
  			var month = NowDate.getMonth();
   			var date = NowDate.getDate();
   			var h = NowDate.getHours();
   			var m = NowDate.getMinutes();
   			var s = NowDate.getSeconds();
   			var mSec = NowDate.getMilliseconds();

   			var myChatTime = year + "-" + ((month+1)<10 ? '0' : '') + (month+1) +"-"+  (date<10 ? '0' : '') + date + " " + (h<10 ? '0' : '') + h +':' + (m<10 ? '0' : '') + m + ":" + (s<10 ? '0' : '') + s + "." + Math.round(mSec/100);

			//console.log(myChatTime);
			
			PartnerMsg.memChatMemVO.memId = fromMemId;
			PartnerMsg.memChatToMemVO.memId = toMemId;
			PartnerMsg.action = action;
			PartnerMsg.memChatContent = message;
			PartnerMsg.memChatDate = myChatTime;
			
			webSocket.send(JSON.stringify(PartnerMsg));

			inputMessage.value = "";
			inputMessage.focus();

			//var myChatTime = "13 mins ago";
			
			var NowDate = new Date();
			var h = NowDate.getHours();
			var m = NowDate.getMinutes();
			var s = NowDate.getSeconds();
			var myChatTime = (h<10 ? '0' : '') + h +':' + (m<10 ? '0' : '') + m + ":" + (s<10 ? '0' : '') + s;
			
			var myName = "<%=memVO.getMemName()%>"; //從VO取得
			
			var myChatString = document.createElement('li');
			myChatString.className = "right clearfix";	
			
			var chatSpan = document.createElement('span');
			chatSpan.className = "chat-img pull-right";
			
			myChatString.appendChild(chatSpan);
			
			var myPic = document.createElement('img');
			myPic.className = "img-circle";
			myPic.alt ="User Avatar";
			
			var memPicPoint = "/mem/DBGifReader5?memId=";
			var memPicUrl = "http://" + window.location.host + webCtx + memPicPoint;
			myPic.src = memPicUrl + fromMemId;
			
			chatSpan.appendChild(myPic);
			
			var chatBody = document.createElement('div');
			chatBody.className = "chat-body clearfix";
			myChatString.appendChild(chatBody);
			
			var header = document.createElement('div');
			header.className = "header";
			
			chatBody.appendChild(header);
			
			var myChatTimeFrame = document.createElement('small');
			myChatTimeFrame.className = "text-muted";

			var myChatTimeTextNode = document.createTextNode(myChatTime);
			
			header.appendChild(myChatTimeFrame);
			
			var chatTimeIcon = document.createElement('span');
			chatTimeIcon.className = "glyphicon glyphicon-time";
			
			myChatTimeFrame.appendChild(chatTimeIcon);
			
			myChatTimeFrame.appendChild(myChatTimeTextNode);
						
			var myNameFrame = document.createElement('strong');
			myNameFrame.className = "pull-right primary-font";

			var myNameTextNode = document.createTextNode(myName);			
	
			header.appendChild(myNameFrame);
			myNameFrame.appendChild(myNameTextNode);
						
			var myMessage = document.createElement('p');
			myMessage.className = "pull-right";
			
			var myMessageTextNode = document.createTextNode(message);
			
			chatBody.appendChild(myMessage);
			myMessage.appendChild(myMessageTextNode);	
	
// 				<li class='right clearfix'>
// 					<span class='chat-img pull-right'>
// 						<img src= myPic alt='User Avatar' class='img-circle' />
// 					</span>
// 					<div class='chat-body clearfix'>
// 						<div class='header'>
// 							<small class='text-muted'>
// 								<span class='glyphicon glyphicon-time'></span>"
// 								myChatTime
// 							</small>
// 							<strong class='pull-right primary-font'>"
// 							myName
// 							</strong>
// 						</div>
// 						<p class='pull-right'>"
// 						myMessage
// 						</p>
// 					</div>
// 				</li>

			var chatObj = document.getElementsByClassName("chat")
			chatObj[0].append(myChatString);
			
			messagesArea.scrollTop = messagesArea.scrollHeight;

		}
	}

	function disconnect() {
		webSocket.close();
// 		document.getElementById('sendMessage').disabled = true;
// 		document.getElementById('connect').disabled = false;
// 		document.getElementById('disconnect').disabled = true;
	}

// 	function updateStatus(newStatus) {
// 		statusOutput.innerHTML = newStatus;
// 	}
</script>

</html>

<script>
	//利用json建立右邊列表
	function createMemCardList(aObj){
		var  memCardList = document.getElementById('memCardList');
	

		var card = document.createElement('div');
		card.className = "card memCard";
		
		memCardList.appendChild(card);
		
		var picMidDiv = document.createElement('div');
		picMidDiv.style.verticalAlign = "middle";
		picMidDiv.className = "memProfile pull-left";
		
		card.appendChild(picMidDiv);
		
		
		var picMidSpan = document.createElement('span');
		picMidSpan.style.display = "inline-block";
		picMidSpan.style.height = "100%";	
		picMidSpan.style.verticalAlign = "middle";
		picMidSpan.style.width = "1px";
		picMidSpan.style.marginLeft = "-10px";
		
		picMidDiv.appendChild(picMidSpan);
		
		
		var memPic = document.createElement('img');
		memPic.style.display = "inline-block";
		memPic.style.maxHeight = "100%";
		memPic.style.verticalAlign = "middle";
		memPic.className = "img-responsive";
		
		
		var memPicPoint = "/mem/DBGifReader5?memId=";
		var memPicUrl = "http://" + window.location.host + webCtx + memPicPoint;
		memPic.src = memPicUrl + aObj.memId;
		
		picMidDiv.appendChild(memPic);
		
		var memIntro = document.createElement('div');
		memIntro.className = "card-block memIntro";
		
		card.appendChild(memIntro);
		
		var memName = document.createElement('h4');
		memName.className = "card-title";
		//memName.innerText = aObj.memName;
		var memNameTextNode = document.createTextNode(aObj.memName);
		
		
		memIntro.appendChild(memName);
		memName.appendChild(memNameTextNode);
		
		var memIntroP = document.createElement('p');
		memIntroP.className = "card-text";
		//memIntroP.innerText = aObj.memIntro;
		
		var memIntroTextNode = document.createTextNode(aObj.memIntro);
		
		
		memIntro.appendChild(memIntroP);
		memIntroP.appendChild(memIntroTextNode);
		
		var memBtn = document.createElement('a');
		memBtn.href = "#";
		memBtn.className = "btn btn-primary memBtn";
		//memBtn.innerText = "跟你聊一聊";
		var memBtnTextNode = document.createTextNode("跟你聊一聊");
		memBtn.addEventListener("click", openChatWindow);
		memBtn.dataset.id = aObj.memId;
		
		memIntro.appendChild(memBtn);
		memBtn.appendChild(memBtnTextNode);
		
// 	<div class="card memCard">
// 		<div style="vertical-align: middle" class="memProfile pull-left">
// 			<span style="display:inline-block;height:100%;vertical-align:middle;width:1px;margin-left:-10px"></span>
// 			<img style="display:inline-block;max-height:100%;vertical-align:middle" class="img-responsive" src='data:image/jpeg;base64,${memVO.bs64}' alt="Card image cap">
// 		</div>
	
// 		<div class="card-block memIntro">
//         <h4 class="card-title">${memVO.memName}</h4>
//         <p class="card-text">${memVO.memIntro}</p>
//         <a href="#" class="btn btn-primary memBtn">跟你聊一聊</a>		            
//     	</div>
// </div>

	}
	
</script>

<!-- map相關 -->
<script>
	var map;
	var fromMarker;
	var toMarkers = [];
	var infowindows = [];
	var contentStrings = [];	
	
	function initialize(){
		if(navigator.geolocation){
			//alert('Geolocation support!');
			navigator.geolocation.getCurrentPosition(succCallback,errorCallback,{
				enableHighAccuracy:false,
				timeout:100000,
				maximumAge:0
			});
		}else{
			alert('Geolocation NO support!');
		}
	}
	  
	function errorCallback(err) {		
		var fackPos = {
			"coords" : {"latitude" : "<%=24.967880%>",    
						"longitude" : "<%=121.191602%>"   
						}
		};
		succCallback(fackPos);
	}
	
	function succCallback(e) {
		memLat = e.coords.latitude;
		memLng = e.coords.longitude; 
				
		//console.log('緯度：'+memLat+' 經度：'+memLng);
		/* 有座標 call ajax */
		getListForUploader();
		
		
		var latlng = new google.maps.LatLng(memLat,memLng);

		map = new google.maps.Map(document.getElementById('map-canvas'),{
			center:latlng,
			zoom:18,
			mapTypeId:google.maps.MapTypeId.ROADMAP
		});
		
		fromMarker = new google.maps.Marker({
			map: map,
			position: latlng,
			icon: 'img/fromMem.png',
			title: '我在這裡!'
		});
		fromMarker.addListener('click', toggleBounce);
		
		clearMarkers();
		
 	}

	function toggleBounce() {
	  if (this.getAnimation() !== null) {
		  this.setAnimation(null);
	  } else {
		  this.setAnimation(google.maps.Animation.BOUNCE);
	  }
	}
	
	
	function addMarkerWithTimeout(aObj, timeout) {
		
		var latlng = new google.maps.LatLng(aObj.memLat,aObj.memLng);
		var genderIcon;
		if(aObj.memGender == "f"){
			genderIcon = 'img/toMemFemale.png';
		}
		else{
			genderIcon = 'img/toMemMale.png';
		}
	
		var markTitle = aObj.memName + " (距離:" + aObj.memDis + "公尺)";
		
		
		/* Mark的更多資訊 */
		var memPicPoint = "/mem/DBGifReader5?memId=";
		var memPicUrl = "http://" + window.location.host + webCtx + memPicPoint;
		
		var contentString = document.createElement('div');
		contentString.className = "thumbnail";
	
		var profile = document.createElement('img');
		profile.src = memPicUrl + aObj.memId ;
		profile.className = "memProfile2 img-circle";
	
		contentString.appendChild(profile);
	
	
		var caption = document.createElement('div');
		caption.className = "caption";
	
		contentString.appendChild(caption);
	
	
		var memName = document.createElement('h2');
		//memName.innerText = aObj.memName;
		
		var memNameTextNode = document.createTextNode(aObj.memName);
	
		caption.appendChild(memName);
		memName.appendChild(memNameTextNode);
	
		var memIntro = document.createElement('p');
		//memIntro.innerText = aObj.memIntro;
		
		var memIntroTextNode = document.createTextNode(aObj.memIntro);
	
		caption.appendChild(memIntro);
		memIntro.appendChild(memIntroTextNode);
	
		var btnFrame = document.createElement('p');
	
		caption.appendChild(btnFrame);
	
		var btn = document.createElement('a');
		//btn.innerText = '跟你聊一聊';
		var btnTextNode = document.createTextNode('跟你聊一聊');
		btn.addEventListener("click", openChatWindow);
		btn.dataset.id = aObj.memId;
		
		btn.href = "#";
		btn.className = "btn btn-primary memBtn";
		
		btnFrame.appendChild(btn);
		btn.appendChild(btnTextNode);
		
		/* 讓contentString生命週期久一點 */
		contentStrings.push(contentString);
		
		// 	var contentString = 
								
		// 		'<div class="thumbnail">'
		// 							+'<img src="' + 'img/profile_user.jpg' + '" class="memProfile2 img-circle" alt="">'
		// 							+'<div class="caption">'
		// 								+'<h2>' + '詹姆斯' + '</h2>'
		// 								+'<p>' + '我崇尚自然，希望靠這次旅行，能認識更多的人' + '</p>'
		// 								+'<p>'
		// 									+'<a href="#" class="btn btn-info">' + '跟你聊一聊' + '</a>'
		// 								+'</p>'
		// 							+'</div>'
		// 						+'</div>';  
	
		var infowindow = new google.maps.InfoWindow({
			    content: contentString
		});	
		
		/* 讓infowindow生命週期久一點 */
		infowindows.push(infowindow);
		
		window.setTimeout(
	
		function() {			
		 	var tmpMark =
				new google.maps.Marker({
			      position: latlng,
			      map: map,
			      icon: genderIcon,
			      animation: google.maps.Animation.DROP,
			      title: markTitle
				});	
				tmpMark.addListener('click', toggleBounce);
				
				tmpMark.addListener('click', function() {
					    infowindow.open(map, tmpMark);
				});
				
				toMarkers.push(tmpMark);
				
		}, timeout);		

	}
	
	function clearMarkers() {
		  for (var i = 0; i < toMarkers.length; i++) {
			  toMarkers[i].setMap(null);
		  }
		  toMarkers = [];
	}
	
	
	google.maps.event.addDomListener(window, 'load', initialize);
	//window.addEventListener('load',initialize,false);		

</script>

<!-- 聊天視窗的隱藏跟顯示  jQuery -->
<script>
	$(document).ready(function(){
	  $(".panel-heading").click(function(){
		  $("#messagesArea").toggle();
		  $(".panel-footer").toggle();
	  });
	  
	  $("#closeChatWindow").click(function(){
		  $("#chatWindow").hide(300);
	  });
	  	  
	});
</script>

<script>

function openChatWindow(e){
	
	$("#chatWindow").show();
	$("#messagesArea").show();
	$(".panel-footer").show();
	
// 	console.log("ID is "+e.target);
// 	console.log("ID is "+e.target.nodeName);
// 	console.log("ID is "+e.target.dataset.id);

	/* 偷傳自己的訊息 */
	var action = "bindMemIdWithSession";

	PartnerMsg.memChatMemVO.memId = fromMemId;
	//PartnerMsg.memChatToMemVO.memId = toMemId;
	toMemId = e.target.dataset.id;
	PartnerMsg.memChatToMemVO.memId = e.target.dataset.id;
	PartnerMsg.action = action;

	webSocket.send(JSON.stringify(PartnerMsg));
	
	
	var yourName = " Chat";
	for (var i = 0; i < toMemArray.length; i++) {
        //console.log(toMemArray[i].memId);
        if(toMemArray[i].memId == toMemId ){
        	yourName = toMemArray[i].memName;
        	break;
        }
    }
	
	$("span.glyphicon-comment").text(" "+ yourName);
}

window.addEventListener('load',connect,false);	

</script>


