package br.com.autocheck.model.dao;

import br.com.autocheck.connection.Conexao;
import br.com.autocheck.model.vo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Connection conn;

    public UsuarioDAO() {
        this.conn = new Conexao().conexao();
    }

    public String inserir(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO tb_usuario VALUES (tb_usuario_id_usuario_seq.NEXTVAL, ?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getCpf());
        stmt.setString(3, usuario.getEndereco());
        stmt.setString(4, usuario.getTelefone());
        stmt.execute();
        stmt.close();

        return "Usuário cadastrado com sucesso!";
    }

    public String atualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE tb_usuario SET nome_usuario = ?, endereco_usuario = ?, tel_usuario = ? WHERE id_usuario = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getEndereco());
        stmt.setString(3, usuario.getTelefone());
        stmt.setInt(4, usuario.getId());
        stmt.executeUpdate();
        stmt.close();

        return "Usuário atualizado com sucesso!";
    }

    public String deletar(int id) throws SQLException {
        String sql = "DELETE FROM tb_usuario WHERE id_usuario = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.execute();
        stmt.close();

        return "Usuário removido com sucesso!";
    }

    public Usuario buscar(int id) throws SQLException {
        String sql = "SELECT * FROM tb_usuario WHERE id_usuario = ?";
        Usuario usuario = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt(1));
                usuario.setNome(rs.getString(2));
                usuario.setCpf(rs.getString(3));
                usuario.setEndereco(rs.getString(4));
                usuario.setTelefone(rs.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao buscar o usuário pelo id: " + id, e);
        }

        return usuario;
    }

    public List<Usuario> listar() throws SQLException {
        List<Usuario> listaUsuario = new ArrayList<Usuario>();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tb_usuario");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getInt("id_usuario"));
            usuario.setNome(rs.getString("nome_usuario"));
            usuario.setCpf(rs.getString("cpf_usuario"));
            usuario.setEndereco(rs.getString("endereco_usuario"));
            usuario.setTelefone(rs.getString("tel_usuario"));
            listaUsuario.add(usuario);
        }
        return listaUsuario;
    }

    public int buscarUltimoId() throws SQLException {
        String sql = "SELECT MAX(id_usuario) AS ultimo_id FROM tb_usuario";
        int ultimoId = 0;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ultimoId = rs.getInt("ultimo_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao obter o último ID.", e);
        }
        return ultimoId;
    }

    public Usuario buscarPorCpf(String cpf) throws SQLException {
        String sql = "SELECT * FROM tb_usuario WHERE cpf_usuario = ?";
        Usuario usuario = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome_usuario"));
                usuario.setCpf(rs.getString("cpf_usuario"));
                usuario.setEndereco(rs.getString("endereco_usuario"));
                usuario.setTelefone(rs.getString("tel_usuario"));
            }
        }

        return usuario;
    }
}
