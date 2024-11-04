package br.com.autocheck.app;

import br.com.autocheck.connection.Conexao;
import br.com.autocheck.controller.CarroController;
import br.com.autocheck.model.dao.CarroDAO;
import br.com.autocheck.model.vo.Carro;
import br.com.autocheck.model.vo.Usuario;

import java.sql.Connection;

public class CarroMain {
    public static void main(String[] args) {
        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.conexao();

            CarroDAO carroDAO = new CarroDAO();
            CarroController carroController = new CarroController();

            // Cria um usuário (exemplo)
            Usuario usuario = new Usuario(1, "João Silva", "12345678901", "Rua A, 123", "21987654321");

            // Cria um carro (exemplo)
            Carro carro = new Carro(0, "1HGCM82633A123456", "Honda", "Civic", "2023", "2024");

            // Cadastra o carro e vincula ao usuário
            String resultado = carroController.cadastrarCarro(carro, usuario);
            System.out.println(resultado);

        } finally {
            if (conn != null) {
                conexao.closeConnection();
            }
        }
    }
}
