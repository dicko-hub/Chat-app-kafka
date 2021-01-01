package alom.server.clientConnexion.service;

import com.sun.jersey.api.client.ClientResponse;

import alom.server.clientConnexion.back.User;
import alom.server.clientConnexion.ressource.ConnexionClient;
import alom.server.clientConnexion.exception.ErrorMessage;

public class GestionAuth {

	String token;
	ConnexionClient connect = new ConnexionClient();
	ErrorMessage error = new ErrorMessage();
    ClientResponse response ;
	
	public GestionAuth(String token) {
		this.token = token;
	}

	public User getUser() {
		response = connect.getIsConnect(token);
        if(response.getStatus() != 200) {
			error = response.getEntity(ErrorMessage.class);
			return null;
		}else {
			return response.getEntity(User.class);
		}
	}
	
}
