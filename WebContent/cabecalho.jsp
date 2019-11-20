<jsp:useBean id="calcula" class="beans.BeanCursoJsp" type="beans.BeanCursoJsp" scope="page" />
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Nome: ${param.nome}
	<br/>
	Ano: ${param.ano}
	<br/>
	Sexo: ${param.sexo}
	<br/>
	<%-- <jsp:setProperty property="*" name="calcula" />
	<h3>Cabecalho</h3>
	<jsp:getProperty property="nome" name="calcula" />
	<br/>
	<jsp:getProperty property="ano" name="calcula" />
	<br/>
	<jsp:getProperty property="sexo" name="calcula" /> --%>
</body>
</html>