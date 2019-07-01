package es.bit.rest;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import es.bit.models.StatusMessage;
import es.bit.models.Usuario;
import es.bit.persistence.UsuariosManager;

@Path("/usuarios")
public class UsuariosService {

	private static Logger logger = Logger.getLogger("UsuariosService");

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getUsers() {

		List<Usuario> usuarios;
		try {
			usuarios = UsuariosManager.getInstance().getUsuarios();			
			return Response.status(Response.Status.OK).entity(usuarios).build();
		} catch (Exception e) {
			logger.severe("!!!!Exception:" + e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(new StatusMessage(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Ooopsss..."))
					.build();
		}

	}

	@GET()
	@Path("/{uid}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getUser(@PathParam("uid") int uid) {

		Usuario usuario;
		try {
			usuario = UsuariosManager.getInstance().getUsuario(uid);
			return Response.status((usuario != null) ? Response.Status.OK : Response.Status.NOT_FOUND)
					.entity((usuario != null) ? usuario
							: new StatusMessage(Response.Status.NOT_FOUND.getStatusCode(), "No encontrado"))
					.build();
		} catch (Exception e) {
			logger.severe("!!!!Exception:" + e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(new StatusMessage(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Ooopsss..."))
					.build();
		}

	}

	@POST()
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUser(@Valid Usuario nuevo) {

		try {
			int nid = UsuariosManager.getInstance().createUsuario(nuevo);
			nuevo.setUid(nid);
			return Response.status(Response.Status.CREATED).entity(nuevo).build();
		} catch (Exception e) {
			logger.severe("!!!!Exception:" + e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(new StatusMessage(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Ooopsss..."))
					.build();
		}

	}

	@PUT()
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(Usuario usuario) {

		try {
			boolean isOK = UsuariosManager.getInstance().updateUsuario(usuario);
			return Response.status(isOK ? Response.Status.OK : Response.Status.NOT_FOUND)
					.entity(isOK ? usuario : new StatusMessage(Response.Status.NOT_FOUND.getStatusCode(), "No existe"))
					.build();
		} catch (Exception e) {
			logger.severe("!!!!Exception:" + e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(new StatusMessage(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Ooopsss..."))
					.build();
		}

	}

	@DELETE()
	@Path("/{uid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUser(@PathParam("uid") int uid) {

		try {
			Usuario usuario = UsuariosManager.getInstance().getUsuario(uid);
			if (usuario != null) {
				boolean isOK = UsuariosManager.getInstance().deleteUsuario(usuario);
				return Response.status(Response.Status.OK)
						.entity(new StatusMessage(Response.Status.OK.getStatusCode(), "Borrado")).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND)
						.entity(new StatusMessage(Response.Status.NOT_FOUND.getStatusCode(), "No encontrado")).build();
			}

		} catch (Exception e) {
			logger.severe("!!!!Exception:" + e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(new StatusMessage(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Ooopsss..."))
					.build();
		}

	}

}
