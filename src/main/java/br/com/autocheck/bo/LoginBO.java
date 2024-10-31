package br.com.autocheck.bo;

/**
 * Esta classe lida com as regras de negócio da entidade Login
 **/

public class LoginBO {
	
	// Verificar tamanho de senha até 8 digitos
	public boolean validarSenha(String senha) {
		if (senha.length() <= 8) {
			return true;
		}
		return false;
	}

}
