package br.com.autocheck.app;

import br.com.autocheck.connection.Conexao;

public class Main {
	public static void main(String[] args) {

		Conexao conexao = null;

		try {
			conexao = new Conexao();
			System.out.println("Conexão estabelecida com sucesso!");
		} finally {
			if (conexao != null) {
				System.out.println();
				conexao.closeConnection();
			}
		}

	}

}
