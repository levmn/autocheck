package br.com.autocheck.bo;

import br.com.autocheck.model.dao.LoginDAO;
import br.com.autocheck.model.vo.Login;

import java.sql.SQLException;

public class LoginBO {

    private LoginDAO loginDAO;

    public LoginBO() {
        this.loginDAO = new LoginDAO();
    }

    public Login autenticar(String login, String senha) throws SQLException, IllegalArgumentException {
        validarCredenciais(login, senha);

        Login usuarioAutenticado = loginDAO.autenticar(login, senha);
        if (usuarioAutenticado == null) {
            throw new IllegalArgumentException("Erro: CPF, e-mail ou senha inválidos.");
        }
        return usuarioAutenticado;
    }

    private void validarCredenciais(String login, String senha) {
        if (!isValidCPF(login) && !isValidEmail(login)) {
            throw new IllegalArgumentException("Erro: O campo de login deve ser um CPF válido (11 dígitos) ou um e-mail.");
        }

        if (!isValidSenha(senha)) {
            throw new IllegalArgumentException("Erro: A senha deve ter pelo menos 8 caracteres.");
        }
    }

    private boolean isValidCPF(String cpf) {
        String cpfRegex = "\\d{11}";
        return cpf != null && cpf.matches(cpfRegex);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return email != null && email.matches(emailRegex);
    }

    private boolean isValidSenha(String senha) {
        return senha != null && senha.length() >= 8;
    }
}
