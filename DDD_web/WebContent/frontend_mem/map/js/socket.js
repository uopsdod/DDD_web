
    var MyPoint = "/MyEchoServer/nina/308";
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
    
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
		};

		webSocket.onmessage = function(event) {
			
	        var jsonObj = JSON.parse(event.data);
//	        alert(jsonObj[0] + " : " +jsonObj[1]);
	        var one = document.getElementById(jsonObj[0]);
	       
	        if(one!=null){//版面上有這個id才更新降價
		        var price = one.childNodes[0];
	//	        alert(price.innerText);
		        price.style="font-size:30px;margin-top:10px; text-decoration:line-through";
		        
		        var priceNew = document.createElement("div");
		        priceNew.style="font-size:30px;margin-top:10px;";
		        priceNew.innerText=jsonObj[1];
		        one.appendChild(priceNew);
		        setInterval(function(){price.remove(0);}, 3000);
	        
		       // $(this).parent(".ex").hide("slow");
//		        $("#"+jsonObj[0]+":first-child").hide("slow",function(){ this.remove(); });
		    }//if
		};

		webSocket.onclose = function(event) {
			updateStatus("WebSocket 已離線");
		};
	}
	
	
	var inputUserName = document.getElementById("userName");
	inputUserName.focus();
	
	function sendMessage() {
	    var userName = inputUserName.value.trim();
	    if (userName === ""){
	        alert ("使用者名稱請勿空白!");
	        inputUserName.focus();	
			return;
	    }
	    
	    var inputMessage = document.getElementById("message");
	    var message = inputMessage.value.trim();
	    
	    if (message === ""){
	        alert ("訊息請勿空白!");
	        inputMessage.focus();	
	    }else{
	        var jsonObj = {"userName" : userName, "message" : message};
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
	
	alert(path);