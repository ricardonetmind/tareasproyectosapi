package es.bit.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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

import es.bit.models.Proyecto;
import es.bit.models.StatusMessage;
import es.bit.models.Tarea;
import es.bit.models.Usuario;
import es.bit.persistence.ProyectosManager;
import es.bit.persistence.TareasManager;
import es.bit.persistence.UsuariosManager;

@Path("/proyectos")
public class ProyectosService {
	private static Logger logger = Logger.getLogger("ProyectosService");

	private static List<Proyecto> proyectos;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProyectos() {

		try {
			List<Proyecto> proyectos = ProyectosManager.getInstance().getProyectos();

			return Response.status(Response.Status.OK).entity(proyectos).build();

		} catch (Exception e) {
			logger.severe("!!!!Exception:" + e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(new StatusMessage(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Ooopsss..."))
					.build();
		}
	}

	@GET()
	@Path("/{pid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("pid") int pid) {

		Proyecto proyecto;
		try {
			proyecto = ProyectosManager.getInstance().getProyecto(pid);
			return Response.status((proyecto != null) ? Response.Status.OK : Response.Status.NOT_FOUND)
					.entity((proyecto != null) ? proyecto
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
	public Response addProyecto(Proyecto nuevo) {

		try {
			int nid = ProyectosManager.getInstance().createProyecto(nuevo);
			nuevo.setPid(nid);
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
	public Response updateUser(Proyecto proyecto) {

		try {
			boolean isOK = ProyectosManager.getInstance().updateProyecto(proyecto);
			return Response.status(isOK ? Response.Status.OK : Response.Status.NOT_FOUND)
					.entity(isOK ? proyecto : new StatusMessage(Response.Status.NOT_FOUND.getStatusCode(), "No existe"))
					.build();
		} catch (Exception e) {
			logger.severe("!!!!Exception:" + e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(new StatusMessage(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Ooopsss..."))
					.build();
		}

	}

	@DELETE()
	@Path("/{pid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUser(@PathParam("pid") int pid) {

		try {
			Proyecto Proyecto = ProyectosManager.getInstance().getProyecto(pid);
			if (Proyecto != null) {
				boolean isOK = ProyectosManager.getInstance().deleteProyecto(Proyecto);
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
