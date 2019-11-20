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
import dao.DaoUsuario;

@WebServlet("/pages/ConsultarUsuario")
public class PesquisaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DaoUsuario daoUsuario = new DaoUsuario();
	
    public PesquisaServlet() {
        super();
    }    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String nome = request.getParameter("pesquisa");
		
		List<BeanUsuario> usuarios = daoUsuario.getLista(nome.toLowerCase());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/consulta-usuarios.jsp");
		request.setAttribute("usuarios", usuarios);
		dispatcher.forward(request, response);
	}

}
