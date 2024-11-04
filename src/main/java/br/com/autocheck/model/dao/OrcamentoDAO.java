package br.com.autocheck.model.dao;

import br.com.autocheck.model.vo.Diagnostico;
import br.com.autocheck.model.vo.Orcamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrcamentoDAO {
    private Connection conn;

    public OrcamentoDAO(Connection conn) {
        this.conn = conn;
    }

    public String inserir(Orcamento orcamento, Diagnostico diagnostico) throws SQLException {
        String sql = "INSERT into tb_orcamento (id_diagnostico, valorFinal, valorPecas, valorMaoObra, dataHora) VALUES (seq_tb_diagnostico_id.NEXTVAL, ?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, diagnostico.getId());
        stmt.setDouble(2, orcamento.getValorFinal());
        stmt.setDouble(3, orcamento.getValorPecas());
        stmt.setDouble(4, orcamento.getValorMaoObra());
        stmt.setObject(5, orcamento.getDataHora());
        stmt.execute();
        stmt.close();

        return "Orçamento cadastrado com sucesso!";
    }

    public String deletar(int id) throws SQLException {
        String sql = "DELETE FROM tb_orcamento WHERE id_diagnostico = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.execute();
        stmt.close();

        return "Orçamento removido com sucesso!";
    }

    public String atualizar(Orcamento orcamento, Diagnostico diagnostico) throws SQLException {
        String sql = "UPDATE tb_orcamento SET valorFinal = ?, valorPecas = ?, valorMaoObra = ?, dataHora = ? WHERE id_diagnostico = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setDouble(1, orcamento.getValorFinal());
        stmt.setDouble(2, orcamento.getValorPecas());
        stmt.setDouble(3, orcamento.getValorMaoObra());
        stmt.setObject(4, orcamento.getDataHora());
        stmt.setInt(5, diagnostico.getId());

        stmt.executeUpdate();
        stmt.close();

        return "Orçamento atualizado com sucesso!";
    }

    public List<Orcamento> listar(Diagnostico diagnostico) throws SQLException {
        List<Orcamento> listaOrcamento = new ArrayList<Orcamento>();
        String sql = "SELECT * FROM tb_orcamento";

        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Orcamento orcamento = new Orcamento();
            diagnostico.setId(rs.getInt(1));
            orcamento.setValorFinal(rs.getDouble(2));
            orcamento.setValorPecas(rs.getDouble(3));
            orcamento.setValorMaoObra(rs.getDouble(4));
            orcamento.setDataHora(rs.getTimestamp(5).toLocalDateTime());

            listaOrcamento.add(orcamento);
        }

        System.out.println(listaOrcamento);
        return listaOrcamento;
    }
}
