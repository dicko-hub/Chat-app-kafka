package alom.server.clientConnexion.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RESTExceptionMapper implements ExceptionMapper<RESTException> {

	@Override
	public Response toResponse(RESTException ex) {
      ErrorMessage em = ex.getErrorMessage();
      return Response.status(em.getCode())
            .entity(em)
            .type(MediaType.APPLICATION_JSON)
            .build();
   }

}