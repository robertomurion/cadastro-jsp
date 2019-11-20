package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanUsuario;
import beans.BeanTelefone;
import dao.DaoTelefone;
import dao.DaoUsuario;

@WebServlet(name = "SalvarTelefone", urlPatterns = { "/pages/SalvarTelefone" })
public class TelefoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();
	private DaoTelefone daoTelefone = new DaoTelefone();

	public TelefoneServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idPhone = request.getParameter("idPhone");
		String idUser = request.getParameter("idUser");
		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("delete")) {
			daoTelefone.delete(idPhone);
		} else if (acao != null && acao.equalsIgnoreCase("editar")) {
			BeanTelefone beanTelefone = daoTelefone.consultar(idPhone);
			request.setAttribute("phone", beanTelefone);
		} else if  (idUser == null ) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/lista-usuarios.jsp");
			// request.setAttribute("usuarios", daoUsuario.getLista());
			dispatcher.forward(request, response);
			return;
		}
		BeanUsuario beanUsuario = daoUsuario.consultar(idUser);
		request.setAttribute("usuario", beanUsuario);
		List<BeanTelefone> telefones = daoTelefone.getLista(idUser);
		request.setAttribute("telefones", telefones);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/cadastrotelefone.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idPhone = request.getParameter("idPhone");
		String idUser = request.getParameter("usuario");
		String numero = request.getParameter("numero");
		String tipo = request.getParameter("tipo");

		DaoTelefone daoTelefone = new DaoTelefone();

		BeanTelefone beanTelefone = new BeanTelefone();
		beanTelefone.setIdPhone(!idPhone.isEmpty() ? Long.parseLong(idPhone) : null);
		beanTelefone.setIdUser(Long.parseLong(idUser));
		beanTelefone.setNumero(numero);
		beanTelefone.setTipo(tipo);
		if (idPhone == null || idPhone.isEmpty()) {
			daoTelefone.salvar(beanTelefone);
		} else {
			daoTelefone.atualizar(beanTelefone);
		}
		BeanUsuario beanUsuario = daoUsuario.consultar(idUser);
		request.setAttribute("usuario", beanUsuario);
		List<BeanTelefone> telefones = daoTelefone.getLista(idUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/cadastrotelefone.jsp");
		request.setAttribute("telefones", telefones);
		request.setAttribute("msg", "Cadastro salvo com sucesso!");
		dispatcher.forward(request, response);
	}

}
