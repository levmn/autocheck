package br.com.autocheck.app;

/*
 * Testando conexão com o banco de dados
 * */

import br.com.autocheck.connection.Conexao;
import br.com.autocheck.model.dao.UsuarioDAO;
import br.com.autocheck.model.vo.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DBTest {
    public static void main(String[] args) {
        Conexao conexao = new Conexao();
        Connection conn = null;
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try {
            conn = conexao.conexao();
            if (conn != null) {
                System.out.println("Conexão estabelecida com sucesso!");

                List<Usuario> usuarios = usuarioDAO.listar();
                if (usuarios.isEmpty()) {
                    System.out.println("Nenhum usuário encontrado.");
                } else {
                    System.out.println("Usuários encontrados:");
                    for (Usuario usuario : usuarios) {
                        System.out.println("id: " + usuario.getId() + ", Nome: " + usuario.getNome() + ", CPF: "
                                + usuario.getCpf() + ", Endereço: " + usuario.getEndereco() + ", Telefone: "
                                + usuario.getTelefone());
                    }
                }
            } else {
                System.out.println("Falha ao estabelecer a conexão.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
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
