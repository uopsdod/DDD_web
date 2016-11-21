<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/images/index.jpg">    
</head>

<body onload="connect();" onunload="disconnect();">
    <header id="top_header">
        <div class="col-md-5 col-md-offset-1 ">
            <img src="<%=request.getContextPath()%>/frontend_mem/images/4.png" id="LogoImg">
        </div>
        <div class="col-md-5 col-md-offset-1 ">
        
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

            <div class="">


				<div class="col-xs-12 col-sm-12">
					
                    <div class="card memCard">
     					<div class="memProfile">
     	                   <img class="img-circle pull-left img-responsive" src="img/profile_user.jpg" alt="Card image cap">
     					</div>

                        <div class="card-block">
	                        <h4 class="card-title">詹姆斯</h4>
	                        <p class="card-text">我崇尚自然，希望靠這次旅行，能認識更多的人</p>
	                        <a href="#" class="btn btn-primary memBtn ">跟你聊一聊</a>
                      	</div>
                    </div>					
					
					

                    <div class="card memCard">
     					<div class="memProfile">
     	                   <img class="img-circle pull-left img-responsive" src="img/profile_user.jpg" alt="Card image cap">
     					</div>

                        <div class="card-block">
	                        <h4 class="card-title">詹姆斯</h4>
	                        <p class="card-text">喜歡旅行、認識新朋友，夢想是有一天能走片世界各地</p>
	                        <a href="#" class="btn btn-primary memBtn ">跟你聊一聊</a>
                      	</div>
                    </div>	
                    
                
                    <div class="card memCard">
     					<div class="memProfile">
     	                   <img class="img-circle pull-left img-responsive" src="img/profile_user.jpg" alt="Card image cap">
     					</div>

                        <div class="card-block">
	                        <h4 class="card-title">詹姆斯</h4>
	                        <p class="card-text">我是川普女孩</p>
	                        <a href="#" class="btn btn-primary memBtn ">跟你聊一聊</a>
                      	</div>
                    </div>	                 
                    
                    
				</div>

            </div> <!--  -->

    	</div> <!-- myList -->

    </section>
    
	<!-- 聊天的div -->
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-5">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<!-- 聊天視窗標題 -->
						<span class="glyphicon glyphicon-comment"></span> Chat
						<div class="btn-group pull-right">
							<button type="button"
								class="btn btn-default btn-xs dropdown-toggle"
								data-toggle="dropdown">
								<span class="glyphicon glyphicon-chevron-down"></span>
							</button>
							<!-- 右上角的選單 -->
							<ul class="dropdown-menu slidedown">
								<li><a href="http://www.jquery2dotnet.com"> <span
										class="glyphicon glyphicon-refresh"></span> Refresh
								</a></li>
								<li><a href="http://www.jquery2dotnet.com"> <span
										class="glyphicon glyphicon-ok-sign"></span> Available
								</a></li>
								<li><a href="http://www.jquery2dotnet.com"> <span
										class="glyphicon glyphicon-remove"></span> Busy
								</a></li>
								<li><a href="http://www.jquery2dotnet.com"> <span
										class="glyphicon glyphicon-time"></span> Away
								</a></li>
								<li class="divider"></li>
								<li><a href="http://www.jquery2dotnet.com"> <span
										class="glyphicon glyphicon-off"></span> Sign Out
								</a></li>
							</ul>
						</div>
					</div>
					<!--  聊天內容視窗 -->
					<div id="messagesArea" class="panel-body">
						<ul class="chat">



						</ul>
					</div>


					<div class="panel-footer">
						<h5 id="userName"></h5>
						<input type="button" id="connect" class="btn btn-success btn-sm"
							value="連線" onclick="connect();" /> <input type="button"
							id="disconnect" class="btn btn-warning btn-sm" value="離線"
							onclick="disconnect();" />
					</div>

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
		</div>
	</div>


	<h3 id="statusOutput" class="statusOutput"></h3>

