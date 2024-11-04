package br.com.autocheck.app;

/*
 * Testando operações e validações da entidade Usuário
 * */

import br.com.autocheck.controller.UsuarioController;
import br.com.autocheck.model.vo.Usuario;

import java.util.ArrayList;

public class UsuarioMain {
    public static void main(String[] args) {
        UsuarioController usuarioController = new UsuarioController();

        // Teste de inserção de usuário
        System.out.println("Teste de Inserção:");
        String resultadoInsercao = usuarioController.inserirUsuario("João Silva", "12345678901", "Rua A, 123",
                "21987654321");
        System.out.println(resultadoInsercao);

        // Verificando se o usuário foi inserido
        Usuario usuarioInserido = usuarioController.buscarUsuario(1); // Tentando buscar pelo ID que foi atribuído
        if (usuarioInserido != null) {
            System.out.println("Usuário inserido: " + usuarioInserido);
        } else {
            System.out.println("Usuário não encontrado após inserção.");
            return;
        }

        // Teste de inserção de usuário duplicado
        System.out.println("\nTeste de Inserção Duplicada:");
        String resultadoInsercaoDuplicada = usuarioController.inserirUsuario("João Silva", "12345678901", "Rua A, 123",
                "21987654321");
        System.out.println(resultadoInsercaoDuplicada);

        // Teste de atualização
        System.out.println("\nTeste de Atualização:");
        Usuario usuarioAtualizado = new Usuario(1, "João Silva", "12345678901", "Rua B, 456", "21987654321"); // Dados de atualização
        String resultadoAtualizacao = usuarioController.atualizarUsuario(usuarioAtualizado.getId(),
                usuarioAtualizado.getNome(), usuarioAtualizado.getEndereco(), usuarioAtualizado.getTelefone());
        System.out.println(resultadoAtualizacao);

        // Teste de busca
        System.out.println("\nTeste de Busca:");
        try {
            Usuario usuarioBuscado = usuarioController.buscarUsuario(1); // ID do usuário que deseja buscar
            System.out.println("Usuário encontrado: " + usuarioBuscado);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Teste de listagem
        System.out.println("\nTeste de Listagem:");
        ArrayList<Usuario> listaUsuarios = usuarioController.listarUsuarios();
        if (listaUsuarios.isEmpty()) {
            System.out.println("Nenhum usuário encontrado.");
        } else {
            System.out.println("Usuários encontrados:");
            for (Usuario usuario : listaUsuarios) {
                System.out.println(
                        "id: " + usuario.getId() + ", Nome: " + usuario.getNome() + ", CPF: " + usuario.getCpf()
                                + ", Endereço: " + usuario.getEndereco() + ", Telefone: " + usuario.getTelefone());
            }
        }

        // Teste de exclusão
        System.out.println("\nTeste de Exclusão:");
        try {
            String resultadoExclusao = usuarioController.deletarUsuario(1); // ID do usuário que deseja excluir
            System.out.println(resultadoExclusao);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Listagem após exclusão
        System.out.println("\nListagem Após Exclusão:");
        listaUsuarios = usuarioController.listarUsuarios();
        if (listaUsuarios.isEmpty()) {
            System.out.println("Nenhum usuário encontrado.");
        } else {
            System.out.println("Usuários restantes:");
            for (Usuario usuario : listaUsuarios) {
                System.out.println(
                        "id: " + usuario.getId() + ", Nome: " + usuario.getNome() + ", CPF: " + usuario.getCpf()
                                + ", Endereço: " + usuario.getEndereco() + ", Telefone: " + usuario.getTelefone());
            }
        }
    }
}
