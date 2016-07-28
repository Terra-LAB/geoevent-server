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
	   
		<title>GeoCollector | Trocar senha </title>
		
		<!-- CSS -->
		<link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
		<link href='https://fonts.googleapis.com/css?family=Oswald:400,700' rel='stylesheet' type='text/css'>
		<link href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,700' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" id="font-awesome-css" href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" type="text/css" media="screen">
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logo/geo.ico">
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">	
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/normalize.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
		
	</head>

	<body class="background">
	
		<div class="container login">
			<div class="row">
				<div class="col-md-12">
					<div class="login-container">
						
						<div id="aviso" style="">
						</div>
					
						<div class="logo">
							<img src="${pageContext.request.contextPath}/images/logo/geo.png">
			            </div>
			            
			            <h3 class="text-center login-title"> Trocar senha </h3>
			            <hr/>
			            <div class="row" style="margin-right: 0px;">
				            <form name="trocasenha-form" id="trocasenha-form" class="form-reset-pass">
				                <input style="margin-bottom:5px;" type="email" class="form-control above below font" id="email" name="email" placeholder="Email" required autofocus>			               
  				                <input style="margin-bottom:5px;" type="password" class="form-control  font" id="antigoPassword" name="antigoPassword" placeholder="Antigo password" required autofocus>			               
			                    <input style="margin-bottom:5px;" type="password" class="form-control above below font" id="novoPassword" name="novoPassword" placeholder="Novo password" required autofocus>	
			                    <button id="trocaSenha" style="color:#FFFFFF; margin-top: 10px;"class="btn btn-login" type="submit"> Trocar senha </button>
			                </form>
			                
		                </div>
		                <hr/>
		                <div class="row" style="margin-right: 0px;" >
							<div class="col-md-5">
							 	<p class="font"> <a href="${pageContext.request.contextPath}/login.jsp"> Voltar para o login</a> </p>
							 </div>
			                
			                <div class="col-md-7 text-right">
			               		<p class="font"> Não tem registro? <a href=""> Cadastre agora!</a> </p>
			                </div>
		                </div>
		                
					</div>
				</div>
				
			</div>
		</div>
	</body>
	
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-select.js"></script>
    
    <script>
    
    $("#trocasenha-form").submit(function () {
    	trocar();
    	return false;
    });
    
    function trocar () {	
		var password = $("#novoPassword").val();
		if (password.length > 5)
	    	$.post("${pageContext.request.contextPath}/trocaSenha", $("#trocasenha-form").serialize(), function (data, textStatus, xhrObject ) {
	    		if (data === "nao-encontrou"){
					$("#aviso").html("<div class='alert alert-danger font-opensans'> <b>Atenção!</b> <br> Email/Senha incompatíveis. </div>");	
	    		}
	    		if(data === "password-trocado"){
	    			redirectUrl = '${pageContext.request.contextPath}/login.jsp?passwordUpdated=true';
					window.location = redirectUrl;	
    			}
	    		if(data === "erro"){
	    			redirectUrl = '${pageContext.request.contextPath}/erro.jsp';
					window.location = redirectUrl;	
	    		}
	    	});
   		else
			$("#aviso").html("<div class='alert alert-danger font-opensans'> <b>Atenção!</b> <br> O tamanho mínimo para a senha são seis caracteres. </div>");	
    }
    
    </script>
   	
</html>
