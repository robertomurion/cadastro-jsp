package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCategoria;
import connection.SingleConnection;

public class DaoCategoria {
	private Connection connection;
	
	public DaoCategoria() {
		connection = SingleConnection.getConnection();
	}
	public List<BeanCategoria> getLista() {
		String sql = "select * from categoria ORDER BY categorianome ASC ";
		List<BeanCategoria> categoria = new ArrayList<BeanCategoria>();
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// criando o objeto BeanTelaLogin
				BeanCategoria beanCategoria = new BeanCategoria();
				beanCategoria.setCategoriaNome(rs.getString("categorianome"));
				beanCategoria.setIdCategoria(rs.getLong("idcategoria"));
				categoria.add(beanCategoria);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoria;
	}
	
	public BeanCategoria consultar(Long idCategoria) {
		String sql = "select * from categoria where idcategoria = '" + idCategoria + "'";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// criando o objeto BeanTelaLogin
				BeanCategoria beanCategoria = new BeanCategoria();
				beanCategoria.setCategoriaNome(rs.getString("categorianome"));
				beanCategoria.setIdCategoria(idCategoria);
				return beanCategoria;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
