package br.com.autocheck.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.autocheck.connection.Conexao;
import br.com.autocheck.model.vo.Usuario;

public class UsuarioDAO {
	private Connection conn;

	public UsuarioDAO() {
		this.conn = new Conexao().conexao();
	}

	public Usuario obterUsuarioPorCredenciais(String login, String senha) throws SQLException {
		String sql = "SELECT u.id_usuario, u.nome_usuario, u.cpf_usuario, u.endereco_usuario, u.tel_usuario "
				+ "FROM tb_usuario u " + "JOIN tb_login l ON u.id_usuario = l.id_usuario "
				+ "WHERE l.login = ? AND l.senha = ?";
		Usuario usuario = null;

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, login);
			stmt.setString(2, senha);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id_usuario"));
				usuario.setNome(rs.getString("nome_usuario"));
				usuario.setCpf(rs.getString("cpf_usuario"));
				usuario.setEndereco(rs.getString("endereco_usuario"));
				usuario.setTelefone(rs.getString("tel_usuario"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Erro ao obter o usuário pelas credenciais fornecidas.", e);
		}

		return usuario;
	}

	public String inserir(Usuario usuario) throws SQLException {
		String sql = "INSERT INTO tb_usuario (id_usuario, nome_usuario, cpf_usuario, endereco_usuario, tel_usuario) VALUE (seq_tb_usuario_id.NEXTVAL, ?, ?, ?, ?)";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, usuario.getId());
		stmt.setString(2, usuario.getNome());
		stmt.setString(3, usuario.getCpf());
		stmt.setString(4, usuario.getEndereco());
		stmt.setString(5, usuario.getTelefone());
		stmt.execute();
		stmt.close();

		return "Usuário cadastrado com sucesso!";
	}

	public String atualizar(Usuario usuario) throws SQLException {
		String sql = "UPDATE tb_usuario SET nome_usuario = ?, endereco_usuario = ?, telefone_usuario = ? WHERE id_usuario = ?";

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

	public Usuario buscarPorId(int id) throws SQLException {
		String sql = "SELECT * FROM tb_usuario WHERE id_usuario = ?";
		Usuario usuario = null;

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id_usuario"));
				usuario.setNome(rs.getString("nome_usuario"));
				usuario.setCpf(rs.getString("cpf_usuario"));
				usuario.setEndereco(rs.getString("endereco_usuario"));
				usuario.setTelefone(rs.getString("tel_usuario"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Erro ao buscar o usuário pelo id: " + id, e);
		}

		return usuario;
	}

}
