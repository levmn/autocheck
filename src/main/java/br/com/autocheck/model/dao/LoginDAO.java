package br.com.autocheck.model.dao;

import br.com.autocheck.connection.Conexao;
import br.com.autocheck.model.vo.Login;
import br.com.autocheck.model.vo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public Login autenticar(String cpf, String senha) throws SQLException {
        String sql = "SELECT l.senha_usuario, u.id_usuario, u.nome_usuario, u.cpf_usuario, u.endereco_usuario, u.tel_usuario " +
                "FROM tb_login l " +
                "JOIN tb_usuario u ON l.id_usuario = u.id_usuario " +
                "WHERE u.cpf_usuario = ? AND l.senha_usuario = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id_usuario"));
                    usuario.setNome(rs.getString("nome_usuario"));
                    usuario.setCpf(rs.getString("cpf_usuario"));
                    usuario.setEndereco(rs.getString("endereco_usuario"));
                    usuario.setTelefone(rs.getString("tel_usuario"));

                    return new Login(usuario, cpf, senha);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao autenticar usuário: " + e.getMessage());
        }

        return null;
    }
}