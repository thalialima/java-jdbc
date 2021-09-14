package br.com.alura.connection;

import java.sql.Connection;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

//deixa o código centralizado em uma única classe
//Padrão Factory
public class ConnectionFactory {
	
	public DataSource dataSource;
	
	public ConnectionFactory() {
		//criando pool de conexões
		ComboPooledDataSource comboPooledDataSources = new ComboPooledDataSource();
		comboPooledDataSources.setJdbcUrl("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC");
		comboPooledDataSources.setUser("root");
		comboPooledDataSources.setPassword("tha333");
		
		//seta o número máximo de conexões que se pode ter
		comboPooledDataSources.setMaxPoolSize(15);
		
		this.dataSource = comboPooledDataSources;
	}
	
	//factory method
	public Connection recuperarConexao() throws SQLException {
		//representa uma conexão com um banco de dados específico
		return this.dataSource.getConnection();
	}
}