package alom.server.back.exceptions;

import java.io.Serializable;

public class RESTException extends Exception implements Serializable{
	
	private ErrorMessage errorMessage;

	public RESTException() {
		super();
	}

	
	public RESTException(ErrorMessage errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
	
}
