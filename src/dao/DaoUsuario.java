package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import beans.BeanUsuario;
import connection.SingleConnection;

public class DaoUsuario {
	private Connection connection;

	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanUsuario beanUsuario) {
		String sql = "INSERT INTO usuario (login,senha, nome, fone, cep, rua, bairro, cidade, estado,ibge, arquivobase64, contenttype, nomearquivo, fotobase64miniatura, ativo, sexo) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement insert = connection.prepareStatement(sql)) {
			insert.setString(1, beanUsuario.getLogin());
			insert.setString(2, beanUsuario.getSenha());
			insert.setString(3, beanUsuario.getNome());
			insert.setString(4, beanUsuario.getFone());
			insert.setString(5, beanUsuario.getCep());
			insert.setString(6, beanUsuario.getRua());
			insert.setString(7, beanUsuario.getBairro());
			insert.setString(8, beanUsuario.getCidade());
			insert.setString(9, beanUsuario.getEstado());
			insert.setString(10, beanUsuario.getIbge());
			insert.setString(11, beanUsuario.getArquivoBase64());
			insert.setString(12, beanUsuario.getContentType());
			insert.setString(13, beanUsuario.getNomeArquivo());
			insert.setString(14, beanUsuario.getFotoBase64Miniatura());
			insert.setBoolean(15, beanUsuario.getAtivo());
			insert.setString(16, beanUsuario.getSexo());
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

	public List<BeanUsuario> getLista() {
		String sql = "select login, senha, iduser, nome, fone, cep, rua, bairro, cidade, estado, \n" + 
				"       ibge, momento, nomearquivo, contenttype, fotobase64miniatura, ativo, sexo from usuario where login <> 'admin' ORDER BY momento DESC ";
		 
		return listaUsuarios(sql);
	}
	
	public List<BeanUsuario> getLista(String nome) {
		String sql = "select login, senha, iduser, nome, fone, cep, rua, bairro, cidade, estado, \n" + 
				"       ibge, momento, nomearquivo, contenttype, fotobase64miniatura, ativo, sexo from usuario where login <> 'admin' and lower(nome) like '%" + nome + "%' ORDER BY nome ASC ";
		return listaUsuarios(sql);
	}

	private List<BeanUsuario> listaUsuarios(String sql) {
		List<BeanUsuario> usuarios = new ArrayList<BeanUsuario>();
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// criando o objeto BeanTelaLogin
				BeanUsuario beanUsuario = new BeanUsuario();
				beanUsuario.setIdUser(rs.getLong("iduser"));
				beanUsuario.setLogin(rs.getString("login"));
				beanUsuario.setSenha(rs.getString("senha"));
				beanUsuario.setNome(rs.getString("nome"));
				beanUsuario.setFone(rs.getString("fone"));
				beanUsuario.setCep(rs.getString("cep"));
				beanUsuario.setRua(rs.getString("rua"));
				beanUsuario.setBairro(rs.getString("bairro"));
				beanUsuario.setCidade(rs.getString("cidade"));
				beanUsuario.setEstado(rs.getString("estado"));
				beanUsuario.setIbge(rs.getString("ibge"));
				beanUsuario.setNomeArquivo(rs.getString("nomearquivo"));
			//	beanUsuario.setArquivoBase64(rs.getString("arquivobase64"));
				beanUsuario.setContentType(rs.getString("contenttype"));
				beanUsuario.setFotoBase64Miniatura(rs.getString("fotobase64miniatura"));
				beanUsuario.setAtivo(rs.getBoolean("ativo"));
				beanUsuario.setSexo(rs.getString("sexo"));
				usuarios.add(beanUsuario);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	public void delete(String idUser) {

		String sql = "Delete from usuario where iduser = '" + idUser + "' and idUser <> '1'";  // Apaga usuário
		String sql2 = "Delete from telefone where iduser = '" + idUser + "' and idUser <> '1'";  // Apaga telefones do usuário
		try (PreparedStatement stmt = connection.prepareStatement(sql);PreparedStatement stmt2 = connection.prepareStatement(sql2) ) {
			stmt.execute();
			stmt2.execute();
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

	public void atualizar(BeanUsuario beanUsuario) {
		String sql = "update usuario set login=?, senha=?, nome=?, fone=?, cep=?, rua=?, bairro=?, cidade=?, estado=?,ibge=?, momento=CURRENT_TIMESTAMP AT TIME ZONE 'UTC', ativo=?, sexo=? where iduser = " + beanUsuario.getIdUser();
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, beanUsuario.getLogin());
			stmt.setString(2, beanUsuario.getSenha());
			stmt.setString(3, beanUsuario.getNome());
			stmt.setString(4, beanUsuario.getFone());
			stmt.setString(5, beanUsuario.getCep());
			stmt.setString(6, beanUsuario.getRua());
			stmt.setString(7, beanUsuario.getBairro());
			stmt.setString(8, beanUsuario.getCidade());
			stmt.setString(9, beanUsuario.getEstado());
			stmt.setString(10, beanUsuario.getIbge());
			stmt.setBoolean(11, beanUsuario.getAtivo());
			stmt.setString(12, beanUsuario.getSexo());
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
	
	
	public void atualizarComArquivo(BeanUsuario beanUsuario) {
		String sql = "update usuario set login=?, senha=?, nome=?, fone=?, cep=?, rua=?, bairro=?, cidade=?, estado=?,ibge=?, momento=CURRENT_TIMESTAMP AT TIME ZONE 'UTC', arquivobase64=?, contenttype=?, nomearquivo=?, fotobase64miniatura=?, ativo=?, sexo=? where iduser = " + beanUsuario.getIdUser();
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, beanUsuario.getLogin());
			stmt.setString(2, beanUsuario.getSenha());
			stmt.setString(3, beanUsuario.getNome());
			stmt.setString(4, beanUsuario.getFone());
			stmt.setString(5, beanUsuario.getCep());
			stmt.setString(6, beanUsuario.getRua());
			stmt.setString(7, beanUsuario.getBairro());
			stmt.setString(8, beanUsuario.getCidade());
			stmt.setString(9, beanUsuario.getEstado());
			stmt.setString(10, beanUsuario.getIbge());
			stmt.setString(11, beanUsuario.getArquivoBase64());
			stmt.setString(12, beanUsuario.getContentType());
			stmt.setString(13, beanUsuario.getNomeArquivo());
			stmt.setString(14, beanUsuario.getFotoBase64Miniatura());
			stmt.setBoolean(15, beanUsuario.getAtivo());
			stmt.setString(16, beanUsuario.getSexo());
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

	public BeanUsuario consultar(String idUser) {
		String sql = "select * from usuario where iduser = '" + idUser + "' and login <> 'admin'";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// criando o objeto BeanTelaLogin
				BeanUsuario beanUsuario = new BeanUsuario();
				beanUsuario.setIdUser(rs.getLong("iduser"));
				beanUsuario.setLogin(rs.getString("login"));
				beanUsuario.setSenha(rs.getString("senha"));
				beanUsuario.setNome(rs.getString("nome"));
				beanUsuario.setFone(rs.getString("fone"));
				beanUsuario.setCep(rs.getString("cep"));
				beanUsuario.setRua(rs.getString("rua"));
				beanUsuario.setBairro(rs.getString("bairro"));
				beanUsuario.setCidade(rs.getString("cidade"));
				beanUsuario.setEstado(rs.getString("estado"));
				beanUsuario.setIbge(rs.getString("ibge"));
				beanUsuario.setArquivoBase64(rs.getString("arquivobase64"));
				beanUsuario.setContentType(rs.getString("contenttype"));
				beanUsuario.setNomeArquivo(rs.getString("nomearquivo"));
				beanUsuario.setFotoBase64Miniatura(rs.getString("fotobase64miniatura"));
				beanUsuario.setAtivo(rs.getBoolean("Ativo"));
				beanUsuario.setSexo(rs.getString("sexo"));
				return beanUsuario;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean validarLogin(String login) {

		String sql = "select * from usuario where login = '" + login + "'";
		return validarLoginUsuario(sql);
	}
	
	public boolean validarLogin(String login, String idUser) {
		
		String sql = "select * from usuario where login = '" + login + "' and iduser <> '" + idUser + "'";
		return validarLoginUsuario(sql);
	}
	
	private boolean validarLoginUsuario(String sql) {
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			return (rs.next()? false:true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	

}
