package br.com.autocheck.bo;

import br.com.autocheck.model.dao.UsuarioDAO;

/**
 * Esta classe lida com as regras de negócio da entidade Usuário
 **/

public class UsuarioBO {

	private UsuarioDAO usuarioDAO;

	public UsuarioBO() {
		this.usuarioDAO = new UsuarioDAO();
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
