package servlet;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.apache.tomcat.util.codec.binary.Base64;

import beans.BeanUsuario;
import dao.DaoUsuario;

@WebServlet("/pages/SalvarUsuario")
@MultipartConfig
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	public UsuarioServlet() {
		super();

	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idUser = request.getParameter("idUser");
		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("delete")) {
			daoUsuario.delete(idUser);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/lista-usuarios.jsp");
			// request.setAttribute("usuarios", daoUsuario.getLista());
			dispatcher.forward(request, response);
			
		} else if (acao != null && acao.equalsIgnoreCase("editar")) {
			BeanUsuario beanUsuario = daoUsuario.consultar(idUser);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/cadastrousuario.jsp");
			request.setAttribute("user", beanUsuario);
			dispatcher.forward(request, response);
			
		} else if (acao != null && acao.equalsIgnoreCase("download")) {
			BeanUsuario beanUsuario = daoUsuario.consultar(idUser);
			response.setHeader("Content-Disposition",
					"attachment;filename=arquivo." + beanUsuario.getContentType().split("\\/")[1]);

			/* Converte a base64 da imagem do banco para byte[] */
			byte[] imagemFotoBytes = Base64.decodeBase64(beanUsuario.getArquivoBase64());
			/* Coloca os bytes em um objeto de entrada para preocessar */
			InputStream is = new ByteArrayInputStream(imagemFotoBytes);
			/* Inicio da resposta para o navegador */
			int read = 0;
			byte[] bytes = new byte[1024];
			OutputStream os = response.getOutputStream();
			while ((read = is.read(bytes)) != -1) {
				os.write(bytes, 0, read);
			}
			os.flush();
			os.close();

			
		}else if (acao == null || acao.isEmpty()) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/lista-usuarios.jsp");
		// request.setAttribute("usuarios", daoUsuario.getLista());
		dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String idUser = request.getParameter("idUser");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String nome = request.getParameter("nome");
		String fone = request.getParameter("fone");
		String cep = request.getParameter("cep");
		String rua = request.getParameter("rua");
		String bairro = request.getParameter("bairro");
		String cidade = request.getParameter("cidade");
		String estado = request.getParameter("estado");
		String ibge = request.getParameter("ibge");
		String ativo = request.getParameter("ativo");
		String sexo = request.getParameter("sexo");
		
		

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/lista-usuarios.jsp");
			dispatcher.forward(request, response);

		} else {
			DaoUsuario daoUsuario = new DaoUsuario();

			BeanUsuario beanUsuario = new BeanUsuario();
			beanUsuario.setIdUser(!idUser.isEmpty() ? Long.parseLong(idUser) : null);
			beanUsuario.setLogin(login);
			beanUsuario.setSenha(senha);
			beanUsuario.setNome(nome);
			beanUsuario.setFone(fone);
			beanUsuario.setCep(cep);
			beanUsuario.setRua(rua);
			beanUsuario.setBairro(bairro);
			beanUsuario.setCidade(cidade);
			beanUsuario.setEstado(estado);
			beanUsuario.setIbge(ibge);
			beanUsuario.setAtivo(ativo == null ? false : true);
			beanUsuario.setSexo(sexo);
			
			// verificar se login foi informado
			if (login.isEmpty() || login == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/cadastrousuario.jsp");
				request.setAttribute("msg", "O login deve ser informado!");
				request.setAttribute("user", beanUsuario);
				dispatcher.forward(request, response);
				return;
			}
			// verificar se senha foi informada
			if (senha.isEmpty() || senha == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/cadastrousuario.jsp");
				request.setAttribute("msg", "A senha deve ser informada!");
				request.setAttribute("user", beanUsuario);
				dispatcher.forward(request, response);
				return;
			}

			// verificar se nome foi informado
			if (nome.isEmpty() || nome == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/cadastrousuario.jsp");
				request.setAttribute("msg", "O nome deve ser informado!");
				request.setAttribute("user", beanUsuario);
				dispatcher.forward(request, response);
				return;
			}
			
			// verificar se cep foi informado
			if (cep.isEmpty() || cep == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/cadastrousuario.jsp");
				request.setAttribute("msg", "O campo CEP deve ser informado!");
				request.setAttribute("user", beanUsuario);
				dispatcher.forward(request, response);
				return;
			}
			// verificar se rua foi informado
			if (rua.isEmpty() || rua == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/cadastrousuario.jsp");
				request.setAttribute("msg", "O campo Rua deve ser informado!");
				request.setAttribute("user", beanUsuario);
				dispatcher.forward(request, response);
				return;
			}
			// verificar se bairro foi informado
			if (bairro.isEmpty() || bairro == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/cadastrousuario.jsp");
				request.setAttribute("msg", "O campo Bairro deve ser informado!");
				request.setAttribute("user", beanUsuario);
				dispatcher.forward(request, response);
				return;
			}
			// verificar se cidade foi informado
			if (cidade.isEmpty() || cidade == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/cadastrousuario.jsp");
				request.setAttribute("msg", "O campo Cidade deve ser informado!");
				request.setAttribute("user", beanUsuario);
				dispatcher.forward(request, response);
				return;
			}
			// verificar se estado foi informado
			if (estado.isEmpty() || estado == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/cadastrousuario.jsp");
				request.setAttribute("msg", "O campo Estado deve ser informado!");
				request.setAttribute("user", beanUsuario);
				dispatcher.forward(request, response);
				return;
			}
			// verificar se ibge foi informado
			if (ibge.isEmpty() || ibge == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/cadastrousuario.jsp");
				request.setAttribute("msg", "O campo IBGE deve ser informado!");
				request.setAttribute("user", beanUsuario);
				dispatcher.forward(request, response);
				return;
			}

			// Inicio File upload de arquivo
			if (ServletFileUpload.isMultipartContent(request)) {
				Part arquivo = request.getPart("arquivo");
				byte[] bytesImagem = converteStreamParaByte(arquivo.getInputStream());
				String arquivoBase64 = Base64.encodeBase64String(bytesImagem);
				beanUsuario.setArquivoBase64(arquivoBase64);
				beanUsuario.setContentType(arquivo.getContentType());
				beanUsuario.setNomeArquivo(arquivo.getSubmittedFileName().isEmpty() ? null : arquivo.getSubmittedFileName());
				//beanUsuario.setNomeArquivo(arquivo.getName().isEmpty() ? null : arquivo.getName());
				
			
				/*Inicio miniatura imagem*/
				if ((beanUsuario.getContentType().split("\\/")[1]).equalsIgnoreCase("png") || (beanUsuario.getContentType().split("\\/")[1]).equalsIgnoreCase("jpeg") ){
				/*Transformar em um bufferedImage*/
					BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(bytesImagem)); 
				/*Pega o tipo de imagem*/
					int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();
				/*Cria imagem em miniatura*/
					BufferedImage resizImage = new BufferedImage(100,100,type);
					Graphics2D g = resizImage.createGraphics();
					g.drawImage(bufferedImage,0,0,100,100,null);
					g.dispose();
				/*Escrever imagem novamente*/
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ImageIO.write(resizImage,"png",baos);
					
					String miniaturaBase64 = "data:image/png;base64," + DatatypeConverter.printBase64Binary(baos.toByteArray());
					beanUsuario.setFotoBase64Miniatura(miniaturaBase64);
				}
				/*Fim miniatura imagem*/
			}
			// Fim File upload de arquivo

			if (idUser == null || idUser.isEmpty()) {
				if (daoUsuario.validarLogin(login)) {
					daoUsuario.salvar(beanUsuario);
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/cadastrousuario.jsp");
					request.setAttribute("msg",
							"O login \"" + beanUsuario.getLogin() + "\" já existe. Tente outro por favor!");
					request.setAttribute("user", beanUsuario);
					dispatcher.forward(request, response);
				}
			} else {
				if (daoUsuario.validarLogin(login, idUser)) {
					if (beanUsuario.getArquivoBase64() == null || beanUsuario.getArquivoBase64().isEmpty()) {
						daoUsuario.atualizar(beanUsuario);
					} else {
						daoUsuario.atualizarComArquivo(beanUsuario);
					}
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/cadastrousuario.jsp");
					request.setAttribute("msg",
							"O login \"" + beanUsuario.getLogin() + "\" já existe. Tente outro por favor!");
					request.setAttribute("user", beanUsuario);
					dispatcher.forward(request, response);
					return;
				}
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/lista-usuarios.jsp");
			// request.setAttribute("usuarios", daoUsuario.getLista());
			request.setAttribute("msg", "Cadastro salvo com sucesso!");
			dispatcher.forward(request, response);

		}
	}

	/* Converte a entrada de fluxo de dados do arquivo para um byte[] */
	private byte[] converteStreamParaByte(InputStream arquivo) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int reads = arquivo.read();
		while (reads != -1) {
			baos.write(reads);
			reads = arquivo.read();
		}
		return baos.toByteArray();
	}

	
}
