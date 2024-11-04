package br.com.autocheck.model.dao;

import br.com.autocheck.model.vo.Oficina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OficinaDAO {
    private Connection conn;

    public OficinaDAO(Connection conn) {
        this.conn = conn;
    }

    public String inserir(Oficina oficina) throws SQLException {
        String sql = "INSERT INTO tb_oficina (id_oficina, unidade, cep, numero_endereco, responsavel) VALUES (seq_tb_oficina_id.NEXTVAL, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, oficina.getUnidade());
            stmt.setString(2, oficina.getCep());
            stmt.setInt(3, oficina.getNumeroEndereco());
            stmt.setString(4, oficina.getResponsavel());
            stmt.executeUpdate();
        }

        return "Oficina cadastrada com sucesso!";
    }

    public String deletar(int id) throws SQLException {
        String sql = "DELETE FROM tb_oficina WHERE id_oficina = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }

        return "Oficina removida com sucesso!";
    }

    public String atualizar(Oficina oficina) throws SQLException {
        String sql = "UPDATE tb_oficina SET unidade = ?, cep = ?, numero_endereco = ?, responsavel = ? WHERE id_oficina = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, oficina.getUnidade());
            stmt.setString(2, oficina.getCep());
            stmt.setInt(3, oficina.getNumeroEndereco());
            stmt.setString(4, oficina.getResponsavel());
            stmt.setInt(5, oficina.getId());
            stmt.executeUpdate();
        }

        return "Oficina atualizada com sucesso!";
    }

    public List<Oficina> listar() throws SQLException {
        List<Oficina> listaOficina = new ArrayList<>();
        String sql = "SELECT * FROM tb_oficina";

        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Oficina oficina = new Oficina();
                oficina.setId(rs.getInt("id_oficina"));
                oficina.setUnidade(rs.getString("unidade"));
                oficina.setCep(rs.getString("cep"));
                oficina.setNumeroEndereco(rs.getInt("numero_endereco"));
                oficina.setResponsavel(rs.getString("responsavel"));

                listaOficina.add(oficina);
            }
        }

        return listaOficina;
    }
}
