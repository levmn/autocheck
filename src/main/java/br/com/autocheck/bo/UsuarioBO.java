package br.com.autocheck.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.autocheck.model.dao.UsuarioDAO;
import br.com.autocheck.model.vo.Usuario;

/**
 * Esta classe lida com as regras de negócio da entidade Usuário
 **/

public class UsuarioBO {

//	private UsuarioDAO usuarioDAO;
//
//	public UsuarioBO() {
//		this.usuarioDAO = new UsuarioDAO();
//	}

	public void inserir(Usuario usuario) throws ClassNotFoundException, SQLException {

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		// Regras de negócio

		usuarioDAO.inserir(usuario);

	}

	public void atualizar(Usuario usuario) throws ClassNotFoundException, SQLException {

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		// Regras de negócio

		usuarioDAO.atualizar(usuario);

	}

	public void deletar(int id) throws ClassNotFoundException, SQLException {

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		// Regras de negócio

		usuarioDAO.deletar(id);
	}

	public Usuario obterPorId(int id) throws ClassNotFoundException, SQLException {
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		// Verificar se o ID é válido
		if (id <= 0) {
			throw new IllegalArgumentException("ID inválido");
		}

		// Buscar o usuário pelo ID no banco de dados
		Usuario usuario = usuarioDAO.buscarPorId(id);

		return usuario;
	}

	public ArrayList<Usuario> listar() throws ClassNotFoundException, SQLException {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return (ArrayList<Usuario>) usuarioDAO.listar();

	}

	// Validar formato do CPF e CPF único
	public boolean validarCpf(String cpf) {
		String regexCpf = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";

		if (cpf.matches(regexCpf) && cpf != null) {
			return true;
		}
		return false;
	}

	// Validar número de telefone
	public boolean validarTelefone(String telefone) {
		if (telefone.length() == 11) {
			return true;
		}
		return false;
	}

	// Validar endereço
	public boolean validarEndereco(String endereco) {
		if (endereco != null) {
			return true;
		}
		return false;
	}

	// Associação com Carro e Agendamento

}
