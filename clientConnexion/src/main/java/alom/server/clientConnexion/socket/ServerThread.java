package alom.server.clientConnexion.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.TimeUnit;

import alom.server.clientConnexion.back.Demande;
import alom.server.clientConnexion.back.User;
import alom.server.clientConnexion.service.GestionAuth;
import alom.server.clientConnexion.service.GestionTopic;

public class ServerThread extends Thread{
	
	private BufferedReader in ;
	private Socket socket;
	private String token;
	private PrintWriter out ;
	private GestionAuth auth;
	private GestionTopic topic = new GestionTopic();
	
	public ServerThread(Socket ss) throws IOException {
		this.socket = ss;
		this.out = new PrintWriter(socket.getOutputStream());
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.token = in.readLine();
		this.auth = new GestionAuth(token);
	}

	public void run() {
		
		try {
			handleRequest();
			socket.close();
			System.out.println("connexion fermee");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void handleRequest () throws IOException {
	    User user = auth.getUser();
		     while(user!=null) {
		    	 Demande dmd = topic.getDemandeUser(user.getLogin());
		    	 String currentTopic = dmd.getTopic();
		    	 while(dmd.getTopic().equals(currentTopic)) {
		    		 //recupere les messages sur le topic dans dmd
		    		 List<String> recordsCons = topic.sendDemande(dmd).getMsgs();
		    		 if(recordsCons.size()!=0) {
			    		 for (String recordCons : recordsCons) {
			    			// envoi vers le client en tcp
			    	        		out.println(recordCons);
			    	        		out.flush();
			    		 }
		    		 }
		    			 dmd = topic.getDemandeUser(user.getLogin());
		    		 try {
			    		 TimeUnit.MILLISECONDS.sleep(200);
					} catch (Exception e) {
						System.out.println("Probleme avec la fonction sleep(1)");
					}
		    	 }
		    	 user = auth.getUser();
		     }  
	        out.println("Vous n'etes pas connecté");
	}

}
