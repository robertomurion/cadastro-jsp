<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="../resources/js/jquery.min.js" type="text/javascript"></script>
<script src="../resources/js/jquery.maskMoney.js" type="text/javascript"></script>
<title>Cadastro de Produtos</title>
</head>
<link rel="stylesheet" href="../resources/css/cadastro.css">
<body>
	<a href="acessoliberado.jsp">Menu</a>
	<a href="../LoginServlet?sair=true">Sair</a>
	<h3 style="color: red;">${msg}</h3>
	<jsp:useBean id="dao" class="dao.DaoCategoria" />
	<form action="SalvarProduto" method="post" id="formUser">
		<ul class="form-style-1">
			<li>
				<h2>Cadastro de Produtos</h2>
				<table>
					<tr>
						<td>Id:</td>
						<td><input type="text" readonly="readonly" id="idProduto"
							name="idProduto" placeholder="preenchimento automático"
							value="${product.idProduto}" class="field-long"></td>
					</tr>
					<tr> 
						<td>Categoria:</td>    
						<td><select id="categoria" name="categoria" style="width:187px">
								<option value="">[----------selecione----------]</option>
								<c:forEach var="categoria" items="${categorias}" varStatus="id">
								<option value="${categoria.idCategoria}" ${categoria.idCategoria == categoriaselecionada  ? 'selected' : ''}>${categoria.categoriaNome}  </option>
								</c:forEach>
						</select></td>
					</tr>
					
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							value="${product.nome}"></td>
					</tr>
					<tr>
						<td>Quantidade:</td>
						<td><input type="number" id="quantidade" name="quantidade"
							value="${product.quantidade}"></td>
					</tr>
					<tr>
						<td>Valor R$:</td>
						<td><input type="text" id="valor" name="valor" maxlength="20"
							data-thousands="." data-decimal="," value="${product.valorEmTexto}"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"
							onclick="return validarCampos() ? true : false"> <input
							type="submit" value="Cancelar"
							onclick="document.getElementById('formUser').action='SalvarProduto?acao=reset'"></td>
					</tr>
				</table>
			</li>
		</ul>
	</form>
	<script type="text/javascript">
		$(function() {
			$('#valor').maskMoney();
		})
		function validarCampos() {
			if (document.getElementById("nome").value == '') {
				alert('O campo Nome precisa ser preenchido');
				return false;
			} else

			if (document.getElementById("quantidade").value == '') {
				alert('O campo Quantidade precisa ser preenchido');
				return false;
			} else

			if (document.getElementById("valor").value == '') {
				alert('O campo Valor precisa ser preenchido');
				return false;
			}
			return true;
		}
	</script>
</body>
</html>