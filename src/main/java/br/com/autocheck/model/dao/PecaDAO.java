package br.com.autocheck.model.dao;

import br.com.autocheck.connection.Conexao;
import br.com.autocheck.model.vo.Peca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PecaDAO {
    private Connection conn;

    public PecaDAO() {
        this.conn = new Conexao().conexao();
    }

    public String inserir(Peca peca) throws SQLException {
        String sql = "INSERT INTO tb_peca VALUES (?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, peca.getId());
        stmt.setString(2, peca.getNome());
        stmt.setDouble(3, peca.getValor());
        stmt.execute();
        stmt.close();

        return "Peça cadastrada com sucesso!";
    }

    public String atualizar(Peca peca) throws SQLException {
        String sql = "UPDATE tb_peca SET nome_peca = ?, valor_peca = ? WHERE id_peca = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, peca.getNome());
        stmt.setDouble(2, peca.getValor());
        stmt.setInt(3, peca.getId());
        stmt.executeUpdate();
        stmt.close();

        return "Peça atualizada com sucesso!";
    }

    public String deletar(int id) throws SQLException {
        String sql = "DELETE FROM tb_peca WHERE id_peca = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.execute();
        stmt.close();

        return "Peça removida com sucesso!";
    }

    public Peca buscar(int id) throws SQLException {
        String sql = "SELECT * FROM tb_peca WHERE id_peca = ?";
        Peca peca = null;

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            peca = new Peca(rs.getInt("id_peca"), rs.getString("nome_peca"), rs.getDouble("valor_peca"));
        }
        rs.close();
        stmt.close();

        return peca;
    }

    public List<Peca> listar() throws SQLException {
        List<Peca> listaPeca = new ArrayList<>();
        String sql = "SELECT * FROM tb_peca";

        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Peca peca = new Peca(rs.getInt("id_peca"), rs.getString("nome_peca"), rs.getDouble("valor_peca"));
            listaPeca.add(peca);
        }
        rs.close();
        stmt.close();

        return listaPeca;
    }
}
