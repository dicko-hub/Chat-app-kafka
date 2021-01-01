package alom.client.face.ressource;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import alom.client.face.back.Demande;
import alom.client.face.back.Message;


public class TopicClient {
	
	WebResource service;
	
	public TopicClient() {

		DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
		defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(defaultClientConfig);
		this.service = client.resource("http://localhost:8081/back/webresources/kafka");
	}
	
	public ClientResponse addProduce(Message msg) {
		return service.path("/produce").type(MediaType.APPLICATION_JSON_TYPE).
				post(ClientResponse.class, msg);
	}
	
	public ClientResponse addConsume(Demande dmd) {
		return service.path("/consume").type(MediaType.APPLICATION_JSON_TYPE).
				post(ClientResponse.class, dmd);
	}
}
