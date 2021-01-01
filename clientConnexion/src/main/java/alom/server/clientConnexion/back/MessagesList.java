package alom.server.clientConnexion.back;

import java.util.ArrayList;


public class MessagesList {

private ArrayList<String> msgs;

	
	public MessagesList() {
		
	}


	public MessagesList(ArrayList<String> msgs) {
		
		this.msgs = msgs;
	}


	public ArrayList<String> getMsgs() {
		return msgs;
	}


	public void setMsgs(ArrayList<String> msgs) {
		this.msgs = msgs;
	}
	
	

}
