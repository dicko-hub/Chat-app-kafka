package alom.server.back.ressource;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import alom.server.back.controlleur.TopicController;
import alom.server.back.exceptions.ErrorMessage;
import alom.server.back.exceptions.RESTException;
import alom.server.back.exceptions.RESTExceptionMapper;
import alom.server.back.model.Demande;
import alom.server.back.model.Message;
import alom.server.back.model.MessagesList;

@Path("/kafka")
public class TopicRessource {

	TopicController controller = new TopicController();
	RESTExceptionMapper exception = new RESTExceptionMapper();
	
	@POST
	@Path("/consume")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consume(Demande dmd) throws RESTException {
		MessagesList recordsCons= controller.consume(dmd);
		
		if(recordsCons == null)
			return exception.toResponse(new RESTException(new ErrorMessage(509,"Probleme avec le serveur")));
		
		return Response.status(Status.OK).entity(recordsCons).build();
	}
	
	@POST
	@Path("/produce")
	@Produces(MediaType.APPLICATION_JSON)
	public Response produce(Message msg) throws RESTException {
		//msg contient un login et un topic pour verification
		Boolean result = controller.produce(msg);
		if(!result)
			return exception.toResponse(new RESTException(new ErrorMessage(509,"Probleme avec le serveur")));
		
		return Response.status(Status.OK).entity(result).build();
	}

}
