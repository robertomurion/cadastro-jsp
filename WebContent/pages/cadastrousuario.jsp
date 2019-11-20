<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Usuários</title>
<!-- Adicionando JQuery remoto
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script> -->

<!-- Adicionando JQuery local -->
<script src="../resources/js/jquery.min.js" type="text/javascript"></script>
</head>
<link rel="stylesheet" href="../resources/css/cadastro.css">
<body>
	<a href="/cadastro-jsp/pages/acessoliberado.jsp">Menu</a>
	<a href="../LoginServlet?sair=true">Sair</a>
	<h3 style="color: red;">${msg}</h3>
	<form action="SalvarUsuario" method="post" id="formUser"
		enctype="multipart/form-data">
		<ul class="form-style-1">
			<li>
				<h2>Cadastro de Usuários</h2>
				<table>
					<tr>
						<td>IdUser:</td>
						<td><input type="text" readonly="readonly" id="idUser"
							name="idUser" style="width: 188px"
							placeholder="preenchimento automático" value="${user.idUser}"
							class="field-long"></td>
						<td>Ativo:</td>
						<td><input type="checkbox" name="ativo" id="ativo"
							value="true" ${user.ativo ? 'checked' : ''}></td>
					</tr>
					<tr>
						<td>Login:</td>
						<td><input type="text" id="login" name="login"
							value="${user.login}"></td>
						<td>Senha</td>
						<td><input type="password" id="senha" name="senha"
							value="${user.senha}"></td>

					</tr>
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							value="${user.nome}"></td>

						<td>Sexo:</td>
						<td><input type="radio" name="sexo" id="sexo"
							value="masculino" ${user.sexo == 'masculino' ? 'checked' : ''}>Masculino
							<input type="radio" name="sexo" id="sexo" value="feminino"
							${user.sexo == 'feminino'? 'checked' : ''}>Feminino</td>

						<!-- 		<td>Fone:</td>
						<td><input type="text" id="fone" name="fone"
							value="${user.fone}"></td>
			 -->
					</tr>
					<tr>
						<td>CEP:</td>
						<td><input type="text" id="cep" name="cep"
							onblur="consultaCep()" value="${user.cep}"></td>
						<td>Rua:</td>
						<td><input type="text" id="rua" name="rua"
							value="${user.rua}"></td>

					</tr>
					<tr>
						<td>Bairro:</td>
						<td><input type="text" id="bairro" name="bairro"
							value="${user.bairro}"></td>
						<td>Cidade:</td>
						<td><input type="text" id="cidade" name="cidade"
							value="${user.cidade}"></td>
					</tr>
					<tr>
						<td>Estado:</td>
						<td><input type="text" id="estado" name="estado"
							value="${user.estado}"></td>
						<td>IBGE:</td>
						<td><input type="text" id="ibge" name="ibge"
							value="${user.ibge}"></td>
					</tr>
					<tr>
						<td>Arquivo:</td>
						<td><input type="file" name="arquivo" id="arquivo"
							style="width: 170px"></td>

					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"
							onclick="return validarCampos() ? true : false"> <input
							type="submit" value="Listar"
							onclick="document.getElementById('formUser').action='SalvarUsuario?acao=reset'"></td>
					</tr>
				</table>
			</li>
		</ul>
	</form>
	<script type="text/javascript">
		

		function validarCampos() {
			
			document.getElementById("sexo").required = "required";

			if (document.getElementById("login").value == '') {
				alert('O campo Login precisa ser preenchido');
				return false;
			} else

			if (document.getElementById("senha").value == '') {
				alert('O campo Senha precisa ser preenchido');
				return false;
			} else

			if (document.getElementById("nome").value == '') {
				alert('O campo Nome precisa ser preenchido');
				return false;
			} else if (document.getElementById("sexo").value == '') {
				alert('O campo Sexo precisa ser preenchido');
				return false;
			} else

			if (document.getElementById("fone").value == '') {
				alert('O campo Fone precisa ser preenchido');
				return false;
			} else

			if (document.getElementById("cep").value == '') {
				alert('O campo CEP precisa ser preenchido');
				return false;
			} else

			if (document.getElementById("rua").value == '') {
				alert('O campo Rua precisa ser preenchido');
				return false;
			} else

			if (document.getElementById("bairro").value == '') {
				alert('O campo Bairro precisa ser preenchido');
				return false;
			} else

			if (document.getElementById("cidade").value == '') {
				alert('O campo Cidade precisa ser preenchido');
				return false;
			} else

			if (document.getElementById("estado").value == '') {
				alert('O campo Estado precisa ser preenchido');
				return false;
			} else

			if (document.getElementById("ibge").value == '') {
				alert('O campo IBGE precisa ser preenchido');
				return false;
			}
			return true;
		}

		function consultaCep() {

			function limpa_formulário_cep() {
				// Limpa valores do formulário de cep.
				$("#rua").val("");
				$("#bairro").val("");
				$("#cidade").val("");
				$("#estado").val("");
				$("#ibge").val("");
			}

			//Nova variável "cep" somente com dígitos.
			var cep = $("#cep").val().replace(/\D/g, '');

			//Verifica se campo cep possui valor informado.
			if (cep != "") {

				//Expressão regular para validar o CEP.
				var validacep = /^[0-9]{8}$/;

				//Valida o formato do CEP.
				if (validacep.test(cep)) {

					//Preenche os campos com "..." enquanto consulta webservice.
					$("#rua").val("...");
					$("#bairro").val("...");
					$("#cidade").val("...");
					$("#estado").val("...");
					$("#ibge").val("...");

					//Consulta o webservice viacep.com.br/
					$.getJSON("https://viacep.com.br/ws/" + cep
							+ "/json/?callback=?", function(dados) {

						if (!("erro" in dados)) {
							$("#cep").val(cep);
							$("#rua").val(dados.logradouro);
							$("#bairro").val(dados.bairro);
							$("#cidade").val(dados.localidade);
							$("#estado").val(dados.uf);
							$("#ibge").val(dados.ibge);

						} else {
							//CEP pesquisado não foi encontrado.
							limpa_formulário_cep();
							alert("CEP não encontrado.");
						}
					});
				} else {
					//cep é inválido.
					limpa_formulário_cep();
					alert("Formato de CEP inválido.");
				}
			} else {
				//cep sem valor, limpa formulário.
				limpa_formulário_cep();
				alert("O campo CEP precisa ser preenchido");
			}

		}
	</script>
</body>
</html>