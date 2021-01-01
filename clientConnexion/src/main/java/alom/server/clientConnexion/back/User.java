package alom.server.clientConnexion.back;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class User {
	
	private String nom;
	private String prenom;
	
	//critere d'initité
	private String login;
	private String pwd;
	private String token;
    private String topic;
    private String groupeId;
	
	public User() {
		super();
	}
	
	
	public User(String login, String pwd) {
		super();
		this.login = login;
		this.pwd = pwd;
	}
	
	public User(String login, String pwd, String topic) {
		super();
		this.login = login;
		this.pwd = pwd;
		this.topic = topic;
	}

	public User(String nom, String prenom, String login, String pwd) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.pwd = pwd;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}
	
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getGroupeId() {
		return this.login;
	}


	@Override
	public String toString() {
		return "User [nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", pwd=" + pwd + ", token=" + token
				+ ", topic=" + topic + ", groupeId=" + groupeId + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		if (pwd == null) {
			if (other.pwd != null)
				return false;
		} else if (!pwd.equals(other.pwd))
			return false;
		return true;
	}
	
}
