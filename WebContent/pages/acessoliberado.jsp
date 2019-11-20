<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Menu</title>
</head>
<link rel="stylesheet" href="/cadastro-jsp/resources/css/cadastro.css">
<body>
	<a href="/cadastro-jsp/LoginServlet?sair=true">Sair</a>
	<div class="form-style-1" style="text-align:center;">
		<h1>Seja bem-vindo ao sistema</h1>
		<table style="text-align:center;">
			<tr>
				<td width="200px"><a href="/cadastro-jsp/pages/cadastrousuario.jsp"><img
						src="/cadastro-jsp/resources/img/user.png" alt="Cadastro de Usuário"
						title="Cadastro de Usuário" width="60px" height="60px" style="text-align:center;"></a></td>
				<td width="200px"><a href="/cadastro-jsp/pages/SalvarProduto?acao=listacategoria"><img
						src="/cadastro-jsp/resources/img/goods.png" alt="Cadastro de Produto"
						title="Cadastro de Produto" width="60px" height="60px" style="text-align:center;"></a></td>
			</tr>
			<tr>
				<td>Cadastro de Usuário</td>
				<td>Cadastro de Produto</td>
			</tr>
		</table>
	</div>
</body>
</html>