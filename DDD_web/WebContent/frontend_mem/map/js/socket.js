
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
			
	        var jsonObj = JSON.parse(event.data);
//	        alert(jsonObj[0] + " : " +jsonObj[1]);
	        var one = document.getElementById(jsonObj[0]);
	       
	        if(one!=null){//版面上有這個id才更新降價
	        	
	        
		        var price = one.childNodes[0];
	//	        alert(price.innerText);
		        price.style="font-size:30px;margin-top:10px;margin-bottom:-20px; text-decoration:line-through";
		        
		        var priceNew = document.createElement("div");
		        priceNew.style="font-size:30px;margin-top:10px;margin-bottom:-20px;";
		        priceNew.innerText=jsonObj[1];     
		        if(jsonObj[1]=="已下架"){
		        	priceNew.style="font-size:20px;margin-top:10px;margin-bottom:-20px;color:red;";
		        }
		        
		        
		        var box = one.childNodes;
		        if(box.length==1){
		        	  one.appendChild(priceNew);
				      setInterval(function(){price.remove(0);}, 3000);  	
		        }else if(box.length==2){
		        	one.innerHTML = null;
		        	one.appendChild(priceNew);
		        }
		       // $(this).parent(".ex").hide("slow");
//		        $("#"+jsonObj[0]+":first-child").hide("slow",function(){ this.remove(); });
		    }//if
		};

		webSocket.onclose = function(event) {
	
		};
	}
	
	
	function sendMessage() {
	   
	}

	
	function disconnect () {
		webSocket.close();
		
	}

	
	function updateStatus(newStatus) {
		statusOutput.innerHTML = newStatus;
	}
	
