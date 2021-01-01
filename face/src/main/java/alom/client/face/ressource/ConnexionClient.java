package alom.client.face.ressource;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import alom.client.face.back.User;

public class ConnexionClient {

	WebResource service;
	
	public ConnexionClient() {

		DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
		defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(defaultClientConfig);
		this.service = client.resource("http://localhost:8080/connexion/webresources/connexion");
	}
	
	public ClientResponse getDisconnexion(String token){
		return service.path("/disconnect").path(token).type(MediaType.APPLICATION_JSON_TYPE).
				get(ClientResponse.class);
	}
	
	public ClientResponse getIsConnect(String token) {
		return service.path("/isconnect").path(token).type(MediaType.APPLICATION_JSON_TYPE).
				get(ClientResponse.class);
	}
	
	public ClientResponse addConnect(User user) {
		return service.path("/connect").type(MediaType.APPLICATION_JSON_TYPE).
				post(ClientResponse.class, user);
	}
}
