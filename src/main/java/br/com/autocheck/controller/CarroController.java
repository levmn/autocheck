package br.com.autocheck.controller;

import br.com.autocheck.bo.CarroBO;
import br.com.autocheck.model.vo.Carro;
import br.com.autocheck.model.vo.Usuario;

import java.sql.SQLException;

public class CarroController {
    private CarroBO carroBO;

    public CarroController() {
        this.carroBO = new CarroBO();
    }

    public String cadastrarCarro(Carro carro, Usuario usuario) {
        try {
            return carroBO.inserirCarro(carro);
        } catch (IllegalArgumentException e) {
            return "Erro de validação: " + e.getMessage();
        } catch (SQLException e) {
            return "Erro ao cadastrar carro: " + e.getMessage();
        }
    }
}
