package alom.server.connexion.ressource;

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
import javax.ws.rs.core.Response.Status;

import alom.server.connexion.back.User;
import alom.server.connexion.back.UsersList;
import alom.server.connexion.controller.UserController;
import alom.server.connexion.exception.ErrorMessage;
import alom.server.connexion.exception.RESTException;
import alom.server.connexion.exception.RESTExceptionMapper;

@Path("/user")
public class UserRessource {
	
	UserController controller = new UserController();
	RESTExceptionMapper exception = new RESTExceptionMapper();
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers() throws RESTException{
		
		UsersList listes = controller.getUsers();
		
	    return Response.status(Status.OK).entity(listes).build();
	}
	
	@GET
	@Path("/get/{login}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("login") String login) throws RESTException {
		//user contient un login et un pwd pour verification
		User userGet = controller.getUser(login);
		
		if(userGet == null)
			return exception.toResponse(new RESTException(new ErrorMessage(404,"Cet utilisateur n'existe pas")));
	
		return Response.status(Status.OK).entity(userGet).build();
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUser(User user) throws RESTException {
		
		User userAdded = controller.addUser(user);
		
		if(userAdded == null)
			return exception.toResponse(new RESTException(new ErrorMessage(409,"Cet utilisateur existe deja")));
			
		return Response.status(Status.CREATED).entity(userAdded).build();	     
	}
	
	@PUT
	@Path("/{login}")
	@Consumes(MediaType.APPLICATION_JSON )
	public Response updateUser(@PathParam("login") String login, User user) throws RESTException {

		User userUpdated = controller.updateUser(login, user);
		
		if(userUpdated == null)
			return exception.toResponse(new RESTException(new ErrorMessage(406,"Cet utilisateur existe pas")));
		
		return Response.status(Status.OK).entity(userUpdated).build();
	}
	
	@DELETE
	@Path("/{login}")
	public Response deleteUser(@PathParam("login") String login) throws RESTException {

		User user = controller.deleteUser(login);
		
		if(user == null)
			return exception.toResponse(new RESTException(new ErrorMessage(406,"Cet utilisateur n'existe pas")));
		
		return Response.status(Status.OK).entity(user).build();
	}
	
	@DELETE
	public Response deleteUsers() throws RESTException {

		controller.deleteUsers();
		return Response.status(Status.OK).build();
	}
	
}
