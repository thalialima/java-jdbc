package br.com.alura.connection;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

//deixa o código centralizado em uma única classe
//Padrão Factory
public class ConnectionFactory {
	//factory method
	public Connection recuperarConexao() throws SQLException {
		//representa uma conexão com um banco de dados específico
		return DriverManager
				.getConnection("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC", "root", "tha333");
	}
}