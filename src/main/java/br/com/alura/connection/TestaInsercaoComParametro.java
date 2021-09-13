package br.com.alura.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercaoComParametro {

	public static void main(String[] args) throws SQLException {
		//parâmetros
		String nome = "Teclado";
		String descricao = "Teclado com teclas barulhentas";
		
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.recuperarConexao();

		// faz com que a responsabilidade de gerenciar os atributos seja do JDBC
		// Statement.RETURN_GENERATED_KEYS retorna o id gerado
		//Cria um objeto PreparedStatement para enviar instruções SQL parametrizadas ao banco de dados
		PreparedStatement pstm = connection.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)", 
				Statement.RETURN_GENERATED_KEYS);
		
		pstm.setString(1, nome);
		pstm.setString(2, descricao);
		
		// o método execute retorna um boolean (true p/ lista | false p/ outra coisa)
		pstm.execute();

		ResultSet rst = pstm.getGeneratedKeys();
		while (rst.next()) {
			Integer id = rst.getInt(1);
			System.out.println("O id criado foi: " + id);
		}

	}

}
