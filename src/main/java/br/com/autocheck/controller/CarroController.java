package br.com.autocheck.controller;

import br.com.autocheck.bo.CarroBO;
import br.com.autocheck.model.dao.CarroDAO;
import br.com.autocheck.model.vo.Carro;
import br.com.autocheck.model.vo.Usuario;

import java.sql.SQLException;

public class CarroController {
    private CarroBO carroBO;

    public CarroController(CarroDAO carroDAO) {
        this.carroBO = new CarroBO(carroDAO);
    }

    public String cadastrarCarro(Carro carro, Usuario usuario) {
        try {
            return carroBO.inserirCarro(carro, usuario);
        } catch (IllegalArgumentException e) {
            return "Erro de validação: " + e.getMessage();
        } catch (SQLException e) {
            return "Erro ao cadastrar carro: " + e.getMessage();
        }
    }
}
