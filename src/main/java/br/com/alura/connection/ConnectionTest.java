package br.com.alura.connection;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionTest {

	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.recuperarConexao();
		
		System.out.println("Fechando Conexão");
		
		connection.close();

	}

}
