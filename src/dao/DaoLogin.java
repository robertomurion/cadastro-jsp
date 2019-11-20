package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.BeanUsuario;
import connection.SingleConnection;


public class DaoLogin {
	
	private Connection connection;
	
	public DaoLogin() {
		connection = SingleConnection.getConnection();
	}
	
	public boolean validarLogin(BeanUsuario beanUsuario) throws Exception {
		
		String sql = "select * from usuario where login = '" + beanUsuario.getLogin() + "' and senha = '" + beanUsuario.getSenha() + "' and ativo = true";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			return true;
		}else {
			
			return false;
		}
		
	}

}
