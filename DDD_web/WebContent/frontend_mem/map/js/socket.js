
    var MyPoint = "/MyEchoServer/nina/1";
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
			
			
		 var allBag	= JSON.parse(event.data);		 
		 var PriceBox = allBag.Bag;	//取出更新房價陣列包
		 var PeopleBox = allBag.onTimePeople;//取出更新房價陣列包
	
		 if(PriceBox!=null){
			 for(var i =0;i<PriceBox.length;i++){
		        var jsonObj = PriceBox[i];
	//	        alert(jsonObj[0] + " : " +jsonObj[1]);
		        
		        var one = roomMap.get(jsonObj[0]);
		     
		        if(one!=null){//版面上有這個id才更新降價
		        	
		        
		       		if(one.childNodes[0].childNodes[0]!=jsonObj[1]){	//價錢有變才換價錢
		       	
		       			$(one.childNodes[0]).toggle("fast",showPrice.bind(null, one,jsonObj[1]));    	
		       			$(one.childNodes[0]).toggle("slow");
		       		
		       		}
			    }//if
			 }//for each
		 }//if PriceBox!=null
		 
		 
		 if(PeopleBox.length!=0){
			 for (var key in PeopleBox) {
				  if (PeopleBox.hasOwnProperty(key)) {
				    var val = PeopleBox[key];
//				    console.log(val);
				    
				    var one =hotelMap.get(key+"");
				    
				    if(one==null){		//點地圖後,node物件會消失,所以重新建立node物件陣列
				    	roomMap = new Map;
				    	for(var i=0 ;i<FirstRoomId.length;i++){
				    		var item = document.getElementById(FirstRoomId[i]);
				    		if(item!=null){
				    		roomMap.set(""+FirstRoomId[i],item);
				    		}
				    	}
				    	
				    	hotelMap = new Map;
				    	for(var i=0 ;i<FirstHotelId.length;i++){
				    		var item = document.getElementById(FirstHotelId[i]);
				    		if(item!=null){
				    		hotelMap.set(""+FirstHotelId[i],item);
				    		}
				    	}	
				    }
				    
				    if(one.childNodes[0]!=null){
				    one.childNodes[0].remove();
				    }
				    
				    if(val!=0){
				    var nowPeople = document.createTextNode("正在瀏覽"+val+"人");	
				    one.appendChild(nowPeople);	
				    }
				    
				  }
			 }
		 }
		 
		 
		 
		 
		 
		 
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
	
