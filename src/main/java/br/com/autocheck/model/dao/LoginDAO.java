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

    public Login autenticar(String login, String senha) throws SQLException {
        String sql = "SELECT l.senha, u.id, u.nome, u.cpf, u.email, u.endereco, u.telefone " +
                "FROM login l " +
                "JOIN usuario u ON l.id_usuario = u.id " +
                "WHERE (u.cpf = ? OR u.email = ?) AND l.senha = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, login);
            stmt.setString(3, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setCpf(rs.getString("cpf"));
//                    usuario.setEmail(rs.getString("email"));
                    usuario.setEndereco(rs.getString("endereco"));
                    usuario.setTelefone(rs.getString("telefone"));

                    Login loginObj = new Login(usuario, login, senha);
                    return loginObj;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao autenticar usuário: " + e.getMessage());
        }

        return null;
    }
}
