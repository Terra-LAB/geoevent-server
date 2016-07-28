<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="description" content="">
	    <meta name="author" content="">
	   
		<title>GeoCollector | Erro </title>
		
		<!-- CSS -->
		<link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
		<link href='https://fonts.googleapis.com/css?family=Oswald:400,700' rel='stylesheet' type='text/css'>
		<link href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,700' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" id="font-awesome-css" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" type="text/css" media="screen">
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logo/geo.ico">
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/construcao.css">	
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/normalize.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
		
	</head>

	<body class="background">
	
		<div class="container login">
			<div class="row">
				<div class="col-md-12">
					<div class="login-container"  style="text-align:center;">
						<div class="logo">
							<img src="${pageContext.request.contextPath}/images/logo/geo.png">
			            </div>
			            <h3 class="text-center login-title"> Autorização Negada</h3>
			            <hr/>
			            
			            <button onClick="window.location='${pageContext.request.contextPath}/login.jsp';"  id="entrar"style="color:#FFFFFF;"class="btn btn-login" type="submit"> Login </button>
	
					</div>
				</div>
				
			</div>
		</div>

	
	</body>
	
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-select.js"></script>
    
	
	
</html>
