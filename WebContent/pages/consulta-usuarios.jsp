<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista de Usuários</title>
</head>
<link rel="stylesheet" href="../resources/css/listausuario.css">
<link rel="stylesheet" href="../resources/css/cadastro.css">
<body>
	<a href="../acessoliberado.jsp">Menu</a>
	<a href="cadastrousuario.jsp">Cadastro Usuário</a>
	<a href="../LoginServlet?sair=true">Sair</a>
	<jsp:useBean id="dao" class="dao.DaoUsuario" />
	<br>
	<div class="container">
		<h3 style="color: red;">${msg}</h3>
		<form action="ConsultarUsuario" method="post" id="formUser">
			<ul class="form-style-1">
				<li>
					<table>
						<tr>
							<td>Consulta:</td>
							<td><input type="text" id="pesquisa" name="pesquisa"></td>
							<td><input type="submit" value="Pesquisar"></td>
						</tr>
					</table>
				</li>
			</ul>
		</form>

		<table class="responsive-table">
			<caption>Lista de Usuários Cadastrados</caption>
			<tr>
				<!--	<th>Usuario</th>	-->
				<th>Nome</th>
				<!--	<th>Fone</th>    -->
				<th>CEP</th>
				<th>Rua</th>
				<th>Bairro</th>
				<th>Cidade</th>
				<th>Estado</th>
				<th>IBGE</th>
				<th>Fones</th>
				<th>Arquivo</th>
				<!--	<th>ArquivoNome</th>   -->
				<th>Editar</th>
				<th>Excluir</th>

			</tr>
			<!-- percorre contatos montando as linhas da tabela -->
			<c:forEach var="usuario" items="${usuarios}" varStatus="id">
				<tr>
					<!-- 	<td style="width: 150px">${usuario.login}</td> -->
					<td style="width: 150px">${usuario.nome}</td>
					<!-- 	<td style="width: 150px">${usuario.fone}</td>    -->
					<td style="width: 150px">${usuario.cep}</td>
					<td style="width: 150px">${usuario.rua}</td>
					<td style="width: 150px">${usuario.bairro}</td>
					<td style="width: 150px">${usuario.cidade}</td>
					<td style="width: 150px">${usuario.estado}</td>
					<td style="width: 150px">${usuario.ibge}</td>
					<td style="width: 150px"><a
						href="SalvarTelefone?idUser=${usuario.idUser}"><img
							src="../resources/img/fone.png" alt="Fone" title="Fone" width="20px"
							height="20px"></a></td>
					<td style="width: 150px"><c:if
							test="${!empty usuario.contentType}">
							<a href="SalvarUsuario?acao=download&idUser=${usuario.idUser}">
						</c:if><img
						src='<c:out value = "${(empty usuario.nomeArquivo)  ? 
							'../resources/img/userStandard.png' : (usuario.contentType == 
							'image/jpeg' || usuario.contentType == 'image/png' ?
							 usuario.fotoBase64Miniatura : '../resources/img/file.png')}"/>'
						width="40px" height="40px"
						<c:if
							test="${empty usuario.nomeArquivo}"> onclick="alert('Não possui arquivo')" </c:if> />
						<c:if test="${!empty usuario.nomeArquivo}">
							</a>
						</c:if></td>
					<!-- 	<td style="width: 150px">${usuario.nomeArquivo}</td>	-->
					<td style="width: 150px"><a
						href="SalvarUsuario?acao=editar&idUser=${usuario.idUser}"><img
							src="../resources/img/editar.png" alt="Editar" title="Editar"
							width="20px" height="20px"></a></td>
					<td style="width: 150px"><a href="javascript:func()"
						onclick="confirmacao('${usuario.idUser}','${usuario.nome}')"><img
							src="../resources/img/excluir.png" alt="Excluir" title="Excluir"
							width="20px" height="20px"></a></td>
					
				</tr>
			</c:forEach>
		</table>
	</div>
	<script type="text/javascript">
		function confirmacao(id, nome) {
			var resposta = confirm("Deseja remover o usuário " + nome + "?");

			if (resposta == true) {
				window.location.href = "SalvarUsuario?acao=delete&idUser=" + id;
			}
		}
	</script>
</body>
</html>