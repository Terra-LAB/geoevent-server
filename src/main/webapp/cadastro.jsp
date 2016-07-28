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
	   
		<title>GeoCollector | Cadastro de Usuário </title>
		
		<!-- CSS -->
		<link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
		<link href='https://fonts.googleapis.com/css?family=Oswald:400,700' rel='stylesheet' type='text/css'>
		<link href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,700' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" id="font-awesome-css" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" type="text/css" media="screen">
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logo/geo.ico">
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/normalize.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cadastroUsuario.css">
		
	</head>

	<body class="color-a">
	
	<!--  CONTAINER CABEÇALHO --> 
	<div class="container-fluid  background-color" id="cabecalho">
	
		<div class="header"> 
		
			<nav role="navigation" class="navbar navbar-default" style="margin-bottom:0px">
				
				<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
		            <span class="sr-only">Toggle navigation</span>
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
		          </button>
					<img class="width250" src="${pageContext.request.contextPath}/images/logo/geocollector.png">
				</div>
				
				<div id="navbar" class="navbar-collapse collapse">
					<div class="menu">
						<ul class="nav navbar-nav navbar-right">
							<li> <a href="index.jsp"> <span> Início </span></a> </li>
							<li> <a href="login.jsp"> <span> <span class="glyphicon glyphicon-log-in" aria-hidden="true"></span> Login</span> </a> </li>
						</ul>
					</div>
				</div>
			</nav>
			
		</div>
	</div>
	<!-- FIM CONTAINER CABEÇALHO -->
	
	<!--  FORM DO CADASTRO  -->
	<div class="container-fluid color-a page-section">
		<div class="container" id="definicao">
			<div class="row">
				<div class="col-md-5 login-container" style="padding-left:50px;">
					<br>
					<p class="font-opensans font-weight-800 font-size-title"> Criar uma conta GeoCollector</p>
					<br>
					
					<div id="aviso">
						
					</div>
					
					<form id="cadastroUsuario" class="font-opensans">
						<div class="form-group">
					    	<label for="nomeCompleto">Nome Completo</label>
					    	<input type="text" required="required" class="form-control" id="nomeCompleto" name="nomeCompleto">
					  	</div>
					  	<div class="form-group">
					    	<label for="email">Email</label>
					    	<input type="email" required="required" class="form-control" id="email" name="email">
					 	</div>
					  	<div class="form-group">
					    	<label for="senha">Senha</label>
					    	<input type="password" required="required" class="form-control" name="senha" id="senha">
					  	</div>
  					  	<div class="form-group">
					    	<label for="confirmacaoSenha">Confirmação de Senha</label>
					    	<input type="password" required="required" class="form-control" name="cfmSenha" id="cfmSenha">
					  	</div>
					  
					  	<p class="font-opensans"> Cadastrando no GeoCollector, você está aceitando os <a href="">Termos de Serviço </a>  e a <a href="">Política de Privacidade</a>. </p>
					  
					  	<button id="registrar" style="color:#FFFFFF;"class="btn btn-login"> Registrar </button>
					  	<span style="display:none;" id="loader"> <p class="font text-center"> <label> Cadastrando... <img  width="40px;" src="${pageContext.request.contextPath}/images/loading.gif"/></label></p></span>
					  	
					</form>
				
				</div>
				<div class="col-md-1"></div>
				<div class="col-md-4 font-opensans">
					<h3 class="font-weight-800 font-opensans">
						Experimente gratuitamente!
					</h3>
					<br>
					<p class="font-opensans font-size-title">Cria uma conta e veja como o GeoCollector pode lhe ajudar na realização de sua coleta em campo e futuras tomadas de decisão. </p>
					<br>
					<hr/>
					
					<div class="col-md-12 display-table" style="display:table;">
						<img class="pull-left" style="width: 40px;" src="${pageContext.request.contextPath}/images/icons/dinheiro.png"/>
						<div class="vertical-align"> Economize tempo, papel e dinheiro </div>
					</div>
					<div class="col-md-12 display-table">
						<img class="pull-left" style="width: 40px;" src="${pageContext.request.contextPath}/images/icons/internet_coleta.png"/>
						<div class="vertical-align"> Visualize toda sua coleta na Internet </div>
					</div>

					<div class="col-md-12 display-table">
					<img class="pull-left" style="width: 40px;" src="${pageContext.request.contextPath}/images/icons/export.png"/>
						<div class="vertical-align"> Exporte os dados da maneira que desejar </div>
					</div>
	
					<div class="col-md-12 display-table">
					<img class="pull-left" style="width: 40px;" src="${pageContext.request.contextPath}/images/icons/seguranca_dados.png"/>
						<div class="vertical-align"> Dados salvos em um servidor seguro </div>
					</div>			
				</div>
				
				<div class="col-md-2"></div>
				
					
			</div>
			<br><br><br>
		</div>
	</div>
	
	
				
	
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.12.0/jquery.validate.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-select.js"></script>
    
    <script>
    
    $("#cadastroUsuario").submit(function () {
    	valida();
    	return false;
    });
    
    
   	function valida () {
   		var nomeCompleto = $("#nomeCompleto").val();
   		var email = $("#email").val();
   		var senha = $("#senha").val();
   		var cfmSenha = $("#cfmSenha").val();
   		
	   		
   		if(senha && nomeCompleto && email && cfmSenha) {
	   		if (senha.length < 6 && senha != cfmSenha) 
	   			$("#aviso").html("<div class='alert alert-danger font-opensans'> <b>Atenção!</b> <br> <ul> <li> O tamanho mínimo para a senha são 6 caracteres. </li> <li> As senhas não conferem. Tente novamente. </li> </ul> </div>");
	   		else if	(senha.length < 6 )
	   			$("#aviso").html("<div class='alert alert-danger font-opensans'> <b>Atenção!</b> <br> <ul> <li> O tamanho mínimo para a senha são 6 caracteres. </li> </ul> </div>");
	   		else if (senha != cfmSenha)
	   			$("#aviso").html("<div class='alert alert-danger font-opensans'> <b>Atenção!</b> <br> <ul> <li> As senhas não conferem. Tente novamente. </li> </ul> </div>");
	   		else {
	   			$("#loader").show();
	   			$.post("${pageContext.request.contextPath}/CadastroUsuarioServlet", $("#cadastroUsuario").serialize(), function(data, textStatus, xhrObject ) {  				
	   				var status = data;
	   				
	   				if (status === "existe") {
	   		   			$("#aviso").html("<div class='alert alert-danger font-opensans'> <b>Atenção!</b> <br> <ul> <li> Este email já está cadastrado. </li> </ul> </div>");
	   				}
	   				else if (status === "erro") {
	   		   			$("#aviso").html("<div class='alert alert-danger font-opensans'> <b>Atenção!</b> <br> <ul> <li> Ocorreu um erro interno. Entre em contato com os administradores. </li> </ul> </div>");
	   					alert("internal error");
	   				}
	   				else if (status === "criado"){
	   					redirectUrl = '${pageContext.request.contextPath}/login.jsp?cadastro=sucesso';
	   					window.location = redirectUrl;
   				  	}
		   	 		$("#loader").hide();
	   			});
	   		}
   		}
   	}

    </script>
    
    

	</body>
	
	
</html>
