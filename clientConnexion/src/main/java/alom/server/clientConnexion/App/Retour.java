package alom.server.clientConnexion.App;

import alom.server.clientConnexion.socket.Server;

public class Retour 
{
    public static void main( String[] args )
    {
    	//Lancement du serveur socket
        
    	Thread server = new Server();
        server.start();
    	
        System.out.println("connexion en attente");
        
    }
}
