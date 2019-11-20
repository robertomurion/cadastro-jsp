<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista de Produtos</title>
</head>
<link rel="stylesheet" href="../resources/css/listausuario.css">
<body>
	<a href="acessoliberado.jsp">Menu</a>
	<a href="SalvarProduto?acao=listacategoria">Cadastro Produto</a>
	<a href="../LoginServlet?sair=true">Sair</a>
	<jsp:useBean id="dao" class="dao.DaoProduto" />
	<br>
	<div class="container">
		<table class="responsive-table">
			<caption>Lista de Produtos Cadastrados</caption>
			<tr>
				<th>Nome</th>
				<th>Quantidade</th>
				<th>Valor</th>
				<th>Excluir</th>
				<th>Editar</th>

			</tr>
			<!-- percorre contatos montando as linhas da tabela -->
			<c:forEach var="produto" items="${dao.lista}" varStatus="id">
				<tr>
					<td style="width: 150px">${produto.nome}</td>
					<td style="width: 150px">${produto.quantidade}</td>
					<fmt:setLocale value="pt_BR" />
					<td style="width: 150px"><fmt:formatNumber type="currency"
							value="${produto.valor}" /></td>
					<td style="width: 150px"><a
						href="javascript:func()" onclick="confirmacao('${produto.idProduto}','${produto.nome}')"><img
							src="../resources/img/excluir.png" alt="Excluir" title="Excluir"
							width="20px" height="20px"></a></td>
					<td style="width: 150px"><a
						href="SalvarProduto?acao=editar&idProduto=${produto.idProduto}&idCategoria=${produto.idCategoria}"><img
							src="../resources/img/editar.png" alt="Editar" title="Editar"
							width="20px" height="20px"></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script type="text/javascript">
		function confirmacao(id, nome) {
			var resposta = confirm("Deseja remover o produto " + nome + "?");

			if (resposta == true) {
				window.location.href = "SalvarProduto?acao=delete&idProduto=" + id;
			}
		}
	</script>
</body>
</html>