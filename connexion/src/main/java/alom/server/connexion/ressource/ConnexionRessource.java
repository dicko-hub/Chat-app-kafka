package alom.server.connexion.ressource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import alom.server.connexion.back.User;
import alom.server.connexion.controller.ConnexionController;
import alom.server.connexion.exception.ErrorMessage;
import alom.server.connexion.exception.RESTException;
import alom.server.connexion.exception.RESTExceptionMapper;

@Path("/connexion")
public class ConnexionRessource {

	ConnexionController controller = new ConnexionController();
	RESTExceptionMapper exception = new RESTExceptionMapper();
	
	@GET
	@Path("/disconnect/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response disconnect(@PathParam("token") String token) throws RESTException {
		
		User userGet = controller.disconnect(token);
		
		if(userGet == null)
			return exception.toResponse(new RESTException(new ErrorMessage(404,"Cet token n'existe pas")));
		
		return Response.status(Status.OK).entity(userGet).build();
	}
	
	@GET
	@Path("/isconnect/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response isconnect(@PathParam("token") String token) throws RESTException {
		
		User userGet = controller.isconnect(token);
		
		if(userGet == null)
			return exception.toResponse(new RESTException(new ErrorMessage(404,"Cet token n'existe pas")));
		
		return Response.status(Status.OK).entity(userGet).build();
	}
	
	@POST
	@Path("/connect")
	@Produces(MediaType.APPLICATION_JSON)
	public Response connect(User user) throws RESTException {
		//user contient un login et un pwd pour verification
		String token = controller.connect(user);
		
		if(token == null)
			return exception.toResponse(new RESTException(new ErrorMessage(404,"Cet utilisateur n'existe pas")));
		
		return Response.status(Status.OK).entity(token).build();
	}
	
}
