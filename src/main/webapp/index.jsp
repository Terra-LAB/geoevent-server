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
	   
		<title>GeoCollector | Solu��es em Geoinform�tica </title>
		
		<!-- CSS -->
		<link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
		<link href='https://fonts.googleapis.com/css?family=Oswald:400,700' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" id="font-awesome-css" href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" type="text/css" media="screen">
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logo/geo.ico">
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/normalize.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/home-style.css">
		
		<style>
			/* Paste this css to your style sheet file or under head tag */
			/* This only works with JavaScript, 
			if it's not present, don't show loader */
			.no-js #loader { display: none;  }
			.js #loader { display: block; position: absolute; left: 100px; top: 0; }
			.se-pre-con {
				position: fixed;
				left: 0px;
				top: 0px;
				width: 100%;
				height: 100%;
				z-index: 9999;
				background: url(images/loading.gif) center no-repeat #fff;
			}
		</style>

			
	</head>

	<body class="background-color">
	

	
	<!--  CONTAINER CABE�ALHO --> 
	<div class="container-fluid" id="cabecalho">
	
		<div class="header"> 
		
			<nav role="navigation" class="navbar navbar-default">
				
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
							<li> <a style="text-decoration:none; color:#ffffff;" href="#definicao"> <span> O que �</span></a> </li>
							<li> <a style="text-decoration:none; color:#ffffff;" href="#funcionamento"> <span> Como funciona </span></a> </li>
							<li> <a style="text-decoration:none; color:#ffffff;" href="#vantagens"> <span> Vantagens </span></a></li>
							<li> <a style="text-decoration:none; color:#ffffff;" href="#casoDeUso"> <span> Casos de Uso </span></a></li>							
							<li> <a style="text-decoration:none; color:#ffffff;" href="#contato"> <span> Contato </span></a> </li>
							<li> <a href="login.jsp"> <span> <span class="glyphicon glyphicon-log-in" aria-hidden="true"></span> Login</span> </a> </li>
						</ul>
					</div>
				</div>
			</nav>
			
		</div>
	</div>
	<!-- FIM CONTAINER CABE�ALHO -->
	
	<div class="container">
		<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
		 <!-- Indicators -->
		<ol class="carousel-indicators">
		  <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
		  <li data-target="#carousel-example-generic" data-slide-to="1"></li>
		</ol>
		
		<!-- Wrapper for slides -->
		<div class="carousel-inner">
		
		  <div class="item active">	  	
		  	<div class="row">
		  		<div class="col-md-7">
		  			<br> <br>
		  			<h1 class="slide-font-header"> Facilidade na Coleta de Dados</h1>
		  			<p class="slide-font-text"> Colete informa��es geo-referenciadas atrav�s de um dispositivo m�vel, e sincronize diretamente com a web.</p>
		  			<button type="button" class="btn btn-default btn-lg"> <a style="color: #000000; text-decoration: none;" href="#funcionamento"> Como funciona? </a></button>
		  		</div>
		  		
		  		<div class="col-md-5">
		  			<img style="width: 100%;" src="${pageContext.request.contextPath}/images/icons/slide1.png">
		  		</div>
		  	</div>
		  </div>
		  
		  <div class="item">
				<div class="row">
					<div class="col-md-7">
						<br> <br>
						<h1 class="slide-font-header"> Gera��o de Relat�rios</h1>
						<p class="slide-font-text"> Mapas de Kernel, Gr�ficos, Tabelas. Use as informa��es para gerar resultados para futuras tomadas de decis�es. </p>
						<!--  <button type="button" class="btn btn-default btn-lg">Como funciona?</button>						-->
					</div>
					
					<div class="col-md-5">
						<img style="width: 100%;" src="${pageContext.request.contextPath}/images/icons/slide2.png">
					</div>
				</div>
		  </div>
		  
		  </div>
		</div>
	</div>
	
	<div class="container-fluid color-a page-section">
		<div class="container" id="definicao">
			<div class="row">
				<h1 class=""> O que � </h1>
					<div class="col-md-6">
						<img  style="width: 70%;" src="${pageContext.request.contextPath}/images/home/definicao.png">
					</div>
					<div class="col-md-6">
					<p class="slide-font-text"> GeoCollector � uma plataforma de mapeamento, que ajuda cidades e comunidades � tomar decis�es atrav�s da coleta em campo e visualiza��o da informa��o em tempo real. </p>
					<br><p class="slide-font-text "> Setores p�blicos e privados usam o GeoCollector para realizar rapidamente coletas e mapeamentos qualitativos e quantitativos de dados. </p>
					<br><p class="slide-font-text"> Projetamos o formul�rio de coleta de acordo com sua necessidade, gerenciando e visualizando os dados georreferenciados em tempo real, sem a necessidade de um profissional na �rea. </p>

					</div>
				
					
			</div>
			<br><br><br>
		</div>
	</div>
	
	<div class="container-fluid color-a page-section">
		<div class="container" id="funcionamento">
			<div class="row">
				<h1 class=""> Como funciona </h1>
				
					<div class="col-md-3">
						<img class="page-section-icon" src="${pageContext.request.contextPath}/images/icons/colete.png">
						<h4> Colete os dados</h4>
						<p class="subheader"> Colete informa��es geo-referenciadas em qualquer lugar, mesmo estando offline.</p>
					</div>
					
					<div class="col-md-3">
					<img class="page-section-icon" src="${pageContext.request.contextPath}/images/icons/nuvem.png">
						<h4> Sincronize na nuvem </h4>
						<p class="subheader"> Envie os dados coletados para um servidor 24/7.</p>
					
					</div>
					
					<div class="col-md-3">
					<img class="page-section-icon" src="${pageContext.request.contextPath}/images/icons/visualize.png">
						<h4> Visualize os dados</h4>
						<p class="subheader"> Visualize os dados coletados, e gere gr�ficos, mapas, tabelas e relat�rios.</p>
					
					</div>
					
					<div class="col-md-3">
					<img class="page-section-icon" src="${pageContext.request.contextPath}/images/icons/tiposExportacao.png">
						<h4> Exporte para um SIG </h4>
						<p class="subheader"> Use os dados para realizar tomadas de decis�es, enviando-os para algum SIG, como TerraView, ArcGIS.</p>
					
					</div>
			</div>
			<br><br><br>
		</div>
	</div>
	
	<div class="container-fluid background-color color-white page-section">
		<div class="container" id="vantagens">
			<h1> Vantagens</h1>
			
			<div class="row">
				<div class="col-md-6">
					<div class="col-md-12">
						<h3> Simples coleta de dados</h3>
						<p class="subheader"> Use smartphones ou tablets para coletar dados em campo. Atrav�s de poucos cliques, colete diversas informa��es.</p>
					</div>
					<div class="col-md-12">
						<h3>Economize tempo e dinheiro </h3>
						<p class="subheader"> N�o transcreva mais dados em papel na coleta, e do papel para a m�quina. Economize tempo j� enviando todas as informa��es coletadas diretamente para o servidor usando a web. </p>
					</div>
					<div class="col-md-12">
						<h3> Utilize sem Internet </h3>
						<p class="subheader"> Sem Internet no local da coleta? Sem problemas. Fa�a a coleta e sincronize quando ter uma rede dispon�vel.</p>
					</div>
				</div>
				<div class="col-md-6">
					<img style="width: 100%;" src="${pageContext.request.contextPath}/images/icons/mapa-pontos.png">
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-6">
					<div class="col-md-12">
						<h3> Integra��o de dados + An�lise</h3>
						<p class="subheader"> Veja os dados em seu navegador web, ou exporte eles. GeoCollector exporta os dados coletados em formatos �teis. Voc� poder� usar os dados no TerraView, Microsoft Acess/Excel, Google Earth, Fusion Tables, ESRI ArcGIS.</p>
					</div>
					<div class="col-md-12">
						<h3> Customizamos seu formul�rio</h3>
						<p class="subheader"> Precisa de um formul�rio espec�fico para sua coleta? GeoCollector fornece � voc� design de seu formul�rio de acordo com a necessidade do seu projeto.  </p>
					</div>
				</div>
				
				<div class="col-md-6">
					<img style="margin-top:30px; width: 100%;" src="${pageContext.request.contextPath}/images/icons/tiposExportacaoBranco.png">
				</div>
			</div>
			
			<br><br>
		</div>
		

		
	</div>
	
	<div class="container-fluid color-a page-section">
		<div class="container" id="casoDeUso">
			<h1> Casos de Uso </h1>
			<div class="row">
				<div class="col-md-3"style="text-align:center; margin-top: 30px;">
					<img  style="width: 80%;" src="${pageContext.request.contextPath}/images/home/caso-uso-agua.png">
				</div>
				<div class="col-md-9">
					<h3> Projeto Vigi�gua </h3>
					<p class="slide-font-text">
						Projeto entre os laborat�rios Laborat�rio de Simula��o de Sistemas Terrestres (TerraLab) , Laborat�rio de Biotecnologia Molecular (LBTM)  e as autarquias Secret�ria de Vigil�ncia Sanit�ria de Ouro Preto e sua ger�ncia estadual Funda��o Ezequiel Dias (FUNED). Com a utiliza�ao do sistema de monitoramento de dados Geocollector, foi poss�vel otimizar a gest�o da vigil�ncia sanit�ria atrav�s de mapas de concentra��o de pontos de potabilidade em tempo de an�lise, seguindo todas as normas da portaria N� 2.914/2011 - Minist�rio da Sa�de.
			  			<a href="construcao.jsp"><b>Leia mais ...</b> </a>
			  		</p>
			  		<br><br>
				</div>
				
			</div>	
		</div>
	</div>

	
	<div class="container-fluid background-color-cinza color-white page-section">
		<div class="container" id="contato">
			<h1> Contato </h1>
			
			<div class="row">
				<div class="col-md-6">
					<form class="form" id="contato">
						<div class="input-container half">
							<input type="text" class="form-control" name="nome" id="nome"  placeholder="Nome" required="required">
						</div>
						<div class="input-container half">
							<input type="email" class="form-control" name="email" id="email"  placeholder="Email" required="required">
						</div>
						<div class="input-container half">
							<input type="text" class="form-control" name="telefone" id="telefone"  placeholder="Telefone">
						</div>
						<div class="input-container half">
							<input type="text" class="form-control" name="site" id="site"  placeholder="Site">
						</div>
						<div class="input-container">
							<textarea placeholder="Mensagem" class="form-control" rows="5" cols="30" name="mensagem" id="mensagem" required="required"> </textarea>
						</div>
						
						<button type="submit" class="btn btn-danger half"> Enviar </button>
					</form>
				</div>
				
				<div class="col-md-2">
				

				</div>
				
				<div class="col-md-4">
					
					<h3> <span class="glyphicon glyphicon glyphicon-map-marker" aria-hidden="true" style="line-height: inherit; font-size: 35px;"></span> Endere�o </h3>
						<p class="subheader"> Ouro Preto, Minas Gerais </p>
						<p class="subheader"> Brazil </p>
						<p class="subheader"> <span class="glyphicon glyphicon glyphicon-earphone" aria-hidden="true" style="line-height: inherit; font-size: 15px;"></span>  +55 31 7360-2425 </p>
						<p class="subheader"> <i class="fa fa-share"></i> <a style="color: #FFFFFF;" href="mailto:contato@geocollector.com.br"> contato@geocollector.com.br </a> </p>
				
					<div class="scroll-top-wrapper ">
						<span class="scroll-top-inner">
							<span class="glyphicon glyphicon glyphicon-upload" aria-hidden="true" style="line-height: inherit; font-size: 35px;"></span>
						</span>
					</div>
				</div>
			</div>
			
			<div class="row footer">
				<div class="col-md-12">
					<p class="subheader">Copyright � 2015 GeoCollector Ltd. Todos os direitos reservados.</p>
				</div>
			</div>
			
		
			
			
		</div>
	</div>


				
	
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-select.js"></script>
    
    <script>
    
    $(function() {
    	  $('a[href*=#]:not([href=#])').click(function() {
    	    if (location.pathname.replace(/^\//,'') == this.pathname.replace(/^\//,'') && location.hostname == this.hostname) {
    	      var target = $(this.hash);
    	      target = target.length ? target : $('[name=' + this.hash.slice(1) +']');
    	      if (target.length) {
    	        $('html,body').animate({
    	          scrollTop: target.offset().top
    	        }, 1000);
    	        return false;
    	      }
    	    }
    	  });
    	});
    
    
	$(function(){
	 
		$(document).on( 'scroll', function(){
	 
			if ($(window).scrollTop() > 100) {
				$('.scroll-top-wrapper').addClass('show');
			} else {
				$('.scroll-top-wrapper').removeClass('show');
			}
		});
	});
	
	$(function(){
		 
		$(document).on( 'scroll', function(){
	 
			if ($(window).scrollTop() > 100) {
				$('.scroll-top-wrapper').addClass('show');
			} else {
				$('.scroll-top-wrapper').removeClass('show');
			}
		});
	 
		$('.scroll-top-wrapper').on('click', scrollToTop);
	});
	 
	function scrollToTop() {
		verticalOffset = typeof(verticalOffset) != 'undefined' ? verticalOffset : 0;
		element = $('body');
		offset = element.offset();
		offsetTop = offset.top;
		$('html, body').animate({scrollTop: offsetTop}, 500, 'linear');
	}
	
	function sendMessage () {
		$.post("${pageContext.request.contextPath}/SendMessage", $("#form-contato").serialize() , function (data) { 
			alert("Mensagem enviada com sucesso.");
    		$(".se-pre-con").fadeOut("slow");;

		});	
	}
	
    $("#contato").submit(function () {
    	sendMessage();
    	return false;
    });	
    
    

    //paste this code under head tag or in a seperate js file.
    	// Wait for window load
    	$(window).load(function() {
    		// Animate loader off screen
    	});
	
	</script>
	
	

	</body>
	
	
</html>
