package alom.client.face.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import alom.client.face.back.Message;
import alom.client.face.back.User;

public class DemandeThread extends Thread{

    private BufferedReader in ;
	private String response;
	private Message msg;
	private Socket socket;
	private User user;
	private PrintWriter out ;
	private boolean On = true;
	
	public DemandeThread(User user) throws IOException{
		
		this.socket = new Socket("127.0.0.1",8082);
		this.out = new PrintWriter(socket.getOutputStream());
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.user = user;
		out.println(user.getToken());
		out.flush();
	}
	
	@Override
    public void run() {
       try {
    	 response = in.readLine();
    	 msg = convertToMessage(response);
         while(On){
        	if(!msg.getContent().equals("**disconnect**") || !msg.getSender().equals(user.getLogin())) {
        		recevoirAndDisplay(msg);
                response = in.readLine();
                msg = convertToMessage(response);
        	}
        	
         }
         System.out.println("Serveur déconecté");
         in.close();
         socket.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
    }
	
	private Message convertToMessage(String response) {
		
			if(response!="") {
				String[] array = response.split(" : ");
				return array.length ==4 ?  new Message(array[0], array[1], array[2],
						formatDate(array[3])) : null;
			}
				return null;
	}
	
	private void recevoirAndDisplay(Message msg) {
		if(msg!=null && !msg.getSender().equals(user.getLogin())) {
		System.out.println("                   "+msg.getContent());
		System.out.println("                   [ "+msg.getSender().toUpperCase()+" : "+msg.getTimestamp()+" ]" );
		}
	}
	
	private String formatDate(String date) {
		return date.replace('T', ' ').substring(0,19);
	}
	
	public void turnOff() {
		this.On = false;
	}
	
}
