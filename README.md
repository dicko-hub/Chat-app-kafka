# Chat-app-kafka
## Architecture de code:
Dans cette partie on explique les rôles des classes majeures qui font tourner le système de chat.
#### Face :
Ici nous avons deux classes importantes dans les packages :
##### alom.client.face.app : la classe Client permet de lancer la connexion, elle contient le code de login et
d’envoi de messages puis de déconnexion.
##### alom.client.face.socket : la classe DemandeThread est instanciée par la classe Client après réception et
confirmation des infos utilisateur, cette classe est un thread qui reçoit un user et crée une connexion socket
vers le serveur socket en lui envoyant le token de l’utilisateur en question et maintient ensuite la connexion
pour recevoir les messages retour pour les afficher en console.
Ces deux classes se basent sur les ressources et services des autres packages pour communiquer avec les
autres services du système.
#### Connexion :
Le package le plus important de ce service est alom.server.connexion.ressource, il contient la classe
userRessource et connexionRessource qui ont pour rôle de gérer les opérations CRUD sur un utilisateur et de
connecté, vérifier la connexion et déconnecté un utilisateur.
#### ClientConnexion :
Ici nous avons deux classes importantes dans les packages :
##### alom.server.clientConnexion.App : la classe Retour permet de lancer le serveur socket d’écoute
##### alom.server.clientConnexion.socket : la classe Server procède à une écoute sur un port et ouvre un thread
de gestion par user à chaque connexion en lui fournissant le socket crée. Le thread qui reçoit le socket est une
instance de la classe ServerThread.
ServerThread reçoit un socket et lit immédiatement le token reçu du client, il procède à sa vérification et
entame ensuite la consommation des messages kafka disponible sur le topic courant de cet utilisateur.
Ces trois classes se basent sur les ressources et services des autres packages pour communiquer avec les
autres services du système.
#### Back :
Le package le plus important de ce service est alom.server.back.ressource, il contient la classe
TopicRessource qui a pour rôle de gérer la création et consommation de messages.

## Les scénarios de tests :
Pour tester ce système vous pourrez suivre ce scénario, dans un premier temps il faut démarrer :
• zookeeper-server-start.sh
• kafka-server-start.sh
• le serveur tomcat avec le service Back et Connexion
• lancer la classe Retour.java du service ClientConnexion
nous allons procéder aux tests côté client, il s’agit d’une story de discussion avec trois (3) clients A, B et C
### Communication OneToOne :
• lancer la classe Client du service Face ;
• entrer le login et password de A dans la console ainsi que le channel principal ;
• lancer la classe Client du service Face ;
• entrer le login et password de B dans la console ainsi que le channel principal ;
• écrire des messages d’une part et de l’autre des consoles des deux utilisateurs afin de voir que la
discussion fonctionne ;
• lancer la classe Client du service Face ;
• entrer le login et password de C dans la console ainsi que le channel secondaire ;
• écrire un message à partir de C pour être sûr que A et B ne le reçoivent pas ;
• écrire un message de A et vérifier que B le reçoit mais pas C ;
### Comunication OneToMany :
• écrire la commande « **change** » puis « secondaire » dans la console de B pour basculer dans
le channel ou se trouve C ;
• écrire un message avec C pour vérifier que seul B reçoit ce message pas A ;
• déconnecté A avec la commande « **disconnect** »
• lancer la classe Client du service Face ;
• entrer le login et password de A dans la console ainsi que le channel secondaire ;
• écrire un message avec B pour vérifier que A et C le reçoivent ;
• alterner l ‘envoi de messages avec les trois utilisateurs pour vérifier le fonctionnement du
système ;
• déconnecté les utilisateurs les uns après les autres en envoyant des messages pour vérifier qu’un
utilisateur déconnecté n’en reçoit plus et que les autres sont informés du départ d’un des leurs ;
