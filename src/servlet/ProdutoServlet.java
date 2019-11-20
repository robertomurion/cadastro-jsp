package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCategoria;
import beans.BeanProduto;
import dao.DaoCategoria;
import dao.DaoProduto;



@WebServlet("/pages/SalvarProduto")
public class ProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DaoProduto daoProduto = new DaoProduto();
	private DaoCategoria daoCategoria = new DaoCategoria();
    public ProdutoServlet() {
        super();

    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String idProduto = request.getParameter("idProduto");
		String acao = request.getParameter("acao");
		String idCategoria = request.getParameter("idCategoria");

		if (acao != null && acao.equalsIgnoreCase("delete")) {
			daoProduto.delete(idProduto);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/lista-produtos.jsp");
			// request.setAttribute("produtos", daoProduto.getLista());
			dispatcher.forward(request, response);
		} else 
			if (acao != null && acao.equalsIgnoreCase("editar")) {
			BeanProduto beanProduto = daoProduto.consultar(idProduto);
			List <BeanCategoria> beanCategoria = daoCategoria.getLista();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/cadastroproduto.jsp");
			request.setAttribute("product", beanProduto);
			request.setAttribute("categorias", beanCategoria);
			request.setAttribute("categoriaselecionada", idCategoria);
			dispatcher.forward(request, response);
		} else 
			if (acao !=null && acao.equalsIgnoreCase("listacategoria")) {
			List <BeanCategoria> beanCategoria = daoCategoria.getLista();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/cadastroproduto.jsp");
			request.setAttribute("categorias", beanCategoria);
			dispatcher.forward(request, response);
		}else
			if (acao == null || acao.isEmpty()) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/lista-produtos.jsp");
		// request.setAttribute("produtos", daoProduto.getLista());
		dispatcher.forward(request, response);
		}
		
	}

		
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String idProduto = request.getParameter("idProduto");
		String nome = request.getParameter("nome");
		String quantidade = request.getParameter("quantidade");
		String valor = request.getParameter("valor");
		String idCategoria = request.getParameter("categoria");
		
		
		if (acao != null && acao.equalsIgnoreCase("reset")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/lista-produtos.jsp");
			dispatcher.forward(request, response);

		} else {
			DaoProduto daoProduto = new DaoProduto();

			BeanProduto beanProduto = new BeanProduto();
			beanProduto.setIdProduto(idProduto.isEmpty() ? null : Long.parseLong(idProduto));
			beanProduto.setNome(nome);
			beanProduto.setQuantidade(quantidade.isEmpty() ? null : Double.parseDouble(quantidade));
			
			
			beanProduto.setValor(valor.isEmpty() ? null : Double.parseDouble(valor.replace(".", "").replace(",", ".")));
			beanProduto.setIdCategoria(idCategoria.isEmpty() ? null : Long.parseLong(idCategoria));
			
			
			//verificar se nome foi informado
			if (nome.isEmpty() || nome == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/cadastroproduto.jsp");
				request.setAttribute("msg",
						"O nome deve ser informado!");
				request.setAttribute("product", beanProduto);
				dispatcher.forward(request, response);
				return;
			}
			
			//verificar se quantidade foi informada
			if (quantidade.isEmpty() || quantidade == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/cadastroproduto.jsp");
				request.setAttribute("msg",
						"A quantidade deve ser informada!");
				request.setAttribute("product", beanProduto);
				dispatcher.forward(request, response);
				return;
			}
			
			//verificar se valor foi informado
			if (valor.isEmpty() || valor == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/cadastroproduto.jsp");
				request.setAttribute("msg",
						"O valor deve ser informado!");
				request.setAttribute("product", beanProduto);
				dispatcher.forward(request, response);
				return;
			}
			//verificar se categoria foi informado
			if (idCategoria.isEmpty() || idCategoria == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/cadastroproduto.jsp");
				request.setAttribute("msg",
						"A categoria do produto deve ser informada!");
				request.setAttribute("product", beanProduto);
				dispatcher.forward(request, response);
				return;
			}
			
			if (idProduto == null || idProduto.isEmpty()) {
				if (daoProduto.validarNome(nome)) {
					daoProduto.salvar(beanProduto);
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/cadastroproduto.jsp");
					request.setAttribute("msg",
							"O Produto com nome \"" + beanProduto.getNome() + "\" já existe. Tente outro por favor!");
					request.setAttribute("product", beanProduto);
					dispatcher.forward(request, response);
				}
			} else {
				if (daoProduto.validarNomeUpdate(nome, idProduto)) {
					daoProduto.atualizar(beanProduto);
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/cadastroproduto.jsp");
					request.setAttribute("msg",
							"O Produto com nome \"" + beanProduto.getNome() + "\" já existe. Tente outro por favor!");
					request.setAttribute("product", beanProduto);
					dispatcher.forward(request, response);
					return;
				}
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/lista-produtos.jsp");
			// request.setAttribute("produtos", daoProduto.getLista());
			dispatcher.forward(request, response);

		}
	}


}
