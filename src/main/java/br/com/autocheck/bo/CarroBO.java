package br.com.autocheck.bo;

import br.com.autocheck.model.dao.CarroDAO;
import br.com.autocheck.model.vo.Carro;

import java.sql.SQLException;
import java.util.List;

public class CarroBO {

    private CarroDAO carroDAO;

    public CarroBO() {
        this.carroDAO = new CarroDAO();
    }

    public String inserirCarro(Carro carro) throws SQLException {
        if (carro.getUsuario() == null || carro.getUsuario().getId() == 0) {
            throw new IllegalArgumentException("Usuário inválido. Um carro deve estar vinculado a um usuário.");
        }

        if (!validarChassi(carro.getChassi())) {
            throw new IllegalArgumentException("Número de chassi inválido.");
        }

        if (carroDAO.isChassiExistente(carro.getChassi())) {
            throw new IllegalArgumentException("Chassi já cadastrado.");
        }

        String resultado = carroDAO.inserir(carro);
        carroDAO.inserirUsuarioCarro(carro, carro.getUsuario());
        return resultado + " Carro vinculado ao usuário com sucesso!";
    }

    public Carro buscarCarro(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID do carro inválido.");
        }

        return carroDAO.buscar(id);
    }

    public void deletarCarro(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID do carro inválido.");
        }

        carroDAO.deletar(id);
    }

    public List<Carro> listarCarro() throws SQLException {
        return carroDAO.listar();
    }

    public boolean ativarCarro(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID do carro inválido.");
        }

        return carroDAO.atualizarStatusCarro(id, true);
    }

    public boolean desativarCarro(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID do carro inválido.");
        }

        return carroDAO.atualizarStatusCarro(id, false);
    }

    private boolean validarChassi(String chassi) {
        return chassi != null && chassi.length() == 17;
    }
}
