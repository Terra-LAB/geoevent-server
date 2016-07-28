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
	   
		<title>GeoCollector | Banco de Endereços  </title>
		
		<!-- CSS -->
		<link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
		<link href='https://fonts.googleapis.com/css?family=Oswald:400,700' rel='stylesheet' type='text/css'>
		<link href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,700' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" id="font-awesome-css" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" type="text/css" media="screen">
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logo/geo.ico">
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/normalize.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/fileinput.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cadastroUsuario.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/geocollector/home.css">
	</head>

	<body class="color-a">

	<jsp:include page="header.jsp" />
		
	<div class="container-fluid color-a page-section">
		<div class="container login-container">
			<div class="row">
				<div class="col-md-12">
					<br>
					<h3 style="vertical-align: bottom;"class="font-opensans font-weight-800"> <img src="${pageContext.request.contextPath}/images/geocollector/bancoEnderecos.png" style="width: 20px;"> Banco de Endereços </h3>
					<hr style="margin-bottom: -5px;"/>
					<br>
				</div>			
			</div>
			
			<div class="row">
				<div class="col-md-8">
					<div id="alert" class="alert alert-warning open-sans" role="alert"> 
						<b> Atenção! </b> O envio de um banco de endereços irá substituir a base de endereços antiga, caso exista.</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-4">
					<form class="font-opensans form-inline"  method="post" enctype="multipart/form-data" action='${pageContext.request.contextPath}/Upload'>	
						<div class="form-group">
						   	<input type="file" name="file" id="file">
   					  		<button type="submit" id="enviar" style="color:#FFFFFF; margin-top:10px;"class="btn btn-blue"> Enviar </button>
						</div>
			  		</form>
				</div>
				<div class="col-md-4">
					<p class="font-opensans"> Para importar um banco de enderecos deve ser enviado um arquivo Excel (XLS) com a relação bairro-rua, blabla.</p>
					<p class="font-opensans"> * Apenas arquivos XLS </p>
				</div>
				
					
			</div>
						
			

		</div>
	</div>	
  
   	<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script	>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-select.js"></script>
    <script src="${pageContext.request.contextPath}/js/fileinput.js"></script>

	</body>
</html>
