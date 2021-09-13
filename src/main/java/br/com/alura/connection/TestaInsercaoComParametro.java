package br.com.alura.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercaoComParametro {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection connection = factory.recuperarConexao()) {

			// faz com que o JDBC não seja mais responsável pelo commit do produto
			// o controle transacional se torna manual
			connection.setAutoCommit(false);

			// faz com que a responsabilidade de gerenciar os atributos seja do JDBC
			// Statement.RETURN_GENERATED_KEYS retorna o id gerado
			// Cria um objeto PreparedStatement para enviar instruções SQL parametrizadas ao
			// banco de dados

			try (PreparedStatement pstm = connection.prepareStatement(
					"INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {

				adicionarVariavel("SmartTV", "45 polegadas", pstm);
				adicionarVariavel("Rádio", "Rádio a pilha", pstm);

				// quando trabalhamos com controle transacional manual, o commit deve ser
				// explícito
				connection.commit();

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ROLLBACK EXECUTADO");
				connection.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void adicionarVariavel(String nome, String descricao, PreparedStatement pstm) throws SQLException {

		pstm.setString(1, nome);
		pstm.setString(2, descricao);

		if (nome.equals("Rádio")) {
			throw new RuntimeException("Não foi possível add o produto");
		}

		// o método execute retorna um boolean (true p/ lista | false p/ outra coisa)
		pstm.execute();

		// o try com recursos chama o método .close da classe AutoClosable
		try (ResultSet rst = pstm.getGeneratedKeys()) {
			while (rst.next()) {
				Integer id = rst.getInt(1);
				System.out.println("O id criado foi: " + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
