<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="org.apache.shiro.subject.*, org.apache.shiro.*" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="description" content="">
	    <meta name="author" content="">
	   
		<title>GeoCollector | Informações do Ponto  </title>
		
		<!-- CSS -->
		<link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
		<link href='https://fonts.googleapis.com/css?family=Oswald:400,700' rel='stylesheet' type='text/css'>
		<link href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,700' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" id="font-awesome-css" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" type="text/css" media="screen">
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logo/geo.ico">
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/normalize.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cadastroUsuario.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/geocollector/home.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.fancybox.css">
	
	
	</head>

	<body class="color-a">
	
		  
    	<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    	
		<script src="https://maps.googleapis.com/maps/api/js?sensor=false&amp;language=pt"></script>
		  
	      
	      <script>
		    
			    $( document ).ready(function() {
			        carregaMapa();
			    });

			    
			    
			    function carregaMapa() {
			    	var latitude  = '${point.latitude}';
			    	var longitude =  '${point.longitude}';
			    	var color = '${point.color}';
			    	var latLng = new google.maps.LatLng(latitude, longitude);
			    	
				    var mapCanvas = document.getElementById('map-canvas');
				    var mapOptions = {
				      center: new google.maps.LatLng(latitude, longitude),
				      zoom: 17,
				      zoomControl: true,
				      zoomControlOptions: {
				        style: google.maps.ZoomControlStyle.SMALL
				      },
				      mapTypeId: 
			          	google.maps.MapTypeId.ROADMAP
				    
				    }
				    mapa = new google.maps.Map(mapCanvas, mapOptions);
				    
				    
			    	

			    	
			    	var contentInfoWindow = 
						'<div id="container">'+
				    	
				    	'<div id="row">'+
				    	'<div class="col-md-12">'+
							'<h3> Dispositivo MAC nº ${point.macAdress} </h3>' +
			      		
				    	'<div id="row">'+
				    	'<div class="col-md-12">'+
				      		'<p class="left"> <span class="glyphicon glyphicon-home" aria-hidden="true"></span> ${point.street} ,${point.number} , ${point.neighborhood} </p>' +
			      		'</div></div>' +
			      		
			      		
			      		'<div id="row">'+
			      		'<div class="col-md-12">'+
			      		'<p class="left"> <span class="glyphicon glyphicon-calendar" aria-hidden="true"></span> ${point.collectDate}  </p>' +
			      		'</div></div>' +
			      		
			      		'<div id="row">'+
			      		'<div class="col-md-12">'+
			      		'<p class="left"> <span class="glyphicon glyphicon-globe" aria-hidden="true"></span> Lat.: ${point.latitude}   Long.: ${point.longitude} </p></div>' +
			      		'</div></div>' +
			      						    
				      	'</div>';
				      	
			      	var infowindow = new google.maps.InfoWindow ({
			      		content :  contentInfoWindow
			      	});
			      	
					var pinIcon = new google.maps.MarkerImage(
							'../images/markers/' + color + '.png',
							null, /* size is determined at runtime */
						    null, /* origin is 0,0 */
						    null, /* anchor is bottom center of the scaled image */
						    new google.maps.Size(50, 55)
					);  
			      	
			    	var marker = new google.maps.Marker ({
			    		position: latLng,
			    		icon: pinIcon,
			    		map: mapa
			    	});
			    	
			    	google.maps.event.addListener(marker, 'click', function(){
			    		infowindow.open(mapa, marker);
			    	});	
			    	
			    }
			    
			    
			    

		    </script>
	
	<jsp:include page="header.jsp" />
	
	
	<div class="container-fluid color-a page-section">
		<div class="container login-container">
			<div class="row">
				<div class="col-md-12">
					<br>
					<h3 style="vertical-align: bottom;"class="font-opensans font-weight-800"> <img src="${pageContext.request.contextPath}/images/geocollector/marcador.png" style="width: 20px;"> Informações do Ponto </h3>
					<hr style="margin-bottom: -5px;"/>
					<br>
				</div>			
			</div>
			
			<div class="row">
				<div class="col-md-6">
					<p class="font-opensans font-size-title" style="font-weight: 700;"> <span class="glyphicon glyphicon-globe" aria-hidden="true"></span> Mapa </p>
					<div id="map-canvas" style="height: 380px;"></div>
				</div>	

				<div class="col-md-6 font-opensans">
					<p class="font-opensans font-size-title" style="font-weight: 700;"> <span class="glyphicon glyphicon-tasks" aria-hidden="true"></span> Dados </p>
					
					<form id="dadosPonto" class="font-opensans">
						
						<div class="row">
							<div class="col-md-4 form-group">
								<label for="nome"> País </label>
								<input type="text" class="form-control" id="pais" disabled value="${point.country}")>			
							</div>
							<div class="col-md-4 form-group">
								<label for="numVigilancia"> Estado </label>
								<input type="text" class="form-control" id="estado" disabled value="${point.state}">			
							</div>
							
							<div class="col-md-4 form-group">
								<label for="numVigilancia"> Cidade </label>
								<input type="text" class="form-control" id="cidade" disabled value="${point.city}">			
							</div>
						</div>
					
					
						<div class="row">
							<div class="col-md-8 form-group">
								<label for="nome"> Nome </label>
								<input type="text" class="form-control" id="nome" disabled value="${point.name}")>			
							</div>
							<div class="col-md-4 form-group">
								<label for="numVigilancia"> Tipo de Cadastro</label>
								<input type="text" class="form-control" id="tipoCadastro" disabled value="${point.type}">			
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-6 form-group">
								<label for="rua"> Rua </label>
								<input type="text" class="form-control" id="rua" disabled value="${point.street}">			
							</div>
							<div class="col-md-2 form-group">
								<label for="numero"> Número</label>
								<input type="text" class="form-control" id="numero" disabled value="${point.number}">			
							</div>
							<div class="col-md-4 form-group">
								<label for="bairro"> Bairro </label>
								<input type="text" class="form-control" id="bairro" disabled value="${point.neighborhood}">			
							</div>
						</div>
						<div class="row">
							<div class="col-md-4 form-group">
								<label for="rua"> CEP </label>
								<input type="text" class="form-control" id="rua" disabled value="${point.postalCode}">			
							</div>
							<div class="col-md-8 form-group">
								<label for="numero"> Complemento </label>
								<input type="text" class="form-control" id="numero" disabled value="${point.supplement}">			
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-12 form-group">
								<label for="descricao"> Descrição </label>
								<input type="text" class="form-control" id="descricao" disabled value="${point.description}">			
							</div>
						</div>
						
						<hr/>
						<div class="row">
							<div class="col-md-3 form-group">
								<label for="latitude"> Latitude </label>
								<input type="text" class="form-control" id="latitude" disabled value="${point.latitude}">			
							</div>
							<div class="col-md-3 form-group">
								<label for="longitude"> Longitude </label>
								<input type="text" class="form-control" id="longitude" disabled value="${point.longitude}">			
							</div>
							<div class="col-md-6 form-group">
								<label for="layer"> Layer</label>
								<input type="text" class="form-control" id="layer" disabled value="${point.layerName}">			
							</div>
						</div>
								
					</form>
				</div>
				
			</div>
						
			<!--  <div class="row">
				<div class="col-md-12">
					<p class="font-opensans font-size-title" style="font-weight: 700;"> <span class="glyphicon glyphicon-picture" aria-hidden="true" style="padding-top:10px;"></span> Fotos</p>
					<hr/>
					<c:if test="${empty imageList}">
						<p class="font-opensans font-size-title" style="font-weight: 700;"> Não há fotos registradas. </p>
					</c:if>
					<c:forEach var="imagem" items="${imageList}">
						<div class="col-md-3">
							<a class="fancybox-buttons" data-fancybox-group="button" href="${imagem.getFullUrl()}"><img  style="width: 100%; height: 100%;" src="${imagem.getFullUrl()}" alt="" /></a>
						</div>
			  		</c:forEach>
					
				</div>
			</div>-->
			<br><br><br>
		</div>
	</div>	
  
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-select.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.fancybox.js"></script>
    <script src="${pageContext.request.contextPath}/js/fancybox.js"></script>

	</body>
</html>
