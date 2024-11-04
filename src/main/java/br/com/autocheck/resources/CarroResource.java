package br.com.autocheck.resources;

import br.com.autocheck.bo.CarroBO;
import br.com.autocheck.model.dao.UsuarioDAO;
import br.com.autocheck.model.vo.Carro;
import br.com.autocheck.model.vo.Usuario;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.sql.SQLException;
import java.util.List;

@Path("carro")
public class CarroResource {

    private CarroBO carroBO = new CarroBO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastro(Carro carro, @Context UriInfo uriInfo) throws SQLException {
        if (carro.getUsuario() == null || carro.getUsuario().getId() == 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Usuário inválido. Um carro deve estar vinculado a um usuário.")
                    .build();
        }

        Usuario usuario = usuarioDAO.buscar(carro.getUsuario().getId());
        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuário não encontrado.")
                    .build();
        }

        carro.setUsuario(usuario);

        String resultado = carroBO.inserirCarro(carro);

        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(carro.getId()));
        return Response.created(builder.build()).entity(resultado).build();
    }

    @DELETE
    @Path("deletar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletar(@PathParam("id") int id) throws SQLException {
        carroBO.deletarCarro(id);
        return Response.ok("Carro deletado com sucesso!").build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscar(@PathParam("id") int id) throws SQLException {
        Carro carro = carroBO.buscarCarro(id);

        if (carro != null) {
            return Response.ok(carro).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Carro não encontrado!").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar() throws SQLException {
        List<Carro> carros = carroBO.listarCarro();
        return Response.ok(carros).build();
    }

    @PUT
    @Path("ativar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ativarCarro(@PathParam("id") int id) throws SQLException {
        boolean sucesso = carroBO.ativarCarro(id);
        if (sucesso) {
            return Response.ok("Carro ativado com sucesso!").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Carro não encontrado!").build();
        }
    }

    @PUT
    @Path("desativar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response desativarCarro(@PathParam("id") int id) throws SQLException {
        boolean sucesso = carroBO.desativarCarro(id);
        if (sucesso) {
            return Response.ok("Carro desativado com sucesso!").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Carro não encontrado!").build();
        }
    }
}
