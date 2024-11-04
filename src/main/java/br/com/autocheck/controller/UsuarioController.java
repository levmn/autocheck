package br.com.autocheck.controller;

import br.com.autocheck.bo.UsuarioBO;
import br.com.autocheck.model.dao.UsuarioDAO;
import br.com.autocheck.model.vo.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioController {

    private UsuarioBO usuarioBO;

    public UsuarioController() {
        this.usuarioBO = new UsuarioBO();
    }

    public String inserirUsuario(String nome, String cpf, String endereco, String telefone) {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            int proximoId = usuarioDAO.buscarUltimoId() + 1;

            Usuario novoUsuario = new Usuario(proximoId, nome, cpf, endereco, telefone);

            UsuarioBO usuarioBO = new UsuarioBO();

            usuarioBO.inserir(novoUsuario);
            return "Usuário cadastrado com sucesso! id=" + novoUsuario.getId();

        } catch (IllegalArgumentException e) {
            return "Erro de validação: " + e.getMessage();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return "Erro ao cadastrar usuário: " + e.getMessage();
        }
    }

    public String atualizarUsuario(int id, String nome, String endereco, String telefone) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNome(nome);
        usuario.setEndereco(endereco);
        usuario.setTelefone(telefone);

        try {
            usuarioBO.atualizar(usuario);
            return "Usuário atualizado com sucesso!";
        } catch (IllegalArgumentException e) {
            return "Erro de validação: " + e.getMessage();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return "Erro ao atualizar usuário: " + e.getMessage();
        }
    }

    public String deletarUsuario(int id) {
        try {
            usuarioBO.deletar(id);
            return "Usuário removido com sucesso!";
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return "Erro ao deletar usuário: " + e.getMessage();
        }
    }

    public Usuario buscarUsuario(int id) {
        try {
            Usuario usuario = usuarioBO.buscar(id);
            if (usuario != null) {
                return usuario;
            } else {
                System.out.println("Usuário com id=" + id + " não encontrado.");
                return null;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Usuario> listarUsuarios() {
        try {
            return usuarioBO.listar();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
