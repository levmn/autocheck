package br.com.autocheck.model.dao;

import br.com.autocheck.model.vo.Diagnostico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiagnosticoDAO {
    private Connection conn;

    public DiagnosticoDAO(Connection conn) {
        this.conn = conn;
    }

    public String inserir(Diagnostico diagnostico) throws SQLException {
        String sql = "INSERT into tb_diagnostico (id_diagnostico, cod_falha_diagnostico, categoria_diagnostico, descricao_diagnostico, descricao_problema_diagnostico) VALUES (seq_tb_diagnostico_id.NEXTVAL, ?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, diagnostico.getId());
        stmt.setString(2, diagnostico.getCodigoFalha());
        stmt.setString(3, diagnostico.getCategoria());
        stmt.setString(4, diagnostico.getDescricao());
        stmt.setString(5, diagnostico.getDescricaoProblema());
        stmt.execute();
        stmt.close();

        return "Diagnóstico registrado com sucesso!";
    }

    public String deletar(int id) throws SQLException {
        String sql = "DELETE FROM tb_diagnostico WHERE id_diagnostico = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.execute();
        stmt.close();
        return "Diagnóstico removido com sucesso!";
    }

    public List<Diagnostico> listar() throws SQLException {
        List<Diagnostico> listaDiagnostico = new ArrayList<Diagnostico>();
        String sql = "SELECT * FROM tb_diagnostico";

        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Diagnostico diagnostico = new Diagnostico();
            diagnostico.setId(rs.getInt(1));
            diagnostico.setCodigoFalha(rs.getString(2));
            diagnostico.setCategoria(rs.getString(3));
            diagnostico.setDescricao(rs.getString(4));
            diagnostico.setDescricaoProblema(rs.getString(5));

            listaDiagnostico.add(diagnostico);
        }

        System.out.println(listaDiagnostico);
        return listaDiagnostico;
    }
}
