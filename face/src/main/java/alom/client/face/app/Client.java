package alom.client.face.app;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import com.sun.jersey.api.client.ClientResponse;

import alom.client.face.back.Message;
import alom.client.face.back.User;
import alom.client.face.ressource.ConnexionClient;
import alom.client.face.ressource.UserClient;
import alom.client.face.service.GestionTopic;
import alom.client.face.socket.DemandeThread;


public class Client {
	
   private String token;
   private ConnexionClient connect = new ConnexionClient();
   private UserClient users = new UserClient();
   private User user;
   private GestionTopic topic = new GestionTopic();
   private Scanner in = new Scanner(System.in);
   private String date ;
   private Message msg = new Message();
   private boolean userExist = false;
   private ClientResponse response ;
   
   public Client() {
	  
   }
   
   private void init () throws IOException {
	   if(!userExist)
		   users.addUser(user);
	   else
		   users.updateUser(user.getLogin(), user);
	   
	   this.token = connect.addConnect(user).getEntity(String.class);
	   user.setToken(token);
   }
   
   	private void checkIfUserExist() {
   		 response = users.getUser(user.getLogin());
   		if(response.getStatus() == 200) {
   			User base = response.getEntity(User.class);
			this.userExist = base.equals(user) ?  true : false;
		}else {
			this.userExist = false;
		}
   			
   	}
   	
	private void  AskUserInformation() {
	
		System.out.println("Entrez votre login (>=3 lettres)");
		String login = in.nextLine();
		while (login.length() < 3) {
			login = in.nextLine();
		}
		
		System.out.println("Entrez votre password (>=3 lettres)");
		String password = in.nextLine();
		while (password.length() < 3) {
			password = in.nextLine();
		}
		
		System.out.println("Entrez votre channel d'écoute (>=3 lettres)");
		String topic = in.nextLine();
		while (topic.length() < 3) {
			topic = in.nextLine();
		}
		this.user = new User(login, password, topic);
		checkIfUserExist();
	}
	
	private void sendAndDisplay(String nextMessage, String date) {
		msg.setSender(user.getLogin());
    	msg.setTopic(user.getTopic());
    	msg.setContent(nextMessage);
		topic.sendMessage(msg);
		System.out.println("[ "+user.getLogin().toUpperCase()+" : "+date+" ]" );
	}
	
	private String formatDate() {
		return LocalDateTime.now().toString().replace('T', ' ').substring(0,19);
	}
	
	private void run() {
		System.out.println("Bienvenue sur le channel.");
		String nextMessage = in.nextLine();
		while(!nextMessage.equals("**disconnect**")) {
			  date = formatDate();
			  while(!nextMessage.equals("**change**") && !nextMessage.equals("**disconnect**")){
				sendAndDisplay(nextMessage, date);
				nextMessage = in.nextLine();
				date = formatDate();
			  }
			   if(!nextMessage.equals("**disconnect**")) {
				   changeChannel();
				   nextMessage = in.nextLine();
			   }else {
				   sendAndDisplay(nextMessage, date);
				   connect.getDisconnexion(token);
			   }
				   
			}
		System.out.println("close");
		in.close();
	}

	private void changeChannel() {
		System.out.println("Entrez votre channel d'écoute (>=3 lettres)");
		String topic = in.nextLine();
		while (topic.length() < 3) {
			topic = in.nextLine();
		}
		user.setTopic(topic);
		users.updateUser(user.getLogin(), user);
		System.out.println("Bienvenue sur le channel.");
	}
	
   public static void main(String[] args) throws IOException {
	   
	   	Client client = new Client();
	   	client.AskUserInformation();
	   	client.init();
        
        DemandeThread recevoir = new DemandeThread(client.user);
        recevoir.start();
        
        client.run();
        recevoir.turnOff();
  }
}