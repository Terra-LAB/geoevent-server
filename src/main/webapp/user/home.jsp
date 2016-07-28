<%@page import="geocollector.model.Role"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="org.apache.shiro.subject.*, org.apache.shiro.*"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>GeoCollector | Home</title>

<!-- CSS -->
<link href='https://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Oswald:400,700'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,700'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" id="font-awesome-css"
	href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css"
	type="text/css" media="screen">

<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/images/logo/geo.ico">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/normalize.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/cadastroUsuario.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/geocollector/home.css">

</head>

<body class="color-a">


	<script
		src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDi-V8V2YiATHJHD8bKNYP8PE5pGCg9Zek&v=3.exp&signed_in=true&libraries=visualization"></script>



	<script>
		$(document).ready(function() {
			carregaMapa();
			loadLayers();
			$("#dataMap").addClass("active");
		});

		function loadLayers() {
			$
					.getJSON(
							"${pageContext.request.contextPath}/GetLayers",
							function(responseJson) {
								if (responseJson) {
									var i = responseJson.length - 1;
									var countdown = function() {
										if (i >= 0) {
											var layerName = responseJson[i].name;
											var layerValue = layerName.replace(
													/ /gi, "%");
											$("#checkboxes")
													.append(
															"<label class='checkbox-inline'> <input type='checkbox' value=" + layerValue + " checked>"
																	+ layerName
																	+ "</label>");
											i--;
											window.setTimeout(countdown, 0);
										}
										if (i == -1) {
											atualizaMapa();
											i--;
										}
									}
									countdown();
								} else {
									alert("vazio");
								}

							});

		}

		function carregaMapa() {
			var mapCanvas = document.getElementById('map-canvas');
			var mapOptions = {
				center : new google.maps.LatLng(-10.2007853, -51.9993021),
				zoom : 5,
				zoomControl : true,
				zoomControlOptions : {
					style : google.maps.ZoomControlStyle.SMALL
				},
				mapTypeId : google.maps.MapTypeId.ROADMAP

			}
			map = new google.maps.Map(mapCanvas, mapOptions);
		}
	</script>


	<!--  CONTAINER CABEÇALHO -->


	<%
		Subject currentUser = SecurityUtils.getSubject();
	%>

	<%
		if (!currentUser.hasRole(Role.ROLE_USER.toString())) {
	%>
	<jsp:include
		page="${pageContext.request.contextPath}/permissionDenied.jsp" />
	<%
		} else {
	%>


	<jsp:include page="header.jsp" />


	<div class="container-fluid color-a page-section">
		<div class="container login-container">
			<div class="row">
				<div class="col-md-12">
					<br>
					<h3 style="vertical-align: bottom;"
						class="font-opensans font-weight-800">
						<img
							src="${pageContext.request.contextPath}/images/geocollector/minhacoleta.png"
							width="40px"> Minha coleta
					</h3>
					<hr style="margin-bottom: -5px;" />
					<br>
				</div>
			</div>

			<div class="row">
				<div class="col-md-3">
					<p class="font-opensans font-size-title" style="font-weight: 700;">
						Resumo:</p>
					<p class="font-opensans">
						Número de coletas: <span id="numColetas"> 0 </span>
					</p>
				</div>

				<div class="col-md-5 font-opensans">
					<p class="font-opensans font-size-title" style="font-weight: 700;">
						Layers:</p>

					<div id="checkboxes"></div>

					<br>
					<button id="atualizar" style="color: #FFFFFF; margin-top: 10px;"
						class="btn btn-blue">Atualizar layers</button>
				</div>

				<div class="col-md-4">
					<p class="font-opensans font-size-title" style="font-weight: 700;">
						Mapa:</p>
					<div class="btn-group " role="group" aria-label="...">
						<button id="kernelMap" type="button"
							class="btn btn-default btn-blue ">Mapa de Intensidade</button>
						<button id="dataMap" type="button"
							class="btn btn-default btn-blue">Mapa de Coletas</button>
					</div>
				</div>

			</div>

			<div class="row" style="margin-top: 10px;">
				<div class="col-md-6">
					<div id="alert" class="alert alert-success open-sans" role="alert">
						<b> Atenção! </b> Para abrir informações do ponto coletado basta
						clicar sobre ele.
					</div>
				</div>

				<div class="col-md-6">
					<div id="alert" class="alert alert-info open-sans" role="alert">
						<b> Atualização de dados: </b> Para atualizar a plataforma com
						novos dados de campo entre na página novamente ou aperter o botão
						'F5'.
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12" id="map-canvas2">
					<div id="map-canvas" style="height: 500px;"></div>
				</div>
			</div>
			<br> <br> <br>
		</div>
	</div>





	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap-select.js"></script>

	<script>
		var markers = new Array();

		$("#atualizar").click(function() {

			carregaMapa();
			atualizaMapa();

			$("#kernelMap").removeClass("active");
			$("#dataMap").addClass("active");

			currentMapStatus = "Mapa de Coleta";
		});

		function atualizaMapa() {
			var layers = "";

			$("#checkboxes input:checked").each(function() {
				var valor = $(this).val();
				valor = valor.replace(/ /gi, "%");
				layers = layers.concat("&" + valor);
			});

			if (layers.length > 0) {
				markers = [];
				taxiData = [];
				layers = layers.replace("&", "");
				$
						.post(
								"${pageContext.request.contextPath}/GetPoints",
								{
									camadas : layers
								},
								function(responseJson) {
									flag = 0;
									cont = 0;
									$
											.each(
													responseJson,
													function(index, ponto) {
														cont++;
														flag = 1;
														var latitude = ponto.latitude;
														var longitude = ponto.longitude;
														var latLng = new google.maps.LatLng(
																latitude,
																longitude);
														taxiData.push(latLng);

														color = ponto.color;

														var pinIcon = new google.maps.MarkerImage(
																'${pageContext.request.contextPath}/images/markers/'
																		+ color
																		+ '.png',
																null, /* size is determined at runtime */
																null, /* origin is 0,0 */
																null, /* anchor is bottom center of the scaled image */
																new google.maps.Size(
																		50, 55));

														var marker = new google.maps.Marker(
																{
																	position : latLng,
																	icon : pinIcon,
																	map : map
																});
														markers.push(marker);

														var infoWindowContent = '<div id="container">'
																+

																'<div id="row">'
																+

																'<div class="col-md-12">'
																+ '<p class="left"> <span class="glyphicon glyphicon-home" aria-hidden="true"></span>'
																+ ponto.street
																+ ','
																+ ponto.number
																+ ','
																+ ponto.neighborhood
																+ ' </p>'
																+ '</div></div>'
																+

																'<div id="row">'
																+ '<div class="col-md-12">'
																+ '<p class="left"> <span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>'
																+ ponto.collectDate
																+ '</p>'
																+ '</div></div>'
																+

																'<div id="row">'
																+ '<div class="col-md-12">'
																+ '<p class="left"> <span class="glyphicon glyphicon-globe" aria-hidden="true"></span> Lat.:'
																+ ponto.latitude
																+ '  Long.: '
																+ ponto.longitude
																+ ' </p></div>'
																+ '</div></div>'
																+

																'<div id="row">'
																+ '<div class="col-md-12">'
																+ '<p class="right"><button type="submit" onclick="carregaPonto('
																+ ponto.id
																+ ')" class="btn btn-primary btn-sm"> Abrir ficha </button> </p></div>'
																+ '</div></div>'
																+

																'</div>';
														createInfoWindow(
																marker,
																infoWindowContent);

													});

									$("#numColetas").html(cont);

									if (flag == 1) {
										var bounds = new google.maps.LatLngBounds();
										for (var i = 0; i < markers.length; i++) {
											bounds.extend(markers[i]
													.getPosition());
										}
										map.fitBounds(bounds);
										flag = 0;
									} else {
										carregaMapa();

									}

								}, 'json');

				var infoWindow = new google.maps.InfoWindow();
				function createInfoWindow(marker, infoWindowContent) {
					google.maps.event.addListener(marker, 'click', function() {
						infoWindow.setContent(infoWindowContent);
						infoWindow.open(map, this);
					});
				}
			} else {
				pointArray = [];
				taxiData = [];
				carregaMapa();
				$("#numColetas").html(0);
			}
		}

		function carregaPonto(id) {
			redirectUrl = '${pageContext.request.contextPath}/user/informacoesPonto?idPoint='
					+ id;
			window.location = redirectUrl;
		}
	</script>

	<script>
		currentMapStatus = "Mapa de Coleta";
		var mapType = null;
		$(".btn-group > button.btn").on("click", function() {
			mapType = this.innerHTML;

			if (mapType === "Mapa de Intensidade") {

				$("#alert").fadeOut(1000);

				$("#kernelMap").addClass("active");
				$("#dataMap").removeClass("active");
				loadKernelMap();
				currentMapStatus = "Mapa de Intensidade";
			} else {

				$("#alert").fadeIn(1000);

				$("#kernelMap").removeClass("active");
				$("#dataMap").addClass("active");
				if (currentMapStatus != "Mapa de Coleta") {
					carregaMapa();
					atualizaMapa();
				}
				currentMapStatus = "Mapa de Coleta";
			}

		});

		function loadKernelMap() {
			$("#map-canvas").html("");
			pointArray = new google.maps.MVCArray(taxiData);
			heatmap = new google.maps.visualization.HeatmapLayer({
				data : pointArray,
				radius : 50,
				scaleRadius : false,
				opacity : 0.6,
				maxIntensity : 6,
				dissipating : true
			});

			var mapOptions = {
				zoom : 5,
				center : new google.maps.LatLng(-10.2007853, -51.9993021),
				mapTypeId : google.maps.MapTypeId.ROADMAP
			};
			map = new google.maps.Map(document.getElementById('map-canvas'),
					mapOptions);

			heatmap.setMap(map);

			var bounds = new google.maps.LatLngBounds();
			for (var i = 0; i < markers.length; i++) {
				bounds.extend(taxiData[i]);
			}
			map.fitBounds(bounds);
		}
	</script>

	<%
		}
	%>

</body>


</html>
