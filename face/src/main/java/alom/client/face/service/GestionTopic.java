package alom.client.face.service;

import java.time.LocalDateTime;
import com.sun.jersey.api.client.ClientResponse;

import alom.client.face.back.Demande;
import alom.client.face.back.Message;
import alom.client.face.back.MessagesList;
import alom.client.face.back.User;
import alom.client.face.exception.ErrorMessage;
import alom.client.face.ressource.TopicClient;
import alom.client.face.ressource.UserClient;

public class GestionTopic {

	TopicClient topic = new TopicClient();
	UserClient user = new UserClient();
	ErrorMessage error = new ErrorMessage();
    ClientResponse response ;
	
	public GestionTopic() {
		
	}
	
	public boolean sendMessage(Message msg) {
		response = topic.addProduce(msg);
		  if(response.getStatus() != 200) {
				error = response.getEntity(ErrorMessage.class);
				return false;
			}else {
				return response.getEntity(boolean.class);
			}
	}
	
	public MessagesList sendDemande(Demande dmd){
		response = topic.addConsume(dmd);
		  if(response.getStatus() != 200) {
				error = response.getEntity(ErrorMessage.class);
				return null;
			}else {
				return response.getEntity(MessagesList.class);
			}
		}
	
	public Demande getDemandeUser(String login) {
		response = user.getUser(login);
		  if(response.getStatus() != 200) {
				error = response.getEntity(ErrorMessage.class);
				return null;
			}else {
				User sender = response.getEntity(User.class);
				Demande dmd = new Demande(sender.getLogin(),sender.getTopic(), sender.getGroupeId());
				dmd.setTimestamp(LocalDateTime.now().toString());
				return dmd;
			}
		}
}
