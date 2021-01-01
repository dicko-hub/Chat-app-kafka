package alom.client.face.ressource;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import alom.client.face.back.User;


public class UserClient {

	WebResource service;
	
	public UserClient() {

		DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
		defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(defaultClientConfig);
		this.service = client.resource("http://localhost:8080/connexion/webresources/user");
	}
	

	public ClientResponse getUsers(){
		return  service.path("/").accept(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);
	}
	
	public ClientResponse getUser(String login) {
		return service.path("/get").path(login).type(MediaType.APPLICATION_JSON_TYPE).
				get(ClientResponse.class);
	}
	
	public ClientResponse addUser(User user) {
		
		return service.path("/add").type(MediaType.APPLICATION_JSON_TYPE).
				accept(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class, user);
	}
	
	public ClientResponse updateUser(String login, User user) {
		return service.path("/").path(login).type(MediaType.APPLICATION_JSON_TYPE).
				put(ClientResponse.class, user);
	}
	
	public ClientResponse deleteUser(String login) {
		return service.path("/").path(login).accept(MediaType.APPLICATION_JSON_TYPE).delete(ClientResponse.class);
	}
	
	public ClientResponse deleteUsers() {
		return service.path("/").accept(MediaType.APPLICATION_JSON_TYPE).delete(ClientResponse.class);
	}
	
}
