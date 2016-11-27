
    var MyPoint_B = "/SocketOfWish/pika/878";
    var host_B = window.location.host;
    
    var path_B = window.location.pathname;
    
    var webCtx_B = path_B.substring(0, path_B.indexOf('/', 1));
    var endPointURL_B = "ws://" + window.location.host + webCtx_B + MyPoint_B;

    
	
	var webSocket; 
	
	function connect_B() {
		webSocket = new WebSocket(endPointURL_B);

		webSocket.onopen = function(event) {
			console.log("連線成功");
		};
		webSocket.onmessage = function(event) {
			
			 var countName = JSON.parse(event.data);
			 var aWishRoomId =countName.aWishRoomId;
			 var productCount =countName.productCount;
			 
			 
			 if(document.getElementById(aWishRoomId+"_forCount")!=null){
				 document.getElementById(aWishRoomId+"_forCount").innerHTML=productCount; 
			 }
			 
		}
		
	}
	
	
	function sendMessage() {
	   
	}

	
	function disconnect_B () {
		webSocket.close();
		
	}

	
	function updateStatus(newStatus) {
		
	}
	
