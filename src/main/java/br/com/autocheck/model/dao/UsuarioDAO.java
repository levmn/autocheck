package br.com.autocheck.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.autocheck.connection.Conexao;
import br.com.autocheck.model.vo.Usuario;

public class UsuarioDAO {

	private Connection conn;

	public UsuarioDAO() {
		this.conn = new Conexao().conexao();
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

	public String deletar(int id) throws SQLException {
		String sql = "DELETE FROM tb_usuario WHERE id = ?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.execute();
		stmt.close();
		return "Usuário removido com sucesso!";
	}

	public List<Usuario> listar() throws SQLException {
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		String sql = "SELECT * FROM tb_usuario";

		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Usuario usuario = new Usuario();
			usuario.setId(rs.getInt(1));
			usuario.setNome(rs.getString(2));
			usuario.setCpf(rs.getString(3));
			usuario.setEndereco(rs.getString(4));
			usuario.setTelefone(rs.getString(5));

			listaUsuario.add(usuario);
		}

		System.out.println(listaUsuario);
		return listaUsuario;
	}

}
