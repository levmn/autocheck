package br.com.autocheck.model.dao;

import br.com.autocheck.model.vo.Carro;
import br.com.autocheck.model.vo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarroDAO {
    private Connection conn;

    public CarroDAO(Connection connection) {
        this.conn = connection;
    }

    public String inserir(Carro carro) throws SQLException {
        String sql = "INSERT into tb_carro (id_carro, chassi_carro, marca_carro, modelo_carro, ano_fabricacao_carro, ano_modelo_carro) VALUES (seq_tb_carro_id.NEXTVAL, ?, ?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, carro.getId());
        stmt.setString(2, carro.getChassi());
        stmt.setString(3, carro.getMarca());
        stmt.setString(4, carro.getModelo());
        stmt.setString(5, carro.getAnoFabricacao());
        stmt.setString(6, carro.getAnoModelo());
        stmt.execute();
        stmt.close();

        return "Carro cadastrado com sucesso!";
    }

    public String deletar(int id) throws SQLException {
        String sql = "DELETE FROM tb_carro WHERE id_carro = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.execute();
        stmt.close();
        return "Carro removido com sucesso!";
    }

    public List<Carro> listar() throws SQLException {
        List<Carro> listaCarro = new ArrayList<Carro>();
        String sql = "SELECT * FROM tb_carro";

        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Carro carro = new Carro();
            carro.setId(rs.getInt(1));
            carro.setChassi(rs.getString(2));
            carro.setMarca(rs.getString(3));
            carro.setModelo(rs.getString(4));
            carro.setAnoFabricacao(rs.getString(5));
            carro.setAnoModelo(rs.getString(6));

            listaCarro.add(carro);
        }

        System.out.println(listaCarro);
        return listaCarro;
    }

    public void inserirUsuarioCarro(Carro carro, Usuario usuario) {
        String sql = "INSERT into tb_usuario_carro (id_usuario, id_carro) VALUES (?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, usuario.getId());
            stmt.setInt(2, carro.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
