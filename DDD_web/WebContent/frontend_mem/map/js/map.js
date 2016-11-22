var map;	
var markSet=[];
var areaLocal = new Map();
var mapMark = new Map();

function myMap() {
		  var mapCanvas = document.getElementById("right");
		  var mapOptions = {
		    center: {lat: 23.363, lng: 121.044},
		    zoom: 8
		  };
		  map = new google.maps.Map(mapCanvas, mapOptions);
		  
		 
}
$(function(){myMap();})

function setMarker(hotel){
	
	var HotelPosition = new google.maps.LatLng(hotel.hotelLat,hotel.hotelLon);
	
	var iconImg = { 
		url:'mapImage/house.png',
//		size: new google.maps.Size(10, 10)	
	};
	var marker = new google.maps.Marker({
		    position: HotelPosition,
		    map: map,
		    icon: iconImg,
		 
	  });
	 
	 var contentString = hotel.hotelName;
	
	 var infowindow = new google.maps.InfoWindow({
         //包含在資訊視窗中顯示的文字字串或 DOM 節點。
         content: contentString 
      });
	
	
	 					
	 marker.addListener('mouseover', function() {
	        //使用 InfoWindow.open() 方法將資訊視窗附加到新的標記
	          infowindow.open(map, marker);
	  });
	 
	 marker.addListener('mouseout', function() {
	        //使用 InfoWindow.open() 方法將資訊視窗附加到新的標記
	          infowindow.close(map, marker);
	  });
	 
	 marker.addListener('click', function() {
	        //使用 InfoWindow.open() 方法將資訊視窗附加到新的標記
//	        window.location.href = ; 
	        window.open("/DDD_web/HotelRoomSearch?action=hotelPage&hotelId=" + hotel.hotelId);
	        
	  });
	

	mapMark.set(hotel.hotelId,marker);	
	markSet.push(marker);
	
	
}
function changeMark(hotel){
	var markerImg = mapMark.get(hotel.hotelId);
	markerImg.setIcon("mapImage/house2.png");
}

function backMark(hotel){
	var markerImg = mapMark.get(hotel.hotelId);
	markerImg.setIcon("mapImage/house.png");
}


function cancelMark(){
	
	for(var i =0;i<markSet.length;i++){
		markSet[i].setMap(null);		
	}	
	markSet.length=0;
	mapMark = new Map();
}

function setMapLocal(cityObj,zoneObj){
	
//	console.log(cityObj+zoneObj);
	
	var obj = areaLocal.get(cityObj+zoneObj);
	
//	console.log(obj[0],obj[1]);
	
	var Center = new google.maps.LatLng(obj[0],obj[1]);
	
	map.panTo(Center);	
	map.setZoom(16); 	
	map.setCenter(Center);
	 
	 
}
function initArea(){
	areaLocal.set('桃園市中壢區',[24.956525,121.223238]);
	areaLocal.set('桃園市桃園區',[24.992016,121.312564]);
	areaLocal.set('台北市信義區',[25.036188,121.565404]);
	
//	var obj = areaLocal.get('桃園市中壢區');
//	alert(obj[0]);
}

$(function(){initArea();})

  
	
