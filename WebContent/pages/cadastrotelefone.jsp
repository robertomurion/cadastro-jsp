<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../resources/css/listausuario.css">
<link rel="stylesheet" href="../resources/css/cadastro.css">
<meta charset="ISO-8859-1">
<title>Cadastro de Telefones</title>
</head>
<body>
	<a href="acessoliberado.jsp">Menu</a>
	<a href="lista-usuarios.jsp">Lista Usuários</a>
	<a href="LoginServlet?sair=true">Sair</a>
	<h3 style="color: red;">${msg}</h3>
	<form action="SalvarTelefone" method="post" id="formUser">
		<ul class="form-style-1">
			<li>
				<h2>Cadastro de Telefones</h2>
				<table>
					<tr>
						<td>id:</td>
						<td><input type="text" readonly="readonly" id="idPhone"
							placeholder="preenchimento automático" name="idPhone"
							value="${phone.idPhone}" class="field-long"></td>
					</tr>
					<tr>
						<td>usuario:</td>
						<td><input type="text" readonly="readonly" id="usuario"
							name="usuario" value="${usuario.idUser}" class="field-long"></td>
					</tr>
					<tr>
						<td>Nome:</td>
						<td><input type="text" readonly="readonly" id="nome"
							name="nome" value="${usuario.nome}"></td>
					</tr>
					<tr>
						<td>Telefone:</td>
						<td><input type="text" id="numero" name="numero"
							value="${phone.numero}"></td>
						<td>Tipo: <select id="tipo" name="tipo">
								<option value="">[--selecine--]</option>
								<option value="celular"
									${phone.tipo == 'celular' ? 'selected' : ''}>celular</option>
								<option value="comercial"
									${phone.tipo == 'comercial' ? 'selected' : ''}>comercial</option>
								<option value="residencial"
									${phone.tipo == 'residencial' ? 'selected' : ''}>residencial</option>
						</select></td>
					</tr>


					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"
							onclick="return validarCampos() ? true : false"></td>
					</tr>
				</table>
			</li>
		</ul>
	</form>

	<br>

	<div align="center" class="container">
		<table class="responsive-table">
			<caption>Lista de Telefones Cadastrados</caption>
			<tr>
				<th>Nome</th>
				<th>Telefone</th>
				<th>Tipo</th>
				<th>Excluir</th>
				<th>Editar</th>

			</tr>
			<!-- percorre contatos montando as linhas da tabela -->
			<c:forEach var="telefone" items="${telefones}" varStatus="id">
				<tr>
					<td style="width: 150px">${usuario.nome}</td>
					<td style="width: 150px">${telefone.numero}</td>
					<td style="width: 150px">${telefone.tipo}</td>
					<td style="width: 150px"><a href="javascript:func()"
						onclick="confirmacao('${telefone.idPhone}','${usuario.idUser}', '${telefone.numero}')"><img
							src="../resources/img/excluir.png" alt="Excluir" title="Excluir"
							width="20px" height="20px"></a></td>
					<td style="width: 150px"><a
						href="SalvarTelefone?acao=editar&idPhone=${telefone.idPhone}&idUser=${usuario.idUser}"><img
							src="../resources/img/editar.png" alt="Editar" title="Editar"
							width="20px" height="20px"></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("usuario").value == '') {
				alert('O campo Usuário precisa ser preenchido');
				return false;
			} else if (document.getElementById("nome").value == '') {
				alert('O campo Nome precisa ser preenchido');
				return false;
			} else if (document.getElementById("numero").value == '') {
				alert('O campo Telefone precisa ser preenchido');
				return false;
			} else

			if (document.getElementById("tipo").value == '') {
				alert('O campo Tipo precisa ser selecionado');
				return false;
			}
			return true;
		}

		function confirmacao(idPhone, idUser, numero) {
			var resposta = confirm("Deseja remover o telefone " + numero + "?");

			if (resposta == true) {
				window.location.href = "SalvarTelefone?acao=delete&idPhone="
						+ idPhone + "&idUser=" + idUser;
			}
		}
	</script>
</body>
</html>