package alom.server.connexion.controller;

import java.util.ArrayList;
import java.util.HashMap;
import alom.server.connexion.back.*;

public class UserController {
	
	//login and user
	private static HashMap<String,User> UserRepository = Repository.getInstance().getUsers();
	
	
	
	public UsersList getUsers() {
		
		ArrayList<User> users = new ArrayList<User>(UserRepository.values());
		UsersList listes = new UsersList(users);
		
	    return listes;
	}
	
	public User getUser(String login) {
		if (UserRepository.containsKey(login)) {
			return UserRepository.get(login);
	    } 
		return null;
	    
	}
	
	public User addUser(User user) {
		
		boolean status = true;
		
	     if(user.getLogin() == null || user.getLogin().equals("")){
	    	 status = false;
	     }
	     
	     if(user.getPwd() == null || user.getPwd().equals("")){
	    	 status = false;
	     }
	     
	     for(User base : UserRepository.values()) {
				if(base.equals(user))
					status =  false;
		    } 
	     
	     if(status) {
	    	 UserRepository.put(user.getLogin(),user);
	    	 return user;
	     }
	     return null;
	     
	}
	
	public User updateUser( String login, User user) {

		if (UserRepository.containsKey(login)) {
	        
	    	UserRepository.put(login, user);
	    	return user;
	    } 
		return null;
	    
	}
	
	public User deleteUser(String login) {

		
		if (UserRepository.containsKey(login)) {
			
			return UserRepository.remove(login);
		}
		
		return null;
		
	}
	
	public void deleteUsers() {

		UserRepository.clear();
	}
	
}
