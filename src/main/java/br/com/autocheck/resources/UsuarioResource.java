package br.com.autocheck.resources;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import br.com.autocheck.bo.UsuarioBO;
import br.com.autocheck.model.vo.Usuario;

@Path("/usuario")
public class UsuarioResource {

	private UsuarioBO usuarioBO = new UsuarioBO();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastraResource(Usuario usuario, @Context UriInfo uriInfo)
			throws ClassNotFoundException, SQLException {
		usuarioBO.inserir(usuario);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder(); // Info do front-end
		builder.path(Integer.toString(usuario.getId()));

		return Response.created(builder.build()).build();
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizaResource(Usuario usuario, @PathParam("id") int id)
			throws ClassNotFoundException, SQLException {
		usuarioBO.atualizar(usuario);

		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	public Response deletaResource(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
		usuarioBO.deletar(id);

		return Response.ok().build();
	}

}
