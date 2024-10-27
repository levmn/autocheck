package br.com.autocheck.app;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.autocheck.connection.Conexao;

/*
 * Testando conexão com o banco de dados
 * */

public class Main {
	public static void main(String[] args) {

		Conexao conexao = new Conexao();
		Connection conn = null;

		try {
			conn = conexao.conexao();
			if (conn != null) {
				System.out.println("Conexão estabelecida com sucesso!");
			} else {
				System.out.println("Falha ao estabelecer a conexão.");
			}
		} finally {
			if (conn != null) {
				System.out.println("Encerrando conexão...");
				try {
					conn.close();
					System.out.println("Conexão encerrada com sucesso.");
				} catch (SQLException e) {
					System.err.println("Erro ao encerrar a conexão: " + e.getMessage());
				}
			}
		}
	}
}
