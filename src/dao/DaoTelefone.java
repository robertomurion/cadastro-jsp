package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import beans.BeanTelefone;
import connection.SingleConnection;

public class DaoTelefone {
	
	private Connection connection;
	
	public DaoTelefone() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanTelefone beanTelefone) {
		String sql = "INSERT INTO telefone (numero, tipo, iduser) values(?, ?, ?)";
		try (PreparedStatement insert = connection.prepareStatement(sql)) {
			insert.setString(1, beanTelefone.getNumero());
			insert.setString(2, beanTelefone.getTipo());
			insert.setLong(3, beanTelefone.getIdUser());
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

	
	public List<BeanTelefone> getLista(String idUser) {
		String sql = "select * from telefone where iduser = '" + idUser + "'";
		List<BeanTelefone> telefones = new ArrayList<BeanTelefone>();
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// criando o objeto BeanTelefone
				BeanTelefone beanTelefone= new BeanTelefone();
				beanTelefone.setIdPhone(rs.getLong("idphone"));
				beanTelefone.setNumero(rs.getString("numero"));
				beanTelefone.setTipo(rs.getString("tipo"));
				beanTelefone.setIdUser(rs.getLong("iduser"));
				telefones.add(beanTelefone);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return telefones;
	}

	public BeanTelefone consultar(String idPhone) {
		String sql = "select * from telefone where idphone = '" + idPhone + "'";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// criando o objeto BeanTelaLogin
				BeanTelefone beanTelefone = new BeanTelefone();
				beanTelefone.setIdPhone(rs.getLong("idphone"));
				beanTelefone.setNumero(rs.getString("numero"));
				beanTelefone.setTipo(rs.getString("tipo"));
				beanTelefone.setIdUser(rs.getLong("iduser"));
				return beanTelefone;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void delete(String idPhone) {

		String sql = "Delete from telefone where idphone = '" + idPhone + "'";
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
	
	public void atualizar(BeanTelefone beanTelefone) {
		String sql = "update telefone set numero=?, tipo=?, iduser=? where idphone = " + beanTelefone.getIdPhone();
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, beanTelefone.getNumero());
			stmt.setString(2, beanTelefone.getTipo());
			stmt.setLong(3, beanTelefone.getIdUser());
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
	
}
