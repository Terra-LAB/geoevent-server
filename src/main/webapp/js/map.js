var map = null;
function showlocation() {
	// One-shot position request.
	navigator.geolocation.getCurrentPosition(callback);
	
	return true;
}

function callback(position) {

	var lat = request.getAttribute("latitude");
	var long = request.getAttribute("longitude");

	var latLong = new google.maps.LatLng(lat, long);

	var marker = new google.maps.Marker({
		position: latLong
	});      

	marker.setMap(map);
	map.setZoom(8);
	map.setCenter(marker.getPosition());
	
	return true;
}

google.maps.event.addDomListener(window, 'load', initMap);
function initMap() {
	var mapOptions = {
			center: new google.maps.LatLng(0, 0),
			zoom: 1,
			mapTypeId: google.maps.MapTypeId.ROADMAP
	};
	map = new google.maps.Map(document.getElementById("map-canvas"), 
			mapOptions);
	
	return true;

}