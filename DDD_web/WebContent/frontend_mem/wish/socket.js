
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
			
		};

		webSocket.onmessage = function(event) {
			
		 var allBag = JSON.parse(event.data);	
		 var PriceBox = allBag.Bag;
		 if(PriceBox!=null){
		 
			 for(var i =0;i<PriceBox.length;i++){
		        var jsonObj = PriceBox[i];
	//	        alert(jsonObj[0] + " : " +jsonObj[1]);
		        
		        
		        var one = roomMap.get(jsonObj[0]);
		        
		       
		        if(one!=null){//版面上有這個id才更新降價
		        	
		       		if(one.childNodes[0].childNodes[0]!=jsonObj[1]){	//價錢有變才換價錢
		       		
		       			$(one.childNodes[0]).fadeOut("slow",showPrice.bind(null, one,jsonObj[1]));    	
		       			$(one.childNodes[0]).fadeIn("slow");
		       		
		       		}
			    }//if
			 }//for each	
		 }//if
		 };//onmessage

		webSocket.onclose = function(event) {
	
		};
	}
	function showPrice(one,box){
		
		one.childNodes[0].childNodes[0].remove();	 
    	var price = document.createTextNode(box);
    	one.childNodes[0].appendChild(price);
		
	}
	
	function sendMessage() {
	   
	}

	
	function disconnect () {
		webSocket.close();
		
	}

	
	function updateStatus(newStatus) {
		statusOutput.innerHTML = newStatus;
	}
	
