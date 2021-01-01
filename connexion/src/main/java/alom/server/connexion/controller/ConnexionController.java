package alom.server.connexion.controller;

import java.util.HashMap;

import alom.server.connexion.back.Repository;
import alom.server.connexion.back.SecureTokenGenerator;
import alom.server.connexion.back.User;

public class ConnexionController {
	
	//token and user
	private static HashMap<String,User> TokenRepository = Repository.getInstance().getTokens();

	UserController userController = new UserController();
	
	public String connect(User user) {
		User userGet = userController.getUser(user.getLogin());
		String login = user.getLogin();
		String pwd = user.getPwd();
		if (userGet.getLogin().equals(login)) {
			if(userGet.getPwd().equals(pwd)) {
				String token = tokenGenerator(userGet);
				TokenRepository.put(token, userGet);
				return token;
			}
	    }
		return null;
		
	}
	
	public User disconnect(String token) {
		if (TokenRepository.containsKey(token)) {
			
			return TokenRepository.remove(token);
		}
		
		return null;
		
	}
	
	public User isconnect(String token) {
		if (TokenRepository.containsKey(token)) {
			return TokenRepository.get(token);
		} 
	return null;
	}

	private String tokenGenerator(User user) {
		//algorithme
		return SecureTokenGenerator.nextToken();
	}
}
