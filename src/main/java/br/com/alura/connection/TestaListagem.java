package br.com.alura.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaListagem {

	public static void main(String[] args) throws SQLException {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.recuperarConexao();

		// Cria um objeto PreparedStatement para enviar instruções SQL parametrizadas ao
		// banco de dados
		PreparedStatement pstm = connection.prepareStatement("SELECT ID, NOME, DESCRICAO FROM PRODUTO");

		// Executa a instrução SQL fornecida, que pode retornar vários resultados
		boolean resultado = pstm.execute();

		System.out.println(resultado);

		// Uma tabela de dados que representa um conjunto de resultados do banco de
		// dados
		ResultSet rst = pstm.getResultSet();

		// Move o cursor uma linha para frente de sua posição atual
		while (rst.next()) {
			System.out.println("--------------------------------");
			Integer id = rst.getInt("ID");
			System.out.println("Id: " + id);
			String nome = rst.getString("NOME");
			System.out.println("Nome: " + nome);
			String descricao = rst.getString("DESCRICAO");
			System.out.println("Descrição: " + descricao);
			System.out.println("--------------------------------");
		}

		connection.close();
	}

}
