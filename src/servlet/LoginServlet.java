package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BeanUsuario;
//import beans.BeanTelaLogin;
import dao.DaoLogin;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoLogin daoLogin = new DaoLogin();
	private BeanUsuario beanUsuario = new BeanUsuario();

	public LoginServlet() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sair = request.getParameter("sair");

		if (Boolean.parseBoolean(sair)) {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			session.invalidate();

			response.sendRedirect("telalogin.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		//String url = request.getParameter("url");
		
		//if (url == null || url.isEmpty()) { url ="/pages/acessoliberado.jsp";}
		
		beanUsuario.setLogin(login);
		beanUsuario.setSenha(senha);

		try {
			if (login != null && !login.isEmpty()) {

				if (daoLogin.validarLogin(beanUsuario)) {
					HttpServletRequest req = (HttpServletRequest) request;
					HttpSession session = req.getSession();
					session.setAttribute("usuario", beanUsuario);

					RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/acessoliberado.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("acessonegado.jsp");
					dispatcher.forward(request, response);
				}

			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("telalogin.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
