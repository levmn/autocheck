package br.com.autocheck.app;

import br.com.autocheck.controller.LoginController;
import br.com.autocheck.model.vo.Login;

public class LoginUsuarioMain {

    public static void main(String[] args) {
        LoginController loginController = new LoginController();

        // Dados de exemplo para testes
        String cpfCorreto = "12345678901";
        String senhaCorreta = "senhaValida123";

        String cpfInvalido = "12345678"; // CPF inválido (menor que 11 dígitos)
        String senhaInvalida = "123"; // Senha inválida (menos de 8 caracteres)

        System.out.println("Teste de Autenticação com Credenciais Válidas:");
        try {
            Login loginValido = loginController.autenticar(cpfCorreto, senhaCorreta);
            if (loginValido != null) {
                System.out.println("Autenticação bem-sucedida para o usuário: " + loginValido.getUsuario().getNome());
            } else {
                System.out.println("Falha na autenticação com credenciais válidas.");
            }
        } catch (Exception e) {
            System.out.println("Erro durante autenticação válida: " + e.getMessage());
        }

        System.out.println("\nTeste de Autenticação com CPF Inválido:");
        try {
            Login loginCpfInvalido = loginController.autenticar(cpfInvalido, senhaCorreta);
            if (loginCpfInvalido == null) {
                System.out.println("Autenticação falhou como esperado para CPF inválido.");
            }
        } catch (Exception e) {
            System.out.println("Erro durante autenticação com CPF inválido: " + e.getMessage());
        }

        System.out.println("\nTeste de Autenticação com Senha Inválida:");
        try {
            Login loginSenhaInvalida = loginController.autenticar(cpfCorreto, senhaInvalida);
            if (loginSenhaInvalida == null) {
                System.out.println("Autenticação falhou como esperado para senha inválida.");
            }
        } catch (Exception e) {
            System.out.println("Erro durante autenticação com senha inválida: " + e.getMessage());
        }

        System.out.println("\nTeste de Autenticação com Credenciais Incorretas:");
        try {
            Login loginIncorreto = loginController.autenticar("00000000000", "senhaErrada");
            if (loginIncorreto == null) {
                System.out.println("Autenticação falhou como esperado para credenciais incorretas.");
            }
        } catch (Exception e) {
            System.out.println("Erro durante autenticação com credenciais incorretas: " + e.getMessage());
        }
    }
}
