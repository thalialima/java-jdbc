package br.com.alura.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.recuperarConexao();

		// executa clausulas SQL a partir da aplicação
		Statement stm = connection.createStatement();
		// o método execute retorna um boolean (true p/ lista | false p/ outra coisa)
		boolean resultado = stm.execute("INSERT INTO PRODUTO (nome, descricao) VALUES ('Mouse', 'Mouse sem fio')",
				Statement.RETURN_GENERATED_KEYS);
		// Statement.RETURN_GENERATED_KEYS retorna o id gerado
		System.out.println(resultado);

		ResultSet rst = stm.getGeneratedKeys();
		while(rst.next()) {
			Integer id = rst.getInt(1);
			System.out.println("O id criado foi: " + id);
		}
	}

}
