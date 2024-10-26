package br.com.autocheck.connection;

/**
 * No package 'jdbc' se encontra o arquivo .jar e o script sql com as queries para criação da tabela no seu banco
 * 
 * Na raíz do projeto temos o arquivo .env.sample com as variáveis definidas, ele deve ser duplicado e transformado em .env
 * para que a aplicação reconheça o login no banco de dados
 * 
 * DB_USER = usuário
 * DB_PASSWORD = senha
 * **/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

public class Conexao {

	private static final Dotenv dotenv = Dotenv.load();

	public Connection conexao() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = dotenv.get("DB_URL");
			String user = dotenv.get("DB_USER");
			String password = dotenv.get("DB_PASSWORD");

			System.out.println("Conexão realizada!");
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
