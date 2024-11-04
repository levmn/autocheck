package br.com.autocheck.bo;

import br.com.autocheck.model.dao.UsuarioDAO;
import br.com.autocheck.model.vo.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioBO {

    private UsuarioDAO usuarioDAO;

    public UsuarioBO() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public void inserir(Usuario usuario) throws ClassNotFoundException, SQLException, IllegalArgumentException {
        validarCampos(usuario);

        if (usuarioDAO.buscar(usuario.getId()) != null) {
            throw new IllegalArgumentException("Erro: id do usuário já está cadastrado.");
        }

        if (usuarioDAO.buscarPorCpf(usuario.getCpf()) != null) {
            throw new IllegalArgumentException("Erro: CPF já está cadastrado.");
        }

        usuarioDAO.inserir(usuario);
    }

    public void atualizar(Usuario usuario) throws ClassNotFoundException, SQLException, IllegalArgumentException {

        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new IllegalArgumentException("Erro: Nome não pode ser nulo ou vazio.");
        }

        if (!isValidEndereco(usuario.getEndereco())) {
            throw new IllegalArgumentException("Erro: Endereço deve ter pelo menos 5 caracteres.");
        }

        if (!isValidTelefone(usuario.getTelefone())) {
            throw new IllegalArgumentException("Erro: Telefone deve ter entre 10 e 11 dígitos.");
        }

        if (usuarioDAO.buscar(usuario.getId()) == null) {
            throw new IllegalArgumentException("Erro: Usuário não encontrado para o id fornecido.");
        }

        usuarioDAO.atualizar(usuario);
    }

    public void deletar(int id) throws ClassNotFoundException, SQLException {
        if (usuarioDAO.buscar(id) == null) {
            throw new IllegalArgumentException("Erro: Usuário não encontrado para o id fornecido.");
        }
        usuarioDAO.deletar(id);
    }

    public Usuario buscar(int id) throws ClassNotFoundException, SQLException {
        Usuario usuario = usuarioDAO.buscar(id);
        if (usuario == null) {
            throw new SQLException("Erro: Usuário com id: " + id + " não encontrado.");
        }
        return usuario;
    }

    public ArrayList<Usuario> listar() throws ClassNotFoundException, SQLException {
        return (ArrayList<Usuario>) usuarioDAO.listar();
    }

    private void validarCampos(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new IllegalArgumentException("Erro: Nome não pode ser nulo ou vazio.");
        }

        if (!isValidCPF(usuario.getCpf())) {
            throw new IllegalArgumentException("Erro: CPF deve ter 11 dígitos.");
        }

        if (!isValidEndereco(usuario.getEndereco())) {
            throw new IllegalArgumentException("Erro: Endereço deve ter pelo menos 5 caracteres.");
        }

        if (!isValidTelefone(usuario.getTelefone())) {
            throw new IllegalArgumentException("Erro: Telefone deve ter entre 10 e 11 dígitos.");
        }
    }

    private boolean isValidCPF(String cpf) {
        String cpfRegex = "\\d{11}";
        return cpf != null && cpf.matches(cpfRegex);
    }

    private boolean isValidEndereco(String endereco) {
        return endereco != null && endereco.length() >= 5;
    }

    private boolean isValidTelefone(String telefone) {
        String telefoneRegex = "\\d{10,11}";
        return telefone != null && telefone.matches(telefoneRegex);
    }
}
