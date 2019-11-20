package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanProduto;
import connection.SingleConnection;

public class DaoProduto {

	private Connection connection;

	public DaoProduto() {
		connection = SingleConnection.getConnection();
	}
	
	
	public void salvar(BeanProduto beanProduto) {
		String sql = "INSERT INTO produto (nome, quantidade, valor, idcategoria) values(?, ?, ?, ?)";
		try (PreparedStatement insert = connection.prepareStatement(sql)) {
			insert.setString(1, beanProduto.getNome());
			insert.setDouble(2, beanProduto.getQuantidade());
			insert.setDouble(3, beanProduto.getValor());
			insert.setLong(4, beanProduto.getIdCategoria());
			insert.execute();
			// connection.commit(); // salva no banco (ativar qdo autocommint estiver False)
			connection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public List<BeanProduto> getLista() {
		String sql = "select * from produto";
		List<BeanProduto> produtos = new ArrayList<BeanProduto>();
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// criando o objeto BeanTelaLogin
				BeanProduto beanProduto = new BeanProduto();
				beanProduto.setIdProduto(rs.getLong("idproduto"));
				beanProduto.setNome(rs.getString("nome"));
				beanProduto.setQuantidade(rs.getDouble("quantidade"));
				beanProduto.setValor(rs.getDouble("valor"));
				beanProduto.setIdCategoria(rs.getLong("idcategoria"));
				produtos.add(beanProduto);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produtos;
	}

	public void delete(String idProduto) {

		String sql = "Delete from produto where idproduto = '" + idProduto + "'";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void atualizar(BeanProduto beanProduto) {
		String sql = "update produto set nome=?, quantidade=?, valor=?, idcategoria=? where idproduto = " + beanProduto.getIdProduto();
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, beanProduto.getNome());
			stmt.setDouble(2, beanProduto.getQuantidade());
			stmt.setDouble(3, beanProduto.getValor());
			stmt.setLong(4, beanProduto.getIdCategoria());
			stmt.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();

			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
	}

	public BeanProduto consultar(String idProduto) {
		String sql = "select * from produto where idproduto = '" + idProduto + "'";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// criando o objeto BeanTelaLogin
				BeanProduto beanProduto = new BeanProduto();
				beanProduto.setIdProduto(rs.getLong("idproduto"));
				beanProduto.setNome(rs.getString("nome"));
				beanProduto.setQuantidade(rs.getDouble("quantidade"));
				beanProduto.setValor(rs.getDouble("valor"));
				beanProduto.setIdCategoria(rs.getLong("idcategoria"));
				return beanProduto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean validarNome(String nome) {

		String sql = "select * from produto where nome = '" + nome + "'";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			return (rs.next()? false:true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean validarNomeUpdate(String nome, String idProduto) {

		String sql = "select * from produto where nome = '" + nome + "' and idproduto <> '" + idProduto + "'";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			return (rs.next()? false:true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
