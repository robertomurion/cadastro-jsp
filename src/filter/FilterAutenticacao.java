package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



import beans.BeanUsuario;

@WebFilter(urlPatterns= {"/pages/*"})
public class FilterAutenticacao implements Filter {

    public FilterAutenticacao() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		//String urlParaAutenticar = req.getServletPath();
		
		BeanUsuario beanUsuario = (BeanUsuario)session.getAttribute("usuario");
		//if (beanUsuario == null && !urlParaAutenticar.equalsIgnoreCase("/pages/LoginServlet")) {
		if (beanUsuario == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/acessonegado.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
