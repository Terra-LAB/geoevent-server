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
	          
				<img style="width: 90px; margin-left:50px;"src="${pageContext.request.contextPath}/images/logo/geo_semsombra.png">
			
			</div>
			
			<div id="navbar" class="navbar-collapse collapse">
				<div class="menu dropdown">
					<ul class="nav navbar-nav navbar-right">
						<li> <a href="home.jsp"> <span> Minha Coleta </span></a> </li>
						<li> <a href="exportarColeta.jsp"> <span> Exportar Coleta </span></a> </li>
						<li> <a href="bancoEnderecos.jsp"> <span> Banco de Endereços </span></a> </li>
						<li> <a href="${pageContext.request.contextPath}/logout"> <span> <span class="glyphicon glyphicon-log-in" aria-hidden="true"></span> Sair </span> </a> </li>
					</ul>
				</div>
			</div>
		</nav>
		
	</div>
</div>
