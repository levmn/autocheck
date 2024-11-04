package br.com.autocheck.model.dao;

import br.com.autocheck.connection.Conexao;
import br.com.autocheck.model.vo.Login;
import br.com.autocheck.model.vo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginDAO {
    private Connection conn;

    public LoginDAO() {
        this.conn = new Conexao().conexao();
    }

    public String inserir(Usuario usuario, Login login) throws SQLException {
        String sql = "INSERT INTO tb_login (id_usuario, login_usuario, senha_usuario) VALUES (seq_tb_usuario_id.NEXTVAL, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, usuario.getId());
        stmt.setString(2, login.getLogin());
        stmt.setString(3, login.getSenha());
        stmt.execute();
        stmt.close();

        return "Login realizado com sucesso.";
    }

    public String deletar(int id) throws SQLException {
        String sql = "DELETE FROM tb_login WHERE id_usuario = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.execute();
        stmt.close();

        return "Login removido com sucesso!";
    }
}