</body>
<!-- 聊天相關 -->
<script>
	var MyPoint = "/android/live2/MsgCenter";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

	var statusOutput = document.getElementById("statusOutput");

	var webSocket;

	/* 使用者資訊 */
	var toMemId = "10000001";
	var fromMemId = "10000002";
	var yourPic = "img/profile_user.jpg";
	var myPic = "img/profile_user2.jpg";

	document.getElementById("userName").innerHTML = fromMemId;
	var messagesArea = document.getElementById("messagesArea");

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
			updateStatus("WebSocket 成功連線");
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;

			/* 偷傳自己的訊息 */
			var action = "bindMemIdWithSession";

			PartnerMsg.memChatMemVO.memId = fromMemId;
			PartnerMsg.memChatToMemVO.memId = toMemId;
			PartnerMsg.action = action;

			webSocket.send(JSON.stringify(PartnerMsg));
		};

		/* 接收訊息 */
		webSocket.onmessage = function(event) {
			var jsonObj = JSON.parse(event.data);

			//console.log("Debug: "+jsonObj.memChatContent);

			var yourChatTime = "13 mins ago";
			var yourName = jsonObj.memChatMemVO.memId;
			var yourMessage = jsonObj.memChatContent;

			var yourChatString = "<li class='left clearfix'><span class='chat-img pull-left'><img src='" +  yourPic
                +"' alt='User Avatar' class='img-circle'/></span><div class='chat-body clearfix'>"
					+ "<div class='header'><strong class='primary-font'>"
					+ yourName
					+ "</strong><small class='pull-right text-muted'><span class='glyphicon glyphicon-time'></span> "
					+ yourChatTime
					+ "</small></div><p>"
					+ yourMessage
					+ "</p></div></li>";

			$(".chat").append(yourChatString);
			messagesArea.scrollTop = messagesArea.scrollHeight;

		};

		webSocket.onclose = function(event) {
			updateStatus("WebSocket 已離線");
		};
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

			PartnerMsg.memChatMemVO.memId = fromMemId;
			PartnerMsg.memChatToMemVO.memId = toMemId;
			PartnerMsg.action = action;
			PartnerMsg.memChatContent = message;

			webSocket.send(JSON.stringify(PartnerMsg));

			inputMessage.value = "";
			inputMessage.focus();

			var myChatTime = "13 mins ago";
			var myName = fromMemId;
			var myMessage = message;

			var myChatString = "<li class='right clearfix'><span class='chat-img pull-right'><img src='" + myPic
                + "' alt='User Avatar' class='img-circle' /></span><div class='chat-body clearfix'>"
					+ "<div class='header'><small class='text-muted'><span class='glyphicon glyphicon-time'></span>"
					+ myChatTime
					+ "</small><strong class='pull-right primary-font'>"
					+ myName
					+ "</strong></div><p class='pull-right'>"
					+ myMessage + "</p></div></li>";

			$(".chat").append(myChatString);
			messagesArea.scrollTop = messagesArea.scrollHeight;

		}
	}

	function disconnect() {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
		document.getElementById('connect').disabled = false;
		document.getElementById('disconnect').disabled = true;
	}

	function updateStatus(newStatus) {
		statusOutput.innerHTML = newStatus;
	}
</script>

</html>

<!-- map相關 -->
<script>
	var map;
	var fromMarker;
	var toMarkers = [];

	var toMembers= {};

	toMembers['AA101'] = {
		key: 'AA101',	
		lat: 24.967880,
		lng: 121.191
	};

	toMembers['AA102'] = {
		key: 'AA102',
		lat: 24.967880,
		lng: 121.193
	};
	
	toMembers['AA103'] = {
		key: 'AA103',
		lat: 24.967880,
		lng: 121.1945
	};

	toMembers['AA104'] = {
		key: 'AA104',
		lat: 24.967880,
		lng: 121.194
	};
	
	toMembers['AA105'] = {
		key: 'AA105',
		lat: 24.967880,
		lng: 121.192
	};	
	
	toMembers['AA106'] = {
		key: '吳神',	
		lat: 24.967745,
		lng: 121.192009
	};	
		
	/* 更多資訊 */	
	  var contentString = '<div class="thumbnail">'
							+'<img src="' + 'img/profile_user.jpg' + '" class="memProfile2 img-circle" alt="">'
							+'<div class="caption">'
								+'<h2>' + '詹姆斯' + '</h2>'
								+'<p>' + '我崇尚自然，希望靠這次旅行，能認識更多的人' + '</p>'
								+'<p>'
									+'<a href="#" class="btn btn-info">' + '跟你聊一聊' + '</a>'
								+'</p>'
							+'</div>'
						+'</div>';   

	  var infowindow = new google.maps.InfoWindow({
	    content: contentString
	  });
	  	
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
		var lat = e.coords.latitude;
		var lng = e.coords.longitude; 
				
		console.log('緯度：'+lat+' 經度：'+lng);
		
		var latlng = new google.maps.LatLng(lat,lng);

		map = new google.maps.Map(document.getElementById('map-canvas'),{
			center:latlng,
			zoom:18,
			mapTypeId:google.maps.MapTypeId.ROADMAP
		});
		
		fromMarker = new google.maps.Marker({
			map: map,
			position: latlng,
			icon: 'img/fromMem.png',
			title: '這不是我家'
		});
		fromMarker.addListener('click', toggleBounce);
		
		clearMarkers();

		for (var key in toMembers) {
		    addMarkerWithTimeout(toMembers[key], 200);
		}
		
 	}

	function toggleBounce() {
	  if (this.getAnimation() !== null) {
		  this.setAnimation(null);
	  } else {
		  this.setAnimation(google.maps.Animation.BOUNCE);
	  }
	}
	
	function addMarkerWithTimeout(aObj, timeout) {
		
	var latlng = new google.maps.LatLng(aObj.lat,aObj.lng);
	
		window.setTimeout(
	
		function() {
		 	var tmpMark =
				new google.maps.Marker({
			      position: latlng,
			      map: map,
			      icon: 'img/toMemFemale.png',
			      animation: google.maps.Animation.DROP,
			      title: aObj.key
				});	
				tmpMark.addListener('click', toggleBounce);
				toMarkers.push(tmpMark);
				
				tmpMark.addListener('click', function() {
					    infowindow.open(map, tmpMark);
				});
				
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