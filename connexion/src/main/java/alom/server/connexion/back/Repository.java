package alom.server.connexion.back;

import java.util.HashMap;

public class Repository {


	private static HashMap<String,User> users = new HashMap<String,User> ();
	private static HashMap<String,User> tokens = new HashMap<String,User> ();
	
	private static final Repository INSTANCE = new Repository();
	
	private  Repository() {
		 
	}
	
	 public static Repository getInstance(){
		 return INSTANCE;
	 }
	
	 public HashMap<String,User> getUsers(){
		 return users;
	 }
	 
	 public HashMap<String,User> getTokens(){
		 return tokens;
	 }
}
