package br.com.autocheck.resources;

import br.com.autocheck.bo.UsuarioBO;
import br.com.autocheck.model.vo.Usuario;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.sql.SQLException;
import java.util.ArrayList;

@Path("usuario")
public class UsuarioResource {

    private UsuarioBO usuarioBO = new UsuarioBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastro(Usuario usuario, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException {
        usuarioBO.inserir(usuario);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(usuario.getId()));
        return Response.created(builder.build()).build();
    }

    @PUT
    @Path("atualizar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(Usuario usuario, @PathParam("id") int id) throws ClassNotFoundException, SQLException {
        usuarioBO.atualizar(usuario);
        return Response.ok().build();

    }

    @DELETE
    @Path("deletar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletar(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        usuarioBO.deletar(id);
        return Response.ok().build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscar(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        Usuario usuario = usuarioBO.buscar(id);

        if (usuario != null) {
            return Response.ok(usuario).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Usuário não encontrado!").build();
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Usuario> listar() throws ClassNotFoundException, SQLException {
        return (ArrayList<Usuario>) usuarioBO.listar();
    }

}