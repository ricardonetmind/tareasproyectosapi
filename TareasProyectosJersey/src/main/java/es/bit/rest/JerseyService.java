package es.bit.rest;
 
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import es.bit.models.Track;

  
@Path("/test")
public class JerseyService
{
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMsg()
    {
         return "Hello World !! - Jersey 2";
    }
    
    @GET
	@Path("/track")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Valid
	public Track getTrackInJSON() {

		Track track = new Track();
		track.setTitle("Enter Sandman");
		track.setSinger("Metallica");

		return track;
	}

	@POST
	@Path("/tracks")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response createTrackInJSON(@Valid Track track) {
		
		track.setSinger(track.getSinger().toUpperCase());
		return Response.status(201).entity(track).build();

	}

}