package br.com.autocheck.controller;

import br.com.autocheck.bo.LoginBO;
import br.com.autocheck.model.vo.Login;

import java.sql.SQLException;

public class LoginController {

    private LoginBO loginBO;

    public LoginController() {
        this.loginBO = new LoginBO();
    }

    public Login autenticar(String cpf, String senha) {
        try {
            return loginBO.autenticar(cpf, senha);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
            return null;
        } catch (SQLException e) {
            System.out.println("Erro de autenticação: " + e.getMessage());
            return null;
        }
    }
}
