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
	   
		<title>GeoCollector | Login</title>
		
		<!-- CSS -->
		<link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
		<link href='https://fonts.googleapis.com/css?family=Oswald:400,700' rel='stylesheet' type='text/css'>
		<link href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,700' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" id="font-awesome-css" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" type="text/css" media="screen">
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logo/geo.ico">
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">	
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/normalize.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
		
	</head>

	<body class="background">
	
	<script>
	
	  window.fbAsyncInit = function() {
	    FB.init({
	      appId      : '1418024215186225',
	      xfbml      : true,
	      version    : 'v2.3'
	    });
	  };
	
	  (function(d, s, id){
	     var js, fjs = d.getElementsByTagName(s)[0];
	     if (d.getElementById(id)) {return;}
	     js = d.createElement(s); js.id = id;
	     js.src = "//connect.facebook.net/en_US/sdk.js";
	     fjs.parentNode.insertBefore(js, fjs);
	   }(document, 'script', 'facebook-jssdk'));
	  
	</script>
	
		<div class="container login">
			<div class="row">
				<div class="col-md-12">
					<div class="login-container">
						
						<div id="aviso" style="">
						</div>
						
						<div class="logo">
							<img src="${pageContext.request.contextPath}/images/logo/geo.png">
			            </div>
			         
			            <h3 class="text-center login-title"> Entre com sua conta </h3>
			            <hr/>
			            <div class="row" style="margin-right: 0px;">
				            <form id="login" name="login" class="form-signin">
				            
			            		<%
			            			Cookie cookie = null;
			            			Cookie[] cookies = null;
			            			String email = null;
		            				String key = null;
			            			cookies = request.getCookies();
			            			if ( cookies != null ){
			            				for(int i = 0; i < cookies.length; i++){
			            					cookie = cookies[i];
			            					if(cookie.getName().equals("email-geo"))
			            						email = cookie.getValue();
			            					if(cookie.getName().equals("key-geo"))
			            						key = cookie.getValue();
			            				}
			            				
			            				if (email != null && key != null){
		            					%>
								        	<input id="email" name="email" type="email" class="form-control above font" placeholder="Email" required value='<%=email%>' >
						                	<input id="senha" name="senha" type="password" class="form-control below font" placeholder="Senha" required value='<%=key%>'>		            					
		            						<div class="login-checkbox">
						                    	<input type="checkbox" class="login-checkbox" id="lembrar" name="lembrar" value="lembrar" checked> Lembrar-me
						                    </div>
		            						
		            					<%
			            				} else {
		            					%>
			            					<input id="email" name="email" type="email" class="form-control above font" placeholder="Email" required>
				               				<input id="senha" name="senha" type="password" class="form-control below font" placeholder="Senha" required>
				                    		<div class="login-checkbox">
						                    	<input type="checkbox" class="login-checkbox" id="lembrar" name="lembrar" value="lembrar"> Lembrar-me
						                    </div>
				               					
			            				<%
			            				}
			            			}
			            			else {
		            				%>
		            					<input id="email" name="email" type="email" class="form-control above font" placeholder="Email" required>
			               				<input id="senha" name="senha" type="password" class="form-control below font" placeholder="Senha" required>	
		            					<div class="login-checkbox">
					                    	<input type="checkbox" class="login-checkbox" id="lembrar" name="lembrar" value="lembrar"> Lembrar-me
					                    </div>
		            					
		            				<%
			            			}
		            				%>			
			                        <button id="entrar"style="color:#FFFFFF;" class="btn btn-login" type="submit"> Entrar </button> 
			                </form>
			                
     			            <div class="row">
			                	<div class="col-md-12">
			                	<span style="display:none;" id="loader"> <p class="font text-center"> <label> Logando...  <img width="40px;" src="${pageContext.request.contextPath}/images/loading.gif"></label></p></span>
		                		<p class="font text-center"> <a href="${pageContext.request.contextPath}/login.sign"> OU entre com Facebook </a> </p>
			                	</div>
			                </div>
			                
		                </div>
		                <hr/>
		                <div class="row" style="margin-right: 0px;" >
							<div class="col-md-5">
							 	<p class="font"> <a href="${pageContext.request.contextPath}/recuperarSenha.jsp"> Esqueceu sua senha? </a> </p>
							 	<p class="font"> <a href="${pageContext.request.contextPath}/trocarSenha.jsp"> Trocar senha </a> </p>
							 </div>
			                
			                <div class="col-md-7 text-right">
			               		<p class="font"> Não tem registro? <a href="${pageContext.request.contextPath}/cadastro.jsp"> Cadastre agora!</a> </p>
			                </div>
		                </div>
		                
					</div>
				</div>
				
			</div>
		</div>

	
	</body>
	
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-select.js"></script>
    
    <script>
    
    $("#login").submit(function () {
    	login();
    	return false;
    });
    
    function login () {
    	var senha = $("#senha").val();
    	   	
   	 	if	(senha.length < 6 ) {
			$("#aviso").html("<div class='alert alert-danger font-opensans'> <b>Atenção!</b> <br> O tamanho mínimo para a senha são 6 caracteres. </div>");
   	 	} else {
   	 		$("#loader").show();
   	 		$.post("${pageContext.request.contextPath}/Login", $("#login").serialize(),  function(data, textStatus, xhrObject ) {			
				if(data === "nao-existe"){
					$("#aviso").html("<div class='alert alert-danger font-opensans'> <b>Atenção!</b> <br> Este conta não existe. </div>");
				}
				else if(data === "credenciais invalidas"){
					$("#aviso").html("<div class='alert alert-danger font-opensans'> <b>Atenção!</b> <br> A senha está incorreta. </div>");
				}
				else if (data === "desativada"){
					$("#aviso").html("<div class='alert alert-danger font-opensans'> <b>Atenção!</b> <br> Esta conta não foi ativada ainda. Acesse seu email e verifique se já recebeu seu endereço de ativação. </div>");
				}
				else {
					if(data === "geocollector"){
	   					redirectUrl = '${pageContext.request.contextPath}/user/home.jsp';
	   					window.location = redirectUrl;
					}
				}
	   	 		$("#loader").hide();

					
				
   	 		});
   	 	}
    }
    

    $(document).ready(function(){
    	var param = getUrlParameter('erro');
    	if (param)
  			$("#aviso").html("<div class='alert alert-danger font-opensans'> <b> Ocorreu um erro! </b> <br>  O email de sua conta já está sendo utilizado por uma conta simples.</div>");

    	var param = getUrlParameter('cadastro');
    	if(param)
  			$("#aviso").html("<div class='alert alert-success font-opensans'> <b> Cadastro realizado com sucesso! </b> <br>  Você deverá receber um email em até 5 minutos com seu link para ativação. Verifique em sua caixa de entrada.</div>");
    
    	param = getUrlParameter('ativado');
    	if(param){
  			$("#aviso").html("<div class='alert alert-success font-opensans'> <b> Conta ativada com sucesso! </b> <br> Acesse o sistema e comece a utilizar o Geocollector agora! </div>");
    	}
    	
    	param = getUrlParameter("passwordUpdated");
    	if(param)
  			$("#aviso").html("<div class='alert alert-success font-opensans'> <b> Password atualizado com sucesso! </b> </div>");

    	
    });
    
    function getUrlParameter(sParam)
    {
        var sPageURL = window.location.search.substring(1);
        var sURLVariables = sPageURL.split('&');
        for (var i = 0; i < sURLVariables.length; i++) 
        {
            var sParameterName = sURLVariables[i].split('=');
            if (sParameterName[0] == sParam) 
            {
                return sParameterName[1];
            }
        }
    }  
    
    </script>
	
	
</html>
