<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Google Map</title>		
		<link rel="stylesheet" href="<%=request.getContextPath()%>/bs/bootstrap.css">
		<script src="<%=request.getContextPath()%>/jq/jquery-3.1.1.min.js"></script>
		<script src="<%=request.getContextPath()%>/bs/bootstrap.min.js"></script>
		<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAg_hj_3C0zLlGwQfq7rQat6MhgkZwrZAk"></script>
	
		<style type="text/css">
			html, body { height: 100%; margin: 0; padding: 0; }
			#map-canvas { height: 70%; }
		</style>
	</head>
	<body>
		<h1 class="text-center">哈囉～哩後！</h1>

		<div id="position"></div>

		<hr>
		<div id="map-canvas"></div>
	</body>
</html>

<script type="text/javascript">
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
		key: 'AA106',	
		lat: 24.967880,
		lng: 121.19
	};	
		
	/* 更多資訊 */	
	  var contentString = 
		'<div>'
		+ '<img src='+ 'img/profile_user2.jpg alt="Avatar" style="width:100%">'
		+ '<div>'
		+ '<h4><b>' + 'John Doe' + '</b></h4>' 
		+ '<p>' + 'Architect & Engineer' + '</p>' 
		+ '</div>'
		+ '<button type="button"'+ "onclick=\"window.open(' http://localhost:8081/DDD_web/frontend_mem/chat/chat2.jsp ', 'Yahoo', config='height=500,width=500');\" " +'>'+ '跟我聊聊' +'</button>'
		+ '</div>';

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
		
		document.getElementById('position').innerHTML = '緯度：'+lat+'<br>經度：'+lng;
		
		
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
		console.log(toMarkers.length);
		  for (var i = 0; i < toMarkers.length; i++) {
			  toMarkers[i].setMap(null);
		  }
		  toMarkers = [];
	}
	
	
	google.maps.event.addDomListener(window, 'load', initialize);
	//window.addEventListener('load',initialize,false);		

</script>