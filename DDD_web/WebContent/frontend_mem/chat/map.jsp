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
		<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA_cweOAnsKCSl-jXbIAO3yqlnZ2BbbSR4"></script>
	
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
	var lati = 0;
	var longi = 0; 
  
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
		//console.warn('ERROR(' + err.code + '): ' + err.message);
		
		var fackPos = {
			"coords" : {"latitude" : "<%=24.967880%>",    
						"longitude" : "<%=121.191602%>"   
						}
		};
		succCallback(fackPos);
	}
	
	
	function succCallback(e) {
		lati = e.coords.latitude;
		longi = e.coords.longitude; 
	  
		document.getElementById('position').innerHTML = '緯度：'+lati+'<br>經度：'+longi;
		
		
		var latlng = new google.maps.LatLng(lati,longi);
		
		
		var map = new google.maps.Map(document.getElementById('map-canvas'),{
			center:latlng,
			zoom:17,
			mapTypeId:google.maps.MapTypeId.ROADMAP
		});
		
		var marker = new google.maps.Marker({
			position: latlng,
			map: map,
			icon: 'img/pin.png',
			title: '這不是我家'
		});
 	}
  
  google.maps.event.addDomListener(window, 'load', initialize);
  //window.addEventListener('load',initialize,false);
  
</script>