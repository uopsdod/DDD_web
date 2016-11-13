	function myMap() {
		  var mapCanvas = document.getElementById("right");
		  var mapOptions = {
		    center: new google.maps.LatLng(51.508742,-0.120850),
		    zoom: 5
		  };
		  var map = new google.maps.Map(mapCanvas, mapOptions);
		}
	
	$(function(){myMap();})
	$( function() 
		{
			$("#slider-range").slider({
			  range: true,
			  min: 0,
			  max: 500,
			  values: [ 75, 300 ],
			  slide: function( event, ui ) {
				$( "#amount" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
			  }
			});
			$( "#amount" ).val( "$" + $( "#slider-range" ).slider( "values", 0 ) +
			  " - $" + $( "#slider-range" ).slider( "values", 1 ) );
		} 
	
	)
	
	
