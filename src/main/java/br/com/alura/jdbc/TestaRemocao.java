package br.com.alura.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaRemocao {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.recuperarConexao();
		
		// Cria um objeto PreparedStatement para enviar instruções SQL parametrizadas ao
		// banco de dados
		PreparedStatement pstm = connection.prepareStatement("DELETE FROM PRODUTO WHERE ID > ?");
		pstm.setInt(1, 2);
		pstm.execute();
		
		//quantas linhas foram modificadas após o statement ser executado
		Integer linhasModificadas = pstm.getUpdateCount();
		
		System.out.println("Quantidade de linhas modificadas: " + linhasModificadas);
	}

}
