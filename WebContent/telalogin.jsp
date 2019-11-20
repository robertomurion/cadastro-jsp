<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/cadastro-jsp/resources/css/estilo.css">
<meta charset="ISO-8859-1">
<title>Acesso ao Sistema</title>
</head>
<body>
	<div class="Login-page" style = "text-align: center; ">
			<h2>Projeto desenvolvido em: </h2>
			<h3>JSP + Servlet + JDBC</h3>
			<span ><b>Usuário:</b> admin <br/><b>Senha:</b> admin</span>
		<div class='form' >
			<form action="LoginServlet" method="post">
		<!-- 	<input hidden="hidden" id="url" name= "url" value="${param.url}">    -->
				Login: <input type="text" id="login" name="login"> <br />
				Senha: <input type="password" id="senha" name="senha"> <br />
				<button type="submit" value="Logar">Logar</button>
			</form>
		</div>
	</div>
</body>
</html>