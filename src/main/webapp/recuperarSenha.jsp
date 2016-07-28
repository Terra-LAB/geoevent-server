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

<title>GeoCollector | Resetar Senha</title>

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
	href="${pageContext.request.contextPath}/css/login.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/normalize.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.css">

</head>

<body class="background">

	<div class="container login">
		<div class="row">
			<div class="col-md-12">
				<div class="login-container">

					<div id="aviso" style=""></div>

					<div class="logo">
						<img src="${pageContext.request.contextPath}/images/logo/geo.png">
					</div>

					<h3 class="text-center login-title">Resetar Senha</h3>
					<hr />
					<div class="row" style="margin-right: 0px;">
						<p class="font padding15 size16">Problemas com seu acesso?
							Insira seu email abaixo e enviaremos uma nova senha para você.</p>
						<form name="reset-form" id="reset-form" class="form-reset-pass">
							<input type="text" class="form-control above below font"
								id="email" name="email" placeholder="Email" required autofocus>
							<button id="senha" style="color: #FFFFFF; margin-top: 10px;"
								class="btn btn-login" type="submit">Resetar</button>
						</form>
					</div>

					<div class="row center">
						<span style="display: none;" id="loader">
							<p class="font text-center">
								<label> Resetando... <img width="40px;"
									src="${pageContext.request.contextPath}/images/loading.gif" /></label>
							</p>
						</span>

					</div>

					<hr />
					<div class="row" style="margin-right: 0px;">
						<div class="col-md-5">
							<p class="font">
								<a href="${pageContext.request.contextPath}/login.jsp">
									Voltar para o login</a>
							</p>
						</div>

						<div class="col-md-7 text-right">
							<p class="font">
								Não tem registro? <a
									href="${pageContext.request.contextPath}/cadastro.jsp">
									Cadastre agora!</a>
							</p>
						</div>
					</div>

				</div>
			</div>

		</div>
	</div>
</body>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-select.js"></script>

<script>
	$("#reset-form").submit(function() {
		reset();
		return false;
	});

	function reset() {
			$("#loader").show();
			$.post(
						"${pageContext.request.contextPath}/resetPassword",
						$("#reset-form").serialize(),
						function(data, textStatus, xhrObject) {
							alert("Clico no botao");
							if (data === "email-invalido") {
								$("#aviso")
										.html(
												"<div class='alert alert-danger font-opensans'> <b>Atenção!</b> <br> Email inexistente. Faça seu cadastro <a href='${pageContext.request.contextPath}/cadastro.jsp'> aqui </a>. </div>");
							}
							if (data === "password-trocado") {
								redirectUrl = '${pageContext.request.contextPath}/login.jsp?passwordUpdated=true';
								window.location = redirectUrl;
							}
				   	 		$("#loader").hide();

						});
	}
</script>

</html>
