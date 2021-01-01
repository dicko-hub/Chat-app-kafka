package alom.server.clientConnexion.back;

import java.util.ArrayList;

public class UsersList {

private ArrayList<User> Users;

	
	public UsersList() {
		
	}


	public UsersList(ArrayList<User> Users) {
		
		this.Users = Users;
	}


	public ArrayList<User> getUsers() {
		return Users;
	}


	public void setUsers(ArrayList<User> Users) {
		this.Users = Users;
	}
	
	

}
