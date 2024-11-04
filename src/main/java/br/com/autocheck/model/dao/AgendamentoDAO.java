package br.com.autocheck.model.dao;

import br.com.autocheck.model.vo.Agendamento;
import br.com.autocheck.model.vo.Diagnostico;
import br.com.autocheck.model.vo.Oficina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAO {
    private Connection conn;

    public AgendamentoDAO(Connection conn) {
        this.conn = conn;
    }

    public String inserir(Agendamento agendamento, Diagnostico diagnostico, Oficina oficina) throws SQLException {
        String sql = "INSERT INTO tb_agendamento (id_oficina, id_diagnostico, disponibilidade_agendamento, data_e_hora_agendamento) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, oficina.getId());
        stmt.setInt(2, diagnostico.getId());
        stmt.setBoolean(3, agendamento.isDisponivel());
        stmt.setObject(4, agendamento.getDataHora());

        stmt.execute();
        stmt.close();

        return "Aluno cadastrado com sucesso!";
    }

    public String deletar(int id) throws SQLException {
        String sql = "DELETE FROM tb_agendamento WHERE id_oficina = ? AND id_diagnostico = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.setInt(2, id);
        stmt.execute();
        stmt.close();

        return "Agendamento removido com sucesso!";
    }

    public String atualizar(Agendamento agendamento) throws SQLException {
        String sql = "UPDATE tb_agendamento SET data_hora_agendamento = ?, disponibilidade_agendamento = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setObject(1, agendamento.getDataHora());
        stmt.setBoolean(2, agendamento.isDisponivel());
        stmt.executeUpdate();
        stmt.close();

        return "Agendamento atualizado com sucesso!";
    }

    public List<Agendamento> listar(Diagnostico diagnostico, Oficina oficina) throws SQLException {
        List<Agendamento> listaAgendamento = new ArrayList<Agendamento>();
        String sql = "SELECT * FROM tb_agendamento";

        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Agendamento agendamento = new Agendamento();
            oficina.setId(rs.getInt(1));
            diagnostico.setId(rs.getInt(2));
            agendamento.setDataHora(rs.getTimestamp(3).toLocalDateTime());
            agendamento.setDisponivel(false);

            listaAgendamento.add(agendamento);
        }

        System.out.println(listaAgendamento);
        return listaAgendamento;
    }
}
