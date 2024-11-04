package br.com.autocheck.bo;

import br.com.autocheck.model.dao.CarroDAO;
import br.com.autocheck.model.vo.Carro;
import br.com.autocheck.model.vo.Usuario;

import java.sql.SQLException;

public class CarroBO {
    private CarroDAO carroDAO;

    public CarroBO(CarroDAO carroDAO) {
        this.carroDAO = carroDAO;
    }

    public String inserirCarro(Carro carro, Usuario usuario) throws SQLException {
        if (usuario == null || usuario.getId() == 0) {
            throw new IllegalArgumentException("Usuário inválido. Um carro deve estar vinculado a um usuário.");
        }

        if (!validarChassi(carro.getChassi())) {
            throw new IllegalArgumentException("Número de chassi inválido.");
        }

        if (carroDAO.isChassiExistente(carro.getChassi())) {
            throw new IllegalArgumentException("Chassi já cadastrado.");
        }

        String resultado = carroDAO.inserir(carro);
        carroDAO.inserirUsuarioCarro(carro, usuario);
        return resultado + " Carro vinculado ao usuário com sucesso!";
    }

    private boolean validarChassi(String chassi) {
        return chassi != null && chassi.length() == 17;
    }
}
