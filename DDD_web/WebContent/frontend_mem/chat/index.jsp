<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.google.gson.Gson, com.google.gson.GsonBuilder" %>

<!DOCTYPE html>
<html lang="">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Chat Room</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <link rel="stylesheet" href="css/0_main.css">
  <script src="https://code.jquery.com/jquery.js"></script>
  <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

  <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
      <![endif]-->
</head>
<body onload="connect();" onunload="disconnect();">
  <h1 class="text-center">哈囉 ! Chat Room ～哩後！</h1>

<!-- 聊天的div -->
<div class="container">
    <div class="row">
        <div class="col-xs-12 col-sm-10">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <!-- 聊天視窗標題 -->
                    <span class="glyphicon glyphicon-comment"></span> Chat
                    <div class="btn-group pull-right">
                        <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                            <span class="glyphicon glyphicon-chevron-down"></span>
                        </button>
                        <!-- 右上角的選單 --> 
                        <ul class="dropdown-menu slidedown">
                            <li>
                              <a href="http://www.jquery2dotnet.com">
                                <span class="glyphicon glyphicon-refresh"></span>
                                Refresh
                              </a>
                            </li>
                            <li>
                              <a href="http://www.jquery2dotnet.com">
                                <span class="glyphicon glyphicon-ok-sign"></span>
                                Available
                              </a>
                            </li>
                            <li>
                              <a href="http://www.jquery2dotnet.com">
                                <span class="glyphicon glyphicon-remove"></span>
                                Busy
                              </a>
                            </li>
                            <li>
                              <a href="http://www.jquery2dotnet.com">
                                <span class="glyphicon glyphicon-time"></span>
                                Away
                              </a>
                            </li>
                            <li class="divider">
                            </li>
                            <li>
                              <a href="http://www.jquery2dotnet.com">
                                <span class="glyphicon glyphicon-off"></span>
                                Sign Out
                              </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <!--  聊天內容視窗 -->
                <div class="panel-body">
                    <ul class="chat">

                      

                    </ul>
                </div>


                <div class="panel-footer">
                    <input id="userName" class="text-field" type="text" placeholder="使用者名稱"/>
                    <input type="button" id="connect"     class="btn btn-success btn-sm" value="連線" onclick="connect();"/>
                    <input type="button" id="disconnect"  class="btn btn-warning btn-sm" value="離線" onclick="disconnect();"/>
                </div>

                <div class="panel-footer">
                    <div class="input-group">
                        <input id="message"  class="form-control input-sm" type="text" placeholder="Type your message here..." onkeydown="if (event.keyCode == 13) sendMessage();"/>
                        <span class="input-group-btn">
                            <input type="submit" id="sendMessage" class="btn btn-primary btn-sm" value="送出" onclick="sendMessage();"/>
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<h3 id="statusOutput" class="statusOutput"></h3>

</body>

<script>
    
    var MyPoint = "/MyEchoServer";
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));    
    var endPointURL = "ws://localhost:8081/DDD_web/android/live2/MsgCenter";
	var statusOutput = document.getElementById("statusOutput");
	var webSocket;
	
	function connect() {
		// 建立 websocket 物件
		webSocket = new WebSocket(endPointURL);
		
		webSocket.onopen = function(event) {
			updateStatus("WebSocket 成功連線");
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;
			
            /* 偷傳自己的訊息 */
            var fromMemId = "10000001";
            var toMemId = "10000002";
            var action ="bindMemIdWithSession";
            var message = "welcome";
            
            var jsonObj = {"fromMemId" : fromMemId, "toMemId" : toMemId, "action" : action, "message" : message};
            webSocket.send(JSON.stringify(jsonObj));			
		};

		webSocket.onmessage = function(event) {
			var messagesArea = document.getElementById("messagesArea");
	        var jsonObj = JSON.parse(event.data);
	        var message = jsonObj.userName + ": " + jsonObj.message + "\r\n";
	        messagesArea.value = messagesArea.value + message;
	        messagesArea.scrollTop = messagesArea.scrollHeight;
		};

		webSocket.onclose = function(event) {
			updateStatus("WebSocket 已離線");
		};
	}
	
	
	var inputUserName = document.getElementById("userName");
	inputUserName.focus();
	
	function sendMessage() {
	    var userName = inputUserName.value.trim();
// 	    if (userName === ""){
// 	        alert ("使用者名稱請勿空白!");
// 	        inputUserName.focus();	
// 			return;
// 	    }
	    
	    var inputMessage = document.getElementById("message");
	    var message = inputMessage.value.trim();
	    
	    if (message === ""){
	        alert ("訊息請勿空白!");
	        inputMessage.focus();	
	    }else{
	        
            var fromMemId = "10000001";
            var toMemId = "10000002";
            var action ="chat";
            
            var jsonObj = {"fromMemId" : fromMemId, "toMemId" : toMemId, "action" : action, "message" : message};
            webSocket.send(JSON.stringify(jsonObj));	        
	        
	        inputMessage.value = "";
	        inputMessage.focus();
	    }
	}

	
	function disconnect () {
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


