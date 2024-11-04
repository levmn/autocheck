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
        String sql = "INSERT into tb_carro (id_carro, chassi_carro, marca_carro, modelo_carro, ano_fabricacao_carro, ano_modelo_carro) VALUES (tb_carro_id_carro_seq.NEXTVAL, ?, ?, ?, ?, ?)";

        PreparedStatement stmt = null;
        ResultSet generatedKeys = null;

        try {
            stmt = conn.prepareStatement(sql, new String[]{"id_carro"});
            stmt.setString(1, carro.getChassi());
            stmt.setString(2, carro.getMarca());
            stmt.setString(3, carro.getModelo());
            stmt.setString(4, carro.getAnoFabricacao());
            stmt.setString(5, carro.getAnoModelo());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    carro.setId(generatedId);
                }
            }

            return "Carro cadastrado com sucesso!";
        } finally {
            if (generatedKeys != null) generatedKeys.close();
            if (stmt != null) stmt.close();
        }
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

    public void inserirUsuarioCarro(Carro carro, Usuario usuario) throws SQLException {
        if (!isCarroExistente(carro.getId())) {
            throw new SQLException("Carro com ID " + carro.getId() + " não existe.");
        }

        String sql = "INSERT INTO tb_usuario_carro (id_usuario, id_carro, ativo) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuario.getId());
            stmt.setInt(2, carro.getId());
            stmt.setString(3, "s");

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao inserir usuário e carro na tabela relacional.", e);
        }
    }

    public boolean atualizarStatusCarro(int id, boolean ativo) throws SQLException {
        String sql = "UPDATE tb_carro SET ativo = ? WHERE id_carro = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ativo ? "S" : "N");
            stmt.setInt(2, id);

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        }
    }

    public boolean isChassiExistente(String chassi) throws SQLException {
        String sql = "SELECT COUNT(*) FROM tb_carro WHERE chassi_carro = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, chassi);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
        return false;
    }

    private boolean isCarroExistente(int idCarro) throws SQLException {
        String sql = "SELECT COUNT(*) FROM tb_carro WHERE id_carro = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCarro);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        }
    }
}
