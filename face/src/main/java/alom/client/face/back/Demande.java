package alom.client.face.back;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Demande {
	
    private String sender;
    private String topic;
    private String groupeId;
    private String timestamp;
    
	public Demande() {
		super();
	}

	public Demande(String sender, String topic) {
		super();
		this.sender = sender;
		this.topic = topic;
	}
	
	public Demande(String sender, String topic, String groupeId) {
		super();
		this.sender = sender;
		this.topic = topic;
		this.groupeId = groupeId;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getGroupeId() {
		return groupeId;
	}

	public void setGroupeId(String groupeId) {
		this.groupeId = groupeId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Demande [sender=" + sender + ", topic=" + topic + ", groupeId=" + groupeId + ", timestamp=" + timestamp
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((groupeId == null) ? 0 : groupeId.hashCode());
		result = prime * result + ((sender == null) ? 0 : sender.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((topic == null) ? 0 : topic.hashCode());
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
		Demande other = (Demande) obj;
		if (groupeId == null) {
			if (other.groupeId != null)
				return false;
		} else if (!groupeId.equals(other.groupeId))
			return false;
		if (sender == null) {
			if (other.sender != null)
				return false;
		} else if (!sender.equals(other.sender))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (topic == null) {
			if (other.topic != null)
				return false;
		} else if (!topic.equals(other.topic))
			return false;
		return true;
	}
    
}
