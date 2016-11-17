<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Chat Room</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link rel="stylesheet" href="css/styles.css" type="text/css"/>
    </head>
    
    <body onload="connect();" onunload="disconnect();">
        <h1> Chat Room </h1>
	    <h3 id="statusOutput" class="statusOutput"></h3>
        <textarea id="messagesArea" class="panel message-area" readonly ></textarea>
        <div class="panel input-area">
            <input id="userName" class="text-field" type="text" placeholder="使用者名稱"/>
            <input id="message"  class="text-field" type="text" placeholder="訊息" onkeydown="if (event.keyCode == 13) sendMessage();"/>
            <input type="submit" id="sendMessage" class="button" value="送出" onclick="sendMessage();"/>
		    <input type="button" id="connect"     class="button" value="連線" onclick="connect();"/>
		    <input type="button" id="disconnect"  class="button" value="離線" onclick="disconnect();"/>
	    </div>
    </body>
    
<script>
    
    var MyPoint = "/MyEchoServer";
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));    
    var endPointURL = "ws://localhost:8081/DDD_web/android/live2/MsgCenter";
	var statusOutput = document.getElementById("statusOutput");
	var webSocket;

    var PartnerMsg = {
    		"action":"", // uploadTokenId, removeTokenId, bindMemIdWithSession, chat, 
    		"memChatContent":"Empty Message",
    		"memChatDate":"2016-11-17 12:07:51.1", //2016-11-17 12:07:51.1
    		"memChatMemVO":{"memId":"","memOrds":[]},
    		"memChatToMemVO":{"memId":"","memOrds":[]},
    		"tokenId":"aoisjcpoaishcp" // tokenId
    	};	

	function connect() {
		// 建立 websocket 物件
		webSocket = new WebSocket(endPointURL);
		
		webSocket.onopen = function(event) {
			updateStatus("WebSocket 成功連線");
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;
			
            /* 偷傳自己的訊息 */	
			var fromMemId = "10000002";
            var toMemId = "10000001";
            var action ="bindMemIdWithSession";

            PartnerMsg.memChatMemVO.memId = fromMemId;
            PartnerMsg.memChatToMemVO.memId = toMemId;
            PartnerMsg.action = action; 

            webSocket.send(JSON.stringify(PartnerMsg));	          
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
            /* 偷傳自己的訊息 */	
			var fromMemId = "10000002";
            var toMemId = "10000001";
            var action ="chat";

            PartnerMsg.memChatMemVO.memId = fromMemId;
            PartnerMsg.memChatToMemVO.memId = toMemId;
            PartnerMsg.action = action;
            PartnerMsg.memChatContent = message;

            webSocket.send(JSON.stringify(PartnerMsg));     
	        
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
