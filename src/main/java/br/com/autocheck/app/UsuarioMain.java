package br.com.autocheck.app;

/*
 * Testando operações e validações das entidades Usuário e Login
 * */

import br.com.autocheck.controller.LoginController;
import br.com.autocheck.controller.UsuarioController;
import br.com.autocheck.model.vo.Login;
import br.com.autocheck.model.vo.Usuario;

import java.util.ArrayList;

public class UsuarioMain {
    public static void main(String[] args) {
        UsuarioController usuarioController = new UsuarioController();
        LoginController loginController = new LoginController(); // Instancia o LoginController

        // Teste de inserção de usuário
        System.out.println("Teste de Inserção:");
        String resultadoInsercao = usuarioController.inserirUsuario("João Silva", "12345678901", "Rua A, 123", "21987654321");
        System.out.println(resultadoInsercao);

        // Verificando se o usuário foi inserido
        Usuario usuarioInserido = usuarioController.buscarUsuario(1); // Tentando buscar pelo ID que foi atribuído
        if (usuarioInserido != null) {
            System.out.println("Usuário inserido: " + usuarioInserido);

            // Inserindo o login do usuário
            String senhaValida = "senhaValida123"; // Defina a senha que deseja usar
            String resultadoLogin = loginController.inserirLogin(usuarioInserido, senhaValida);
            System.out.println(resultadoLogin);

            // Autenticando o usuário após a inserção
            Login loginAutenticado = loginController.autenticar(usuarioInserido.getCpf(), senhaValida);
            if (loginAutenticado != null) {
                System.out.println("Autenticação bem-sucedida para o usuário: " + loginAutenticado.getUsuario().getNome());
            } else {
                System.out.println("Falha na autenticação após a inserção.");
            }
        } else {
            System.out.println("Usuário não encontrado após inserção.");
            return;
        }

        // Teste de inserção de usuário duplicado
        System.out.println("\nTeste de Inserção Duplicada:");
        String resultadoInsercaoDuplicada = usuarioController.inserirUsuario("João Silva", "12345678901", "Rua A, 123", "21987654321");
        System.out.println(resultadoInsercaoDuplicada);

        // Teste de atualização
        System.out.println("\nTeste de Atualização:");
        Usuario usuarioAtualizado = new Usuario(1, "João Silva", "12345678901", "Rua B, 456", "21987654321"); // Dados de atualização
        String resultadoAtualizacao = usuarioController.atualizarUsuario(usuarioAtualizado.getId(), usuarioAtualizado.getNome(), usuarioAtualizado.getEndereco(), usuarioAtualizado.getTelefone());
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
                System.out.println("id: " + usuario.getId() + ", Nome: " + usuario.getNome() + ", CPF: " + usuario.getCpf() + ", Endereço: " + usuario.getEndereco() + ", Telefone: " + usuario.getTelefone());
            }
        }
    }
}
